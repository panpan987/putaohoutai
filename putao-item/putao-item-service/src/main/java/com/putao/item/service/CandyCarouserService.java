package com.putao.item.service;

import com.putao.item.mapper.CandyCarouserMapper;
import com.putao.item.pojo.CandyCarouser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @version 1.0
 * @author: panpan
 * @create: 2020-08-31 14:25
 **/
@Service
public class CandyCarouserService {

  @Autowired
  private CandyCarouserMapper candyCarouserMapper;

  public List<CandyCarouser> findAllCandyCarouser() {
    return this.candyCarouserMapper.selectAll();
  }

  public Boolean insertCandyCarouser(CandyCarouser candyCarouser) {
    return this.candyCarouserMapper.insert(candyCarouser) == 1;
  }
}
