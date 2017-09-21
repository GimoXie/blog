$(document).ready(function(){
		// 先销毁表格 避免二次加载时无法加载的问题
		$("#commentList").bootstrapTable("destroy");
		// 生成table
		var tables = $("#commentList").bootstrapTable({
			url : 'admin/commentList.aspx',
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
	        escape : true, //防止XSS
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
				title : '博客标题',
				field : 'blog.title',
				sortable : false,
				align : 'center',
				valign : 'middle'
			},{
				title : '用户IP',
				field : 'userIp',
				sortable : false,
				align : 'center',
				valign : 'middle'
			},{
				title : '评论内容',
				field : 'content',
				sortable : false,
				align : 'center',
				valign : 'middle',
				formatter : function(value, row, index){
					/* 防止xss */
					/* var pattern = new RegExp("[%--`~!@#$^&*()=|{}':;',\\[\\].<>/?~！@#￥……&*（）——| {}【】‘；：”“'。，、？]");
					var str = ""; 
					for (var i = 0; i < value.length; i++) { 
					 str = str+value.substr(i, 1).replace(pattern, ''); 
					} */
					// value.replace(/</g,'&lt;').replace(/>/g,'&gt;').replace(/"/g, "&quot;").replace(/'/g, "&#039;");
					if(value != null && value.length>20){
						value = '<span class="" data-type="success" data-toggle="tooltip" data-placement="left" title="'+value+'">'+value.substring(0,20)+'.....</span>';
					}
					return value;
				}
			},{
				title : '评论日期',
				field : 'commentDateStr',
				sortable : false,
				align : 'center',
				valign : 'middle'
			},{
				title : '评论状态',
				field : 'state',
				sortable : false,
				align : 'center',
				valign : 'middle',
				formatter : function(value, row, index) {
					if(value==0){
						return "<span style=\"color:blue;\">待审核</span>";
					}else if(value==1){
						return "<span style=\"color:green;\">审核通过</span>";
					}else if(value==2){
						return "<span style=\"color:red;\">审核未通过</span>";
					}
				}
			},{
				title : '操作',
				field :'opt',
				valign : 'middle',
				align : 'center',
				formatter : function(value, row, index) {
					var str = '';
					str += '<button type="button" class="btn btn-primary btn-xs" onclick="audit(\''+row.id+'\',1)">通过</button>&nbsp;&nbsp;';
					str += '<button type="button" class="btn btn-warning btn-xs" onclick="audit(\''+row.id+'\',2)">不通过</button>&nbsp;&nbsp;';
					str += '<a style="color:red;" onclick="showDelDialog(\'' + index + '\')" title="删除"><span class="fa fa-trash-o"/></a>&nbsp;&nbsp;';
					return str
				}
			}]
		});
		$("#commentList").bootstrapTable('hideColumn', 'id');
	});
	
	//弹出审核窗口
	function audit(id,opt){
		$.ajax({
			type: "POST",
            url: "admin/comment/audit.aspx",
            data: {id:id,state:opt},
            dataType: "json",
            success: function(data){
    		    BootstrapDialog.show({
    		        type:BootstrapDialog.TYPE_SUCCESS,
    		    	title: '提示',
    		    	message: '审核成功!',
    		    	buttons: [{
    		    		icon: 'fa fa-check',
    	                label: '确定',
    	                cssClass: 'btn-success',
    	                action: function(dialogItself){
    	                    dialogItself.close();
    	                }
    	            }]
    		    });
                $('#commentList').bootstrapTable('refresh');
             }
		});
	}
	
	// 弹出删除提示框
	function showDelDialog(index){
		var rows=$('#commentList').bootstrapTable('getData',true);
		var row = rows[index];
		BootstrapDialog.show({
		 	type:BootstrapDialog.TYPE_WARNING,
            title: '提醒消息',
            message: '确定要删除<strong style=\"color:red;\">\"'+row.content+'\"</strong>这条评论吗?',
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
                        url: "admin/comment/deleteByIds.aspx",
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
                                    $('#commentList').bootstrapTable('refresh');
                                 }
                    });
                }
            }]
        });
	}
	
	// 批量删除
	function deleteByIds(){
		var comments = $('#commentList').bootstrapTable('getSelections');
		if(comments.length == 0){
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
            		$.each(comments, function (index, comment) {
                        ids.push(comment.id);
                    });
            		$.ajax({
                        type: "POST",
                        url: "admin/comment/deleteByIds.aspx",
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
                                    $('#commentList').bootstrapTable('refresh');
                                 }
                    });
                }
            }]
	    });
	}