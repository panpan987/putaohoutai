package com.putao.item.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.putao.common.pojo.PageResult;
import com.putao.item.mapper.CandyCartoonDetailMapper;
import com.putao.item.mapper.CandyCartoonMapper;
import com.putao.item.pojo.CandyCartoon;
import com.putao.item.pojo.CandyCartoonDetail;
import com.putao.item.pojo.CandyGame;
import com.putao.item.pojo.CandyGameDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.xml.soap.Detail;
import java.util.List;

/**
 * @version 1.0
 * @author: panpan
 * @create: 2020-08-14 17:29
 **/
@Service
@Transactional
public class CandyCartoonService {

  @Autowired
  private CandyCartoonMapper candyCartoonMapper;

  @Autowired
  private CandyCartoonDetailMapper candyCartoonDetailMapper;

  /**
   * 分页查询candy
   * @param page
   * @return
   */
  public PageResult<CandyCartoon> queryCandyCartoonsByPage(Integer page, Integer rows) {
    //初始化查询对象
    Example example = new Example(CandyCartoon.class);
    Example.Criteria criteria = example.createCriteria();

    //分页
    PageHelper.startPage(page,rows);

    List<CandyCartoon> candyCartoons = candyCartoonMapper.selectByExample(example);


    PageInfo<CandyCartoon> pageInfo = new PageInfo<>(candyCartoons);

    return new PageResult<>(pageInfo.getTotal(),pageInfo.getPages(),pageInfo.getList());

  }

  public Boolean insertCandyCartoon(CandyCartoon candyCartoon) {
    return this.candyCartoonMapper.insertSelective(candyCartoon) == 1;
  }

  public Boolean insComment(String candyid) {

    CandyCartoon candyCartoon = this.candyCartoonMapper.selectByPrimaryKey(candyid);
    candyCartoon.setComment(candyCartoon.getComment() + 1);
    candyCartoon.setHot(candyCartoon.getReading() + candyCartoon.getComment()*10);
    candyCartoonMapper.updateByPrimaryKeySelective(candyCartoon);

    Example example = new Example(CandyCartoonDetail.class);
    Example.Criteria criteria = example.createCriteria();
    criteria.andEqualTo("candyCartoonId",candyCartoon.getId());
    CandyCartoonDetail candyCartoonDetail = candyCartoonDetailMapper.selectOneByExample(example);

    candyCartoonDetail.setComment(candyCartoonDetail.getComment() + 1);
    candyCartoonDetailMapper.updateByPrimaryKeySelective(candyCartoonDetail);


    return true;
  }

  public Boolean insReading(String candyid) {
    CandyCartoon candyCartoon = this.candyCartoonMapper.selectByPrimaryKey(candyid);
    candyCartoon.setReading(candyCartoon.getReading() + 1);
    candyCartoon.setHot(candyCartoon.getReading() + candyCartoon.getComment()*10);
    candyCartoonMapper.updateByPrimaryKeySelective(candyCartoon);

    Example example = new Example(CandyCartoonDetail.class);
    Example.Criteria criteria = example.createCriteria();
    criteria.andEqualTo("candyCartoonId",candyCartoon.getId());
    CandyCartoonDetail candyCartoonDetail = candyCartoonDetailMapper.selectOneByExample(example);

    candyCartoonDetail.setReading(candyCartoonDetail.getReading() + 1);
    candyCartoonDetailMapper.updateByPrimaryKeySelective(candyCartoonDetail);

    return true;
  }


  public List<CandyCartoon> getHot() {
    Example example = new Example(CandyCartoon.class);
    example.setOrderByClause("hot desc limit 10");
    List<CandyCartoon> candyCartoonList = candyCartoonMapper.selectByExample(example);
    return candyCartoonList;
  }
}
