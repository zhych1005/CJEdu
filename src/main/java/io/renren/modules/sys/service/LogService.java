package io.renren.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.modules.sys.entity.LogSysEntity;

public interface LogService extends IService<LogSysEntity> {

    int addLog(LogSysEntity logSysEntity);
}