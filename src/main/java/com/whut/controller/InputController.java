package com.whut.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.whut.bean.HiddenDanger;
import com.whut.bean.Input;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;

/*
  Created by IntelliJ IDEA.
  User: ccc
  Date: 2019/10/17
  Time: 10:30
  To change this template use File | Settings | File Templates.
*/
@RestController
public class InputController {

    @RequestMapping("/api/input/insert")
    public String insertInput(
            @RequestParam("data") String data
    )
    {
        try {
            Input input=new Input();
            //在data字符串中对input赋值
            JSONObject jsonData= JSON.parseObject(data);
            input.setId(jsonData.getString("inputId"));
            input.setCheckTableId(jsonData.getInteger("checktableId"));
            input.setCheckDate(new SimpleDateFormat("yyyy-MM-dd").parse(jsonData.getString("checkDate")));
            input.setUserName(jsonData.getString("userName"));
            input.setDeptId(jsonData.getInteger("deptId"));
            input.setDepeedId(jsonData.getInteger("deptedId"));
            input.setQualified(jsonData.getBoolean("isqualified"));
            input.setDesc(jsonData.getString("desc"));
            input.setType(jsonData.getString("type"));
            input.setOtherPerson(jsonData.getString("otherPerson"));
            //判断检查表是否合格
            Boolean isQ=jsonData.getBoolean("isHd");
            if(isQ==true)
            {
                //创建隐患类的对象
                HiddenDanger hiddenDanger=new HiddenDanger();
                //对hiddenDanger对象赋值
                JSONObject jsonHd=JSON.parseObject(jsonData.getString("Hd"));
                hiddenDanger.setType(jsonHd.getString("type"));
                hiddenDanger.sethPhoto(jsonHd.getString("hPhoto"));
                hiddenDanger.setStatus(jsonHd.getString("status"));
                hiddenDanger.setStartDate(new SimpleDateFormat("yyyy-MM-dd").parse(jsonHd.getString("startDate")));
                hiddenDanger.setEndDate(new SimpleDateFormat("yyyy-MM-dd").parse(jsonHd.getString("endDate")));
                hiddenDanger.setFinishDate(new SimpleDateFormat("yyyy-MM-dd").parse(jsonHd.getString("finishDate")));
                hiddenDanger.setrPhoto(jsonHd.getString("rPhoto"));
                hiddenDanger.setDesc(jsonHd.getString("desc"));
                hiddenDanger.setFile(jsonHd.getBoolean("isFile"));
            }
            else
            {

            }
        }catch (Exception e)
        {

        }
        finally {

        }
        //创建input对象

        return "";
    }
}
