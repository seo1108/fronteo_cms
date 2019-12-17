package com.fronteo.cms.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.fronteo.cms.common.Util;
import com.fronteo.cms.dto.UserInfo;

@Service
public class LoginService {
	public void UserInfoSetSessionAttr(UserInfo dto, HttpServletRequest httpSvltReq) throws Exception {
		httpSvltReq.getSession().setAttribute("SESSION_USERINFO", dto);
		httpSvltReq.getSession().setAttribute("userId", dto.getUserId());
		httpSvltReq.getSession().setMaxInactiveInterval(60 * 60);
	}
	
	public boolean checkLogin(Map<String, Object> params,
			HttpServletRequest httpSvltReq,
			HttpServletResponse httpSvltRes) throws Exception {
		try {	
			if ("fronteo".equals(params.get("userId")) && "123qwe!@#".equals(params.get("password"))) {
				UserInfo dto = Util.getUserInfo(httpSvltReq);
				dto.setUserId(params.get("userId").toString());
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
