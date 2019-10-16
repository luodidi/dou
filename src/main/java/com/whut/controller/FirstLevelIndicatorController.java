package com.whut.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.whut.bean.FirstLevelIndicator;
import com.whut.service.IFirstLevelIndicatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/*
  Created by IntelliJ IDEA.
  User: ccc
  Date: 2019/10/16
  Time: 15:40
  To change this template use File | Settings | File Templates.
*/
@RestController
public class FirstLevelIndicatorController {

    @Autowired
    private IFirstLevelIndicatorService firstLevelIndicatorService;
    //添加一条一级指标
    //Post
    @RequestMapping("/api/firstLevelIndicator/insert")
    public String insertFirstLevelIndicator(
            @RequestParam("checkTableId") Integer checkTableId,
            @RequestParam("project") String project
    )
    {
//            |参数        		|是否必选     |类型     |说明
//            |id         	  |N		  |int	   |自己的id（自增长）
//            |checkTableId	  |Y		  |int     |所属检查表的id
//            |project		  |Y		  |string  |一级指标内容
//            |addDate	      |N          |string  |添加日期
//            |isDelete         |N          |boolean |是否删除
//            |deleteDate       |N          |string  |删除日期
        //创建一个一级指标对象
        FirstLevelIndicator firstLevelIndicator=new FirstLevelIndicator();
        firstLevelIndicator.setCheckTableId(checkTableId);
        firstLevelIndicator.setProject(project);
        Date date=new Date();
        firstLevelIndicator.setAddDate(date);
        firstLevelIndicator.setDelete(false);
        //调用service层添加一级指标
        int temp=firstLevelIndicatorService.insertFirstLevelIndicator(firstLevelIndicator);
        //创建返回值的json对象
        JSONObject re=new JSONObject();
        //添加成功
//        成功：
//        {
//            "status": 1,
//                "data": {
//            "id": 1,
//                    "checkTableId": 2,
//                    "project": "作业行为",
//                    "addDate": "2010-9-10 13:32:20",
//                    "isDelete": true,
//                    "deleteDate": "2010-10-15 09:10:32"
//        }

        if(temp>0)
        {
            re.put("status",1);
            JSONObject data=new JSONObject();
            data.put("id",temp);
            data.put("checkTableId",checkTableId);
            data.put("addDate",new SimpleDateFormat("yyyy-MM-dd").format(date));
            data.put("isDelete",false);
            data.put("deleteDate",null);
            re.put("data",data);
        }
        //添加失败
//        失败：
//        {
//            "status": 0,
//                "message": "添加失败"
//        }
        else
        {
            re.put("status",0);
            re.put("message","添加失败");
        }
        //返回json数据
        return re.toJSONString();
    }



    //按检查表id获取所有一级指标
    //Get
    @RequestMapping("/api/firstLevelIndicator/getList")
    public String getListFirstLevelIndicator(Integer checkTableId,Integer pageNum,Integer pageSize)
    {
        //根据检查表id获得所有的一级指标
        PageInfo<Map<String,Object>> pageInfo=firstLevelIndicatorService.getListFirstLevelIndicator(
                checkTableId,pageNum,pageSize);
        //遍历pageinfo的list，获取所有的一级指标信息
//        成功：
//        {
//            "status": 1,
//                "data": {
//            "checkTableId": 2,
//                    "total": 20,	//总记录条数
//                    "pageNum": 1,	//当前页号
//                    "pageSize": 10,	//每页条数
//                    "list": [
//            {
//                "id": 1,
//                    "checkTableId": 2,
//                    "project": "作业行为",
//                    "addDate": "2010-9-10 13:32:20",
//                    "isDelete": true,
//                    "deleteDate": "2010-10-15 09:10:32"
//            },
//            {
//                "id": 2,
//                    "checkTableId": 2,
//                    "project": "作业行为",
//                    "addDate": "2010-9-10 13:32:20",
//                    "isDelete": true,
//                    "deleteDate": "2010-10-15 09:10:32"
//            }
//		]
//        }
        JSONObject re=new JSONObject();
        re.put("status",1);
        JSONObject data=new JSONObject();
        data.put("checkTableId",checkTableId);
        data.put("total",pageInfo.getSize());
        data.put("pageNum",pageNum);
        data.put("pageSize",pageSize);
        JSONArray array=new JSONArray();
        for(Map<String,Object> map:pageInfo.getList())
        {
            JSONObject temp=new JSONObject();
            temp.put("id",map.get("id"));
            temp.put("checkTable",map.get("checkTableId"));
            temp.put("project",map.get("project"));
            temp.put("addDate",new SimpleDateFormat("yyyy-MM-dd").format((Date)map.get("addDate")));
            temp.put("isDelete",map.get("isDelete"));
            temp.put("deleteDate",new SimpleDateFormat("yyyy-MM-dd").format((Date)map.get("deleteDate")));
            array.add(temp);
        }
        data.put("list",array);
        re.put("data",data);
        //返回数据
        return re.toJSONString();
    }

    //根据一级指标的id修改该一级指标
    //Post
    @RequestMapping("/api/firstLevelIndicator/update")
    public String updateFirstLevelIndicator(
            @RequestParam("id") Integer id,
            @RequestParam("checkTableId") Integer checkTableId,
            @RequestParam("project") String project,
            @RequestParam("addDate") String addDate,
            @RequestParam("isDelete") Boolean isDelete,
            @RequestParam("deleteDate") String deleteDate
    )
    {
//            |参数        		|是否必选     |类型     |说明
//            |id         	  |Y		  |int	   |自己的id
//            |checkTableId	  |Y		  |int     |所属检查表的id
//            |project		  |Y		  |string  |一级指标内容
//            |addDate	      |N          |string  |添加日期
//            |isDelete         |N          |boolean |是否删除
//            |deleteDate       |N          |string  |删除日期
        //创建FirstLevelIndicator对象
        FirstLevelIndicator firstLevelIndicator=new FirstLevelIndicator();
        firstLevelIndicator.setId(id);
        firstLevelIndicator.setCheckTableId(checkTableId);
        firstLevelIndicator.setProject(project);
        try {
            firstLevelIndicator.setAddDate(new SimpleDateFormat("yyyy-MM-dd").parse(addDate));
            firstLevelIndicator.setDeleteDate(new SimpleDateFormat("yyyy-MM-dd").parse(deleteDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        firstLevelIndicator.setDelete(isDelete);
        //调用service层修改数据
        int num=firstLevelIndicatorService.updateFirstLevelIndicator(firstLevelIndicator);
        //创建json对象作为返回值
        JSONObject re=new JSONObject();
        //修改成功
        if(num>0)
        {
//            {
//                "status": 1,
//                    "data": {
//                "id": 1,
//                        "checkTableId": 2,
//                        "project": "作业行为",
//                        "addDate": "2010-9-10 13:32:20",
//                        "isDelete": true,
//                        "deleteDate": "2010-10-15 09:10:32"
//            }
              re.put("status",1);
              JSONObject data=new JSONObject();
              data.put("id",id);
              data.put("checkTableId",checkTableId);
              data.put("addDate",addDate);
              data.put("isDelete",isDelete);
              data.put("deleteDate",deleteDate);
              re.put("data",data);
        }
        //修改失败
        else
        {
//            失败：
//            {
//                "status": 0,
//                    "message": "添加失败"
//            }
            re.put("status",0);
            re.put("message","添加失败");
        }
        return re.toJSONString();
    }
}
