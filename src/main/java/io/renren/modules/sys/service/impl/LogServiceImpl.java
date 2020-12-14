package io.renren.modules.sys.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.modules.sys.dao.LogDao;
import io.renren.modules.sys.entity.LogEntity;
import io.renren.modules.sys.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("LogService")
public class LogServiceImpl extends ServiceImpl<LogDao, LogEntity> implements LogService {

    private final LogDao logDao;

    @Autowired
    public LogServiceImpl(LogDao logDao) {
        this.logDao = logDao;
    }

    @Override
    public int addLog(LogEntity logEntity) {
        return logDao.addLog(logEntity);
    }
}