package io.renren.modules.sys.controller;


import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.sys.entity.LogSysEntity;
import io.renren.modules.sys.entity.StuEntity;
import io.renren.modules.sys.entity.SubjectEntity;
import io.renren.modules.sys.service.LogService;
import io.renren.modules.sys.service.StuService;
import io.renren.modules.sys.service.SubjectService;
import io.renren.modules.sys.vo.StuVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/sys/stu")
public class StuController {

    private final StuService stuService;
    private final SubjectService subjectService;
    private final LogService logService;

    @Autowired
    public StuController(StuService stuService, SubjectService subjectService, LogService logService) {
        this.stuService = stuService;
        this.subjectService = subjectService;
        this.logService = logService;
    }

    /**
     * 新生的添加
     *
     * @param stuVO 学生
     * @return Ok
     */
    @PostMapping("/save")
    @Transactional
    public R addStu(@RequestBody StuVO stuVO) {
        //学生的重复性判断
        if (stuService.findStuByName(stuVO.getStuName()) >= 1) {
            return R.error("学生：" + stuVO.getStuName() + "已存在！");
        }
        // 学员添加
        StuEntity stuEntity = new StuEntity();
        stuEntity.setStuName(stuVO.getStuName());
        stuEntity.setMobile(stuVO.getMobile());
        stuEntity.setGender(stuVO.getGender());
        stuEntity.setAge(stuVO.getAge());
        stuEntity.setAddress(stuVO.getAddress());
        stuEntity.setParent(stuVO.getParent());
        stuEntity.setDescription(stuVO.getDescription());
        stuService.addStu(stuEntity);

        // 课程添加
        SubjectEntity subjectEntity = new SubjectEntity();
        subjectEntity.setStuId(stuEntity.getStuId());
        subjectEntity.setCost(stuVO.getCost());
        subjectEntity.setSubName(stuVO.getSubName());
        subjectEntity.setSubTotal(stuVO.getSubTotal());
        subjectEntity.setSubSurplus(stuVO.getSubTotal());
        subjectEntity.setSubUse(0);
        subjectService.addSub(subjectEntity);

        // 日志添加
        LogSysEntity logSysEntity = new LogSysEntity();
        logSysEntity.setStuId(stuEntity.getStuId());
        logSysEntity.setSubId(subjectEntity.getSubId());
        logSysEntity.setOperation(stuVO.getStuName() + "+" + stuVO.getSubName() + "~学员添加");
        logSysEntity.setDescription(stuVO.getDescription());
        logSysEntity.setStatus(1);
        logService.addLog(logSysEntity);
        return R.ok();
    }

    /**
     * 学生列表的查询
     * @return 学生列表
     */
    @GetMapping("/list")
    public R selectAllStu(@RequestParam Map<String, Object> params) {
        PageUtils stu = stuService.selectAllStu(params);
        return R.ok().put("stuList", stu);
    }

    /**
     * 通过学生的id查询学生的详情
     * @param stuId 学生的id
     * @return 学生信息
     */
    @GetMapping("/info/{stuId}")
    public R selectStuInfo(@PathVariable("stuId") Integer stuId) {
        StuVO stuVO = stuService.selectStuInfo(stuId);
        return R.ok().put("stuVO", stuVO);
    }

    /**
     * 学生信息的删除
     * @param params 学生信息
     * @return R删除状态
     */
    @PostMapping("/delete")
    @Transactional
    public R deleteStu(@RequestParam Map<String, Object> params) {
        int stuId = Integer.parseInt(params.get("stuId").toString());
        String psd = (String) params.get("psd");
        if (psd == null || !psd.equals("admin")) {
            return R.error("删除失败，密码错误！");
        } else {
            int i = stuService.deleteStuByStuId(stuId);
            if (i == 0) {
                return R.error();
            } else {
                StuVO stuVO = stuService.selectStuInfo(stuId);
                LogSysEntity logSysEntity = new LogSysEntity();
                logSysEntity.setStuId(stuVO.getStuId());
                logSysEntity.setSubId(stuVO.getSubId());
                logSysEntity.setOperation(stuVO.getStuName() + "+" + stuVO.getSubName() + "~学员删除");
                logSysEntity.setDescription(stuVO.getDescription());
                logSysEntity.setStatus(4);
                logService.addLog(logSysEntity);
                return R.ok();
            }
        }
    }

    @PostMapping("/setDown")
    @Transactional
    public R setDown(@RequestParam Map<String, Object> params) {
        int stuId = Integer.parseInt(params.get("stuId").toString());
        String psd = (String) params.get("psd");
        if (psd == null || !psd.equals("admin")) {
            return R.error("扣减课时失败，密码错误！");
        } else {
            //todo 线程安全的添加
            int i = subjectService.setDown(stuId);
            if (i == 0) {
                return R.error();
            } else {
                StuVO stuVO = stuService.selectStuInfo(stuId);
                LogSysEntity logSysEntity = new LogSysEntity();
                logSysEntity.setStuId(stuVO.getStuId());
                logSysEntity.setSubId(stuVO.getSubId());
                logSysEntity.setOperation(stuVO.getStuName() + "+" + stuVO.getSubName() + "~课时扣减,剩余" + stuVO.getSubSurplus() + "课时");
                logSysEntity.setDescription(stuVO.getDescription());
                logSysEntity.setStatus(3);
                logService.addLog(logSysEntity);
                return R.ok();
                //todo 微信通知的发送
            }
        }
    }

    @PostMapping("/update")
    @Transactional
    public R update(@RequestBody StuVO stuVO) {
        // 学生信息
        StuEntity stu = new StuEntity();
        stu.setStuId(stuVO.getStuId());
        stu.setStuName(stuVO.getStuName());
        stu.setMobile(stuVO.getMobile());
        stu.setGender(stuVO.getGender());
        stu.setAddress(stuVO.getAddress());
        stu.setAge(stuVO.getAge());
        stu.setParent(stuVO.getParent());
        // 课程信息
        SubjectEntity sub = new SubjectEntity();
        sub.setStuId(stuVO.getStuId());
        sub.setSubName(stuVO.getSubName());
        sub.setSubTotal(stuVO.getSubTotal());
        sub.setSubUse(stuVO.getSubUse());
        sub.setSubSurplus(stuVO.getSubSurplus());
        sub.setCost(stuVO.getCost());
        // 学生信息的修改
        int i = stuService.updateStuById(stu);
        // 课程信息的修改
        int j = subjectService.updateSubById(sub);

        if (i == 0 || j == 0) {
            return R.error("修改失败！");
        } else {
            LogSysEntity logSysEntity = new LogSysEntity();
            logSysEntity.setStuId(stuVO.getStuId());
            logSysEntity.setSubId(stuVO.getSubId());
            logSysEntity.setOperation(stuVO.getStuName() + "+" + stuVO.getSubName() + "~信息修改");
            logSysEntity.setDescription(stuVO.getDescription());
            logSysEntity.setStatus(2);
            logService.addLog(logSysEntity);
            return R.ok("修改成功");
        }
    }
}