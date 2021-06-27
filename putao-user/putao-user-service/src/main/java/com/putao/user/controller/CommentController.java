package com.putao.user.controller;

import com.putao.user.pojo.Comment;
import com.putao.user.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @version 1.0
 * @author: panpan
 * @create: 2020-09-13 21:19
 **/
@RestController
@RequestMapping("/comment")
public class CommentController {

  @Autowired
  private CommentService commentService;

  @Autowired
  private RedisTemplate redisTemplate;

  /**
   * 查询所有
   * @return
   */
  @GetMapping
  public ResponseEntity<List<Comment>> findAll() {

    return ResponseEntity.ok(commentService.findAll());
  }

  /**
   * 根据belong(标题+id)查询
   * @return
   */
  @GetMapping("findByBelong/{belong}")
  public ResponseEntity<List<Comment>> findByBelong(@PathVariable String belong) {
    return ResponseEntity.ok(commentService.findByBelong(belong));
  }

  /**
   * 根据id查询
   * @param commentid
   * @return
   */
  @GetMapping("{commentid}")
  public ResponseEntity<Comment> findById(@PathVariable String commentid) {
    return ResponseEntity.ok(commentService.findById(commentid));
  }

  /**
   * 保存
   * @param comment
   * @return
   */
  @PostMapping
  public ResponseEntity<Boolean> save(@RequestBody Comment comment) {
    commentService.save(comment);
    return ResponseEntity.ok(true);
  }

  /**
   * 更新
   * @param commentid
   * @param comment
   * @return
   */
  @PutMapping("{commentid}")
  public ResponseEntity<Boolean> update(@PathVariable String commentid, @RequestBody Comment comment) {
    comment.set_id(commentid);
    commentService.update(comment);
    return ResponseEntity.ok(true);
  }

  /**
   * 删除
   * @param commentid
   * @return
   */
  @DeleteMapping("{commentid}")
  public ResponseEntity<Boolean> delete(@PathVariable String commentid) {
    commentService.deleteById(commentid);
    return ResponseEntity.ok(true);
  }

  /**
   * 根据父id分页查询
   * @return
   */
  @GetMapping("page/{parentid}/{page}/{size}")
  public ResponseEntity<Page<Comment>> findByParentid(@PathVariable String parentid,
                                                      @PathVariable Integer page,
                                                      @PathVariable Integer size) {
    Page<Comment> pageData = commentService.findByParentid(parentid, page, size);
    return ResponseEntity.ok(pageData);
  }

  /**
   * 点赞+1
   * @param commentid
   * @return
   */
  @PutMapping("thumbup/{commentid}/{userid}/{candydetailid}")
  public ResponseEntity<Boolean> thumbup(@PathVariable String commentid,
                                         @PathVariable String userid,
                                         @PathVariable String candydetailid) {

    //判断当前用户是否已经点赞
    String key = new StringBuilder().append(userid).append("_").append(candydetailid).toString();
    if (redisTemplate.opsForValue().get(key) != null) {
      return ResponseEntity.ok(false);
    }

    commentService.thumbup(commentid);
    redisTemplate.opsForValue().set(key,1);
    return ResponseEntity.ok(true);
  }

}
