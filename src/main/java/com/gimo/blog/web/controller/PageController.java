package com.gimo.blog.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gimo.blog.core.feature.orm.mybatis.Page;
import com.gimo.blog.web.model.Blog;
import com.gimo.blog.web.service.BlogService;
import com.gimo.blog.web.util.PagerUtil;

/**
 * 公共页面跳转控制器
 * 
 * @author GimoXie
 *
 */
@Controller
@RequestMapping("/")
public class PageController {
	
	private final static Logger logger = LoggerFactory.getLogger(PageController.class);

	@Resource
	private BlogService blogService;

	/**
	 * 拦截web.xml中配置的index.html请求,跳转到博客主页 跳转主页前，进行各种信息的加载(集成redis缓存)
	 * @return
	 */
	@RequestMapping("/index")
	public ModelAndView index(@RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "typeId", required = false) String typeId,
			@RequestParam(value = "releaseDateStr", required = false) String releaseDateStr, HttpServletRequest req) {
		page = StringUtils.isEmpty(page)?"1":page;
		Page<Blog> pager = new Page<Blog>(Integer.parseInt(page), 8);
		//封装查询条件
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("pager", pager);
		map.put("typeId", typeId);
		map.put("releaseDateStr", releaseDateStr);
		//分页查询博客信息
		List<Blog> blogList = blogService.queryAll(pager,map);
		//设置缩略图片
		blogService.setImageList(blogList);
		
		//翻页组件生成的参数
		StringBuffer param = new StringBuffer();
		if(!StringUtils.isEmpty(typeId))
			param.append("&typeId="+typeId);
		if(!StringUtils.isEmpty(releaseDateStr))
			param.append("&releaseDateStr="+releaseDateStr);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("blogList", blogList);
		mav.addObject("pageCode", PagerUtil.genPagination(req.getContextPath() + "/index.html", blogService.getCount(map), Integer.parseInt(page), 8, param.toString()));
		mav.addObject("pageTitle", "Welcome to my blog!");
		mav.addObject("mainPage", "/WEB-INF/views/blog/blogList.jsp");
		mav.setViewName("blog/index");
		return mav;
	}

	/**
	 * 拦截login.html请求，跳转到管理登陆界面
	 * @return
	 */
	@RequestMapping("/toLogin")
	public String toLogin() {
		return "login";
	}

	/**
	 * 拦截web.xml中配置的401.html请求 跳转到401.jsp
	 * @return
	 */
	@RequestMapping("/401")
	public String page401() {
		return "401";
	}

	/**
	 * 拦截web.xml中配置的404.html请求 跳转到404.jsp
	 * @return
	 */
	@RequestMapping("/404")
	public String page404() {
		return "404";
	}

	/**
	 * 拦截web.xml中配置的500.html请求 跳转到500.jsp
	 * @return
	 */
	@RequestMapping("/500")
	public String page500() {
		return "500";
	}
}
