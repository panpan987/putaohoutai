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
 * @create: 2020-08-16 22:21
 **/
@Table(name = "tb_candy_video_detail")
public class CandyVideoDetail implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private String id;  //主键

  private String candyVideoId; //candy_video的id

  private String src; //视频源

  private String poster; //封面图片

  private String title; //标题

  private String preImage;

  private String upName;

  private String upAvatar;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getCandyVideoId() {
    return candyVideoId;
  }

  public void setCandyVideoId(String candyVideoId) {
    this.candyVideoId = candyVideoId;
  }

  public String getSrc() {
    return src;
  }

  public void setSrc(String src) {
    this.src = src;
  }

  public String getPoster() {
    return poster;
  }

  public void setPoster(String poster) {
    this.poster = poster;
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
