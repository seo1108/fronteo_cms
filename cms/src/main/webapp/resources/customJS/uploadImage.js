/*

function doAddImage(obj,data,name){
	//alert('data.divFlag:['+data.divFlag+']/data.innerName:['+data.innerName+']/data.viewflag:['+data.viewFlag+']');
	if(data != null ){
		if(data.errorMsg==""){
			var qry="";
			var qryImaName="";
			var doScripts =data.script;
			if(data.viewFlag=="profile"){
				qry = 
					"<div class='loveProfile_edit'>\n"+
			  		"	<div class='close_btn_popup_w5'>\n"+
	 		  		"		<input type='hidden' id='contentImgID' name='contentImgID' value='" + data.contentId + "'  /> \n" +
			  		"		<a href=\"javascript:deleteImangeContent('"+data.contentId+"','div"+data.contentId+"','"+data.viewFlag+"','"+data.innerName+"');\"><img src='css/cssImg/cross.png' width='12' height='12' ></a>\n"+
			  		"	</div>\n"+
			  		"	<img src='"+contextPath+data.thumbnail_url+"' width='240' height='240' alt='"+data.name+"' onclick=\"javascript:showImageFile('"+contextPath+data.url+"','"+data.name+"');\">\n"+
			 	    "</div>\n" +
			 	    "<img src='"+contextPath+data.thumbnail_url+"' width='240' height='240' alt='"+data.name+"'>\n";
			}else if(data.viewFlag=="whisper"){
		 		  qry = 
						"<div class='addbox_img' id='div"+ data.contentId+"'>\n"+
				  		"	<div class='close_btn_popup_w3'>\n"+
		 		  		"		<input type='hidden' id='contentImgID' name='contentImgID' value='" + data.contentId + "'  /> \n" +
				  		"		<a href=\"javascript:deleteImangeContent('"+data.contentId+"','div"+data.contentId+"','"+data.viewFlag+"','"+data.innerName+"');\"><img src='css/cssImg/cross.png' width='12' height='12' ></a>\n"+
				  		"	</div>\n"+
				  		"	<img src='"+contextPath+data.thumbnail_url+"' width='48' height='48' alt='"+data.name+"');\">\n"+
				 	    "</div>\n";
			}else if(data.viewFlag=="diary"){
		 		  qry = 
						"<div class='addbox_img' id='div"+ data.contentId+"'>\n"+
				  		"	<div class='close_btn_popup_w3'>\n"+
		 		  		"		<input type='hidden' id='contentImgID' name='contentImgID' value='" + data.contentId + "'  /> \n" +
				  		"		<a href=\"javascript:deleteImangeContent('"+data.contentId+"','div"+data.contentId+"','"+data.viewFlag+"','"+data.innerName+"');\"><img src='css/cssImg/cross.png' width='12' height='12' ></a>\n"+
				  		"	</div>\n"+
				  		"	<img src='"+contextPath+data.thumbnail_url+"' width='48' height='48' alt='"+data.name+"');\">\n"+
				 	    "</div>\n";
			}else if(data.viewFlag=="message"){
		 		  qry = 
					"<div class='addbox_img' id='div"+ data.contentId+"'>\n"+
			  		"	<div class='close_btn_popup_w3'>\n"+
	 		  		"		<input type='hidden' id='contentImgID' name='contentImgID' value='" + data.contentId + "'  /> \n" +
			  		"		<a href=\"javascript:deleteImangeContent('"+data.contentId+"','div"+data.contentId+"','"+data.viewFlag+"','"+data.innerName+"');\"><img src='css/cssImg/cross.png' width='12' height='12' ></a>\n"+
			  		"	</div>\n"+
			  		"	<img src='"+contextPath+data.thumbnail_url+"' width='48' height='48' alt='"+data.name+"');\">\n"+
			 	    "</div>\n";
			}else if(data.viewFlag=="album"){
		 		  qry = 
					"<div class='addbox_img' id='div"+ data.contentId+"'>\n"+
			  		"	<div class='close_btn_popup_w3'>\n"+
	 		  		"		<input type='hidden' id='contentImgID' name='contentImgID' value='" + data.contentId + "'  /> \n" +
			  		"		<a href=\"javascript:deleteImangeContent('"+data.contentId+"','div"+data.contentId+"','"+data.viewFlag+"','"+data.innerName+"');\"><img src='css/cssImg/cross.png' width='12' height='12' ></a>\n"+
			  		"	</div>\n"+
			  		"	<img src='"+contextPath+data.thumbnail_url+"' width='48' height='48' alt='"+data.name+"');\">\n"+
			 	    "</div>\n";
		 	//어드민의 공지사항등록.	 
			}else if(data.viewFlag=="notice"){
		 		  qry = 
					"<div class='addbox_img' id='div"+ data.contentId+"'>\n"+
			  		"	<div class='close_btn_popup_w3'>\n"+
	 		  		"		<input type='hidden' id='contentImgID' name='contentImgID' value='" + data.contentId + "'  /> \n" +
			  		"		<a href=\"javascript:noticeDeleteImangeContent('"+data.contentId+"','div"+data.contentId+"','"+data.viewFlag+"','"+data.innerName+"');\"><img src='css/cssImg/cross.png' width='12' height='12' ></a>\n"+
			  		"	</div>\n"+
			  		"	<img src='"+contextPath+data.thumbnail_url+"' width='48' height='48' alt='"+data.name+"');\">\n"+
			 	    "</div>\n";
		 		 qryImaName = "<div id='url"+data.contentId+"'>"+data.url+"</div>";
		 		$('#imangeurl').append(qryImaName);
			}else if(data.viewFlag=="profile_lover"){
				var obj = $('#profile_lover_img');
				var imgTag =  "<img id='"+obj.attr('id')+"' src='"+contextPath+data.thumbnail_url+"' width='235' height='235'/>";
				$('#profile_lover_img').replaceWith(imgTag);
				resizeCropMe($('#profile_lover_img'));
				$('#loverContentId').val(data.contentId);
			}else if(data.viewFlag=="profile_idealType"){
				var obj = $('#profile_idealType_img');
				var imgTag =  "<img id='"+obj.attr('id')+"' src='"+contextPath+data.thumbnail_url+"' width='235' height='235'/>";
				$('#profile_idealType_img').replaceWith(imgTag);
				resizeCropMe($('#profile_idealType_img'));
				$('#loverContentId').val(data.contentId);
			}else if(data.viewFlag=="profile_sample"){
				qry="";
			}else {
		 		  qry = 
						"<div class='addbox_img' id='div"+ data.contentId+"'>\n"+
				  		"	<div class='close_btn_popup_w3'>\n"+
		 		  		"		<input type='hidden' id='contentMovID' name='contentMovID' value='" + data.contentId + "'  /> \n" +
				  		"		<a href=\"javascript:deleteVideoContent('"+data.contentId+"','div"+data.contentId+"');\"><img src='css/cssImg/cross.png' width='12' height='12' ></a>\n"+
				  		"	</div>\n"+
				  		"	<img src='"+contextPath+data.thumbnailURL+"' width='48' height='48' onload='javascript:resizeCropMe($(this));'>\n"+
				 	    "</div>\n";
			}
			if(data.divFlag=="append"){
				$('#'+data.innerName).append(qry);
			}else if(data.divflag=="replace"){
				//alert('replace');
				document.getElementById(data.innerName).innerHTML = qry;
			}
			if(doScripts != ""){
				eval(doScripts);
			}
		}else{
			//Error가 발생하면 checkSessionExpired에서 보여줌 여기서는 메세지 보여주는 부분 제외..
			//alert(data.errorMsg);
			if(data.errorType=="sessionExpired"){
				document.location.href="index";
			}
		}
	}
}

function deleteImageContent(contentId,divId,viewFlag,parentDivId) {
	
	var targetUrl = 'json/deleteImageContent';
	var params = {CONTENTID:contentId
					,divId:divId
					,viewFlag:viewFlag
					,parentDivId:parentDivId
					};
	syncJson(params,targetUrl,function(data){deleteImageContentCallBack(data);});
}

function deleteImageContentCallBack(data){
	if(data.STATUS=='REMOVE'){
		$('#'+data.divId).remove();
	}else	if(data.STATUS=='Error'){
//		alert(data.MESSAGE);
	}
}

function deleteContentGroup(contentGroupId,contentId,divId,viewFlag,parentDivId) {
	
	var targetUrl = 'json/deleteImageContent';
	var params = { CONTENTGROUPID:contentGroupId
			        ,CONTENTID:contentId
					,divId:divId
					,viewFlag:viewFlag
					,parentDivId:parentDivId
					};
	syncJson(params,targetUrl,function(data){deleteContentGroupCallBack(data);});
}

function deleteContentGroupCallBack(data){
	if(data.STATUS=='REMOVE'){
		$('#'+data.divId).remove();
	}else	if(data.STATUS=='Error'){
//		alert(data.MESSAGE);
	}
}

function deleteAllImangeContent(divId,viewFlag) {
	
	var contentIdAr = [];
	$("[id=contentImgID]").each(function(){contentIdAr.push($(this).val());});
	var targetUrl = 'json/deleteAllImageContent';
	var params = {contentIdAr:contentIdAr,divId:divId,viewFlag:viewFlag};
	syncJson(params,targetUrl,function(data){deleteAllImageContentCallBack(data);});
}

function deleteAllImageContentCallBack(data){
	if(data.STATUS=='REMOVE'){
		if(data.viewFlag=='album'){
			$("#"+data.divId+" .addbox_img").each(function(){
				$(this).remove();
			});
		}else{
			$("#"+data.divId+" .addbox_img").each(function(){
				$(this).remove();
			});
			$("#"+data.divId).hide();
		}
	}else	if(data.STATUS=='Error' || data.STATUS=='ALERT'){
//		alert(data.MESSAGE);
	}
}

function progressBarStartImage() {
	$('#ProgressBarDiv').show();
	$('#ProgressBarImage').attr('src', 'img/loading_after.gif');
}

function progressBarEndImage() {
	$('#ProgressBarImage').attr('src',  'img/loading_before.gif');
	$('#ProgressBarDiv').hide();
}
*/

function itemImageUploadPreCheck(formName, imgPer, multiUploadflag, imageMenu, itemSeq, token, uploadType, elementId) {
	if ('MAIN' == getRadioValue('pictureRadios')) {
		if (!$('#mainPicDiv').is(':visible')) {
			imageUpload(formName, imgPer, multiUploadflag, imageMenu, itemSeq, token, uploadType, elementId);
			return true;
		} else {
			alert('메인이미지는 하나만 등록가능합니다.');
			return false;
		}
	} else {
		imageUpload(formName, imgPer, multiUploadflag, imageMenu, itemSeq, token, uploadType, elementId);
		return true;
	}
}


function imageUpload(formName, imgPer, multiUploadflag, imageMenu, relativeSeq, token, uploadType, elementId){
	//var fileList = 'files_' + suffix;
	$('#'+formName).fileupload({
		url:'../image/uploadS3Image',
		maxFileSize: 10000000,//
		type:'POST',
		dataType: 'json',
		singleFileUploads:eval(multiUploadflag), /* true:multi upload, false:single upload */
		submit: function (e, data) {
			data.formData = {
					imgPer		:imgPer
					,imageMenu	:imageMenu
					,relativeSeq:relativeSeq
					,token		:token
					,uploadType	:uploadType
					,elementId	:elementId};
		},
		done: function (e, data) {
			var jsonData = (data.jqXHR.responseText) ? jQuery.parseJSON(data.jqXHR.responseText) : data.result;
			
			if (jsonData.uploadType == 'ITEM' || jsonData.uploadType == 'HOTEL' || jsonData.uploadType == 'PACKAGE') {
				appendItemImage(jsonData.relativeSeq, jsonData.contentSeq, jsonData.imageUrl, jsonData.realUploadPath);
			} else if (jsonData.uploadType == 'MENU') {
				appendMenuImage(jsonData.contentSeq, jsonData.realUploadPath + jsonData.imageUrl, jsonData.elementId);
			}
		},			 
		drop: function (e, data) {
			$.each(data.files, function (index, file) {
			});
		},
		change: function (e, data) {
			$.each(data.files, function (index, file) {
			});
		},
		fail: function (e, data) {
			alert("Error: " + data.errorThrown + " Text-Status: " + data.textStatus);
		},
		start: function (e) {
//            	 progressBarStartImage();
		}, 
		stop: function (e) {
//           	  progressBarEndImage();
		}
	});
	
}

function appendItemImage(itemSeq, contentId, imageUrl, realUploadPath) {
	if ('MAIN' == getRadioValue('pictureRadios')) {
		var html = 
			"	<img id='"+contentId+"' class='img-responsive' src='" + realUploadPath + imageUrl + "' alt='Photo'>" +
			"	<div class='main'><a href='#'><img src='../../dist/img/custom/btn_main.png' width='30px' height='30px'></img></a></div>" +
			"	<div class='delete' onclick=\"javascript:deleteCategoryItemImage('"+itemSeq+"','"+contentId+"','MAIN','"+imageUrl+"');\"><a><img src='../../dist/img/custom/btn_trashcan.png'></img></a></div>";
				
			
			$('#mainPicDiv').append(html);
			$('#mainPicDiv').show();
	} else {
		var html = 
			"<div id='"+contentId+"' class='col-sm-3 item-pic' style='padding-bottom:10px;'>" +
			"	<img class='img-responsive' src='" + realUploadPath + imageUrl + "' alt='Photo'>" +
			"	<div class='delete' onclick=\"javascript:deleteCategoryItemImage('"+itemSeq+"','"+contentId+"','SUB','"+imageUrl+"');\"><a><img src='../../dist/img/custom/btn_trashcan.png'></img></a></div>" +
			"</div>";
			
		$('#subPicDiv').append(html);
	}
}

function checkMainImageRegist() {
	if (!$('#mainPicDiv').is(':visible')) {
		alert("메인 이미지를 등록해주세요.");
		return false;
	} else {
		return true;
	}
}

function changeClass(id, className) {
	$('#'+id).addClass(className);
}

function appendMenuImage(contentSeq, imageUrl, elementId) {
	$('#menuImg'+elementId).attr('src',imageUrl);
	$('#menuContentSeq'+elementId).val(contentSeq);
//	}
}