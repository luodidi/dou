package com.whut.service;

import com.github.pagehelper.PageInfo;
import com.whut.bean.User;

import java.util.Map;

/**
 * @author luodidi
 * @version 1.0
 * @date 2019/10/10 15:54
 */
public interface IUserService {


    public Map<String,Object> login(Integer id, String password);

    public PageInfo<Map<String,Object>> getListUser(Integer page, Integer size);
    //显示用户的具体信息
    //public int getUserInfo(int id);

    // 添加用户
    public int insertUser(User user);

    // 修该用户
    public int updateUserById(User user);

    // 删除用户
    public int deleteUser(Integer id);

}
