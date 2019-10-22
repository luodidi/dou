package com.whut.bean;

/*
  Created by IntelliJ IDEA.
  User: ccc
  Date: 2019/10/17
  Time: 10:21
  To change this template use File | Settings | File Templates.
*/

import java.util.Date;

//用于描述录入表的信息
public class Input {
    private String id;//录入检查表的id
    private Integer checkTableId;//所使用的检查表id
    private String userName;//检查人的姓名
    private Integer deptId;//检查的单位
    private Date checkDate;//检查的日期
    private Integer deptedId;//被检查的部门id
    private Boolean isQualified;//是否合格
    private String desc;//不合格描述
    private String type;//检查类型
    private String otherPerson;//参检人

    @Override
    public String toString() {
        return "Input{" +
                "id='" + id + '\'' +
                ", checkTableId=" + checkTableId +
                ", userName='" + userName + '\'' +
                ", deptId=" + deptId +
                ", checkDate=" + checkDate +
                ", deptedId=" + deptedId +
                ", isQualified=" + isQualified +
                ", desc='" + desc + '\'' +
                ", type='" + type + '\'' +
                ", otherPerson='" + otherPerson + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getCheckTableId() {
        return checkTableId;
    }

    public void setCheckTableId(Integer checkTableId) {
        this.checkTableId = checkTableId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    public Integer getDeptedId() {
        return deptedId;
    }

    public void setDeptedId(Integer deptedId) {
        this.deptedId = deptedId;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOtherPerson() {
        return otherPerson;
    }

    public void setOtherPerson(String otherPerson) {
        this.otherPerson = otherPerson;
    }
}
