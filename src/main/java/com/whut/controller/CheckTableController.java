package com.whut.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.whut.bean.CheckTable;
import com.whut.service.ICheckTableService;
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
  Time: 12:15
  To change this template use File | Settings | File Templates.
*/
@RestController
public class CheckTableController {
    @Autowired
    ICheckTableService checkTableService;


    //添加一条检查表模板
    //POST
    @RequestMapping("/api/checkTable/insert")
    public String insertCheckTable(
                                   @RequestParam("name") String name,
                                   @RequestParam("identifier")String identifier,
                                   @RequestParam("type") String type,
                                   @RequestParam("deptId")Integer deptId
                                   )
    {
        /**
         * // |参数		 |是否必选  |类型     |说明
         * //|id       	 |N		   |int     |检查表id
         * //|name        |Y        |string  |检查表名
         * //|identifier  |Y	       |string  |检查表编号
         * //|type        |Y		   |string  |检查表类型
         * //|depId       |Y        |int     |所属门
         * //|addDate	 |N        |string  |添加日期
         * //|isDelete    |N        |boolean |是否删除
         * //|deleteDate  |N        |string  |删除日期
         */

        //创建CheckTable对象
        //id自增

        CheckTable checkTable=new CheckTable();

        checkTable.setName(name);
        checkTable.setIdentifier(identifier);
        checkTable.setType(type);
        checkTable.setDeptId(deptId);
        Date date=new Date();
        checkTable.setAddDate(date);
        checkTable.setDelete(false);
        //添加时删除时间为null

        //获得新添加检查表在数据库中自增的id
        int insertId=checkTableService.insertCheckTable(checkTable);
        JSONObject jsonObject=new JSONObject();
        if(insertId>0)
        {
            //大于零时添加成功
            //返回json
//            {
//                "status": 1 ,
//                    "data": {
//                        "id": 1,
//                        "name": "井下采区变配电室安全检查表",
//                        "identifier": "xxx",
//                        "type": "项目安全",
//                        "depId": "112",
//                        "addDate": "2010-9-10 13:32:20",
//                        "isDelete": true,
//                        "deleteDate": "2010-10-15 09:10:32"
//            }

            jsonObject.put("status",1);

            JSONObject jsonObject1=new JSONObject();
            jsonObject1.put("id",insertId);
            jsonObject1.put("name",name);
            jsonObject1.put("identifier",identifier);
            jsonObject1.put("deptId",deptId);
            jsonObject1.put("addDate",new SimpleDateFormat("yyyy-MM-dd").format(date));
            jsonObject1.put("isDelete",false);
            jsonObject1.put("deleteDate",null);

            jsonObject.put("data",jsonObject1);
        }
        else {
//            失败：
//            {
//                "status": 0,
//                    "message": "添加失败"
//            }
            jsonObject.put("status",0);
            jsonObject.put("message","添加失败");
        }
        //返回检查表的数据
        return jsonObject.toJSONString();
    }

    //获取所有检查表的id和名字
    //Post
    @RequestMapping("/api/checkTable/getIdAndNameList")
    public String getIdAndNameListCheckTable()
    {
        //调用service层获取所有记录
        List<Map<String,Object>> list=checkTableService.getIdAndNameListCheckTable();
        //创建json对象作为返回值
        JSONObject re=new JSONObject();
        //创建JSONArray并且遍历list
        if(list.size()>0)
        {
            JSONArray array=new JSONArray();
            for(Map<String,Object> map:list)
            {
                JSONObject temp = new JSONObject();
                temp.put("id", map.get("id"));
                temp.put("name", map.get("name"));
                array.add(temp);
            }
            re.put("status",1);
            re.put("data",array);
        }
        else
        {
            re.put("status",0);
            re.put("message","当前暂无检查表信息");
        }
        return re.toJSONString();
    }
    //获取检查表列表（模板）分页
    //GET
    @RequestMapping("/api/checkTable/getList")
    public String getList(Integer pageNum,Integer pageSize)
    {
        //  |参数	   |是否必选  |类型      |说明
//	|pageNum   |Y       |int      |当前页号
//	|pageSize  |N		|int      |每页显示条数

        //获得数据
        PageInfo<Map<String,Object>> list=checkTableService.getListCheckTable(pageNum,pageSize);
//        成功：
//        {
//            "status": 1,
//                "data": {
//                "total": 32,	//总记录条数
//                    "pageNum": 2,
//                    "pageSize": 10,
//                    "list": [
//            {
//                "id": 1,
//                    "name": "井下采区变配电室安全检查表",
//                    "identifier": "xxx",
//                    "type": "项目安全",
//                    "depId": "112",
//                    "addDate": "2010-9-10 13:32:20",
//                    "isDelete": true,
//                    "deleteDate": "2010-10-15 09:10:32"
//            },
//            {
//                "id": 2,
//                    "name": "luodi身体检查表",
//                    "identifier": "xxx",
//                    "type": "项目安全",
//                    "depId": "112",
//                    "addDate": "2010-9-10 13:32:20",
//                    "isDelete": true,
//                    "deleteDate": "2010-10-15 09:10:32"
//            }
//		]
//        }
//        }

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
            temp.put("name",map.get("cName"));
            temp.put("identifier",map.get("identifier"));
            temp.put("type",map.get("type"));
            temp.put("dept",map.get("dName"));
            temp.put("addDate",new SimpleDateFormat("yyyy-MM-dd").format(map.get("addDate")));
            temp.put("isDelete",map.get("isDelete"));
            temp.put("deleteDate",new SimpleDateFormat("yyyy-MM-dd").format(map.get("deleteDate")));
            listJSON.add(temp);
        }
        re.put("list",listJSON);

        return re.toJSONString();
    }

    //修改检查表（模板）
    //POST
    @RequestMapping("/api/checkTable/update")
    public String updateCheckTable(
            @RequestParam("id") Integer id,
            @RequestParam("name") String name,
            @RequestParam("identifier")String identifier,
            @RequestParam("type") String type,
            @RequestParam("deptId")Integer deptId,
            @RequestParam("addDate") String addDate,
            @RequestParam("isDelete") Boolean isDelete,
            @RequestParam("deleteDate") String deleteDate
    )
    {
        CheckTable checkTable=new CheckTable();
        checkTable.setId(id);
        checkTable.setName(name);
        checkTable.setIdentifier(identifier);
        checkTable.setType(type);
        checkTable.setDeptId(deptId);

        try {
            checkTable.setAddDate(new SimpleDateFormat( "yyyy-MM-dd").parse(addDate));
            checkTable.setDeleteDate(new SimpleDateFormat( "yyyy-MM-dd").parse(deleteDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        checkTable.setDelete(isDelete);
        int nums=checkTableService.updateCheckTable(checkTable);

        JSONObject re=new JSONObject();
        if(nums>0)
        {
            //返回数据
//        成功：
//        {
//            "status": 1 ,
//                "data": {
//            "id": 1,
//                    "name": "井下采区变配电室安全检查表",
//                    "identifier": "xxx",
//                    "type": "项目安全",
//                    "depId": "112",
//                    "addDate": "2010-9-10 13:32:20",
//                    "isDelete": true,
//                    "deleteDate": "2010-10-15 09:10:32"
//        },
//        }
//
            re.put("status",1);
            JSONObject data=new JSONObject();
            data.put("id",checkTable.getId());
            data.put("name",checkTable.getName());
            data.put("identifier",checkTable.getIdentifier());
            data.put("type",checkTable.getType());
            data.put("deptId",checkTable.getId());
            data.put("addDate",checkTable.getAddDate());
            data.put("isDelete",checkTable.getDelete());
            data.put("deleteDate",checkTable.getDelete());
            re.put("data",data);
        }
        else {
//        失败：
//        {
//            "result": 0,
//                "message": "添加失败"
            re.put("status",0);
            re.put("message","添加失败");
//        }
        }
        return re.toJSONString();
    }

    //检查表子项指标获取 *待定
    //GET
    @RequestMapping("/api/checkTable/")
    public String ccc()
    {
        return "";
    }

}
