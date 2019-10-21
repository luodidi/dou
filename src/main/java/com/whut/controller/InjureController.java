package com.whut.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.whut.bean.Accident;
import com.whut.bean.FirstLevelIndicator;
import com.whut.bean.Injure;
import com.whut.service.IInjureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author lixing
 * @version 1.0
 * @date 2019/10/20 22:47
 */
@RestController
public class InjureController {
    @Autowired
    IInjureService iInjureService;

    @RequestMapping("/api/injure/insert")
    public  String addInjure(@RequestParam("accidentId") Integer accidentId,
                             @RequestParam("userId") Integer userId,
                             @RequestParam("level") String level,
                             @RequestParam("injureNature") String injureNature,
                             @RequestParam("lossWorkDay") Integer lossWorkDay,
                             @RequestParam("degree") String degree,
                             @RequestParam("damage") String damage,
                             @RequestParam("position") String position,
                             @RequestParam("compensate") Double compensate)
    {

        //创建CheckTable对象
        //id自增
        Injure injure=new Injure();
        injure.setAccidentId(accidentId);
        injure.setUserId(userId);
        injure.setLevel(level);
        injure.setInjureNature(injureNature);
        injure.setLossWorkDay(lossWorkDay);
        injure.setDegree(degree);
        injure.setDamage(damage);
        injure.setPosition(position);
        injure.setCompensate(compensate);

        //获得新添加检查表在数据库中自增的id
        int s= iInjureService.addInjure(injure);


        JSONObject jsonObject = new JSONObject();
        if (s > 0) {

            jsonObject.put("status", 1);

            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("accidentId", accidentId);
            jsonObject1.put("userId", userId);
            jsonObject1.put("level", level);
            jsonObject1.put("injureNature", injureNature);
//            jsonObject1.put("date", new SimpleDateFormat("yyyy-MM-dd").format(date));
            jsonObject1.put("lossWorkDay", lossWorkDay);
            jsonObject1.put("degree", degree);
            jsonObject1.put("damage", damage);
            jsonObject1.put("position", position);
            jsonObject1.put("compensate", compensate);


            jsonObject.put("data", jsonObject1);
        } else {
            jsonObject.put("status", 0);
            jsonObject.put("message", "添加失败");
        }

        //返回检查表的数据
        return jsonObject.toJSONString();
    }


    @RequestMapping("/api/injure/update")
    public String updateInjure(
            @RequestParam("accidentId") Integer accidentId,
            @RequestParam("userId") Integer userId,
            @RequestParam("level") String level,
            @RequestParam("injureNature") String injureNature,
            @RequestParam("lossWorkDay") Integer lossWorkDay,
            @RequestParam("degree") String degree,
            @RequestParam("damage") String damage,
            @RequestParam("position") String position,
            @RequestParam("compensate") Double compensate
    )
    {

        Injure injure=new Injure();
//        Injure injure=new Injure();
        injure.setAccidentId(accidentId);
        injure.setUserId(userId);
        injure.setLevel(level);
        injure.setInjureNature(injureNature);
        injure.setLossWorkDay(lossWorkDay);
        injure.setDegree(degree);
        injure.setDamage(damage);
        injure.setPosition(position);
        injure.setCompensate(compensate);

        //调用service层修改数据
        int num=iInjureService.updateInjure(injure);
//                firstLevelIndicatorService.updateFirstLevelIndicator(firstLevelIndicator);
        //创建json对象作为返回值
        JSONObject re=new JSONObject();
        //修改成功
        if(num>0)
        {

            re.put("status",1);
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("accidentId", accidentId);
            jsonObject1.put("userId", userId);
            jsonObject1.put("level", level);
            jsonObject1.put("injureNature", injureNature);
            jsonObject1.put("lossWorkDay", lossWorkDay);
            jsonObject1.put("degree", degree);
            jsonObject1.put("damage", damage);
            jsonObject1.put("position", position);
            jsonObject1.put("compensate", compensate);

            re.put("data",jsonObject1);
        }
        //修改失败
        else
        {
            re.put("status",0);
            re.put("message","修改失败");
        }
        return re.toJSONString();
    }

    @RequestMapping("/api/injure/delete")
    public String deleteAccident(

            @RequestParam("accidentId") Integer accidentId,
            @RequestParam("userId") Integer userId
    )
    {


        int i=iInjureService.deleteInjuret(accidentId,userId);

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

    @RequestMapping("/api/injure/detail")
    public String getDetail(
            @RequestParam("accidentId") Integer accidentId,
            @RequestParam("userId") Integer userId
    )
    {
        Map<String,Object> map=iInjureService.getInjuredetail(accidentId,userId);
//                iAccidentService.getdetail(id);
//                firstLevelIndicatorService.getDetailFirstLevelIndicator(firstLevelIndicatorId);
//        select f.id id,c.name checkTableName,project,f.addDate addDate,f.isDelete isDelete,f.deleteDate deleteDate
//        from firstlevelindicators as f,checktables as c
//        where f.id=#{firstLevelIndicatorId} and f.checkTableId=c.id;
        JSONObject re=new JSONObject();
        if(map.size()>0)
        {
            //
//    #{accidentId}
//        #{userId},
//            #{level},
//            #{injureNature},
//            #{lossWorkDay}，
//            #{degree}，
//            #{damage}，
//            #{position}，
//            #{compensate}
            re.put("status",1);
            JSONObject jsonObject1=new JSONObject();
            jsonObject1.put("accidentId",map.get("accidentId"));
            jsonObject1.put("userId", map.get("userId"));
            jsonObject1.put("level", map.get("level"));
            jsonObject1.put("injureNature", map.get("injureNature"));
//            jsonObject1.put("date", new SimpleDateFormat("yyyy-MM-dd").format((Date) map.get("date")));
            jsonObject1.put("lossWorkDay", map.get("lossWorkDay"));
            jsonObject1.put("degree", map.get("degree"));
            jsonObject1.put("damage", map.get("damage"));
            jsonObject1.put("position", map.get("position"));
            jsonObject1.put("compensate", map.get("compensate"));
            re.put("date",jsonObject1);
        }
        else
        {
            re.put("status",0);
            re.put("message","获取失败");
        }
        return re.toJSONString();
    }

    //获取所有检查表的id和名字
    //Post
    @RequestMapping("/api/injure/getInjure")
    public String getAccident()
    {
        //调用service层获取所有记录
        List<Map<String,Object>> list=iInjureService.getInjure();
//                iAccidentService.getAccident();
//                checkTableService.getIdAndNameListCheckTable();
        //创建json对象作为返回值
        JSONObject re=new JSONObject();
        //创建JSONArray并且遍历list
        if(list.size()>0)
        {
            JSONArray array=new JSONArray();
            for(Map<String,Object> map:list)
            {
                JSONObject temp = new JSONObject();
                temp.put("accidentId", map.get("accidentId"));
                temp.put("userId",map.get("userId"));
                temp.put("level", map.get("level"));
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
    @RequestMapping("/api/injure/getList")
    public String getList(Integer pageNum,Integer pageSize)
    {
        //  |参数	   |是否必选  |类型      |说明
//	|pageNum   |Y       |int      |当前页号
//	|pageSize  |N		|int      |每页显示条数

        //获得数据
        PageInfo<Map<String,Object>> list= (PageInfo<Map<String, Object>>) iInjureService.getListInjuret(pageNum,pageSize);
//                iAccidentService.getListAccident(pageNum,pageSize);
//                checkTableService.getListCheckTable(pageNum,pageSize);
//        成功：

        JSONObject re=new JSONObject();
        re.put("status",1);
        JSONObject data=new JSONObject();
        data.put("total",list.getSize());
        data.put("pageNum",pageNum);
        data.put("pageSize",pageSize);
        JSONArray listJSON=new JSONArray();
        for(Map<String,Object> map:list.getList())
        {
            JSONObject jsonObject1=new JSONObject();
            re.put("status",1);
            jsonObject1.put("accidentId",map.get("accidentId"));
            jsonObject1.put("userId", map.get("userId"));
            jsonObject1.put("level", map.get("level"));
            jsonObject1.put("injureNature", map.get("injureNature"));
//            jsonObject1.put("date", new SimpleDateFormat("yyyy-MM-dd").format((Date) map.get("date")));
            jsonObject1.put("lossWorkDay", map.get("lossWorkDay"));
            jsonObject1.put("degree", map.get("degree"));
            jsonObject1.put("damage", map.get("damage"));
            jsonObject1.put("position", map.get("position"));
            jsonObject1.put("compensate", map.get("compensate"));
            listJSON.add(jsonObject1);

        }
        re.put("list",listJSON);

        return re.toJSONString();
    }

}


