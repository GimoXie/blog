package com.gimo.blog.web.dao;

import java.util.List;

import com.gimo.blog.core.generic.GenericDao;
import com.gimo.blog.web.model.Permission;

/**
 * 权限 Dao 接口
 * 
 * @author Gimo
 * @since 2016年7月17日 下午1:02:55
 **/
public interface PermissionDao extends GenericDao<Permission, Long> {

    int deleteByPrimaryKey(Long id);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);

    /**
     * 通过角色id 查询角色 拥有的权限
     * 
     * @param roleId
     * @return
     */
    List<Permission> selectPermissionsByRoleId(Long roleId);
}