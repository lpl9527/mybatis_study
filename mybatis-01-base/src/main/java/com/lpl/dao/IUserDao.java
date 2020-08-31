package com.lpl.dao;

import com.lpl.bean.User;

import java.util.List;

/**
 * 用户Dao接口
 */
public interface IUserDao {
    /**
     * 查询所有用户
     */
    List<User> findAll();
}
