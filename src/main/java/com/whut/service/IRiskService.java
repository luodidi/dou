package com.whut.service;

import com.github.pagehelper.PageInfo;
import com.whut.bean.Risk;

import java.util.List;
import java.util.Map;

/**
 * @author luodidi
 * @version 1.0
 * @date 2019/10/19 12:14
 */
public interface IRiskService {

    public PageInfo<Map<String,Object>> getListRisk(Integer page, Integer size);

    public int getRiskById(int id);             // 通过id查找 显示详细的信息

    public int insertRisk(Risk risk);           // 添加风险

    public  int updateRiskById(Risk risk);      // 更新

    public int deleteRisk(Integer id);          // 删除

    public Map<String, Object> getDetailRisk(Integer riskId);      // 通过id获取详细信息

    public List<Map<String,Object>> getAllList(); //获得所有风险点的信息



}
