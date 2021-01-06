package io.renren.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.sys.entity.LogSysEntity;

import java.util.Map;

public interface LogService extends IService<LogSysEntity> {

    int addLog(LogSysEntity logSysEntity);

    /**
     * 查询所有的日志
     * @return 日志LogVO
     */
    PageUtils findAllLog(Map<String, Object> params);
}