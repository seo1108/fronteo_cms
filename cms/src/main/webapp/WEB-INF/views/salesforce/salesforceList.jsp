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

<div style="padding:10px;">
		<span class="f20">목록</span> <span class="f14" style="padding-left:20px;">검색결과</span> <span class="f14" style="color:red;"><fmt:formatNumber value="${totalCnt}" pattern="#,###" /></span>
	</div>	
	
<!-- 	<div style="padding:10px;background-color:#d2d6de;">
	</div>	
 -->	
	<div class="box-body table-responsive no-padding">
		<table class="table table-striped">
			<thead class="thead-light">
				<tr>
					<th class="text-center">NO</th>
					<th class="text-center">문의일</th>
					<th class="text-center">분류</th>
					<th class="text-center">항목</th>
					<th class="text-center">성</th>
					<th class="text-center">이름</th>
					<th class="text-center">연락처</th>
					<!-- <th class="text-center">이메일</th> -->
					<th class="text-center">회사명</th>
					<th class="text-center">보기</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${data}" var="list" varStatus="status">
					<tr class="text-center">
						<td class="quiztable-content-center-white"><fmt:formatNumber value="${list.customerSeq}" pattern="#,###" /></td>
						<td class="quiztable-content-center-white">${list.regdate}</td>
						<td class="quiztable-content-center-white">${list.pathName}</td>
						<td class="quiztable-content-center-white">${list.concernName}</td>
						<td class="quiztable-content-center-white">${list.familyname}</td>
						<td class="quiztable-content-center-white">${list.firstname}</td>
						<td class="quiztable-content-center-white">${list.phone}</td>
						<%-- <td class="quiztable-content-center-white">${list.email}</td> --%>
						<td class="quiztable-content-center-white">${list.company}</td>
						<td class="quiztable-content-center-white">
							<button class="btn btn-primary btn-sm" onclick="go_detail('${list.customerSeq}');">상세보기</button>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
	<script type="text/javascript">
		$(document).ready(function() {
			if (Number('${totalPage}') > 0) {
				$('#pagination').twbsPagination({
					initiateStartPageClick: false,
					totalPages : Number('${totalPage}'),
					visiblePages : Number('${totalPage}') > 10 ? 10 : Number('${totalPage}'),
					startPage : Number('${page}'),
					onPageClick : function(e, page) {
						var rowCount = '${rowCount}';
						var regFromDate = '${regFromDate}';
						var regToDate = '${regToDate}';
						var pathType = '${pathType}';
						$("#divList").load("salesforceList?rowCount="+rowCount+"&regFromDate="+regFromDate+"&regToDate="+regToDate
								+"&pathType="+pathType+"&page="+page, function(data) {});
					}
				});
			}
		});
		
		function go_detail(customerSeq) {
			$.redirect( "salesforceDetail", { customerSeq : customerSeq }, "POST", "" );
		}
		
		
	</script>	
	