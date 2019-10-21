package com.whut.dao;

import com.whut.bean.Department;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author luodidi
 * @version 1.0
 * @date 2019/10/19 12:05
 */
@Mapper
@Repository
public interface IDepartmentDao {

    public List<Map<String, Object>> getListDepartment();

    // 添加部门
    public int insertDepartment(Department department);

    // 修改部门
    public int updateDepartmentById(Department department);

    // 删除部门
    public int deleteDepartment(Integer id);
}
