package com.whut.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.whut.bean.Injure;
import com.whut.dao.IInjureDao;
import com.whut.service.IAccidentService;
import com.whut.service.IInjureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author lixing
 * @version 1.0
 * @date 2019/10/20 22:47
 */
@Service
public class InjureImpl implements IInjureService {
    @Autowired
    IInjureDao iInjureDao;

//要修改
    @Override
    public int addInjure(Injure injure) {
        if (iInjureDao.addInjure(injure) == 1)

            return 1;
        else
            return 0;
    }

    @Override
    public int updateInjure(Injure injure) {
        return iInjureDao.updateInjure(injure);
    }

    @Override
    public int deleteInjuret(Integer accidentId, Integer userId) {
        return iInjureDao.deleteInjure(accidentId, userId);
    }

    @Override
    public Map<String, Object> getInjuredetail(Integer accidentId, Integer userId) {
        return iInjureDao.getInjuredetail(accidentId, userId);
    }

    @Override
    public List<Map<String, Object>> getInjure() {
        return iInjureDao.getInjure();
    }

    @Override
    public PageInfo<Map<String, Object>> getListInjuret(Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<Map<String, Object>> list = iInjureDao.getListInjuret();
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
}
