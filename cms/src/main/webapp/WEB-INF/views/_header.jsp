<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<header class="main-header">
	<a href="#" class="logo">
		<!-- mini logo for sidebar mini 50x50 pixels -->
    	<span class="logo-mini"><b>FRONTEO</b> Korea</span>
    	<!-- logo for regular state and mobile devices -->
    	<span class="logo-lg"><b>FRONTEO</b> Korea</span>
  	</a>
  	<nav class="navbar navbar-static-top" role="navigation">
    	<ul class="nav navbar-nav">

            
        </ul>
    	<div class="navbar-custom-menu" style="padding-top:10px;padding-right:10px;">
     		<button type="button" class="btn btn-danger btn-sm" onclick="javascript:logout();">로그아웃</button>
 		</div> 
  	</nav>
</header>

	<script type="text/javascript">
	function logout() {
		var url='../json/logout';
		var params = {};
			
		asyncJson(params, url, function(data) {
			window.location.href="../login";
		});
	}
	</script>
