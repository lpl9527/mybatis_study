package com.lpl.bean;

/**
 * 封装的查询条件
 */
public class QueryVo {

    //封装在用户中的查询条件
    private User user;
    //其它查询条件
    private String otherParam;

    public String getOtherParam() {
        return otherParam;
    }

    public void setOtherParam(String otherParam) {
        this.otherParam = otherParam;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
