package com.lpl.dao;

import com.lpl.bean.Account;

import java.util.List;

public interface IAccountDao {

    /**
     * 查询所有账户，包含账户所属用户信息
     */
    List<Account> findAll();
}
