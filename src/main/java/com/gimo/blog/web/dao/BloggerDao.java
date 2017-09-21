package com.gimo.blog.web.dao;

import com.gimo.blog.core.generic.GenericDao;
import com.gimo.blog.web.model.Blogger;

public interface BloggerDao extends GenericDao<Blogger, Long>{

	Blogger selectByPrimaryKey(String id);

}
