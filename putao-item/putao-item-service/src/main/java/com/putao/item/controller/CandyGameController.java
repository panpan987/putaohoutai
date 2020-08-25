package com.putao.item.controller;

import com.putao.common.PageResult;
import com.putao.item.pojo.CandyGame;
import com.putao.item.service.CandyGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Id;

/**
 * @version 1.0
 * @author: panpan
 * @create: 2020-08-14 17:30
 **/
@Controller
@RequestMapping("singlegame")
public class CandyGameController {

  @Autowired
  private CandyGameService candyGameService;

  /**
   * 分页查询candy
   * @param page
   * @return
   */
//  目前只根据页码查询,将来可以根据按钮上的 综合,时间 等查询
  @GetMapping("page/{page}")
  public ResponseEntity<PageResult<CandyGame>> queryCandyGamesByPage(
          @PathVariable("page")Integer page,
          @RequestParam(value = "rows", required = false,defaultValue = "10")Integer rows
  ) {
    PageResult<CandyGame> result = this.candyGameService.queryCandyGamesByPage(page,rows);
    if (CollectionUtils.isEmpty(result.getItems())) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(result);
  }

}
