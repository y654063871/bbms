package cn.qlu.yhy.service.impl;

import cn.qlu.yhy.dao.IUserDao;
import cn.qlu.yhy.dao.impl.UserDaoImpl;
import cn.qlu.yhy.model.User;
import cn.qlu.yhy.service.IUserService;

public class UserServiceImpl implements IUserService{

    private static IUserDao userDao = new UserDaoImpl();

    public User GetUser(String username, String password) {
        User user = new User(username, password);

        user = userDao.getUser(user);

        return user;
    }



}
