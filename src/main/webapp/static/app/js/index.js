$(function() {
    App.init();

    var Index = (function() {
        var me = {};

        // 处理一级菜单点击
        me.handleMenuClick = function() {
            $('#page-sidebar-menu > li').click(function(e) {
                var menu = $('#page-sidebar-menu');
                var li = menu.find('li.active').removeClass('active');
                //设置mainTag的值为第一个span的内容
                $('#mainTag').html($(this).find('span:eq(0)').html().trim());
                //修改mainTagI 的class为第一个i标签的class
                $('#mainTagI').attr('class',$(this).find('i:eq(0)').attr('class'));
                // 添加选中打开的样式
                //$(this).addClass('active');
            });
        };
        /* select2.css .select2-container .select2-choice -> line-height: 32px; */
        // 处理子菜单点击
        me.handleSubMenuClick = function() {
            $('#page-sidebar-menu li a').click(function(e) {
            	//设置subTag为当前a标签的文本
            	$("#subTag").html(this.text);
                e.preventDefault();
                var url = this.href;
                if (url != null && url != 'javascript:;') {
                	if(url.indexOf('javascript:') == 0){
                		var func = eval(url.substring(url.indexOf(':')+1,url.indexOf('(')));
                		new func();
                	}else{
                		$.get(url, function(data) {
                			$('#main-content').html(data);
                		});
                	}
                }
            });
        };

        me.init = function() {
            me.handleMenuClick();
            me.handleSubMenuClick();
        };

        return me;
    })();

    Index.init();

    $('#btn-dashboard').trigger("click");
    
    function getRootPath(){
    	// 获取当前网址
    	var currentPath = window.document.location.href;
    	// 获取主机地址之后的目录
    	var pathName = window.document.location.pathname;
    	
    	var pos = currentPath.indexOf(pathName);
    	// 获取主机地址
    	var localhostPath = currentPath.substring(0,pos);
    	// 项目名称
    	var projectName = pathName.substring(0,pathName.substr(1).indexOf('/')+1);
    	
    	var applicationPath = localhostPath + projectName;
    	return applicationPath;
    }
    var applicationPath = getRootPath();
    UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;
	UE.Editor.prototype.getActionUrl = function(action){
		if(action == 'uploadimage'){
			return applicationPath+'/admin/attach/uploadimage.aspx';
		}else{
			return this._bkGetActionUrl.call(this, action);
		}
	}	
});

function modifyPassword(){
	var $form = $('<div></div>');
	$form.append('<div class="form-group">');
	$form.append('<label for="oldPassword">原始密码</label>');
	$form.append('<input type="password" class="form-control" id="oldPassword" placeholder="请输入原始密码...">');
	$form.append('</div>');
	$form.append('<div class="form-group">');
	$form.append('<label for="newPassword">新密码</label>');
	$form.append('<input type="password" class="form-control" id="newPassword" placeholder="请输入新密码...">');
	$form.append('</div>');
	$form.append('<div class="form-group">');
	$form.append('<label for="newPasswordConfirm">确认密码</label>');
	$form.append('<input type="password" class="form-control" id="newPasswordConfirm" placeholder="请确认密码...">');
	$form.append('</div>');
	BootstrapDialog.show({
		type: BootstrapDialog.TYPE_PRIMARY,				
        title: '修改密码',
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
            	if($('#oldPassword').val() == '' || $('#newPassword').val() == '' || $('#newPasswordConfirm').val() == ''){
            		return;
            	}
            	if($('#newPassword').val() != $('#newPasswordConfirm').val()){
            		BootstrapDialog.show({
            			type: BootstrapDialog.TYPE_INFO,				
            	        title: '提醒消息',
            	        message: '两次输入的新密码不一致，请重新输入！',
            	        buttons: [{
        	                label: '好的',
        	                action: function(dialogItself){
        	                	$('#newPassword').val('');
        	                	$('#newPasswordConfirm').val('');
        	                    dialogItself.close();
        	                }
        		    	}]
            		});
            	}else{
            		$.ajax({
                    	type: "POST",
                        url: "admin/modifyPassword.aspx",
                        data: JSON.stringify({
                        	    oldPassword:sha256_digest($('#oldPassword').val()),
                        	    newPassword:sha256_digest($('#newPassword').val()),
                        	    newPasswordConfirm:sha256_digest($('#newPasswordConfirm').val())
                        	  }),
                        contentType: "application/json",
                    	dataType: "json",
                        success: function(data){
                        	if(data.result){
                            	BootstrapDialog.show({
                    		        type:BootstrapDialog.TYPE_SUCCESS,
                    		    	title: '提示',
                    		    	message: '修改成功!',
                    		    	buttons: [{
                    		    		icon: 'fa fa-check',
                    	                label: '确定',
                    	                cssClass: 'btn-success',
                    	                action: function(dialogItself){
                    	                    dialogItself.close();
                    	                }
                    	            }]
                    		    });
                        	}else{
                        		BootstrapDialog.show({
                    		        type:BootstrapDialog.TYPE_INFO,
                    		    	title: '提醒消息',
                    		    	message: data.reason,
                    		    	buttons: [{
                    	                label: '确定',
                    	                action: function(dialogItself){
                    	                    dialogItself.close();
                    	                }
                    	            }]
                    		    });
                        	}

                        }
                    });
            	}
            }
        }]
    });
}

function refreshCache(){
	BootstrapDialog.show({
	 	type:BootstrapDialog.TYPE_WARNING,
        title: '提醒消息',
        message: '确定要刷新缓存',
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
        		$.ajax({
                    type: "POST",
                    url: "admin/systemManage/refreshCache.aspx",
                    dataType: "json",
                    success: function(data){
                    		    BootstrapDialog.show({
                    		        type:BootstrapDialog.TYPE_SUCCESS,
                    		    	title: '提示',
                    		    	message: '刷新成功!',
                    		    	buttons: [{
                    		    		icon: 'fa fa-check',
                    	                label: '确定',
                    	                cssClass: 'btn-success',
                    	                action: function(dialogItself){
                    	                    dialogItself.close();
                    	                }
                    	            }]
                    		    });
                             }
                });
            }
        }]
    });
}