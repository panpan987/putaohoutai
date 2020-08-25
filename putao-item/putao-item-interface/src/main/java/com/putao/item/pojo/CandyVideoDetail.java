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
  private Long id;  //主键

  private Long candyVideoId; //candy_video的id

  private String src; //视频源

  private String poster; //封面图片

  private String title; //标题

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getCandyVideoId() {
    return candyVideoId;
  }

  public void setCandyVideoId(Long candyVideoId) {
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
}
