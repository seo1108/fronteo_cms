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
    <title>Press</title>
    
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
          	등록
		  </h1>
        </section>

        <!-- Main content -->
        <section class="content">
        
        <div class="row">
			<div class="col-xs-12">
		        <div class="col-xs-12 box box-danger">
		        	<div style="float:right;padiing-top:100px;padding-top:10px;padding-bottom:10px;">
			        	<button class="btn btn-warning btn-sm" onclick="">저장 </button>
			        </div>
							
					<form name="frm" id="frm" method="post" enctype="multipart/form-data" action="">
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
							             	<input type="text" name="no" id="title" value="" class="form-control" style="display:inline;" readOnly/>
							             </td>
						              </tr>		
						              <tr>
						                 <th class="quiztable-content-center-white">작성일</th>
							             <td>
							             	<input type="text" name="date" id="title" value="" class="form-control" style="display:inline;" readOnly/>
							             </td>
						              </tr>		
						              <tr>
						                 <th class="quiztable-content-center-white">상태</th>
							             <td>
							             	<input type="radio" name="type" value="1" style="margin-right: 10px;" checked/><span>eDiscovery</span>
							                <input type="radio" name="type" value="2" style="margin-right: 10px; margin-left: 50px;" /><span>Business Solution</span>
							                <input type="radio" name="type" value="2" style="margin-right: 10px; margin-left: 50px;" /><span>AI Consulting</span>
							                <input type="radio" name="type" value="2" style="margin-right: 10px; margin-left: 50px;" /><span>Corporate</span>
							             </td>   
						              </tr>			
							          <tr>
						                 <th class="quiztable-content-center-white">제목</th>
							             <td>
							             	<input type="text" name="title" id="title" value="" class="form-control" style="display:inline;"/>
							             </td>
						              </tr>		
							          <tr>
						                 <th class="quiztable-content-center-white">상태</th>
							             <td>
							             	<input type="radio" name="exposure" value="Y" style="margin-right: 10px;" checked/><span>노출</span>
							                <input type="radio" name="exposure" value="N" style="margin-right: 10px; margin-left: 50px;" /><span>비노출</span>
							             </td>   
						              </tr>		
							          <tr>
						                 <th class="quiztable-content-center-white">내용</th>
							             <td><textarea name="content" id="content" rows="22" cols="170"></textarea></td>
						              </tr>		
						              <tr>
						                 <th class="quiztable-content-center-white">URL</th>
							             <td>
							             	<input type="text" name="url" id="url" value="" class="form-control" style="display:inline;"/>
							             </td>
						              </tr>	
						              <tr>
						                 <th class="quiztable-content-center-white">파일첨부</th>
							             <td>
							             	<input id="attach" type="file" name="file" id="file" style="width:100%;"> 
							             </td>
						              </tr>		
								   </tbody>
					           </table>
							</div>
						</form>
						
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

	<script type="text/javascript">

	jQuery(document).ready(function(){
		var oEditors = [];
		var skinUrl = "../resources/smarteditor/SmartEditor2Skin.html";
		
		nhn.husky.EZCreator.createInIFrame({
		    oAppRef: oEditors,
		    elPlaceHolder: "content",
		    sSkinURI: skinUrl,
		    fCreator: "createSEditor2"
		
		});
    });
	
	
	   

	</script>
  </body>
</html>