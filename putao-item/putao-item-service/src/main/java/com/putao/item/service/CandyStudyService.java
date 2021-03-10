package com.putao.item.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.putao.common.pojo.PageResult;
import com.putao.item.mapper.CandyGameMapper;
import com.putao.item.mapper.CandyStudyDetailMapper;
import com.putao.item.mapper.CandyStudyMapper;
import com.putao.item.pojo.CandyGame;
import com.putao.item.pojo.CandyGameDetail;
import com.putao.item.pojo.CandyStudy;
import com.putao.item.pojo.CandyStudyDetail;
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
public class CandyStudyService {

  @Autowired
  private CandyStudyMapper candyStudyMapper;

  @Autowired
  private CandyStudyDetailMapper candyStudyDetailMapper;

  /**
   * 分页查询candy
   * @param page
   * @return
   */
  public PageResult<CandyStudy> queryCandyStudiesByPage(Integer page, Integer rows) {
    //初始化查询对象
    Example example = new Example(CandyStudy.class);
    Example.Criteria criteria = example.createCriteria();

    //分页
    PageHelper.startPage(page,rows);

    List<CandyStudy> candyStudies = candyStudyMapper.selectByExample(example);

    PageInfo<CandyStudy> pageInfo = new PageInfo<>(candyStudies);

    return new PageResult<>(pageInfo.getTotal(),pageInfo.getPages(),pageInfo.getList());

  }


  public Boolean insertCandyStudy(CandyStudy candyStudy) {
    return this.candyStudyMapper.insertSelective(candyStudy) == 1;
  }


  public Boolean insComment(String candyid) {

    CandyStudy candyStudy = this.candyStudyMapper.selectByPrimaryKey(candyid);
    candyStudy.setComment(candyStudy.getComment() + 1);
    candyStudy.setHot(candyStudy.getReading()+ candyStudy.getComment()*10);
    candyStudyMapper.updateByPrimaryKeySelective(candyStudy);

    Example example = new Example(CandyStudyDetail.class);
    Example.Criteria criteria = example.createCriteria();
    criteria.andEqualTo("candyStudyId",candyStudy.getId());
    CandyStudyDetail candyStudyDetail = candyStudyDetailMapper.selectOneByExample(example);

    candyStudyDetail.setComment(candyStudyDetail.getComment() + 1);
    candyStudyDetailMapper.updateByPrimaryKeySelective(candyStudyDetail);

    return true;
  }

  public Boolean insReading(String candyid) {
    CandyStudy candyStudy = this.candyStudyMapper.selectByPrimaryKey(candyid);
    candyStudy.setReading(candyStudy.getReading() + 1);
    candyStudy.setHot(candyStudy.getReading()+ candyStudy.getComment()*10);
    candyStudyMapper.updateByPrimaryKeySelective(candyStudy);

    Example example = new Example(CandyStudyDetail.class);
    Example.Criteria criteria = example.createCriteria();
    criteria.andEqualTo("candyStudyId",candyStudy.getId());
    CandyStudyDetail candyStudyDetail = candyStudyDetailMapper.selectOneByExample(example);

    candyStudyDetail.setReading(candyStudyDetail.getReading() + 1);
    candyStudyDetailMapper.updateByPrimaryKeySelective(candyStudyDetail);

    return true;
  }


  public List<CandyStudy> getHot() {
    Example example = new Example(CandyStudy.class);
    example.setOrderByClause("hot desc limit 10");
    List<CandyStudy> candyStudyList = candyStudyMapper.selectByExample(example);
    return candyStudyList;
  }
}
