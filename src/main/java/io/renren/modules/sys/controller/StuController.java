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
        logSysEntity.setOperation("学员添加");
        logSysEntity.setDescription(stuVO.getDescription());
        logService.addLog(logSysEntity);
        return R.ok();
    }

    /**
     * 学生列表的查询
     *
     * @return 学生列表
     */
    @GetMapping("/list")
    public R selectAllStu(@RequestParam Map<String, Object> params) {
        PageUtils stu = stuService.selectAllStu(params);
        return R.ok().put("stuList", stu);
    }

    /**
     * 通过学生的id查询学生的详情
     *
     * @param stuId 学生的id
     * @return 学生信息
     */
    @GetMapping("/info/{stuId}")
    public R selectStuInfo(@PathVariable("stuId") Integer stuId) {
        StuVO stuVO = stuService.selectStuInfo(stuId);
        return R.ok().put("stuVO", stuVO);
    }

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
            return R.error("删除失败，密码错误！");
        } else {
            int i = subjectService.setDown(stuId);
            if (i == 0) {
                return R.error();
            } else {
                return R.ok();
            }
        }
    }
}