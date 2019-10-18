package com.whut.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.whut.bean.CheckTableDetail;
import com.whut.bean.HiddenDanger;
import com.whut.bean.Input;
import com.whut.service.impl.InputServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.UUID;


/*
  Created by IntelliJ IDEA.
  User: ccc
  Date: 2019/10/17
  Time: 10:30
  To change this template use File | Settings | File Templates.
*/
@RestController
public class InputController {

    @Autowired
    InputServiceImpl inputService;

    //创建新的录用表并且添加隐患和具体二级指标
    //Post
    @Transactional
    @RequestMapping("/api/input/insert")
    public String insertInput (
            @RequestParam("data") String data
    )throws Exception
    {
        JSONObject re=new JSONObject();
        try {
            Input input=new Input();
            //在data字符串中对input赋值
            JSONObject jsonData= JSON.parseObject(data);
            input.setId(jsonData.getString("inputId"));
            input.setCheckTableId(jsonData.getInteger("checktableId"));
            input.setCheckDate(new SimpleDateFormat("yyyy-MM-dd").parse(jsonData.getString("checkDate")));
            input.setUserName(jsonData.getString("userName"));
            input.setDeptId(jsonData.getInteger("deptId"));
            input.setDeptedId(jsonData.getInteger("deptedId"));
            input.setQualified(jsonData.getBoolean("isqualified"));
            input.setDesc(jsonData.getString("desc"));
            input.setType(jsonData.getString("type"));
            input.setOtherPerson(jsonData.getString("otherPerson"));

            //向数据库中添加录入表信息
            inputService.insertInput(input);

            //判断检查表是否合格
            Boolean isQ=jsonData.getBoolean("isHd");

            //存在隐患
            if(isQ==true)
            {

                //创建隐患类的对象
                HiddenDanger hiddenDanger=new HiddenDanger();
                
                //对hiddenDanger对象赋值
                JSONObject jsonHd=JSON.parseObject(jsonData.getString("Hd"));
                hiddenDanger.setInputId(jsonData.getString("inputId"));
                hiddenDanger.setType(jsonHd.getString("type"));
                hiddenDanger.sethPhoto(jsonHd.getString("hPhoto"));
                hiddenDanger.setStatus(jsonHd.getString("status"));
                hiddenDanger.setStartDate(new SimpleDateFormat("yyyy-MM-dd").parse(jsonHd.getString("startDate")));
                hiddenDanger.setEndDate(new SimpleDateFormat("yyyy-MM-dd").parse(jsonHd.getString("endDate")));
                hiddenDanger.setFinishDate(new SimpleDateFormat("yyyy-MM-dd").parse(jsonHd.getString("finishDate")));
                hiddenDanger.setrPhoto(jsonHd.getString("rPhoto"));
                hiddenDanger.setDesc(jsonHd.getString("desc"));
                hiddenDanger.setFile(jsonHd.getBoolean("isFile"));
                hiddenDanger.setDispatchUserId(jsonHd.getInteger("dispatchUserId"));
                hiddenDanger.setDispatchDeptId(jsonHd.getInteger("dispatchDeptId"));
                hiddenDanger.setDeptId(jsonHd.getInteger("deptId"));
                hiddenDanger.setContent(jsonHd.getString("content"));

                //向数据库中添加隐患数据
                inputService.insertHiddenDanger(hiddenDanger);
            }
            //获取所有二级指标信息
            JSONArray jsonArray=jsonData.getJSONArray("list");
            //遍历
            for(Object o:jsonArray)
            {
                JSONObject jsonObject=(JSONObject) o;
                //创建CheckTableDetail类的对象
                CheckTableDetail checkTableDetail=new CheckTableDetail();
                checkTableDetail.setInputId(jsonData.getString("inputId"));
                checkTableDetail.setSecondLevelIndicatorId(jsonObject.getInteger("secondLevelId"));
                checkTableDetail.setQualified(jsonObject.getBoolean("isqualified"));
                checkTableDetail.setDesc(jsonObject.getString("desc"));

                //添加具体的二级指标内容
                inputService.insertCheckTableDetail(checkTableDetail);

            }
        }catch (Exception e)
        {
            re.put("status",0);
            re.put("message","添加失败");
            throw e;
        }
        re.put("status",1);
        re.put("message","添加成功");

        return re.toJSONString();
    }

    //用于保存图片
    //Post
    @RequestMapping("/api/input/savePicture")
    public String savePicture(@RequestParam("avatar") MultipartFile multipartFile)throws IOException
    {
        String path="F:\\douPictures";
        File file=new File(path);
        if(!file.exists())
        {
            file.mkdirs();
        }
        FileInputStream fileInputStream=(FileInputStream)multipartFile.getInputStream();
        String rPhoto=UUID.randomUUID().toString().replace("-", "")+".jpg";
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(path + File.separator + rPhoto));
        byte[] bs = new byte[1024];
        int len;
        while ((len = fileInputStream.read(bs)) != -1) {
            bos.write(bs, 0, len);
        }
        bos.flush();
        bos.close();
        JSONObject re=new JSONObject();
        re.put("status",1);
        re.put("path",path + File.separator + rPhoto);
        return re.toJSONString();
    }
}
