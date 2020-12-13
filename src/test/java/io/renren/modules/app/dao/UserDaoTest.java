package io.renren.modules.app.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTest extends TestCase {

    @Autowired
    private UserDao userDao;

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Test
    public void testSelectAllKeyboarder() {
//        ArrayList<KeyboarderVO> keyboarderVOS = userDao.selectAllKeyboarder();
//        String name = null;
//        ArrayList<String> list = new ArrayList<>();
//        for (KeyboarderVO keyboarderVO : keyboarderVOS) {
//            if (keyboarderVO.getAreaId() == 12) {
//                name = keyboarderVO.getAreaName();
//                for (UserVO userVO : keyboarderVO.getUserVOS()) {
//                    list.add(userVO.getUsername());
//                }
//            }
//        }
//        System.out.println(name);
//        System.out.println(list);
    }
}