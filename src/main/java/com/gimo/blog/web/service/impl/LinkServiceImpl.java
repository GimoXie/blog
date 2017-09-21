package com.gimo.blog.web.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gimo.blog.core.feature.orm.mybatis.Page;
import com.gimo.blog.core.generic.GenericDao;
import com.gimo.blog.core.generic.GenericServiceImpl;
import com.gimo.blog.web.dao.LinkDao;
import com.gimo.blog.web.model.Link;
import com.gimo.blog.web.service.LinkService;

/**
 * 友情链接业务操作实现类
 * @author GimoXie
 *
 */
@Service("linkService")
public class LinkServiceImpl extends GenericServiceImpl<Link, Long> implements LinkService {
	
	@Resource
	LinkDao linkDao;

	@Override
	public int insert(Link model) {
		return linkDao.insertSelective(model);
	}

	@Override
	public int update(Link model) {
		return linkDao.updateByPrimaryKeySelective(model);
	}

	@Override
	public int delete(Long id) {
		return linkDao.deleteByPrimaryKey(id);
	}

	@Override
	public Link selectById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Link selectOne() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Link> selectList() {
		return linkDao.selectList();
	}

	@Override
	public List<Link> queryAll(Page<Link> pager, Map<String, Object> map) {
		return linkDao.queryAll(pager, map);
	}

	@Override
	public Long getCount(Map<String, Object> map) {
		return linkDao.getCount(map);
	}

	@Override
	public GenericDao<Link, Long> getDao() {
		// TODO Auto-generated method stub
		return null;
	}

}
