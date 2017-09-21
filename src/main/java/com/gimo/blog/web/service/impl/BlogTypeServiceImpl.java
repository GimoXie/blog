package com.gimo.blog.web.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gimo.blog.core.feature.orm.mybatis.Page;
import com.gimo.blog.core.generic.GenericDao;
import com.gimo.blog.core.generic.GenericServiceImpl;
import com.gimo.blog.web.dao.BlogTypeDao;
import com.gimo.blog.web.model.BlogType;
import com.gimo.blog.web.model.Comment;
import com.gimo.blog.web.service.BlogTypeService;

/**
 * 博客类型实现类
 * @author GimoXie
 *
 */
@Service("blogTypeService")
public class BlogTypeServiceImpl extends GenericServiceImpl<BlogType, Long> implements BlogTypeService{
	
	@Resource
	BlogTypeDao blogTypeDao;
	
	@Override
	public int insert(BlogType model) {
		return blogTypeDao.insertSelective(model);
	}

	@Override
	public int update(BlogType model) {
		return blogTypeDao.updateByPrimaryKeySelective(model);
	}

	@Override
	public int delete(Long id) {
		return blogTypeDao.deleteByPrimaryKey(id);
	}

	/**
	 * 根据id查询博客类型
	 */
	@Override
	public BlogType selectById(Long id) {
		return blogTypeDao.selectByPrimaryKey(id);
	}
	
	@Override
    public List<BlogType> selectList() {
        return blogTypeDao.selectList();
    }

	@Override
	public List<BlogType> queryAll(Page<BlogType> pager, Map<String, Object> map) {
		return blogTypeDao.queryAll(pager, map);
	}
	
	@Override
	public Long getCount(Map<String, Object> map) {
		return blogTypeDao.getCount(map);
	}

	@Override
	public GenericDao<BlogType, Long> getDao() {
		return blogTypeDao;
	}
}
