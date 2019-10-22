package com.whut.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.whut.bean.Accident;
import com.whut.bean.CheckTable;
import com.whut.bean.FirstLevelIndicator;
import com.whut.service.IAccidentService;
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
 * @date 2019/10/20 16:31
 */
@RestController
public class AccidentController {

    @Autowired
    IAccidentService iAccidentService;

    @RequestMapping("/api/accident/insert")
    public String addAccident(
            //@RequestParam("id") Integer id,
            @RequestParam("accidentType") String accidentType,
            @RequestParam("name") String name,
            @RequestParam("place") String place,
            @RequestParam("date") String date,
            @RequestParam("status") String status,
            @RequestParam("nature") String nature,
            @RequestParam("level") String level,
            @RequestParam("reason") String reason,
            @RequestParam("directLoss") Double directLoss,
            @RequestParam("indirectLoss") Double indirectLoss,
            @RequestParam("lossWorkDay") Integer lossWorkDay,
            @RequestParam("outageTime") Integer outageTime,
            @RequestParam("survey") String survey,
            @RequestParam("causeAnalysis") String causeAnalysis,
            @RequestParam("injure") String injure,
            @RequestParam("lossDegree") String lossDegree,
            @RequestParam("resolution") String resolution,
            @RequestParam("lesson") String lesson,
            @RequestParam("measure") String measure,
            @RequestParam("remark") String remark
    ) throws ParseException {

        //创建accident对象
        //id自增
        Accident accident = new Accident();
        //accident.setId(id);
        accident.setAccidentType(accidentType);
        accident.setName(name);
        accident.setPlace(place);
        accident.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(date));
        accident.setStatus(status);
        accident.setNature(nature);
        accident.setLevel(level);
        accident.setReason(reason);
        accident.setDirectLoss(directLoss);
        accident.setIndirectLoss(indirectLoss);
        accident.setOutageTime(outageTime);
        accident.setSurvey(survey);
        accident.setCauseAnalysis(causeAnalysis);
        accident.setInjure(injure);
        accident.setLossDegree(lossDegree);
        accident.setLesson(lesson);
        accident.setMeasure(measure);
        accident.setRemark(remark);
        accident.setResolution(resolution);
        accident.setLossWorkDay(lossWorkDay);


        //获得新添加检查表在数据库中自增的id
        int insertId = iAccidentService.addAccident(accident);

        JSONObject jsonObject = new JSONObject();
        if (insertId > 0) {

            jsonObject.put("status", 1);

            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("id", insertId);
            jsonObject1.put("accidentType", accidentType);
            jsonObject1.put("name", name);
            jsonObject1.put("place", place);
            jsonObject1.put("date", new SimpleDateFormat("yyyy-MM-dd").parse(date));
            jsonObject1.put("status", status);
            jsonObject1.put("nature", nature);
            jsonObject1.put("level", level);
            jsonObject1.put("reason", reason);
            jsonObject1.put("directLoss", directLoss);
            jsonObject1.put("indirectLoss", indirectLoss);
            jsonObject1.put("lossWorkDay", lossWorkDay);
            jsonObject1.put("outageTime", outageTime);
            jsonObject1.put("survey", survey);
            jsonObject1.put("causeAnalysis", causeAnalysis);
            jsonObject1.put("injure", injure);
            jsonObject1.put("lossDegree", lossDegree);
            jsonObject1.put("resolution", resolution);
            jsonObject1.put("lesson", lesson);
            jsonObject1.put("measure", measure);
            jsonObject1.put("remark", remark);

            jsonObject.put("data", jsonObject1);
        } else {
            jsonObject.put("status", 0);
            jsonObject.put("message", "添加失败");
        }

        //返回检查表的数据
        return jsonObject.toJSONString();
    }


    @RequestMapping("/api/accident/update")
    public String updateAccident(
            @RequestParam("id") Integer id,
            @RequestParam("accidentType") String accidentType,
            @RequestParam("name") String name,
            @RequestParam("place") String place,
            @RequestParam("date") String date,
            @RequestParam("status") String status,
            @RequestParam("nature") String nature,
            @RequestParam("level") String level,
            @RequestParam("reason") String reason,
            @RequestParam("directLoss") Double directLoss,
            @RequestParam("indirectLoss") Double indirectLoss,
            @RequestParam("lossWorkDay") Integer lossWorkDay,
            @RequestParam("outageTime") Integer outageTime,
            @RequestParam("survey") String survey,
            @RequestParam("causeAnalysis") String causeAnalysis,
            @RequestParam("injure") String injure,
            @RequestParam("lossDegree") String lossDegree,
            @RequestParam("resolution") String resolution,
            @RequestParam("lesson") String lesson,
            @RequestParam("measure") String measure,
            @RequestParam("remark") String remark

    ) throws ParseException {


        Accident accident = new Accident();
        accident.setId(id);
        accident.setAccidentType(accidentType);
        accident.setName(name);
        accident.setPlace(place);
            accident.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(date));
            accident.setStatus(status);
            accident.setNature(nature);
            accident.setLevel(level);
            accident.setReason(reason);
            accident.setDirectLoss(directLoss);
            accident.setIndirectLoss(indirectLoss);
            accident.setOutageTime(outageTime);
            accident.setSurvey(survey);
            accident.setCauseAnalysis(causeAnalysis);
            accident.setInjure(injure);
            accident.setLossDegree(lossDegree);
            accident.setLesson(lesson);
            accident.setMeasure(measure);
            accident.setRemark(remark);
            accident.setResolution(resolution);
            accident.setLossWorkDay(lossWorkDay);
            int s = iAccidentService.updateAccident(accident);
            JSONObject jsonObject = new JSONObject();
            if (s > 0) {

                jsonObject.put("status", 1);

                JSONObject jsonObject1 = new JSONObject();
                jsonObject1.put("id", id);
                jsonObject1.put("accidentType", accidentType);
                jsonObject1.put("name", name);
                jsonObject1.put("place", place);
                jsonObject1.put("date", new SimpleDateFormat("yyyy-MM-dd").parse(date));
                jsonObject1.put("status", status);
                jsonObject1.put("nature", nature);
                jsonObject1.put("level", level);
                jsonObject1.put("reason", reason);
                jsonObject1.put("directLoss", directLoss);
                jsonObject1.put("indirectLoss", indirectLoss);
//            jsonObject1.put("indirectLoss",null);
                jsonObject1.put("lossWorkDay", lossWorkDay);
                jsonObject1.put("outageTime", outageTime);
                jsonObject1.put("survey", survey);
                jsonObject1.put("causeAnalysis", causeAnalysis);
                jsonObject1.put("injure", injure);
                jsonObject1.put("lossDegree", lossDegree);
                jsonObject1.put("resolution", resolution);
                jsonObject1.put("lesson", lesson);
                jsonObject1.put("measure", measure);
                jsonObject1.put("remark", remark);

                jsonObject.put("data", jsonObject1);
            } else {
                jsonObject.put("status", 0);
                jsonObject.put("message", "添加失败");
            }

            //返回检查表的数据
            return jsonObject.toJSONString();
        }


    @RequestMapping("/api/accident/delete")
    public String deleteAccident(
        @RequestParam("id") Integer id)
    {


        int i=iAccidentService.deleteAccident(id);
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

    @RequestMapping("/api/accident/detail")
    public String getDetailFirstLevelIndicator(
            @RequestParam("id") Integer id)
    {
        Map<String,Object> map=iAccidentService.getdetail(id);
//                firstLevelIndicatorService.getDetailFirstLevelIndicator(firstLevelIndicatorId);
//        select f.id id,c.name checkTableName,project,f.addDate addDate,f.isDelete isDelete,f.deleteDate deleteDate
//        from firstlevelindicators as f,checktables as c
//        where f.id=#{firstLevelIndicatorId} and f.checkTableId=c.id;
        JSONObject re=new JSONObject();
        if(map!=null)
        {
            re.put("status",1);
            JSONObject jsonObject1=new JSONObject();
            jsonObject1.put("id",map.get("id"));
            jsonObject1.put("accidentType", map.get("accidentType"));
            jsonObject1.put("name", map.get("name"));
            jsonObject1.put("place", map.get("place"));
            jsonObject1.put("date", new SimpleDateFormat("yyyy-MM-dd").format((Date) map.get("date")));
            jsonObject1.put("status", map.get("status"));
            jsonObject1.put("nature", map.get("nature"));
            jsonObject1.put("level", map.get("level"));
            jsonObject1.put("reason", map.get("reason"));
            jsonObject1.put("directLoss", map.get("directLoss"));
            jsonObject1.put("indirectLoss", map.get("indirectLoss"));
//            jsonObject1.put("indirectLoss",null);
            jsonObject1.put("lossWorkDay", map.get("lossWorkDay"));
            jsonObject1.put("outageTime", map.get("outageTime"));
            jsonObject1.put("survey", map.get("survey"));
            jsonObject1.put("causeAnalysis", map.get("causeAnalysis"));
            jsonObject1.put("injure", map.get("injure"));
            jsonObject1.put("lossDegree", map.get("lossDegree"));
            jsonObject1.put("resolution", map.get("resolution"));
            jsonObject1.put("lesson", map.get("lesson"));
            jsonObject1.put("measure", map.get("measure"));
            jsonObject1.put("remark", map.get("remark"));
            re.put("date",jsonObject1);
        }
        else
        {
            re.put("status",0);
            re.put("message","获取失败");
        }
        return re.toJSONString();
    }


    @RequestMapping("/api/accident/getAccident")
    public String getAccident()
    {
        //调用service层获取所有记录
        List<Map<String,Object>> list=iAccidentService.getAccident();
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
                temp.put("id", map.get("id"));
                temp.put("accidentType",map.get("accidentType"));
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
    @RequestMapping("/api/accident/getList")
    public String getList(Integer pageNum,Integer pageSize)
    {
        //  |参数	   |是否必选  |类型      |说明
//	|pageNum   |Y       |int      |当前页号
//	|pageSize  |N		|int      |每页显示条数

        //获得数据
        PageInfo<Map<String,Object>> list=iAccidentService.getListAccident(pageNum,pageSize);
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
            jsonObject1.put("id",map.get("id"));
            jsonObject1.put("accidentType", map.get("accidentType"));
            jsonObject1.put("name", map.get("name"));
            jsonObject1.put("place", map.get("place"));
            jsonObject1.put("date", new SimpleDateFormat("yyyy-MM-dd").format((Date) map.get("date")));
            jsonObject1.put("status", map.get("status"));
            jsonObject1.put("nature", map.get("nature"));
            jsonObject1.put("level", map.get("level"));
            jsonObject1.put("reason", map.get("reason"));
            jsonObject1.put("directLoss", map.get("directLoss"));
            jsonObject1.put("indirectLoss", map.get("indirectLoss"));
//            jsonObject1.put("indirectLoss",null);
            jsonObject1.put("lossWorkDay", map.get("lossWorkDay"));
            jsonObject1.put("outageTime", map.get("outageTime"));
            jsonObject1.put("survey", map.get("survey"));
            jsonObject1.put("causeAnalysis", map.get("causeAnalysis"));
            jsonObject1.put("injure", map.get("injure"));
            jsonObject1.put("lossDegree", map.get("lossDegree"));
            jsonObject1.put("resolution", map.get("resolution"));
            jsonObject1.put("lesson", map.get("lesson"));
            jsonObject1.put("measure", map.get("measure"));
            jsonObject1.put("remark", map.get("remark"));
            listJSON.add(jsonObject1);
        }
        re.put("list",listJSON);

        return re.toJSONString();
    }

    @RequestMapping("/api/accident/sumNumberId")
    public String sumNumberId()
    {
        int i=iAccidentService.sumNumberId();
        JSONObject re=new JSONObject();
        re.put("status",1);
        re.put("totalAccident",i);
        return re.toJSONString();
    }

    @RequestMapping("/api/accident/sumDirectLoss")
    public String sumDirectLoss()
    {
        List<Map<String,Object>> list= iAccidentService.sumDirectLoss();
        JSONObject re=new JSONObject();

        JSONArray array=new JSONArray();
        for(Map<String,Object> map:list)
        {
            JSONObject temp=new JSONObject();
            temp.put("accidentType",map.get("accidentType"));
            temp.put("sum(directLoss)",map.get("sum(directLoss)"));
            array.add(temp);
        }
        re.put("list",array);
        return re.toJSONString();
    }

    @RequestMapping("/api/accident/sumIndirectLoss")
    public String sumIndirectLoss()
    {
        List<Map<String,Object>> list= iAccidentService.sumIndirectLoss();
        JSONObject re=new JSONObject();

        JSONArray array=new JSONArray();
        for(Map<String,Object> map:list)
        {
            JSONObject temp=new JSONObject();
            temp.put("accidentType",map.get("accidentType"));
            temp.put("sum(indirectLoss)",map.get("sum(indirectLoss)"));
            array.add(temp);
        }
        re.put("list",array);
        return re.toJSONString();
    }

    @RequestMapping("/api/accident/sumLevel")
    public String sumLevel()
    {
        List<Map<String,Object>> list= iAccidentService.sumLevel();
        JSONObject re=new JSONObject();

        JSONArray array=new JSONArray();
        for(Map<String,Object> map:list)
        {
            JSONObject temp=new JSONObject();
            temp.put("level",map.get("level"));
            temp.put("count(level)",map.get("count(level)"));
            array.add(temp);
        }
        re.put("list",array);
        return re.toJSONString();
    }

}





