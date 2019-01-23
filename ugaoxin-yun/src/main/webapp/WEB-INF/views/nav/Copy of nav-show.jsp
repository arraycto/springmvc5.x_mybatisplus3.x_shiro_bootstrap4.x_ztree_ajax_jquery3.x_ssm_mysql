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
    <!-- Main CSS-->
  
    <!-- Font-icon css-->
    <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
  </head>
  <body class="app sidebar-mini rtl">
    <!-- Navbar--> 
    <%@ include file="../../../index/header.jsp" %>
    <%@ include file="../../../index/aside.jsp" %> 
    
     <link rel="stylesheet" type="text/css" href="<%=basePath%>static/css/jquery.treegrid.css">
     
    <main class="app-content">
      <div class="app-title">
        <div>
          <h1><i class="fa fa-th-list"></i>导航菜单管理</h1>
          <p>通用云平台的列表展示</p>
        </div>
        <ul class="app-breadcrumb breadcrumb side">
          <li class="breadcrumb-item"><i class="fa fa-home fa-lg"></i></li>
          <li class="breadcrumb-item">Tables</li>
          <li class="breadcrumb-item active"><a href="#">Data Table</a></li>
        </ul>
      </div>
      
      
        <!-- 查询、添加、批量删除、导出、刷新 -->
<div class="row-fluid">
    <div class="pull-right">
        <div class="btn-group">
            <button type="button" class="btn btn-primary btn-sm" id="btn-add">
                <i class="fa fa-plus"></i> 添加
            </button>
            <button type="button" class="btn btn-primary btn-sm" id="delByIds">
                <i class="fa fa-remove"></i> 删除
            </button>
            <button type="button" class="btn btn-primary btn-sm" id="btn-export">
                <i class="fa fa-sign-in"></i> 导出
            </button>
            <button type="button" class="btn btn-primary btn-sm" id="btn-re">
                <i class="fa fa-refresh"></i>刷新
            </button>
        </div>
    </div>
       <div class="row">
        <form id="queryForm" action="<%=path%>/yun/role/showRolesList" method="post">
            <div class="col-xs-2" style="display: inline-block;margin:13px;">
                <input type="text" id="keyword" name="keyword" class="form-control input-sm"
                    placeholder="请输入要检索的内容">
            </div>
            <button type="button" class="btn btn-primary btn-sm" id="query">
             <i class="fa fa-search"></i> 检索
            </button>
        </form>
    </div>
</div>



      <div class="row">
      
    

        <div class="col-md-12">
          <div class="tile">
            <div class="tile-body">
              <table class="table table-hover table-bordered" id="tbody-result">
                <thead>
                  <tr>
                    <th><input type="checkbox" name="allChecked" id="allChecked" /></th>
                   
                    <th>ID</th>
                    <th>导航菜单名称</th>
                    <th>导航菜单图标</th>
                    <th>导航菜单超链接</th>
                    <th>导航菜单授权</th>
                    <th>操作</th>
                  </tr>
                </thead>
                <tbody id="list">
                 <!--  <tr>
                    <td>Tiger Nixon</td>
                    <td>System Architect</td>
                    <td>Edinburgh</td>
                    <td>61</td>
                  </tr>
                  -->
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </div>
    </main>
    
    
    <!-- Sidebar menu-->
    <div class="app-sidebar__overlay" data-toggle="sidebar"></div>
    <aside class="app-sidebar">
      <div class="app-sidebar__user">
        <div>
          <p class="app-sidebar__user-name">John Doe</p>
          <p class="app-sidebar__user-designation">Frontend Developer</p>
        </div>
      </div>
      <ul class="app-menu" id="show">
        
      </ul>
       <script>
    $(function(){
	     $.ajax({
			url: '<%=basePath%>yun/left/getLeftMenus',
	 		data:{},
			type: 'get',
			dataType: 'json',
			success: function(jsonDataResult) {
				
				var curPathName = window.location.pathname;
				var item="";
				var curIsExpandIdx = 0;
				jsonDataResult.forEach(function(curObj,index){
					
					item += " <li class='treeview'><a class='app-menu__item' href='#' data-toggle='treeview'><i class='app-menu__icon fa "+curObj.menuIcon+"'></i><span class='app-menu__label'>"+curObj.menuName+"</span><i class='treeview-indicator fa fa-angle-right'></i></a>";
					item +="<ul class='treeview-menu' >";
					if(curObj.children&& curObj.children.length>0){
						(function(argIdx){
							curObj.children.forEach(function(innerObj,innerIdx){
								if(innerObj.menuUrl&&innerObj.menuUrl!="" && (curPathName.toString().indexOf(innerObj.menuUrl.toString())!=-1)){
									curIsExpandIdx=argIdx;
									item += "<li><a class='treeview-item active' href='<%=basePath%>"+innerObj.menuUrl+"'><i class='icon fa "+innerObj.menuIcon+"'></i> "+innerObj.menuName+"</a></li>";
								}else{
									item += "<li><a class='treeview-item' href='<%=basePath%>"+innerObj.menuUrl+"'><i class='icon fa "+innerObj.menuIcon+"'></i> "+innerObj.menuName+"</a></li>";
								}
							})
						})(index)
					}
					item +=" </ul>";
					item +="</li>"
				});
				$('#show').append(item);
				 
				console.log(curIsExpandIdx);
				$("#show >li").eq(curIsExpandIdx).addClass("is-expanded");
				
				$(document).on('click', '#show>li', function(){
					if($(this).hasClass('is-expanded')){
						$(this).removeClass("is-expanded");
					}else{
						$(this).addClass("is-expanded");
					}
				})
				
			},
			   error: function(XMLHttpRequest, textStatus, errorThrown) {
				   alert(XMLHttpRequest.status);
				   alert(XMLHttpRequest.readyState);
				   alert(textStatus);
				     }
		})
    	
    })
</script> 


    <!-- Essential javascripts for application to work-->
    <script src="<%=basePath%>static/main/js/jquery-3.2.1.min.js"></script>
    <%-- <script src="<%=basePath%>js/jquery.min.js"></script> --%>
    <script src="<%=basePath%>static/main/js/popper.min.js"></script>
    <script src="<%=basePath%>static/main/js/bootstrap.min.js"></script>
    <script src="<%=basePath%>static/main/js/main.js"></script>
    <!-- The javascript plugin to display page loading on top-->
    <script src="<%=basePath%>static/main/js/plugins/pace.min.js"></script>
    <script src="<%=basePath%>static/js/jquery.cookie.js"></script>
    <!-- Page specific javascripts-->
    <!-- Data table plugin-->
   
    <script type="text/javascript" src="<%=basePath%>static/js/DataTables/datatables.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>static/js/dataTables.treeGrid.js"></script>
      
      <%-- 
    <script type="text/javascript" src="<%=basePath%>static/main/js/plugins/jquery.dataTables.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>static/main/js/plugins/dataTables.bootstrap.min.js"></script> --%>
   
     
     
   <!--  <script type="text/javascript">$('#tbody-result').DataTable();</script> -->
    <!-- Google analytics script-->
    <script type="text/javascript">
      if(document.location.hostname == 'www.ugaoxin.com') {
      	(function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
      	(i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
      	m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
      	})(window,document,'script','//www.google-analytics.com/analytics.js','ga');
      	ga('create', 'UA-72504830-1', 'auto');
      	ga('send', 'pageview');
      }
    </script>
    
    
    
    
    <script type="text/javascript">

    var myTable;
    $(document).ready(function() {
     
      myTable = $('#tbody-result').DataTable( {
    	  
    	  "treeGrid": {
    	        'left': 10,
    	        'expandIcon': '<span>+</span>',
    	        'collapseIcon': '<span>-</span>'
    	    },
          "processing": true,//刷新的那个对话框
          "serverSide": true,//服务器端获取数据
          "paging": true,//开启分页
          "destroy":true,
          "ordering":true, 
          order: [[ 1, "desc" ]],   //排序
          columnDefs:[
             
				
    			{  
    	        	   'targets':[0],
    	        	   'orderable':false, 
    	        	   "data" : "mId", 
    	    			   "render" : function(data, type, full, meta) {
    	    				return '<input type="checkbox" value="'+ full.mId + '" name="mId"/>';
    	    				}
    	    	},
    			{
    	         'targets':[5],
    	        'orderable':false,
    	         }
    	         	  /* 'treeGrid': {
    	         	        'left': 10,
    	         	        'expandIcon': '<span>+</span>',
    	         	        'collapseIcon': '<span>-</span>'
    	         	    } */
          ],
          lengthMenu:[
              [ 10, 20, 50 ],
              [ '10 页', '20 页', '50页' ]
          ],
          "ajax": {
              "url": "showNavList",
              "type": "POST",
              "data": function (d) {
            	  
            	   var param = {};  
            	  var searchParams;
            	   var orderId="";
                   var orderType="";
                 
                   $('#tbody-result').on( 'order.dt', function () {
                       // This will show: "Ordering on column 1 (asc)", for example
                       var order = myTable.order();
                       orderId=order[0][0];
                       orderType=order[0][1]; 
                      searchParams= {
                               "keyword":$("#keyword").val(),
                               "orderId":orderId,
                               "orderType":orderType,
                               "order":d.order,
                               "columns":d.columns 
                      
                              };  
                       
                   } ); 
                   searchParams= {
                          "keyword":$("#keyword").val(),
                          "orderId":orderId,
                          "orderType":orderType,
                          "order":JSON.stringify(d.order),
                          "columns":JSON.stringify(d.columns) 
                         };  
                 //附加查询参数
                 if(searchParams){
                     $.extend(d,searchParams); //给d扩展参数
                 }
             
              },
              "dataType" : "json",
              "dataFilter": function (json) {//json是服务器端返回的数据
                  json = JSON.parse(json);
                  var returnData = {};
                  returnData.draw = json.data.draw;
                  returnData.recordsTotal = json.data.recordsTotal;//返回数据全部记录
                  returnData.recordsFiltered = json.data.recordsTotal;//后台不实现过滤功能，每次查询均视作全部结果
                  returnData.data = json.data.list;//返回的数据列表
                  return JSON.stringify(returnData);//这几个参数都是datatable需要的，必须要
              }
          },
           "searching" : false, 
  		    "columns": [
  			  { data:function (data, type, full) {
  				var mId =data.mId;
  				return mId;
              } }, 
             
              { "data": "mId" },
              { "data": "menuName" },
              { "data": "menuIcon" }  ,
              { "data": "menuUrl" }  , 
              { "data":  "children" } , 
              {data: function (data, type, full) { 
				            	  
            	      var roleId =data.mId;
                      return "<button class=\'btn btn-primary btn-xs\' onclick='toUpdate("+JSON.stringify(data.mId)
                    		  +")'>修改</button>  <button class=\'btn-xs btn \' onclick='del("+data.mId+")'>删除</button>";
                 } 
              }
          ],
         
          "oLanguage" : { // 国际化配置
              "sProcessing" : "正在获取数据，请稍后...",
              "sLengthMenu" : "显示 _MENU_ 条",
              "sZeroRecords" : "没有找到数据",
              "stripeClasses": ["odd", "even"],
              "sInfo" : "从 _START_ 到  _END_ 条记录 总记录数为 _TOTAL_ 条",
              "sInfoEmpty" : "记录数为0",
              "sInfoFiltered" : "(全部记录数 _MAX_ 条)",
              "sInfoPostFix" : "",
              "sSearch" : "查询",
              "sUrl" : "",
              "oPaginate" : {
                  "sFirst" : "第一页",
                  "sPrevious" : "上一页",
                  "sNext" : "下一页",
                  "sLast" : "最后一页"
              }
          }
          
	   });

    });
   /*  $('#myTable').DataTable().draw();
    
     */
   /*  $('#tbody-result').on( 'order.dt', function () {
    	
       
        // This will show: "Ordering on column 1 (asc)", for example
        var order = myTable.order();
       // console.log('Ordering on column '+order[0][0]+' ('+order[0][1]+')')
    
    
    
       
    } ); */
    
   </script>
	<!-- <script type="text/javascript">
		 
		 $(document).on('click','#query',function(){
			 
			 $("#tbody-result").dataTable().fnDraw(false);
		 })
		 
	</script> -->

	 
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
      	        $("#list input[name='roleId']").each(function(i){
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
    	$.cookie('rolesDoType', "add");
      window.location.href="saveOrUp";
                    
    })
  
     
       
     // 跳转到更新页面
      function toUpdate(roles){ 
    	   
    	   var json= JSON.stringify(roles);  
    	   $.cookie('rolesDoType', json);
            window.location.href="saveOrUp";  
                    
       }
    
    </script>
    
   <!--  <script type="text/javascript">
  			$('#tbody-result').treegrid();
 </script> -->



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
  </body>
</html>