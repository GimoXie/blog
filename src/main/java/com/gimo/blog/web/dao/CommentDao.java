package com.gimo.blog.web.dao;

import java.util.List;
import java.util.Map;

import com.gimo.blog.core.feature.orm.mybatis.Page;
import com.gimo.blog.core.generic.GenericDao;
import com.gimo.blog.web.model.Comment;

public interface CommentDao extends GenericDao<Comment, Long>{

	List<Comment> queryAll(Page<Comment> pager, Map<String,Object> map);

	Long getCount(Map<String, Object> map);

}
