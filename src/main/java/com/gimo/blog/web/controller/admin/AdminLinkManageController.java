package com.gimo.blog.web.controller.admin;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gimo.blog.web.model.Link;
import com.gimo.blog.web.service.LinkService;

@Controller
@RequestMapping("/admin/link")
public class AdminLinkManageController {

	@Resource
	private LinkService linkService;
	
	@RequestMapping(value = "/deleteByIds", method = RequestMethod.POST)
	public @ResponseBody Object deleteByIds(@RequestParam(value = "ids[]") String[] ids) {
		for (String id : ids) {
			linkService.delete(Long.parseLong(id));
		}
		return null;
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody Object save(Link link) {
		if(link.getId() == null)
			linkService.insert(link);
		else
			linkService.update(link);
		return null;
	}
}
