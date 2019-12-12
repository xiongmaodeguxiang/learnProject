package com.xh.zl.controller;

import com.xh.zl.config.ServiceProperties;
import com.xh.zl.entity.SysRole;
import com.xh.zl.mapper.SysRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author:zl
 * @Description
 * @Date: 2019/12/11 11:17
 */
@RestController

public class TestController {
    @Autowired
    ServiceProperties serviceProperties;

    @Autowired
    SysRoleMapper sysRoleMapper;

    @RequestMapping("/list")
    public Object getRoles(){
        List<SysRole> sysRoles = sysRoleMapper.selectList();
        System.out.println(sysRoles.size());
        return sysRoles;
    }
    @RequestMapping("/list1")
    public Object getRoles1(){
        List<SysRole> sysRoles = sysRoleMapper.selectList1();
        System.out.println(sysRoles.size());
        return sysRoles;
    }
    @RequestMapping("/config")
    public Object config(){
        return serviceProperties.toString();
    }

}
