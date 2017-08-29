<!--该页面是用来存放js的引入  -->
<!-- 使用方式： 在页面的head标签用引入     @include file="/js/common_js.jsp" -->


<%
	String path = request.getContextPath();
	String htph = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/";
	request.setAttribute("basePath", path);
%>

<script type="text/javascript" src="${basePath}/easyui/jquery.min.js"></script>
<script type="text/javascript"
	src="${basePath}/easyui/jquery.easyui.min.js"></script>

<!-- ajaxForm 依赖脚本 -->
<script type="text/JavaScript" src="${basePath}/js/jquery.form.min.js"></script>
<script type="text/javascript" src="${basePath}/js/common_form.js"></script>

<script type="text/javascript" src="${basePath}/js/common_window.js"></script>
<script type="text/javascript" src="${basePath}/js/resetPw.js"></script>
<script type="text/javascript" src="${basePath}/js/cloud.js"></script>
<script type="text/javascript" src="${basePath}/js/jsapi.js"></script>
<script type="text/javascript" src="${basePath}/js/format+zh_CN,default,corechart.I.js"></script>
<script type="text/javascript" src="${basePath}/js/jquery.gvChart-1.0.1.min.js"></script>
<script type="text/javascript" src="${basePath}/js/jquery.ba-resize.min.js"></script>

<!--//引入EasyUI中文提示信息-->
<script type="text/javascript"
	src="${basePath}/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${basePath }/js/user.js"></script>
<script type="text/javascript" src="${basePath }/js/DateFormat.js"></script>
<script type="text/javascript" src="${basePath }/js/jquery.easing.min.js"></script>


<link rel="stylesheet" href="${basePath}/easyui/themes/metro-blue/easyui.css" /> 
<link rel="stylesheet" type="text/css" href="${basePath}/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="${basePath}/css/style.css">
<link rel="stylesheet" type="text/css" href="${basePath}/css/project.css">
<link rel="stylesheet" type="text/css" href="${basePath}/css/zzsc.css">
