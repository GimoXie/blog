package com.gimo.blog.web.controller.admin;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gimo.blog.web.model.Blog;
import com.gimo.blog.web.service.BlogService;

/**
 * 管理页面跳转控制器
 * @author GimoXie
 *
 */
@Controller
@RequestMapping("/admin")
public class AdminPageController {
	
	private final static Logger logger = LoggerFactory.getLogger(AdminPageController.class);
	
	@Resource
	private BlogService blogService;
	
	/**
	 * 拦截login.aspx请求，跳转到管理登陆界面
	 * @return
	 */
	@RequestMapping("/toLogin")
	public String toLogin() {
		return "/admin/login";
	}
	
	/**
	 * 身份验证通过后，跳转到管理主页
	 * @return
	 */
	@RequestMapping("/toIndex")
	public String toIndex() {
		return "admin/index";
	}
	
	/**
	 * 跳转到dashboard.jsp
	 * @return
	 */
	@RequestMapping("/toDashboard")
	public String toDashboard() {
		return "admin/dashboard";
	}
	
	/**
	 * 跳转到writeBlog.jsp
	 * @return
	 */
	@RequestMapping("/toWriteBlog")
	public String toWriteBlog() {
		return "admin/writeBlog";
	}
	
	/**
	 * 跳转到manageBlog.jsp
	 * @return
	 */
	@RequestMapping("/toManageBlog")
	public String toManageBlog() {
		return "admin/manageBlog";
	}

	/**
	 * 跳转到manageComment.jsp
	 * @return
	 */
	@RequestMapping("/toManageComment")
	public String toManageComment() {
		return "admin/manageComment";
	}
	
	/**
	 * 跳转到manageBlogType.jsp
	 */
	@RequestMapping("/toManageBlogType")
	public String toManageBlogType() {
		return "admin/manageBlogType";
	}
	
	/**
	 * 跳转到manageLink.jsp
	 */
	@RequestMapping("/toManageLink")
	public String toManageLink() {
		return "admin/manageLink";
	}
	
	/**
	 * 跳转到manageLink.jsp
	 */
	@RequestMapping("/toModifyBloggerInfo")
	public String toModifyBloggerInfo() {
		return "admin/modifyBloggerInfo";
	}
	
	/**
	 * 跳转到modifyBlog.jsp
	 * @param id 博客的id
	 * @return 将查询到的博客返回给前端
	 */
	@RequestMapping("/toModifyBlog")
	public ModelAndView toModifyBlog(Long id) {
		ModelAndView mav = new ModelAndView();
		// 通过id查询指定博客信息
		Blog blog = blogService.selectById(id);
		mav.addObject("blog", blog);
		mav.setViewName("admin/modifyBlog");
		return mav;
	}
	
}
