package com.whut.service;

import com.github.pagehelper.PageInfo;
import com.whut.bean.Role;

import java.util.List;
import java.util.Map;

/**
 * @author luodidi
 * @version 1.0
 * @date 2019/10/19 12:15
 */
public interface IRoleService {

    public PageInfo<Map<String,Object>> getListRole(Integer page, Integer size);

    public int getRoleById(int id);           // 通过id查找 显示详细的信息

    public int insertRole(Role role);         // 添加风险

    public int updateRoleById(Role role);     // 更新

    public int deleteRole(Integer id);            // 删除
}
