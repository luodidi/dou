package com.whut.bean;

/**
 * @author luodidi
 * @version 1.0
 * @date 2019/10/19 12:00
 */
public class Hazard {
    //  危险源id
    private  int id;

    // 风险id
    private int riskId;

    // 危险源name
    private String name;

    // 危险源类型
    private String type;

    // 等级L
    private  int L;

    // 等级E
    private int E;

    // 等级C
    private int C;

    // 危险源等级
    private  String degree;

    // 管控措施
    private String measure;

    // 管控层级
    private String level;

    // 部门id
    private int deptId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRiskId() {
        return riskId;
    }

    public void setRiskId(int riskId) {
        this.riskId = riskId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getL() {
        return L;
    }

    public void setL(int l) {
        L = l;
    }

    public int getE() {
        return E;
    }

    public void setE(int e) {
        E = e;
    }

    public int getC() {
        return C;
    }

    public void setC(int c) {
        C = c;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    // 用户id
    private int userId;




}
