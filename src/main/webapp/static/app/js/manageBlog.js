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
				checkbox : true,
			},{
				title : '序号',
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
					str += '<a onclick="toModifyBlog(\'' + index + '\')" title="编辑"><span class="fa fa-pencil-square-o"/></a>&nbsp;&nbsp;';
					str += '<a style="color:red;" onclick="showDelDialog(\'' + index + '\')" title="删除"><span class="fa fa-trash-o"/></a>&nbsp;&nbsp;';
					return str
				}
			}]
		});
		$("#blogList").bootstrapTable('hideColumn', 'id');
	});
	
	// 弹出删除提示框
	function showDelDialog(index){
		var rows=$('#blogList').bootstrapTable('getData',true);
		var row = rows[index];
		BootstrapDialog.show({
			 	type:BootstrapDialog.TYPE_WARNING,
	            title: '提醒消息',
	            message: '确定要删除<strong style=\"color:red;\">\"'+row.title+'\"</strong>这篇文章吗?',
	            buttons: [{
		    		icon: 'fa fa-times',
	                label: '取消',
	                action: function(dialogItself){
	                    dialogItself.close();
	                }
		    	},{
		    		icon: 'fa fa-check',
	                label: '确定',
	                cssClass: 'btn-warning',
	                action: function(dialogItself){
	                	dialogItself.close();
	            		var ids = [];
	                    ids.push(row.id);
	            		$.ajax({
	                        type: "POST",
	                        url: "admin/blog/deleteByIds.aspx",
	                        data: {ids:ids},
	                        dataType: "json",
	                        success: function(data){
	                        		    BootstrapDialog.show({
	                        		        type:BootstrapDialog.TYPE_SUCCESS,
	                        		    	title: '提示',
	                        		    	message: '删除成功!',
	                        		    	buttons: [{
	                        		    		icon: 'fa fa-check',
	                        	                label: '确定',
	                        	                cssClass: 'btn-success',
	                        	                action: function(dialogItself){
	                        	                    dialogItself.close();
	                        	                }
	                        	            }]
	                        		    });
	                                    $('#blogList').bootstrapTable('refresh');
	                                 }
	                    });
	                }
	            }]
	        });
	}
	
	// 跳转到博客修改界面
	function toModifyBlog(index){
		var rows=$('#blogList').bootstrapTable('getData',true);
		var row = rows[index];
		$.get('admin/toModifyBlog.aspx?id='+row.id, function(data) {
            $('#main-content').html(data);
        });
	}
	
	// 删除选中行的数据
	function deleteByIds(){
		var blogs = $('#blogList').bootstrapTable('getSelections');
		if(blogs.length == 0){
			BootstrapDialog.show({
				type: BootstrapDialog.TYPE_INFO,				
	            title: '提醒消息',
	            message: '未选中任何数据！',
	            buttons: [{
	                label: '好的',
	                action: function(dialogItself){
	                    dialogItself.close();
	                }
		    	}]
	        });
			return;
		}
		BootstrapDialog.show({
	        type:BootstrapDialog.TYPE_WARNING,
	    	title: '提醒消息',
	    	message: '确认要删除选中的数据吗？',
	    	buttons: [{
	    		icon: 'fa fa-times',
                label: '取消',
                action: function(dialogItself){
                    dialogItself.close();
                }
	    	},{
	    		icon: 'fa fa-check',
                label: '确定',
                cssClass: 'btn-warning',
                action: function(dialogItself){
                	dialogItself.close();
            		var ids = [];
            		$.each(blogs, function (index, blog) {
                        ids.push(blog.id);
                    });
            		$.ajax({
                        type: "POST",
                        url: "admin/blog/deleteByIds.aspx",
                        data: {ids:ids},
                        dataType: "json",
                        success: function(data){
                        		    BootstrapDialog.show({
                        		        type:BootstrapDialog.TYPE_SUCCESS,
                        		    	title: '提醒消息',
                        		    	message: '删除成功！',
                        		    	buttons: [{
                        		    		icon: 'fa fa-check',
                        	                label: '确定',
                        	                cssClass: 'btn-success',
                        	                action: function(dialogItself){
                        	                    dialogItself.close();
                        	                }
                        	            }]
                        		    });
                                    $('#blogList').bootstrapTable('refresh');
                                 }
                    });
                }
            }]
	    });
		
	}