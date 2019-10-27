package com.whut.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.whut.bean.Risk;
import com.whut.service.IRiskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author luodidi
 * @version 1.0
 * @date 2019/10/19 14:31
 */
@RestController
public class RiskController {
    @Autowired
    IRiskService riskService;


    // get
    // 获取风险列表
    @RequestMapping("/api/risk/getList")
    public String getListRisk(Integer pageNum, Integer pageSize) {

        PageInfo<Map<String, Object>> list = riskService.getListRisk(pageNum, pageSize);

        JSONObject jt = new JSONObject();
        jt.put("status", 1);
        JSONObject data = new JSONObject();
        data.put("total", list.getSize());
        data.put("pageNum", pageNum);
        data.put("pageSize", pageSize);
        JSONArray listJSON = new JSONArray();
        for (Map<String, Object> map : list.getList()) {
            JSONObject temp = new JSONObject();
            temp.put("id", map.get("id"));
            temp.put("name", map.get("name"));
            temp.put("place", map.get("place"));
            temp.put("level", map.get("level"));
            temp.put("telephone", map.get("telephone"));
            listJSON.add(temp);
        }
        data.put("list",listJSON);
        jt.put("data", data);
        return jt.toJSONString();
    }


    // post
    // 更新风险信息
    @RequestMapping("/api/risk/update")
    public String updateRisk(
            @RequestParam("id") Integer id,
            @RequestParam("name") String name,
            @RequestParam("place")String place,
            @RequestParam("level") String level,
            @RequestParam("telephone")String telephone)
    {
        Risk risks=new Risk();

        risks.setId(id);
        risks.setName(name);
        risks.setPlace(place);
        risks.setLevel(level);
        risks.setTelephone(telephone);

        int nums=riskService.updateRiskById(risks);

        JSONObject re=new JSONObject();

        if(nums>0)
        {
            re.put("status",1);
            JSONObject data=new JSONObject();

            data.put("id",risks.getId());
            data.put("name",risks.getName());
            data.put("place",risks.getPlace());
            data.put("level",risks.getLevel());
            data.put("telephone",risks.getTelephone());
            re.put("data",data);
        }
        else {

            re.put("status",0);
            re.put("message","添加失败");

        }
        return re.toJSONString();
    }

    // 插入风险信息
    //POST
    @RequestMapping("/api/risk/insert")
    public String insertRisk(
            @RequestParam("name") String name,
            @RequestParam("place")String place,
            @RequestParam("level") String level,
            @RequestParam("telephone")String telephone
    )
    {
        //创建Risk对象
        //id自增
        Risk risk=new Risk();

        risk.setName(name);
        risk.setPlace(place);
        risk.setLevel(level);
        risk.setTelephone(telephone);

        int insertId=riskService.insertRisk(risk);
        JSONObject jsonObject=new JSONObject();
        if(insertId>0) {
            jsonObject.put("status",1);
            JSONObject jsonObject1=new JSONObject();

            jsonObject1.put("id",insertId);
            jsonObject1.put("name",name);
            jsonObject1.put("place",place);
            jsonObject1.put("level",level);
            jsonObject1.put("telephone",telephone);

            jsonObject.put("data",jsonObject1);
        }
        else {

            jsonObject.put("status",0);
            jsonObject.put("message","添加失败");
        }

        //返回数据
        return jsonObject.toJSONString();
    }

    //根据id删除风险记录
    //delete
    @RequestMapping("/api/risk/delete")
    public String deleteRisk(
            @RequestParam("id") Integer id
    )
    {
        int delete=riskService.deleteRisk(id);
        JSONObject re=new JSONObject();

        //删除成功
        if(delete>0) {
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

    @RequestMapping("/api/risk/getDetail")
    public String getDetail(
            @RequestParam("id")Integer id
    )
    {
        Map<String,Object> map=riskService.getDetailRisk(id);
        JSONObject re=new JSONObject();
//        select id,name,place,level,telephone
        if(map==null||map.size()<=0)
        {
            re.put("status",0);
            re.put("message","获得详情失败");
        }
        else
        {
            re.put("status",1);
            JSONObject data=new JSONObject();
            data.put("id",map.get("id"));
            data.put("name",map.get("name"));
            data.put("place",map.get("place"));
            data.put("level",map.get("level"));
            data.put("telephone",map.get("phone"));
            re.put("data",data);
        }
        return re.toJSONString();
    }

    @RequestMapping("/api/risk/getAllList")
    public String getAllList()
    {
        List<Map<String,Object>> list=riskService.getAllList();
        JSONObject re=new JSONObject();
        if(list==null||list.size()<1)
        {
            re.put("status",0);
            re.put("message","获得风险点列表失败");
        }
        else
        {
            re.put("status",1);
            JSONArray data= new JSONArray();
            for(Map<String,Object>map:list)
            {
                JSONObject temp=new JSONObject();
                temp.put("id",map.get("id"));
                temp.put("name",map.get("name"));
                temp.put("place",map.get("place"));
                temp.put("level",map.get("level"));
                temp.put("telephone",map.get("telephone"));
                data.add(temp);
            }
            re.put("data",data);
        }
        return re.toJSONString();
    }
}
