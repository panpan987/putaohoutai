package com.putao.item.service;

import com.putao.item.mapper.CandyGameDetailMapper;
import com.putao.item.mapper.CandyGameMapper;
import com.putao.item.pojo.CandyGame;
import com.putao.item.pojo.CandyGameDetail;
import net.bytebuddy.implementation.bytecode.assign.primitive.PrimitiveUnboxingDelegate;
import org.apache.commons.lang.StringUtils;
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
public class CandyGameDetailService {

  @Autowired
  private CandyGameDetailMapper candyGameDetailMapper;

  @Autowired
  private CandyGameMapper candyGameMapper;

  /**
   * 根据candygameid获取CandyGameDetail
   * @param candygameid
   * @return
   */
  public CandyGameDetail queryCandyGameDetailByCandyGameId(String candygameid) {
    //初始化查询对象
    Example example = new Example(CandyGameDetail.class);
    Example.Criteria criteria = example.createCriteria();

    criteria.andEqualTo("candyGameId",candygameid);

    return candyGameDetailMapper.selectOneByExample(example);

  }

  /**
   * 根据singlegame插入一条CandyGameDetail
   * @param singlegame
   * @return
   */
  public Boolean insertCandyGameDetail(CandyGameDetail singlegame) {

    return this.candyGameDetailMapper.insertSelective(singlegame) == 1;
  }

}
