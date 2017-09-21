package com.gimo.blog.web.dao;

import java.util.List;
import java.util.Map;

import com.gimo.blog.core.feature.orm.mybatis.Page;
import com.gimo.blog.core.generic.GenericDao;
import com.gimo.blog.web.model.Blog;

public interface BlogDao extends GenericDao<Blog, Long>{

	//获取博客总数
	Long getCount(Map<String, Object> map);

	//分页查询所有博客
	List<Blog> queryAll(Page<Blog> pager, Map<String, Object> map);

	//获取上一篇博客
	Blog getLastBlog(Long id);

	//获取下一篇博客
	Blog getNextBlog(Long id);

	List<Blog> selectList();

	Long deleteByBlogTypeId(long typeId);

}
