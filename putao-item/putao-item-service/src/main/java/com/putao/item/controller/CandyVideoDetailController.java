package com.putao.item.controller;

import com.putao.item.pojo.CandyGameDetail;
import com.putao.item.pojo.CandyVideoDetail;
import com.putao.item.service.CandyGameDetailService;
import com.putao.item.service.CandyVideoDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @version 1.0
 * @author: panpan
 * @create: 2020-08-16 22:35
 **/
@Controller
@RequestMapping("minivideo")
public class CandyVideoDetailController {

  @Autowired
  private CandyVideoDetailService candyVideoDetailService;

  @GetMapping("{candydetailid}")
  public ResponseEntity<CandyVideoDetail> queryCandyVideoDetailByCandyVideoId(@PathVariable("candydetailid")Integer candyVideoDetailId) {
    CandyVideoDetail candyVideoDetail = this.candyVideoDetailService.queryCandyVideoDetailByCandyVideoId(candyVideoDetailId);
    if (candyVideoDetail == null) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(candyVideoDetail);
  }

}
