package io.renren.modules.sys.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.modules.sys.dao.LogSysDao;
import io.renren.modules.sys.entity.LogSysEntity;
import io.renren.modules.sys.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("LogService")
public class LogServiceImpl extends ServiceImpl<LogSysDao, LogSysEntity> implements LogService {

    private final LogSysDao logSysDao;

    @Autowired
    public LogServiceImpl(LogSysDao logSysDao) {
        this.logSysDao = logSysDao;
    }

    /**
     * 添加日志
     * @param logSysEntity 日志对象
     * @return int
     */
    @Override
    public int addLog(LogSysEntity logSysEntity) {
        return logSysDao.addLog(logSysEntity);
    }
}