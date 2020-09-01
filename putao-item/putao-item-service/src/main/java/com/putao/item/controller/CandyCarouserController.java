package com.putao.item.controller;

import com.putao.item.pojo.CandyCarouser;
import com.putao.item.service.CandyCarouserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @version 1.0
 * @author: panpan
 * @create: 2020-08-31 14:25
 **/
@Controller
@RequestMapping("carouser")
public class CandyCarouserController {

  @Autowired
  private CandyCarouserService candyCarouserService;

  /**
   * 查询所有的轮播图信息
   * @return
   */
  @GetMapping("find")
  public ResponseEntity<List<CandyCarouser>> findAllCandyCarouser() {
    List<CandyCarouser> carouserList = this.candyCarouserService.findAllCandyCarouser();
    if (CollectionUtils.isEmpty(carouserList)) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(carouserList);
  }

  /**
   * 新增轮播图信息
   * @param candyCarouser
   * @return
   */
  @PostMapping("insert")
  public ResponseEntity<Boolean> insertCandyCarouser(@RequestBody CandyCarouser candyCarouser) {
    if (candyCarouser == null) {
      return ResponseEntity.badRequest().build();
    }
    Boolean b = this.candyCarouserService.insertCandyCarouser(candyCarouser);
    return ResponseEntity.ok(b);
  }
}
