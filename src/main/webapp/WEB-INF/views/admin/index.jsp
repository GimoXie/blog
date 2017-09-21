<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en" class="no-js">
    <!--<![endif]-->
    <!-- BEGIN HEAD -->
    <head>
        <base href="<%=basePath%>">
        <meta charset="utf-8" />
        <title>Blog后台</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta content="width=device-width, initial-scale=1.0" name="viewport" />
        <meta content="" name="description" />
        <meta content="" name="author" />
        <meta name="MobileOptimized" content="320">

        <!-- BEGIN GLOBAL MANDATORY STYLES -->
        <link href="${pageContext.request.contextPath}/static/assets/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
        <link href="${pageContext.request.contextPath}/static/assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <link href="${pageContext.request.contextPath}/static/assets/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css" />
        <link href="${pageContext.request.contextPath}/static/assets/plugins/select2/select2.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/static/assets/plugins/select2/select2.css" rel="stylesheet" type="text/css"/>
        <!-- END GLOBAL MANDATORY STYLES -->

        <!-- BEGIN THEME STYLES -->
        <link href="${pageContext.request.contextPath}/static/assets/css/style-metronic.css" rel="stylesheet" type="text/css" />
        <link href="${pageContext.request.contextPath}/static/assets/css/style.css" rel="stylesheet" type="text/css" />
        <link href="${pageContext.request.contextPath}/static/assets/css/style-responsive.css" rel="stylesheet" type="text/css" />
        <link href="${pageContext.request.contextPath}/static/assets/css/plugins.css" rel="stylesheet" type="text/css" />
        <link href="${pageContext.request.contextPath}/static/assets/css/pages/tasks.css" rel="stylesheet" type="text/css" />
        <link href="${pageContext.request.contextPath}/static/assets/css/themes/default.css" rel="stylesheet" type="text/css" id="style_color" />
        <link href="${pageContext.request.contextPath}/static/assets/plugins/bootstrap-table/dist/bootstrap-table.min.css" rel="stylesheet" type="text/css" />
        <link href="${pageContext.request.contextPath}/static/assets/plugins/bootstrap-dialog/dist/css/bootstrap-dialog.min.css" rel="stylesheet" type="text/css" />
        <%-- <link href="${pageContext.request.contextPath}/static/assets/plugins/bootstrap-tagsinput/dist/bootstrap-tagsinput.css" rel="stylesheet" type="text/css" /> --%>
        <!-- END THEME STYLES -->

		<!-- BEGIN UEDITOR STYLES -->
		<link href="${pageContext.request.contextPath}/static/assets/plugins/ueditor/themes/default/css/ueditor.min.css" rel="stylesheet" type="text/css" />
		<!-- END UEDITOR STYLES -->

		<link href="${pageContext.request.contextPath}/static/app/css/admin.css" rel="stylesheet" type="text/css" />

        <link rel="shortcut icon" href="${pageContext.request.contextPath}/static/app/img/favicon.ico" />
    </head>
    <!-- END HEAD -->

    <!-- BEGIN BODY -->
    <body class="page-header-fixed">
        <!-- BEGIN HEADER -->
        <div class="header navbar navbar-inverse navbar-fixed-top">
            <!-- BEGIN TOP NAVIGATION BAR -->
            <div class="header-inner">
                <!-- BEGIN LOGO -->
                <a class="navbar-brand" href="javascript:;">
                    <img src="${pageContext.request.contextPath}/static/assets/img/logo.png" alt="logo" class="img-responsive" />
                </a>
                <!-- END LOGO -->
                <!-- BEGIN RESPONSIVE MENU TOGGLER -->
                <a href="javascript:;" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <img src="${pageContext.request.contextPath}/static/assets/img/menu-toggler.png" alt="" />
                </a>
                <!-- END RESPONSIVE MENU TOGGLER -->
                <!-- BEGIN TOP NAVIGATION MENU -->
                <ul class="nav navbar-nav pull-right">
                    <li class="dropdown user">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
                            <img alt="" src="${pageContext.request.contextPath}/static/assets/img/avatar1_small.jpg"/>
                            <span class="username"> ${userInfo.nickName} </span>
                            <i class="fa fa-angle-down"></i>
                        </a>
                        <ul class="dropdown-menu">
                            <li>
                                <a href="javascript:;" id="trigger_fullscreen">
                                    <i class="fa fa-move"></i> 全屏
                                </a>
                            </li>
                            <li>
                                <a href="extra_lock.html">
                                    <i class="fa fa-lock"></i> 锁屏
                                </a>
                            </li>
                            <li>
                                <a href="admin/logout.aspx">
                                    <i class="fa fa-key"></i> 退出
                                </a>
                            </li>
                        </ul>
                    </li>
                    <!-- END USER LOGIN DROPDOWN -->
                </ul>
                <!-- END TOP NAVIGATION MENU -->
            </div>
            <!-- END TOP NAVIGATION BAR -->
        </div>
        <!-- END HEADER -->
        <div class="clearfix"></div>
        <!-- BEGIN CONTAINER -->
        <div class="page-container">
            <!-- BEGIN SIDEBAR -->
            <div class="page-sidebar-wrapper">
                <div class="page-sidebar navbar-collapse collapse">
                    <!-- BEGIN SIDEBAR MENU -->
                    <ul class="page-sidebar-menu" id="page-sidebar-menu">
                        <li class="sidebar-toggler-wrapper">
                            <!-- BEGIN SIDEBAR TOGGLER BUTTON -->
                            <div class="sidebar-toggler hidden-phone"></div>
                            <!-- BEGIN SIDEBAR TOGGLER BUTTON -->
                        </li>

                        <li class="start active">
                            <a href="admin/toDashboard.aspx" id="btn-dashboard">
                                <i class="fa fa-home"></i><span class="title"> 首页 </span><span class="selected"></span>
                            </a>
                        </li>

                        <li class="">
                            <a href="javascript:;">
                                <i class="fa fa-file-text"></i><span class="title"> 博客管理 </span><span class="arrow "></span>
                            </a>
                            <ul class="sub-menu">
                            	<li>
                                    <a href="admin/toWriteBlog.aspx">
                                                                                                             发布博客
                                    </a>
                                </li>
                                <li>
                                    <a href="admin/toManageBlog.aspx">
                                                                                                             博文管理
                                    </a>
                                </li>
                                <li>
                                    <a href="admin/toManageBlogType.aspx">
                                                                                                            类别管理
                                    </a>
                                </li>
                                <li>
                                    <a href="admin/toManageComment.aspx">
                                                                                                             评论管理
                                    </a>
                                </li>
                                <li>
                                    <a href="admin/toManageLink.aspx">
                                                                                                              友情链接
                                    </a>
                                </li>
                            </ul>
                        </li>

                        <li class="">
                            <a href="javascript:;">
                                <i class="fa fa-user"></i><span class="title"> 个人中心 </span><span class="arrow "></span>
                            </a>
                            <ul class="sub-menu">
                                <li>
                                    <a href="admin/toModifyBloggerInfo.aspx">
                                                                                                             信息修改
                                    </a>
                                </li>
                                <li>
                                    <!-- <a href="admin/toModifyPassword.aspx"> -->
                                    <a href="javascript:modifyPassword();">
                                                                                                             密码修改
                                    </a>
                                </li>
                                
                                <!-- 测试权限控制 -->
                                <shiro:hasAnyRoles name="super_admin">
                                    <li>
                                        <a href="javascript:;">super_admin 拥有此角色</a>
                                    </li>
                                </shiro:hasAnyRoles>
                                
                                <shiro:hasPermission name="user:create">
                                    <li>
                                        <a href="javascript:;">user:create 拥有此权限</a>
                                    </li>
                                </shiro:hasPermission>
                                
                                <shiro:hasPermission name="user:update">
                                    <li>
                                        <a href="javascript:;">user:update 拥有此权限</a>
                                    </li>
                                </shiro:hasPermission>
     
                            </ul>
                        </li>
						
						<li class="">
							<a href="javascript:;">
                                <i class="fa fa-gears"></i><span class="title"> 系统管理 </span><span class="arrow "></span>
                            </a>
                            <ul class="sub-menu">
                            	<li>
                                    <!-- <a href="admin/toRefreshCache.aspx"> -->
                                    <a href="javascript:refreshCache();">
                                                                                                             刷新缓存
                                    </a>
                                </li>
                            </ul>
						</li>
                    </ul>
                    <!-- END SIDEBAR MENU -->
                </div>
            </div>
            <!-- END SIDEBAR -->
            <!-- BEGIN CONTENT -->
            <div class="page-content-wrapper">
                <div class="page-content">
                    <!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->
                    <!-- dialogs -->
					<div class="modal fade" tabindex="-1" role="dialog" aria-labelledby="deleteBlogLabel" aria-hidden="true" data-backdrop="static">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" data-dismiss="modal" aria-hidden="true" class="close">&times;</button>
									<h4 id="deleteBlogLabel" class="modal-title" Style="color:red;">Warning<i class="fa fa-warning"></i></h4>
								</div>
								<div class="modal-body" id="delete-modal-body">
								</div>
								<div class="modal-footer">
									<button type="button" data-dismiss="modal" class="btn btn-cancel">关闭<i class="fa fa-close"></i></button>
									<button type="button" data-dismiss="modal" class="btn btn-primary">确认<i class="fa fa-check"></i></button>
								</div>
							</div>
						</div>
					</div>
                    <!-- /.modal -->
                    <!-- END SAMPLE PORTLET CONFIGURATION MODAL FORM-->
                    <!-- BEGIN STYLE CUSTOMIZER -->
                    <div class="theme-panel hidden-xs hidden-sm">
                        <div class="toggler"></div>
                        <div class="toggler-close"></div>
                        <div class="theme-options">
                            <div class="theme-option theme-colors clearfix">
                                <span> 主题颜色 </span>
                                <ul>
                                    <li class="color-black current color-default" data-style="default"></li>
                                    <li class="color-blue" data-style="blue"></li>
                                    <li class="color-brown" data-style="brown"></li>
                                    <li class="color-purple" data-style="purple"></li>
                                    <li class="color-grey" data-style="grey"></li>
                                    <li class="color-white color-light" data-style="light"></li>
                                </ul>
                            </div>
                            <div class="theme-option">
                                <span> 布局 </span>
                                <select class="layout-option form-control input-small">
                                    <option value="fluid">顺序</option>
                                    <option value="boxed">盒状</option>
                                </select>
                            </div>
                            <div class="theme-option">
                                <span> 标题 </span>
                                <select class="header-option form-control input-small">
                                    <option value="fixed">固定</option>
                                    <option value="default">默认</option>
                                </select>
                            </div>
                            <div class="theme-option">
                                <span> 工具栏 </span>
                                <select class="sidebar-option form-control input-small">
                                    <option value="fixed">固定</option>
                                    <option value="default">默认</option>
                                </select>
                            </div>
                            <div class="theme-option">
                                <span> 工具栏位置 </span>
                                <select class="sidebar-pos-option form-control input-small">
                                    <option value="left">左边</option>
                                    <option value="right">右边</option>
                                </select>
                            </div>
                            <div class="theme-option">
                                <span> 页脚 </span>
                                <select class="footer-option form-control input-small">
                                    <option value="fixed">固定</option>
                                    <option value="default">默认</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <!-- END STYLE CUSTOMIZER -->

                    <!-- BEGIN PAGE HEADER-->
                    <div class="row">
                        <div class="col-md-12">
                            <!-- BEGIN PAGE TITLE & BREADCRUMB-->
                            <h3 class="page-title" id="index-page-title">Admin Dashboard</h3>
                            <ul class="page-breadcrumb breadcrumb">
                                <li>
                                    <i class="fa fa-home" id="mainTagI"></i>
                                    <a href="javascript:;" id="mainTag">
                                                                                                              首页
                                    </a>
                                    <i class="fa fa-angle-right"></i>
                                </li>
                                <li>
                                    <a href="javascript:;" id="subTag">
                                        Dashboard
                                    </a>
                                </li>
                            </ul>
                            <!-- END PAGE TITLE & BREADCRUMB-->
                        </div>
                    </div>
                    <!-- END PAGE HEADER-->

                    <!-- BEGIN DASHBOARD STATS -->
                    <div id="main-content"></div>

                    <!-- END PORTLET-->
                </div>
            </div>
            <!-- END CONTENT -->
        </div>
        <!-- END CONTAINER -->
        <!-- BEGIN FOOTER -->
        <div class="footer">
            <div class="footer-inner">
                2016 &copy; Blog By Gimo.
            </div>
            <div class="footer-tools">
                <span class="go-top"><i class="fa fa-angle-up"></i></span>
            </div>
        </div>
        <!--[if lt IE 9]>
        <script src="${pageContext.request.contextPath}/static/assets/plugins/respond.min.js"></script>
        <script src="assets/plugins/excanvas.min.js"></script>
        <![endif]-->
        <script src="${pageContext.request.contextPath}/static/assets/plugins/jquery/jquery-1.11.1.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/static/assets/plugins/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/static/assets/plugins/jquery-ui/jquery-ui-1.10.3.custom.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/static/assets/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/static/assets/plugins/bootstrap-hover-dropdown/twitter-bootstrap-hover-dropdown.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/static/assets/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/static/assets/plugins/jquery.blockui.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/static/assets/plugins/jquery.cokie.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/static/assets/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>
		<!-- ueditor -->
		<script src="${pageContext.request.contextPath}/static/assets/plugins/ueditor/ueditor.config.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/static/assets/plugins/ueditor/ueditor.all.min.js" type="text/javascript"></script>

        <script src="${pageContext.request.contextPath}/static/assets/plugins/jquery-validation/dist/jquery.validate.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/static/assets/plugins/select2/select2.min.js" type="text/javascript" ></script>
        <script src="${pageContext.request.contextPath}/static/assets/plugins/bootstrap-table/dist/bootstrap-table.min.js" type="text/javascript" ></script>
        <script src="${pageContext.request.contextPath}/static/assets/plugins/bootstrap-table/src/locale/bootstrap-table-zh-CN.js" type="text/javascript" ></script>
        <script src="${pageContext.request.contextPath}/static/assets/plugins/bootstrap-dialog/dist/js/bootstrap-dialog.min.js" type="text/javascript" ></script>
        <%-- <script src="${pageContext.request.contextPath}/static/assets/plugins/bootstrap-tagsinput/lib/angular/angular.js" type="text/javascript" ></script>
        <script src="${pageContext.request.contextPath}/static/assets/plugins/bootstrap-tagsinput/dist/bootstrap-tagsinput.js" type="text/javascript" ></script>
        <script src="${pageContext.request.contextPath}/static/assets/plugins/bootstrap-tagsinput/dist/bootstrap-tagsinput-angular.js" type="text/javascript" ></script> --%>
        
        <script src="${pageContext.request.contextPath}/static/assets/scripts/app.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/static/app/js/index.js" type="text/javascript" ></script>

		<script src="${pageContext.request.contextPath}/static/app/lib/security/sha256.min.js" type="text/javascript"></script>
        <!-- <script data-main="app/js/main" src="app/lib/requirejs/require.js"></script> -->
    </body>
</html>