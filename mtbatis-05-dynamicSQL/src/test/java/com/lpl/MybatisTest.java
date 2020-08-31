package com.lpl;

import com.lpl.bean.QueryVo;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MybatisTest {

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
     * 测试查询所有用户
     */
    @Test
    public void findAllTest(){
        //查询所有用户
        List<User> users = userDao.findAll();
        for (User user : users){
            System.out.println(user);
        }
    }
    /**
     * 根据id查询用户
     */
    @Test
    public void findOneTest(){
        //根据id查询用户
        User user = userDao.findById(51);
        System.out.println(user);
    }
    /**
     * 根据用户名模糊查询用户
     */
    @Test
    public void findByNameTest(){
        //根据用户名称模糊查询用户
        //在此处拼接模糊查询条件占位符可以使sql语句预编译，执行效率相对较高
        List<User> users = userDao.findByName("%斑斑七号%");
        for (User user : users){
            System.out.println(user);
        }
    }
    /**
     * 根据查询条件查询用户
     */
    @Test
    public void findByConditionTest(){
        //创建用户查询条件
        User user = new User();
        user.setUsername("%斑斑%");
        user.setSex("男");
        //根据查询条件查询用户
        List<User> users = userDao.findUserByCondition(user);
        for (User u : users){
            System.out.println(u);
        }
    }
    /**
     * 查询用户id是否在id列表中
     */
    @Test
    public void findUserInIdsTest(){
        //创建查询条件对象
        QueryVo vo = new QueryVo();
        List<Integer> ids = new ArrayList<Integer>();
        ids.add(48);
        ids.add(51);
        ids.add(53);
        vo.setIds(ids);
        //根据查询条件查询用户
        List<User> users = userDao.findUserInIds(vo);
        for (User user : users){
            System.out.println(user);
        }
    }
    //----------------------------------------------------------------------------
    /**
     * 根据封装的查询条件实体查询用户
     */
    @Test
    public void findByVoTest(){
        QueryVo vo = new QueryVo();
        User user = new User();
        user.setUsername("%斑斑%");
        vo.setUser(user);
        //执行模糊查询
        List<User> users = userDao.findUserByVo(vo);
        for (User u : users){
            System.out.println(u);
        }
    }
}
