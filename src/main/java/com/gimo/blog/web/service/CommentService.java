package com.gimo.blog.web.service;

import java.util.List;
import java.util.Map;

import com.gimo.blog.core.feature.orm.mybatis.Page;
import com.gimo.blog.core.generic.GenericService;
import com.gimo.blog.web.model.Comment;

/**
 * 评论信息业务接口类
 * @author GimoXie
 *
 */
public interface CommentService extends GenericService<Comment, Long> {
	
	/**
	 * 条件查询查询所有评论
	 * @param map
	 * @return
	 */
	List<Comment> queryAll(Page<Comment> pager ,Map<String,Object> map);
	
	/**
	 * 条件获取评论数量
	 * @param map
	 * @return
	 */
	Long getCount(Map<String,Object> map);
}
