<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="blog-post">

  <div class="blog-list-title">
	<img src="${pageContext.request.contextPath}/static/app/img/list_icon.png" /> 最新博客
  </div>
  
  <br>
  
  <ul>
  	<c:forEach var="blog" items="${blogList}">
  	  <li>
  	    <a href="${pageContext.request.contextPath}/articles/${blog.id}.html">
	      <h3 class="blog-post-title"><a href="${pageContext.request.contextPath}/articles/${blog.id}.html">${blog.title }</a></h3>
	    </a> 
  	    <span class="summary">摘要：${blog.summary }...</span> 
  	    <span class="img">
  	      <c:forEach var="image" items="${blog.imageList }">
		    <a href="${pageContext.request.contextPath}/articles/${blog.id}.html">${image }</a>
		    &nbsp;&nbsp;
	      </c:forEach>
  	    </span>
  	    <br>
  	    <span class="blog-post-meta">发表于 <fmt:formatDate value="${blog.releaseDate }" type="date" pattern="yyyy-MM-dd HH:mm" /> 阅读(${blog.clickHit}) 评论(${blog.replyHit})</span>
  	  </li>
  	  <hr/>
  	</c:forEach>
  </ul>

  <nav class="page">
    <ul class="pagination pagination-sm">
	  ${pageCode}
	</ul>
  </nav>
  
</div>
