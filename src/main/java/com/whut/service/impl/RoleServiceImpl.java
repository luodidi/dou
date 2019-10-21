package com.whut.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.whut.bean.Role;
import com.whut.dao.IRoleDao;
import com.whut.service.IRiskService;
import com.whut.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author luodidi
 * @version 1.0
 * @date 2019/10/19 12:29
 */
@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
   private IRoleDao iRoleDao;

    @Override
    public PageInfo<Map<String,Object>> getListRole(Integer page, Integer size){
        PageHelper.startPage(page,size);
        List<Map<String,Object>> list=iRoleDao.getListRole();
        PageInfo<Map<String,Object>> pageInfo= new PageInfo<>(list);
        return pageInfo;
    }


    // 通过id查找 显示详细的信息
    @Override
    public int getRoleById(int id){
        return iRoleDao.getRoleById(id);
    }

    //添加风险
    @Override
    public int insertRole(Role role){
        return iRoleDao.insertRole(role);
    }

    // 更新
    @Override
    public int updateRoleById(Role role){
        return iRoleDao.updateRoleById(role);
    }

    // 删除
    @Override
    public int deleteRole(Integer id){
        return iRoleDao.deleteRole(id);
    }
}
