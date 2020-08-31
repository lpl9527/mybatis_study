package com.lpl.dao.impl;

import com.lpl.bean.User;
import com.lpl.dao.IUserDao;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class UserDaoImpl implements IUserDao {

    //SqlSessionFactory工厂对象
    private SqlSessionFactory factory;
    //为了保证SqlSessionFactory一定有值，将其放入UserDaoImpl构造器中传入
    public UserDaoImpl(SqlSessionFactory factory){
        this.factory = factory;
    }

    /**
     * 查询所有用户
     */
    public List<User> findAll(){
        //1.使用SqlSessionFactory工厂创建SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //2.使用SqlSession执行查询所有方法
        //此处方法的参数应该指定方法所在的名称空间(所在接口)加上方法名称
        List<User> users = sqlSession.selectList("com.lpl.dao.IUserDao.findAll");
        //3.关闭资源
        sqlSession.close();
        //4.返回结果
        return users;
    }
}
