package com.whut.service;

import com.whut.bean.CheckTableDetail;
import com.whut.bean.HiddenDanger;
import com.whut.bean.Input;

/*
  Created by IntelliJ IDEA.
  User: ccc
  Date: 2019/10/17
  Time: 10:31
  To change this template use File | Settings | File Templates.
*/
public interface IInputService {
    public int insertHiddenDanger(HiddenDanger hiddenDanger);
    public int insertInput(Input input);
    public int insertCheckTableDetail(CheckTableDetail checkTableDetail);
}
