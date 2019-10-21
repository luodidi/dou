package com.whut.service;

import com.github.pagehelper.PageInfo;
import com.whut.bean.Approval;

import java.util.List;
import java.util.Map;

/**
 * @author lixing
 * @version 1.0
 * @date 2019/10/20 15:29
 */
public interface IApprovalService {



    public int resetApproval(int dangerousOperationId);//退回
    public int passApproval(int dangerousOperationId);//通过
}
