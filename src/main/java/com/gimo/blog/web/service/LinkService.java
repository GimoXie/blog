package com.gimo.blog.web.service;

import java.util.List;
import java.util.Map;

import com.gimo.blog.core.feature.orm.mybatis.Page;
import com.gimo.blog.core.generic.GenericService;
import com.gimo.blog.web.model.Link;

/**
 * 友情链接业务操作接口类
 * @author GimoXie
 *
 */
public interface LinkService extends GenericService<Link, Long> {
	
	/**
	 * 条件查询查询所有友情链接
	 * @param map
	 * @return
	 */
	List<Link> queryAll(Page<Link> pager ,Map<String,Object> map);
	
	/**
	 * 条件获取友情链接数量
	 * @param map
	 * @return
	 */
	Long getCount(Map<String,Object> map);

}
