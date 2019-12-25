<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.springframework.context.ApplicationContext"%>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils"%>

<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String curUri = request.getRequestURI();
System.out.println("___________________________________" + curUri);
%>
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>FRONTEO Korea&nbsp;ADMIN</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.5 -->
    <link rel="stylesheet" href="..<%=request.getContextPath()%>/resources/bootstrap/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="..<%=request.getContextPath()%>/resources/dist/css/AdminLTE.min.css">
    <!-- iCheck -->
    <link rel="stylesheet" href="..<%=request.getContextPath()%>/resources/plugins/iCheck/square/blue.css">


    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  <body class="hold-transition login-page">
    <div class="login-box">
      <div class="login-logo">
        <a href="#"><b>FRONTEO Korea</b><br>관리자페이지</a>
      </div><!-- /.login-logo -->
      <div class="login-box-body">
        <p class="login-box-msg">로그인 해 주세요.</p>
        <form action="#" method="post" name="loginform">
        	<input type="hidden" name="returnUrl" value="${ returnUrl }" />
          <div class="form-group has-feedback">
            <input type="text" id="userId" class="form-control" placeholder="사용자 아이디" value="">
            <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
          </div>
          <div class="form-group has-feedback">
			<input type="password" id="password" class="form-control" placeholder="비밀번호" value="">
            <span class="glyphicon glyphicon-lock form-control-feedback"></span>
          </div>
          <div class="row">

            <div class="col-xs-4">
              <input id="btnLogin" type="button" onclick="login();" class="btn btn-primary btn-block btn-flat" value="Sign In">
            </div><!-- /.col -->
          </div>
        </form>
      </div><!-- /.login-box-body -->
    </div><!-- /.login-box -->

    <!-- jQuery 2.1.4 -->
    <script src="..<%=request.getContextPath()%>/resources/plugins/jQuery/jQuery-2.1.4.min.js"></script>
    <!-- Bootstrap 3.3.5 -->
    <script src="..<%=request.getContextPath()%>/resources/bootstrap/js/bootstrap.min.js"></script>
    <!-- iCheck -->
    <script src="..<%=request.getContextPath()%>/resources/plugins/iCheck/icheck.min.js"></script>
    
    <script src="..<%=request.getContextPath()%>/resources/plugins/iCheck/icheck.min.js"></script>
    <script src="..<%=request.getContextPath()%>/resources/customJS/common.js"></script>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    
    <script>
      $(function () {
        $('input').iCheck({
          checkboxClass: 'icheckbox_square-blue',
          radioClass: 'iradio_square-blue',
          increaseArea: '20%' // optional
        });
      });
    </script>
    <script>
	    $("#password").on("keyup", function (event) {
	    	if (event.keyCode == 13) {
	    		$('#btnLogin').click();
	    	}
	    }); 
    </script>
    
    
	<script type="text/javascript">
	function login() {
		var adminId		= $('#userId').val();
		var password	= $('#password').val();
		console.log(adminId + ' ' + password);
		if (adminId == '') {
			swal("사용자아이디를 입력하세요.", "", "info");
		} else if (password == '') {
			swal("비밀번호를 입력하세요.", "", "info");
		} else {
			var url='json/login';
			var params = {
					"adminId"		: adminId
					,"password"		: password
					,"returnUrl"	: $("input[name=returnUrl]").val()
			};
			asyncJson(params, url, function(data) {
				loginCallback(data);
			});
		}	
	}

	function loginCallback(data) {
		if ('SUCCESS' == data.STATUS) {
			window.location.href = data.returnUrl.length > 0 ? data.returnUrl : "/";
		} else if ('FAIL' == data.STATUS) {
			swal("로그인에 실패했습니다.", "", "error");
		}
	}

	
    </script>
  </body>
</html>
