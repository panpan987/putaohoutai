package com.putao.item.controller;

import com.putao.common.utils.IdWorker;
import com.putao.item.mapper.CandyStudyDetailMapper;
import com.putao.item.pojo.CandyGame;
import com.putao.item.pojo.CandyGameDetail;
import com.putao.item.pojo.CandyStudy;
import com.putao.item.pojo.CandyStudyDetail;
import com.putao.item.service.CandyGameDetailService;
import com.putao.item.service.CandyGameService;
import com.putao.item.service.CandyStudyDetailService;
import com.putao.item.service.CandyStudyService;
import org.apache.commons.lang.StringUtils;
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
@RequestMapping("studygarden")
public class CandyStudyDetailController {

  @Autowired
  private CandyStudyDetailService candyStudyDetailService;

  @Autowired
  private CandyStudyService candyStudyService;

  @Autowired
  private IdWorker idWorker;

  @GetMapping("{candyid}")
  public ResponseEntity<CandyStudyDetail> queryCandyStudyDetailByCandyStudyId(@PathVariable("candyid")String candystudyid) {
    if (StringUtils.isBlank(candystudyid)) {
      return ResponseEntity.badRequest().build();
    }
    CandyStudyDetail candyStudyDetail = this.candyStudyDetailService.queryCandyStudyDetailByCandyStudyId(candystudyid);
    if (candyStudyDetail == null) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(candyStudyDetail);
  }

  /**
   * 增加candyGame(同时在candyGame和candyGameDetail表中同时添加数据)
   * 因为在详情这张表中添加数据,所有先增加数据少的那张表,获得id再在详细表中设置id
   * @param candystudy
   * @return
   */
  @PostMapping("insert")
  public ResponseEntity<Boolean> insertCandyStudyDetail(@RequestBody CandyStudyDetail candystudy) {
    if (candystudy == null) {
      return ResponseEntity.badRequest().build();
    }

    //保存candyGame
    CandyStudy candyStudy = new CandyStudy();

    candyStudy.setId(idWorker.nextId() + "");
    candyStudy.setTitle(candystudy.getTitle());
    candyStudy.setPreImage(candystudy.getPreImage());
    candyStudy.setUpName(candystudy.getUpName());
    candyStudy.setUpAvatar(candystudy.getUpAvatar());

    Boolean b1 = this.candyStudyService.insertCandyStudy(candyStudy);

    //保存candyStudyDetail
    //设置id
    candystudy.setId(idWorker.nextId()+"");
    candystudy.setCandyStudyId(candyStudy.getId());

    Boolean b2 = this.candyStudyDetailService.insertCandyStudyDetail(candystudy);

    return ResponseEntity.ok(b1 && b2);
  }

}
