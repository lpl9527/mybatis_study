package com.lpl.dao.impl;

import com.lpl.bean.User;
import com.lpl.dao.IUserDao;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class UserDaoImpl implements IUserDao {

    private SqlSessionFactory factory;
    //为了保证factory有值，在创建dao实现类时就将其传入
    public UserDaoImpl(SqlSessionFactory factory){
        this.factory = factory;
    }
    /**
     * 查询所有用户
     */
    public List<User> findAll() {
        //1.根据factory获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //2.调用sqlSession中的方法，实现查询
        List<User> users = sqlSession.selectList("com.lpl.dao.IUserDao.findAll");//参数就是能定位到配置文件中具体的方法字符串（名称空间+方法id）
        //3.释放资源
        sqlSession.close();
        return users;
    }

    /**
     * 保存用户
     */
    public void saveUser(User user) {
        //1.根据factory获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //2.调用sqlSession中的方法，实现保存
        sqlSession.insert("com.lpl.dao.IUserDao.saveUser", user);
        //3.提交事务
        sqlSession.commit();
        //4.释放资源
        sqlSession.close();
    }

    /**
     * 更新用户
     */
    public void updateUser(User user) {
        //1.根据factory获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //2.调用sqlSession中的方法，实现更新
        sqlSession.update("com.lpl.dao.IUserDao.updateUser", user);
        //3.提交事务
        sqlSession.commit();
        //4.释放资源
        sqlSession.close();
    }

    /**
     * 根据id删除用户
     */
    public void deleteUser(Integer id) {
        //1.根据factory获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //2.调用sqlSession中的方法，实现删除
        sqlSession.update("com.lpl.dao.IUserDao.deleteUser", id);
        //3.提交事务
        sqlSession.commit();
        //4.释放资源
        sqlSession.close();
    }

    /**
     * 根据id查询用户
     */
    public User findById(Integer userId) {
        //1.根据factory获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //2.调用sqlSession中的方法，实现查询
        User user = sqlSession.selectOne("com.lpl.dao.IUserDao.findById", userId);//参数就是能定位到配置文件中具体的方法字符串（名称空间+方法id）
        //3.释放资源
        sqlSession.close();
        return user;
    }

    /**
     * 根据用户名称模糊查询用户
     */
    public List<User> findByName(String username) {
        //1.根据factory获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //2.调用sqlSession中的方法，实现查询
        List<User> users = sqlSession.selectList("com.lpl.dao.IUserDao.findByName", username);//参数就是能定位到配置文件中具体的方法字符串（名称空间+方法id）
        //3.释放资源
        sqlSession.close();
        return users;
    }

    /**
     * 查询用户总数
     */
    public int findTotal() {
        //1.根据factory获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //2.调用sqlSession中的方法，实现查询
        int count = sqlSession.selectOne("com.lpl.dao.IUserDao.findTotal");//参数就是能定位到配置文件中具体的方法字符串（名称空间+方法id）
        //3.释放资源
        sqlSession.close();
        return count;
    }
}
