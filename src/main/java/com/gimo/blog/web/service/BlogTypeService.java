package com.gimo.blog.web.service;

import java.util.List;
import java.util.Map;

import com.gimo.blog.core.feature.orm.mybatis.Page;
import com.gimo.blog.core.generic.GenericService;
import com.gimo.blog.web.model.BlogType;

/**
 * 博客类型业务操作接口类
 * 
 * @author GimoXie
 *
 */
public interface BlogTypeService extends GenericService<BlogType, Long> {

	/**
	 * 根据id查询博客类型
	 */
	BlogType selectById(Long id);

	/**
	 * 获取博客类别总数
	 * 
	 * @param map
	 * @return
	 */
	Long getCount(Map<String, Object> map);

	/**
	 * 分页查询所有博客类别
	 * 
	 * @param pager
	 * @param map
	 * @return
	 */
	List<BlogType> queryAll(Page<BlogType> pager, Map<String, Object> map);
}
