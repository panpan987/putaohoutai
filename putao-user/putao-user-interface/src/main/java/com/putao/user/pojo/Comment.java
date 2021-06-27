package com.putao.user.pojo;

import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

/**
 * @version 1.0
 * @author: panpan
 * @create: 2020-09-13 21:01
 **/
public class Comment implements Serializable {

  @Id
  private String _id;

  private String belong;  //属于哪个栏目哪个candyid

  private String content;

  private String publishtime;

  private String userid;

  private String username;

  private String useravatar;

  private Integer thumbup;

  private Integer reply;

  private String parentid;


  public String get_id() {
    return _id;
  }

  public void set_id(String _id) {
    this._id = _id;
  }

  public String getBelong() {
    return belong;
  }

  public void setBelong(String belong) {
    this.belong = belong;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getPublishtime() {
    return publishtime;
  }

  public void setPublishtime(String publishtime) {
    this.publishtime = publishtime;
  }

  public String getUserid() {
    return userid;
  }

  public void setUserid(String userid) {
    this.userid = userid;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getUseravatar() {
    return useravatar;
  }

  public void setUseravatar(String useravatar) {
    this.useravatar = useravatar;
  }

  public Integer getThumbup() {
    return thumbup;
  }

  public void setThumbup(Integer thumbup) {
    this.thumbup = thumbup;
  }

  public Integer getReply() {
    return reply;
  }

  public void setReply(Integer reply) {
    this.reply = reply;
  }

  public String getParentid() {
    return parentid;
  }

  public void setParentid(String parentid) {
    this.parentid = parentid;
  }
}
