package com.whut.bean;

import java.util.Date;

/**
 * @author lixing
 * @version 1.0
 * @date 2019/10/20 16:18
 */
public class Accident {
//     id,accidentTypeId,name,place,date,status,nature,level,reason,directLoss,indirectLoss,lossWorkDay,
//            outageTime,survey,causeAnalysis,injure,lossDegree,resolution,lesson,measure,remark
    private int id,lossWorkDay,outageTime;
    private String name,place,status,nature,level,reason,survey,causeAnalysis,injure,
            lossDegree,resolution,lesson,measure,remark,accidentType;
    private Date date;
    private double directLoss,indirectLoss;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccidentType() {
        return accidentType;
    }

    public void setAccidentType(String accidentType) {
        this.accidentType = accidentType;
    }

    public int getLossWorkDay() {
        return lossWorkDay;
    }

    public void setLossWorkDay(int lossWorkDay) {
        this.lossWorkDay = lossWorkDay;
    }

    public int getOutageTime() {
        return outageTime;
    }

    public void setOutageTime(int outageTime) {
        this.outageTime = outageTime;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getSurvey() {
        return survey;
    }

    public void setSurvey(String survey) {
        this.survey = survey;
    }

    public String getCauseAnalysis() {
        return causeAnalysis;
    }

    public void setCauseAnalysis(String causeAnalysis) {
        this.causeAnalysis = causeAnalysis;
    }

    public String getInjure() {
        return injure;
    }

    public void setInjure(String injure) {
        this.injure = injure;
    }

    public String getLossDegree() {
        return lossDegree;
    }

    public void setLossDegree(String lossDegree) {
        this.lossDegree = lossDegree;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getLesson() {
        return lesson;
    }

    public void setLesson(String lesson) {
        this.lesson = lesson;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getDirectLoss() {
        return directLoss;
    }

    public void setDirectLoss(double directLoss) {
        this.directLoss = directLoss;
    }

    public double getIndirectLoss() {
        return indirectLoss;
    }

    public void setIndirectLoss(double indirectLoss) {
        this.indirectLoss = indirectLoss;
    }

    @Override
    public String toString() {
        return "Accident{" +
                "id=" + id +
                ", accidentType=" + accidentType +
                ", lossWorkDay=" + lossWorkDay +
                ", outageTime=" + outageTime +
                ", name='" + name + '\'' +
                ", place='" + place + '\'' +
                ", status='" + status + '\'' +
                ", nature='" + nature + '\'' +
                ", level='" + level + '\'' +
                ", reason='" + reason + '\'' +
                ", survey='" + survey + '\'' +
                ", causeAnalysis='" + causeAnalysis + '\'' +
                ", injure='" + injure + '\'' +
                ", lossDegree='" + lossDegree + '\'' +
                ", resolution='" + resolution + '\'' +
                ", lesson='" + lesson + '\'' +
                ", measure='" + measure + '\'' +
                ", remark='" + remark + '\'' +
                ", date=" + date +
                ", directLoss=" + directLoss +
                ", indirectLoss=" + indirectLoss +
                '}';
    }
}
