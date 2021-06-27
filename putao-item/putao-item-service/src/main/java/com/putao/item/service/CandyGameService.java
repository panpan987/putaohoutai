package com.putao.item.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.putao.common.pojo.PageResult;
import com.putao.item.mapper.CandyGameDetailMapper;
import com.putao.item.mapper.CandyGameMapper;
import com.putao.item.pojo.CandyGame;
import com.putao.item.pojo.CandyGameDetail;
import org.apache.commons.lang3.StringUtils;
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
public class CandyGameService {

  @Autowired
  private CandyGameMapper candyGameMapper;

  @Autowired
  private CandyGameDetailMapper candyGameDetailMapper;

  /**
   * 分页查询candy
   * @param page
   * @return
   */
  public PageResult<CandyGame> queryCandyGamesByPage(Integer page, Integer rows,String sortBy,Boolean desc) {
    //初始化查询对象
    Example example = new Example(CandyGame.class);
    Example.Criteria criteria = example.createCriteria();

    //分页
    PageHelper.startPage(page,rows);

    //排序
    if (StringUtils.isNotBlank(sortBy)) {
      example.setOrderByClause(sortBy + " " + (desc ? "desc" : "asc"));
    }

    List<CandyGame> candyGames = candyGameMapper.selectByExample(example);

    PageInfo<CandyGame> pageInfo = new PageInfo<>(candyGames);

    return new PageResult<>(pageInfo.getTotal(),pageInfo.getPages(),pageInfo.getList());

  }


  public Boolean insertCandyGame(CandyGame candyGame) {
    return this.candyGameMapper.insertSelective(candyGame) == 1;
  }


  public Boolean insComment(String candyid) {

    CandyGame candyGame = this.candyGameMapper.selectByPrimaryKey(candyid);
    candyGame.setComment(candyGame.getComment() + 1);
    candyGame.setHot(candyGame.getReading() + candyGame.getComment()*10);
    candyGameMapper.updateByPrimaryKeySelective(candyGame);

    Example example = new Example(CandyGameDetail.class);
    Example.Criteria criteria = example.createCriteria();
    criteria.andEqualTo("candyGameId",candyGame.getId());
    CandyGameDetail candyGameDetail = candyGameDetailMapper.selectOneByExample(example);

    candyGameDetail.setComment(candyGameDetail.getComment() + 1);
    candyGameDetailMapper.updateByPrimaryKeySelective(candyGameDetail);


    return true;
  }

  public Boolean insReading(String candyid) {
    CandyGame candyGame = this.candyGameMapper.selectByPrimaryKey(candyid);
    candyGame.setReading(candyGame.getReading() + 1);
    candyGame.setHot(candyGame.getReading() + candyGame.getComment()*10);
    candyGameMapper.updateByPrimaryKeySelective(candyGame);

    Example example = new Example(CandyGameDetail.class);
    Example.Criteria criteria = example.createCriteria();
    criteria.andEqualTo("candyGameId",candyGame.getId());
    CandyGameDetail candyGameDetail = candyGameDetailMapper.selectOneByExample(example);

    candyGameDetail.setReading(candyGameDetail.getReading() + 1);
    candyGameDetailMapper.updateByPrimaryKeySelective(candyGameDetail);

    return true;
  }


  public List<CandyGame> getHot() {
    Example example = new Example(CandyGame.class);
    example.setOrderByClause("hot desc limit 10");
    List<CandyGame> candyGameList = candyGameMapper.selectByExample(example);
    return candyGameList;
  }
}
