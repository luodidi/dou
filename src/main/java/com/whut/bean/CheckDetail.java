package com.whut.bean;

/*
  Created by IntelliJ IDEA.
  User: ccc
  Date: 2019/10/17
  Time: 10:14
  To change this template use File | Settings | File Templates.
*/

//用于描述二级指标
public class CheckDetail {
    public String inputId;//录入表的id
    public Integer secondLevelIndicatorId;//二级指标id
    public Boolean isQualified;//是否合格
    public String desc;//不合格说明

    @Override
    public String toString() {
        return "CheckDetail{" +
                "inputId='" + inputId + '\'' +
                ", secondLevelIndicatorId=" + secondLevelIndicatorId +
                ", isQualified=" + isQualified +
                ", desc='" + desc + '\'' +
                '}';
    }

    public String getInputId() {
        return inputId;
    }

    public void setInputId(String inputId) {
        this.inputId = inputId;
    }

    public Integer getSecondLevelIndicatorId() {
        return secondLevelIndicatorId;
    }

    public void setSecondLevelIndicatorId(Integer secondLevelIndicatorId) {
        this.secondLevelIndicatorId = secondLevelIndicatorId;
    }

    public Boolean getQualified() {
        return isQualified;
    }

    public void setQualified(Boolean qualified) {
        isQualified = qualified;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
