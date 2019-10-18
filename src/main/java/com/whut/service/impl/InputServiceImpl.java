package com.whut.service.impl;

import com.whut.bean.CheckTableDetail;
import com.whut.bean.HiddenDanger;
import com.whut.bean.Input;
import com.whut.dao.IInputDao;
import com.whut.service.IInputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


    public int insetHiddenDanger(HiddenDanger hiddenDanger)
    {
        return inputDao.insetHiddenDanger(hiddenDanger);
    }
}
