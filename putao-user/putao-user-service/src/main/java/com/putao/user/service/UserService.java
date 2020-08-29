package com.putao.user.service;

import com.putao.user.mapper.UserMapper;
import com.putao.user.pojo.User;
import com.putao.common.utils.utils.BCrypt;
import com.putao.common.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.Calendar;
import java.util.List;

/**
 * @version 1.0
 * @author: panpan
 * @create: 2020-08-21 00:49
 **/
@Service
@Transactional
public class UserService {

  @Autowired
  private UserMapper userMapper;

  @Autowired
  private IdWorker idWorker;

  /**
   * 校验用户名是否可用(有没有重复)
   *
   * @param user
   * @return
   */
  public Boolean checkUser(User user) {

    Example example = new Example(User.class);
    Example.Criteria criteria = example.createCriteria();
    if (user != null) {
      criteria.andEqualTo("username",user.getUsername());
    }
    User user1 = userMapper.selectOneByExample(example);
    //若用户数量为0,返回true,用户名不存在,可以注册
    return user1 == null;
  }


  /**
   * 查询用户名和密码查询是否匹配
   * @param user
   * @return
   */
  public Boolean checkLogin(User user) {
    //根据用户名查询用户信息
    User userMatchUserName = this.findUserByCondition(user);

    String password = user.getPassword();
    //判断密码对不对,并返回
    return BCrypt.checkpw(password, userMatchUserName.getPassword());

  }

   /**
   * 增加用户
   *
   * @param user
   * @return
   */
  public Boolean addUser(User user) {
    //设置id
    user.setId(idWorker.nextId() + "");
    //生成盐
    String gensalt = BCrypt.gensalt();
    String password = user.getPassword();

    String hashpw = BCrypt.hashpw(password, gensalt);

    //加密,重新赋值给user
    user.setPassword(hashpw);
    user.setGensalt(gensalt);

    return this.userMapper.insertSelective(user) == 1;

  }

  /**
   * 根据id删除用户
   *
   * @param userId
   * @return
   */
  public Boolean deleteUserById(String userId) {

    return this.userMapper.deleteByPrimaryKey(userId) == 1;
  }

  /**
   * 根据用户名删除用户
   *
   * @param username
   * @return
   */
  public boolean deleteUserByUsername(String username) {
    Example example = new Example(User.class);
    Example.Criteria criteria = example.createCriteria();
    criteria.andEqualTo("username", username);

    return this.userMapper.deleteByExample(example) == 1;
  }

  /**
   * 根据id修改用户
   *
   * @param user
   * @return
   */
  public Boolean updateUserByUserId(User user) {

    return this.userMapper.updateByPrimaryKeySelective(user) == 1;
  }


  /**
   * 根据条件查询用户(此时id不能为null)
   *
   * @param user
   * @return
   */
  public User findUserByCondition(User user) {
    Example example = new Example(User.class);
    Example.Criteria criteria = example.createCriteria();
    if (!StringUtils.isEmpty(user.getUsername()))
      criteria.andEqualTo("username", user.getUsername());
    if (!StringUtils.isEmpty(user.getId())){
      criteria.andEqualTo("id", user.getId());
    }
    return this.userMapper.selectOneByExample(example);
  }


  /**
   * 根据用户名和密码查询用户
   * @param username
   * @param password
   * @return
   */
  public User queryUser(String username, String password) {
    Example example = new Example(User.class);
    Example.Criteria criteria = example.createCriteria();
    criteria.andEqualTo("username",username);
    User user = this.userMapper.selectOneByExample(example);
    boolean b = BCrypt.checkpw(password, user.getPassword());
    if (b) {
      return user;
    }else {
      return null;
    }
  }
}
