package com.lpl;

import com.lpl.bean.User;
import com.lpl.dao.IUserDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class UserTest {

    private InputStream in;
    private SqlSession sqlSession;
    private IUserDao userDao;

    /**
     * 初始化对象
     */
    @Before     //用于在测试方法执行之前执行
    public void init() throws Exception{
        //1.读取mybatis全局配置文件，生成输入字节流
        in = Resources.getResourceAsStream("sqlMapConfig.xml");
        //2.创建SqlSessionFactory
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //3.获取SqlSession对象，参数设置是否自动提交
        sqlSession = factory.openSession(true);
        //4.获取dao的代理对象
        userDao = sqlSession.getMapper(IUserDao.class);
    }
    /**
     * 释放资源
     */
    @After      //用于在测试方法执行之后执行
    public void destroy() throws Exception{
        //提交事务
        //sqlSession.commit();
        sqlSession.close();
        in.close();
    }
    /**
     * 测试查询所有用户，包含此用户的所有角色
     */
    @Test
    public void findAllTest(){
        //查询所有用户
        List<User> users = userDao.findAll();
        for (User user : users){
            System.out.println("每一个用户的信息是：-----------");
            System.out.println(user);
            System.out.println(user.getRoles());
        }
    }

}
