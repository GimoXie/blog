package com.gimo.blog.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gimo.blog.core.generic.GenericDao;
import com.gimo.blog.core.generic.GenericServiceImpl;
import com.gimo.blog.web.dao.BloggerDao;
import com.gimo.blog.web.model.Blogger;
import com.gimo.blog.web.service.BloggerService;

/**
 * 博主业务实现类
 * @author GimoXie
 *
 */
@Service("bloggerService")
public class BloggerServiceImpl extends GenericServiceImpl<Blogger, Long> implements BloggerService {
	
	@Resource
	private BloggerDao bloggerDao;
	
	@Override
	public int insert(Blogger model) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Blogger model) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * 根据id查询博客类型
	 */
	@Override
	public Blogger selectById(Long id){
		return bloggerDao.selectByPrimaryKey(id);
	}
	
	@Override
	public Blogger selectOne() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Blogger> selectList() {
		// TODO Auto-generated method stub
		return null;
	}

	/*@Override
	public List<Blogger> queryAll(Page<Blogger> pager, Map<String, Object> map) {
		return null;
	}
	
	@Override
	public Long getCount(Map<String, Object> map) {
		return null;
	}*/


	@Override
	public GenericDao<Blogger, Long> getDao() {
		return bloggerDao;
	}

}
