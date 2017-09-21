package com.gimo.blog.web.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gimo.blog.core.feature.orm.mybatis.Page;
import com.gimo.blog.web.lucene.BlogIndex;
import com.gimo.blog.web.model.Blog;
import com.gimo.blog.web.model.Comment;
import com.gimo.blog.web.service.BlogService;
import com.gimo.blog.web.service.CommentService;
import com.gimo.blog.web.util.PagerUtil;

/**
 * 博文控制器
 * 
 * @author GimoXie
 *
 */
@Controller
@RequestMapping("/articles")
public class BlogController {

	private final static Logger logger = LoggerFactory.getLogger(BlogController.class);

	@Resource
	private BlogService blogService;

	@Resource
	private CommentService commentService;

	private BlogIndex blogIndex = new BlogIndex();

	/**
	 * 博文详细信息
	 * 
	 * @param id
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/{id}")
	public ModelAndView details(@PathVariable("id") Long id, HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		// 通过id查询指定博客信息
		Blog blog = blogService.selectById(id);
		
		HttpSession session = request.getSession();
		if(blog != null){
			// 点击量增加1
			@SuppressWarnings("unchecked")
			List<Long> visitList = (List<Long>) session.getAttribute("visitList");
			if (visitList == null || !visitList.contains(id)) {
				blog.setClickHit(blog.getClickHit() + 1);
				blogService.update(blog);
				if (visitList == null) {
					visitList = new ArrayList<Long>();
					visitList.add(id);
				} else {
					visitList.add(id);
				}
				session.setAttribute("visitList", visitList);
			}
			// 评论的查询条件
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("blogId", blog.getId());
			map.put("state", 1);
			
			
			mav.addObject("blog", blog);
			mav.addObject("keywords", Arrays.asList(blog.getKeyWord().split(",")));
			mav.addObject("pageTitle", "博客详情:" + blog.getTitle());
			mav.addObject("mainPage", "/WEB-INF/views/blog/blogView.jsp");
			mav.addObject("pageCode", PagerUtil.getUpAndDownPageCode(blogService.getLastBlog(id),
					blogService.getNextBlog(id), request.getServletContext().getContextPath()));
			mav.addObject("commentList", commentService.queryAll(new Page<Comment>(true), map));
			mav.setViewName("blog/index");
		}else{
			mav.addObject("mainPage", "/WEB-INF/views/404.jsp");
			mav.setViewName("blog/index");
		}
		return mav;
	}

	/**
	 * 根据关键字查询相关博客信息
	 * 
	 * @param q
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/search")
	public ModelAndView search(@RequestParam(value = "q", required = false) String q,
			@RequestParam(value = "page", required = false) String page, HttpServletRequest request) throws Exception {
		int pageSize = 10;// 每页显示的数量
		if (StringUtils.isEmpty(page)) {
			page = "1";
		}
		List<Blog> blogList = blogIndex.searchBlog(q);
		Integer toIndex = blogList.size() >= Integer.parseInt(page) * pageSize ? Integer.parseInt(page) * pageSize
				: blogList.size();
		ModelAndView mav = new ModelAndView();
		mav.addObject("pageTitle", "搜索关键字'" + q + "'结果页面");
		mav.addObject("mainPage", "/WEB-INF/views/blog/searchResult.jsp");
		mav.addObject("blogList", blogList.subList((Integer.parseInt(page) - 1) * pageSize, toIndex));
		mav.addObject("pageCode", PagerUtil.genUpAndDownPageCode(Integer.parseInt(page), blogList.size(), q, pageSize,
				request.getServletContext().getContextPath()));
		mav.addObject("q", q);
		mav.addObject("resultTotal", blogList.size());
		mav.setViewName("blog/index");
		return mav;
	}

}
