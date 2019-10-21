package com.whut.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.whut.bean.Dangerousoperation;
import com.whut.service.IDangerousoperationService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Map;

/**
 * @author lixing
 * @version 1.0
 * @date 2019/10/16 15:50
 */

@RestController
public class DangerousoperationController {

    @Autowired
    private IDangerousoperationService iDangerousoperationService;
//
//    @RequestMapping("/api/dangerousoperation/getList")
//    public String findDangerousoperation(Model model, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "5") int size) {
//        List<Dangerousoperation> m=iDangerousoperationService.findDangerousoperation(page,size);
//       PageInfo<Dangerousoperation> mm=new PageInfo<>(m);
//       model.addAttribute(" ",mm); //添加数据集
//        return "dangerousoperation-list"; //返回数据集合
//    }

    //查询
    //GET
    @RequestMapping("/api/dangerousoperation/getList")
    public String getList(Integer pageNum, Integer pageSize) {

        //获得数据
        PageInfo<Map<String, Object>> list = iDangerousoperationService.findDangerousoperation(pageNum, pageSize);
//
        JSONObject re = new JSONObject();
        re.put("status", 1);
        JSONObject data = new JSONObject();
        data.put("total", list.getSize());
        data.put("pageNum", pageNum);
        data.put("pageSize", pageSize);
        JSONArray listJSON = new JSONArray();
        for (Map<String, Object> map : list.getList()) {
            JSONObject temp = new JSONObject();
            temp.put("id",map.get("id"));
            temp.put("deptId", map.get("deptId"));
            temp.put("name", map.get("name"));
            temp.put("place", map.get("place"));
            temp.put("curator", map.get("curator"));
            listJSON.add(temp);
        }
        data.put("list",listJSON);
        re.put("data",data);

        return re.toJSONString();
    }

    //详细
  /*  @RequestMapping("/api/dangerousoperation/detail")
    public String detaildangerousoperation(int dangerousoperationId) {

        //获得数据
        // PageInfo<Map<String,Object>> list=iDangerousoperationService.find(dangerousoperationId);
        int s=iDangerousoperationService.find(dangerousoperationId);
        JSONObject re = new JSONObject();
        re.put("status", 1);
      if(s>0)
      {
            JSONObject object = new JSONObject();
            object.put("id", dangerousoperationId.getId());
            object.put("deptId", dangerousoperationId.getDeptId());
            object.put("applyDate", dangerousoperationId.getApplyDate());
            object.put("status", dangerousoperationId.getStatus());
            object.put("keepFile", dangerousoperationId.getKeepFile());
            object.put("name", dangerousoperationId.getName());
            object.put("startDate", dangerousoperationId.getStartDate());
            object.put("endDate", dangerousoperationId.getEndDate());
            object.put("place", dangerousoperationId.getPlace());
            object.put("applyPerson", dangerousoperationId.getApplyPerson());
            object.put("curator", dangerousoperationId.getCurator());
            object.put("content", dangerousoperationId.getContent());
            object.put("number", dangerousoperationId.getNumber());
            object.put("majorFactor", dangerousoperationId.getMajorFactor());
            object.put("safeMeasure", dangerousoperationId.getSafeMeasure());
            object.put("keepYear", dangerousoperationId.getKeepYear());
            re.put("data", object);
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
*/
    @RequestMapping("/api/dangerousoperation/detail")
    public String getDetailFirstLevelIndicator(
            @RequestParam("dangerousoperationId") Integer dangerousoperationId)
    {
        Map<String,Object> map=iDangerousoperationService.find(dangerousoperationId);
        JSONObject re=new JSONObject();
        if(map.size()>0)
        {
            re.put("status",1);
            JSONObject object = new JSONObject();
            object.put("id", map.get("id"));
            object.put("deptId", map.get("deptId"));
            object.put("applyDate", new SimpleDateFormat("yyyy-MM-dd").format((Date) map.get("applyDate")));
            object.put("status", map.get("status"));
            object.put("keepFile", map.get("keepFile"));
            object.put("name", map.get("name"));
            object.put("startDate", new SimpleDateFormat("yyyy-MM-dd").format((Date) map.get("startDate")));
            object.put("endDate", new SimpleDateFormat("yyyy-MM-dd").format((Date) map.get("endDate")));
            object.put("place", map.get("place"));
            object.put("applyPerson", map.get("applyPerson"));
            object.put("curator", map.get("curator"));
            object.put("content", map.get("content"));
            object.put("number", map.get("number"));
            object.put("majorFactor", map.get("majorFactor"));
            object.put("safeMeasure", map.get("safeMeasure"));
            object.put("keepYear", map.get("keepYear"));
            re.put("date",object);
        }
        else
        {
            re.put("status",0);
            re.put("message","获取失败");
        }
        return re.toJSONString();
    }


    @RequestMapping("/api/dangerousoperation/insert")
    public String addangerousoperation(@RequestParam("deptId") Integer deptId,
                                       @RequestParam("applyDate") String applyDate,
                                       @RequestParam("status") String status,
                                       @RequestParam("keepFile") Boolean keepFile,
                                       @RequestParam("name") String name,
                                       @RequestParam("startDate") String startDate,
                                       @RequestParam("endDate") String endDate,
                                       @RequestParam("place") String place,
                                       @RequestParam("applyPerson") String applyPerson,
                                       @RequestParam("curator") String curator,
                                       @RequestParam("content") String content,
                                       @RequestParam("number") Integer number,
                                       @RequestParam("majorFactor") String majorFactor,
                                       @RequestParam("safeMeasure") String safeMeasure,
                                       @RequestParam("keepYear") Integer keepYear) throws ParseException {
        //创建CheckTable对象
        //id自增
        Dangerousoperation dangerousoperation = new Dangerousoperation();
        dangerousoperation.setDeptId(deptId);
        dangerousoperation.setStatus(status);
        dangerousoperation.setKeepFile(false);
        dangerousoperation.setName(name);
        dangerousoperation.setPlace(place);
        dangerousoperation.setApplyPerson(applyPerson);
        dangerousoperation.setCurator(curator);
        dangerousoperation.setContent(content);
        dangerousoperation.setNumber(number);
        dangerousoperation.setMajorFactor(majorFactor);
        dangerousoperation.setSafeMeasure(safeMeasure);
        dangerousoperation.setKeepYear(keepYear);

        Date date = new Date();
        dangerousoperation.setApplyDate(date);

        dangerousoperation.setStartDate(new SimpleDateFormat("yyyy-MM-dd").parse(startDate));
        dangerousoperation.setEndDate(new SimpleDateFormat("yyyy-MM-dd").parse(endDate));


        //获得新添加检查表在数据库中自增的id
        int id = iDangerousoperationService.addangerousoperation(dangerousoperation);
        JSONObject jsonObject = new JSONObject();
        if (id > 0) {
            jsonObject.put("status", 1);

            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("id", id);
            jsonObject1.put("deptId", deptId);
            jsonObject1.put("applyDate", new SimpleDateFormat("yyyy-MM-dd").format(date));
            jsonObject1.put("status", status);
            jsonObject1.put("keepFile", false);
            jsonObject1.put("name", name);
            jsonObject1.put("startDate", startDate);
            jsonObject1.put("endDate", endDate);
            jsonObject1.put("place", place);
            jsonObject1.put("applyPerson", applyPerson);
            jsonObject1.put("curator", curator);
            jsonObject1.put("content", content);
            jsonObject1.put("number", number);
            jsonObject1.put("majorFactor", majorFactor);
            jsonObject1.put("safeMeasure", safeMeasure);
            jsonObject1.put("keepYear", keepYear);
            jsonObject.put("data", jsonObject1);
        } else {
//            失败：
//            {
//                "status": 0,
//                    "message": "添加失败"
//            }
            jsonObject.put("status", 0);
            jsonObject.put("message", "添加失败");
        }

        //返回检查表的数据
        return jsonObject.toJSONString();
    }


    @RequestMapping("/api/dangerousoperation/update")
    public String updateangerousoperation(@RequestParam("id") Integer id,
                                          @RequestParam("deptId") Integer deptId,
                                          @RequestParam("applyDate") String applyDate,
                                          @RequestParam("status") String status,
                                          @RequestParam("keepFile") Boolean keepFile,
                                          @RequestParam("name") String name,
                                          @RequestParam("startDate") String startDate,
                                          @RequestParam("endDate") String endDate,
                                          @RequestParam("place") String place,
                                          @RequestParam("applyPerson") String applyPerson,
                                          @RequestParam("curator") String curator,
                                          @RequestParam("content") String content,
                                          @RequestParam("number") Integer number,
                                          @RequestParam("majorFactor") String majorFactor,
                                          @RequestParam("safeMeasure") String safeMeasure,
                                          @RequestParam("keepYear") Integer keepYear) {
        Dangerousoperation dangerousoperation = new Dangerousoperation();
        dangerousoperation.setId(id);
        dangerousoperation.setDeptId(deptId);
        dangerousoperation.setStatus(status);
        dangerousoperation.setKeepFile(keepFile);
        dangerousoperation.setName(name);
        dangerousoperation.setPlace(place);
        dangerousoperation.setApplyPerson(applyPerson);
        dangerousoperation.setCurator(curator);
        dangerousoperation.setContent(content);
        dangerousoperation.setNumber(number);
        dangerousoperation.setMajorFactor(majorFactor);
        dangerousoperation.setSafeMeasure(safeMeasure);
        dangerousoperation.setKeepYear(keepYear);
        try {
            dangerousoperation.setApplyDate(new SimpleDateFormat("yyyy-MM-dd").parse(applyDate));
            dangerousoperation.setEndDate(new SimpleDateFormat("yyyy-MM-dd").parse(endDate));
            dangerousoperation.setStartDate(new SimpleDateFormat("yyyy-MM-dd").parse(startDate));

        } catch (ParseException e) {
            e.printStackTrace();
        }


        int s = iDangerousoperationService.updateangerousoperation(dangerousoperation);
        JSONObject re = new JSONObject();
        if (s > 0) {
            re.put("status", 1);
//            JSONObject object=new JSONObject();
//            data.put("id",dangerousoperation.getId() );
//            data.put("deptId",dangerousoperation.getDeptId());
//            data.put()
            JSONObject object = new JSONObject();
            object.put("id", dangerousoperation.getId());
            object.put("deptId", dangerousoperation.getDeptId());
            object.put("applyDate", dangerousoperation.getApplyDate());
            object.put("status", dangerousoperation.getStatus());
            object.put("keepFile", dangerousoperation.getKeepFile());
            object.put("name", dangerousoperation.getName());
            object.put("startDate", dangerousoperation.getStartDate());
            object.put("endDate", dangerousoperation.getEndDate());
            object.put("place", dangerousoperation.getPlace());
            object.put("applyPerson", dangerousoperation.getApplyPerson());
            object.put("curator", dangerousoperation.getCurator());
            object.put("content", dangerousoperation.getContent());
            object.put("number", dangerousoperation.getNumber());
            object.put("majorFactor", dangerousoperation.getMajorFactor());
            object.put("safeMeasure", dangerousoperation.getSafeMeasure());
            object.put("keepYear", dangerousoperation.getKeepYear());
            re.put("data", object);
        } else {
            re.put("status", 0);
            re.put("message", "添加失败");

        }
        return re.toJSONString();
    }

    @RequestMapping("/api/dangerousoperation/delete")
    public String deleteangerousoperation(@RequestParam("dangerousoperationId") int dangerousoperationId) {

        int i = iDangerousoperationService.deletedangerousoperation(dangerousoperationId);
        JSONObject re = new JSONObject();
        if (i > 0) {
            re.put("status", 1);
            re.put("message", "删除成功");

        } else {
            re.put("status", 0);
            re.put("message", "删除失败");
        }

        return re.toJSONString();
    }

    @RequestMapping("/api/dangerousoperation/submit")
    public String submitdangerousoperation(@RequestParam("dangerousoperationId") Integer dangerousoperationId) {
        int s = iDangerousoperationService.submitdangerousoperation(dangerousoperationId);
        JSONObject re = new JSONObject();
        if (s > 0) {
            re.put("status", 1);
            re.put("message", "提交成功");

        } else {
            re.put("status", 0);
            re.put("message", "提交失败");
        }
        return re.toJSONString();

    }

    @RequestMapping("/api/dangerousoperation/withdraw")
    public String withdrawdangerousoperation(@RequestParam("dangerousoperationId") Integer dangerousoperationId) {
        int s = iDangerousoperationService.withdrawdangerousoperation(dangerousoperationId);
        JSONObject re = new JSONObject();
        if (s > 0) {
            re.put("status", 1);
            re.put("message", "撤回成功");

        } else {
            re.put("status", 0);
            re.put("message", "撤回失败");
        }
        return re.toJSONString();

    }

    @RequestMapping("/api/dangerousoperation/file")
    public String filedangerousoperation(@RequestParam("dangerousoperationId") Integer dangerousoperationId) {
        int s = iDangerousoperationService.filedangerousoperation(dangerousoperationId);
        JSONObject re = new JSONObject();
        if (s > 0) {
            re.put("status", 1);
            re.put("message", "归档成功");

        } else {
            re.put("status", 0);
            re.put("message", "归档失败");
        }
        return re.toJSONString();

    }
}


