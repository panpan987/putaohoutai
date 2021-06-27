package com.putao.auth.service;

import com.putao.auth.client.UserClient;
import com.putao.auth.config.JwtProperties;
import com.putao.common.pojo.UserInfo;
import com.putao.common.utils.JwtUtils;
import com.putao.user.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @version 1.0
 * @author: panpan
 * @create: 2020-08-26 01:17
 **/
@Service
public class AuthService {

  @Autowired
  private UserClient userClient;

  @Autowired
  private JwtProperties jwtProperties;

  /**
   * 登录生成token
   *
   * @param username
   * @param password
   * @return
   */
  public String accredit(String username, String password) {
    //1.根据用户名和密码查询
    User user = this.userClient.queryUser(username, password);
    //生成用户token
    return createUserToken(user);

  }

  /**
   * 抽取生成token的方法
   * @param user
   * @return
   */
  private String createUserToken(User user) {
    //2.判断user
    if (user == null) {
      return null;
    }

    //3.jwtutils生成jwt类型的token
    //生成载荷,载荷就是用户信息
    try {
      UserInfo userInfo = new UserInfo();
      userInfo.setId(user.getId());
      userInfo.setUsername(user.getUsername());
      return JwtUtils.generateToken(userInfo, jwtProperties.getPrivateKey(), this.jwtProperties.getExpire());

    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

}
