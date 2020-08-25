package com.putao.item.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @version 1.0
 * @author: panpan
 * @create: 2020-08-14 17:19
 **/
@Table(name = "tb_candy_video")
public class CandyVideo implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String title; //标题描述

  private String preImage;  //展示图片,只此一张

  private Date createTime;  //candy创建时间

  private String upName; //up主昵称

  private String upAvatar; //up头像

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getPreImage() {
    return preImage;
  }

  public void setPreImage(String preImage) {
    this.preImage = preImage;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public String getUpName() {
    return upName;
  }

  public void setUpName(String upName) {
    this.upName = upName;
  }

  public String getUpAvatar() {
    return upAvatar;
  }

  public void setUpAvatar(String upAvatar) {
    this.upAvatar = upAvatar;
  }
}
