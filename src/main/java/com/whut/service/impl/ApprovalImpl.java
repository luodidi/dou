package com.whut.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.whut.bean.Approval;
import com.whut.dao.IApprovalDao;
import com.whut.service.IApprovalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author lixing
 * @version 1.0
 * @date 2019/10/20 15:29
 */
@Service
public class ApprovalImpl implements IApprovalService {

    @Autowired
    IApprovalDao iApprovalDao;

    @Override
    public int resetApproval(int dangerousOperationId) {
        return iApprovalDao.resetApproval(dangerousOperationId);
    }

    @Override
    public int passApproval(int  dangerousOperationId) {
        return iApprovalDao.passApproval(dangerousOperationId);
    }
}
