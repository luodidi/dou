package com.whut.bean;

import java.util.Date;

/*
  Created by IntelliJ IDEA.
  User: ccc
  Date: 2019/10/16
  Time: 15:37
  To change this template use File | Settings | File Templates.
*/
public class FirstLevelIndicator {
    public Integer id;//一级指标id
    public Integer checkTableId;//所属的检查表
    public String project;//该一级指标的内容
    public Date addDate;//添加的时间
    public Boolean isDelete;//是否删除
    public Date deleteDate;//删除的时间

    @Override
    public String toString() {
        return "FirstLevelIndicator{" +
                "id=" + id +
                ", checkTableId=" + checkTableId +
                ", project='" + project + '\'' +
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

    public Integer getCheckTableId() {
        return checkTableId;
    }

    public void setCheckTableId(Integer checkTableId) {
        this.checkTableId = checkTableId;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
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
