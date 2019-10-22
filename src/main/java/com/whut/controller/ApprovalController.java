package com.whut.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.whut.bean.Approval;
import com.whut.service.IApprovalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * @author lixing
 * @version 1.0
 * @date 2019/10/20 15:30
 */
@RestController
public class ApprovalController {
    @Autowired
    IApprovalService iApprovalService;




    @RequestMapping("/api/preApprove/reset")
    public String resetApproval(@RequestParam("dangerousOperationId") Integer dangerousOperationId )
    {
        int s=  iApprovalService.resetApproval(dangerousOperationId);
        JSONObject re=new JSONObject();

        //删除成功
        if(s>0)
        {
            re.put("status",1);
            re.put("message","退回成功");
        }
        //删除失败
        else
        {
            re.put("status",0);
            re.put("message","退回失败");
        }
        return re.toJSONString();
    }



    @RequestMapping("/api/preApprove/pass")
    public String passApproval(@RequestParam("dangerousOperationId") Integer dangerousOperationId)
    {

        int s=  iApprovalService.passApproval(dangerousOperationId);
        JSONObject re=new JSONObject();

        //删除成功
        if(s>0)
        {
            re.put("status",1);
            re.put("message","审核成功");
        }
        //删除失败
        else
        {
            re.put("status",0);
            re.put("message","审核失败");
        }
        return re.toJSONString();
    }

    @RequestMapping("/api/preApprove/getList")
    public String getListApprove(Integer pageNum,Integer pageSize)
    {

        //获得数据
        PageInfo<Map<String,Object>> list=(PageInfo<Map<String, Object>>) iApprovalService.getListApproval(pageNum,pageSize);
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
            jsonObject1.put("dangerousOperationId",map.get("dangerousOperationId"));
            jsonObject1.put("result", map.get("result"));
            jsonObject1.put("record", map.get("record"));
            jsonObject1.put("approverId", map.get("approverId"));
            listJSON.add(jsonObject1);
        }
        re.put("list",listJSON);

        return re.toJSONString();
    }

    }

