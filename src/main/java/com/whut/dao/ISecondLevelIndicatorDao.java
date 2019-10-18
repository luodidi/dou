package com.whut.dao;

import com.whut.bean.SecondLevelIndicator;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/*
  Created by IntelliJ IDEA.
  User: ccc
  Date: 2019/10/16
  Time: 19:00
  To change this template use File | Settings | File Templates.
*/
@Mapper
@Repository
public interface ISecondLevelIndicatorDao {
    //检查表二级指标添加
    public int insertSecondLevelIndicator(SecondLevelIndicator secondLevelIndicator);
    //修改二级指标
    public int updateSecondLevelIndicator(SecondLevelIndicator secondLevelIndicator);
    //由一级指标id获取所有其二级指标(分页)
    public List<Map<String,Object>> getListSecondLevelIndicator(Integer firstLevelIndicatorId);
    //由一级指标id获取其所有的二级指标（不分页）
    public List<Map<String,Object>> getAllListSecondLevelIndicator(Integer firstLevelIndicatorId);
    //由二级指标id获得其详情
    public Map<String,Object> getDetailSecondLevelIndicator(Integer SecondLevelIndicatorId);
}
