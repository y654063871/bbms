package cn.qlu.yhy.model;

import java.util.Date;

import cn.qlu.yhy.model.enumpack.Role;
import cn.qlu.yhy.model.enumpack.Sex;

public class User {

    public int id;

    public String username;

    public String password;

    public Sex sex;

    public int age;

    public Date birthday;

    public String tele;

    public String email;

    public String address;

    public Role role;

    public int creditScore;

    public int moneyScore;

    public Date createDate;

    public Date lastLoginDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getTele() {
        return tele;
    }

    public void setTele(String tele) {
        this.tele = tele;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public int getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(int creditScore) {
        this.creditScore = creditScore;
    }

    public int getMoneyScore() {
        return moneyScore;
    }

    public void setMoneyScore(int moneyScore) {
        this.moneyScore = moneyScore;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public User() {
        super();
    }

    public User(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }

    public User(int id, String username, Sex sex, int age, Role role,
            int creditScore) {
        super();
        this.id = id;
        this.username = username;
        this.sex = sex;
        this.age = age;
        this.role = role;
        this.creditScore = creditScore;
    }

    public User(int id, String username, String password, Sex sex, int age,
            Date birthday, String tele, String email, String address,
            Role role, int creditScore, int moneyScore, Date createDate,
            Date lastLoginDate) {
        super();
        this.id = id;
        this.username = username;
        this.password = password;
        this.sex = sex;
        this.age = age;
        this.birthday = birthday;
        this.tele = tele;
        this.email = email;
        this.address = address;
        this.role = role;
        this.creditScore = creditScore;
        this.moneyScore = moneyScore;
        this.createDate = createDate;
        this.lastLoginDate = lastLoginDate;
    }





}
