package com.gimo.blog.web.dao;

import java.util.List;

import com.gimo.blog.core.generic.GenericDao;
import com.gimo.blog.web.model.Role;

/**
 * 角色Dao 接口
 * 
 * @author Gimo
 * @since 2016年7月17日 下午1:02:55
 **/
public interface RoleDao extends GenericDao<Role, Long> {

    int deleteByPrimaryKey(Long id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    /**
     * 通过用户id 查询用户 拥有的角色
     * 
     * @param id
     * @return
     */
    List<Role> selectRolesByUserId(Long userId);
}