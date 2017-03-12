package cn.qlu.yhy.dao;

import java.util.List;

import cn.qlu.yhy.model.User;

public interface IUserDao {

    public User getUser(User user);

    public User getUserByName(String userName);

    public boolean createUser(User user);

    public boolean deleteUser(User user);

    public boolean updateUser(User user);

    public boolean getUserExistByName(String userName);

    public List<User> findUsers();

}
