package com.putao.item.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.putao.common.pojo.PageResult;
import com.putao.item.mapper.CandyGameMapper;
import com.putao.item.pojo.CandyGame;
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

  /**
   * 分页查询candy
   * @param page
   * @return
   */
  public PageResult<CandyGame> queryCandyGamesByPage(Integer page, Integer rows) {
    //初始化查询对象
    Example example = new Example(CandyGame.class);
    Example.Criteria criteria = example.createCriteria();

    //分页
    PageHelper.startPage(page,rows);

    List<CandyGame> candyGames = candyGameMapper.selectByExample(example);

    PageInfo<CandyGame> pageInfo = new PageInfo<>(candyGames);

    return new PageResult<>(pageInfo.getTotal(),pageInfo.getPages(),pageInfo.getList());

  }


  public Boolean insertCandyGame(CandyGame candyGame) {
    return this.candyGameMapper.insertSelective(candyGame) == 1;
  }
}
