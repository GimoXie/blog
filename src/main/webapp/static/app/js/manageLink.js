$(document).ready(function(){
		// 先销毁表格 避免二次加载时无法加载的问题
		$("#linkList").bootstrapTable("destroy");
		// 生成table
		var tables = $("#linkList").bootstrapTable({
			url : 'admin/linkList.aspx',
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
				checkbox : true
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
				title : '链接名称',
				field : 'linkName',
				sortable : false,
				align : 'center',
				valign : 'middle'
			},{
				title : '链接地址',
				field : 'linkUrl',
				sortable : false,
				align : 'center',
				valign : 'middle',
				formatter : function(value, row, index) {
					return "<a href=\""+value+"\">http://"+value.substring(7)+"</a>"
				}
			},{
				title : '优先级',
				field : 'orderNo',
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
					str += '<a onclick="showSaveDialog(\'' + index + '\')" title="编辑"><span class="fa fa-pencil-square-o"/></a>&nbsp;&nbsp;';
					str += '<a style="color:red;" onclick="showDelDialog(\'' + index + '\')" title="删除"><span class="fa fa-trash-o"/></a>&nbsp;&nbsp;';
					return str
				}
			}]
		});
		$("#linkList").bootstrapTable('hideColumn', 'id');
	});
	
	// 弹出新增/修改窗口
	function showSaveDialog(index){
		var id = '';
		var linkName = '';
		var linkUrl = '';
		var orderNo = '';
		if(index != -1){
			var rows=$('#linkList').bootstrapTable('getData',true);
			var row = rows[index];
			id = row.id;
			linkName = row.linkName;
			linkUrl = row.linkUrl;
			orderNo = row.orderNo;
		}
		var $form = $('<div></div>');
		$form.append('<div class="form-group">');
		$form.append('<input type="hidden" class="form-control" id="linkId" placeholder="id" value="'+id+'">');
		$form.append('<label for="linkName">链接名称</label>');
		$form.append('<input type="text" class="form-control" id="linkName" placeholder="请输入链接名称..." value="'+linkName+'">');
		$form.append('</div>');
		$form.append('<div class="form-group">');
		$form.append('<label for="linkUrl">链接地址</label>');
		$form.append('<input type="text" class="form-control" id="linkUrl" placeholder="请输入链接地址..." value="'+linkUrl+'">');
		$form.append('</div>');
		$form.append('<div class="form-group">');
		$form.append('<label for="orderNo">优先级</label>');
		$form.append('<input type="text" class="form-control" id="blogTypeOrderNo" placeholder="请输入优先级..." value="'+orderNo+'">');
		$form.append('</div>');
		BootstrapDialog.show({
			type: BootstrapDialog.TYPE_PRIMARY,				
            title: '新增/修改友情链接',
            message: $form,
            buttons: [{
            	icon: 'fa fa-times',
                label: '取消',
                action: function(dialogItself){
                	dialogItself.close();
                }
            },{
            	icon: 'fa fa-check',
                label: '确定',
                cssClass: 'btn-primary',
                action: function(dialogItself){
                	dialogItself.close();
                    $.ajax({
                    	type: "POST",
                        url: "admin/link/save.aspx",
                        data: {
	                        	id:$('#linkId').val(),
	                        	linkName:$('#linkName').val(),
	                        	linkUrl:$('#linkUrl').val(),
	                        	orderNo:$('#blogTypeOrderNo').val()
                        	  },
                    	dataType: "json",
                        success: function(data){
                        	BootstrapDialog.show({
                		        type:BootstrapDialog.TYPE_SUCCESS,
                		    	title: '提示',
                		    	message: '新增/修改 成功!',
                		    	buttons: [{
                		    		icon: 'fa fa-check',
                	                label: '确定',
                	                cssClass: 'btn-success',
                	                action: function(dialogItself){
                	                    dialogItself.close();
                	                }
                	            }]
                		    });
                        	$('#linkList').bootstrapTable('refresh');
                        }
                    });
                }
            }]
        });
		/* BootstrapDialog.show({
			size: BootstrapDialog.SIZE_WIDE,			
            message: function(dialog) {
                var $message = $('<div></div>');
                var pageToLoad = dialog.getData('pageToLoad');
                $message.load(pageToLoad);
        
                return $message;
            },
            data: {
                'pageToLoad': 'admin/toWriteBlog.aspx'
            }
        }); */
	}
	
	// 弹出删除提示框
	function showDelDialog(index){
		var rows=$('#linkList').bootstrapTable('getData',true);
		var row = rows[index];
		BootstrapDialog.show({
		 	type:BootstrapDialog.TYPE_WARNING,
            title: '提醒消息',
            message: '确定要删除<strong style=\"color:blue;\">\"'+row.linkName+'\"</strong>这个友情链接吗?',
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
                        url: "admin/link/deleteByIds.aspx",
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
                                    $('#linkList').bootstrapTable('refresh');
                                 }
                    });
                }
            }]
        });
	}
	
	// 批量删除
	function deleteByIds(){
		var links = $('#linkList').bootstrapTable('getSelections');
		if(links.length == 0){
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
            		$.each(links, function (index, link) {
                        ids.push(link.id);
                    });
            		$.ajax({
                        type: "POST",
                        url: "admin/link/deleteByIds.aspx",
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
                                    $('#linkList').bootstrapTable('refresh');
                                 }
                    });
                }
            }]
	    });
	}