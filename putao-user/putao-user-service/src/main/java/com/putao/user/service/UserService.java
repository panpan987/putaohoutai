package com.putao.user.service;

import com.putao.user.mapper.UserMapper;
import com.putao.user.pojo.User;
import com.putao.common.utils.utils.BCrypt;
import com.putao.common.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
   * 校验该用户名的user是否已存在
   *
   * @param username
   * @return
   */
  public Boolean checkUser(String username) {
    Example example = new Example(User.class);
    Example.Criteria criteria = example.createCriteria();
    criteria.andEqualTo("username", username);

    return this.userMapper.selectOneByExample(example) == null;
  }


  /**
   * 查询用户名和密码查询是否匹配
   * @param user
   * @return
   */
  public Boolean checkLogin(User user) {

    Example example = new Example(User.class);
    Example.Criteria criteria = example.createCriteria();
    criteria.andEqualTo("username",user.getUsername());

    User u = this.userMapper.selectOneByExample(example);
    //不能根据用户名查询到用户
    if (u == null) {
      return false;
    }

    return BCrypt.checkpw(user.getPassword(), u.getPassword());

  }

  /**
   * 根据id查询用户信息
   * @param id
   * @return
   */
  public User findUserById(String id) {
    return this.userMapper.selectByPrimaryKey(id);
  }

   /**
   * 增加用户
   *
   * @param user
   * @return
   */
  public Boolean addUser(User user) {
    //再次检查是否同名
    Boolean hasUser = this.checkUser(user.getUsername());
    if (!hasUser) {
      return false;
    }
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
   * 根据id修改用户
   *
   * @param user
   * @return
   */
  public Boolean updateUserByUserId(User user) {

    return this.userMapper.updateByPrimaryKeySelective(user) == 1;
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
