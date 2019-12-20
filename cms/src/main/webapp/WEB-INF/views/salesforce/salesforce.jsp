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
    <title>Salesforce 관리</title>
    
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
          <span class="f24">Salesforce</span> 
          <span class="f14" style="padding-left:20px;">전체 Salesforce</span> <span class="f14" style="color:blue;"><fmt:formatNumber value="${totalCnt}" pattern="#,###" /></span>
        </section>

        <!-- Main content -->
        <section class="content">
        	
        	<div class="row">
				<div class="col-xs-12">

					<div class="box box-danger">
						<div class="box-body">
						<form id="ReqForm" class="form-inline col-md">
							<table style="width:100%;">
								<tr>
									<td style="width:10%;">
										기간
									</td>
									<td style="width:40%;">
										<div class="input-group date">
	        		         				<div class="input-group-addon">
	                		   					<i class="fa fa-calendar"></i>
	                 						</div>
	                 						<input type="text" class="form-control pull-right" style="width:250px;" id="regDate">
	                 					</div>	
	                 				</td>
	                 				<td style="width:10%;">
										분류
									</td>
									<td style="width:40%;">
										<input type="radio" name="pathType" value="" style="margin-right: 10px;" checked/><span>전체</span>
						                <input type="radio" name="pathType" value="1" style="margin-right: 10px; margin-left: 30px;" /><span>Contact</span>
						                <input type="radio" name="pathType" value="2" style="margin-right: 10px; margin-left: 30px;" /><span>E-Book</span>
						                <input type="radio" name="pathType" value="3" style="margin-right: 10px; margin-left: 30px;" /><span>Whitepapers</span>	
	                 				</td>
	                 			</tr>	
                 			</table>
						</form>
						</div>
						
					</div> 
					
					<div style="text-align: center; padding-bottom:10px;">
						<button type="submit" class="btn btn-success btn-sm" onclick="clearFilter();" style="display:none;">기본설정</button>
						<button type="submit" class="btn btn-primary btn-sm" onclick="search();">검색</button>									
					</div>
					
						
					<div class="box" id="divList">

						
					</div>
					
					<div>
						<select name="resultCount" class="form-control" style="width:100px;float:right;">
							<c:forEach items="${rows}" var="row" varStatus="status">
								<option value="${row}" <c:if test="${rowCount eq row}">selected</c:if>>${row}개</option>
							</c:forEach>
						</select>
					</div>
					<!-- /.box -->
				</div>
			</div>
	 			  
	 		<div id="paging" class="row">
				<div class="col-sm-14" style="margin-top:-50px;">
					<ul id="pagination" class="pagination-sm"></ul>
				</div>
			</div>	  
        </section><!-- /.content -->
      </div><!-- /.content-wrapper -->
      <%@ include file ="../_footer.jsp" %>
	</div>


    <!-- jQuery 2.1.4 -->
    <script src="../resources/plugins/jQuery/jQuery-2.1.4.min.js"></script>
    
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
    
    <script src="../resources/customJS/common.js"></script>
    <script src="../resources/customJS/jquery.twbsPagination.min.js"></script>
    <script src="../resources/customJS/jquery.redirect.js"></script>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    
    <!-- date-range-picker -->
    <script type="text/javascript" src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>
    
	<script type="text/javascript">
	
	jQuery(document).ready(function() {
		$('#regDate').daterangepicker({
			autoUpdateInput: false,
			ranges: {
				'오늘': [moment(), moment()],
				'어제': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
				'1주일': [moment().subtract(1, 'weeks'), moment().subtract(1, 'days')],
				'1개월': [moment().subtract(1, 'months'), moment().subtract(1, 'days')],
				'3개뭘': [moment().subtract(3, 'months'), moment().subtract(1, 'days')],
				'6개월': [moment().subtract(6, 'months'), moment().subtract(1, 'days')],
				'1년': [moment().subtract(1, 'years'), moment().subtract(1, 'days')]
			},
			locale: {
				format: "YYYY-MM-DD"
			}
		}, function(start_date, end_date) {
		    $('#regDate').val(start_date.format('YYYY-MM-DD')+' - '+end_date.format('YYYY-MM-DD'));
		});
		
		$("#divList").load("salesforceList?&rowCount=${rowCount}&page=1", function(data) {});
    });
	
	function search() {
		$('#pagination').twbsPagination('destroy');
		
		var rowCount = $('select[name=resultCount]').val();
		
		var regFromDate = '';
		var regToDate = ''; 
		
		if ($('#regDate').val() != '') {
			regFromDate = $('#regDate').val().split(' - ')[0];
			regToDate = $('#regDate').val().split(' - ')[1];
		}
		
		var pathType = $(":input:radio[name=pathType]:checked").val();
		
 		$("#divList").html('');
		$("#divList").load("salesforceList?rowCount="+rowCount+"&regFromDate="+regFromDate+"&regToDate="+regToDate
				+"&pathType="+pathType+"&page=1", function(data) {}); 
	}
	
	function clearFilter() {
		$('#regDate').val('');
		$("input:radio[name=search_type]:input[value='']").attr("checked", true);
	}

	</script>
  </body>
</html>