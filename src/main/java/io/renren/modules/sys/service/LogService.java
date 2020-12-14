package io.renren.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.modules.sys.entity.LogEntity;

public interface LogService extends IService<LogEntity> {

    int addLog(LogEntity logEntity);
}