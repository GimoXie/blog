package com.gimo.blog.web.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.gimo.blog.core.feature.orm.mybatis.Page;
import com.gimo.blog.core.generic.GenericService;
import com.gimo.blog.web.model.Blog;

/**
 * 博客业务接口类
 * @author GimoXie
 *
 */
public interface BlogService extends GenericService<Blog, Long> {
	
   /**
    * 获取博客总数
    * @param map
    * @return
    */
   Long getCount(Map<String, Object> map);

   /**
    * 分页查询所有博客
    * @param pager
    * @param map
    * @return
    */
   List<Blog> queryAll(Page<Blog> pager, Map<String, Object> map);
   
   /**
    * 获取上一篇博客
    * @param id
    * @return
    */
   Blog getLastBlog(Long id);

   /**
    * 获取下一篇博客
    * @param id
    * @return
    */
   Blog getNextBlog(Long id);

   /**
    * 根据博客类型删除博客
    * @param parseLong
    * @return
    */
   Long deleteByBlogTypeId(long typeId);
   
   /**
    *  为每一篇博客设置缩略图
    */
   void setImageList(List<Blog> blogList); 
   
}
