$(function(){
	//表格高度
    $(window).resize(function() {
        $('#mytab').bootstrapTable('resetView', {
            height: tableHeight()
        })
    })
   //先销毁表格
        $('#mytab').bootstrapTable('destroy');
        //初始化表格,动态从服务器加载数据
        $("#mytab").bootstrapTable({
            method: "get",  //使用post/get请求到服务器获取数据
            url: "showNavList", //获取数据的Servlet地址
            contentType: "application/x-www-form-urlencoded;charset=utf-8",
            toolbar: "#toolbar", 
            striped: true,  //表格显示条纹
            pagination: false, //启动分页
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
        	showExport: true,  //是否显示导出,扩展 ，csv excel json
            exportDataType: "basic",   
            idField: 'mId',
            columns:[
        	{
        		title:'全选',
        		field:'select',
        		checkbox:true,
        		width:25,
        		align:'center',
        		valign:'middle'
        	},
        	{
        		title:'ID',
        		field:'mId',
//        		visible:false
        	},
        	{
        		title:'菜单名称',
        		field:'menuName',
        		sortable:true
        	},
        	{
        		title:'图标',
        		field:'menuIcon',
        		sortable:true
        	},
        	{
        		title:'URL',
        		field:'menuUrl',
        		sortable:true
        	},
        	{
        		title:'授权权限',
        		field:'menuAuthorization',
        	},
        	{
        		title:'是否系统菜单',
        		field:'isOn',
        		visible:false
        	},{
        		title:'操作',
        		field:'mId',
        		width:120,
        		align:'center',
        		valign:'middle',
        		formatter:actionFormatter,
        		sortable:true
        	}
    	],
    	 treeShowField: 'menuName',
         parentIdField: 'parentId', 
         onLoadSuccess: function(data) { 
             // jquery.treegrid.js
               $("#mytab").treegrid({
               initialState: 'collapsed',
               treeColumn: 1,
               expanderExpandedClass:'fa fa-envelope-open',
               expanderCollapsedClass:'fa fa-envelope',
               onChange: function() {
                 $("#mytab").bootstrapTable('resetWidth');
               }
             });
           }
    })
     
    
    
      //操作栏的格式化
        function actionFormatter(value, row, index) {
        	
            var id = value;
            var result = "<div  class='btn-group pull-right' style='margin-right: 20px;'>";
            result += "<a href='javascript:;' class='btn btn-xs green' onclick=\"doById(" + id + ")\" title='新增'><span class='glyphicon glyphicon-plus' aria-hidden='true'></span>新增</a>";
            result += "<a href='javascript:;' class='btn btn-xs green' onclick=\"upById(" + id + ")\" title='修改'><span class='glyphicon glyphicon-pencil' aria-hidden='true'></span>修改</a>";
            result +="</div>";
            return result;
        }
        
        
        doById = function (pid) {
        	 $.cookie('doType',"add");
        	 $.cookie('pid', pid+"");
        	 window.location.href="childrenSaveOrUp";

        }
        
        upById = function (pid) {
         $.cookie('doType',pid+""); 
         $.cookie('pid', pid+"");
       	 window.location.href="childrenSaveOrUp";

       }
        
        
    function operateFormatter(value,row,index){
     	   return jsonDateFormat(value);
         }
        
        function jsonDateFormat(jsonDate) {
            //json日期格式转换为正常格式
            var jsonDateStr = jsonDate.toString();//此处用到toString（）是为了让传入的值为字符串类型，目的是为了避免传入的数据类型不支持.replace（）方法
            try {
                var k = parseInt(jsonDateStr.replace("/Date(", "").replace(")/", ""), 10);
                if (k < 0) 
                    return null;

                var date = new Date(parseInt(jsonDateStr.replace("/Date(", "").replace(")/", ""), 10));
                var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
                var day = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
                var hours = date.getHours() < 10 ? "0" + date.getHours() : date.getHours();
                var minutes = date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
                var seconds = date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();
                var milliseconds = date.getMilliseconds();
                return date.getFullYear() + "-" + month + "-" + day + " " + hours + ":" + minutes + ":" + seconds;
            }
            catch (ex) {
                return "时间格式转换错误";
            }
        }
   

    //请求服务数据时所传参数
    function queryParams(params){
    	return{
    	    pageSize: params.limit,
    	    pageNumber:params.pageNumber,
    		//search:$('#search').val(),
    		//Tel:$('#search_tel').val() 
    		 
    	}
    }
    //查询按钮事件
    $('#search_btn').click(function(){
    	var search = $('#search').val();
    	$('#mytab').bootstrapTable('refresh', {url: 'all?search='+search});
    })
    
    //增加按钮事件
    $('#btn-add').click(function(){
		$('.tableBody').addClass('animated slideOutLeft');
		setTimeout(function(){
			$('.tableBody').removeClass('animated slideOutLeft').css('display','none');
		},500)
		$('.addBody').css('display','block');
		$('.addBody').addClass('animated slideInRight');
    })
    //删除按钮与修改按钮的出现与消失
    $('.bootstrap-table').change(function(){
    	var dataArr=$('#mytab .selected');
    	if(dataArr.length==1){
    		$('#btn-edit').css('display','block').removeClass('fadeOutRight').addClass('animated fadeInRight');
    	}else{
    		$('#btn-edit').addClass('fadeOutRight');
    		setTimeout(function(){
    			$('#btn-edit').css('display','none');
    		},400);	
    	}
    	if(dataArr.length>=1){
    		$('#btn-delete').css('display','block').removeClass('fadeOutRight').addClass('animated fadeInRight');
    	}else{
    		$('#btn-delete').addClass('fadeOutRight');
    		setTimeout(function(){
    			$('#btn-delete').css('display','none');
    		},400);	
    	}
    });
    //修改按钮事件
    $('#btn-edit').click(function(){
    	var dataArr=$('#mytab').bootstrapTable('getSelections');
    	$('.tableBody').addClass('animated slideOutLeft');
		setTimeout(function(){
			$('.tableBody').removeClass('animated slideOutLeft').css('display','none');
		},500)
		$('.changeBody').css('display','block');
		$('.changeBody').addClass('animated slideInRight');
		$('#edit_ID').val(dataArr[0].ID);
		$('#edit_LoginName').val(dataArr[0].LoginName);
		$('#edit_Name').val(dataArr[0].Name);
		$('#edit_Tel').val(dataArr[0].Tel);
		$('#edit_Email').val(dataArr[0].Email);
		if(dataArr[0].Attribute==1){
			$("#editForm input[name=Attribute]:eq(0)").prop("checked",true);
			$("#editForm input[name=Attribute]:eq(1)").prop("checked",false);
		}
		else if(dataArr[0].Attribute==2){
			$("#editForm input[name=Attribute]:eq(1)").prop("checked",true);
			$("#editForm input[name=Attribute]:eq(0)").prop("checked",false);
		}
		//先清空角色复选框
	    $('#editForm .edit input').prop('checked',false);
		//获取用户角色
		$.post('admin/Index/getUserById',
				{ID:dataArr[0].ID},
				function(data){
				   var roleIDArr=data.res.user.RoleID;
                   //将对应用户的角色列表显示到对应的修改页
                   for(var i=0;i<roleIDArr.length;i++){
                	   for(var j=0;j<$('#editForm .edit input').length;j++){
                		   if(roleIDArr[i]==$('#editForm .edit input:eq('+j+')').val()){
                			   $('#editForm .edit input:eq('+j+')').prop('checked',true);
                		   }
                	   }
                   }
		        }
		);
    })
    /*
     * 用户管理增加用户页面所有事件
    */
    //增加页面表单验证   
    // Validate the form manually
    $('#add-saveBtn').click(function() {
       //点击保存时触发表单验证
       $('#addForm').bootstrapValidator('validate');
       //如果表单验证正确，则请求后台添加用户
       if($("#addForm").data('bootstrapValidator').isValid()){
    	   var _info = $('#addForm').serialize();
    	  $.post(
				"doAdd",
				$('#addForm').serialize(),
				function(data){
					//后台返回添加成功
					if(data.suc==true){
						$('.addBody').addClass('animated slideOutLeft');
				    	setTimeout(function(){
							$('.addBody').removeClass('animated slideOutLeft').css('display','none');
						},500);
				    	$('.tableBody').css('display','block').addClass('animated slideInRight');
				    	$('#mytab').bootstrapTable('refresh', {url: 'all'});
				    	$('#addForm').data('bootstrapValidator').resetForm(true);
				    	//隐藏修改与删除按钮
						$('#btn-delete').css('display','none');
						$('#btn-edit').css('display','none');
					}
					//否则
					else{
					}
				}
			) 
       }
    });
    //增加页面返回按钮事件
    $('#add-backBtn').click(function() {
    	$('.addBody').addClass('animated slideOutLeft');
    	setTimeout(function(){
			$('.addBody').removeClass('animated slideOutLeft').css('display','none');
		},500)
    	$('.tableBody').css('display','block').addClass('animated slideInRight');  
    	$('#addForm').data('bootstrapValidator').resetForm(true);
    });
    /*
     * 用户管理修改用户页面所有事件
    */
    //修改页面回退按钮事件
    $('#edit-backBtn').click(function(){
    	$('.changeBody').addClass('animated slideOutLeft');
    	setTimeout(function(){
			$('.changeBody').removeClass('animated slideOutLeft').css('display','none');
		},500)
    	$('.tableBody').css('display','block').addClass('animated slideInRight'); 
    	$('#editForm').data('bootstrapValidator').resetForm(true);
    })
    //修改页面保存按钮事件
    $('#edit-saveBtn').click(function(){
    	$('#editForm').bootstrapValidator('validate');
    	if($("#editForm").data('bootstrapValidator').isValid()){
    		 $.post("../index.php/admin/index/updateUserById",
				$('#editForm').serialize(),
				function(data){
					if(data.suc==true){
						//隐藏修改与删除按钮
						$('#btn-delete').css('display','none');
						$('#btn-edit').css('display','none');
						//回退到人员管理主页
						$('.changeBody').addClass('animated slideOutLeft');
				    	setTimeout(function(){
							$('.changeBody').removeClass('animated slideOutLeft').css('display','none');
						},500)
				    	$('.tableBody').css('display','block').addClass('animated slideInRight'); 
				    	//刷新人员管理主页
				    	$('#mytab').bootstrapTable('refresh', {url: '../index.php/admin/index/userManagement'});
				    	//修改页面表单重置
				    	$('#editForm').data('bootstrapValidator').resetForm(true);
					}else{
					}
			    }
    		 ) 
    	}
    })
    //删除事件按钮
    $('#btn-delete').click(function(){
    	var dataArr=$('#mytab').bootstrapTable('getSelections');
    	$('.popup_de .show_msg').text('确定要删除该用户吗?');
    	$('.popup_de').addClass('bbox');
    	$('.popup_de .btn_submit').one('click',function(){
    		var ID=[];
        	for(var i=0;i<dataArr.length;i++){
        		ID[i]=dataArr[i].ID;
        	}
        	$.post("../index.php/admin/index/deleteUserById",
        			{ID:ID},
        			function(data){
        		if(data.suc==true){
        			$('.popup_de .show_msg').text('删除成功！');
					$('.popup_de .btn_cancel').css('display','none');
					$('.popup_de').addClass('bbox');
					$('.popup_de .btn_submit').one('click',function(){
						$('.popup_de').removeClass('bbox');
					})
        			$('#mytab').bootstrapTable('refresh', {url: '../index.php/admin/index/userManagement'});
        		}else{
        		}
        	});
    	})
    })
    //弹出框取消按钮事件
     $('.popup_de .btn_cancel').click(function(){
	   $('.popup_de').removeClass('bbox');
      })
    //弹出框关闭按钮事件
     $('.popup_de .popup_close').click(function(){
	   $('.popup_de').removeClass('bbox');
   　　})
})
function tableHeight() {
    return $(window).height() - 140;
}
