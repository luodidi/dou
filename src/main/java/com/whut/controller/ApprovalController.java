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

    }

