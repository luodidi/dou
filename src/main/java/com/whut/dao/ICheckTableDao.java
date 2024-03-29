package com.whut.dao;

import com.whut.bean.CheckTable;
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
  Time: 12:10
  To change this template use File | Settings | File Templates.
*/
@Mapper
@Repository
public interface ICheckTableDao {

    //添加一个检查表模板
    public int insertCheckTable(CheckTable checkTable);

    //获得所有的检查表模板
    public List<Map<String, Object>> getListCheckTable();

    //根据检查表模板的id来修改该检查表的xx
    public int updateCheckTable(CheckTable checkTable);

    //获取所有检查表的id和name
    public List<Map<String, Object>> getIdAndNameListCheckTable();

    //根据检查表id获得其详情
    public Map<String, Object> getDetailCheckTable(Integer checkTableId);

    //由检查表id删除检查表
    public int deleteCheckTable(@Param("checkTableId") Integer checkTableId, @Param("deleteDate")Date deleteDate);

}