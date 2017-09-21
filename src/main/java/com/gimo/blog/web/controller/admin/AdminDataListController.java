package com.gimo.blog.web.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gimo.blog.core.feature.orm.mybatis.Page;
import com.gimo.blog.web.model.Blog;
import com.gimo.blog.web.model.BlogType;
import com.gimo.blog.web.model.Comment;
import com.gimo.blog.web.model.Link;
import com.gimo.blog.web.service.BlogService;
import com.gimo.blog.web.service.BlogTypeService;
import com.gimo.blog.web.service.CommentService;
import com.gimo.blog.web.service.LinkService;

/**
 * 后台管理操作控制器
 * @author GimoXie
 *
 */
@Controller
@RequestMapping("/admin")
public class AdminDataListController {

	private final static Logger logger = LoggerFactory.getLogger(AdminDataListController.class);
	
	@Resource
	private BlogService blogService;
	
	@Resource
	private BlogTypeService blogTypeService;
	
	@Resource
	private CommentService commentService;
	
	@Resource 
	private LinkService linkService;

	@RequestMapping(value = "/blogList", method = RequestMethod.POST)
	public @ResponseBody Object blogList(@RequestBody Page<Blog> page) {
		// 查询参数
		Map<String, Object> params = new HashMap<String, Object>();
		// 结果参数
		Map<String, Object> response = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(page.getSearch())) {
			params.put("title", page.getSearch());
		}
		// 分页查询博客信息
		List<Blog> blogList = blogService.queryAll(page, params);
		Long count = blogService.getCount(params);
		response.put("total", count);
		response.put("rows", blogList);
		response.put("page", page.getPageNo());
		return response;
	}
	
	@RequestMapping(value = "/blogTypeList", method = RequestMethod.POST)
	public @ResponseBody Object blogTypeList(@RequestBody Page<BlogType> page) {
		// 查询参数
		Map<String, Object> params = new HashMap<String, Object>();
		// 结果参数
		Map<String, Object> response = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(page.getSearch())) {
			params.put("typeName", page.getSearch());
		}
		// 分页查询博客类型信息
		List<BlogType> blogTypeList = blogTypeService.queryAll(page, params);
		// 查询总数
		Long count = blogTypeService.getCount(params);
		response.put("total", count);
		response.put("rows", blogTypeList);
		response.put("page", page.getPageNo());
		return response;
	}
	

	@RequestMapping(value = "/commentList", method = RequestMethod.POST)
	public @ResponseBody Object commentList(@RequestBody Page<Comment> page) {
		// 查询参数
		Map<String, Object> params = new HashMap<String, Object>();
		// 结果参数
		Map<String, Object> response = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(page.getSearch())) {
			params.put("state", page.getSearch());
		}
		// 分页查询博客类型信息
		List<Comment> commentList = commentService.queryAll(page, params);
		// 查询总数
		Long count = commentService.getCount(params);
		response.put("total", count);
		response.put("rows", commentList);
		response.put("page", page.getPageNo());
		return response;
	}

	@RequestMapping(value = "/linkList", method = RequestMethod.POST)
	public @ResponseBody Object linkList(@RequestBody Page<Link> page) {
		// 查询参数
		Map<String, Object> params = new HashMap<String, Object>();
		// 结果参数
		Map<String, Object> response = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(page.getSearch())) {
			params.put("linkName", page.getSearch());
		}
		// 分页查询博客类型信息
		List<Link> linkList = linkService.queryAll(page, params);
		// 查询总数
		Long count = linkService.getCount(params);
		response.put("total", count);
		response.put("rows", linkList);
		response.put("page", page.getPageNo());
		return response;
	}
}
