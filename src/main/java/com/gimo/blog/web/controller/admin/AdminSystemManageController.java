package com.gimo.blog.web.controller.admin;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.gimo.blog.web.model.Blog;
import com.gimo.blog.web.model.BlogType;
import com.gimo.blog.web.model.Link;
import com.gimo.blog.web.service.BlogService;
import com.gimo.blog.web.service.BlogTypeService;
import com.gimo.blog.web.service.LinkService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/admin/systemManage")
public class AdminSystemManageController {
	

	@Resource
	private LinkService linkService;

	@Resource
	private BlogTypeService blogTypeService;

	@Resource
	private BlogService blogService;

	/**
	 * 刷新缓存
	 * @param request
	 * @return
	 */
	@RequestMapping("/refreshCache")
	public  @ResponseBody Object refreshCache(HttpServletRequest request){
		JSONObject result = new JSONObject();
		result.put("success", true);
		try{
			ServletContext application = RequestContextUtils.getWebApplicationContext(request).getServletContext();
			
			List<BlogType> blogTypeCountList = blogTypeService.selectList();
			List<Blog> blogCountList = blogService.selectList();
			List<Link> linkCountList = linkService.selectList();
			
			application.setAttribute("blogTypeCountList", blogTypeCountList);
			application.setAttribute("blogCountList", blogCountList);
			application.setAttribute("linkCountList", linkCountList);
		}catch(Exception e){
			result.put("success", false);
		}
		return result;
	}
}
