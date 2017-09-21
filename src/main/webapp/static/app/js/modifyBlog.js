$(document).ready(function(){
	  UE.delEditor('editor-container');
	  // 为下拉框赋初始化值
	  $(".blogType-select").select2();
	  $(".blogType-select").val('${blog.blogType.id}').trigger('change');
	  //为编辑器赋值
	  var ue = UE.getEditor('editor-container');
	  ue.ready(function(){
		  ue.setContent($('#blog-content').html());
	  });
  });
  
  //更新博客
  function publish(){
      var id = '${blog.id}';
	  var title = $('#blog_title').val();
	  var blogTypeId = $('#blog_type').val();
	  var contentNoTag = UE.getEditor('editor-container').getContentTxt();
	  var content = UE.getEditor('editor-container').getContent();
	  var summary = contentNoTag.substr(0,155);
	  var keyWord = $('#blog_keywords').val();
	  if(title==null || title==''){
		BootstrapDialog.show({
			title: '提醒消息',				
			type: BootstrapDialog.TYPE_INFO,				
	        message: '请输入标题！',
	        buttons: [{
	            label: '好的',
	            action: function(dialogItself){
	                dialogItself.close();
	            }
		    }]
	    });
	  }else if(blogTypeId==null || blogTypeId==''){
		  BootstrapDialog.show({
			  title: '提醒消息',				
			  type: BootstrapDialog.TYPE_INFO,				
		      message: '请选择博客类别！',
		      buttons: [{
		          label: '好的',
		          action: function(dialogItself){
		              dialogItself.close();
		          }
			  }]
		  });
	  }else if(content==null || content==''){
		  BootstrapDialog.show({
			  title: '提醒消息',				
			  type: BootstrapDialog.TYPE_INFO,				
		      message: '请填写内容！',
		      buttons: [{
		          label: '好的',
		          action: function(dialogItself){
		              dialogItself.close();
		          }
			  }]
		  });
	  }else{
		  $.post('admin/blog/publish.aspx',
			{
			  'id' : id,
			  'title' : title,
			  'summary' : summary,
			  'content' : content,
			  'contentNoTag' : contentNoTag,
			  'blogType.id' : blogTypeId,
			  'keyWord' : keyWord
			},	  
		  	function(result){
				if(result.success){
					BootstrapDialog.show({
        		        type:BootstrapDialog.TYPE_SUCCESS,
        		    	title: '提醒消息',
        		    	message: '博客更新成功！',
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
					 	type:BootstrapDialog.TYPE_WARNING,
			            title: '提醒消息',
			            message: '博客更新失败！',
			            buttons: [{
				    		icon: 'fa fa-times',
			                label: '确定',
			                action: function(dialogItself){
			                    dialogItself.close();
			                }
				    	}]
					});
				}
			},
			'json'
		  );
	  }
  }