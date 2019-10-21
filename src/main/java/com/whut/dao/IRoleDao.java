package com.whut.dao;

import com.whut.bean.Role;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author luodidi
 * @version 1.0
 * @date 2019/10/19 12:09
 */
@Mapper
@Repository
public interface IRoleDao {

    public int getRoleById(int id);        // 通过id查找 显示详细的信息

    public List<Map<String, Object>> getListRole();

    public int insertRole(Role role);      // 添加风险

    public int updateRoleById(Role role);     // 更新

    public int deleteRole(Integer id);         // 删除


}
