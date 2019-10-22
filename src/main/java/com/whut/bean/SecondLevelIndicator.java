package com.whut.bean;

import java.util.Date;

/*
  Created by IntelliJ IDEA.
  User: ccc
  Date: 2019/10/16
  Time: 18:56
  To change this template use File | Settings | File Templates.
*/
public class SecondLevelIndicator {
    private Integer id;//二级指标的id
    private Integer firstLevelIndicatorId;//所属一级指标的id
    private String content;//该二级指标的内容
    private Date addDate;//添加的时间
    private Boolean isDelete;//是否删除
    private Date deleteDate;//删除的时间

    @Override
    public String toString() {
        return "SecondLevelIndicator{" +
                "id=" + id +
                ", firstLevelIndicatorId=" + firstLevelIndicatorId +
                ", content='" + content + '\'' +
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

    public Integer getFirstLevelIndicatorId() {
        return firstLevelIndicatorId;
    }

    public void setFirstLevelIndicatorId(Integer firstLevelIndicatorId) {
        this.firstLevelIndicatorId = firstLevelIndicatorId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
