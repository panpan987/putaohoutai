package com.putao.user.mapper;


import com.putao.user.pojo.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @version 1.0
 * @author: panpan
 * @create: 2020-09-13 21:08
 **/
public interface CommentDao extends MongoRepository<Comment, String> {

  public Page<Comment> findByParentid(String parentid, Pageable pageable);

}
