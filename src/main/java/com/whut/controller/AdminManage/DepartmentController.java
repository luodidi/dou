package com.whut.controller.AdminManage;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.whut.bean.Department;
import com.whut.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author luodidi
 * @version 1.0
 * @date 2019/10/19 23:53
 */
@RestController
public class DepartmentController {

    @Autowired
    IDepartmentService departmentService;

    //POST
    // 插入部门
    @RequestMapping("/api/department/insert")
    public String insertDepartment(
            @RequestParam("name") String name
    )
    {

        //id自增
        Department dept=new Department();
        dept.setName(name);

        int insertId=departmentService.insertDepartment(dept);
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

    //根据id删除部门
    //delete
    @RequestMapping("/api/department/delete")
    public String deleteDepartment(
            @RequestParam("id") Integer id
    )
    {
        int i=departmentService.deleteDepartment(id);
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

    // 更新部门
    @RequestMapping("/api/department/update")
    public String updateDepartment(
            @RequestParam("id") Integer id,
            @RequestParam("name") String name)
          
    {
        Department dept=new Department();

        dept.setId(id);
        dept.setName(name);

        int up=departmentService.updateDepartmentById(dept);

        JSONObject re=new JSONObject();

        if(up>0)
        {
            re.put("status",1);
            JSONObject data=new JSONObject();

            data.put("id",dept.getId());
            data.put("name",dept.getName());

            re.put("data",data);
        }
        else {

            re.put("status",0);
            re.put("message","更新失败");

        }
        return re.toJSONString();
    }

    //GET
    // 获取所有的部门信息
    @RequestMapping("/api/department/getList")
    public String getListDepartment(Integer pageNum,Integer pageSize)
    {

        PageInfo<Map<String,Object>> list=departmentService.getListDepartment(pageNum,pageSize);

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
        re.put("list",listJSON);

        return re.toJSONString();
    }


}
