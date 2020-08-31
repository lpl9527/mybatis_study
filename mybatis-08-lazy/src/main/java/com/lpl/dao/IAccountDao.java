package com.lpl.dao;

import com.lpl.bean.Account;

import java.util.List;

public interface IAccountDao {

    /**
     * 查询所有账户，包含账户所属用户信息
     */
    List<Account> findAll();
    /**
     * 根据用户id查询用户下的所有账户
     */
    List<Account> findByUid(Integer uid);
}
