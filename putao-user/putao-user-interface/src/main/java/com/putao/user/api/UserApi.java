package com.putao.user.api;

import com.putao.user.pojo.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @version 1.0
 * @author: panpan
 * @create: 2020-08-26 01:57
 **/
public interface UserApi {

  /**
   * 根据用户名和密码查询用户
   * @param username
   * @param password
   * @return
   */
  @GetMapping("query")
  public User queryUser(@RequestParam("username")String username,
                        @RequestParam("password")String password);

  /**
   * 根据用户id查询用户
   * @param userId
   * @return
   */
  @GetMapping("find/{userId}")
  public User findUserById(@PathVariable("userId")String userId);
}
