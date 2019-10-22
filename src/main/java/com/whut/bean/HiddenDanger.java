package com.whut.bean;

/*
  Created by IntelliJ IDEA.
  User: ccc
  Date: 2019/10/17
  Time: 8:48
  To change this template use File | Settings | File Templates.
*/

import java.util.Date;

//表示隐患的类
public class HiddenDanger {
    private Integer id;//隐患id
    private String inputId;//录入表的id
    private String type;//隐患的分类
    private String hPhoto;//隐患图片的保存路径
    private String status;//整改的状态
    private Date startDate;//下发整改时间
    private Date endDate;//截止时间
    private Date finishDate;//完成时间
    private String rPhoto;//整改图片路径
    private String desc;//整改描述
    private Boolean isFile;//是否归档
    private Integer dispatchUserId;//下发整改的人的id
    private Integer dispatchDeptId;//下发整改的部门的id
    private Integer deptId;//负责整改的部门的id
    private String content;//隐患的内容

    @Override
    public String toString() {
        return "HiddenDanger{" +
                "id=" + id +
                ", inputId='" + inputId + '\'' +
                ", type='" + type + '\'' +
                ", hPhoto='" + hPhoto + '\'' +
                ", status='" + status + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", finishDate=" + finishDate +
                ", rPhoto='" + rPhoto + '\'' +
                ", desc='" + desc + '\'' +
                ", isFile=" + isFile +
                ", dispatchUserId=" + dispatchUserId +
                ", dispatchDeptId=" + dispatchDeptId +
                ", deptId=" + deptId +
                ", content='" + content + '\'' +
                '}';
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInputId() {
        return inputId;
    }

    public void setInputId(String inputId) {
        this.inputId = inputId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String gethPhoto() {
        return hPhoto;
    }

    public void sethPhoto(String hPhoto) {
        this.hPhoto = hPhoto;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public String getrPhoto() {
        return rPhoto;
    }

    public void setrPhoto(String rPhoto) {
        this.rPhoto = rPhoto;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Boolean getFile() {
        return isFile;
    }

    public void setFile(Boolean file) {
        isFile = file;
    }

    public Integer getDispatchUserId() {
        return dispatchUserId;
    }

    public void setDispatchUserId(Integer dispatchUserId) {
        this.dispatchUserId = dispatchUserId;
    }

    public Integer getDispatchDeptId() {
        return dispatchDeptId;
    }

    public void setDispatchDeptId(Integer dispatchDeptId) {
        this.dispatchDeptId = dispatchDeptId;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }
}
