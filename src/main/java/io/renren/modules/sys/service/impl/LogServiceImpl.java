package io.renren.modules.sys.service.impl;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.modules.sys.dao.LogSysDao;
import io.renren.modules.sys.entity.LogSysEntity;
import io.renren.modules.sys.service.LogService;
import io.renren.modules.sys.vo.LogVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

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
     */
    @Override
    public int addLog(LogSysEntity logSysEntity) {
        int i = logSysDao.addLog(logSysEntity);
        return i;
    }

    /**
     * 查询所有的日志
     * @return 日志LogVO
     */
    @Override
    public PageUtils findAllLog(Map<String, Object> params) {
        int pageNo = Integer.parseInt(params.get("page").toString());
        int pageSize = Integer.parseInt(params.get("limit").toString());
        Integer  status;
        try {
            status = Integer.parseInt(params.get("status").toString());
        } catch (Exception e) {
            status = null;
        }
        String stuName = (String) params.get("stuName");
        String subName = (String) params.get("subName");
        String level = (String) params.get("level");
        IPage<LogVO> page = logSysDao.findAllLog(new Page<>(pageNo, pageSize), stuName, subName, status, level);
        return new PageUtils(page);
    }
}