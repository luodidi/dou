package com.whut.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.whut.bean.Risk;
import com.whut.dao.IHazardDao;
import com.whut.dao.IRiskDao;
import com.whut.service.IRiskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author luodidi
 * @version 1.0
 * @date 2019/10/19 12:30
 */
@Service
public class RiskServiceImpl implements IRiskService {

    @Autowired
    private IRiskDao iRiskDao;

    @Override
    public PageInfo<Map<String,Object>> getListRisk(Integer page, Integer size){
        PageHelper.startPage(page,size);
        List<Map<String,Object>> list= iRiskDao.getListRisk();
        PageInfo<Map<String,Object>> pageInfo= new PageInfo<>(list);
        return pageInfo;
    }

   // @Override
   // public Map<String, Object> getDetailRisk(Integer riskId) {return iRiskDao.getDetailRisk(riskId);}

    // 获取所有风险列表 通过id查找 显示详细的信息
    @Override
    public int getRiskById(int id){
        return iRiskDao.getRiskById(id);
    }

    // 添加用户
    @Override
    public int insertRisk(Risk risk){
        iRiskDao.insertRisk(risk);
        return risk.getId();
    }

    @Override
    public int updateRiskById(Risk risk){

        return iRiskDao.updateRiskById(risk);
    }

    @Override
    public int deleteRisk(Integer id){
        return iRiskDao.deleteRisk(id);
    }


}
