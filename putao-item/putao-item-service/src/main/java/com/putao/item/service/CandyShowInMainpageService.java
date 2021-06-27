package com.putao.item.service;

import com.putao.item.mapper.CandyShowInMainpageMapper;
import com.putao.item.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @author: panpan
 * @create: 2020-08-31 16:19
 **/
@Service
@Transactional
public class CandyShowInMainpageService {

  @Autowired
  private CandyShowInMainpageMapper candyShowInMainpageMapper;

  @Autowired
  private CandyCartoonDetailService candyCartoonDetailService;

  @Autowired
  private CandyGameDetailService candyGameDetailService;

  @Autowired
  private CandyStudyDetailService candyStudyDetailService;

  @Autowired
  private CandyVideoDetailService candyVideoDetailService;

  /**
   * 新增
   *
   * @param candyShowInMainpage
   * @return
   */
  public Boolean insertCandyShowInMainpage(CandyShowInMainpage candyShowInMainpage) {
    return this.candyShowInMainpageMapper.insertSelective(candyShowInMainpage) == 1;
  }

  /**
   * 查询....我吐了,有什么简单点的?
   *
   * @return
   */
  public List<CandyShowInMainpage> findCandyShowInMainpage() {


      List<CandyShowInMainpage> list = this.candyShowInMainpageMapper.selectAll();

      List<CandyShowInMainpage> newList = new ArrayList<CandyShowInMainpage>();

      for (int i = 0; i < list.size(); i++) {
        CandyShowInMainpage showInMainpage = list.get(i);
        String[] tabbar = showInMainpage.getTabbar().split(",");

        if (tabbar[1].equals("singlegame")) {
          String[] ids = showInMainpage.getCandyIds().split(",");

          StringBuilder preImages = new StringBuilder();
          StringBuilder titles = new StringBuilder();

          for (int count = 0; count < ids.length; count++) {

            CandyGameDetail gameDetail = this.candyGameDetailService.queryCandyGameDetailByCandyGameId(ids[count]);

            if (count == ids.length - 1) {
              preImages.append(gameDetail.getPreImage());
              titles.append(gameDetail.getTitle());
            } else {
              preImages.append(gameDetail.getPreImage()).append(",");
              titles.append(gameDetail.getTitle()).append("**");
            }

          }
          showInMainpage.setPreImages(preImages.toString());
          showInMainpage.setTitles(titles.toString());
          newList.add(showInMainpage);

        }

        if (tabbar[1].equals("cartoon")) {
          String[] ids = showInMainpage.getCandyIds().split(",");

          StringBuilder preImages = new StringBuilder();
          StringBuilder titles = new StringBuilder();

          for (int count = 0; count < ids.length; count++) {

            CandyCartoonDetail cartoonDetail = this.candyCartoonDetailService.queryCandyCartoonDetailByCandyCartoonId(ids[count]);

            if (count == ids.length - 1) {
              preImages.append(cartoonDetail.getPreImage());
              titles.append(cartoonDetail.getTitle());
            } else {
              preImages.append(cartoonDetail.getPreImage()).append(",");
              titles.append(cartoonDetail.getTitle()).append("**");
            }

          }
          showInMainpage.setPreImages(preImages.toString());
          showInMainpage.setTitles(titles.toString());
          newList.add(showInMainpage);
        }

        if (tabbar[1].equals("studygarden")) {
          String[] ids = showInMainpage.getCandyIds().split(",");

          StringBuilder preImages = new StringBuilder();
          StringBuilder titles = new StringBuilder();

          for (int count = 0; count < ids.length; count++) {

            CandyStudyDetail studyDetail = this.candyStudyDetailService.queryCandyStudyDetailByCandyStudyId(ids[count]);

            if (count == ids.length - 1) {
              preImages.append(studyDetail.getPreImage());
              titles.append(studyDetail.getTitle());
            } else {
              preImages.append(studyDetail.getPreImage()).append(",");
              titles.append(studyDetail.getTitle()).append("**");
            }

          }
          showInMainpage.setPreImages(preImages.toString());
          showInMainpage.setTitles(titles.toString());
          newList.add(showInMainpage);
        }


        if (tabbar[1].equals("minivideo")) {
          String[] ids = showInMainpage.getCandyIds().split(",");

          StringBuilder preImages = new StringBuilder();
          StringBuilder titles = new StringBuilder();

          for (int count = 0; count < ids.length; count++) {

            CandyVideoDetail candyVideoDetail = this.candyVideoDetailService.queryCandyVideoDetailByCandyVideoId(ids[count]);

            if (count == ids.length - 1) {
              preImages.append(candyVideoDetail.getPreImage());
              titles.append(candyVideoDetail.getTitle());
            } else {
              preImages.append(candyVideoDetail.getPreImage()).append(",");
              titles.append(candyVideoDetail.getTitle()).append("**");
            }

          }
          showInMainpage.setPreImages(preImages.toString());
          showInMainpage.setTitles(titles.toString());
          newList.add(showInMainpage);
        }
      }

    return newList;
  }
}
