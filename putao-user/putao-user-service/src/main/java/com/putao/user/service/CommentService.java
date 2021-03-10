package com.putao.user.service;

import com.putao.common.utils.IdWorker;
import com.putao.user.mapper.CommentDao;
import com.putao.user.pojo.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @version 1.0
 * @author: panpan
 * @create: 2020-09-13 21:11
 **/
@Service
@Transactional
public class CommentService {

  @Autowired
  private CommentDao commentDao;

  @Autowired
  private IdWorker idWorker;

  @Autowired
  private MongoTemplate mongoTemplate;

  public List<Comment> findAll() {
    return commentDao.findAll();
  }

  public Comment findById(String id) {
    return commentDao.findById(id).get();
  }

  public void save(Comment comment) {
    comment.set_id(idWorker.nextId() + "");
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    comment.setPublishtime(sdf.format(new Date()));
    comment.setThumbup(0);
    comment.setReply(0);
    //如果当前添加的吐槽，有父节点，那么父节点的吐槽回复数+1
    if (comment.getParentid() != null && !"".equals(comment.getParentid())) {
      Query query = new Query();
      query.addCriteria(Criteria.where("_id").is(comment.getParentid()));
      Update update = new Update();
      update.inc("reply", 1);
      mongoTemplate.updateFirst(query, update, "comment");
    }

    commentDao.save(comment);
  }

  public void update(Comment comment) {
    commentDao.save(comment);
  }

  public void deleteById(String id) {
    commentDao.deleteById(id);
  }

  public Page<Comment> findByParentid(String parentid, int page, int size) {
    PageRequest pageable = PageRequest.of(page - 1 ,size);
    return commentDao.findByParentid(parentid, pageable);
  }


  public void thumbup(String commentid) {

    //使用原生的mongo命令来实现自增
    Query query = new Query();
    query.addCriteria(Criteria.where("_id").is(commentid));
    Update update = new Update();
    update.inc("thumbup", 1);
    mongoTemplate.updateFirst(query, update, "comment");
  }

  public List<Comment> findByBelong(String belong) {
    Query query = new Query();
    query.addCriteria(Criteria.where("belong").is(belong));
    return mongoTemplate.find(query, Comment.class, "comment");
  }
}
