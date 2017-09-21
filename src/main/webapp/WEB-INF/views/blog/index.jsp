<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/static/app/img/g_favicon.ico" />

    <title>${pageTitle }</title>

    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/static/assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/assets/plugins/bootstrap-dialog/dist/css/bootstrap-dialog.min.css" rel="stylesheet"/>
    
    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath}/static/app/css/blog.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="${pageContext.request.contextPath}/static/assets/scripts/ie-emulation-modes-warning.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>

  <body>

     <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">Gimo的博客</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
          <ul class="nav navbar-nav">
            <li class="active"><a href="${pageContext.request.contextPath}/index.html">首页</a></li>
            <li><a href="#about">咨讯</a></li>
            <li><a href="#contact">联系我</a></li>
          </ul>

          <form action="${pageContext.request.contextPath}/articles/search.html" class="navbar-form navbar-right" role="search" method="post" onsubmit="return checkData()">
            <div class="form-group">
              <input type="text" id="q" name="q" value="${q}" class="form-control" placeholder="请输入要查询的关键字...">
            </div>
            <button type="submit" class="btn btn-default">搜索</button>
          </form>
        </div><!--/.nav-collapse -->
      </div>
    </nav>

    <div class="container">

      <div class="blog-header">
        <h2 class="blog-title"><!-- 博客简介 --></h2>
        <p class="lead blog-description">知识分享，个人笔记本，欢迎大家来扯犊子。</p>
      </div>
	  <hr>
      <div class="row">

        <div class="col-sm-8 blog-main">
        
		  <!-- /.blog-post -->
          <jsp:include page="${mainPage}"/>

        </div><!-- /.blog-main -->

        <div class="col-sm-3 col-sm-offset-1 blog-sidebar">
          <div class="sidebar-module sidebar-module-inset">
            <h4>关于博主</h4>
            <p>博主是一只15届毕业的Java菜鸡，曾就职于某物流公司，目前在某旅游公司打杂。喜欢玩技术，打游戏，想联系我的请点击<a href="#">关于我</a></p>
            <p>(⊙﹏⊙) 才发现那个页面还没写 有时间再写吧。</p>
          </div>
          <div class="sidebar-module">
            <h4>按日期分类</h4>
            <ol class="list-unstyled">
              <c:forEach var="blogCount" items="${blogCountList }">
			    <li>
			      <a href="${pageContext.request.contextPath}/index.html?releaseDateStr=${blogCount.releaseDateStr }">${blogCount.releaseDateStr }(${blogCount.blogCount })</a>
			    </li>
			  </c:forEach>
            </ol>
          </div>
          <div class="sidebar-module">
            <h4>按类别分类</h4>
            <ol class="list-unstyled">
              <c:forEach var="blogTypeCount" items="${blogTypeCountList }">
			    <li>
			      <a href="${pageContext.request.contextPath}/index.html?typeId=${blogTypeCount.id }">${blogTypeCount.typeName }(${blogTypeCount.blogCount })</a>
			    </li>
			  </c:forEach>
            </ol>
          </div>
          <div class="sidebar-module">
            <h4>友情链接</h4>
            <ol class="list-unstyled">
              <c:forEach var="linkCount" items="${linkCountList }">
              	<li><a href="${linkCount.linkUrl }">${linkCount.linkName }</a></li>
              </c:forEach>
            </ol>
          </div>
        </div><!-- /.blog-sidebar -->

      </div><!-- /.row -->

    </div><!-- /.container -->

    <div class="blog-footer">
      <p>Powered by <a href="http://blog.gimo.com">gimo</a>&nbsp;&nbsp;Copyright © 2016-2017</p>
    </div>


	<!-- dialogs -->
	<div class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" data-dismiss="modal" aria-hidden="true" class="close">&times;</button>
				</div>
				<div class="modal-body">
				</div>
				<div class="modal-footer">
					<button type="button" data-dismiss="modal" class="btn btn-cancel">关闭<i class="fa fa-close"></i></button>
					<button type="button" data-dismiss="modal" class="btn btn-primary">确认<i class="fa fa-check"></i></button>
				</div>
			</div>
		</div>
	</div>

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="${pageContext.request.contextPath}/static/assets/plugins/jquery-1.10.2.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/assets/plugins/bootstrap/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/assets/plugins/bootstrap-dialog/dist/js/bootstrap-dialog.min.js" type="text/javascript" ></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="${pageContext.request.contextPath}/static/assets/scripts/ie10-viewport-bug-workaround.js"></script>
    <script src="${pageContext.request.contextPath}/static/app/js/blog.js"></script>
  </body>
</html>
