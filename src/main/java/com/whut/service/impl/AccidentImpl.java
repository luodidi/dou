package com.whut.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.whut.bean.Accident;
import com.whut.dao.IAccidentDao;
import com.whut.service.IAccidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author lixing
 * @version 1.0
 * @date 2019/10/20 16:31
 */
@Service
public class AccidentImpl implements IAccidentService {

    @Autowired
    IAccidentDao iAccidentDao;

    @Override
    public int addAccident(Accident accident) {
       if( iAccidentDao.addAccident(accident)==1)
            return accident.getId();
        else
            return 0;
    }

    @Override
    public int updateAccident(Accident accident) {
        return iAccidentDao.updateAccident(accident);
    }

    @Override
    public int deleteAccident(Integer id) {
        return iAccidentDao.deleteAccident(id);
    }



    @Override
    public Map<String, Object> getdetail(Integer Accidentid) {
        return iAccidentDao.getdetail(Accidentid);
    }

    @Override
    public List<Map<String, Object>> getAccident() {
        return iAccidentDao.getAccident();
    }

    @Override
    public PageInfo<Map<String, Object>> getListAccident(Integer page, Integer size) {
        PageHelper.startPage(page,size);
        List<Map<String,Object>> list=iAccidentDao.getListAccident();
        PageInfo<Map<String,Object>> pageInfo= new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public int sumNumberId() {
        return iAccidentDao.sumNumberId();
    }

    @Override
    public List<Map<String, Object>> sumDirectLoss() {
        return iAccidentDao.sumDirectLoss();
    }

    @Override
    public List<Map<String, Object>> sumIndirectLoss() {
        return iAccidentDao.sumIndirectLoss();
    }

    @Override
    public List<Map<String, Object>> sumLevel() {
        return iAccidentDao.sumLevel();
    }


}
