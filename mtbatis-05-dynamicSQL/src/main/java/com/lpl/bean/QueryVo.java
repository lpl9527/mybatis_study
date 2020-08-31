package com.lpl.bean;

import java.util.List;

/**
 * 封装的查询条件
 */
public class QueryVo {

    //封装在用户中的查询条件
    private User user;

    //定义id查询集合列表
    private List<Integer> ids;

    //其它查询条件
    private String otherParam;

    public String getOtherParam() {
        return otherParam;
    }

    public void setOtherParam(String otherParam) {
        this.otherParam = otherParam;
    }

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
