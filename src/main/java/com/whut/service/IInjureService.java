package com.whut.service;

import com.github.pagehelper.PageInfo;
import com.whut.bean.Injure;

import java.util.List;
import java.util.Map;

/**
 * @author lixing
 * @version 1.0
 * @date 2019/10/20 22:48
 */
public interface IInjureService {
    public int addInjure(Injure injure);
    public int updateInjure(Injure injure);
    public  int deleteInjuret(Integer accidentId,Integer userId);
    public Map<String,Object> getInjuredetail(Integer accidentId, Integer userId);//详细
    public List<Map<String, Object>> getInjure(); //查询部分
    public PageInfo<Map<String, Object>> getListInjuret(Integer page, Integer size);//查询全部



}
