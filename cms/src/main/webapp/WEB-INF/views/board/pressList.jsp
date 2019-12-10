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
					<th class="text-center"><input type="checkbox" name="selectAll" class="form-check-input" /></th>
					<th class="text-center">NO</th>
					<th class="text-center">작성일</th>
					<th class="text-center">구분</th>
					<th class="text-center">제목</th>
					<th class="text-center">상태</th>
					<th class="text-center">관리</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${data}" var="list" varStatus="status">
					<tr class="text-center">
						<th class="quiztable-content-center-white">
							<input type="checkbox" name="selected" value="${list.bbsSeq}" class="form-check-input" />
						</th>
						<td class="quiztable-content-center-white"><fmt:formatNumber value="${list.bbsSeq}" pattern="#,###" /></td>
						<td class="quiztable-content-center-white">${list.regdate}</td>
						<td class="quiztable-content-center-white">${list.subTypeName}</td>
						<td class="quiztable-content-center-white" style="width:50%;">
							<pre style="height:100px;">${list.contents}</pre>
						</td>
						<td class="quiztable-content-center-white">
							<c:choose>
								<c:when test="${list.exposure eq 'Y'}">노출</c:when>
								<c:otherwise>비노출</c:otherwise>
							</c:choose>
						</td>
						<td class="quiztable-content-center-white">
							<button class="btn btn-primary btn-sm" onclick="">상세보기</button>
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
						var keyword = '${keyword}';
						$("#divList").load("pressList?rowCount="+rowCount+"&regFromDate="+regFromDate+"&regToDate="+regToDate
								+"&keyword="+keyword+"&page="+page, function(data) {});
					}
				});
			}
			
			$('input[name=selectAll]').on('click', function () {
				var checked = $(this).prop('checked');
				$('input[name=selected]').each(function () {
					if (checked)
						$(this).prop('checked', !($(this).prop('checked') === checked));
					else
						$(this).prop('checked', ($(this).prop('checked') === checked));
				});
			});
		});
	</script>	
	