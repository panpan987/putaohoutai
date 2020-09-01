package com.putao.item.pojo;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @version 1.0
 * @author: panpan
 * @create: 2020-08-31 16:15
 **/
@Table(name = "tb_candy_show_in_mainpage")
public class CandyShowInMainpage implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String tabbar;  //标题 + path

  private String candyIds;  //candyIds

  //这个字段数据库没有,动态查询添加
  @Transient
  private String preImages; //首页展示的图片组

  //这个字段数据库没有,动态查询添加
  @Transient
  private String titles;  //首页展示的剪短文字


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getTabbar() {
    return tabbar;
  }

  public void setTabbar(String tabbar) {
    this.tabbar = tabbar;
  }

  public String getCandyIds() {
    return candyIds;
  }

  public void setCandyIds(String candyIds) {
    this.candyIds = candyIds;
  }

  public String getPreImages() {
    return preImages;
  }

  public void setPreImages(String preImages) {
    this.preImages = preImages;
  }

  public String getTitles() {
    return titles;
  }

  public void setTitles(String titles) {
    this.titles = titles;
  }

}
