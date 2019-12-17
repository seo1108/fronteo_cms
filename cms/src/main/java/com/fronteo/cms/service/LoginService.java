package com.fronteo.cms.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fronteo.cms.common.Util;
import com.fronteo.cms.dao.LoginDao;
import com.fronteo.cms.dto.UserInfo;

@Service
public class LoginService {
	@Autowired
	private LoginDao dao;
	
	public void UserInfoSetSessionAttr(UserInfo dto, HttpServletRequest httpSvltReq) throws Exception {
		httpSvltReq.getSession().setAttribute("SESSION_USERINFO", dto);
		httpSvltReq.getSession().setAttribute("adminId", dto.getAdminId());
		httpSvltReq.getSession().setMaxInactiveInterval(60 * 60 * 24);
	}
	
	public boolean checkLogin(Map<String, Object> params,
			HttpServletRequest httpSvltReq,
			HttpServletResponse httpSvltRes) throws Exception {
		try {	
			
			boolean isExist = dao.existAdmin(params);
			
			if (isExist) {
				Map<String, Object> adminmap = dao.getAdminInfo(params);
				
				UserInfo dto = Util.getUserInfo(httpSvltReq);
				dto.setAdminId(adminmap.get("adminId").toString());
				UserInfoSetSessionAttr(dto, httpSvltReq);
				httpSvltReq.getSession().setAttribute("isRedirect", "F");
				
				return true;
			} else {
				return false;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
	
}
