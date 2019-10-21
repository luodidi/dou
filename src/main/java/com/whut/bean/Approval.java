package com.whut.bean;

/**
 * @author lixing
 * @version 1.0
 * @date 2019/10/20 15:24
 */
public class Approval {
    private int dangerousOperationId,approverId;
    private String result,record;

    public int getDangerousOperationId() {
        return dangerousOperationId;
    }

    public void setDangerousOperationId(int dangerousOperationId) {
        this.dangerousOperationId = dangerousOperationId;
    }

    public int getApproverId() {
        return approverId;
    }

    public void setApproverId(int approverId) {
        this.approverId = approverId;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    @Override
    public String toString() {
        return "Approval{" +
                "dangerousOperationId=" + dangerousOperationId +
                ", approverId=" + approverId +
                ", result='" + result + '\'' +
                ", record='" + record + '\'' +
                '}';
    }
}
