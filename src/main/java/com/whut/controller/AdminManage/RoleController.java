package com.whut.controller.AdminManage;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.whut.bean.Role;
import com.whut.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author luodidi
 * @version 1.0
 * @date 2019/10/19 23:54
 */
@RestController
public class RoleController {

    @Autowired
    IRoleService roleService;

    //POST
    // 插入部门信息
    @RequestMapping("/api/role/insert")
    public String insertRole(
            @RequestParam("name") String name
    )
    {
        //创建Risk对象
        //id自增
        Role role=new Role();

        role.setName(name);

        int insertId=roleService.insertRole(role);
        JSONObject jsonObject=new JSONObject();
        if(insertId>0) {
            jsonObject.put("status",1);
            JSONObject jsonObject1=new JSONObject();

            jsonObject1.put("id",insertId);
            jsonObject1.put("name",name);

            jsonObject.put("data",jsonObject1);
        }
        else {

            jsonObject.put("status",0);
            jsonObject.put("message","添加失败");
        }

        //返回数据
        return jsonObject.toJSONString();
    }

    //根据id删除角色
    //delete
    @RequestMapping("/api/role/delete")
    public String deleteRole(
            @RequestParam("id") Integer id
    )
    {
        int i=roleService.deleteRole(id);
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

    // post
    // 更新角色信息
    @RequestMapping("/api/role/update")
    public String updateRole(
            @RequestParam("id") Integer id,
            @RequestParam("name") String name)

    {
        Role roles=new Role();

        roles.setId(id);
        roles.setName(name);

        int up=roleService.updateRoleById(roles);

        JSONObject re=new JSONObject();

        if(up>0)
        {
            re.put("status",1);
            JSONObject data=new JSONObject();

            data.put("id",roles.getId());
            data.put("name",roles.getName());

            re.put("data",data);
        }
        else {

            re.put("status",0);
            re.put("message","更新失败");

        }
        // 返回数据
        return re.toJSONString();
    }

    //GET
    // 获取数据
    @RequestMapping("/api/role/getList")
    public String getList(Integer pageNum,Integer pageSize)
    {

        PageInfo<Map<String,Object>> list=roleService.getListRole(pageNum,pageSize);

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

            listJSON.add(temp);
        }
        data.put("list",listJSON);
        re.put("data",data);

        return re.toJSONString();
    }
}
