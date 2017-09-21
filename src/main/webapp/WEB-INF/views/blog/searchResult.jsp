<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="blog-search">
	<div class="blog-list-title">
		<img src="${pageContext.request.contextPath}/static/app/img/search_icon.png" /> 搜索&nbsp;<font color="red">${q }</font>&nbsp;的结果
		&nbsp;(总共搜索到&nbsp;${resultTotal }&nbsp;条记录)
	</div>
	<br>
	<ul>
		<c:choose>
			<c:when test="${blogList.size()==0 }">
				<div align="center" style="padding-top: 20px">未查询到结果，请换个关键字试试看！</div>
			</c:when>
			<c:otherwise>
				<c:forEach var="blog" items="${blogList}">
					<li>
						<%-- <span class="blog-post-title">
							<a href="${pageContext.request.contextPath}/articles/${blog.id }.html" target="_blank">${blog.title }</a>
						</span> --%>
						<a href="${pageContext.request.contextPath}/articles/${blog.id}.html">
					      	<h3 class="blog-post-title"><a href="${pageContext.request.contextPath}/articles/${blog.id}.html">${blog.title }</a></h3>
					    </a> 
						<span class="summary">摘要:${blog.content }...</span>
						<br>
						<span class="link">
							博客链接:<a href="${pageContext.request.contextPath}/articles/${blog.id }.html">${pageContext.request.contextPath}/articles/${blog.id }.html</a>
							&nbsp;&nbsp;&nbsp;&nbsp;
						</span>
						<br>
						<span class="blog-post-meta">发布日期：${blog.releaseDateStr }</span>
						<br>
					</li>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</ul>
	<br>
    <div class="search-pager">
		${pageCode }
	</div>
</div>

