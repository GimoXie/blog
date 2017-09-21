package com.gimo.blog.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gimo.blog.core.generic.GenericDao;
import com.gimo.blog.core.generic.GenericServiceImpl;
import com.gimo.blog.web.dao.PermissionDao;
import com.gimo.blog.web.model.Permission;
import com.gimo.blog.web.service.PermissionService;

/**
 * 权限Service实现类
 *
 * @author Gimo
 * @since 2016年7月17日 下午1:02:55
 */
@Service
public class PermissionServiceImpl extends GenericServiceImpl<Permission, Long> implements PermissionService {

    @Resource
    private PermissionDao permissionDao;


    @Override
    public GenericDao<Permission, Long> getDao() {
        return permissionDao;
    }

    @Override
    public List<Permission> selectPermissionsByRoleId(Long roleId) {
        return permissionDao.selectPermissionsByRoleId(roleId);
    }
}
