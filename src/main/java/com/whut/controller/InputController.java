package com.whut.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.whut.bean.CheckTableDetail;
import com.whut.bean.HiddenDanger;
import com.whut.bean.Input;
import com.whut.service.impl.FirstLevelIndicatorImpl;
import com.whut.service.impl.InputServiceImpl;
import com.whut.service.impl.SecondLevelIndicatorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


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
    @Autowired
    FirstLevelIndicatorImpl firstLevelIndicatorService;
    @Autowired
    SecondLevelIndicatorServiceImpl secondLevelIndicatorService;

    //创建新的录用表并且添加隐患和具体二级指标
    //Post
    @Transactional //出现异常时进行回滚
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
                //hiddenDanger.setStatus(jsonHd.getString("status"));
                hiddenDanger.setStatus("未整改");
                hiddenDanger.setStartDate(new SimpleDateFormat("yyyy-MM-dd").parse("2099-12-31"));
                hiddenDanger.setEndDate(new SimpleDateFormat("yyyy-MM-dd").parse("2099-12-31"));
                hiddenDanger.setFinishDate(new SimpleDateFormat("yyyy-MM-dd").parse("2099-12-31"));
                hiddenDanger.setDesc("");
                hiddenDanger.setFile(false);
                hiddenDanger.setDispatchUserId(1);
                hiddenDanger.setDispatchDeptId(1);
                hiddenDanger.setDeptId(1);
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
        //String path= ClassUtils.getDefaultClassLoader().getResource("").getPath()+ "static/HDphotos/";
        String path="F:/ideaWorkPlace/dou/src/main/resources/static/HDphotos/";
        String path1="F:/ideaWorkPlace/dou/target/classes/static/HDphotos/";
        File file=new File(path);
        if(!file.exists())
        {
            file.mkdirs();
        }
        String rPhoto=UUID.randomUUID().toString().replace("-", "")+".jpg";
        filePicture(multipartFile,path,rPhoto);
        filePicture(multipartFile,path1,rPhoto);
        JSONObject re=new JSONObject();
        re.put("status",1);
        re.put("path","HDphotos/"+rPhoto);
        return re.toJSONString();
    }

    private void filePicture(MultipartFile multipartFile,String path,String rPhoto) throws IOException {
        FileInputStream fileInputStream=(FileInputStream)multipartFile.getInputStream();
        //BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(path + File.separator + rPhoto));        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(path + File.separator + rPhoto));
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(path  + rPhoto));
        byte[] bs = new byte[1024];
        int len;
        while ((len = fileInputStream.read(bs)) != -1) {
            bos.write(bs, 0, len);
        }
        bos.flush();
        bos.close();
    }
    //由隐患id获得其详情
    //Post
    @RequestMapping("/api/input/getDetailHiddenDanger")
    public String getDetailHiddenDanger(
            @RequestParam("id") Integer hiddenDangerId
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
            @RequestParam("inputId") String inputId)
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

    //获取录入表列表（分页）
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
            data.put("list",list);
            re.put("data",data);
        }
        //数量<=0
        else
        {
            re.put("status",0);
            re.put("message","暂无录用表信息");
        }
        return re.toJSONString();
    }

    //下发整改
    //Post
    @RequestMapping("/api/input/toRectify")
    public String toRectify(
//            status=#{status},
//    startDate=#{startDate},
//    endDate=#{endDate},
//    dispatchDeptId=#{dispatchDeptId},
//    dispatchUserId=#{dispatchUserId},
//    deptId=#{deptId}
            @RequestParam("id") Integer id ,
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate,
            @RequestParam("dispatchDeptId") Integer dispatchDeptId ,
            @RequestParam("dispatchUserId") Integer dispatchUserId ,
            @RequestParam("deptId") Integer deptId
    ) throws ParseException {
        HiddenDanger hiddenDanger=new HiddenDanger();
        hiddenDanger.setId(id);
        hiddenDanger.setStatus("未整改");
        hiddenDanger.setStartDate(new SimpleDateFormat("yyyy-MM-dd").parse(startDate));
        hiddenDanger.setEndDate(new SimpleDateFormat("yyyy-MM-dd").parse(endDate));
        hiddenDanger.setDispatchUserId(dispatchUserId);
        hiddenDanger.setDispatchDeptId(dispatchDeptId);
        hiddenDanger.setDeptId(deptId);
        int i=inputService.toRectify(hiddenDanger);
        JSONObject re=new JSONObject();
        if(i>0)
        {
            re.put("status",1);
            re.put("message","下发整改成功");
        }
        else
        {
            re.put("status",0);
            re.put("message","下发整改失败");
        }
        return re.toJSONString();
    }

    //完成整改
    //Post
    @RequestMapping("/api/input/rectify")
    public String rectify(
//            rPhoto=#{rPhoto},
//    status=#{status},
//    finishDate=#{finishDate},
//            `desc`=#{desc}
            @RequestParam("rPhoto") String rPhoto,
            @RequestParam("id") Integer id ,
            @RequestParam("finishDate") String finishDate,
            @RequestParam("desc") String desc
    ) throws ParseException {
        HiddenDanger hiddenDanger=new HiddenDanger();
        hiddenDanger.setId(id);
        hiddenDanger.setrPhoto(rPhoto);
        hiddenDanger.setStatus("整改中");
        hiddenDanger.setFinishDate(new SimpleDateFormat("yyyy-MM-dd").parse(finishDate));
        hiddenDanger.setDesc(desc);
        int i=inputService.rectify(hiddenDanger);
        JSONObject re=new JSONObject();
        if(i>0)
        {
            re.put("status",1);
            re.put("message","完成整改成功");
        }
        else
        {
            re.put("status",0);
            re.put("message","完成整改失败");
        }
        return re.toJSONString();
    }

    //获取某种状态的隐患表（分页）
    //Get
    @RequestMapping("/api/input/getListHiddenDanger")
    public String getHiddenDangerList(String status,int pageNum,int pageSize)
    {
        PageInfo<Map<String,Object>> pageInfo=null;
        if(status.equals("已逾期"))
        {
            pageInfo=inputService.getHiddenDangerListTimeOut(pageNum,pageSize);
        }
        else
        {
            pageInfo=inputService.getHiddenDangerList(status,pageNum,pageSize);
        }
//        h.id,
//                c.name checkTableName,
//            h.type,
//            h.status,
//            h.content,
//            h.isFile

        JSONObject re=new JSONObject();
        if(pageInfo.getSize()>0)
        {
            re.put("status",1);
            JSONObject data=new JSONObject();
            data.put("total",pageInfo.getSize());
            data.put("pageNum",pageNum);
            data.put("pageSize",pageSize);
            JSONArray list=new JSONArray();
            for(Map<String,Object> map:pageInfo.getList())
            {
                JSONObject temp=new JSONObject();
                temp.put("id",map.get("id"));
                temp.put("checkTableName",map.get("checkTableName"));
                temp.put("type",map.get("type"));
                //temp.put("hPhoto",map.get("hPhoto"));
                temp.put("status",map.get("status"));
                //temp.put("startDate",new SimpleDateFormat("yyyy-MM-dd").format((Date) map.get("startDate")));
                //temp.put("endDate",new SimpleDateFormat("yyyy-MM-dd").format(map.get("endDate")));
                //temp.put("finishDate",new SimpleDateFormat("yyyy-MM-dd").format(map.get("finishDate")));
                //temp.put("rPhoto",map.get("rPhoto"));
                //temp.put("desc",map.get("desc"));
                temp.put("isFile",map.get("isFile"));
                //temp.put("dispatchUserName",map.get("dispatchUserName"));
               // temp.put("dispatchDeptName",map.get("dispatchDeptName"));
               // temp.put("deptName",map.get("deptName"));
                temp.put("content",map.get("content"));
                list.add(temp);
            }
            data.put("list",list);
            re.put("data",data);
        }
        else
        {
            re.put("status",0);
            re.put("message","查询失败");
        }
        return re.toJSONString();
    }

    //获得各种隐患分类的数量
    @Transactional
    @RequestMapping("/api/input/getNumberHiddenDanger")
    public String getNumberHiddenDanger()
    {
//        SELECT h.type hdType,i.`type` inputType,COUNT(h.`type`) hdTypeNum,COUNT(i.`type`) inputTypeNum
//        FROM input AS i,hiddendanger AS h
//        WHERE i.id=h.`inputId`
//        and to_days(now()) &lt;= to_days(h.endDate)
//        GROUP BY h.type,i.`type`

        List<Map<String,Object>> data=inputService.getNumberHiddenDanger();
        List<Map<String,Object>> dataTimeOut=inputService.getNumberHiddenDangerTimeOut();
        JSONObject re=new JSONObject();

        HashMap<String,Integer> map=new HashMap<>();
        map.put("wzgyb",0);//未整改一般
        map.put("wzgjd",0);//未整改较大
        map.put("wzgyz",0);//未整改严重
        map.put("yzgyb",0);//已整改一般
        map.put("yzgjd",0);//已整改
        map.put("yzgyz",0);
        map.put("zgzyb",0);
        map.put("zgzjd",0);//整改中
        map.put("zgzyz",0);
        map.put("yyqyb",0);
        map.put("yyqjd",0);//已逾期
        map.put("yyqyz",0);

        if(!(dataTimeOut==null||data.size()<1))
        {
            for(Map<String,Object> map1:dataTimeOut)
            {
                if(map1.get("hdType").equals("一般隐患"))
                {
                    map.put("yyqyb",((Long)map1.get("hdTypeNum")).intValue()+(int)map.get("yyqyb"));
                }
                else if(map1.get("hdType").equals("较大隐患"))
                {
                    map.put("yyqjd",((Long)map1.get("hdTypeNum")).intValue()+(int)map.get("yyqjd"));
                }
                else
                {
                    map.put("yyqyz",((Long)map1.get("hdTypeNum")).intValue()+map.get("yyqyz"));
                }
            }
        }
        if(!(data==null||data.size()<1))
        {
            for (Map<String,Object>map1:data)
            {
                if(map1.get("status").equals("未整改"))
                {
                    if(map1.get("hdType").equals("一般隐患"))
                    {
                        map.put("wzgyb",((Long)map1.get("hdTypeNum")).intValue()+(int)map.get("wzgyb"));
                    }
                    else if(map1.get("hdType").equals("较大隐患"))
                    {
                        map.put("wzgjd",((Long)map1.get("hdTypeNum")).intValue()+(int)map.get("wzgjd"));
                    }
                    else
                    {
                        map.put("wzgyz",((Long)map1.get("hdTypeNum")).intValue()+(int)map.get("wzgyz"));
                    }
                }
                else if(map1.get("status").equals("已整改"))
                {
                    if(map1.get("hdType").equals("一般隐患"))
                    {
                        map.put("yzgyb",((Long)map1.get("hdTypeNum")).intValue()+(int)map.get("yzgyb"));
                    }
                    else if(map1.get("hdType").equals("较大隐患"))
                    {
                        map.put("yzgjd",((Long)map1.get("hdTypeNum")).intValue()+(int)map.get("yzgjd"));
                    }
                    else
                    {
                        map.put("yzgyz",((Long)map1.get("hdTypeNum")).intValue()+(int)map.get("yzgyz"));
                    }
                }
                else
                {
                    if(map1.get("hdType").equals("一般隐患"))
                    {
                        map.put("zgzyb",((Long)map1.get("hdTypeNum")).intValue()+(int)map.get("zgzyb"));
                    }
                    else if(map1.get("hdType").equals("较大隐患"))
                    {
                        map.put("zgzjd",((Long)map1.get("hdTypeNum")).intValue()+(int)map.get("zgzjd"));
                    }
                    else
                    {
                        map.put("zgzyz",((Long)map1.get("hdTypeNum")).intValue()+(int)map.get("zgzyz"));
                    }
                }
            }
        }

        re.put("status",1);
        JSONArray data1=new JSONArray();
        String inputName[]=new String[]{"未整改","整改中","已整改","已逾期"};
        String hdName[]=new String[]{"一般隐患","较大隐患","严重隐患"};
        String input[]=new String[]{"wzgyb","wzgjd","wzgyz",
                                    "zgzyb","zgzjd","zgzyz",
                                    "yzgyb","yzgjd","zgzyz",
                                    "yyqyb","yyqjd","zgzyz"};
        int i=0;
        int k=0;
        while (i<4)
        {
            JSONObject temp=new JSONObject();
            temp.put("hdStatus",inputName[i]);
            temp.put("total",map.get(input[k++])+map.get(input[k++])+map.get(input[k++]));
            int j=0;
            JSONArray list=new JSONArray();
            while (j<3)
            {
                JSONObject temp2=new JSONObject();
                temp2.put("name",hdName[j]);
                temp2.put("value",map.get(input[k-3+j]));
                list.add(temp2);
                j++;
            }
            temp.put("list",list);
            data1.add(temp);
            i++;
        }
        re.put("totalYB",map.get("wzgyb")+map.get("zgzyb")+map.get("yzgyb")+map.get("yyqyb"));
        re.put("totalJD",map.get("wzgjd")+map.get("zgzjd")+map.get("yzgjd")+map.get("yyqjd"));
        re.put("totalYZ",map.get("wzgyz")+map.get("zgzyz")+map.get("zgzyz")+map.get("zgzyz"));
        re.put("data",data1);

        return re.toJSONString();
    }

    //获得录入表及其一级指标内容、二级指标内容
    //Post
    @RequestMapping("/api/input/getInputAllInfoById")
    public String getInputAllInfoById(
            @RequestParam("inputId") String inputId
    )
    {
        JSONObject re=new JSONObject();
        re.put("status",1);
        //获取该id录入表信息
        Map<String,Object> inputInfo=inputService.getDetailInput(inputId);
        JSONObject data=new JSONObject();
//        i.id,
//                c.name checkTableName,
//            c.id checkTableId,
//            i.userName,   <!--检查人的姓名-->
//            dIng.name deptName,
//            i.checkDate,
//            dEd.name deptedName,
//            i.isQualified,
//            i.desc,
//            i.type,
//            i.otherPerson
        data.put("inputId",inputInfo.get("id"));
        data.put("checkTableName",inputInfo.get("checkTableName"));
        data.put("checkTableId",inputInfo.get("checkTableId"));
        data.put("userName",inputInfo.get("userName"));
        data.put("deptName",inputInfo.get("deptName"));
        data.put("checkDate",new SimpleDateFormat("yyyy-MM-dd").format(inputInfo.get("checkDate")));
        data.put("deptedName",inputInfo.get("deptedName"));
        data.put("isQualified",inputInfo.get("isQualified"));
        data.put("desc",inputInfo.get("desc"));
        data.put("type",inputInfo.get("type"));
        data.put("otherPerson",inputInfo.get("otherPerson"));
        //获得录入表的所有一级指标
        List<Map<String,Object>> firstList=firstLevelIndicatorService.getAllListFirstLevelIndicator(
                (int)inputInfo.get("checkTableId"));
//        select id,project
        JSONArray firstListJson=new JSONArray();
        for(Map<String,Object> firstMap:firstList)
        {
            JSONObject firstTemp=new JSONObject();
            firstTemp.put("firstId",firstMap.get("id"));
            firstTemp.put("project",firstMap.get("project"));
            //由一级指标id获得其所有二级指标
            List<Map<String,Object>> secondList=secondLevelIndicatorService.getAllListSecondLevelIndicator(
                    (int)firstMap.get("id"));
//            select id,content
            JSONArray secondListJson=new JSONArray();
            //根据二级指标id和inputId获得其checkDetail
            for(Map<String,Object>secondMap:secondList)
            {
                JSONObject secondTemp=new JSONObject();
                secondTemp.put("secondId",secondMap.get("id"));
                secondTemp.put("content",secondMap.get("content"));
                Map<String,Object> checkDetailMap=inputService.getAllCheckDetail(inputId,(int)secondMap.get("id"));
                secondTemp.put("isQualified",checkDetailMap.get("isQualified"));
                secondTemp.put("desc",checkDetailMap.get("desc"));
                secondListJson.add(secondTemp);
            }
            firstTemp.put("secondList",secondListJson);
            firstListJson.add(firstTemp);
        }
        data.put("firstList",firstListJson);
        re.put("data",data);
        return re.toJSONString();
    }

    //撤回隐患
    //Post
    @RequestMapping("/api/input/withdrawHD")
    public String withdrawHD(
            //@RequestParam("hiddenDangerId")
                    Integer hiddenDangerId
    )
    {
        int i=inputService.withdrawHiddenDanger(hiddenDangerId);
        JSONObject re=new JSONObject();
        if(i>0)
        {
            re.put("status",1);
            re.put("message","撤回成功");
        }
        else
        {
            re.put("status",0);
            re.put("message","撤回失败");
        }
        return re.toJSONString();
    }
}
