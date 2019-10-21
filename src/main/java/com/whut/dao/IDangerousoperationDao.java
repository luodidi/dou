package com.whut.dao;

import com.whut.bean.Dangerousoperation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author lixing
 * @version 1.0
 * @date 2019/10/16 15:53
 */
@Mapper
@Repository
public interface IDangerousoperationDao {
 // public List<Dangerousoperation> findDangerousoperation(); //查询
  public List<Map<String, Object>> findDangerousoperation(); //有页码，全部
  public  Map<String,Object> find(@Param("dangerousoperationId") int dangerousoperationId);//详细
 public List<Map<String, Object>> findListDangerousoperation(); //无页码，部分

 public  int updateangerousoperation(Dangerousoperation dangerousoperation);
 public int addangerousoperation(Dangerousoperation dangerousoperation);
 public  int deletedangerousoperation(@Param("dangerousoperationId") int dangerousoperationId);
 public int submitdangerousoperation(@Param("dangerousoperationId") int dangerousoperationId);
 public int withdrawdangerousoperation(@Param("dangerousoperationId") int dangerousoperationId);
 public int filedangerousoperation(@Param("dangerousoperationId") int dangerousoperationId);
}
