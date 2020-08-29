package com.putao.item.controller;

import com.putao.common.utils.IdWorker;
import com.putao.item.pojo.CandyGameDetail;
import com.putao.item.pojo.CandyVideo;
import com.putao.item.pojo.CandyVideoDetail;
import com.putao.item.service.CandyVideoDetailService;
import com.putao.item.service.CandyVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

  @Autowired
  private CandyVideoService candyVideoService;

  @Autowired
  private IdWorker idWorker;


  @GetMapping("{candydetailid}")
  public ResponseEntity<CandyVideoDetail> queryCandyVideoDetailByCandyVideoId(@PathVariable("candydetailid") Integer candyVideoId) {
    CandyVideoDetail candyVideoDetail = this.candyVideoDetailService.queryCandyVideoDetailByCandyVideoId(candyVideoId);
    if (candyVideoDetail == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(candyVideoDetail);
  }

  @PostMapping("insert")
  public ResponseEntity<Boolean> insertCandyVideoDetail(@RequestBody CandyVideoDetail minivideo) {
    if (minivideo == null) {
      return ResponseEntity.badRequest().build();
    }

    //保存candyVideo
    CandyVideo candyVideo = new CandyVideo();

    candyVideo.setId(idWorker.nextId() + "");
    candyVideo.setTitle(minivideo.getTitle());
    candyVideo.setPreImage(minivideo.getPreImage());
    candyVideo.setUpName(minivideo.getUpName());
    candyVideo.setUpAvatar(minivideo.getUpAvatar());

    Boolean b1 = this.candyVideoService.insertCandyVideo(candyVideo);

    //保存candyVieoDetail
    minivideo.setId(idWorker.nextId() + "");
    minivideo.setCandyVideoId(candyVideo.getId());

    Boolean b2 = this.candyVideoDetailService.insertCandyVideoDetail(minivideo);

    return ResponseEntity.ok(b1 && b2);


  }

}
