package cn.qlu.yhy.test;

import org.junit.Test;

import cn.qlu.yhy.dao.impl.UserDaoImpl;
import cn.qlu.yhy.model.User;
import cn.qlu.yhy.service.impl.UserServiceImpl;

public class Test1 {

    @Test
    public void testUserDao() {
        UserDaoImpl userdao = new UserDaoImpl();

        User user = new User("yang", "123");
        userdao.getUser(user);

        System.out.println(user.getAddress());
    }

    @Test
    public void testUserService() {
        UserServiceImpl userService = new UserServiceImpl();

        User user = userService.GetUser("yang", "123");

        System.out.println(user.getAddress());
    }
}
