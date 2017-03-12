package cn.qlu.yhy.dao.mybatisImp;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import cn.qlu.yhy.dao.IUserDao;
import cn.qlu.yhy.model.User;

public class UserDaoImpl implements IUserDao {
    @Autowired
    private SqlSessionTemplate sst;

    private static final String CLASS_NAME = User.class.getName();


    public User getUserByName(String userName) {
        return sst.selectOne(CLASS_NAME + ".getUserByName", userName);
    }

    public User getUser(User user) {
        // TODO Auto-generated method stub
        return null;
    }

    public boolean createUser(User user) {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean deleteUser(User user) {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean updateUser(User user) {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean getUserExistByName(String userName) {
        // TODO Auto-generated method stub
        return false;
    }

    public List<User> findUsers() {
        // TODO Auto-generated method stub
        return null;
    }

}
