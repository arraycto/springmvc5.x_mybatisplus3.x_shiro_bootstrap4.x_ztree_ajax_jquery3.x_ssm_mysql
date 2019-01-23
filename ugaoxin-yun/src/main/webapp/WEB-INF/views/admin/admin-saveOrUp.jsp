<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>  
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" /> 

<html>
  <head>
    <meta name="description" content="Vali is a responsive and free admin theme built with Bootstrap 4, SASS and PUG.js. It's fully customizable and modular.">
    <!-- Twitter meta-->
    <meta property="twitter:card" content="summary_large_image">
    <meta property="twitter:site" content="@pratikborsadiya">
    <meta property="twitter:creator" content="@pratikborsadiya">
    <!-- Open Graph Meta-->
    <meta property="og:type" content="website">
    <meta property="og:site_name" content="Vali Admin">
    <meta property="og:title" content="Vali - Free Bootstrap 4 admin theme">
    <meta property="og:url" content="http://pratikborsadiya.in/blog/vali-admin">
    <meta property="og:image" content="http://pratikborsadiya.in/blog/vali-admin/hero-social.png">
    <meta property="og:description" content="Vali is a responsive and free admin theme built with Bootstrap 4, SASS and PUG.js. It's fully customizable and modular.">
    <title>管理员的添加//修改</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

  
    <!-- Font-icon css-->
    <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
  </head>
  <body class="app sidebar-mini rtl">
    <!-- Navbar-->
    <%@ include file="../../../index/header.jsp" %>
    <%@ include file="../../../index/aside.jsp" %> 
    <main class="app-content">
      <div class="app-title">
        <div>
          <h1><i class="fa fa-edit"></i> 添加/更新</h1>
          <p>添加/更新，管理员的信息，各位dayday up！</p>
        </div>
        <ul class="app-breadcrumb breadcrumb">
          <li class="breadcrumb-item"><i class="fa fa-home fa-lg"></i></li>
          <li class="breadcrumb-item">Forms</li>
          <li class="breadcrumb-item"><a href="#">Sample Forms</a></li>
        </ul>
      </div>
      <div class="row">
          <div class="col-md-6">
          <div class="tile">
            <h3 class="tile-title">Save or Update</h3>
            <div class="tile-body">
              <form class="form-horizontal" id="saveOrUp">
                <div class="form-group row">
                  <label class="control-label col-md-3">管理员名称</label>
                  <div class="col-md-8">
                     <input class="form-control" type="hidden" name="id"  value="${yunAdmin.id }">  
                    <input class="form-control" type="text" name="adminName" placeholder="请输入管理员名称" value="${yunAdmin.adminName }">
                  </div>
                </div> 
                 <div class="form-group row">
                  <label class="control-label col-md-3">管理员密码</label>
                  <div class="col-md-8">
                 
                    <input class="form-control" type="password" name="adminPassword" placeholder="如果不改密码，置空即可" value="${yunAdmin.adminPassword }">
                  </div>
                </div>
                <div class="form-group row">
                  <label class="control-label col-md-3">真实姓名</label>
                  <div class="col-md-8">
                    <textarea class="form-control" rows="4"   name="realName"     placeholder="请输入真实姓名">${yunAdmin.realName }</textarea>
                  </div>
                </div>
                  <div class="form-group row">
                  <label class="control-label col-md-3">邮箱</label>
                  <div class="col-md-8">
                 
                    <input class="form-control" type="text" name="email" placeholder="请输入邮箱" value="${yunAdmin.email }">
                  </div>
                </div>
               <div class="form-group row">
                  <label class="control-label col-md-3">性别</label>
                  <div class="col-md-8" data-toggle="buttons"> 
                 
                   <label class="btn btn-primary active"> 
                    <input  type="radio"  name="sex"   value="0"  ${yunAdmin.sex.value==0?'checked':''}> 女
                    </label>
                     <label class="btn btn-primary">
                     <input type="radio" name="sex"   value="1"   ${yunAdmin.sex.value==1?'checked':''}> 男
                     </label>
                  </div>
                </div> 
                  <div class="form-group row">
                  <label class="control-label col-md-3">电话</label>
                  <div class="col-md-8">
                 
                    <input class="form-control" type="text" name="telephone" placeholder="请输入电话" value="${yunAdmin.telephone }">
                  </div>
                </div>
                 <div class="form-group row">
                  <label class="control-label col-md-3">角色授权</label>
                  <div class="col-md-8" data-toggle="buttons"> 
                     <c:forEach items="${yunRoles}" var="role">
	                     <label class="btn btn-primary"> 
	                      <input  type="radio"  name="roleId"   value="${role.rId }" ${role.rId==yunAdmin.roleId?'checked':''}> ${role.roleName }
	                    </label>  
	                 </c:forEach> 
                    
                  </div>
                </div>
                
              </form>
            </div>
               <div class="tile-footer">
              <div class="row">
                <div class="col-md-8 col-md-offset-3">
                  <button class="btn btn-primary" type="button" id="doAdd"><i class="fa fa-fw fa-lg fa-check-circle"></i>操作</button>&nbsp;&nbsp;&nbsp;<a class="btn btn-secondary" href="#"><i class="fa fa-fw fa-lg fa-times-circle"></i>Cancel</a>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="clearix"></div>
        
    </main>
    <!-- Essential javascripts for application to work-->
    <script src="<%=basePath%>static/main/js/jquery-3.2.1.min.js"></script>
    <script src="<%=basePath%>static/main/js/popper.min.js"></script>
    <script src="<%=basePath%>static/main/js/bootstrap.min.js"></script>
    <script src="<%=basePath%>static/main/js/main.js"></script>
     <script src="<%=basePath%>static/ztree/js/jquery.ztree.all.min.js"></script><%-- 
     <script src="<%=basePath%>static/icheck/icheck.min.js"></script> --%>
    
     <link rel="stylesheet" type="text/css" href="<%=basePath%>static/ztree/css/metroStyle/metroStyle.css">
      <link rel="stylesheet" type="text/css" href="<%=basePath%>static/icheck/flat/green.css">
    <!-- The javascript plugin to display page loading on top-->
    <script src="<%=basePath%>static/main/js/plugins/pace.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>static/main/js/plugins/bootstrap-notify.min.js"></script>
        <!-- Main CSS-->
    <link rel="stylesheet" type="text/css" href="<%=basePath%>static/main/css/main.css">
    <!-- Page specific javascripts-->
    <!-- Google analytics script-->
    <script type="text/javascript">
      if(document.location.hostname == 'pratikborsadiya.in') {
      	(function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
      	(i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
      	m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
      	})(window,document,'script','//www.google-analytics.com/analytics.js','ga');
      	ga('create', 'UA-72504830-1', 'auto');
      	ga('send', 'pageview');
      }
    </script>
    <script type="text/javascript">
   
    $("#doAdd").click(function(){ 
 	   
 	   
 	   
 	   // 联合参数处理
 	   var params="";
 	   params+=$("#saveOrUp").serialize();  //javabean 
 	   
 	   $.ajax({
        	url:"doAdmin",
            async:true,
            type:"get",
            dataType:"json",
            cache:false,    //不允许缓存
            data:params,
            success: function(data){
                var obj = eval(data);
                if(obj.code==1)
                  {   
                	$.notify({
                  		title: "操作提醒 : ",
                  		message: "添加成功哦！^_^",
                  		icon: 'fa fa-check' ,
                  		url: 'show'
                  		
                  	},{
                  		type: "info"
                  	});
                	window.location.href="show";
                     
                  }
                  else
                  {
                      
               	    swal("Cancelled", "操作失败", "error");
                  }

            }
 	   });
    })
    
    </script>
    <!-- 获取传递过来的变量 -->
    <script type="text/javascript">
          var ztreeMenus='${menus}'
    </script>
    <!-- 初始化Ztree数据 -->
    <script type="text/javascript">
       var ztreeObject;
       var nodes;
       var setting={
    		data:{
    			simpleData:{
    				enable:true,
    				idKey:"mId",
    				pIdKey:"parentId",
    				rootPId:0
    			},
    			key:{
    				name:"menuName",
    				title:"menuName"
    			}
    			
    		},
    		check:{
    			enable:true,
    			nocheckInherit:true
    			
    		}
    	
       };
       
      /*  $(function(){
    	   
    	   ztreeMenus=eval('('+ztreeMenus+")");
    	   
    	   ztreeObject=$.fn.zTree.init($("#ztree"),setting,ztreeMenus);
    	   
    	   ztreeObject.expandAll(true);
    	   
       })
       */
       
       $(function(){
    	    var rId='${rId}'
    	   loadZtreeData(rId);
    	   
            ztreeObject=$.fn.zTree.init($("#ztree"),setting,ztreeMenus);
            //ztreeObject.checkNode(ztreeMenus, true, true);
 	        ztreeObject.expandAll(true);
       })
       
       function loadZtreeData(rId){
    	   $.ajax({
    		   type:"post",
    		   contentType: "application/x-www-form-urlencoded;charset=utf-8",
    		   url:"getAdminMenusByrId?rId="+rId,
    		   data:{},
    		   async:false,   
    		   dataType:"json",
    		   success:function(data){
    			   //console.log(data);
    			   ztreeMenus= data;
    		   }
    	   })
       }
    </script>
    
  </body>
</html>