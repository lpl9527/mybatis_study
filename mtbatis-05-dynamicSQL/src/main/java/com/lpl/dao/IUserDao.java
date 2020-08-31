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
     * 根据id查询用户信息
     */
    User findById(Integer userId);
    /**
     * 根据用户名称模糊查询用户
     */
    List<User> findByName(String username);
    /**
     * 根据条件查询
     */
    List<User> findUserByCondition(User user);
    /**
     * 查询id在列表中的用户
     */
    List<User> findUserInIds(QueryVo vo);

    //---------------------------------------------------------------------
    /**
     * 根据封装的参数类来查询
     */
    List<User> findUserByVo(QueryVo vo);
}
