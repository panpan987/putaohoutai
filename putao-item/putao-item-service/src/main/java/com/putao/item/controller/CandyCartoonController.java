package com.putao.item.controller;

import com.putao.common.PageResult;
import com.putao.item.pojo.CandyCartoon;
import com.putao.item.pojo.CandyGame;
import com.putao.item.service.CandyCartoonService;
import com.putao.item.service.CandyGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @version 1.0
 * @author: panpan
 * @create: 2020-08-14 17:30
 **/
@Controller
@RequestMapping("candycartoon")
public class CandyCartoonController {

  @Autowired
  private CandyCartoonService candyCartoonService;

  /**
   * 分页查询candy
   * @param page
   * @return
   */
//  目前只根据页码查询,将来可以根据按钮上的 综合,时间 等查询
  @GetMapping("page/{page}")
  public ResponseEntity<PageResult<CandyCartoon>> queryCandyGamesByPage(
          @PathVariable("page")Integer page,
          @RequestParam(value = "rows", defaultValue = "10")Integer rows
  ) {
    PageResult<CandyCartoon> result = this.candyCartoonService.queryCandyCartoonsByPage(page,rows);
    if (CollectionUtils.isEmpty(result.getItems())) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(result);
  }



}
