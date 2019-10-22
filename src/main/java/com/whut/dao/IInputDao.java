package com.whut.dao;

import com.whut.bean.CheckTableDetail;
import com.whut.bean.HiddenDanger;
import com.whut.bean.Input;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

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

    //由隐患id获得其详情
    public Map<String, Object> getDetailHiddenDanger(Integer hiddenDangerId);

    //由录取表id获得其详情
    public Map<String, Object> getDetailInput(Integer inputId);

    //获取录入表列表（分页）
    public List<Map<String, Object>> getListInput();

    //下发整改：改变状态、添加指令下发人、整改下发日期。。。
    public int toRectify(HiddenDanger hiddenDanger);

    //完成整改
    public int rectify(HiddenDanger hiddenDanger);

    //获得各种状态的隐患表(非逾期)
    public List<Map<String, Object>> getHiddenDangerList(
            @Param("status") String status);

    //获取逾期的隐患
    public List<Map<String, Object>> getHiddenDangerListTimeOut();

    //修改隐患信息
    public int updateHiddenDanger(HiddenDanger hiddenDanger);

    //获得各种隐患的分类(未逾期)
    public List<Map<String,Object>> getNumberHiddenDanger();

    //获得各种隐患的分类（逾期）
    public List<Map<String,Object>> getNumberHiddenDangerTimeOut();
}