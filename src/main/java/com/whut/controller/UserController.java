package com.whut.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.whut.bean.User;
import com.whut.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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

//    @Transactional
//    @RequestMapping("/api/login")
//    public String login(
//    )
//    {
//        JSONObject re=new JSONObject();
//            re.put("status",1);
//            JSONObject data=new JSONObject();
//            data.put("id",123);
//            re.put("data",data);
//        return re.toJSONString();
//    }

    @Transactional
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

    @Transactional
    @RequestMapping("/test")
    public String test(
            @RequestParam("list") String list
    )
    {
        JSONObject jsonObject= JSON.parseObject(list);
        JSONArray jsonArray=jsonObject.getJSONArray("data");
        for(Object o:jsonArray)
        {
            Map<String,Object> map=(Map<String, Object>) o;
            map.get("1");
        }
        return "1";
    }


    //根据id删除角色
    //delete
    @RequestMapping("/api/user/delete")
    public String deleteUser(
            @RequestParam("id") Integer id
    )
    {
        int i=userService.deleteUser(id);
        JSONObject re=new JSONObject();

        //删除成功
        if(i>0) {
            re.put("status",1);
            re.put("message","删除成功");
        }
        //删除失败
        else
        {
            re.put("status",0);
            re.put("message","删除失败");
        }
        return re.toJSONString();
    }


    // 插入用户信息
    //POST

    //插入有问题、、、、、、、、、、、、、、、、、、、、、、、、、、、、。。。。。。。。。。。
    @RequestMapping("/api/user/insert")
    public String insertUser(
            @RequestParam("name") String name,
            @RequestParam("roleId")Integer roleId,
            @RequestParam("deptId") Integer deptId,
            @RequestParam("password")String password
    )
    {
        User users=new User();

        users.setName(name);
        users.setRoleId(roleId);
        users.setDeptId(deptId);
        users.setPassword(password);

        int insertId=userService.insertUser(users);
        JSONObject jsonObject=new JSONObject();
        if(insertId>0) {
            jsonObject.put("status",1);
            JSONObject jsonObject1=new JSONObject();

            jsonObject1.put("id",insertId);
            jsonObject1.put("name",name);
            jsonObject1.put("roleId",roleId);
            jsonObject1.put("deptId",deptId);
            jsonObject1.put("password",password);

            jsonObject.put("data",jsonObject1);
        }
        else {

            jsonObject.put("status",0);
            jsonObject.put("message","添加失败");
        }

        //返回检查表的数据
        return jsonObject.toJSONString();
    }


    // 更新用户信息
    @RequestMapping("/api/user/update")
    public String updateUser(
            @RequestParam("id") Integer id,
            @RequestParam("name") String name,
            @RequestParam("roleId")Integer roleId,
            @RequestParam("deptId") Integer deptId,
            @RequestParam("password")String password)
    {
        User user=new User();

        user.setId(id);
        user.setName(name);
        user.setRoleId(roleId);
        user.setDeptId(deptId);
        user.setPassword(password);

        int nums=userService.updateUserById(user);

        JSONObject re=new JSONObject();

        if(nums>0)
        {
            re.put("status",1);
            JSONObject data=new JSONObject();

            data.put("id",user.getId());
            data.put("name",user.getName());
            data.put("roleId",user.getRoleId());
            data.put("deptId",user.getDeptId());
            data.put("password",user.getPassword());
            re.put("data",data);
        }
        else {

            re.put("status",0);
            re.put("message","添加失败");

        }
        return re.toJSONString();
    }

    //获取检查表列表（模板）分页
    //GET
    @RequestMapping("/api/user/getList")
    public String getList(Integer pageNum,Integer pageSize)
    {

        PageInfo<Map<String,Object>> list=userService.getListUser(pageNum,pageSize);

        JSONObject re=new JSONObject();
        re.put("status",1);
        JSONObject data=new JSONObject();
        data.put("total",list.getSize());
        data.put("pageNum",pageNum);
        data.put("pageSize",pageSize);
        JSONArray listJSON=new JSONArray();
        for(Map<String,Object> map:list.getList())
        {
            JSONObject temp=new JSONObject();

            temp.put("id",map.get("id"));
            temp.put("name",map.get("name"));
            temp.put("roleId",map.get("roleId"));
            temp.put("deptId",map.get("deptId"));
            temp.put("deptName",map.get("deptName"));
            temp.put("password",map.get("password"));


            listJSON.add(temp);
        }
        data.put("list",listJSON);
        re.put("data",data);

        return re.toJSONString();
    }

}
