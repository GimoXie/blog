package com.gimo.blog.web.service;

import java.util.List;

import com.gimo.blog.core.generic.GenericService;
import com.gimo.blog.web.model.Permission;

/**
 * 权限 业务接口
 * 
 * @author Gimo
 * @since 2016年7月17日 下午1:02:55
 **/
public interface PermissionService extends GenericService<Permission, Long> {

    /**
     * 通过角色id 查询角色 拥有的权限
     * 
     * @param roleId
     * @return
     */
    List<Permission> selectPermissionsByRoleId(Long roleId);

}
