package com.whut.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.whut.bean.CheckTable;
import com.whut.dao.ICheckTableDao;
import com.whut.service.ICheckTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/*
  Created by IntelliJ IDEA.
  User: ccc
  Date: 2019/10/16
  Time: 12:14
  To change this template use File | Settings | File Templates.
*/
@Service
public class CheckTableServiceImpl implements ICheckTableService {
    @Autowired
    private ICheckTableDao checkTableDao;

    @Override
    public int insertCheckTable(CheckTable checkTable) {
        if(checkTableDao.insertCheckTable(checkTable)==1)
            return checkTable.getId();
        else
            return 0;
    }

    @Override
    public PageInfo<Map<String,Object>> getListCheckTable(Integer page, Integer size){
        PageHelper.startPage(page,size);
        List<Map<String,Object>> list=checkTableDao.getListCheckTable();
        PageInfo<Map<String,Object>> pageInfo= new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public int updateCheckTable(CheckTable checkTable) {
        return checkTableDao.updateCheckTable(checkTable);
    }

    @Override
    public List<Map<String, Object>> getIdAndNameListCheckTable() {
        return checkTableDao.getIdAndNameListCheckTable();
    }

    @Override
    public Map<String, Object> getDetailCheckTable(Integer checkTableId) {
        return checkTableDao.getDetailCheckTable(checkTableId);
    }

    @Override
    public int deleteCheckTable(Integer checkTableId) {
        return checkTableDao.deleteCheckTable(checkTableId);
    }


}
