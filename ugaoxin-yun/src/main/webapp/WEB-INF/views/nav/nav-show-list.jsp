<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!DOCTYPE html>
<html lang="en">
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
    <title>导航菜单列表</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
  </head>
  <body class="app sidebar-mini rtl">
    <!-- Navbar--> 
    <%@ include file="../../../index/header.jsp" %>
    <%@ include file="../../../index/aside.jsp" %> 
     
    <main class="app-content">
      <div class="app-title">
        <div>
          <h1><i class="fa fa-th-list"></i>导航菜单管理</h1>
          <p>通用云平台的列表展示</p>
        </div>
        <ul class="app-breadcrumb breadcrumb side">
          <li class="breadcrumb-item"><i class="fa fa-home fa-lg"></i></li>
          <li class="breadcrumb-item">Tables</li>
          <li class="breadcrumb-item active"><a href="#">Bootstrap Table TreeGrid</a></li>
        </ul>
      </div>
    <div class="row-fluid">
    <div class="pull-right">
       
    </div>
       <div class="row">
        <form id="queryForm" action="<%=path%>/yun/role/showRolesList" method="post">
            <div  style="display: inline-block;margin:13px;">
                <input type="text" id="keyword" name="keyword" class="form-control input-sm"
                    placeholder="请输入要检索的内容">
            </div>
            <button type="button" class="btn btn-primary btn-sm" id="query">
             <i class="fa fa-search"></i> 检索
            </button>
        </form>
    </div>
</div>
    
    
    <div class="row" id="list"> 
      
    

        <div class="col-md-12">
          <div class="tile">
            <div class="tile-body">
            
		<table id="mytab" class="table table-hover" data-toggle="table" data-pagination="true" data-search="true" data-advanced-search="true" data-id-table="advancedTable" ></table>
		  	<div id="toolbar" class="btn-group pull-right" style="margin-right: 20px;">
	            <button id="btn-edit" type="button" class="btn btn-primary btn-sm" style="display: none; border-radius: 0">
	                <span class="glyphicon glyphicon-pencil" aria-hidden="true" ></span>修改
	            </button>
	            <button id="btn-delete" type="button" class="btn btn-primary btn-sm" style="display: none;">
	                <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
	            </button>
	            <button id="btn-add" type="button" class="btn btn-primary btn-sm">
	                <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
	            </button>
        	</div>
        </div>
        
		<div class="popup_de xycenter">
			<div class="popup_box">
				<span class="popup_close" value="关闭">×</span>
			    <span class="show_msg">确定要删除该权限吗？</span>
			    <div class="btn_box">
				    <div class="popup_btn btn_submit" value="确定">确定</div>
					<div class="popup_btn btn_cancel" value="取消">取消</div>
			    </div>
			</div>	
		</div>
		         </div>
          </div>
        </div>
      </div>
    </main>
	</body>
	
	
	
	<!-- Main CSS-->
	  <link rel="stylesheet" href="<%=basePath%>static/bootstrap/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
	  <link href="<%=basePath%>static/bootstrap/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
	  <link href="<%=basePath%>static/bootstrap/font-awesome/css/font-awesome.min.css" rel="stylesheet">
	  <link href="<%=basePath%>static/css/nav.css" rel="stylesheet">
	  <link rel="stylesheet" type="text/css" href="<%=basePath%>/static/main/css/main.css">
    <!-- Font-icon css-->
     <link rel="stylesheet" type="text/css" href="<%=basePath%>static/css/jquery.treegrid.css">
     
     
     
	 
    <script src="<%=basePath%>static/main/js/jquery-3.2.1.min.js"></script>
    <script src="<%=basePath%>static/bootstrap/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script> 
    <script src="<%=basePath%>static/js/jquery.cookie.js"></script>
    <script src="<%=basePath%>static/bootstrap/bootstrap-table/bootstrap-table.min.js"></script>  
    <script src="<%=basePath%>static/bootstrap/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script> 
    <script src="<%=basePath%>static/bootstrap/bootstrap-table/extensions/export/bootstrap-table-export.min.js"></script>
    <script src="<%=basePath%>static/bootstrap/bootstrap-table/extensions/export/tableExport.js"></script>
    <script src="<%=basePath%>static/js/setBootstrapTableTreeGrid.js"></script>  
    
    <script src="<%=basePath%>static/bootstrap/bootstrap-table/extensions/treegrid/bootstrap-table-treegrid.min.js"></script>

    <script src="<%=basePath%>static/bootstrap/jquery.treegrid/jquery.treegrid.min.js"></script>
   <%--  <script src="<%=basePath%>static/bootstrap/tree-column/bootstrap-table-tree-column.min.js"></script>  --%>
 
   
<script>
     $(function(){
    // 初始化为false
    document.getElementById("allChecked").checked = false;
    //全选
    $("#allChecked").click(function(){
        if (this.checked) {
            $("#list :checkbox").prop("checked",true); 
        }else{
            $("#list :checkbox").prop("checked",false);
        }
    });

    $("#selectAll").click(function(){
        $("#list :checkbox,#all").prop("checked",true);
    });

    $("#unSelect").click(function(){
        $("#list :checkbox,#all").prop("checked",false);
    });

    $("#reverse").click(function(){
        $("#list :checkbox").each(function(){
            $(this).prop("checked",!$(this).prop("checked"));
        });
        allCheck();
    });

    $(document).on('click','#list :checkbox', function(){ 
        allCheck(); 
    });
    //设置全选复选框 
    /* $("#list :checkbox").click(function(){ 
        allCheck(); 
    });  */

    $("#delByIds").click(function(){
    	
    	swal({
      		title: "Are you sure?",
      		text: "You will not be able to recover this imaginary file!",
      		type: "warning",
      		showCancelButton: true,
      		confirmButtonText: "Yes, delete it!",
      		cancelButtonText: "No, cancel plx!",
      		closeOnConfirm: false,
      		closeOnCancel: false
      	}, function(isConfirm) {
      		if (isConfirm) {
      			
      			var roles="";
      	        var valArr = new Array;
      	        $("#list input[name='mId']").each(function(i){
      	            if($(this).prop("checked")){
      	                valArr[i]=$(this).val();
      	                roles+=$(this).val()+",";
      	            }
      	        });
      	        var vals = valArr.join(',');
      	       
      	        $.ajax({
      	           	url:"delRolesByIds/"+roles,
      	               async:true,
      	               type:"GET",
      	               dataType:"json",
      	               data:{ },
      	               cache:false,    //不允许缓存
      	               success: function(data){
      	                   var obj = eval(data);
      	                   if(obj.code==1)
      	                   {   
      	                	   /* alert("删除已选成功！"); */
      	                	   swal("Deleted!", "删除成功", "success");
      	                       window.location.reload();
      	                   }
      	                   else
      	                   {
      	                       /* alert("删除失败"); */
      	                	 swal("Cancelled", "你取消了操作", "error");
      	                   }

      	               }
      	    	   });
      		} else {
      			swal("Cancelled", "你取消了操作 :)", "error");
      		}
      	});
    
    
    	
        //alert(vals);
        
    })

    function allCheck(){
        var count = $("#list :checkbox").length;
        var cut = 0;
        $("#list :checkbox").each(function(){
            if ($(this).prop("checked") == true) {
                cut++;
            }
        });
        /* alert("cut="+cut);
        alert("count"+count); */
        if (cut <= count) {
        	document.getElementById("allChecked").checked = false;
            //$("#allChecked").prop("checked",true);
        }else{
        	document.getElementById("allChecked").checked = true;
            //$("#allChecked").prop("checked",false);
        }
    }
})
    
</script>
 
 
    <script>
       function del(id) {
    	  
    	   $.ajax({
           	url:"delRoleById/"+id,
               async:true,
               type:"GET",
               dataType:"json",
               cache:false,    //不允许缓存
               success: function(data){
                   var obj = eval(data);
                   if(obj.code==1)
                   {   alert("删除成功！");
                       window.location.reload();
                   }
                   else
                   {
                       alert("删除失败");
                   }

               }
    	   });
       }
    </script> 
    <script type="text/javascript">
       $("#btn-add").click(function(){
    	//清空cookie 
    	// $.cookie('rolestoUpdate', '', { expires: -1 }); 
    	//$.removeCookie('rolestoUpdate');
    	$.cookie('navDoType', "add");
      window.location.href="saveOrUp";
                    
    })
    
   
       $("#btn-edit").click(function(){
    	   
    	//获取选中的Id
    	
    	        var ids="";
      	        var valArr = new Array;
      	        $("#list input[name='btSelectItem']").each(function(i){
      	            if($(this).prop("checked")){
      	                valArr[i]=$(this).val();
      	                ids+=$(this).val()+",";
      	            }
      	        });
    	    
    	$.cookie('navDoType', ids+"");
    	
      window.location.href="saveOrUp";
                    
    })
    
    
   
     // 跳转到更新页面
      function toUpdate(roles){ 
    	   
    	   var json= JSON.stringify(roles);  
    	   $.cookie('navDoType', json);
            window.location.href="saveOrUp";  
                    
       }
    
    </script>
    <script type="text/javascript" src="<%=basePath%>static/main/js/plugins/sweetalert.min.js"></script>
    <script type="text/javascript">
      $('#demoNotify').click(function(){
      	$.notify({
      		title: "Update Complete : ",
      		message: "Something cool is just updated!",
      		icon: 'fa fa-check' 
      	},{
      		type: "info"
      	});
      });
      $('#demoSwal').click(function(){
      	swal({
      		title: "Are you sure?",
      		text: "You will not be able to recover this imaginary file!",
      		type: "warning",
      		showCancelButton: true,
      		confirmButtonText: "Yes, delete it!",
      		cancelButtonText: "No, cancel plx!",
      		closeOnConfirm: false,
      		closeOnCancel: false
      	}, function(isConfirm) {
      		if (isConfirm) {
      			swal("Deleted!", "Your imaginary file has been deleted.", "success");
      		} else {
      			swal("Cancelled", "Your imaginary file is safe :)", "error");
      		}
      	});
      });
    </script>
</html>
      