package io.renren.modules.sys.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.modules.sys.dao.SubjectDao;
import io.renren.modules.sys.entity.SubjectEntity;
import io.renren.modules.sys.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SubjectService")
public class SubjectServiceImpl extends ServiceImpl<SubjectDao, SubjectEntity> implements SubjectService {

    private final SubjectDao subjectDao;

    @Autowired
    public SubjectServiceImpl(SubjectDao subjectDao) {
        this.subjectDao = subjectDao;
    }

    /**
     * 添加学科
     * @param subjectEntity 学科对象
     * @return 添加的返回结果 int
     */
    @Override
    public int addSub(SubjectEntity subjectEntity) {
        return subjectDao.addSub(subjectEntity);
    }
}