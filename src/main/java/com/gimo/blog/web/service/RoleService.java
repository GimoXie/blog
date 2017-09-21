package com.gimo.blog.web.service;

import java.util.List;

import com.gimo.blog.core.generic.GenericService;
import com.gimo.blog.web.model.Role;

/**
 * 角色 业务接口
 * 
 * @author Gimo
 * @since 2016年7月17日 下午1:02:55
 **/
public interface RoleService extends GenericService<Role, Long> {
    /**
     * 通过用户id 查询用户 拥有的角色
     * 
     * @param userId
     * @return
     */
    List<Role> selectRolesByUserId(Long userId);
}
