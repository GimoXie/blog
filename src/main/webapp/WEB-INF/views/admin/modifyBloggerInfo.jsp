<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
this is modify blogger info page
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
    <button type="button" class="btn btn-default" title="删除">
        <i class="glyphicon glyphicon-trash"></i>
    </button>
</div>

<!-- dialogs -->
<div class="modal fade" id="deleteBlogModel" tabindex="-1" role="dialog" aria-labelledby="deleteBlogLabel" aria-hidden="true" data-backdrop="static">
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


<script type="text/javascript">
	$(document).ready(function(){
		// 先销毁表格 避免二次加载时无法加载的问题
		$("#blogList").bootstrapTable("destroy");
		// 生成table
		var tables = $("#blogList").bootstrapTable({
			url : 'admin/blogList.aspx',
	        method : 'post',
	        cache : false,// 是否使用缓存
	        pagination : true, // 显示分页组件  
			pageSize : 7,
	        pageList : [ 5, 10, 20 ],  
	        paginationPreText : "上一页",
			paginationNextText : "下一页",
	        sidePagination : "server", // 服务端处理分页  
	        //queryParams : queryParams,
	        buttonsAlign : 'left',
	        search : true,
	        searchAlign : 'left',
	        searchOnEnterKey : true,
	        toolbar : '#toolbar',// 指定工具栏
	        toolbarAlign : 'right',
	        showRefresh : true,//显示刷新
	        showColumns : true,// 显示隐藏列 
	        idField : "id", // 主键 
	        undefinedText : '-',
	        striped : true, // 是否显示行间隔色
			columns : [{
				title : '序号',
				field : '',
				checkbox : true,
				sortable : false,
				valign : 'middle',
				align : 'center',
				formatter : function(value, row, index) {
					return index + 1;
				}
			},{
				title : 'ID',
				field : 'id',
				sortable : false,
				align : 'center',
				valign : 'middle'
			},{
				title : '标题',
				field : 'title',
				sortable : false,
				align : 'center',
				valign : 'middle'
			},{
				title : '发布日期',
				field : 'releaseDateStr',
				sortable : false,
				align : 'center',
				valign : 'middle'
			},{
				title : '博客类型',
				field : 'blogType.typeName',
				sortable : false,
				align : 'center',
				valign : 'middle'
			},{
				title : '操作',
				field :'opt',
				valign : 'middle',
				align : 'center',
				formatter : function(value, row, index) {
					var str = '';
					str += '<a onclick="toModifyBlog(\''
							+ index
							+ '\',\'modifyBlogModel\')" title="编辑"><span class="fa fa-pencil-square-o"/></a>&nbsp;&nbsp;';
					str += '<a style="color:red;" onclick="showDelDialog(\''
						+ index
						+ '\',\'deleteBlogModel\')" title="删除"><span class="fa fa-trash-o"/></a>&nbsp;&nbsp;';
					return str
				}
			}],  
	        onCheck:function(){  
	            //buttonControl('#empUserList','#edit','#delete');  
	        },  
	        onCheckAll:function(){  
	            //buttonControl('#empUserList','#edit','#delete');  
	        },  
	        onUncheckAll:function(){  
	           // buttonControl('#empUserList','#edit','#delete');  
	        },  
	        onUncheck:function(){  
	           // buttonControl('#empUserList','#edit','#delete');  
	        }  
		});
		$("#blogList").bootstrapTable('hideColumn', 'id');
	});
	
	// 弹出删除提示框
	function showDelDialog(index,dolName){
		var rows=$('#blogList').bootstrapTable('getData',true);
		var row = rows[index];
		$('#delete-modal-body').html('确定要删除<strong style=\"color:blue;\">\"'+row.title+'\"</strong>这篇文章吗?');
		$('#' + dolName).modal('show');
	}
	
	// 跳转到博客修改界面
	function toModifyBlog(index,dolName){
		var rows=$('#blogList').bootstrapTable('getData',true);
		var row = rows[index];
		$.get('admin/toModifyBlog.aspx?id='+row.id, function(data) {
            $('#main-content').html(data);
        });
	}
	
</script>