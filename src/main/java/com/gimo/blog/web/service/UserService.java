package com.gimo.blog.web.service;

import com.gimo.blog.core.generic.GenericService;
import com.gimo.blog.web.model.User;

/**
 * 用户 业务 接口
 * 
 * @author Gimo
 * @since 2016年7月17日 下午1:02:55
 **/
public interface UserService extends GenericService<User, Long> {

    /**
     * 用户验证
     * 
     * @param user
     * @return
     */
    User authentication(User user);

    /**
     * 根据用户名查询用户
     * 
     * @param username
     * @return
     */
    User selectByUsername(String username);

    /**
     * 根据博主id查询账号信息
     * @param id
     * @return
     */
	User selectByBloggerId(Long id);
    
}
