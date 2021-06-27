package com.putao.upload.controller;

import com.putao.pojo.FilePath;
import com.putao.upload.service.DeleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @version 1.0
 * @author: panpan
 * @create: 2020-08-24 10:02
 **/
@Controller
@RequestMapping
public class DeleteController {

  @Autowired
  private DeleteService deleteService;

  /**
   * 删除图片
   * @param filePath
   * @return
   */
  @PostMapping("delete")
  public ResponseEntity<Boolean> deleteFileByFilePath(@RequestBody FilePath filePath) {

    String file = filePath.getFilePath();
    try {
      this.deleteService.deleteFileByFilePath(file);
    }catch (Exception e) {
     //Service报错,删除失败
      return ResponseEntity.ok(false);
    }
      //删除成功
      return ResponseEntity.ok(true);
  }

}
