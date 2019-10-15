package com.whut.service.impl;

import com.whut.bean.User;
import com.whut.mapper.IUserDao;
import com.whut.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
