package io.renren.modules.sys.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.modules.sys.dao.StuDao;
import io.renren.modules.sys.entity.StuEntity;
import io.renren.modules.sys.service.StuService;
import io.renren.modules.sys.vo.StuVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    /**
     *  学生列表的查询
     * @return List<StuVO>
     */
    @Override
    public List<StuVO> selectAllStu() {
        return stuDao.selectAllStu();
    }
}