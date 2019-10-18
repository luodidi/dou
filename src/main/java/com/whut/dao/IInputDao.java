package com.whut.dao;

import com.whut.bean.CheckTableDetail;
import com.whut.bean.HiddenDanger;
import com.whut.bean.Input;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/*
  Created by IntelliJ IDEA.
  User: ccc
  Date: 2019/10/17
  Time: 10:31
  To change this template use File | Settings | File Templates.
*/
@Mapper
@Repository
public interface IInputDao {
    //添加录入检查表
    public int insertInput(Input input);
    //添加隐患的信息
    public int insetHiddenDanger(HiddenDanger hiddenDanger);
    //添加二级指标具体内容
    public int insertCheckTableDetail(CheckTableDetail checkTableDetail);
}
