package com.whut.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
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
import java.util.Date;
import java.util.Map;
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

    //由隐患id获得其详情
    //Post
    @RequestMapping("/api/input/getDetailHiddenDanger")
    public String getDetailHiddenDanger(
            @RequestParam("hiddenDangerId") Integer hiddenDangerId
    )
    {
//        h.id,
//                c.name checkTableName,
//            h.type,
//            h.hPhoto,
//            h.status,
//            h.startDate,
//            h.endDate,
//            h.finishDate,
//            h.rPhoto,
//            h.desc,
//            h.isFile,
//            u.name dispatchUserName,
//            dIng.name dispatchDeptName, <!--下发整改的部门-->
//            dEd.name deptName,<!--负责整改的部门-->
//            h.content
        Map<String,Object> map=inputService.getDetailHiddenDanger(hiddenDangerId);
        JSONObject re=new JSONObject();
        //查询成功
        if(map.size()>0)
        {
            re.put("status",1);
            JSONObject data=new JSONObject();
            data.put("id",map.get("id"));
            data.put("checkTableName",map.get("checkTableName"));
            data.put("type",map.get("type"));
            data.put("hPhoto",map.get("hPhoto"));
            data.put("status",map.get("status"));
            data.put("startDate",new SimpleDateFormat("yyyy-MM-dd").format((Date) map.get("startDate")));
            data.put("endDate",new SimpleDateFormat("yyyy-MM-dd").format(map.get("endDate")));
            data.put("finishDate",new SimpleDateFormat("yyyy-MM-dd").format(map.get("finishDate")));
            data.put("rPhoto",map.get("rPhoto"));
            data.put("desc",map.get("desc"));
            data.put("isFile",map.get("isFile"));
            data.put("dispatchUserName",map.get("dispatchUserName"));
            data.put("dispatchDeptName",map.get("dispatchDeptName"));
            data.put("deptName",map.get("deptName"));
            data.put("content",map.get("content"));
            re.put("data",data);
        }
        //查询失败
        else
        {
            re.put("status",0);
            re.put("message","查询失败");
        }
        return re.toJSONString();
    }

    //由录用表id获得其详情
    //Post
    @RequestMapping("/api/input/getDetailInput")
    public String getDetailInput(
            @RequestParam("inputId") Integer inputId)
    {
        Map<String,Object> map=inputService.getDetailInput(inputId);
        JSONObject re=new JSONObject();
        //查询成功
        if(map.size()>0)
        {
//                i.id,
//                c.name checkTableName,
//                i.userName,   <!--检查人的姓名-->
//                dIng.name deptName,
//                i.checkDate,
//                dEd.name deptedName,
//                i.isQualified,
//                i.desc,
//                i.type,
//                i.otherPerson
            re.put("status",1);
            JSONObject data=new JSONObject();
            data.put("id",map.get("id"));
            data.put("checkTableName",map.get("checkTableName"));
            data.put("userName",map.get("userName"));
            data.put("deptName",map.get("deptName"));
            data.put("checkDate",new SimpleDateFormat("yyyy-MM-dd").format((Date)map.get("checkDate")));
            data.put("deptedName",map.get("deptedName"));
            data.put("isQualified",map.get("isQualified"));
            data.put("desc",map.get("desc"));
            data.put("type",map.get("type"));
            data.put("otherPerson",map.get("otherPerson"));
            re.put("data",data);
        }
        //查询失败
        else
        {
            re.put("status",0);
            re.put("message","查询失败");
        }
        return re.toJSONString();
    }

    //获取录入表列表
    //Get
    @RequestMapping("/api/input/getList")
    public String getListInput(
            Integer pageNum,
            Integer pageSize
    )
    {
        PageInfo<Map<String,Object>> pageInfo=inputService.getListInput(pageNum,pageSize);

        JSONObject re=new JSONObject();
        //判断列表的数量
        //数量>0
        if(pageInfo.getSize()>0)
        {
            re.put("status",1);
            JSONObject data=new JSONObject();
            data.put("total",pageInfo.getSize());
            data.put("pageNum",pageNum);
            data.put("pageSize",pageSize);
            JSONArray list=new JSONArray();
            for (Map<String,Object> map:pageInfo.getList())
            {
                JSONObject temp=new JSONObject();
                temp.put("id",map.get("id"));
                temp.put("checkTableName",map.get("checkTableName"));
                temp.put("userName",map.get("userName"));
                temp.put("deptName",map.get("deptName"));
                temp.put("checkDate",new SimpleDateFormat("yyyy-MM-dd").format((Date)map.get("checkDate")));
                temp.put("deptedName",map.get("deptedName"));
                temp.put("isQualified",map.get("isQualified"));
                temp.put("desc",map.get("desc"));
                temp.put("type",map.get("type"));
                temp.put("otherPerson",map.get("otherPerson"));
                list.add(temp);
            }
            re.put("list",list);
        }
        //数量<=0
        else
        {
            re.put("status",0);
            re.put("message","暂无录用表信息");
        }
        return re.toJSONString();
    }
}
