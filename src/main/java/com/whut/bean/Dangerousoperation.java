package com.whut.bean;

import java.util.Date;

/**
 * @author lixing
 * @version 1.0
 * @date 2019/10/16 15:23
 */
public class Dangerousoperation {
    private int id,deptId,number,keepYear;
    private String status,name,place,applyPerson,curator,content,majorFactor,safeMeasure;
    private Boolean keepFile;
    private Date applyDate,startDate,endDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getKeepYear() {
        return keepYear;
    }

    public void setKeepYear(int keepYear) {
        this.keepYear = keepYear;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getApplyPerson() {
        return applyPerson;
    }

    public void setApplyPerson(String applyPerson) {
        this.applyPerson = applyPerson;
    }

    public String getCurator() {
        return curator;
    }

    public void setCurator(String curator) {
        this.curator = curator;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMajorFactor() {
        return majorFactor;
    }

    public void setMajorFactor(String majorFactor) {
        this.majorFactor = majorFactor;
    }

    public String getSafeMeasure() {
        return safeMeasure;
    }

    public void setSafeMeasure(String safeMeasure) {
        this.safeMeasure = safeMeasure;
    }

    public Boolean getKeepFile() {
        return keepFile;
    }

    public void setKeepFile(Boolean keepFile) {
        this.keepFile = keepFile;
    }

    public Date getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
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

    @Override
    public String toString() {
        return "Dangerousoperation{" +
                "id=" + id +
                ", deptId=" + deptId +
                ", number=" + number +
                ", keepYear=" + keepYear +
                ", status='" + status + '\'' +
                ", name='" + name + '\'' +
                ", place='" + place + '\'' +
                ", applyPerson='" + applyPerson + '\'' +
                ", curator='" + curator + '\'' +
                ", content='" + content + '\'' +
                ", majorFactor='" + majorFactor + '\'' +
                ", safeMeasure='" + safeMeasure + '\'' +
                ", keepFile=" + keepFile +
                ", applyDate=" + applyDate +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
