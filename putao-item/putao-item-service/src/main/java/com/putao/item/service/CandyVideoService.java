package com.putao.item.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.putao.common.PageResult;
import com.putao.item.mapper.CandyGameMapper;
import com.putao.item.mapper.CandyVideoMapper;
import com.putao.item.pojo.CandyGame;
import com.putao.item.pojo.CandyVideo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @version 1.0
 * @author: panpan
 * @create: 2020-08-14 17:29
 **/
@Service
@Transactional
public class CandyVideoService {

  @Autowired
  private CandyVideoMapper candyVideoMapper;

  /**
   * 分页查询candy
   * @param page
   * @return
   */
  public PageResult<CandyVideo> queryCandyVideosByPage(Integer page, Integer rows) {
    //初始化查询对象
    Example example = new Example(CandyVideo.class);
    Example.Criteria criteria = example.createCriteria();

    //分页
    PageHelper.startPage(page,rows);

    List<CandyVideo> candyVideo = candyVideoMapper.selectByExample(example);

    PageInfo<CandyVideo> pageInfo = new PageInfo<>(candyVideo);

    return new PageResult<>(pageInfo.getTotal(),pageInfo.getPages(),pageInfo.getList());

  }
}
