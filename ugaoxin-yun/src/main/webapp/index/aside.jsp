<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
    </aside>
   
   