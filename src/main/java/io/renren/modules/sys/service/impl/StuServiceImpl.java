package io.renren.modules.sys.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.modules.sys.dao.StuDao;
import io.renren.modules.sys.entity.StuEntity;
import io.renren.modules.sys.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("StuService")
public class StuServiceImpl extends ServiceImpl<StuDao, StuEntity> implements StuService {

    private final StuDao stuDao;

    @Autowired
    public StuServiceImpl(StuDao stuDao) {
        this.stuDao = stuDao;
    }


    /**
     * 添加学生
     * @param stu 学生对象
     * @return int
     */
    @Override
    public int addStu(StuEntity stu) {
        return stuDao.addStu(stu);
    }
}