package cn.qlu.yhy.service;

import cn.qlu.yhy.model.User;

public interface ILoginService {

    public User login(String username, String password);
}
