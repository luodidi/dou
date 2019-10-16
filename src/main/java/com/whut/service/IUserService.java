package com.whut.service;

import com.whut.bean.User;

import java.util.Map;

/**
 * @author luodidi
 * @version 1.0
 * @date 2019/10/10 15:54
 */
public interface IUserService {

    User getAllUser(int user_id);
    public Map<String,Object> login(Integer id, String password);
}
