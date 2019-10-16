package com.whut.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.whut.bean.FirstLevelIndicator;
import com.whut.dao.IFirstLevelIndicatorDao;
import com.whut.service.IFirstLevelIndicatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/*
  Created by IntelliJ IDEA.
  User: ccc
  Date: 2019/10/16
  Time: 15:42
  To change this template use File | Settings | File Templates.
*/
@Service
public class FirstLevelIndicatorImpl implements IFirstLevelIndicatorService{

    @Autowired
    private IFirstLevelIndicatorDao firstLevelIndicatorDao;

    //添加一条一级指标
    @Override
    public int insertFirstLevelIndicator(FirstLevelIndicator firstLevelIndicator) {
        firstLevelIndicatorDao.insertFirstLevelIndicator(firstLevelIndicator);
        return firstLevelIndicator.getId();
    }

    @Override
    public PageInfo<Map<String, Object>> getListFirstLevelIndicator(Integer checkTableId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Map<String,Object>> list=firstLevelIndicatorDao.getListFirstLevelIndicator(checkTableId);
        return new PageInfo<>(list);
    }

    @Override
    public int updateFirstLevelIndicator(FirstLevelIndicator firstLevelIndicator) {
        return firstLevelIndicatorDao.updateFirstLevelIndicator(firstLevelIndicator);
    }


}
