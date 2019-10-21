package com.whut.service;

import com.github.pagehelper.PageInfo;
import com.whut.bean.Hazard;

import java.util.Map;

/**
 * @author luodidi
 * @version 1.0
 * @date 2019/10/19 12:14
 */
public interface IHazardService {

    public int getHazardById(Integer id);                // 通过id查找 显示详细的信息

    public PageInfo<Map<String,Object>> getListHazard(Integer page, Integer size);

    public int insertHazard(Hazard hazard);             // 添加风险

    public int updateHazardById(Hazard hazard);          // 更新

    public int deleteHazard(Integer id);                  // 删除

    public Map<String, Object> getDetailHazard(Integer hazardId);  // 根据ID获取详细
}
