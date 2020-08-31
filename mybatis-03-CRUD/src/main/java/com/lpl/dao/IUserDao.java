package com.lpl.dao;

import com.lpl.bean.QueryVo;
import com.lpl.bean.User;

import java.util.List;

public interface IUserDao {
    /**
     * 查询所有用户
     */
    List<User> findAll();
    /**
     * 保存用户
     */
    void saveUser(User user);
    /**
     * 更新用户
     */
    void updateUser(User user);
    /**
     * 根据id删除用户
     */
    void deleteUser(Integer id);
    /**
     * 根据id查询用户信息
     */
    User findById(Integer userId);
    /**
     * 根据用户名称模糊查询用户
     */
    List<User> findByName(String username);
    /**
     * 查询总用户数
     */
    int findTotal();

    //---------------------------------------------------------------------
    /**
     * 根据封装的参数类来查询
     */
    List<User> findUserByVo(QueryVo vo);
}
