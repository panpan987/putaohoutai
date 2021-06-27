package com.putao.auth.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @version 1.0
 * @author: panpan
 * @create: 2020-09-04 21:01
 **/
@RestControllerAdvice
public class BaseExceptionHandler {

  @ExceptionHandler(value = Exception.class)
  public String exception(Exception e) {
    return "您尚未登录";
  }
}
