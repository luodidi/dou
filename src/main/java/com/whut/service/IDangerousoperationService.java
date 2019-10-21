package com.whut.service;

import com.github.pagehelper.PageInfo;
import com.whut.bean.Dangerousoperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author lixing
 * @version 1.0
 * @date 2019/10/16 16:08
 */

public interface IDangerousoperationService {

public PageInfo<Map<String,Object>> findDangerousoperation(Integer page, Integer size); //有页码

    public List<Map<String, Object>> findListDangerousoperation(); //无页码

    public  Map<String,Object> find( Integer dangerousoperationId);//详细

    public  int deletedangerousoperation(int dangerousoperationId);
    public int addangerousoperation(Dangerousoperation dangerousoperation);
    public  int updateangerousoperation(Dangerousoperation dangerousoperation);
    public int submitdangerousoperation( int dangerousoperationId);
    public int withdrawdangerousoperation( int dangerousoperationId);
    public int filedangerousoperation( int dangerousoperationId);

}
