package com.fronteo.cms.session;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class SessionListener implements HttpSessionListener {
	
	//private static final Logger logger = LoggerFactory.getLogger(SessionListener.class);
	
	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		HttpSession session = arg0.getSession();
		/*session.setMaxInactiveInterval(60*60);*/
		session.setMaxInactiveInterval(-1);
		
		
		//logger.info(String.format("\n\n.............sessionCreated Session ID:[%s]\n\n", arg0.getSession().getId()));
		
	}
 
	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		ApplicationContext context = (ApplicationContext) WebApplicationContextUtils.getWebApplicationContext(arg0.getSession().getServletContext());
		//UserInfoVO info = (UserInfoVO)arg0.getSession().getAttribute("SESSION_USERINFO");
		
		//logger.info(String.format("\n\n............sessionDestroyed Session ID:[%s]\n\n", arg0.getSession().getId()));
		
		/*if(info!=null && info.getUserSeq()!=null && !info.getUserSeq().equals("")){
		}*/
	}
}