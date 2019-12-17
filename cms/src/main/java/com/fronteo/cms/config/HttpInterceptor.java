package com.fronteo.cms.config;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.fronteo.cms.common.Util;
import com.fronteo.cms.dto.UserInfo;

@Controller("HttpInterceptor")
public class HttpInterceptor extends HandlerInterceptorAdapter { 
	private static final Logger logger = LoggerFactory.getLogger(HttpInterceptor.class);
	
	// 맵핑되기 전 처리를 해주면 된다. 
	@Override 
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
	{ 
		logger.info("================ Before Method"); 
		//return super.preHandle(request, response, handler);
		
//		boolean bn = true;
//		
//		String contextPath = request.getContextPath();
//		UserInfo info = Util.getUserInfo(request);
//		if (null == info || !info.isLogin()) {
//			logger.info("================ GO TO LOGIN");
//			response.sendRedirect(contextPath+"/login");
//			bn=false;
//		} 
//		
//		logger.info("================ GO ON");
//
		boolean bn = true;
		
		String contextPath = request.getContextPath();
		String indexURL	  = contextPath+"/login";
		String redirectURL  = contextPath+"/redirect";
		String URI = request.getRequestURI();
		logger.info(String.format(".........indexURL:[%s]/redirectURL:[%s]..URI:[%s]", indexURL,redirectURL,URI));
		String isRedirect = (String)request.getSession().getAttribute("isRedirect");
		logger.info("================================ " + contextPath + isRedirect);
		
		if (isRedirect != null && isRedirect.equals("T")) {
			request.getSession().setAttribute("isRedirect", null);
			response.sendRedirect(contextPath+"/login");
			
			return false;
		} else {
			if (!URI.equals(indexURL) && !URI.equals(redirectURL)) {
				UserInfo info = Util.getUserInfo(request);
				if (!info.isLogin()) {
					logger.info("================================1 " + isRedirect);
					request.getSession().setAttribute("isRedirect", "T");
					response.sendRedirect(contextPath+"/login");
					
					return false;
				} else {
					logger.info("================================2 " + isRedirect);
				}
			} else {
				logger.info("================================3 " + isRedirect);
			}
		}

		return bn;

	} 
	
	// 맵핑되고난 후 처리를 해주면 된다. 
	@Override 
	public void postHandle( HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception
	{ 
		logger.info("================ Method Executed"); 
		super.postHandle(request, response, handler, modelAndView);
		request.getSession().setAttribute("isRedirect", "F");
	}
	
	// 모든 작업이 완료된 후 실행된다.
	@Override 
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception 
	{ 
		logger.info("================ Method Completed"); 
		//super.afterCompletion(request, response, handler, ex);
	}
}
