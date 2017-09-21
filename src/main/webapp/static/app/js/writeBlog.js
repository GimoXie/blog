$(document).ready(function(){
	  UE.delEditor('editor-container');
	  var ue = UE.getEditor('editor-container');
	  $('.blogType-select').select2();
  });
	    
  //发布博客
  function publish(){
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
        		    	message: '博客发布成功！',
        		    	buttons: [{
        		    		icon: 'fa fa-check',
        	                label: '确定',
        	                cssClass: 'btn-success',
        	                action: function(dialogItself){
        	                	reset();
        	                    dialogItself.close();
        	                }
        	            }]
        		    });
				}else{
					BootstrapDialog.show({
					 	type:BootstrapDialog.TYPE_WARNING,
			            title: '提醒消息',
			            message: '博客发布失败！',
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
  
  //重置
  function reset(){
	  $('#blog_title').val('');
	  $('#blog_keywords').val('');
	  $('#blog_keywords').tagsinput('removeAll');
	  UE.getEditor('editor-container').setContent('');
  }