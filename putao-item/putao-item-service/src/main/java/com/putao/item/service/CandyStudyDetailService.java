package com.putao.item.service;

import com.putao.item.mapper.CandyGameDetailMapper;
import com.putao.item.mapper.CandyGameMapper;
import com.putao.item.mapper.CandyStudyDetailMapper;
import com.putao.item.mapper.CandyStudyMapper;
import com.putao.item.pojo.CandyGameDetail;
import com.putao.item.pojo.CandyStudyDetail;
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
public class CandyStudyDetailService {

  @Autowired
  private CandyStudyDetailMapper candyStudyDetailMapper;

  @Autowired
  private CandyStudyMapper candyStudyMapper;

  /**
   * 根据candygameid获取CandyGameDetail
   *
   * @param candystudyid
   * @return
   */
  public CandyStudyDetail queryCandyStudyDetailByCandyStudyId(String candystudyid) {
    //初始化查询对象
    Example example = new Example(CandyStudyDetail.class);
    Example.Criteria criteria = example.createCriteria();

    criteria.andEqualTo("candyStudyId", candystudyid);

    return candyStudyDetailMapper.selectOneByExample(example);

  }

  /**
   * 根据singlegame插入一条CandyGameDetail
   *
   * @param candystudy
   * @return
   */
  public Boolean insertCandyStudyDetail(CandyStudyDetail candystudy) {

    return this.candyStudyDetailMapper.insertSelective(candystudy) == 1;
  }

}
