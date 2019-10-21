package com.whut.service;

import com.github.pagehelper.PageInfo;
import com.whut.bean.Department;

import java.util.List;
import java.util.Map;

/**
 * @author luodidi
 * @version 1.0
 * @date 2019/10/19 12:12
 */
public interface IDepartmentService {

    public PageInfo<Map<String,Object>> getListDepartment(Integer page, Integer size);

    // 添加部门
    public int insertDepartment(Department department);

    // 修改部门
    public int updateDepartmentById(Department department);

    // 删除部门
    public int deleteDepartment(Integer id);

}
