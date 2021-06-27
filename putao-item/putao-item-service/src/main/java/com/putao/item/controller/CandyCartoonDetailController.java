package com.putao.item.controller;

import com.putao.item.pojo.CandyCartoon;
import com.putao.item.pojo.CandyCartoonDetail;
import com.putao.item.service.CandyCartoonDetailService;
import com.putao.item.service.CandyCartoonService;
import com.putao.common.utils.IdWorker;
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
@RequestMapping("cartoon")
public class CandyCartoonDetailController {

  @Autowired
  private CandyCartoonDetailService candyCartoonDetailService;

  @Autowired
  private CandyCartoonService candyCartoonService;

  @Autowired
  private IdWorker idWorker;

  @GetMapping("{candyid}")
  public ResponseEntity<CandyCartoonDetail> queryCandyCartoonDetailByCandyCartoonId(@PathVariable("candyid")String candycartoonid) {
    if (StringUtils.isBlank(candycartoonid)) {
      return ResponseEntity.badRequest().build();
    }
    CandyCartoonDetail candyCartoonDetail = this.candyCartoonDetailService.queryCandyCartoonDetailByCandyCartoonId(candycartoonid);
    if (candyCartoonDetail == null) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(candyCartoonDetail);
  }

  /**
   * 增加candyCartoon(同时在candyCartoon和candyCartoonDetail表中同时添加数据)
   * 因为在详情这张表中添加数据,所有先增加数据少的那张表,获得id再在详细表中设置id
   * @param cartoon
   * @return
   */
  @PostMapping("insert")
  public ResponseEntity<Boolean> insertCandyCartoonDetail(@RequestBody CandyCartoonDetail cartoon) {
    if (cartoon == null) {
      return ResponseEntity.badRequest().build();
    }

    //保存candyGame
    CandyCartoon candyCartoon = new CandyCartoon();

    candyCartoon.setId(idWorker.nextId() + "");
    candyCartoon.setTitle(cartoon.getTitle());
    candyCartoon.setPreImage(cartoon.getPreImage());
    candyCartoon.setUpName(cartoon.getUpName());
    candyCartoon.setUpAvatar(cartoon.getUpAvatar());

    Boolean b1 = this.candyCartoonService.insertCandyCartoon(candyCartoon);

    //保存candyGameDetail
    //设置id
    cartoon.setId(idWorker.nextId()+"");
    cartoon.setCandyCartoonId(candyCartoon.getId());

    Boolean b2 = this.candyCartoonDetailService.insertCandyCartoonDetail(cartoon);

    return ResponseEntity.ok(b1 && b2);
  }

}
