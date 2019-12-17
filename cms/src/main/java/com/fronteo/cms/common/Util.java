package com.fronteo.cms.common;

import javax.servlet.http.HttpServletRequest;

import com.fronteo.cms.dto.UserInfo;

public class Util {
	public Util(){	}
	
    public static String checkNull(Object obj,String defaultVal){
        String strRtn = "" ;
        if(defaultVal==null) defaultVal = "" ;
        if(obj==null){
            strRtn = defaultVal ;
        }else{
            String tempStr = String.valueOf(obj).trim();
            if(tempStr.equals(""))
                strRtn = defaultVal ;
            else
                strRtn = tempStr ;
        }
        return strRtn ;
    }
    
    public static UserInfo getUserInfo(HttpServletRequest request){
   	 UserInfo info = (UserInfo)request.getSession().getAttribute("SESSION_USERINFO");
		
   	 return info == null?new UserInfo():info;
	}
}
