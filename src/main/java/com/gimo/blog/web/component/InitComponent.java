package com.gimo.blog.web.component;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.gimo.blog.web.model.Blog;
import com.gimo.blog.web.model.BlogType;
import com.gimo.blog.web.model.Link;
import com.gimo.blog.web.service.BlogService;
import com.gimo.blog.web.service.BlogTypeService;
import com.gimo.blog.web.service.LinkService;

/**
 * 初始化组件
 * 把博主信息 根据博客类别分类信息 根据日期归档分类信息 存放到application中，用以提高页面请求性能
 * @author GimoXie
 *
 */
@Component
public class InitComponent implements ServletContextListener,ApplicationContextAware{

	private final static Logger logger = LoggerFactory.getLogger(InitComponent.class);

	private static ApplicationContext applicationContext;

	/**
	 * 设置application上下文环境
	 */
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		InitComponent.applicationContext=applicationContext;
	}

	/**
	 * 容器初始化时的操作
	 */
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		ServletContext application=servletContextEvent.getServletContext();

		//BloggerService bloggerService=(BloggerService) applicationContext.getBean("bloggerService");
		BlogTypeService blogTypeService=(BlogTypeService) applicationContext.getBean("blogTypeService");
		BlogService blogService=(BlogService) applicationContext.getBean("blogService");
		LinkService linkService=(LinkService) applicationContext.getBean("linkService");

		List<BlogType> blogTypeCountList = blogTypeService.selectList();//countList(); // 查询博客类别以及博客的数量
		List<Blog> blogCountList = blogService.selectList();//countList();
		List<Link> linkCountList = linkService.selectList();//queryAll(new Page<Link>(true),null);

		application.setAttribute("blogTypeCountList", blogTypeCountList);
		application.setAttribute("blogCountList", blogCountList);
		application.setAttribute("linkCountList", linkCountList);
	}

	/**
	 * 容器销毁时的操作
	 */
	public void contextDestroyed(ServletContextEvent sce) {
		logger.info("context destroyed...");
	}

}
