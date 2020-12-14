package io.renren.modules.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.renren.modules.sys.entity.LogSysEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LogSysDao extends BaseMapper<LogSysEntity> {

    int addLog(LogSysEntity logSysEntity);
}