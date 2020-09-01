package com.putao.item.controller;

import com.putao.item.pojo.CandyShowInMainpage;
import com.putao.item.service.CandyShowInMainpageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.Id;
import java.util.List;

/**
 * @version 1.0
 * @author: panpan
 * @create: 2020-08-31 16:20
 **/
@Controller
@RequestMapping("showinmainpage")
public class CandyShowInMainpageController {

  @Autowired
  private CandyShowInMainpageService candyShowInMainpageService;

  /**
   * 增加
   * @param candyShowInMainpage
   * @return
   */
  @PostMapping("insert")
  public ResponseEntity<Boolean> insertCandyShowInMainpage(@RequestBody CandyShowInMainpage candyShowInMainpage) {
    if (candyShowInMainpage == null) {
      return ResponseEntity.badRequest().build();
    }
    Boolean b = this.candyShowInMainpageService.insertCandyShowInMainpage(candyShowInMainpage);
    return ResponseEntity.ok(b);
  }

  /**
   * 查询所有
   * @return
   */
  @GetMapping("find")
  public ResponseEntity<List<CandyShowInMainpage>> findCandyShowInMainpage() {
     List<CandyShowInMainpage> list = this.candyShowInMainpageService.findCandyShowInMainpage();
     if (CollectionUtils.isEmpty(list)) {
       return ResponseEntity.notFound().build();
     }
     return ResponseEntity.ok(list);
  }
}
