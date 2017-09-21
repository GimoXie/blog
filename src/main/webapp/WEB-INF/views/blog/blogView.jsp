<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<link rel="stylesheet" href="${pageContext.request.contextPath}/static/assets/plugins/ueditor/third-party/SyntaxHighlighter/shCoreDefault.css">
<link href="${pageContext.request.contextPath}/static/assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<div class="blog-view">
  <div class="blog-list-title">
	<img src="${pageContext.request.contextPath}/static/app/img/blog_show_icon.png" /> 博客信息
  </div>
  
  <div>
  	<div class="blog-view-title">
  		<h3><strong>${blog.title}</strong></h3>
  	</div>
 
   <!--  <div class="blog-view-share">
	  <div class="bshare-custom">
	    <a title="分享到QQ空间" class="bshare-qzone"></a>
	    <a title="分享到新浪微博" class="bshare-sinaminiblog"></a>
	    <a title="分享到人人网" class="bshare-renren"></a>
	    <a title="分享到腾讯微博" class="bshare-qqmb"></a>
	    <a title="分享到网易微博" class="bshare-neteasemb"></a>
	    <a title="更多平台" class="bshare-more bshare-more-icon more-style-addthis"></a>
	    <span class="BSHARE_COUNT bshare-share-count">0</span>
	  </div>
      <script type="text/javascript" charset="utf-8" src="http://static.bshare.cn/b/buttonLite.js#style=-1&amp;uuid=&amp;pophcol=2&amp;lang=zh"></script>
	  <script type="text/javascript" charset="utf-8" src="http://static.bshare.cn/b/bshareC0.js"></script>
    </div> -->
  	
  	<div class="blog-view-info">
		发布时间：『 <fmt:formatDate value="${blog.releaseDate }" type="date"
				pattern="yyyy-MM-dd HH:mm" />』&nbsp;&nbsp;博客类别：${blog.blogType.typeName }&nbsp;&nbsp;阅读(${blog.clickHit }) 评论(${blog.replyHit })
	  <hr>
	</div>
  	<div class="blog-view-content">
  	  ${blog.content}
  	</div>
  	<div>
	  <font><strong>关键字：</strong></font>
	    <c:choose>
		  <c:when test="${keywords==null}">
		    &nbsp;&nbsp;无
		  </c:when>
		  <c:otherwise>
		    <c:forEach var="keyWord" items="${keywords}">
			  &nbsp;&nbsp;<a href="${pageContext.request.contextPath}/articles/search.html?q=${keyWord}"><span class="label label-info">${keyWord}</span></a>&nbsp;&nbsp;
			</c:forEach>
		  </c:otherwise>
	    </c:choose>
    </div>
    <hr>
    <div>
      ${pageCode}
    </div>
  </div>
</div>

<br>

<div class="blog-view">
  <div class="blog-list-title">
    <img src="${pageContext.request.contextPath}/static/app/img/comment_icon.png" /> 评论信息
  </div>
  <c:if test="${commentList.size()>10 }">
	<a href="javascript:showOtherComment()" style="float: right; padding-right: 40px;">显示所有评论</a>
  </c:if>

  <div class="blog-view-commentDatas">
    <c:choose>
	  <c:when test="${commentList.size()==0 }">
	        暂无评论
	  </c:when>
	  <c:otherwise>
		<c:forEach var="comment" items="${commentList}" varStatus="status">
		  <c:choose>
			<c:when test="${status.index<10 }">
				<c:choose>
			      <c:when test="${fn:length(commentList)-status.index-1 ==0}">
					<div class="blog-view-comment">
				      <span><font>沙发&nbsp;&nbsp;&nbsp;&nbsp;${comment.userIp }：${comment.content }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				        <div class="commentDate">[&nbsp;<fmt:formatDate value="${comment.commentDate }" type="date" pattern="yyyy-MM-dd HH:mm" />&nbsp;]</div></font>
				      </span>
					</div>
				  </c:when>
				<c:otherwise>
				  <div class="blog-view-comment">
				    <span><font>${fn:length(commentList)-status.index }楼&nbsp;&nbsp;&nbsp;&nbsp;${comment.userIp }：${comment.content }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<div class="commentDate">[&nbsp;<fmt:formatDate value="${comment.commentDate }" type="date" pattern="yyyy-MM-dd HH:mm" />&nbsp;]</div></font>
					</span>
				  </div>
				</c:otherwise>
			   </c:choose>
			</c:when>
			<c:otherwise>
			  <c:choose>
			    <c:when test="${fn:length(commentList)-status.index-1 ==0}">
				  <div class="blog-view-otherComment">
				    <span><font>沙发&nbsp;&nbsp;&nbsp;&nbsp;${comment.userIp }：${comment.content }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				      <div class="commentDate">[&nbsp;<fmt:formatDate value="${comment.commentDate }" type="date" pattern="yyyy-MM-dd HH:mm" />&nbsp;]</div></font>
				    </span>
				  </div>
				</c:when>
			    <c:otherwise>
				  <div class="blog-view-otherComment">
				    <span><font>${fn:length(commentList)-status.index }楼&nbsp;&nbsp;&nbsp;&nbsp;${comment.userIp }：${comment.content }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				      <div class="commentDate">[&nbsp;<fmt:formatDate value="${comment.commentDate }" type="date" pattern="yyyy-MM-dd HH:mm" />&nbsp;]</div></font>
				    </span>
			      </div>
			    </c:otherwise>
			  </c:choose>
		    </c:otherwise>
		  </c:choose>
	    </c:forEach>
	  </c:otherwise>
	</c:choose>
  </div>
</div>
	  <!-- <div id="example" style="text-align: center"> <ul id="pageLimit"></ul> </div> -->

<div class="blog-view">
  <div class="blog-list-title">
  	<img src="${pageContext.request.contextPath}/static/app/img/publish_comment_icon.png" /> 发表评论
  </div>
  <div class="publish-comment">
	<div class="comment-content">
		<textarea style="width: 100%" rows="3" id="content" name="content" placeholder="来说两句吧..."></textarea>
	</div>
	<div class="verCode">
		验证码：<input type="text" id="imageCode" size="10" />&nbsp;
		<img onclick="javascript:loadimage();" title="换一张" id="randImage" src="${pageContext.request.contextPath}/comment/getVerifiCode.html" >
		<div class="publish-btn">
			<button class="btn btn-primary btn-sm" type="button" onclick="submitData()">发表评论</button>
		</div>
	</div>
  </div>
</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/static/assets/plugins/ueditor/third-party/SyntaxHighlighter/shCore.js"></script>
<script src="${pageContext.request.contextPath}/static/assets/plugins/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/assets/plugins/bootstrap-paginator/src/bootstrap-paginator.js"></script>
<script type="text/javascript">
	//代码高亮
	SyntaxHighlighter.all();
	
	//显示所有评论
	function showOtherComment() {
		$(".blog-view-otherComment").show();
	}
	
	//加载验证码图片
	function loadimage() {
		$("#randImage").attr("src","${pageContext.request.contextPath}/comment/getVerifiCode.html?"+Math.random());
	}
	
	//提交评论数据
	function submitData() {
		var content = $("#content").val();
		var imageCode = $("#imageCode").val();
		if (content == null || content == "") {
			BootstrapDialog.show({
				title: '提醒消息',				
				type: BootstrapDialog.TYPE_INFO,
	            message: '请输入评论内容！',
	            buttons: [{
	                label: '好的',
	                action: function(dialogItself){
	                    dialogItself.close();
	                }
		    	}]
	        });
		} else if (imageCode == null || imageCode == "") {
			BootstrapDialog.show({
				title: '提醒消息',				
				type: BootstrapDialog.TYPE_INFO,				
	            message: '请填写验证码！',
	            buttons: [{
	                label: '好的',
	                action: function(dialogItself){
	                    dialogItself.close();
	                }
		    	}]
	        });
		} else {
			$.post("${pageContext.request.contextPath}/comment/save.html", {
				'content' : content,
				'imageCode' : imageCode,
				'blog.id' : '${blog.id}'
			}, function(result) {
				if (result.success) {
					BootstrapDialog.show({
        		        type:BootstrapDialog.TYPE_SUCCESS,
        		    	title: '提醒消息',
        		    	message: '评论已提成成功，审核通过后显示！',
        		    	buttons: [{
        		    		icon: 'fa fa-check',
        	                label: '确定',
        	                cssClass: 'btn-success',
        	                action: function(dialogItself){
					            window.location.reload();
        	                    dialogItself.close();
        	                }
        	            }]
        		    });
				} else {
					BootstrapDialog.show({
					 	type:BootstrapDialog.TYPE_WARNING,
			            title: '提醒消息',
			            message: result.errorInfo,
			            buttons: [{
				    		icon: 'fa fa-times',
			                label: '确定',
			                action: function(dialogItself){
			                    dialogItself.close();
			                }
				    	}]
					});
				}
			}, "json");
		}
	}
	
	$('#pageLimit').bootstrapPaginator({    
	    currentPage: 1,    
	    totalPages: 10,    
	    size:"small",    
	    bootstrapMajorVersion: 3,    
	    alignment:"right",    
	    numberOfPages:5,    
	    itemTexts: function (type, page, current) {        
	        switch (type) {            
		        case "first": return "首页";            
		        case "prev": return "上一页";            
		        case "next": return "下一页";            
		        case "last": return "末页";            
		        case "page": return page;
	        }
	    },
	    onPageClicked: function (event, originalEvent, type, page) { 
	    	$.ajax({
	    		url: "index.html?page="+page,
	    		type: "post",
	    		success: function (result) {
	    			 alert(result);
	    			 blogList = result.blogList;
	    			 alert(blogList);
	    		}
	    	});
	    }
	}); 
</script>