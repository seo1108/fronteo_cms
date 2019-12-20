jQuery(document).ready(function(){
/*	var URLString = top.location.href + "/";
	tURLString = URLString.split("/");
	iii = tURLString.length > 2 ? (tURLString[3].length > 0 ? false:true) : false
	if(!iii){
	    document.cookie = "UrlString=" + top.window.location.href + "; path=/;";
	    top.window.location.href = "http://"+top.window.location.host;
	}*/

	var regexp = new RegExp(document.location.pathname, 'i');

	$('li.treeview').each(function () {
		var tmpExp = new RegExp($(this).data('uri'), 'i');
		if ( tmpExp.test( document.location.pathname ) ) {
			$(this).addClass('active');
			$(this).find('ul.treeview-menu').addClass('active');
			return false;
		}
	});

	$('ul.treeview-menu>li').each(function () {
		if ( regexp.test( $(this).children('a:first').attr('href') ) ) {
			$(this).addClass('active');
			return false;
		}
	});
});


function getContextPath(){
    var offset=location.href.indexOf(location.host)+location.host.length;
    var ctxPath=location.href.substring(offset,location.href.indexOf('/',offset+1));
    return ctxPath;
}

function pleaseWait(divId){
}

function pleaseWaitEnd(divId){
}

function errorCtl(xhr, textStatus, errorThrown){
	//alert("___"+xhr.html + xhr.responseText+"/"+textStatus +'/An error occurred! ' + (errorThrown ? errorThrown : xhr.status));
	console.log("___"+xhr.html + xhr.responseText+"/"+textStatus +'/An error occurred! ' + (errorThrown ? errorThrown : xhr.status));
}

function asyncJson(params, url, callBack){
	pleaseWait(null);

	var options = {
		url         : url
		,contentType :'application/x-www-form-urlencoded;charset=UTF-8'
		,type        :'POST'
		,dataType    :'json'
		,complete    : function (data){
			try {
				//var json = JSON.stringify(data.responseText);
				var jsonData = JSON.parse(data.responseText);
				
				if(jsonData.errorType == null || jsonData.errorType == 'undefined' || jsonData.errorType ==''){
					if(typeof callBack=='function'){
						callBack(jsonData);
			        }
				}else{
					if(jsonData.errorType=='sessionExpired'){
						alert("세션이 만료되었습니다.");
						window.location.href="../index";
					}
					
				}
				pleaseWaitEnd(null);
			} catch (err) {
				//alert(err);
			}	
		}
		,error       : errorCtl
	};
	$.ajaxSetup(options);
	$.ajax({data:params});
}

function syncJson2(params, url, callBack) {
	pleaseWait(null);
	$.ajaxSetup({
		 url         : url
		,contentType :'application/x-www-form-urlencoded;charset=UTF-8'
		,type        :'POST'
		,dataType    :'JSON'
		,async       : false
		,error       : errorCtl
		,complete    : function (data){
			var jsonData = JSON.parse(data.responseText);
			if(!jsonData.errorType || jsonData.errorType == 'undefined') {
				if(typeof callBack == 'function') {
					callBack(jsonData);
		        }
			} else {
				if(jsonData.errorType=='sessionExpired') {
					window.location.href="index";
				}
				alert(jsonData.errorMsg);
			}
			pleaseWaitEnd(null);
		}
	});
	$.ajax({data:params});
}

//이메일 유효 체크 (@xxx.xxx)
function checkEmail(obj){
	var t = escape($('#'+obj).val());
	t = 'validcheck'+t.substring(t.indexOf('@'));
	if(t.match(/^(\w+)@(\w+)[.](\w+)$/ig) == null && t.match(/^(\w+)@(\w+)[.](\w+)[.](\w+)$/ig) == null){
//	if(t.match(/^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/) == null){
		$('#'+obj).focus();
		return false;
	} else {
		return true;
	}
}

function checkMobile(inputtxt) {  
	inputtxt = inputtxt.replace(/ /g, '');
	inputtxt = inputtxt.replace(/-/g,"");
	
	var pattern = /^\d+$/;
    
	if (!pattern.test(inputtxt)) {
		return false;
	} else if (inputtxt.length < 9) {
		return false;
	} else {	
		return true;
	}
}  



function sleep(num){	//[1/1000초]
	var now = new Date();
	var stop = now.getTime() + num;
	while(true){
		now = new Date();
		if(now.getTime() > stop)return;
	}
}

(function($){
	$.goPost = function(url, obj){
		var $this;
		$('BODY').append(
			($this = $("<FORM>")).attr({
				  action : url
				, method : "POST"
			}).submit(function() {
				if(obj) {
					$.map(obj, function(value, key) {
						$this.append($("<INPUT>").attr({
							  name  : key
							, value : value
						}).css('visibility', 'hidden'));
					});
				}
			})
		);
		$this.submit();
	};
	
	$.numberWithCommas = function (x) {
	    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
	};
})(jQuery);

function getRadioValue(name) {
    if( $('input[name='+name+']:radio:checked').length > 0 ) {
        return $('input[name='+name+']:radio:checked').val();
    }
    else {
        return 0;
    }
}

function getContextPath(){
//	var hostIndex = location.href.indexOf( location.host ) + location.host.length;
//	return location.href.substring( hostIndex, location.href.indexOf('/', hostIndex + 1) );
	
	var offset=location.href.indexOf(location.host)+location.host.length;
    var ctxPath=location.href.substring(offset,location.href.indexOf('/',offset+1));
    return ctxPath;
}

function addComma(num) {
	if (num == null) return '';
	var regexp = /\B(?=(\d{3})+(?!\d))/g;
	return num.toString().replace(regexp, ',');
}

function isFloat(value) {
    if (isNaN(value) || value.toString().indexOf(".") < 0) {
        return false;
    } else {
        if (parseFloat(value)) 
        	return true;
        else
            return false;
    }
}

function isInt(n){
	return Number(n).toString() === n.toString() && n % 1 === 0;
}

function pad(n, width) {
	n = n + '';
	return n.length >= width ? n : new Array(width - n.length + 1).join('0') + n;
}
/*function isFloat(n){
	return Number(n).toString() === n.toString() && n % 1 !== 0;
}*/