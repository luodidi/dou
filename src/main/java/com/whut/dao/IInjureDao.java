package com.whut.dao;

import com.whut.bean.Accident;
import com.whut.bean.Injure;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author lixing
 * @version 1.0
 * @date 2019/10/20 22:47
 */
@Mapper
@Repository
public interface IInjureDao {

    public int addInjure(Injure injure);
    public int updateInjure(Injure injure);
    public int deleteInjure(@Param("accidentId") Integer accidentId,@Param("userId") Integer userId);
    public Map<String,Object> getInjuredetail(Integer accidentId,Integer userId);//详细
    public List<Map<String, Object>> getInjure(); //查询部分
    public List<Map<String, Object>> getListInjuret();//查询全部
}
