package com.whut.controller;

import com.alibaba.fastjson.JSONObject;
import com.whut.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author luodidi
 * @version 1.0
 * @date 2019/10/14 15:20
 */

@RestController
public class UserController {
    @Autowired
    private IUserService userService;


    @RequestMapping("/api/login")
    public String login(
            @RequestParam("id") Integer id,
            @RequestParam("password") String password
    )
    {
        Map<String,Object> map =userService.login(id,password);
        JSONObject re=new JSONObject();
        if(map==null)
        {
            re.put("status",0);
            re.put("message","账号或密码错误");
        }
        else
        {
            re.put("status",1);
            JSONObject data=new JSONObject();
            data.put("username",map.get("userName"));
            data.put("id",map.get("id"));
            data.put("roleId",map.get("roleId"));
            data.put("role",map.get("roleName"));
            data.put("deptId",map.get("deptId"));
            data.put("password",map.get("password"));
            re.put("data",data);
        }
       return re.toJSONString();
    }




}
