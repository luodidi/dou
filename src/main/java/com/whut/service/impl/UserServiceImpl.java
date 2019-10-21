package com.whut.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.whut.bean.User;
import com.whut.dao.IUserDao;
import com.whut.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author luodidi
 * @version 1.0
 * @date 2019/10/14 15:13
 */
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
   private IUserDao iUserDao;


    @Override
    public Map<String,Object> login(Integer id, String password) {
        return iUserDao.login(id,password);
    }


    @Override
    public PageInfo<Map<String,Object>> getListUser(Integer page, Integer size){
        PageHelper.startPage(page,size);
        List<Map<String,Object>> list=iUserDao.getListUser();
        PageInfo<Map<String,Object>> pageInfo= new PageInfo<>(list);
        return pageInfo;
    }
    // 添加用户
    @Override
    public  int insertUser(User user){
        iUserDao.insertUser(user);
        return user.getId();
    }
    // 修该用户
   @Override
    public int updateUserById(User user){
        return iUserDao.updateUserById(user);

    }
    // 通过删除用户
    @Override
    public int deleteUser(Integer id){
        return iUserDao.deleteUser(id);
    }

}
