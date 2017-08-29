<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/js/common_jsAndcss.jsp"%>
</head>
<body>
	<script type="text/javascript">
		setWidth();
		$(window).resize(function() {
			setWidth();
		});
		function setWidth() {
			var width = ($('.leftinfos').width() - 12) / 2;
			$('.inforight').width(width);
		}

		gvChartInit();
		$(function() {
			$.ajax({
				cache : false,
				async : false,
				type : "GET",
				dataType : "json",
				url : "/GJZB/getDefault",
				success : function(result) {
					//用于报表
					if(result.map != null){
						var $tr_name = $("#chartName");
						var $tr_value = $("#charValue");
						for ( var k in result.map) {
							var $th_name = $("<th></th>");
							var $td_value = $("<td></td>");
							$th_name.html(k);
							$tr_name.append($th_name);
							$td_value.html(result.map[k]);
							$tr_value.append($td_value);
						}
					}
					
					//最新发布的项目
					if(result.project != null){
						var $ul = $("#newProject");
						for(var i=0; i<result.project.length;i++){
							var $li = $("<li ></li>");
							var pro = result.project[i];
							var $a = $("<a href='javaScript:void(0);' name='' id='' target='_self' style='line-height:46px; font-size:18px;'></a>");
							var a_href = "/GJZB/Project/getProjectByProjectId?projectId=" + pro.projectId; 
							$a.html(pro.projectName);
							$a.attr('name',a_href);
							$a.attr('id',pro.projectName);
							var $b = $("<b></b>");
							$b.html(new Date(pro.releaseTime).Format("yyyy-MM-dd"));
							$a.click(function(){
								if ($('#tabs').tabs('exists', $(this).attr('id'))) {
									$('#tabs').tabs('select', $(this).attr('id'));
								} else{
									$('#tabs').tabs('add', {
										title : $(this).attr('id'),
										closable : true,
										href : $(this).attr('name'),
									});
								}
							})
							$li.append($a);
							$li.append($b);
							$ul.append($li);
						}
					}//最新项目	
					
					//热门项目
					if(result.hot_pro != null){
						var $ul = $("#hotProject");
						for(var i=0; i<result.hot_pro.length;i++){
							var $li = $("<li ></li>");
							var pro = result.project[i];
							var $a = $("<a href='javaScript:void(0);' name='' id='' target='_self' style='line-height:46px; font-size:18px;'></a>");
							var a_href = "/GJZB/Project/getProjectByProjectId?projectId=" + pro.projectId; 
							$a.html(pro.projectName);
							$a.attr('name',a_href);
							$a.attr('id',pro.projectName);
							var $b = $("<b></b>");
							$b.html(new Date(pro.releaseTime).Format("yyyy-MM-dd"));
							$a.click(function(){
								if ($('#tabs').tabs('exists', $(this).attr('id'))) {
									$('#tabs').tabs('select', $(this).attr('id'));
								} else{
									$('#tabs').tabs('add', {
										title : $(this).attr('id'),
										closable : true,
										href : $(this).attr('name'),
									});
								}
							})
							$li.append($a);
							$li.append($b);
							$ul.append($li);
						}
					}//热门项目	
					
					//大神级别的人物
					//<li><i>会员数：</a></i>2535462</li>
					if(result.hot_user != null){
						$('#proTg').datagrid({    
						    data:result.hot_user,
						    columns:[[    
						        {field:'userName',title:'用户名',width:100,
						        	formatter : function(value, row,index) {
										return "<a href=javascript:deleteuser('"
												+ row.userId
												+ "')>"+value+"</a>";
										}
						        },    
						        {field:'winCount',title:'中标次数',width:100},    
						        {field:'money',title:'金钱',width:100}    
						    ]]    
						}); 
					}//大神级别的人物
					
				}
			});

			jQuery('#myTable5').gvChart({
				chartType : 'PieChart',
				gvSettings : {
					width : 650,
					height : 250
				}
			});
		});
		
		//获取更多的最新项目
		function morePorjects(){
			if ($('#tabs').tabs('exists', '最新项目')) {
				$('#tabs').tabs('select','最新项目');
			} else{
				$('#tabs').tabs('add', {
					title : $(this).attr('最新项目'),
					closable : true,
					href : 'jsp/bid/bid.jsp',
				});
			}
		}
	</script>
	<div class="mainbox">

		<div class="mainleft">
			<div class="leftinfo" >
				<div class="listtitle">
					<a href="#" class="more1">更多</a>数据统计
				</div>
				<div class="maintj">
					<table id='myTable5'>
						<thead>
							<tr id="chartName">
								<th></th>
							</tr>
						</thead>
						<tbody>
							<tr id="charValue">
								<th></th>
							</tr>
						</tbody>
					</table>
				</div>
			</div>


			<div class="leftinfos" style="width:100%" >


				<div class="infoleft" style="height:280px;width:100%;">

					<div class="listtitle"  style="width:100%">
						<a href="javascript:morePorjects()" class="more1">更多</a>最新项目发布
					</div>
					<ul class="newlist" style="height:250px, width:100%" id="newProject"></ul>

				</div>





			</div>

		</div>
		<!--mainleft end-->
		
		<div class="mainright" style="width:30%;">


			<div class="dflist" style="width:100%;">
				<div class="listtitle">
					<a href="#" class="more1">更多</a>热门项目
				</div>
				<ul class="newlist" style="height:250px, width:100%" id="hotProject">
				</ul>
			</div>


			<div class="dflist1" style="width:100%;">
				<div class="listtitle">
					<a href="#" class="more1">更多</a>大神级别的人物
				</div>
				<table id="proTg"></table> 
			</div>





		</div>
		<!--mainright end-->


	</div>



</body>
</html>
