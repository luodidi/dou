package com.whut.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.whut.bean.Hazard;

import com.whut.service.IHazardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


/**
 * @author luodidi
 * @version 1.0
 * @date 2019/10/19 18:57
 */
@RestController
public class HazardController {

    @Autowired
    IHazardService hazardService;

    //获取危险源表列表（模板）分页
    //GET
    @RequestMapping("/api/hazard/getList")
    public String getListHazard(Integer pageNum,Integer pageSize)
    {

        PageInfo<Map<String,Object>> list=hazardService.getListHazard(pageNum,pageSize);

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
            temp.put("riskId",map.get("riskId"));
            temp.put("name",map.get("name"));
            temp.put("type",map.get("type"));
            temp.put("l",map.get("l"));
            temp.put("e",map.get("e"));
            temp.put("c",map.get("c"));
            temp.put("degree",map.get("degree"));
            temp.put("measure",map.get("measure"));
            temp.put("level",map.get("level"));
            temp.put("deptId",map.get("deptId"));
            temp.put("userId",map.get("userId"));

            listJSON.add(temp);
        }
        re.put("list",listJSON);

        return re.toJSONString();
    }

    /*
    插入数据
     */
    //post
    @RequestMapping("/api/hazard/insert")
    public String insertHazard(
            @RequestParam("riskId") Integer riskId,
            @RequestParam("name")String name,
            @RequestParam("type") String type,
            @RequestParam("l")Integer l,
            @RequestParam("e")Integer e,
            @RequestParam("l")Integer c,
            @RequestParam("degree")String degree,
            @RequestParam("measure")String measure,
            @RequestParam("level")String level,
            @RequestParam("deptId")Integer deptId,
            @RequestParam("userId")Integer userId

    )
    {
        Hazard hazard=new Hazard();
        hazard.setRiskId(riskId);
        hazard.setName(name);
        hazard.setType(type);
        hazard.setL(l);
        hazard.setE(e);
        hazard.setC(c);
        hazard.setDegree(degree);
        hazard.setMeasure(measure);
        hazard.setLevel(level);
        hazard.setDeptId(deptId);
        hazard.setUserId(userId);


        int insertId=hazardService.insertHazard(hazard);
        JSONObject jsonObject=new JSONObject();
        if(insertId>0)
        {
            jsonObject.put("status",1);
            JSONObject jsonObject1=new JSONObject();

            jsonObject1.put("id",insertId);
            jsonObject1.put("riskId",riskId);
            jsonObject1.put("name",name);
            jsonObject1.put("type",type);
            jsonObject1.put("l",l);
            jsonObject1.put("e",e);
            jsonObject1.put("c",c);
            jsonObject1.put("name",name);
            jsonObject1.put("degree",degree);
            jsonObject1.put("measure",measure);
            jsonObject1.put("level",level);
            jsonObject1.put("deptId",deptId);
            jsonObject1.put("userId",userId);

            jsonObject.put("data",jsonObject1);
        }
        else {
            jsonObject.put("status",0);
            jsonObject.put("message","添加失败");
        }
        //返回数据给前端访问
        return jsonObject.toJSONString();
    }

    //根据id删除危险
    //delete
    @RequestMapping("/api/hazard/delete")
    public String deleteHazard(
            @RequestParam("id") Integer id
    )
    {
        int delete=hazardService.deleteHazard(id);
        JSONObject re=new JSONObject();

        // 删除成功
        if(delete>0) {
            re.put("status",1);
            re.put("message","删除成功");
        }
        // 删除失败
        else {
            re.put("status",0);
            re.put("message","删除失败");
        }
        return re.toJSONString();
    }

    /*
     更新危险源信息
      */
    // post
    @RequestMapping("/api/hazard/update")
    public String updateHazard(
            @RequestParam("id") Integer id,
            @RequestParam("riskId") Integer riskId,
            @RequestParam("name")String name,
            @RequestParam("type") String type,
            @RequestParam("l") Integer l,
            @RequestParam("e") Integer e,
            @RequestParam("c") Integer c,
            @RequestParam("degree") String degree,
            @RequestParam("measure") String measure,
            @RequestParam("level") String level,
            @RequestParam("deptId") Integer deptId,
            @RequestParam("userId") Integer userId)
    {
        Hazard hazard=new Hazard();
        hazard.setId(id);
        hazard.setRiskId(riskId);
        hazard.setName(name);
        hazard.setType(type);
        hazard.setL(l);
        hazard.setE(e);
        hazard.setC(c);
        hazard.setDegree(degree);
        hazard.setMeasure(measure);
        hazard.setLevel(level);
        hazard.setDeptId(deptId);
        hazard.setUserId(userId);

        int nums=hazardService.updateHazardById(hazard);

        JSONObject re=new JSONObject();

        if(nums>0)
        {
            re.put("status",1);
            JSONObject data=new JSONObject();

            data.put("id",hazard.getId());
            data.put("riskId",hazard.getRiskId());
            data.put("name",hazard.getName());
            data.put("type",hazard.getType());
            data.put("l",hazard.getL());
            data.put("e",hazard.getE());
            data.put("c",hazard.getC());
            data.put("name",hazard.getName());
            data.put("degree",hazard.getDegree());
            data.put("measure",hazard.getMeasure());
            data.put("level",hazard.getLevel());
            data.put("deptId",hazard.getDeptId());
            data.put("userId",hazard.getUserId());

            re.put("data",data);
        }
        else {

            re.put("status",0);
            re.put("message","更新失败");

        }
        return re.toJSONString();
    }

    @RequestMapping("/api/hazard/detail")
    public String getHazardById(@RequestParam("id") Integer id)
    {

        int nums=hazardService.getHazardById(id);

        Hazard hazard=new Hazard();
        JSONObject re=new JSONObject();

        if(nums>0)
        {
            re.put("status",1);
            JSONObject data=new JSONObject();

            data.put("id",hazard.getId());
            data.put("riskId",hazard.getRiskId());
            data.put("name",hazard.getName());
            data.put("type",hazard.getType());
            data.put("l",hazard.getL());
            data.put("e",hazard.getE());
            data.put("c",hazard.getC());
            data.put("name",hazard.getName());
            data.put("degree",hazard.getDegree());
            data.put("measure",hazard.getMeasure());
            data.put("level",hazard.getLevel());
            data.put("deptId",hazard.getDeptId());
            data.put("userId",hazard.getUserId());

            re.put("data",data);
        }
        else {

            re.put("status",0);
            re.put("message","更新失败");

        }
        return re.toJSONString();
    }

    //根据检查表id获得其详情
    //Post
    @RequestMapping("/api/hazard/getDetail")
    public String getDetailHazard(
            @RequestParam("hazardId") Integer hazardId)
    {
        Map<String,Object> map=hazardService.getDetailHazard(hazardId);
        //创建json对象
        JSONObject re=new JSONObject();


        if(map.size()>0)
        {
            re.put("status",1);

            JSONObject jsonObject=new JSONObject();
            jsonObject.put("id",map.get("id"));


            jsonObject.put("riskId",map.get("riskId"));
            jsonObject.put("name",map.get("name"));
            jsonObject.put("type",map.get("type"));
            jsonObject.put("l",map.get("l"));
            jsonObject.put("e",map.get("e"));
            jsonObject.put("c",map.get("c"));
            jsonObject.put("name",map.get("name"));
            jsonObject.put("degree",map.get("degree"));
            jsonObject.put("measure",map.get("measure"));
            jsonObject.put("level",map.get("level"));
            jsonObject.put("deptId",map.get("deptId"));
            jsonObject.put("userId",map.get("userId"));

            re.put("data",jsonObject);
        }
        else
        {
            re.put("status",0);
            re.put("message","获取失败");
        }

        return re.toJSONString();
    }


}
