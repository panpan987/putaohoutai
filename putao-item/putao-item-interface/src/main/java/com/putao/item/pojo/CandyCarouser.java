package com.putao.item.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @version 1.0
 * @author: panpan
 * @create: 2020-08-30 17:23
 **/
@Table(name = "tb_candy_carouser")
public class CandyCarouser implements Serializable{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String tabbar;  //标题

  private String candyId;  //candyil的id

  private String img;  //轮播图

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

  public String getcandyId() {
    return candyId;
  }

  public void setcandyId(String candyId) {
    this.candyId = candyId;
  }

  public String getImg() {
    return img;
  }

  public void setImg(String img) {
    this.img = img;
  }
}
