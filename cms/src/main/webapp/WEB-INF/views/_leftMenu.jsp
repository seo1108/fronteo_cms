<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<%
	String curUri = request.getRequestURI();
%>
	<aside class="main-sidebar">
		<section class="sidebar">
			<ul class="sidebar-menu active">
				<li class="header">MENUS</li>

 				<li class="treeview" data-uri="/salesforce/">
					<a href="/salesforce/salesforce">
						<i class="fa fa-crosshairs"></i> <span>SalesForce 관리</span> 
					</a>
				</li>

	            <li class="treeview" data-uri="/board/">
 					<a href="#">
						<i class="fa fa-list"></i> <span>게시판 관리</span> <i class="fa fa-angle-left pull-right"></i>
					</a>
					<ul class="treeview-menu">
						<li <%=curUri.indexOf("board/press")>0 || curUri.indexOf("board/pressAdd")>0
  									?"class=\"active\"":"" %>>>
							<a href="/board/press"><i class="fa fa-circle-o"></i> <span>Press</span></a>
						</li>
						<li>
							<a href="/board/event"><i class="fa fa-circle-o"></i> <span>Event</span></a>
						</li>
						<li> 
  							<a href="/board/careers"><i class="fa fa-circle-o"></i> <span>Careers</span>
							</a>	
						</li>	
						<li> 
  							<a href="/board/faqs"><i class="fa fa-circle-o"></i> <span>FAQs</span>
							</a>	
						</li>	
					</ul>
	            </li>

				<li class="treeview" data-uri="/page/">
					<a href="#">
						<i class="fa fa-object-ungroup"></i> <span>페이지 관리</span> <i class="fa fa-angle-left pull-right"></i>
					</a>
					<ul class="treeview-menu">
						<li>
							<a href="/page/homepage"><i class="fa fa-circle-o"></i> <span>Home</span></a>
						</li>
						<li>
							<a href="/page/ediscovrybook"><i class="fa fa-circle-o"></i> <span>eDiscovory Book</span></a>
						</li>
					</ul>
				</li>

            	<li class="treeview" data-uri="/contents/">
 					<a href="#">
						<i class="fa fa-book"></i> <span>콘텐츠 관리</span> <i class="fa fa-angle-left pull-right"></i>
					</a>
					<ul class="treeview-menu">
  						<li>
							<a href="/contents/brochure"><i class="fa fa-circle-o"></i>Company Brochure </a>
   						</li>
  						<li>
							<a href="/contents/priefs"><i class="fa fa-circle-o"></i>Product Briefs</a>
   						</li>
   						<li>
							<a href="/contents/videos"><i class="fa fa-circle-o"></i>Videos</a>
   						</li>	
   						<li>
							<a href="/contents/ebook"><i class="fa fa-circle-o"></i>E-Book</a>
   						</li>
   						<li>
							<a href="/contents/whitepapers"><i class="fa fa-circle-o"></i>Whitepapers</a>
   						</li>
					</ul>
				</li>
			</ul>
		</section>
	</aside>