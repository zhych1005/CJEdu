package io.renren.modules.sys.controller;


import io.renren.common.utils.R;
import io.renren.modules.sys.entity.LogSysEntity;
import io.renren.modules.sys.entity.StuEntity;
import io.renren.modules.sys.entity.SubjectEntity;
import io.renren.modules.sys.service.LogService;
import io.renren.modules.sys.service.StuService;
import io.renren.modules.sys.service.SubjectService;
import io.renren.modules.sys.vo.StuVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/save")
    public R addStu(@RequestBody StuVO stuVO) {
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
        subjectEntity.setSubName(stuVO.getStuName());
        subjectEntity.setSubTotal(stuVO.getTotal());
        subjectEntity.setSubSurplus(stuVO.getTotal());
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
}