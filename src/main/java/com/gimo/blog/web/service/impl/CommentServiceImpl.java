package com.gimo.blog.web.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gimo.blog.core.feature.orm.mybatis.Page;
import com.gimo.blog.core.generic.GenericDao;
import com.gimo.blog.core.generic.GenericServiceImpl;
import com.gimo.blog.web.dao.CommentDao;
import com.gimo.blog.web.model.Comment;
import com.gimo.blog.web.service.CommentService;

/**
 * 评论实现类
 * @author GimoXie
 *
 */
@Service("commentService")
public class CommentServiceImpl extends GenericServiceImpl<Comment, Long> implements CommentService{
	
	@Resource
	CommentDao commentDao;

	@Override
	public int insert(Comment model) {
		return commentDao.insertSelective(model);
	}

	@Override
	public int update(Comment model) {
		return commentDao.updateByPrimaryKeySelective(model);
	}

	@Override
	public int delete(Long id) {
		return commentDao.deleteByPrimaryKey(id);
	}

	@Override
	public Comment selectById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comment selectOne() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Comment> selectList() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Comment> queryAll(Page<Comment> pager ,Map<String,Object> map) {
		return commentDao.queryAll(pager,map);
	}

	@Override
	public Long getCount(Map<String, Object> map) {
		return commentDao.getCount(map);
	}
	
	@Override
	public GenericDao<Comment, Long> getDao() {
		return commentDao;
	}


	
}
