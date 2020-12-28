package io.renren.modules.sys.dao;

import io.renren.modules.sys.entity.StuEntity;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StuDaoTest extends TestCase {

    @Autowired
    private StuDao stuDao;

    @Test
    public void addStu() {
        StuEntity stuEntity = new StuEntity();
        stuEntity.setStuName("张12");
        stuEntity.setAddress("郑州");
        stuEntity.setGender(1);
        stuEntity.setMobile("123");
        stuEntity.setAge(12);
        stuEntity.setParent("年后");
        stuDao.addStu(stuEntity);
        System.out.println(stuEntity.getStuId());
    }

//    @Test
//    public void stuList() {
//        System.out.println(stuDao.selectAllStu());
//    }
}