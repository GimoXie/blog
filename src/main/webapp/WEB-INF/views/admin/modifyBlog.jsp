<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<link href="${pageContext.request.contextPath}/static/assets/plugins/bootstrap-tagsinput/dist/bootstrap-tagsinput.css" rel="stylesheet" type="text/css" />

<html xmlns:blog>
<div class="row writeBlog">
	<div class="col-md-4">
		<div class="input-group">
            <span class="input-group-addon">博客标题</span>
            <input type="text" class="form-control" id="blog_title" placeholder="请输入博客标题..." value="${blog.title}">
        </div>
	</div>
	
	<div class="col-md-4">
		<div class="input-group">
            <span class="input-group-addon">博客类别</span>
            <select class="blogType-select" id="blog_type">
			  <c:forEach var="blogTypeCount" items="${blogTypeCountList}">
			    <option value="${blogTypeCount.id}">${blogTypeCount.typeName}</option>
			  </c:forEach>
			</select>
        </div>
	</div>
	
	<div class="col-md-4">
		<div class="input-group">
            <span class="input-group-addon">关键字</span>
            <input type="text" class="form-control" id="blog_keywords" data-role="tagsinput" value="${blog.keyWord}">
        </div>
	</div>
	<!-- ueditor区域 -->
	<div id="editor-container" class="col-md-12 editor-container">
		<span class="input-group-addon">博客内容</span>
	</div>

	<div class="col-md-4 pull-right blog-publish-btn">
  		<button class="btn btn-primary" type="button" onclick="publish()">发布 <i class="fa fa-check"></i></button>
	</div>
	<!-- 隐藏域 -->
	<blog:content id="blog-content">${blog.content}</blog:content>
</div>

<script>
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
</script>

<script src="${pageContext.request.contextPath}/static/assets/plugins/bootstrap-tagsinput/lib/angular/angular.js" type="text/javascript" ></script>
<script src="${pageContext.request.contextPath}/static/assets/plugins/bootstrap-tagsinput/dist/bootstrap-tagsinput.js" type="text/javascript" ></script>
<script src="${pageContext.request.contextPath}/static/assets/plugins/bootstrap-tagsinput/dist/bootstrap-tagsinput-angular.js" type="text/javascript" ></script>

