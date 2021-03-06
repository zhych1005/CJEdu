package io.renren.modules.sys.controller;


import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.app.utils.MessageUtils;
import io.renren.modules.sys.entity.LogSysEntity;
import io.renren.modules.sys.entity.StuEntity;
import io.renren.modules.sys.entity.SubjectEntity;
import io.renren.modules.sys.service.LogService;
import io.renren.modules.sys.service.StuService;
import io.renren.modules.sys.service.SubjectService;
import io.renren.modules.sys.vo.DeductionVO;
import io.renren.modules.sys.vo.StuVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sys/stu")
public class StuController {

    private final StuService stuService;
    private final SubjectService subjectService;
    private final LogService logService;
    private final MessageUtils messageUtils;

    @Autowired
    public StuController(StuService stuService, SubjectService subjectService, LogService logService, MessageUtils messageUtils) {
        this.stuService = stuService;
        this.subjectService = subjectService;
        this.logService = logService;
        this.messageUtils = messageUtils;
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
        subjectEntity.setLevel(stuVO.getLevel());
        subjectEntity.setSubName(stuVO.getSubName());
        subjectEntity.setSubTotal(stuVO.getSubTotal());
        subjectEntity.setSubSurplus(stuVO.getSubTotal());
        subjectEntity.setSubUse(0);
        subjectService.addSub(subjectEntity);

        // 日志添加
        LogSysEntity convert = convert(stuVO);
        convert.setStuId(stuEntity.getStuId());
        convert.setSubId(subjectEntity.getSubId());
        convert.setOperation(stuVO.getStuName() + "+" + stuVO.getSubName() + "~学员添加");
        convert.setDescription(stuVO.getDescription());
        convert.setStatus(1);
        logService.addLog(convert);
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
                LogSysEntity convert = convert(stuVO);
                convert.setOperation(stuVO.getStuName() + "+" + stuVO.getSubName() + "~学员删除");
                convert.setStatus(4);
                logService.addLog(convert);
                return R.ok();
            }
        }
    }

    @PostMapping("/setDown")
    @Transactional
    public R setDown(@RequestParam Map<String, Object> params) {
        int stuId = Integer.parseInt(params.get("stuId").toString());
        int subSurplus = Integer.parseInt(params.get("subSurplus").toString());
        String psd = (String) params.get("psd");
        if (psd == null || !psd.equals("admin")) {
            return R.error("扣减课时失败，密码错误！");
        } else if (subSurplus == 0) {
            return R.error("课时已上完，请充值！");
        } else {
            int i = subjectService.setDown(stuId);
            if (i == 0) {
                return R.error();
            } else {
                DeductionVO deductionVO = stuService.findStuById(stuId);

                // 日志的写入
                LogSysEntity log = new LogSysEntity();
                log.setStuId(deductionVO.getStuId());
                log.setSubId(deductionVO.getSubId());
                log.setDescription(deductionVO.getDescription());
                log.setOperation(deductionVO.getStuName() + "+" + deductionVO.getSubName() + "~课时扣减,剩余" + deductionVO.getSubSurplus() + "课时");
                log.setStatus(3);
                logService.addLog(log);
                messageUtils.classNotify(deductionVO.getOpenId(), deductionVO, new Date());
                return R.ok();
            }
        }
    }

    /**
     * 学生信息的修改
     * @param stuVO 学生信息
     * @return R
     */
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
        sub.setLevel(stuVO.getLevel());
        sub.setSubTotal(stuVO.getSubTotal());
        sub.setSubUse(stuVO.getSubUse());
        sub.setSubSurplus(stuVO.getSubSurplus());
        sub.setCost(stuVO.getCost());
        // 学生信息的修改
        int i = stuService.updateStuById(stu);
        // 课程信息的修改
        int j = subjectService.updateSubById(sub);
        LogSysEntity convert = convert(stuVO);
        convert.setOperation(stuVO.getStuName() + "+" + stuVO.getSubName() + "~信息修改");
        convert.setStatus(2);
        logService.addLog(convert);
        if (i == 0 || j == 0) {
            return R.error("修改失败！");
        } else {
            return R.ok("修改成功");
        }
    }

    /**
     * 充值信息
     * @param sub 充值信息
     * @return 状态返回
     */
    @PostMapping("/recharge")
    @Transactional
    public R recharge(@RequestBody StuVO sub) {
        if (sub.getSubTotal() == null || sub.getCost() == null) {
            return R.error("请填写充值金额、课时！");
        }
        SubjectEntity subjectEntity = new SubjectEntity();
        BeanUtils.copyProperties(sub, subjectEntity);
        subjectEntity.setSubTotal(sub.getSubTotal() + sub.getSubSurplus());
        subjectEntity.setSubUse(0);
        subjectEntity.setSubSurplus(subjectEntity.getSubTotal());
        int recharge = subjectService.recharge(subjectEntity);
        LogSysEntity convert = convert(sub);
        convert.setOperation(sub.getStuName() + "+" + sub.getSubName() + "~会员充值");
        convert.setStatus(6);
        logService.addLog(convert);
        if (recharge == 0) {
            return R.error("充值失败！");
        } else {
            return  R.ok("充值成功！");
        }
    }

    @GetMapping("/allSetDown/{stu}/{pwd}")
    public R allSetDown(@PathVariable("stu") List<Integer> stu, @PathVariable("pwd") String pwd) {
        //密码校验
        if (pwd == null || !pwd.equals("admin")) {
            return R.error("扣减课时失败，密码错误！");
        }
        //查询所有的学生
        ArrayList<DeductionVO> deductionVOS = stuService.deductionInfo();
        System.out.println(deductionVOS);
        //查找选中的学生的剩余课时，没有的提示扣减失败
        for (DeductionVO deductionVO : deductionVOS) {
            for (Integer integer : stu) {
                if (deductionVO.getSubSurplus() == 0 && deductionVO.getStuId().equals(integer)) {
                    return R.error("扣减课时失败，存在0课时学员，请去除后重新扣减！");
                } else if (deductionVO.getStuId().equals(integer)) {
                    //批量扣减
                    subjectService.setDown(integer);
                    // 日志的写入
                    LogSysEntity log = new LogSysEntity();
                    log.setStuId(deductionVO.getStuId());
                    log.setSubId(deductionVO.getSubId());
                    log.setDescription(deductionVO.getDescription());
                    log.setOperation(deductionVO.getStuName() + "+" + deductionVO.getSubName() + "~课时扣减,剩余" + (deductionVO.getSubSurplus() - 1) + "课时");
                    log.setStatus(3);
                    logService.addLog(log);
                    messageUtils.classNotify(deductionVO.getOpenId(), deductionVO, new Date());
                }
            }
        }
        return R.ok();
    }

    private LogSysEntity convert(StuVO sub) {
        LogSysEntity logSysEntity = new LogSysEntity();
        logSysEntity.setStuId(sub.getStuId());
        logSysEntity.setSubId(sub.getSubId());
        logSysEntity.setDescription(sub.getDescription());
        return logSysEntity;
    }
}