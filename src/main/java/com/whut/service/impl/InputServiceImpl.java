package com.whut.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.whut.bean.CheckTableDetail;
import com.whut.bean.HiddenDanger;
import com.whut.bean.Input;
import com.whut.dao.IInputDao;
import com.whut.service.IInputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/*
  Created by IntelliJ IDEA.
  User: ccc
  Date: 2019/10/17
  Time: 10:31
  To change this template use File | Settings | File Templates.
*/
@Service
public class InputServiceImpl implements IInputService {
    @Autowired
    IInputDao inputDao;


    public int insertInput(Input input)
    {
        return inputDao.insertInput(input);
    }


    @Override
    public int insertCheckTableDetail(CheckTableDetail checkTableDetail) {
        return inputDao.insertCheckTableDetail(checkTableDetail);
    }

    @Override
    public Map<String,Object> getDetailHiddenDanger(Integer hiddenDangerId) {
        return inputDao.getDetailHiddenDanger(hiddenDangerId);
    }

    @Override
    public Map<String, Object> getDetailInput(Integer inputId) {
        return inputDao.getDetailInput(inputId);
    }

    @Override
    public PageInfo<Map<String, Object>> getListInput(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return new PageInfo<>(inputDao.getListInput());
    }


    public int insertHiddenDanger(HiddenDanger hiddenDanger)
    {
        return inputDao.insetHiddenDanger(hiddenDanger);
    }
}
