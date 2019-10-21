package com.whut.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.whut.bean.Hazard;
import com.whut.dao.IHazardDao;
import com.whut.service.IHazardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author luodidi
 * @version 1.0
 * @date 2019/10/19 12:23
 */
@Service
public class HazardServiceImpl implements IHazardService {
    @Autowired
    private IHazardDao iHazardDao;


    // 通过id查找 显示详细的信息
    @Override
    public   int getHazardById(Integer id){
        return iHazardDao.getHazardById(id);
    }

    @Override
    public PageInfo<Map<String,Object>> getListHazard(Integer page, Integer size){
        PageHelper.startPage(page,size);
        List<Map<String,Object>> list=iHazardDao.getListHazard();
        PageInfo<Map<String,Object>> pageInfo= new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public Map<String, Object> getDetailHazard(Integer hazardId) {
        return iHazardDao.getDetailHazard(hazardId);
    }

    // 添加危险源信息
    @Override
    public  int insertHazard(Hazard hazard){
        return iHazardDao.insertHazard(hazard);
    }

    // 更新
    @Override
    public  int updateHazardById(Hazard hazard){
        return iHazardDao.updateHazardById(hazard);
    }

    // 删除
    @Override
    public int deleteHazard(Integer id){
        return iHazardDao.deleteHazard(id);
    }


}
