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
    <title>Salesforce</title>
    
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
                Salesforce 상세
		  </h1>
        </section>

        <!-- Main content -->
        <section class="content">
        
        <div class="row">
			<div class="col-xs-12">
		        <div class="col-xs-12 box box-danger">
		        	
		        		<input type="hidden" name="updateType" id="updateType" value="${data.type }" />
		        		<input type="hidden" name="bbsType" id="bbsType" value="E" />
						<div class="card-body">
							<table class="table table-striped">
						        <colgroup>
						          <col width="20%"/>
								  <col width="80%"/>
							    </colgroup>
						       <tbody>
						       	 <tr>
					                 <th class="quiztable-content-center-white">NO.</th>
						             <td>
						             	<input type="text" value="${data.customerSeq}" class="form-control" style="display:inline;" readOnly/>
						             </td>
					              </tr>		
					              <tr>
					                 <th class="quiztable-content-center-white">작성일</th>
						             <td>
						             	<input type="text" value="${data.regdate}" class="form-control" style="display:inline;" readOnly/>
						             </td>
					              </tr>		
					              <tr>
					                 <th class="quiztable-content-center-white">분류</th>
						             <td>
						             	<input type="text" value="${data.pathName}" class="form-control" style="display:inline;" readOnly/>
						             </td>
					              </tr>		
					              <tr>
					                 <th class="quiztable-content-center-white">항목</th>
						             <td>
						             	<input type="text" value="${data.concernName}" class="form-control" style="display:inline;" readOnly/>
						             </td>
					              </tr>		
					              <tr>
					                 <th class="quiztable-content-center-white">내용</th>
						             <td>
						             	<pre>
						             		${data.inquiry}
						             	</pre>
						             </td>
					              </tr>
					          </tbody>
				           </table>
				           
				           <table class="table table-striped" style="padding-top:40px;">
						        <colgroup>
						          <col width="20%"/>
								  <col width="80%"/>
							    </colgroup>
						       <tbody>
						       	  <tr>
					                 <th class="quiztable-content-center-white">성</th>
						             <td>
						             	<input type="text" value="${data.familyname}" class="form-control" style="display:inline;" readOnly/>
						             </td>
					              </tr>	
					              <tr>
					                 <th class="quiztable-content-center-white">이름</th>
						             <td>
						             	<input type="text" value="${data.firstname}" class="form-control" style="display:inline;" readOnly/>
						             </td>
					              </tr>		
					              <tr>
					                 <th class="quiztable-content-center-white">연락처</th>
						             <td>
						             	<input type="text" value="${data.phone}" class="form-control" style="display:inline;" readOnly/>
						             </td>
					              </tr>
					              <%-- <tr>
					                 <th class="quiztable-content-center-white">이메일</th>
						             <td>
						             	<input type="text" value="${data.email}" class="form-control" style="display:inline;" readOnly/>
						             </td>
					              </tr> --%>
					              <tr>
					                 <th class="quiztable-content-center-white">회사</th>
						             <td>
						             	<input type="text" value="${data.company}" class="form-control" style="display:inline;" readOnly/>
						             </td>
					              </tr>	
					              <tr>
					                 <th class="quiztable-content-center-white">부서명</th>
						             <td>
						             	<input type="text" value="${data.deptname}" class="form-control" style="display:inline;" readOnly/>
						             </td>
					              </tr>	
					              <tr>
					                 <th class="quiztable-content-center-white">직책</th>
						             <td>
						             	<input type="text" value="${data.titlename}" class="form-control" style="display:inline;" readOnly/>
						             </td>
					              </tr>	
					              <tr>
					                 <th class="quiztable-content-center-white">이메일 수신 동의</th>
							            <td>
							             	<input type="radio" name="exposure" disabled value="Y" style="margin-right: 10px;"
							             		<c:if test="${data.recvEmail eq 'Y'}">checked</c:if>><span>노출</span>
							                <input type="radio" name="exposure" disabled value="N" style="margin-right: 10px; margin-left: 50px;"
												<c:if test="${data.recvEmail eq 'N'}">checked</c:if>><span>비노출</span>
							             </td>   
					              </tr>							
						       </tbody>
						   </table>    
					              
						</div>
					
					
					<div style="float:right;padiing-top:100px;padding-top:10px;padding-bottom:10px;">
			        	<button class="btn btn-warning" onclick="window.location=document.referrer;">목록 </button>
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
	
	<script type="text/javascript">
	</script>
  </body>
</html>