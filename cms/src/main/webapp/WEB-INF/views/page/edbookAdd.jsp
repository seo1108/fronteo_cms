<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ page import="java.util.Map"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="org.apache.commons.logging.Log"%>
<%@ page import="org.apache.commons.logging.LogFactory"%>
<%@ page import="org.springframework.context.ApplicationContext"%>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils"%>

<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 

<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>eDiscovery Book</title>
    
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.5 -->
    <link rel="stylesheet" href="../resources/bootstrap/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="../resources/dist/css/AdminLTE.min.css">
    <!-- AdminLTE Skins. Choose a skin from the css/skins
         folder instead of downloading all of them to reduce the load. -->
    <link rel="stylesheet" href="../resources/dist/css/skins/_all-skins.min.css">
    <link rel="stylesheet" href="../resources/plugins/select2/select2.min.css">
    <link rel="stylesheet" href="../resources/plugins/jQuerySimpleDPicker/jquery.simple-dtpicker.css">
    <link rel="stylesheet" href="../resources/dist/css/custom.css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  <body class="hold-transition skin-blue sidebar-mini">
    <div class="wrapper">

      <%@ include file ="../_header.jsp" %>
      
      <%@ include file ="../_leftMenu.jsp" %>
 
      <!-- Content Wrapper. Contains page content -->
      <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
          <h1>
          	<c:choose>
                <c:when test="${not empty data.type && data.type eq 'edit'}">
                	eDiscovery Book 챕터 편집
                </c:when>
                <c:otherwise>
                	eDiscovery Book 챕터 등록
                </c:otherwise>
            </c:choose>    
						                 
          	
		  </h1>
        </section>

        <!-- Main content -->
        <section class="content">
        
        <div class="row">
			<div class="col-xs-12">
		        <div class="col-xs-12 box box-danger">
		        	<form name="frm" id="frm" method="post" enctype="multipart/form-data" action="">
		        		<input type="hidden" name="updateType" id="updateType" value="${data.type }" />
		        		<input type="hidden" name="bbsType" id="bbsType" value="F" />
						<div class="card-body">
							<table class="table table-striped">
						        <colgroup>
						          <col width="10%"/>
								  <col width="90%"/>
							    </colgroup>
						       <tbody>
						       	<tr>
					                 <th class="quiztable-content-center-white">NO.</th>
						             <td>
						             	<input type="text" name="chapterSeq" id="chapterSeq" value="${data.chapterSeq}" class="form-control" style="display:inline;" readOnly/>
						             </td>
					              </tr>		
					              <tr>
					                 <th class="quiztable-content-center-white">작성일</th>
						             <td>
						             	<input type="text" name="date" id="regdate" value="${data.regdate}" class="form-control" style="display:inline;" readOnly/>
						             </td>
					              </tr>		
					              <tr>
					                 <th class="quiztable-content-center-white">제목</th>
						             <td>
						             	<input type="text" name="title" id="title" value="${data.title}" class="form-control" style="display:inline;"/>
						             </td>
					              </tr>		
						          <tr>
					                 <th class="quiztable-content-center-white">상태</th>
					                 <c:choose>
						                 <c:when test="${not empty data.type && data.type eq 'edit'}">
								            <td>
								             	<input type="radio" name="exposure" value="Y" style="margin-right: 10px;"
								             		<c:if test="${data.exposure eq 'Y'}">checked</c:if>><span>노출</span>
								                <input type="radio" name="exposure" value="N" style="margin-right: 10px; margin-left: 50px;"
													<c:if test="${data.exposure eq 'N'}">checked</c:if>><span>비노출</span>
								             </td>   
						                 </c:when>
						                 <c:otherwise>
						                 	<td>
								             	<input type="radio" name="exposure" value="Y" style="margin-right: 10px;"/><span>노출</span>
								                <input type="radio" name="exposure" value="N" style="margin-right: 10px; margin-left: 50px;" checked /><span>비노출</span>
								            </td>   
						                 </c:otherwise>
					                 </c:choose>
					              </tr>		
						          <tr>
					                 <th class="quiztable-content-center-white">내용</th>
						             <td><textarea name="contents" id="contents" rows="22" cols="170">${data.contents}</textarea></td>
					              </tr>
					              <tr>
					                 <th class="quiztable-content-center-white">다운로드 링크</th>
						             <td>
						             	<input type="text" name="url" id="url" value="${data.url}" class="form-control" style="display:inline;"/>
						             </td>
					              </tr>		
							   </tbody>
				           </table>
						</div>
					</form>
					
					<div style="float:right;padiing-top:100px;padding-top:10px;padding-bottom:10px;">
						<button class="btn btn-info" style="margin-right:10px;" onclick="preview('${data.chapterSeq}', '${data.type}', '${data.previewUrl}');">미리보기</button>
						<button class="btn btn-success" style="margin-right:10px;" onclick="go_list();">목록으로 가기</button>
						<c:if test="${not empty data.type && data.type eq 'edit'}">
							<button class="btn btn-danger" style="margin-right:10px;" onclick="deleteEbook('${data.chapterSeq}');">삭제</button>
						</c:if>	
			        	<button id="insertBtn" class="btn btn-warning" onclick="ebookInsert();">저장 </button>
			        </div>
			        
		        </div>
	     	</div>
	     	
	     	<h3 style="padding-top:20px;padding-left:20px;">
                <c:if test="${not empty data.type && data.type eq 'edit'}">
                	eDiscovery Book 본문
                </c:if>
		  	</h3>
	     	<div class="col-xs-12">
		        <div class="col-xs-12 box box-danger">
		        	<div style="padiing-top:30px;padding-top:10px;padding-bottom:20px;">
		        		<button class="btn btn-success" onclick="go_detail_content('${data.title}', '${data.chapterSeq}');">본문 등록 </button>
		        	</div>
		        	
		        	<span class="f24">| 본문 리스트</span><br><br>
		        	
		        	<c:choose>
			        	<c:when test="${empty contentList || fn:length(contentList) == 0}">
			        		<span class="f18" style="display:inline-block;padding-bottom:20px;">등록된 본문이 없습니다.</span>
			        	</c:when>
			        	<c:otherwise>
			        	
				        	<div class="box-body table-responsive no-padding">
				        		<span class="f14" style="color:red;">* 테이블을 드래그 드롭하여 순서를 변경할 수 있습니다. (순서 저장 버튼 클릭시, 순서가 저장됩니다.)</span>
								<table class="table table-striped">
									<thead class="thead-light">
										<tr>
											<th class="text-center">순서</th>
											<th class="text-center">제목</th>
											<th class="text-center">보기</th>
										</tr>
									</thead>
									<tbody id="contentListTable">
										<c:forEach items="${contentList}" var="list" varStatus="status">
											<tr class="text-center">
												<td class="quiztable-content-center-white" name="tdId" order="${status.count}" pid="${list.contentSeq}" id="tdId${status.index}">${list.contentOrder}</td>
												<td class="quiztable-content-center-white" style="width:50%;">${list.title}</td>
												<td class="quiztable-content-center-white">
													<button class="btn btn-primary btn-sm" onclick="go_edit_content('${data.title}', '${data.chapterSeq}', '${list.contentSeq}', 'edit');">상세보기</button>
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
								
								<button class="btn btn-success btn-sm" style="float:right;margin-top:20px;margin-bottom:20px;" onclick="updateOrder('${data.chapterSeq}');">순서 저장</button>
							</div>
						</c:otherwise>
				  </c:choose>	
	          </div>
		</div>   	
     	
     	
     	
        </section><!-- /.content -->
        
        
      </div><!-- /.content-wrapper -->
      <%@ include file ="../_footer.jsp" %> 
	</div>


    <!-- jQuery 2.1.4 -->
    <script src="../resources/plugins/jQuery/jQuery-2.1.4.min.js"></script>
    <script src="../resources/customJS/jquery-ui.js"></script>
    
    <script src="../resources/plugins/jQuerySimpleDPicker/jquery.simple-dtpicker.js"></script>
    <!-- Bootstrap 3.3.5 -->
    <script src="../resources/bootstrap/js/bootstrap.min.js"></script>
    <!-- Slimscroll -->
    <script src="../resources/plugins/slimScroll/jquery.slimscroll.min.js"></script>
    <!-- FastClick -->
    <script src="../resources/plugins/fastclick/fastclick.min.js"></script>
    <!-- AdminLTE App -->
    <script src="../resources/dist/js/app.min.js"></script>
    <!-- AdminLTE for demo purposes -->
    <script src="../resources/dist/js/demo.js"></script>
    <script src="../resources/smarteditor/js/HuskyEZCreator.js" charset="utf-8"></script>
    <script src="../resources/customJS/common.js"></script>
    <script src="../resources/customJS/jquery.redirect.js"></script>
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	
	<script type="text/javascript">
	$(document).ready(function() {
		var isupateneed = '${needOrderUpdate}';
		if ('Y' == isupateneed) {
			updateOrderWithoutAlert('${data.chapterSeq}')
		}	
	});
	
	
	
	
	var oEditors = [];
	var skinUrl = "../resources/smarteditor/SmartEditor2Skin.html";
	
	nhn.husky.EZCreator.createInIFrame({
	    oAppRef: oEditors,
	    elPlaceHolder: "contents",
	    sSkinURI: skinUrl,
	    fCreator: "createSEditor2"
	
	});
	
	$('#contentListTable').sortable({
		update: function( event, ui ) {
			changeOrder();
	  	}
	});
    
    
    function changeOrder() {
		$('tbody#contentListTable').find("td[name='tdId']").each(function(index){ 
			$(this).attr('order', (index*1+1));
		});
    }
    
    
    
    
    function updateOrder(chapterSeq) {
    	var ids=[];
    	$('tbody#contentListTable').find("td[name='tdId']").each(function(index) {
    		var idinfo = $(this).attr('pid');
    		idinfo += '||' + $(this).attr('order')
    		ids[index] = idinfo;
    	});
    	
    	if (ids.length > 0) {
	    	$.post('<%=request.getContextPath() %>/json/updateEbookContentOrder', { code: ids.join(), chapterSeq : chapterSeq }, function(data) {
				if (data.STATUS == 'SUCCESS') {
					swal('총 ' + new Intl.NumberFormat('en-US').format(data.RequestCount) + '건 중 ' + new Intl.NumberFormat('en-US').format(data.SavedCount) + '건이 저장되었습니다.',"", "success")
					.then(function(value)  { 
						  if(value){
							  history.go(0);
						  } 
				     });
				} else {
					swal("적용 실패하였습니다.","다시 시도해 보시기 바랍니다.", "error");
				}
			});
    	} else {
    		history.go(0);
    	}
    }
    
    function updateOrderWithoutAlert(chapterSeq) {
    	var ids=[];
    	$('tbody#contentListTable').find("td[name='tdId']").each(function(index) {
    		var idinfo = $(this).attr('pid');
    		idinfo += '||' + $(this).attr('order')
    		ids[index] = idinfo;
    	});
    	
    	if (ids.length > 0) {
	    	$.post('<%=request.getContextPath() %>/json/updateEbookContentOrder', { code: ids.join(), chapterSeq : chapterSeq }, function(data) {
				if (data.STATUS == 'SUCCESS') {
					$.redirect( "edbookAdd", { chapterSeq : chapterSeq, type : 'edit' }, "POST", "" );
				} else {
					console.log("순서 적용 실패");
				}
			});
    	}
    }
	
    
    
    
	function ebookInsert() {
    	// id가 smarteditor인 textarea에 에디터에서 대입
		oEditors.getById["contents"].exec("UPDATE_CONTENTS_FIELD", []); 
		
		if($.trim($("#title").val()) ==''){
			swal("제목을 입력해 주세요.", "", "info");
			return false;
		}
		
		//내용체크
		if($.trim($("#contents").val()) ==''){
			swal("내용을 입력해 주세요.", "", "info");
			return false;
		}
		
	 	event.preventDefault();
	 	
	 	$('#insertBtn').prop('disabled', true);
		
		 var form = $('#frm')[0];
	        
			// Create an FormData object 
	     var params = new FormData(form);
	     console.log(params);
		
		//var params = $("#frm").serialize();
		
		$.ajax({
			url: "<%=request.getContextPath() %>/page/edbookInsert",
			type: "post",
			enctype: 'multipart/form-data',
			data: params,
			processData: false,
            contentType: false,
			timeout: 600000,
			cache: false,
			success: function(data) {
				if(data!="fail") {
					swal("적용되었습니다.","", "success")
					.then(function(value)  { 
						  if(value){
							  //location.href="/board/press";
							  //window.location=document.referrer;
							  //history.go(0);
							  $.redirect( "edbookAdd", { chapterSeq : data, type : 'edit' }, "POST", "" );
						  } 
				     });
				} else {
					swal("적용 실패하였습니다.","다시 시도해 보시기 바랍니다.", "error");
					$('#insertBtn').prop('disabled', false);
				}
			},
			error: function(e) {
				swal("오류가 발생했습니다. 관리자에게 문의하시기 바랍니다.");
				$('#insertBtn').prop('disabled', false);
			}
		});
		
	}
    
    function deleteEbook(chapterSeq) {
    	swal("정말 삭제하시겠습니까?", {
			buttons: {
				cancel: "취소",
			    defeat: "삭제",
			},
		})
		.then(function(value)  {
		  switch (value) {
		    case "defeat":
		    	var codes = [];
		    	codes.push(chapterSeq);
		    	
		    	$.post('<%=request.getContextPath() %>/json/deleteEbookAllContentByChapterSeq', { code: codes.join() }, function(data) {
		    		$.post('<%=request.getContextPath() %>/json/deleteEdbook', { code: codes.join() }, function(data) {
						if (data.STATUS == 'SUCCESS') {
							swal('삭제되었습니다.',"", "success")
							.then(function(value)  { 
								  if(value){
									  window.location=document.referrer;
								  } 
						     });
						} else {
							swal("삭제를 실패했습니다.","다시 시도해 보시기 바랍니다.", "error");
						}
					});
		    	});

		    	break;
			 
		    default:
		    	swal("취소했습니다.", "", "error");
		    	break;
			}
		});
    }
    
    function preview(chapterSeq, type, url) {
    	if ('edit' == type) {
    		window.open(url+"?chapterSeq="+chapterSeq, '_blank');
    	} else {
    		swal("미리보기는 저장 후 볼 수 있습니다.", "", "info");
    	}
    }
    
    function go_detail_content(chaptername, chapterSeq) {
    	$.redirect( "edbookContentAdd", { chaptername : chaptername, chapterSeq : chapterSeq }, "POST", "" );
	}
    
    function go_edit_content(chaptername, chapterSeq, contentSeq, type) {
		$.redirect( "edbookContentAdd", { chaptername : chaptername, chapterSeq : chapterSeq, contentSeq : contentSeq, type : type }, "POST", "" );    	
    }
    
    function go_list() {
    	$.redirect( "edbook", { }, "POST", "");
    }
	</script>
  </body>
</html>