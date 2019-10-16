package com.whut.service.impl;

import com.whut.bean.User;
import com.whut.dao.IUserDao;
import com.whut.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author luodidi
 * @version 1.0
 * @date 2019/10/14 15:13
 */
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
   IUserDao iUserDao;


    @Override
    public User getAllUser(int user_id) {
        return iUserDao.getAllUser(user_id);
    }

    @Override
    public Map<String,Object> login(Integer id, String password) {
        return iUserDao.login(id,password);
    }
}
