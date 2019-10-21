package com.whut.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.whut.bean.SecondLevelIndicator;
import com.whut.service.ISecondLevelIndicatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/*
  Created by IntelliJ IDEA.
  User: ccc
  Date: 2019/10/16
  Time: 19:09
  To change this template use File | Settings | File Templates.
*/
@RestController
public class SecondLevelIndicatorController {
    @Autowired
    private ISecondLevelIndicatorService secondLevelIndicatorService;

    //添加检查表中一级指标下的二级指标
    //Post
    @RequestMapping("/api/secondLevelIndicator/insert")
    public String insertSecondLevelIndicator(
            @RequestParam("firstLevelIndicatorId") Integer firstLevelIndicatorId,
            @RequestParam("content") String content
    )
    {
        //创建secondLevelIndicator对象
        SecondLevelIndicator secondLevelIndicator=new SecondLevelIndicator();
        //id为自增
        secondLevelIndicator.setFirstLevelIndicatorId(firstLevelIndicatorId);
        secondLevelIndicator.setContent(content);
        Date date=new Date();//添加时间为系统当前时间
        secondLevelIndicator.setAddDate(date);
        //添加时是否删除默认为false
        secondLevelIndicator.setDelete(false);

        //调用service层对象实现添加
        int nums=secondLevelIndicatorService.insertSecondLevelIndicator(secondLevelIndicator);

        //创建json对象来返回值
        JSONObject re=new JSONObject();
        //添加成功
        if(nums>0)
        {
//            成功：
//            {
//                "status": 1,
//                    "data": {
//                "id": 1,
//                        "firstLevelIndicatorId": 3,
//                        "content": "作业人员身体、精神状况良好,无饮酒现象",
//                        "addDate": "2010-9-10 13:32:20",
//                        "isDelete": true,
//                        "deleteDate": "2010-10-15 09:10:32"
//            }
            re.put("status",1);
            JSONObject data=new JSONObject();
            data.put("id",nums);
            data.put("firstLevelIndicatorId",firstLevelIndicatorId);
            data.put("content",content);
            data.put("addDate",new SimpleDateFormat("yyyy-MM-dd").format(date));
            data.put("isDelete",false);
            data.put("deleteDate","1970-01-01");
            re.put("data",data);
        }
        //添加失败
        else
        {
//            失败：
//            {
//                "status": 0,
//                    "message": "添加二级指标失败"
//            }
            re.put("status",0);
            re.put("message","添加二级指标失败");
        }
        return re.toJSONString();
    }

    //修改二级指标
    //Post
    @RequestMapping("/api/secondLevelIndicator/update")
    public String updateSecondLevelIndicator(
            @RequestParam("id") Integer id,
            @RequestParam("firstLevelIndicatorId") Integer firstLevelIndicatorId,
            @RequestParam("content") String content,
            @RequestParam("addDate") String addDate,
            @RequestParam("isDelete") Boolean isDelete,
            @RequestParam("deleteDate") String deleteDate
    )
    {
//        |参数        			  |是否必选     |类型      |说明
//            |id			      |Y			|int	  |自己的id
//            |firstLevelIndicatorId |Y	 		|int      |所属一级指标的id
//            |content			   |Y			|string   |内容
//            |addDate	     	   |N           |string   |添加日期
//            |isDelete        	   |N           |boolean  |是否删除
//            |deleteDate            |N           |string   |删除日期
        //创建secondLevelIndicator对象
        SecondLevelIndicator secondLevelIndicator=new SecondLevelIndicator();
        secondLevelIndicator.setId(id);
        secondLevelIndicator.setFirstLevelIndicatorId(firstLevelIndicatorId);
        secondLevelIndicator.setContent(content);
        try {
            secondLevelIndicator.setAddDate(new SimpleDateFormat("yyyy-MM-dd").parse(addDate));
            secondLevelIndicator.setDeleteDate(new SimpleDateFormat("yyyy-MM-dd").parse(deleteDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        secondLevelIndicator.setDelete(isDelete);
        //调用service层更新
        int nums=secondLevelIndicatorService.updateSecondLevelIndicator(secondLevelIndicator);
        //创建json来返回
        JSONObject re=new JSONObject();
        //更新成功
//        成功：
//        {
//            "status": 1,
//                "data": {
//            "id": 1,
//                    "firstLevelIndicatorId": 3,
//                    "content": "作业人员身体、精神状况良好,无饮酒现象",
//                    "addDate": "2010-9-10 13:32:20",
//                    "isDelete": true,
//                    "deleteDate": "2010-10-15 09:10:32"
//        }
        if(nums>0)
        {
            re.put("status",1);
            JSONObject data=new JSONObject();
            data.put("id",id);
            data.put("firstLevelIndicatorId",firstLevelIndicatorId);
            data.put("addDate",addDate);
            data.put("isDelete",isDelete);
            data.put("deleteDate",deleteDate);
            re.put("data",data);
        }
        //更新失败
//        失败：
//        {
//            "status": 0,
//                "message": "添加二级指标失败"
//        }
        else
        {
            re.put("status",0);
            re.put("message","添加二级指标失败");
        }
        return re.toJSONString();
    }

    //由一级指标id获取所有二级指标(分页)
    //Get
    @RequestMapping("/api/secondLevelIndicator/getList")
    public String getListSecondLevelIndicator(Integer firstLevelIndicatorId,Integer pageNum,Integer pageSize)
    {
        //调用service层根据一级指标id获取所有二级指标数据
        PageInfo<Map<String,Object>> pageInfo=secondLevelIndicatorService.getListSecondLevelIndicator(
                firstLevelIndicatorId,pageNum,pageSize);
        //创建json来返回数据
        JSONObject re=new JSONObject();
//        成功：
//        {
//            "status": 1,
//                "data": {
//            "firstLevelIndicatorId": 2,
//                    "total": 20,	//总记录条数
//                    "pageNum": 1,	//当前页号
//                    "pageSize": 10,	//每页条数
//                    "list": [
//            {
//                "id": 1,
//                    "firstLevelIndicatorId": 3,
//                    "content": "作业人员身体精神状况良好,无饮酒现象",
//                    "addDate": "2010-9-10 13:32:20",
//                    "isDelete": true,
//                    "deleteDate": "2010-10-15 09:10:32"
//            }
//		]
//        }
        re.put("status",1);
        JSONObject data=new JSONObject();
        data.put("firstLevelIndicatorId",firstLevelIndicatorId);
        data.put("total",pageInfo.getSize());
        data.put("pageNum",pageNum);
        data.put("pageSize",pageSize);
        JSONArray array=new JSONArray();
        //遍历list
        for(Map<String,Object> map:pageInfo.getList())
        {
            JSONObject temp=new JSONObject();
            temp.put("id",map.get("id"));
            temp.put("firstLevelIndicator",map.get("firstLevelIndicator"));
            temp.put("content",map.get("content"));
            temp.put("addDate",new SimpleDateFormat("yyyy-MM-dd").format((Date) map.get("addDate")));
            temp.put("isDelete",map.get("isDelete"));
            temp.put("deleteDate",new SimpleDateFormat("yyyy-MM-dd").format((Date)map.get("deleteDate")));
            array.add(temp);
        }
        data.put("list",array);
        re.put("data",data);
        return re.toJSONString();
    }

    //由一级指标id获取所有二级指标（不分页）
    //Post
    @RequestMapping("/api/secondLevelIndicator/getAllList")
    public String getAllListSecondLevelIndicator(
            @RequestParam("firstLevelIndicatorId") Integer firstLevelIndicatorId)
    {
        List<Map<String,Object>> list= secondLevelIndicatorService.getAllListSecondLevelIndicator(firstLevelIndicatorId);
        //创建json对象
        JSONObject re=new JSONObject();
        //创建JSONArray并且遍历list
        if(list.size()>0)
        {
            JSONArray array=new JSONArray();
            for(Map<String,Object> map:list)
            {
                JSONObject temp = new JSONObject();
                temp.put("id", map.get("id"));
                temp.put("content", map.get("content"));
                array.add(temp);
            }
            re.put("status",1);
            re.put("data",array);
        }
        else
        {
            re.put("status",0);
            re.put("message","当前该表暂无一级指标信息");
        }
        return re.toJSONString();
    }

    //由二级指标id获得其详情
    //Post
    @RequestMapping("/api/secondLevelIndicator/getDetail")
    public String getDetailSecondLevelIndicator(
            @RequestParam("secondLevelIndicatorId") Integer secondLevelIndicatorId)
    {
        Map<String,Object> map=secondLevelIndicatorService.getDetailSecondLevelIndicator(secondLevelIndicatorId);
//        select s.id,f.project,s.content,s.addDate,s.isDelete,s.deleteDate
//        from secondlevelindicators as s,firstlevelindicators as f
//        where s.id=#{SecondLevelIndicatorId} and s.firstLevelIndicatorId=f.id
        JSONObject re=new JSONObject();
        if(map.size()>0)
        {
            re.put("status",1);
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("id",map.get("id"));
            jsonObject.put("project",map.get("project"));
            jsonObject.put("content",map.get("content"));
            jsonObject.put("addDate",new SimpleDateFormat("yyyy-MM-dd").format((Date) map.get("addDate")));
            jsonObject.put("isDelete",map.get("isDelete"));
            jsonObject.put("deleteDate",new SimpleDateFormat("yyyy-MM-dd").format((Date) map.get("deleteDate")));
            re.put("date",jsonObject);
        }
        else
        {
            re.put("status",0);
            re.put("message","获取失败");
        }
        return re.toJSONString();
    }

    //由id删除二级指标
    //Post
    @RequestMapping("/api/secondLevelIndicator/delete")
    public String deleteSecondLevelIndicator(
            @RequestParam("secondLevelIndicatorId") Integer secondLevelIndicatorId
    )
    {
        int i=secondLevelIndicatorService.deleteSecondLevelIndicator(secondLevelIndicatorId);
        JSONObject re=new JSONObject();
        //删除成功
        if(i>0)
        {
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
}
