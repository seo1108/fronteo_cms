package com.fronteo.cms.controller;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fronteo.cms.common.Util;

@Controller
public class LoginController {
private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@RequestMapping(value = "/redirect", method = RequestMethod.GET)
	public ModelAndView indexRedirct(@RequestParam Map<String, String> params, Model model, HttpServletRequest req,
			HttpServletResponse res) {
		logger.info("\n\n\n----------------redirect-------------------------------\n\n");
		return new ModelAndView("/redirect");
	}

	@RequestMapping(value = "/login", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView loginPage(@RequestParam Map<String, String> params, Model model, HttpServletRequest req,
			HttpServletResponse res) {
		logger.info("\n\n\n----------------login-------------------------------\n\n");
		model.addAttribute("returnUrl", params.get("returnUrl"));
		return new ModelAndView("/login");
	}
	
	@RequestMapping(value = "/json/login", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> login(@RequestParam Map<String, Object> params, HttpServletRequest req,
			HttpServletResponse res) {
		try {
			//boolean isExist = service.checkLogin(params, req, res);
			//params.put("STATUS", isExist ? "SUCCESS" : "FAIL");
			
			String adminId = Util.checkNull(params.get("adminId"), "");
			String password = Util.checkNull(params.get("password"), "");
			
			if ("fronteo".equals(adminId) && "123qwe!@#".equals(password)) {
				params.put("STATUS", "SUCCESS");
				params.put("returnUrl", "dashboard/dashboard");
			} else {
				params.put("STATUS", "FAIL");
			}
			
			
		} catch (Exception ex) {
			params.put("STATUS", "FAIL");
			ex.printStackTrace();
		}
		
		return params;
	}

	@RequestMapping(value = "/json/logout", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> logout(@RequestParam Map<String, String> params, HttpServletRequest req,
			HttpServletResponse res) {

		HttpSession session = req.getSession();
		if (session != null) {
			session.invalidate();
		}
		
		params.put("STATUS", "SUCCESS");
		return params;
	}
}
