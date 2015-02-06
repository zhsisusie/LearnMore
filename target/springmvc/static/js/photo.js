/**
 * 
 */
$(document).ready(function(){
	$("#pclick").bind("click",function(){
		photo();
		
	});
});

function photo(){
	var val;
	$.ajax({
		async:false,
		type:'get',//get是获取数据，post是带数据的向服务器发送请求
		url:'addAction.do',
		dataType:'json',
		success:function(data){
			alert("zhsi");
			val=eval(data);//转换成js对象
			alert(val[0].photoName);
		},
		error:function(data){
			alert("JSON数据获取失败，请联系管理员！");
		}
	});
}