package com.whut.dao;

import com.whut.bean.Accident;
import com.whut.bean.FirstLevelIndicator;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author lixing
 * @version 1.0
 * @date 2019/10/20 16:31
 */

@Mapper
@Repository
public interface IAccidentDao {


    public int addAccident(Accident accident);
    public int updateAccident(Accident accident);
    public  int deleteAccident(Integer id);
    public Map<String,Object> getdetail(Integer id );//详细
    public List<Map<String, Object>> getAccident(); //查询部分
    public List<Map<String, Object>> getListAccident();//查询全部

    public int sumNumberId(); //事故和
    public List<Map<String, Object>> sumDirectLoss();//直接经济损失
    public List<Map<String, Object>> sumIndirectLoss();//间接经济损失
    public List<Map<String, Object>> sumLevel();//严重级别ABCDEF


}
