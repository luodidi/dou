package com.whut.dao;

import com.whut.bean.FirstLevelIndicator;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

/*
  Created by IntelliJ IDEA.
  User: ccc
  Date: 2019/10/16
  Time: 15:41
  To change this template use File | Settings | File Templates.
*/
@Mapper
@Repository
public interface IFirstLevelIndicatorDao {

    //检查表一级指标添加
    public int insertFirstLevelIndicator(FirstLevelIndicator firstLevelIndicator);

    //按照检查表id获取所有该检查表的一级指标（分页）
    public List<Map<String,Object>> getListFirstLevelIndicator(Integer checkTableId);

    //按照一级指标的id修改该一级指标
    public int updateFirstLevelIndicator(FirstLevelIndicator firstLevelIndicator);

    //根据检查表id获取所有该检查表的一级指标（不分页）
    public List<Map<String,Object>> getAllListFirstLevelIndicator(Integer checkTableId);

    //根据一级指标id获得其详情
    public Map<String,Object> getDetailFirstLevelIndicator(Integer firstLevelIndicatorId);

    //根据id删除一级指标
    public int deleteFirstLevelIndicator(@Param("firstLevelIndicatorId") Integer firstLevelIndicatorId, @Param("deleteDate")Date deleteDate);
}
