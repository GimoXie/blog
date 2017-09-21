<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<link href="${pageContext.request.contextPath}/static/assets/plugins/bootstrap-tagsinput/dist/bootstrap-tagsinput.css" rel="stylesheet" type="text/css" />

<div class="row writeBlog">
	<!-- 条件区域 -->
	<div class="col-md-4">
		<div class="input-group">
            <span class="input-group-addon">博客标题</span>
            <input type="text" class="form-control" id="blog_title" placeholder="请输入博客标题...">
        </div>
	</div>
	<div class="col-md-4">
		<div class="input-group">
            <span class="input-group-addon">博客类别</span>
            <select class="blogType-select" id="blog_type">
			  <c:forEach var="blogTypeCount" items="${blogTypeCountList}">
			    <option value="${blogTypeCount.id}">${blogTypeCount.typeName}</option>
			  </c:forEach>
			</select>
        </div>
	</div>
	<div class="col-md-4">
		<div class="input-group">
            <span class="input-group-addon">关键字</span>
            <input type="text" class="form-control" id="blog_keywords" data-role="tagsinput">
        </div>
	</div>
	<!-- ueditor区域 -->
	<div class="col-md-12 editor-container" id="editor-container">
		<span class="input-group-addon">博客内容</span>
	</div>

	<!-- 按钮区域 -->
	<div class="col-md-4 pull-right blog-publish-btn">
  		<button class="btn btn-primary" type="button" onclick="publish()">发布 <i class="fa fa-check"></i></button>
		<button class="btn " id="reset" type="button" onclick="reset()">重置 <i class="fa fa-undo"></i></button>
	</div>
</div>


<script src="${pageContext.request.contextPath}/static/assets/plugins/bootstrap-tagsinput/lib/angular/angular.min.js" type="text/javascript" ></script>
<script src="${pageContext.request.contextPath}/static/assets/plugins/bootstrap-tagsinput/dist/bootstrap-tagsinput.min.js" type="text/javascript" ></script>
<script src="${pageContext.request.contextPath}/static/assets/plugins/bootstrap-tagsinput/dist/bootstrap-tagsinput-angular.min.js" type="text/javascript" ></script>

<script src="${pageContext.request.contextPath}/static/app/js/writeBlog.js" type="text/javascript" ></script>
