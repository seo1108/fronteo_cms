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
					<th class="text-center">웹표기일</th>
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
						<td class="quiztable-content-center-white">${list.exposuredate}</td>
						<td class="quiztable-content-center-white">${list.subTypeName}</td>
						<td class="quiztable-content-center-white" style="width:50%;">${list.title}</td>
						<td class="quiztable-content-center-white">
							<c:choose>
								<c:when test="${list.exposure eq 'Y'}">노출</c:when>
								<c:otherwise>비노출</c:otherwise>
							</c:choose>
						</td>
						<td class="quiztable-content-center-white">
							<button class="btn btn-primary btn-sm" onclick="go_detail('${list.bbsSeq}');">상세보기</button>
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
						$("#divList").load("careerList?rowCount="+rowCount+"&regFromDate="+regFromDate+"&regToDate="+regToDate
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
		
		function go_detail(bbsSeq) {
			$.redirect( "careerAdd", { bbsSeq : bbsSeq, type : 'edit', bbsType : 'C' }, "POST", "" );
		}
		
		function delete_bbs() {
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
					$('input[name=selected]:checked').each(function () {
						codes.push($(this).val());
					});
					
					if (codes.length > 0) {
						$.post('<%=request.getContextPath() %>/json/deleteBbs', { code: codes.join() }, function(data) {
							if (data.STATUS == 'SUCCESS') {
								swal('총 ' + new Intl.NumberFormat('en-US').format(data.RequestCount) + '건 중 ' + new Intl.NumberFormat('en-US').format(data.SavedCount) + '건이 삭제되었습니다.',"", "success")
								.then(function(value)  { 
									  if(value){
										  reloadResult();
									  } 
							     });
							} else {
								swal("삭제를 실패했습니다.","다시 시도해 보시기 바랍니다.", "error");
							}
						});
					} else {
						swal("삭제할 대상을 선택해주세요.","", "info");
					}
			      	break;
				 
			    default:
			    	swal("취소했습니다.", "", "error");
			    	break;
				}
			});
		}
		
		function reloadResult() {
			var rowCount = '${rowCount}';
			var regFromDate = '${regFromDate}';
			var regToDate = '${regToDate}';
			var keyword = '${keyword}';
			var page = '${page}';
			
			$("#divList").html('');
			$("#divList").load("careerList?rowCount="+rowCount+"&regFromDate="+regFromDate+"&regToDate="+regToDate
					+"&keyword="+keyword+"&page="+page, function(data) {});
		}
	</script>	
	