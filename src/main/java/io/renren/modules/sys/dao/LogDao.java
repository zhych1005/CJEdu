package io.renren.modules.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.renren.modules.sys.entity.LogEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LogDao extends BaseMapper<LogEntity> {

    int addLog(LogEntity logEntity);
}