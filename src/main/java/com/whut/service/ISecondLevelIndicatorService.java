package com.whut.service;

import com.github.pagehelper.PageInfo;
import com.whut.bean.SecondLevelIndicator;

import java.util.List;
import java.util.Map;

/*
  Created by IntelliJ IDEA.
  User: ccc
  Date: 2019/10/16
  Time: 19:02
  To change this template use File | Settings | File Templates.
*/
public interface ISecondLevelIndicatorService {
    public int insertSecondLevelIndicator(SecondLevelIndicator secondLevelIndicator);
    public int updateSecondLevelIndicator(SecondLevelIndicator secondLevelIndicator);
    public PageInfo<Map<String,Object>> getListSecondLevelIndicator(Integer firstLevelIndicatorId, int pageNum, int pageSize);
    public List<Map<String,Object>> getAllListSecondLevelIndicator(Integer firstLevelIndicatorId);
}
