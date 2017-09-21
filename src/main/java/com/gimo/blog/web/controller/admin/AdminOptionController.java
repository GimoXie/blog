package com.gimo.blog.web.controller.admin;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gimo.blog.web.model.Blogger;
import com.gimo.blog.web.model.User;
import com.gimo.blog.web.service.BloggerService;
import com.gimo.blog.web.service.UserService;

import net.sf.json.JSONObject;

/**
 * 管理员基本操作控制器
 * @author GimoXie
 *
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminOptionController {
	
	private final static Logger logger = LoggerFactory.getLogger(AdminOptionController.class);
	
	@Resource
	private BloggerService bloggerService;

	@Resource
    private UserService userService;
	/**
	 * 用户登陆
	 * @param blogger 
	 * @param result  
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@Valid User user, BindingResult result, Model model, HttpServletRequest request) {
		try {
            Subject subject = SecurityUtils.getSubject();
            // 已登陆则 跳到首页
            if (subject.isAuthenticated()) {
                return "redirect:/admin/toIndex.aspx";
            }
            if (result.hasErrors()) {
                model.addAttribute("error", "参数错误！");
                return "admin/login";
            }
            // 身份验证
            subject.login(new UsernamePasswordToken(user.getUsername(), user.getPassword()));
            // 验证成功在Session中保存用户信息
            final User authUserInfo = userService.selectByUsername(user.getUsername());
            request.getSession().setAttribute("userInfo", authUserInfo.getBlogger());
        } catch (AuthenticationException e) {
            // 身份验证失败
            model.addAttribute("error", "用户名或密码错误 ！");
            return "admin/login";
        }
        return "redirect:/admin/toIndex.aspx";
    }
	
	/**
	 * 登出
	 * @param session
	 * @return
	 */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
    	//从会话中删除用户信息
        session.removeAttribute("userInfo");
        // 登出操作
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "admin/login";
    }
    
    @RequestMapping(value = "/modifyPassword")
    public @ResponseBody Object modifyPassword(@RequestBody JSONObject param ,HttpServletRequest request){
    	JSONObject result = new JSONObject();
    	result.put("result", false);
    	
    	String oldPassword = param.getString("oldPassword");
    	String newPassword = param.getString("newPassword");
    	String newPasswordConfirm = param.getString("newPasswordConfirm");
    	Blogger blogger = (Blogger) request.getSession().getAttribute("userInfo");
    	User user = userService.selectByBloggerId(blogger.getId());
    	
    	if(!newPassword.equals(newPasswordConfirm)){
    		result.put("result", false);
    		result.put("reason", "两次输入的新密码不一致，请重新输入！");
    		return result;
    	}
    	
    	if(!oldPassword.equals(user.getPassword())){
    		result.put("result", false);
    		result.put("reason", "密码验证不通过！");
    		return result;
    	}
    	
    	user.setPassword(newPassword);
    	int count = userService.update(user);
    	if(count >0){
    		result.put("result", true);
    	}
    	return result;
    }
}
