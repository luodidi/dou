package com.whut.bean;

/**
 * @author luodidi
 * @version 1.0
 * @date 2019/10/10 15:46
 */
public class User {
    private int id;                       // 用户id

    private String name;                  // 用户名

    private  int  roleId;                 // 角色名

    private int deptId;                     // 部门名

    private  String password;               // 用户密码

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
