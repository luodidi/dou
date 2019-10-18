package com.whut.bean;

/*
  Created by IntelliJ IDEA.
  User: ccc
  Date: 2019/10/18
  Time: 12:35
  To change this template use File | Settings | File Templates.
*/

//用来描述检查表中具体的二级指标
public class CheckTableDetail {
    private String inputId;//录入表的id
    private Integer secondLevelIndicatorId;//使用的二级指标的id
    private Boolean isQualified;//是否合格
    private String desc;//不合格说明

    @Override
    public String toString() {
        return "CheckTableDetail{" +
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
