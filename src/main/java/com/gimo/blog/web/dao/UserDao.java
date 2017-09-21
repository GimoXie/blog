package com.gimo.blog.web.dao;

import org.apache.ibatis.annotations.Param;

import com.gimo.blog.core.generic.GenericDao;
import com.gimo.blog.web.model.User;

/**
 * 用户Dao接口
 * 
 * @author Gimo
 * @since 2016年7月17日 下午1:02:55
 **/
public interface UserDao extends GenericDao<User, Long> {

    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /**
     * 用户登录验证查询
     * 
     * @param record
     * @return
     */
    User authentication(@Param("record") User record);

	User selectByUsername(String username);

	User selectByBloggerId(Long id);


}