package io.renren.modules.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.renren.modules.sys.entity.LogSysEntity;
import io.renren.modules.sys.vo.LogVO;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface LogSysDao extends BaseMapper<LogSysEntity> {

    /**
     * 日志的写入
     * @param logSysEntity 日志信息
     * @return 插入状态
     */
    int addLog(LogSysEntity logSysEntity);

    /**
     * 查询所有的日志
     * @return 日志LogVO
     */
    IPage<LogVO> findAllLog(Page<LogVO> page, String stuName, String subName, Integer status, String level);
}