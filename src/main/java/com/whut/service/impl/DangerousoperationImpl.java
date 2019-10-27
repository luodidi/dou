package com.whut.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.whut.bean.Dangerousoperation;
import com.whut.dao.IDangerousoperationDao;
import com.whut.service.IDangerousoperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author lixing
 * @version 1.0
 * @date 2019/10/16 16:07
 */
@Service //获得数据

public class DangerousoperationImpl implements IDangerousoperationService {


    @Autowired
    private IDangerousoperationDao iDangerousoperationDao;


//    //查询
////    @Override
////    public List<Dangerousoperation>findDangerousoperation(int pagenum, int pagesize) {
////        PageHelper.startPage(pagenum,pagesize);//开始页面
////        return iDangerousoperationDao.findDangerousoperation();
////    }


    @Override
    public PageInfo<Map<String, Object>> findDangerousoperation(Integer page, Integer size) {
        PageHelper.startPage(page,size);
        List<Map<String,Object>> list=iDangerousoperationDao.findDangerousoperation();
        PageInfo<Map<String,Object>> pageInfo= new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public List<Map<String, Object>> findListDangerousoperation() {
        return iDangerousoperationDao.findListDangerousoperation();
    }

    @Override
    public Map<String, Object> find(Integer dangerousoperationId) {
        return iDangerousoperationDao.find(dangerousoperationId);}


    @Override
    public int deletedangerousoperation(int dangerousoperationId) {
        return iDangerousoperationDao.deletedangerousoperation(dangerousoperationId);
    }

    @Override
    public int addangerousoperation(Dangerousoperation dangerousoperation) {
        return iDangerousoperationDao.addangerousoperation(dangerousoperation);
    }

    @Override
    public int updateangerousoperation(Dangerousoperation dangerousoperation) {
        return iDangerousoperationDao.updateangerousoperation(dangerousoperation);
    }

    @Override
    public int submitdangerousoperation(int dangerousoperationId) {
        return iDangerousoperationDao.submitdangerousoperation(dangerousoperationId);
    }

    @Override
    public int withdrawdangerousoperation(int dangerousoperationId) {
        return iDangerousoperationDao.withdrawdangerousoperation(dangerousoperationId);
    }

    @Override
    public int filedangerousoperation(int dangerousoperationId) {
        return iDangerousoperationDao.filedangerousoperation(dangerousoperationId);
    }

    @Override
    public PageInfo<Map<String, Object>> getListWait(int pageSize,int pageNum) {
        PageHelper.startPage(pageNum,pageSize);
        return new PageInfo<>(iDangerousoperationDao.getListWait());
    }

    @Override
    public PageInfo<Map<String, Object>> getListNoWait(int pageNum,int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return new PageInfo<>(iDangerousoperationDao.getListNoWait());
    }

    //审核通过或退回
    public int checkStatus(Integer id,String status)
    {
        return iDangerousoperationDao.pass(id,status);
    }


}
