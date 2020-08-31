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

/**
 * 测试二级缓存
 */
public class SecondLevelCacheTest {

    private InputStream in;
    private SqlSessionFactory factory;

    /**
     * 初始化对象
     */
    @Before     //用于在测试方法执行之前执行
    public void init() throws Exception{
        //1.读取mybatis全局配置文件，生成输入字节流
        in = Resources.getResourceAsStream("sqlMapConfig.xml");
        //2.创建SqlSessionFactory
        factory = new SqlSessionFactoryBuilder().build(in);
    }
    /**
     * 释放资源
     */
    @After      //用于在测试方法执行之后执行
    public void destroy() throws Exception{
        in.close();
    }
    /**
     * 测试二级缓存
     *      二级缓存：
     *      它是指Mybatis中SqlSessionFactory对象的缓存。
     *      由同一个SqlSessionFactory对象创建的SqlSesion共享其缓存。
     *      二级缓存中存放的不是对象，而是数据。
     *      二级缓存使用：
     *          第一步：配置Mybatis全局配置文件。
     *          第二布：配置映射文件支持。
     *          第三步：在当前的操作（select标签）中支持。
     */
    @Test
    public void secondLevelCacheTest(){

        SqlSession sqlSession1 = factory.openSession();
        IUserDao userDao1 = sqlSession1.getMapper(IUserDao.class);
        User user1 = userDao1.findById(48);
        System.out.println(user1);
        sqlSession1.close();

        SqlSession sqlSession2 = factory.openSession();
        IUserDao userDao2 = sqlSession2.getMapper(IUserDao.class);
        User user2 = userDao2.findById(48);
        System.out.println(user2);

        System.out.println(user1 == user2);
    }

}
