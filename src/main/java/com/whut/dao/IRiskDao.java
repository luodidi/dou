package com.whut.dao;

import com.whut.bean.Risk;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author luodidi
 * @version 1.0
 * @date 2019/10/19 12:09
 */
@Mapper
@Repository
public interface IRiskDao {

    public List<Map<String, Object>> getListRisk();   // 获得所有的检查表模板

    public int getRiskById(Integer id);               // 通过id查找 显示详细的信息

    public int insertRisk(Risk risk);                 // 添加风险

    public int updateRiskById(Risk risk);             // 更新

    public int deleteRisk(Integer id);                    // 删除



   // public Map<String, Object> getDetailRisk(Integer riskId);      // 通过id获取详细信息


}
