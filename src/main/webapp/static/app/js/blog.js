function checkData(){
	var q = $('#q').val().trim();
	if(q==null ||q == ''){
		q =$('#404q').val().trim();
	}
	if(q==null || q==""){
		BootstrapDialog.show({
			title: '提醒消息',				
			type: BootstrapDialog.TYPE_INFO,
			message: '请输入搜素内容！',
			buttons: [{
				label: '好的',
				action: function(dialogItself){
					dialogItself.close();
				}
			}]
		});
		return false;
	}else{
		return true;
	}
}