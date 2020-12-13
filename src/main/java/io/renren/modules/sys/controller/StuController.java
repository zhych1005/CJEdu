package io.renren.modules.sys.controller;


import io.renren.common.utils.R;
import io.renren.modules.sys.entity.StuEntity;
import io.renren.modules.sys.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sys/stu")
public class StuController {

    private final StuService stuService;

    @Autowired
    public StuController(StuService stuService) {
        this.stuService = stuService;
    }

    @PostMapping("/add")
    public R addStu(StuEntity stuEntity) {
        stuService.addStu(stuEntity);
        return R.ok();
    }
}