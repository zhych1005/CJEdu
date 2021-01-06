package io.renren.modules.sys.controller;


import io.renren.common.utils.R;
import io.renren.modules.sys.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/sys/stuLog")
public class StuLogController {

    private final LogService logService;

    @Autowired
    public StuLogController(LogService logService) {
        this.logService = logService;
    }

    @GetMapping("/list")
    public R findAllLog(@RequestParam Map<String, Object> params) {
        return R.ok().put("list", logService.findAllLog(params));
    }
}