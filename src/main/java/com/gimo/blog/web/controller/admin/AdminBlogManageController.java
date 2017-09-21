package com.gimo.blog.web.controller.admin;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gimo.blog.web.lucene.BlogIndex;
import com.gimo.blog.web.model.Blog;
import com.gimo.blog.web.service.BlogService;

import net.sf.json.JSONObject;

/**
 * 博文管理操作控制器
 * @author GimoXie
 *
 */
@Controller
@RequestMapping("/admin/blog")
public class AdminBlogManageController {

	private final static Logger logger = LoggerFactory.getLogger(AdminBlogManageController.class);
	
	@Resource
	private BlogService blogService;
	
	private BlogIndex blogIndex=new BlogIndex();
	
	@RequestMapping(value = "/publish", method = RequestMethod.POST)
	public @ResponseBody Object publish(Blog blog) throws Exception {
		int resultCount = 0;
		JSONObject result = new JSONObject();
		try{
			if(blog.getId() == null){
				resultCount = blogService.insert(blog);
				blogIndex.addIndex(blog);
			}
			else{
				resultCount = blogService.update(blog);
				blogIndex.updateIndex(blog);
			}
		}catch(Exception e){
			logger.error("publish blog have some problems :" + e.toString());
			result.put("error", e.toString());
		}
		
		if(resultCount > 0)
			result.put("success", true);
		else
			result.put("success", false);
		
		return result;
	}
	
	@RequestMapping(value = "/deleteByIds", method = RequestMethod.POST)
	public @ResponseBody Object deleteByIds(@RequestParam(value = "ids[]") String[] ids) {
		JSONObject result = new JSONObject();
		try{
			for (String id : ids) {
				blogService.delete(Long.parseLong(id));
				blogIndex.deleteIndex(id);
			}
			result.put("success", true);
		}catch(Exception e){
			logger.error("delete blog have some problems :" + e.toString());
			result.put("success", false);
			result.put("error", e.toString());
		}
		return result;
	}
}
