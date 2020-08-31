package com.lpl.dao;

import com.lpl.bean.User;

import java.util.List;

public interface IUserDao {
    /**
     * 查询所有用户
     */
    List<User> findAll();
    /**
     * 根据id查询用户信息
     */
    User findById(Integer userId);
    /**
     * 更新用户信息
     */
    void updateUser(User user);

}
