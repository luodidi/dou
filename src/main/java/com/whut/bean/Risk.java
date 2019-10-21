package com.whut.bean;

/**
 * @author luodidi
 * @version 1.0
 * @date 2019/10/19 12:01
 */
public class Risk {
    // 风险id
    private int id;

    // 风险名称
    private String name;

    // 风险地点

    private  String place;

    // 风险等级

    private  String level;

    // 联系方式
    private  String telephone;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

}
