package com.putao.user.controller;

import com.putao.user.pojo.User;
import com.putao.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
   * 校验用户名是否可用(有没有重复)
   * @param user
   * @return
   */
  //若用户数量为0,返回true,用户名不存在,可以注册
  @PostMapping("check")
  public ResponseEntity<Boolean> checkUser(@RequestBody User user){
    if (user == null) {
      return ResponseEntity.badRequest().build();
    }
    Boolean b = this.userService.checkUser(user);

    return ResponseEntity.ok(b);
  }

  /**
   * 查询用户名和密码查询是否匹配
   * @param user
   * @return
   */
  @PostMapping("checkLogin")
  public ResponseEntity<Boolean> checkLogin(@RequestBody User user) {
    if (user == null) {
      return ResponseEntity.badRequest().build();
    }
    Boolean b = this.userService.checkLogin(user);
    if (b == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(b);
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
    Boolean b = this.userService.addUser(user);

    return ResponseEntity.ok(b);
  }

  /**
   * 根据id删除用户
   * @param userId
   * @return
   */
  @DeleteMapping("delete/{userId}")
  public ResponseEntity<Boolean> deleteUserById(@PathVariable("userId")String userId) {
    if (StringUtils.isEmpty(userId)) {
      return ResponseEntity.badRequest().build();
    }
    Boolean deletedId = this.userService.deleteUserById(userId);
    if (deletedId == null) {
      return ResponseEntity.notFound().build();
    }
    //无论删除成功与否,把true false 返回前端
    return ResponseEntity.ok(deletedId);
  }

  /**
   * 根据用户名删除用户
   * @param username
   * @return
   */
  @DeleteMapping("delete")
  public ResponseEntity<Boolean> deleteUserByUsername(@RequestParam("username")String username) {
    if (StringUtils.isEmpty(username)) {
      return ResponseEntity.badRequest().build();
    }
    Boolean b = this.userService.deleteUserByUsername(username);

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
   * 根据用户id查询用户
   * @param userId
   * @return
   */
  @GetMapping("find/{userId}")
  public ResponseEntity<User> findUserById(@PathVariable("userId")String userId) {
    if (StringUtils.isEmpty(userId)) {
      return ResponseEntity.badRequest().build();
    }
    User user = this.userService.findUserById(userId);
    if (user == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(user);
  }

  /**
   * 根据条件查询用户
   * @param user
   * @return
   */
  @PostMapping("find")
  public ResponseEntity<List<User>> findUserByExample(@RequestBody User user) {
    if (user == null) {
      return ResponseEntity.badRequest().build();
    }
    List<User> userList = this.userService.findUserByExample(user);
    if (CollectionUtils.isEmpty(userList)) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(userList);
  }

  /**
   * 查询所有用户
   * @return
   */
  @GetMapping("find")
  public ResponseEntity<List<User>> findAll() {

    List<User> userList = this.userService.findAll();
    if (CollectionUtils.isEmpty(userList)) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(userList);
  }




}
