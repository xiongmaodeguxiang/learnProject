package com.xh.zl.mapper;

import com.xh.zl.entity.SysRole;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author:zl
 * @Description
 * @Date: 2019/12/11 11:13
 */
@Repository
public interface SysRoleMapper  {
    List<SysRole> selectList();
    List<SysRole> selectList1();
}
