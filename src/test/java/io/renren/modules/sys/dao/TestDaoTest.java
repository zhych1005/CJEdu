package io.renren.modules.sys.dao;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Array;
import java.util.Arrays;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TestDaoTest extends TestCase {
    @Autowired
    private TestDao testDao;

    @Test
    public void update() {
        Integer[] arr = {1, 2, 3};
        testDao.updateList(Arrays.asList(arr));
    }
}