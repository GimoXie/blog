package com.gimo.blog.web.controller.admin;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gimo.blog.web.model.BlogType;
import com.gimo.blog.web.service.BlogService;
import com.gimo.blog.web.service.BlogTypeService;

@Controller
@RequestMapping("/admin/blogType")
public class AdminBlogTypeManageController {
	
	@Autowired  
	HttpServletRequest request;

	@Resource
	private BlogService blogService;
	
	@Resource
	private BlogTypeService blogTypeService;
	
	@Resource
	private AdminSystemManageController adminSystemManageController;
	
	@RequestMapping(value = "/deleteByIds", method = RequestMethod.POST)
	public @ResponseBody Object deleteByIds(@RequestParam(value = "ids[]") String[] ids) {
		for (String id : ids) {
			blogService.deleteByBlogTypeId(Long.parseLong(id));
			blogTypeService.delete(Long.parseLong(id));
		}
		adminSystemManageController.refreshCache(request);
		return null;
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody Object save(BlogType blogType) {
		if(blogType.getId() == null)
			blogTypeService.insert(blogType);
		else
			blogTypeService.update(blogType);
		
		adminSystemManageController.refreshCache(request);
		return null;
	}
}
