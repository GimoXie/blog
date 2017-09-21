package com.gimo.blog.web.service;

import com.gimo.blog.core.generic.GenericService;
import com.gimo.blog.web.model.Blogger;

/**
 * 博主业务接口类
 * @author GimoXie
 *
 */
public interface BloggerService extends GenericService<Blogger, Long>{

	/**
	 * 根据用户id查询博主信息
	 * @param userName
	 * @return
	 */
	Blogger selectById(Long id);
	
}
