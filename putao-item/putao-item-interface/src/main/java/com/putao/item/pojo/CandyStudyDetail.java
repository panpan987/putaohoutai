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
@Table(name = "tb_candy_study_detail")
public class CandyStudyDetail implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private String id;

  private String candyStudyId; //candy_study的id

  private String content; //该candy详细信息

  private String preImage;  //第一张展示的图片

  private String images; //图片数组,中间以逗号隔开

  private String resourceUrl; //资源链接

  private String resourceCode;  //资源提取码

  //private Long commentId; //评论组id

  private String title; //简短的标题

  private String upName;  //上传者昵称

  private String upAvatar;  //上传者头像

  private Date createTime;  //candy创建时间


  private Integer reading;  //浏览量

  private Integer comment;  //评论数

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getCandyStudyId() {
    return candyStudyId;
  }

  public void setCandyStudyId(String candyStudyId) {
    this.candyStudyId = candyStudyId;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getPreImage() {
    return preImage;
  }

  public void setPreImage(String preImage) {
    this.preImage = preImage;
  }

  public String getImages() {
    return images;
  }

  public void setImages(String images) {
    this.images = images;
  }

  public String getResourceUrl() {
    return resourceUrl;
  }

  public void setResourceUrl(String resourceUrl) {
    this.resourceUrl = resourceUrl;
  }

  public String getResourceCode() {
    return resourceCode;
  }

  public void setResourceCode(String resourceCode) {
    this.resourceCode = resourceCode;
  }

//  public Long getCommentId() {
//    return commentId;
//  }
//
//  public void setCommentId(Long commentId) {
//    this.commentId = commentId;
//  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
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

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public Integer getReading() {
    return reading;
  }

  public void setReading(Integer reading) {
    this.reading = reading;
  }

  public Integer getComment() {
    return comment;
  }

  public void setComment(Integer comment) {
    this.comment = comment;
  }
}
