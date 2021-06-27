package com.putao.user.controller;

import com.putao.user.pojo.User;
import com.putao.user.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @version 1.0
 * @author: panpan
 * @create: 2020-08-21 00:50
 **/
@Controller
public class UserController {

  @Autowired
  private UserService userService;

  /**
   * 校验该用户名的user是否已存在
   * @param username
   * @return
   */
  //若返回true,用户名不存在,可以注册
  @GetMapping("check/{username}")
  public ResponseEntity<Boolean> checkUser(@PathVariable("username") String username){
    if (StringUtils.isBlank(username)) {
      return ResponseEntity.ok(false);
    }
    Boolean b = this.userService.checkUser(username);

    return ResponseEntity.ok(b);
  }

  /**
   * 查询用户名和密码查询是否匹配,是的话前台就会登录成功(post)
   * @param user
   * @return
   */
  @PostMapping("checkLogin")
  public ResponseEntity<Boolean> checkLogin(@RequestBody User user) {
    if (user == null) {
      return ResponseEntity.badRequest().build();
    }
    Boolean b = this.userService.checkLogin(user);

    return ResponseEntity.ok(b);
  }

  /**
   * 根据id查询用户信息
   * @param id
   * @return
   */
  @PostMapping("findUserById/{id}")
  public ResponseEntity<User> findUserById(@PathVariable("id") String id) {
    if (StringUtils.isBlank(id)) {
      return ResponseEntity.ok(null);
    }
    User user = this.userService.findUserById(id);
    return ResponseEntity.ok(user);
  }


  /**
   * 增加用户
   * @param user
   * @return
   */
  @PostMapping("add")
  public ResponseEntity<Boolean> addUser(@RequestBody User user) {
    if (user == null) {
      return ResponseEntity.badRequest().build();
    }
    Lock lock = new ReentrantLock();
    lock.lock();

    Boolean b = this.userService.addUser(user);

    lock.unlock();
    return ResponseEntity.ok(b);
  }




  /**
   * 根据id修改用户
   * @param user
   * @return
   */
  @PutMapping("update/{userId}")
  public ResponseEntity<Boolean> updateUserByUserId(@RequestBody User user,@PathVariable("userId") String userId) {
    if (user == null || userId == null) {
      return ResponseEntity.badRequest().build();
    }
    user.setId(userId);
    Boolean b = this.userService.updateUserByUserId(user);
    if (b == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(b);
  }


  /**
   * 根据用户名的密码查询用户信息(get???)
   * @param username
   * @param password
   * @return
   */
  @GetMapping("query")
  public ResponseEntity<User> queryUser(@RequestParam("username")String username,
                                        @RequestParam("password")String password){
    User user = this.userService.queryUser(username, password);
    if(user == null) {
      return ResponseEntity.ok(null);
    }
    return ResponseEntity.ok(user);
  }

}
