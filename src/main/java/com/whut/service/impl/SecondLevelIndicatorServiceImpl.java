package com.whut.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.whut.bean.SecondLevelIndicator;
import com.whut.dao.ISecondLevelIndicatorDao;
import com.whut.service.ISecondLevelIndicatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/*
  Created by IntelliJ IDEA.
  User: ccc
  Date: 2019/10/16
  Time: 19:02
  To change this template use File | Settings | File Templates.
*/
@Service
public class SecondLevelIndicatorServiceImpl implements ISecondLevelIndicatorService {

    @Autowired
    private ISecondLevelIndicatorDao secondLevelIndicatorDao;
    //添加检查表二级指标
    @Override
    public int insertSecondLevelIndicator(SecondLevelIndicator secondLevelIndicator) {
        secondLevelIndicatorDao.insertSecondLevelIndicator(secondLevelIndicator);

        //返回添加的二级指标的id
        return secondLevelIndicator.getId();
    }

    //修改二级指标
    @Override
    public int updateSecondLevelIndicator(SecondLevelIndicator secondLevelIndicator) {
        return secondLevelIndicatorDao.updateSecondLevelIndicator(secondLevelIndicator);
    }

    @Override
    public PageInfo<Map<String, Object>> getListSecondLevelIndicator(Integer firstLevelIndicatorId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Map<String,Object>> list=secondLevelIndicatorDao.getListSecondLevelIndicator(firstLevelIndicatorId);
        return new PageInfo<>(list);
    }

    @Override
    public List<Map<String, Object>> getAllListSecondLevelIndicator(Integer firstLevelIndicatorId) {
        return secondLevelIndicatorDao.getAllListSecondLevelIndicator(firstLevelIndicatorId);
    }

    @Override
    public Map<String, Object> getDetailSecondLevelIndicator(Integer SecondLevelIndicatorId) {
        return secondLevelIndicatorDao.getDetailSecondLevelIndicator(SecondLevelIndicatorId);
    }
}
