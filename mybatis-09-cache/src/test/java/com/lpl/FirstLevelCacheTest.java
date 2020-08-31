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

/**
 * 测试一级缓存
 */
public class FirstLevelCacheTest {

    private InputStream in;
    private SqlSessionFactory factory;
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
        factory = new SqlSessionFactoryBuilder().build(in);
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
     * 测试一级缓存
     *      一级缓存（默认开启）：
     *      它是指Mybatis中SqlSession对象的缓存。
     *      当我们执行查询后，查询到的结果会存到SqlSession对象为我们提供的Map区域中，当再次查询同样的数据时，
     *      mybatis会先去缓存中拿，如果有直接获取（获取到的是同一个对象），不查数据库
     *      当SqlSession对象消失，一级缓存也会随之被清空。
     */
    @Test
    public void firstLevelCacheTest(){

        User user1 = userDao.findById(48);
        System.out.println(user1);

        //--------------------模拟清空缓存--------------------------
        //关闭SqlSession
        /*sqlSession.close();
        //再次打开SqlSession
        sqlSession = factory.openSession();
        //获取dao代理对象
        userDao = sqlSession.getMapper(IUserDao.class);*/
        //-----------------------------------------------------------
        sqlSession.clearCache();    //清空缓存

        User user2 = userDao.findById(48);
        System.out.println(user2);

        System.out.println(user1 == user2);
    }

    /**
     * 测试一级缓存的同步
     *      当调用SqlSession的修改、添加、删除、commit()、close()等方法时，就会清空一级缓存。
     */
    @Test
    public void syncCacheTest(){
        //1.根据id查询用户
        User user1 = userDao.findById(51);
        System.out.println(user1);
        //2.更新用户信息
        user1.setUsername("鲁班2号");
        userDao.updateUser(user1);
        //3.再次查询用户信息
        User user2 = userDao.findById(51);
        System.out.println(user2);

        System.out.println(user1 == user2);
    }

}
