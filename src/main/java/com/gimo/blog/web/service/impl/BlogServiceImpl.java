package com.gimo.blog.web.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.gimo.blog.core.feature.orm.mybatis.Page;
import com.gimo.blog.core.generic.GenericDao;
import com.gimo.blog.core.generic.GenericServiceImpl;
import com.gimo.blog.web.dao.BlogDao;
import com.gimo.blog.web.model.Blog;
import com.gimo.blog.web.service.BlogService;

/**
 * 博客业务实现类
 * 
 * @author GimoXie
 *
 */
@Service("blogService")
public class BlogServiceImpl extends GenericServiceImpl<Blog, Long> implements BlogService {

	@Resource
	private BlogDao blogDao;

	@Override
	public int insert(Blog model) {
		return blogDao.insertSelective(model);
	}

	/**
	 * 根据id更新博客
	 */
	@Override
	public int update(Blog model) {
		return blogDao.updateByPrimaryKeySelective(model);
	}

	@Override
	public int delete(Long id) {
		return blogDao.deleteByPrimaryKey(id);
	}

	/**
	 * 根据id查询博客
	 */
	@Override
	public Blog selectById(Long id) {
		return blogDao.selectByPrimaryKey(id);
	}

	@Override
	public Blog selectOne() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Blog> selectList() {
		return blogDao.selectList();
	}

	/**
	 * 根据条件获取博客记录数
	 */
	@Override
	public Long getCount(Map<String, Object> map) {
		return blogDao.getCount(map);
	}

	/**
	 * 条件查询所有记录
	 */
	@Override
	public List<Blog> queryAll(Page<Blog> pager, Map<String, Object> map) {
		return blogDao.queryAll(pager, map);
	}

	/**
	 * 获取上一篇博客
	 */
	@Override
	public Blog getLastBlog(Long id) {
		return blogDao.getLastBlog(id);
	}

	/**
	 * 获取下一篇博客
	 */
	@Override
	public Blog getNextBlog(Long id) {
		return blogDao.getNextBlog(id);
	}

	@Override
	public Long deleteByBlogTypeId(long typeId) {
		return blogDao.deleteByBlogTypeId(typeId);
	}


	@Override
	public void setImageList(List<Blog> blogList) {
		for (Blog blog : blogList) {
			List<String> imageList = blog.getImageList();
			String blogInfo = blog.getContent();
			Document doc = Jsoup.parse(blogInfo);
			Elements jpgs = doc.select("img[src~=(?i)\\.(png|jpe?g|gif|bmp).aspx]");
			for (int i = 0; i < jpgs.size(); i++) {
				Element jpg = jpgs.get(i);
				imageList.add(jpg.toString());
				if (i == 2)
					break;
			}
		}

	}
	
	

	@Override
	public GenericDao<Blog, Long> getDao() {
		return this.blogDao;
	}

	
}
