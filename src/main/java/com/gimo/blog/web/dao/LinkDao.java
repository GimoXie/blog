package com.gimo.blog.web.dao;

import java.util.List;
import java.util.Map;

import com.gimo.blog.core.feature.orm.mybatis.Page;
import com.gimo.blog.core.generic.GenericDao;
import com.gimo.blog.web.model.Link;

public interface LinkDao extends GenericDao<Link, Long>{

	List<Link> queryAll(Page<Link> pager, Map<String,Object> map);

	Long getCount(Map<String, Object> map);

	List<Link> selectList();

}
