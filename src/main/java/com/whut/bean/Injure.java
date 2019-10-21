package com.whut.bean;

/**
 * @author lixing
 * @version 1.0
 * @date 2019/10/20 22:46
 */
public class Injure {
    private int accidentId,userId,lossWorkDay;
    private String level,injureNature,degree,damage,position;
    private double compensate;

    public int getAccidentId() {
        return accidentId;
    }

    public void setAccidentId(int accidentId) {
        this.accidentId = accidentId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getLossWorkDay() {
        return lossWorkDay;
    }

    public void setLossWorkDay(int lossWorkDay) {
        this.lossWorkDay = lossWorkDay;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getInjureNature() {
        return injureNature;
    }

    public void setInjureNature(String injureNature) {
        this.injureNature = injureNature;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getDamage() {
        return damage;
    }

    public void setDamage(String damage) {
        this.damage = damage;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public double getCompensate() {
        return compensate;
    }

    public void setCompensate(double compensate) {
        this.compensate = compensate;
    }

    @Override
    public String toString() {
        return "Injure{" +
                "accidentId=" + accidentId +
                ", userId=" + userId +
                ", lossWorkDay=" + lossWorkDay +
                ", level='" + level + '\'' +
                ", injureNature='" + injureNature + '\'' +
                ", degree='" + degree + '\'' +
                ", damage='" + damage + '\'' +
                ", position='" + position + '\'' +
                ", compensate=" + compensate +
                '}';
    }
}
