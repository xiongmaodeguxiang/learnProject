package com.xh.zl.entity;

import java.io.Serializable;
import java.sql.Date;

/**
 * @Author:zl
 * @Description
 * @Date: 2019/12/11 11:14
 */
public class SysRole implements Serializable{

    private Long id;
    private String roleName;
    private String roleDesc;
    private Integer type;
    private Date cretetime;
    private Integer state;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getCretetime() {
        return cretetime;
    }

    public void setCretetime(Date cretetime) {
        this.cretetime = cretetime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
