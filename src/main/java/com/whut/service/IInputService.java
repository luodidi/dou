package com.whut.service;

import com.github.pagehelper.PageInfo;
import com.whut.bean.CheckTableDetail;
import com.whut.bean.HiddenDanger;
import com.whut.bean.Input;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

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
    public Map<String,Object> getDetailHiddenDanger(Integer hiddenDangerId);
    public Map<String,Object> getDetailInput(String inputId);
    public PageInfo<Map<String,Object>> getListInput(Integer pageNum,Integer pageSize);
    public List<Map<String,Object>> getAllListInput();
    public int toRectify(HiddenDanger hiddenDanger);
    public int rectify(HiddenDanger hiddenDanger);
    public PageInfo<Map<String,Object>> getHiddenDangerList(String status,int pageNum,int pageSize);
    public PageInfo<Map<String, Object>> getHiddenDangerListTimeOut(int pageNum,int pageSize);
    public List<Map<String,Object>> getNumberHiddenDanger();
    public List<Map<String,Object>> getNumberHiddenDangerTimeOut();

    //由inputId和二级指标id获得所有二级指标内容
    public Map<String,Object> getAllCheckDetail(@Param("inputId") String inputId, @Param("secondId") Integer secondId);

    //撤回隐患
    public int withdrawHiddenDanger(Integer hiddenDangerId);
}
