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
						<i class="fa fa-briefcase"></i> <span>SalesForce 관리</span> 
					</a>
				</li>

	            <li class="treeview" data-uri="/board/">
 					<a href="#">
						<i class="fa fa-list"></i> <span>게시판 관리</span> <i class="fa fa-angle-left pull-right"></i>
					</a>
					<ul class="treeview-menu">
						<li <%=curUri.indexOf("board/press") > 0
  									?"class=\"active\"":"" %>>
							<a href="/board/press"><i class="fa fa-angle-right"></i> <span>Press</span></a>
						</li>
						<li <%=curUri.indexOf("board/event") > 0
  									?"class=\"active\"":"" %>>
							<a href="/board/event"><i class="fa fa-angle-right"></i> <span>Event</span></a>
						</li>
						<li <%=curUri.indexOf("board/career") > 0
  									?"class=\"active\"":"" %>>
  							<a href="/board/career"><i class="fa fa-angle-right"></i> <span>Careers</span>
							</a>	
						</li>	
						<li <%=curUri.indexOf("board/faq") > 0
  									?"class=\"active\"":"" %>>
  							<a href="/board/faq"><i class="fa fa-angle-right"></i> <span>FAQs</span>
							</a>	
						</li>	
					</ul>
	            </li>

				<li class="treeview" data-uri="/page/">
					<a href="#">
						<i class="fa fa-edit"></i> <span>페이지 관리</span> <i class="fa fa-angle-left pull-right"></i>
					</a>
					<ul class="treeview-menu">
						<li <%=curUri.indexOf("page/banner") > 0
  									?"class=\"active\"":"" %>>
							<a href="/page/banner"><i class="fa fa-angle-right"></i> <span>Home</span></a>
						</li>
						<li <%=curUri.indexOf("page/edbook") > 0
  									?"class=\"active\"":"" %>>
							<a href="/page/edbook"><i class="fa fa-angle-right"></i> <span>eDiscovory Book</span></a>
						</li>
					</ul>
				</li>

            	<li class="treeview" data-uri="/contents/">
 					<a href="#">
						<i class="fa fa-file-pdf-o"></i> <span>콘텐츠 관리</span> <i class="fa fa-angle-left pull-right"></i>
					</a>
					<ul class="treeview-menu">
  						<li <%=curUri.indexOf("contents/brochure") > 0
  									?"class=\"active\"":"" %>>
							<a href="/contents/brochure"><i class="fa  fa-angle-right"></i>Company Brochure</a>
   						</li>
  						<li <%=curUri.indexOf("contents/briefs") > 0
  									?"class=\"active\"":"" %>>
							<a href="/contents/briefs"><i class="fa  fa-angle-right"></i>Product Briefs</a>
   						</li>
   						<li <%=curUri.indexOf("contents/video") > 0
  									?"class=\"active\"":"" %>>
							<a href="/contents/video"><i class="fa fa-angle-right"></i>Videos</a>
   						</li>	
   						<li <%=curUri.indexOf("contents/ebook") > 0
  									?"class=\"active\"":"" %>>
							<a href="/contents/ebook"><i class="fa fa-angle-right"></i>E-Book</a>
   						</li>
   						<li <%=curUri.indexOf("contents/whitepaper") > 0
  									?"class=\"active\"":"" %>>
							<a href="/contents/whitepaper"><i class="fa fa-angle-right"></i>Whitepapers</a>
   						</li>
					</ul>
				</li>
			</ul>
		</section>
	</aside>