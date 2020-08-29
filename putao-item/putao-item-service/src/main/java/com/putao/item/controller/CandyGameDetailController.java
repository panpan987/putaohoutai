package com.putao.item.controller;

import com.putao.item.pojo.CandyGame;
import com.putao.item.pojo.CandyGameDetail;
import com.putao.item.service.CandyGameDetailService;
import com.putao.item.service.CandyGameService;
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
@RequestMapping("singlegame")
public class CandyGameDetailController {

  @Autowired
  private CandyGameDetailService candyGameDetailService;

  @Autowired
  private CandyGameService candyGameService;

  @Autowired
  private IdWorker idWorker;

  @GetMapping("{candydetailid}")
  public ResponseEntity<CandyGameDetail> queryCandyGameDetailByCandyGameId(@PathVariable("candydetailid")String candygameid) {
    if (StringUtils.isBlank(candygameid)) {
      return ResponseEntity.badRequest().build();
    }
    CandyGameDetail candyGameDetail = this.candyGameDetailService.queryCandyGameDetailByCandyGameId(candygameid);
    if (candyGameDetail == null) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(candyGameDetail);
  }

  /**
   * 增加candyGame(同时在candyGame和candyGameDetail表中同时添加数据)
   * 因为在详情这张表中添加数据,所有先增加数据少的那张表,获得id再在详细表中设置id
   * @param singlegame
   * @return
   */
  @PostMapping("insert")
  public ResponseEntity<Boolean> insertCandyGameDetail(@RequestBody CandyGameDetail singlegame) {
    if (singlegame == null) {
      return ResponseEntity.badRequest().build();
    }

    //保存candyGame
    CandyGame candyGame = new CandyGame();

    candyGame.setId(idWorker.nextId() + "");
    candyGame.setTitle(singlegame.getTitle());
    candyGame.setPreImage(singlegame.getPreImage());
    candyGame.setUpName(singlegame.getUpName());
    candyGame.setUpAvatar(singlegame.getUpAvatar());

    Boolean b1 = this.candyGameService.insertCandyGame(candyGame);

    //保存candyGameDetail
    //设置id
    singlegame.setId(idWorker.nextId()+"");
    singlegame.setCandyGameId(candyGame.getId());

    Boolean b2 = this.candyGameDetailService.insertCandyGameDetail(singlegame);

    return ResponseEntity.ok(b1 && b2);
  }

}
