package com.whut.service;

import com.github.pagehelper.PageInfo;
import com.whut.bean.CheckTable;

import java.util.Map;

/*
  Created by IntelliJ IDEA.
  User: ccc
  Date: 2019/10/16
  Time: 12:11
  To change this template use File | Settings | File Templates.
*/
public interface ICheckTableService {
    public int insertCheckTable(CheckTable checkTable);
    public PageInfo<Map<String,Object>> getListCheckTable(Integer page, Integer size);
    public int updateCheckTable(CheckTable checkTable);
}
