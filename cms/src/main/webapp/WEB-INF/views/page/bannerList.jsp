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
					<th class="text-center">등록일</th>
					<th class="text-center">제목</th>
					<th class="text-center">이미지 / URL</th>
					<th class="text-center">관리</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${data}" var="list" varStatus="status">
					<tr class="text-center">
						<th class="quiztable-content-center-white">
							<input type="checkbox" name="selected" value="${list.bannerSeq}" class="form-check-input" />
						</th>
						<td class="quiztable-content-center-white"><fmt:formatNumber value="${list.bannerSeq}" pattern="#,###" /></td>
						<td class="quiztable-content-center-white">${list.regdate}</td>
						<td class="quiztable-content-center-white">${list.title}</td>
						<td class="quiztable-content-center-white">
							<img src="${list.filePath}" style="max-width:200px;" /></td>
						<td class="quiztable-content-center-white">
							<input type="radio" name="exposure${list.bannerSeq}" value="Y" style="margin-right: 10px;" onclick="updateBannerExposure('${list.bannerSeq}');"
								<c:if test="${list.exposure eq 'Y'}">checked</c:if>><span>노출</span>
							<input type="radio" name="exposure${list.bannerSeq}" value="N" style="margin-right: 10px; margin-left: 20px;"
								<c:if test="${list.exposure eq 'N'}">checked</c:if>><span>비노출</span>
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
						$("#divList").load("bannerList?rowCount="+rowCount+"&regFromDate="+regFromDate+"&regToDate="+regToDate
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
		
		function go_detail(bannerSeq) {
			$.redirect( "bannerAdd", { bannerSeq : bannerSeq, type : 'edit' }, "POST", "" );
		}
		
		function updateBannerExposure(bannerSeq) {
			swal("상단 배너는 최대 1개까지만 노출 가능합니다.\n계속 진행하시겠습니까?", {
				buttons: {
					cancel: "취소",
				    defeat: "진행",
				},
			})
			.then(function(value)  {
			  switch (value) {
			    case "defeat":
			    	$.post('/json/updateBanner', { bannerSeq: bannerSeq }, function(data) {
						if (data.STATUS == 'SUCCESS') {
							swal('적용되었습니다.',"", "success")
							.then(function(value)  { 
								  if(value){
									  reloadResult();
								  } 
						     });
						} else {
							swal("실패했습니다.","다시 시도해 보시기 바랍니다.", "error");
						}
					});
			      	break;
				 
			    default:
			    	swal("취소했습니다.", "", "error");
			    	break;
				}
			});
		}

		
		function delete_banner() {
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
						$.post('/json/deleteBanner', { code: codes.join() }, function(data) {
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
			var page = '1';
			
			$("#divList").html('');
			$("#divList").load("bannerList?rowCount="+rowCount+"&regFromDate="+regFromDate+"&regToDate="+regToDate
					+"&keyword="+keyword+"&page="+page, function(data) {});
		}
	</script>	
	