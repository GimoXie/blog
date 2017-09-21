package com.gimo.blog.web.dao;

import java.util.List;
import java.util.Map;

import com.gimo.blog.core.feature.orm.mybatis.Page;
import com.gimo.blog.core.generic.GenericDao;
import com.gimo.blog.web.model.BlogType;

public interface BlogTypeDao extends GenericDao<BlogType, Long>{

	BlogType selectByPrimaryKey(Long id);

	List<BlogType> selectList();
	
	Long getCount(Map<String, Object> map);
	
	List<BlogType> queryAll(Page<BlogType> pager, Map<String, Object> map);
}
