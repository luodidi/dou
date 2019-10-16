package com.whut.bean;

import java.util.Date;

/*
  Created by IntelliJ IDEA.
  User: ccc
  Date: 2019/10/16
  Time: 12:08
  To change this template use File | Settings | File Templates.
*/
public class CheckTable {
    private Integer id; //检查表的id
    private String name;//名称
    private String identifier;//编号
    private String type;//类别
    private Integer deptId;//所属部门的id
    private Date addDate;//添加的时间
    private Boolean isDelete;//是否删除
    private Date deleteDate;//删除的时间

    @Override
    public String toString() {
        return "CheckTable{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", identifier='" + identifier + '\'' +
                ", type='" + type + '\'' +
                ", deptId=" + deptId +
                ", addDate=" + addDate +
                ", isDelete=" + isDelete +
                ", deleteDate=" + deleteDate +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }

    public Date getDeleteDate() {
        return deleteDate;
    }

    public void setDeleteDate(Date deleteDate) {
        this.deleteDate = deleteDate;
    }
}
