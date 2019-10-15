package com.whut.controller;

import com.alibaba.fastjson.JSONObject;
import com.whut.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author luodidi
 * @version 1.0
 * @date 2019/10/14 15:20
 */

@Controller
public class UserController {
    @Autowired
    private IUserService userService;

    @GetMapping("/")
    public String all(Model model) {
        return "index.html";
    }
    @ResponseBody
    @GetMapping("/api/login")
    public String login() {
        System.out.println("hello login xxx");
        JSONObject obj=new JSONObject();
        obj.put("status",1);

        JSONObject dataObj = new JSONObject();
        dataObj.put("id","admin");
        obj.put("data",dataObj );

        return obj.toJSONString();
    }

}
