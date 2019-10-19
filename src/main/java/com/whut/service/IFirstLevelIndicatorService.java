package com.whut.service;

import com.github.pagehelper.PageInfo;
import com.whut.bean.FirstLevelIndicator;

import java.util.List;
import java.util.Map;

/*
  Created by IntelliJ IDEA.
  User: ccc
  Date: 2019/10/16
  Time: 15:41
  To change this template use File | Settings | File Templates.
*/
public interface IFirstLevelIndicatorService {
    public int insertFirstLevelIndicator(FirstLevelIndicator firstLevelIndicator);
    public PageInfo<Map<String,Object>> getListFirstLevelIndicator(Integer checkTableId, Integer pageNum, Integer pageSize);
    public int updateFirstLevelIndicator(FirstLevelIndicator firstLevelIndicator);
    public List<Map<String,Object>> getAllListFirstLevelIndicator(Integer checkTableId);
    public Map<String,Object> getDetailFirstLevelIndicator(Integer firstLevelIndicatorId);
    public int deleteFirstLevelIndicator(Integer firstLevelIndicatorId);

}
