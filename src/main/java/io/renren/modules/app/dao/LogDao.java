/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package io.renren.modules.app.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.renren.modules.app.entity.LogEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 日志
 */
@Mapper
public interface LogDao extends BaseMapper<LogEntity> {

    int buildLog(LogEntity logEntity);
}