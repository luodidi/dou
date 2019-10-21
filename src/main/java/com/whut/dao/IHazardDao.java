package com.whut.dao;

import com.whut.bean.Hazard;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author luodidi
 * @version 1.0
 * @date 2019/10/19 12:08
 */
@Mapper
@Repository
public interface IHazardDao {

    public int getHazardById(Integer id);                // 通过id查找 显示详细的信息

    public List<Map<String, Object>> getListHazard();

    public int insertHazard(Hazard hazard);          // 添加风险

    public int updateHazardById(Hazard hazard);             // 更新

    public int deleteHazard(Integer id);                // 删除

    public Map<String, Object> getDetailHazard(Integer hazardId);

}
