package cn.qlu.yhy.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import cn.qlu.yhy.dao.IUserDao;
import cn.qlu.yhy.model.User;
import cn.qlu.yhy.model.enumpack.Sex;
import cn.qlu.yhy.utils.DBUtil;


public class UserDaoImpl implements IUserDao{

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public User getUser(User user) {

        try {
            conn = DBUtil.getConnection();

            String sql = "SELECT id, username, password, age, sex, email, birthday, " +
                    "telephone, address, role, credit_score, money_score, create_date, last_login_date " +
                    "from user where `username` = ? and `password` = ?";

            ps = conn.prepareStatement(sql);
            ps.setString(1, user.username);
            ps.setString(2, user.password);

            rs = ps.executeQuery();
            if (rs.next()) {
                user.setId(rs.getInt("id"));
                user.setAge(rs.getInt("age"));

                int sex = rs.getInt("sex");
                user.setSex(sex == 0 ? Sex.MALE : Sex.FEMALE);

                user.setEmail(rs.getString("email"));
                user.setBirthday(rs.getDate("birthday"));
                user.setTele(rs.getString("telephone"));
                user.setAddress(rs.getString("address"));
                //user.setRole(role);
                user.setCreditScore(rs.getInt("credit_score"));
                user.setMoneyScore(rs.getInt("money_score"));
                user.setCreateDate(rs.getDate("create_date"));
                user.setLastLoginDate(rs.getDate("last_login_date"));
            } else {
                user = null;
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return user;
    }

    public User getUserByName(String userName) {
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
