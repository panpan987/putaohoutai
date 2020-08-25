package com.putao.item.service;

import com.putao.item.mapper.CandyCartoonDetailMapper;
import com.putao.item.pojo.CandyCartoonDetail;
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
public class CandyCartoonDetailService {

  @Autowired
  private CandyCartoonDetailMapper candyCartoonDetailMapper;

  public CandyCartoonDetail queryCandyCartoonDetailByCandyCartoonId(String candycartoonid) {
    //初始化查询对象
    Example example = new Example(CandyCartoonDetail.class);
    Example.Criteria criteria = example.createCriteria();

    criteria.andEqualTo("candyCartoonId", candycartoonid);

    return candyCartoonDetailMapper.selectOneByExample(example);

  }

  public Boolean insertCandyCartoonDetail(CandyCartoonDetail cartoon) {
    return this.candyCartoonDetailMapper.insertSelective(cartoon) == 1;
  }
}
