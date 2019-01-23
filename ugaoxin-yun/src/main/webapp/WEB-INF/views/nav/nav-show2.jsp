<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!DOCTYPE html>
<html lang="en"> 
<head>
  <meta charset="utf-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta content="width=device-width,initial-scale=1.0" name="viewport">
  <meta content="yes" name="apple-mobile-web-app-capable">
  <meta content="black" name="apple-mobile-web-app-status-bar-style">
  <meta content="telephone=no" name="format-detection">
  <meta content="email=no" name="format-detection">
  <title>系统管理</title>
  
</head>

 <body class="app sidebar-mini rtl">
    <!-- Navbar--> 
    <%@ include file="../../../index/header.jsp" %>
    <%@ include file="../../../index/aside.jsp" %> 
    <main class="app-content">
      <div class="app-title">
        <div>
          <h1><i class="fa fa-th-list"></i>管理员管理</h1>
          <p>通用云平台的列表展示</p>
        </div>
        <ul class="app-breadcrumb breadcrumb side">
          <li class="breadcrumb-item"><i class="fa fa-home fa-lg"></i></li>
          <li class="breadcrumb-item">Tables</li>
          <li class="breadcrumb-item active"><a href="#">Data Table</a></li>
        </ul>
      </div>
      
      
      
  <div class="container">
    <h1>Table Treegrid</h1>
    <table id="table"></table>
  </div>
</body>


<link rel="stylesheet" href="<%=basePath%>static/bootstrap/bootstrap4/css/bootstrap.min.css">
  <link rel="stylesheet" href="<%=basePath%>static/bootstrap/bootstrap-table/bootstrap-table.min.css">

   <link rel="stylesheet" href="<%=basePath%>static/bootstrap/jquery.treegrid/jquery.treegrid.css">
     <link rel="stylesheet" href="<%=basePath%>static/bootstrap/tree-column/bootstrap-table-tree-column.css">
     <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
     
     
<script src="<%=basePath%>static/bootstrap/jquery.min.js"></script>
<script src="<%=basePath%>static/bootstrap/bootstrap4/js/bootstrap.min.js"></script>
<script src="<%=basePath%>static/bootstrap/bootstrap-table/bootstrap-table.min.js"></script>
<script src="<%=basePath%>static/bootstrap/bootstrap-table/extensions/treegrid/bootstrap-table-treegrid.min.js"></script>

<script src="<%=basePath%>static/bootstrap/jquery.treegrid/jquery.treegrid.min.js"></script>
 <script src="<%=basePath%>static/bootstrap/tree-column/bootstrap-table-tree-column.min.js"></script> 

<script type="text/javascript">
  var $table = $('#table');
  $(function() {
	  $('#table').bootstrapTable('destroy');
    $table.bootstrapTable({
    	url: 'showNavList',
    	contentType: "application/x-www-form-urlencoded;charset=utf-8",
    	 method: "get",  //使用get请求到服务器获取数据
    	 //toolbar: '#toolbar',
        striped: true,  //表格显示条纹
        pagination: true, //启动分页
        pageSize: 10,  //每页显示的记录数
        pageNumber:1, //当前第几页
        pageList: [5, 10, 15, 20, 25],  //记录数可选列表
        search: false,  //是否启用查询
        strictSearch: true,
        showColumns: true,  //显示下拉框勾选要显示的列
        showRefresh: true,  //显示刷新按钮
        sidePagination: "server", //表示服务端请求
        //设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder
        //设置为limit可以获取limit, offset, search, sort, order
        queryParamsType : "undefined", 
        queryParams:queryParams,
        clickToSelect: true,//是否启用点击选中行
    	toolbarAlign:'right',
    	buttonsAlign:'right',//按钮对齐方式
    	toolbar:'#toolbar',//指定工作栏
    	showExport: true,                     //是否显示导出
        exportDataType: "basic",  
      height: $(window).height(),
      
      sidePagination: 'server',
      idField: 'mId',
      columns: [
        {
          field: 'mId',
          checkbox: true
        },
        {
          field: 'menuName',
          title: '名称'
        },
        // {field: 'id', title: '编号', sortable: true, align: 'center'},
        // {field: 'pid', title: '所属上级'},
        {
          field: 'menuUrl',
          title: 'URL',
          sortable: true,
          align: 'center',
          formatter: 'statusFormatter'
        },
        {
          field: 'menuAuthorization',
          title: '权限值'
        }
      ], 

      // bootstrap-table-treegrid.js 插件配置
      treeShowField: 'menuName',
      parentIdField: 'parentId',
      onLoadSuccess: function(data) {
      
        // jquery.treegrid.js
        $table.treegrid({
          initialState: 'collapsed',
          treeColumn: 1,
          expanderExpandedClass: 'fa fa-minus',
          expanderCollapsedClass:'fa fa-plus',
          onChange: function() {
            $table.bootstrapTable('resetWidth');
          }
        });
      }
      // bootstrap-table-treetreegrid.js 插件配置
    });
  });

  
  //请求服务数据时所传参数
  function queryParams(params){
  	return{
  	    pageSize: params.limit,
  	    pageNumber:params.pageNumber,
  		//search:$('#search').val(),
  		//Tel:$('#search_tel').val() 
  		 
  	}
  }
  

  // 格式化类型
  function typeFormatter(value, row, index) {
    if (value === 'menu') {
      return '菜单';
    }
    if (value === 'button') {
      return '按钮';
    }
    if (value === 'api') {
      return '接口';
    }
    return '-';
  }

  // 格式化状态
  function statusFormatter(value, row, index) {
    if (value === 1) {
      return '<span class="label label-success">正常</span>';
    } else {
      return '<span class="label label-default">锁定</span>';
    }
  }
</script>

</html>
