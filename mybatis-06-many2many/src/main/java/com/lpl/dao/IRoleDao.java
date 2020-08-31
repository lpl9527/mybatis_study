package com.lpl.dao;

import com.lpl.bean.Role;

import java.util.List;

public interface IRoleDao {

    /**
     * 查询所有角色，包含该角色下的所有用户
     */
    List<Role> findAll();
}
