package io.renren.modules.app.service;


import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.modules.app.entity.LogEntity;

/**
 * 用户
 *
 * @author Mark sunlightcs@gmail.com
 */
public interface LogService extends IService<LogEntity> {

    /**
     * 日志添加
     * @param logEntity 日志
     */
    void buildLog(LogEntity logEntity);
}
