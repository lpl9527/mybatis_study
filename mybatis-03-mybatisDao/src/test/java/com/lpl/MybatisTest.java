package com.lpl;

import com.lpl.bean.User;
import com.lpl.dao.IUserDao;
import com.lpl.dao.impl.UserDaoImpl;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
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
        //3.使用工厂对象创建dao对象
        userDao = new UserDaoImpl(factory);
    }
    /**
     * 释放资源
     */
    @After      //用于在测试方法执行之后执行
    public void destroy() throws Exception{
        in.close();
    }
    //------------------------------------------------------------------------------------
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
     * 测试保存用户
     */
    @Test
    public void saveTest(){
        //创建用户对象
        User user = new User();
        user.setUsername("斑斑七号");
        user.setAddress("峡谷1号监狱");
        user.setSex("男");
        user.setBirthday(new Date());
        System.out.println("插入用户之前： " + user);
        //保存用户
        userDao.saveUser(user);
        System.out.println("插入用户之后： " + user);
    }
    /**
     * 更新用户
     */
    @Test
    public void updateUser(){
        User user = new User();
        user.setId(53);
        user.setUsername("青莲居士");
        user.setSex("男");
        user.setAddress("合肥市");
        user.setBirthday(new Date());
        //更新用户
        userDao.updateUser(user);
    }
    /**
     * 根据id删除用户
     */
    @Test
    public void deleteTest(){
        //删除用户
        userDao.deleteUser(54);
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
        List<User> users = userDao.findByName("%斑斑%");
        for (User user : users){
            System.out.println(user);
        }
    }
    /**
     * 查询总用户数
     */
    @Test
    public void findTotalTest(){
        int total = userDao.findTotal();
        System.out.println("总用户数为： " + total);
    }
}
