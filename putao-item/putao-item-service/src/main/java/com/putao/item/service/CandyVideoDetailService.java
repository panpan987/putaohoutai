package com.putao.item.service;

import com.putao.item.mapper.CandyGameDetailMapper;
import com.putao.item.mapper.CandyVideoDetailMapper;
import com.putao.item.pojo.CandyGameDetail;
import com.putao.item.pojo.CandyVideoDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

/**
 * @version 1.0
 * @author: panpan
 * @create: 2020-08-16 22:32
 **/
@Service
@Transactional
public class CandyVideoDetailService {

  @Autowired
  private CandyVideoDetailMapper candyVideoDetailMapper;


  public CandyVideoDetail queryCandyVideoDetailByCandyVideoId(Integer candyVideoId) {
    //初始化查询对象
    Example example = new Example(CandyVideoDetail.class);
    Example.Criteria criteria = example.createCriteria();
    if (candyVideoId != null && candyVideoId > 0) {
      criteria.andEqualTo("candyVideoId",candyVideoId);
    }

    return  candyVideoDetailMapper.selectOneByExample(example);
  }

  public Boolean insertCandyVideoDetail(CandyVideoDetail minivideo) {
    return candyVideoDetailMapper.insertSelective(minivideo) == 1;
  }
}
