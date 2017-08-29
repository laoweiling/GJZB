/*
*form提交(post方式)
*
*formId form Id
*callbackfn 回调函数名(要求函数必须有参数且不能多与两个,一个参数时参数为响应文本,两个参数时第一个参数为响应文本)
*param 回调函数参数(如果为null,那么调用一个参数的回调函数,否则调用两个参数的回调函数)
*dataType：预期服务器返回的数据类型
*/
function jquerySubByFId(formId,callbackFn,param,dataType){
	var formObj = jQuery("#" + formId);
	var options = {
            dataType:  ("undefined"!=dataType && null!=dataType)?dataType:"json",
			success: function(responseText) {
				if(param === null){
					callbackFn(responseText);
				}else{
					callbackFn(responseText,param);
				}
			}
	};
	formObj.ajaxSubmit(options); 
}