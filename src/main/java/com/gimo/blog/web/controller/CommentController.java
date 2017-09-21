package com.gimo.blog.web.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gimo.blog.web.model.Blog;
import com.gimo.blog.web.model.Comment;
import com.gimo.blog.web.service.BlogService;
import com.gimo.blog.web.service.CommentService;
import com.gimo.blog.web.util.VerifyCodeUtils;

import net.sf.json.JSONObject;

/**
 * 评论信息控制器
 * @author GimoXie
 *
 */
@Controller
@RequestMapping("/comment")
public class CommentController {

	private final static Logger logger = LoggerFactory.getLogger(CommentController.class);

	@Resource
	private CommentService commentService;

	@Resource
	private BlogService blogService;

	/**
	 * 提交评论
	 * @param comment
	 * @param imageCode
	 * @param request
	 * @param response
	 * @param session
	 * @throws Exception
	 */
	@RequestMapping("/save")
	public void save(Comment comment, @RequestParam("imageCode") String imageCode, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		//從session中獲取驗證碼的字符串
		String sRand=(String) session.getAttribute("sRand");
		JSONObject result=new JSONObject();
		int resultTotal=0;
		if(!imageCode.equalsIgnoreCase(sRand)){
			result.put("success", false);
			result.put("errorInfo", "请填写正确的验证码!");
		}else{
			String userIp=request.getRemoteAddr(); 
			comment.setUserIp(userIp);
			if(comment.getId()==null){
				resultTotal=commentService.insert(comment);
				Blog blog=blogService.selectById(comment.getBlog().getId());
				blog.setReplyHit(blog.getReplyHit()+1);
				blogService.update(blog);
			}
		}
		if(resultTotal>0){
			result.put("success", true);
		}else{
			result.put("success", false);
			result.put("errorInfo", "评论提交失败，请稍后重试！");
		}
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		out.println(result.toString());
		out.flush();
		out.close();
	}

	/**
	 * 获取验证码
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/getVerifiCode")
	public void getVerifiCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
		/*response.setContentType("image/jpeg");

		String randomString = VerificationCode.getRandomString();
		request.getSession(true).setAttribute("sRand", randomString);

		int width = 88;
		int height = 28;
		Color color = VerificationCode.getRandomColor();
		Color reverse = VerificationCode.getReverseColor(color);

		BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = bi.createGraphics();

		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
		g.setColor(color);
		g.fillRect(0, 0, width, height);
		g.setColor(reverse);
		g.drawString(randomString, 18, 20);

		for (int i = 0, n = VerificationCode.random.nextInt(100); i < n; i++) {
			g.drawRect(VerificationCode.random.nextInt(width), VerificationCode.random.nextInt(height), 1, 1);
		}

		ServletOutputStream out = response.getOutputStream();
		ImageIO.write(bi, "JPEG", out);    
		out.flush();*/

		response.setHeader("Pragma", "No-cache"); 
		response.setHeader("Cache-Control", "no-cache"); 
		response.setDateHeader("Expires", 0); 
		response.setContentType("image/jpeg"); 

		//生成随机字串 
		String verifyCode = VerifyCodeUtils.generateVerifyCode(4); 
		//存入会话session 
		HttpSession session = request.getSession(true); 
		//删除以前的
		session.removeAttribute("sRand");
		session.setAttribute("sRand", verifyCode.toLowerCase()); 
		//生成图片 
		int w = 88, h = 28; 
		VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), verifyCode); 
	}
}
