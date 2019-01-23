/*点击验证码，刷新*/
function flush() {
	
	var codeObject = document.getElementById("googleCode");
	codeObject.src="getGooglCode?t="+new Date().getTime();
	
}

//登录的login方法，通过前台调用后台的微服务接口
function login(){
	 
	$.ajax({
		type:"POST",
		url:"login",
		data:$('#loginForm').serialize(),
		success:function(data){
			
			/*console.info(data);
			alert("登录成功！")*/
			if(data.code==1){
				location.href="../index/index";
			} else {
				alert("用户名或者密码错误，请检查！");
				flush();
				
			}
		}
		
	})
} 

function add(){
	alert("已经进入");
}
  
