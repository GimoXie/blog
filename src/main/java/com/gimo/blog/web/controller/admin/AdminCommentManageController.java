package com.gimo.blog.web.controller.admin;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gimo.blog.web.model.Comment;
import com.gimo.blog.web.service.CommentService;

@Controller
@RequestMapping("/admin/comment")
public class AdminCommentManageController {

	@Resource
	private CommentService commentService;
	
	@RequestMapping(value = "/deleteByIds", method = RequestMethod.POST)
	public @ResponseBody Object deleteByIds(@RequestParam(value = "ids[]") String[] ids) {
		for (String id : ids) {
			commentService.delete(Long.parseLong(id));
		}
		return null;
	}
	
	@RequestMapping(value = "/audit", method = RequestMethod.POST)
	public @ResponseBody Object audit(Comment comment) {
		commentService.update(comment);
		return null;
	}
}
