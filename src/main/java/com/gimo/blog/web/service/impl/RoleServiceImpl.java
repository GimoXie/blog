package com.gimo.blog.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gimo.blog.core.generic.GenericDao;
import com.gimo.blog.core.generic.GenericServiceImpl;
import com.gimo.blog.web.dao.RoleDao;
import com.gimo.blog.web.model.Role;
import com.gimo.blog.web.service.RoleService;

/**
 * 角色Service实现类
 *
 * @author Gimo
 * @since 2016年7月17日 下午1:02:55
 */
@Service
public class RoleServiceImpl extends GenericServiceImpl<Role, Long> implements RoleService {

    @Resource
    private RoleDao roleDao;

    @Override
    public GenericDao<Role, Long> getDao() {
        return roleDao;
    }

    @Override
    public List<Role> selectRolesByUserId(Long userId) {
        return roleDao.selectRolesByUserId(userId);
    }

}
