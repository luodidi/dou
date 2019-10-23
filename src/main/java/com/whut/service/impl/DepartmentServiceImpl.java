package com.whut.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.whut.bean.Department;
import com.whut.dao.IDepartmentDao;
import com.whut.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author luodidi
 * @version 1.0
 * @date 2019/10/19 12:22
 */
@Service
public class DepartmentServiceImpl implements IDepartmentService {
    @Autowired
    private IDepartmentDao iDepartmentDao;

    @Override
    public PageInfo<Map<String,Object>> getListDepartment(Integer page, Integer size){
        PageHelper.startPage(page,size);
        List<Map<String,Object>> list=iDepartmentDao.getListDepartment();
        PageInfo<Map<String,Object>> pageInfo= new PageInfo<>(list);
        return pageInfo;
    }


    // 添加部门
    @Override
    public int insertDepartment(Department department){
        return iDepartmentDao.insertDepartment(department);
    }

    // 修改部门
    @Override
    public int updateDepartmentById(Department department){
        return iDepartmentDao.updateDepartmentById(department);
    }

    // 删除部门
    @Override
    public int deleteDepartment(Integer id){
        return iDepartmentDao.deleteDepartment(id);
    }

    @Override
    public List<Map<String, Object>> getAllDept() {
        return iDepartmentDao.getListDepartment();
    }

}
