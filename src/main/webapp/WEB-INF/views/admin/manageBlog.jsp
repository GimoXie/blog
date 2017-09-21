<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="row">
	<div class="col-md-12">
		<div class="div-table">
			<table id="blogList" width="100%">
				<thead>
					<tr>
						<th>序号</th>
						<th>标题</th>
						<th>发布日期</th>
						<th>博客类型</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>		
		</div>
	</div>
</div>

<!-- table自定义工具条 -->
<div id="toolbar" class="btn-group">
    <button type="button" class="btn btn-default" title="删除" onclick="deleteByIds()">
        <i class="glyphicon glyphicon-trash"></i>
    </button>
</div>

<script src="${pageContext.request.contextPath}/static/app/js/manageBlog.js" type="text/javascript" ></script>
