function modalwindow(title,width,height,url){
	this.title=title;
	this.width=width;
	this.height=height;
	this.url=url;
	var id = new Date().getMilliseconds();
	this.windowid="win_"+id;
	this.iframeid="iframe_"+id;

}
modalwindow.prototype.createWindow = function(){
	var active = document.createElement("div");
	active.id="active";
	document.body.appendChild(active);
	active.innerHTML="<div id="+this.windowid+" class=\"easyui-window\" modal='true' closed='true'  iconCls=\"icon-save\" ><iframe scrolling='yes' style='overflow-x:hidden;' id='"+this.iframeid+"' src='"+this.url+"' frameborder='0' width='100%' height='100%'></iframe></div>";

	$('#'+this.windowid).window({
		title: this.title,
		width: this.width,
		modal: true,
		shadow: false,
		minimizable:false,
		collapsible:false,
		closed: true,
		height: this.height
	});
	this.open();

};
modalwindow.prototype.open = function(){
	$('#'+this.windowid).window('open');
};
modalwindow.prototype.close = function(){
	$('#'+this.windowid).window('close');
};

//模式窗口单一实例
var modalwindow_singleobj;
//创建模式窗口
function createmodalwindow(title,width,height,url){
	modalwindow_singleobj = new modalwindow(title,width,height,url);
	modalwindow_singleobj.createWindow();

}
//关闭模式窗口
function closemodalwindow(){
	if(modalwindow_singleobj){
		modalwindow_singleobj.close();
	}
}