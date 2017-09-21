<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="row">
	<div class="col-md-12">
		<div class="div-table">
			<table id="linkList" width="100%">
				<thead>
					<tr>
						<th>序号</th>
						<th>链接名称</th>
						<th>链接地址</th>
						<th>排序序号</th>
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
	<button type="button" class="btn btn-default" title="新增" onclick="showSaveDialog(-1)">
        <i class="glyphicon glyphicon-plus"></i>
    </button>
    <button type="button" class="btn btn-default" title="删除" onclick="deleteByIds()">
        <i class="glyphicon glyphicon-trash"></i>
    </button>
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

<script src="${pageContext.request.contextPath}/static/app/js/manageLink.js" type="text/javascript" ></script>
