package com.putao.auth.controller;

import com.putao.auth.config.JwtProperties;
import com.putao.auth.service.AuthService;
import com.putao.common.pojo.UserInfo;
import com.putao.common.utils.CookieUtils;
import com.putao.common.utils.JwtUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @version 1.0
 * @author: panpan
 * @create: 2020-08-26 01:15
 **/
@Controller
@EnableConfigurationProperties(JwtProperties.class)
public class AuthController {

  @Autowired
  private AuthService authService;

  @Autowired
  private JwtProperties jwtProperties;

  /**
   * 每次登录都会生成token,覆盖原来的token,存入cookie
   * 用户名和密码配对，则生成token
   * @param username
   * @param password
   * @param request
   * @param response
   * @return
   */
  @PostMapping("accredit")
  public ResponseEntity<Void> accredit(@RequestParam("username") String username, @RequestParam("password") String password,
                                       HttpServletRequest request, HttpServletResponse response) {
    String token = this.authService.accredit(username, password);

    if (StringUtils.isBlank(token)) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    CookieUtils.setCookie(request, response, this.jwtProperties.getCookieName(), token, this.jwtProperties.getExpire() * 60 + 8 * 60 * 60);

    return ResponseEntity.ok(null);

  }


  /**
   * 登录成功  退出再打开页面   刷新页面   这3种情况都会带着cookie中的PT_TOKEN去校验用户信息,返回用户信息渲染页面
   *
   * @param token
   * @return
   * @throws Exception
   */
  @GetMapping("verify")
  public ResponseEntity<UserInfo> verify(@CookieValue(value = "PT_TOKEN") String token) throws Exception {

      //通过jwt工具类使用公钥解析jwt
      UserInfo user = JwtUtils.getInfoFromToken(token, this.jwtProperties.getPublicKey());

      //无论user是否为null都返回ok,防止页面报错

      return ResponseEntity.ok(user);

  }

  @DeleteMapping("exit")
  public ResponseEntity<Void> exit(HttpServletRequest request, HttpServletResponse response) {

    //9527 呵呵 -1
    String token = "eyJhbGciOiJSUzI1NiJ9.eyJpZCI6Ijk1MjciLCJ1c2VybmFtZSI6IuWRteWRt" +
            "SIsImV4cCI6MTU5ODUwODUyMX0.Skz8XMp4xH2HHESpQbBKpMBEQoyOOWdmdoMqqEl8vc" +
            "20FoKCTIMZGWcgt-rcJmX3avAU2fWD1QiFoYbTe-Pppmiaorgm9NdvW6A2P6GjxO5Qm26" +
            "bYCmACCADcLyUncP5qMPzlAD-Fko4QDWK70U5KR2Hd7fGXauXWa8eB6COY4w";
    CookieUtils.setCookie(request, response, this.jwtProperties.getCookieName(), token, 1000);

    return ResponseEntity.ok(null);

  }

}
