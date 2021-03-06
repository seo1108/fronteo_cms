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

<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Careers</title>
    
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
    <link rel="stylesheet" href="../resources/plugins/datepicker/datepicker3.css">
    <link rel="stylesheet" href="../resources/dist/css/custom.css">
    
    <!-- daterange picker -->
    <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" />
   

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
                	Careers 편집
                </c:when>
                <c:otherwise>
                	Careers 등록
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
		        		<input type="hidden" name="bbsType" id="bbsType" value="C" />
		        		<input type="hidden" name="startDate" id="startDate" value="${data.startDate }" />
		        		<input type="hidden" name="endDate" id="endDate" value="${data.endDate }" />
		        		<input type="hidden" name="isAnytime" id="isAnytime" value="${data.isAnytime }" />
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
						             	<input type="text" name="bbsSeq" id="bbsSeq" value="${data.bbsSeq}" class="form-control" style="display:inline;" readOnly/>
						             </td>
					              </tr>		
					              <tr>
					                 <th class="quiztable-content-center-white">작성일</th>
						             <td>
						             	<input type="text" name="regdate" id="date" value="${data.regdate}" class="form-control" style="display:inline;" readOnly/>
						             </td>
					              </tr>		
					              <tr>
					                 <th class="quiztable-content-center-white">구분</th>
					                 <c:choose>
						                 <c:when test="${not empty data.type && data.type eq 'edit'}">
						                 	<td>
								             	<input type="radio" name="subType" value="1" style="margin-right: 10px;" 
								             		<c:if test="${data.subType eq '1'}">checked</c:if>><span>신입</span>
								                <input type="radio" name="subType" value="2" style="margin-right: 10px; margin-left: 50px;" 
								                	<c:if test="${data.subType eq '2'}">checked</c:if>><span>경력</span>
								            </td>
						                 </c:when>
						                 <c:otherwise>
						                 	<td>
								             	<input type="radio" name="subType" value="1" style="margin-right: 10px;" checked/><span>신입</span>
								                <input type="radio" name="subType" value="2" style="margin-right: 10px; margin-left: 50px;" /><span>경력</span>
								            </td>   
						                 </c:otherwise>
					                 </c:choose>
					              </tr>		
					              <tr>
					                 <th class="quiztable-content-center-white">웹표기시간</th>
						             <td>
						             	<div class="input-group date">
	        		         				<div class="input-group-addon">
	                		   					<i class="fa fa-calendar"></i>
	                 						</div>
	                 						<input type="text" class="form-control pull-left" style="width:250px;" id="expsdate" value="${data.exposuredate}">
	                 						<input type="hidden" name="exposuredate" id="exposuredate" />
	                 					</div>	
						             </td>
					              </tr>				
						          <tr>
					                 <th class="quiztable-content-center-white">제목</th>
						             <td>
						             	<input type="text" name="title" id="title" value="${data.title}" class="form-control" style="display:inline;"/>
						             </td>
					              </tr>
					              <tr>
					                 <th class="quiztable-content-center-white">모집기간</th>
						             <td>
						             	<div class="input-group date">
	        		         				<div class="input-group-addon">
	                		   					<i class="fa fa-calendar"></i>
	                 						</div>
	                 						<input type="text" class="form-control pull-left" style="width:250px;" id="careerDate">
	                 						
	                 						<label style="margin-left:100px;font-size:14px;font-weight:normal;" for="chkAnytime"><input class="form-check-input" type="checkbox" id="chkAnytime" name="chkAnytime" />&nbsp;&nbsp;&nbsp;&nbsp;상시 모집</label> 
	                 						
	                 						<!-- <input type="checkbox" name="chkAnytime" id="chkAnytime" style="padding-left:50px;" value="" class="form-check-input" /> -->
	                 					</div>	
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
								             	<input type="radio" name="exposure" value="Y" style="margin-right: 10px;" checked/><span>노출</span>
								                <input type="radio" name="exposure" value="N" style="margin-right: 10px; margin-left: 50px;" /><span>비노출</span>
								            </td>   
						                 </c:otherwise>
					                 </c:choose>
					              </tr>		
						          <tr>
					                 <th class="quiztable-content-center-white">내용</th>
						             <td><textarea name="contents" id="contents" rows="22" cols="170">${data.contents}</textarea></td>
					              </tr>
							   </tbody>
				           </table>
						</div>
					</form>
					
					<div style="float:right;padiing-top:100px;padding-top:10px;padding-bottom:10px;">
						<c:if test="${not empty data.type && data.type eq 'edit'}">
							<button class="btn btn-danger" style="margin-right:10px;" onclick="deleteBbs('${data.bbsSeq}');">삭제</button>
						</c:if>	
			        	<button id="insertBtn" class="btn btn-warning" onclick="bbsInsert();">저장 </button>
			        </div>
			        
		        </div>
	     	</div>
		</div>   	
     	
     	
     	
        </section><!-- /.content -->
        
        
      </div><!-- /.content-wrapper -->
      <%@ include file ="../_footer.jsp" %> 
	</div>


    <!-- jQuery 2.1.4 -->
    <script src="../resources/plugins/jQuery/jQuery-2.1.4.min.js"></script>
    
    <script src="../resources/plugins/jQuerySimpleDPicker/jquery.simple-dtpicker.js"></script>
    <script src="../resources/plugins/datepicker/bootstrap-datepicker.js"></script>
    <script src="../resources/plugins/datepicker/locales/bootstrap-datepicker.kr.js" charset="UTF-8"></script>
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
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	
	 <!-- date-range-picker -->
    <script type="text/javascript" src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>
	
	<script type="text/javascript">
	
	jQuery(document).ready(function(){
		$('#careerDate').daterangepicker({
			autoUpdateInput: false,
			locale: {
				format: "YYYY-MM-DD"
			}
		}, function(start_date, end_date) {
			$('#careerDate').val(start_date.format('YYYY-MM-DD')+' - '+end_date.format('YYYY-MM-DD'));
		    
		    $('#startDate').val(start_date.format('YYYYMMDD'));
		    $('#endDate').val(end_date.format('YYYYMMDD'));
		    $("#chkAnytime").prop('checked', false); 
		    $('#isAnytime').val('N');
		});
		
		$("#chkAnytime").change(function(){
			if($("#chkAnytime").is(":checked")) {
				initDateRangePicker();
	        	$('#isAnytime').val('Y');
	        } else {
	        	$('#isAnytime').val('N');
	        }
	    });
		
		
		var type = '${data.type}';
		var isAny = '${data.isAnytime}';
		
		if (type == 'edit') {
			if (isAny == 'N') {
				var start = '${data.startDate}';
				var end = '${data.endDate}';
				
				$('#careerDate').val(start +' - '+end);
		    
			    $('#startDate').val(start.replaceAll("-", ""));
			    $('#endDate').val(end.replaceAll("-", ""));
			} else {
				$("#chkAnytime").prop('checked', true); 
				
				$('#careerDate').val('');
	        	$('#startDate').val('');
			    $('#endDate').val('');
			}
		}
		
		$('#expsdate').datepicker({
			alendarWeeks: false,
            todayHighlight: true,
            autoclose: true,
            setDate: new Date(),
            format: "yyyy-mm-dd",
            language: "kr"
		});
		
		$("#expsdate").datepicker().on('changeDate', function (e) {
			$("#exposuredate").val($('#expsdate').val().replaceAll('-', ''));
		});
		
		if ('edit' != $('#updateType').val() || '' == $('#expsdate').val()) {
			$('#expsdate').datepicker('update', new Date());
		} else {
			$("#exposuredate").val($('#expsdate').val().replaceAll('-', ''));
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
	
	function initDateRangePicker() {
		$('#careerDate').daterangepicker({
			autoUpdateInput: false,
			locale: {
				format: "YYYY-MM-DD"
			},
			startDate: moment(),
			endDate: moment()
		}, function(start_date, end_date) {
			$('#careerDate').val(start_date.format('YYYY-MM-DD')+' - '+end_date.format('YYYY-MM-DD'));
		    
		    $('#startDate').val(start_date.format('YYYYMMDD'));
		    $('#endDate').val(end_date.format('YYYYMMDD'));
		    $("#chkAnytime").prop('checked', false); 
		    $('#isAnytime').val('N');
		});
		
		$('#careerDate').val('');
    	$('#startDate').val('');
	    $('#endDate').val('');
	}
	
	function bbsInsert() {
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
		
		// 모집기간 체크
		if (false == $("input:checkbox[id='chkAnytime']").is(":checked") && $('#careerDate').val() == '') {
			swal("모집기간을 확인해 주세요.", "", "info");
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
			url: "<%=request.getContextPath() %>/board/bbsInsert",
			type: "post",
			enctype: 'multipart/form-data',
			data: params,
			processData: false,
            contentType: false,
			timeout: 600000,
			cache: false,
			success: function(data) {
				if(data=="ok") {
					swal("적용되었습니다.","", "success")
					.then(function(value)  { 
						  if(value){
							  //location.href="/board/press";
							  window.location=document.referrer;
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
    
    function deleteBbs(bbsSeq) {
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
		    	codes.push(bbsSeq);
				
				$.post('<%=request.getContextPath() %>/json/deleteBbs', { code: codes.join() }, function(data) {
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
			 
	  	      	break;
			 
		    default:
		    	swal("취소했습니다.", "", "error");
		    	break;
			}
		});
    }
    
    String.prototype.replaceAll = function(org, dest) {
	    return this.split(org).join(dest);
	}
	</script>
  </body>
</html>