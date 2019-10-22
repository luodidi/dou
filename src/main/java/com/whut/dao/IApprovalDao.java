package com.whut.dao;

import com.whut.bean.Approval;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author lixing
 * @version 1.0
 * @date 2019/10/20 15:28
 */
@Mapper
@Repository
public interface IApprovalDao {

    public int resetApproval(Integer dangerousOperationId);//退回
    public int passApproval(Integer dangerousOperationId);//通过
    public List<Map<String, Object>> getListApproval();//批复记录
}
