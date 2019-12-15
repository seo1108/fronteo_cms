package com.fronteo.cms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fronteo.cms.common.Const;
import com.fronteo.cms.common.Util;
import com.fronteo.cms.service.SalesforceService;

@Controller
public class SalesforceController {
	@Inject
	private SalesforceService service;
	
	@RequestMapping(value = "salesforce/salesforce", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView salesforce(@RequestParam Map<String,Object> params
			,Model model
			,HttpServletRequest req
            ,HttpServletResponse res) {
		try {
			Map<String, Object> totalmap = service.getTotalSalesforceCount(params);
			long totalCount = Long.parseLong(totalmap.get("cnt").toString());
			model.addAttribute("rowCount", Const.DEFAULT_RESULT_COUNT);
			model.addAttribute("totalCnt", totalCount);
			model.addAttribute("rows", Const.ARR_RESULT_COUNT);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
			
			
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("salesforce/salesforce");
		
		return modelAndView;
	}
	
	@RequestMapping(value = "salesforce/salesforceList", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView bannerList(@RequestParam Map<String,Object> params, 
			Model model
			,HttpServletRequest req
            ,HttpServletResponse res) {
		try {
			int rowCount = Integer.parseInt(params.get("rowCount").toString());
			int startIdx = 0;
			int page = Integer.parseInt(Util.checkNull(params.get("page"), "1"));
			
			Map<String, Object> map = service.getSalesforceCount(params);
			
			long totalCount = Long.parseLong(map.get("cnt").toString());
			int totalPage = (int) Math.ceil((double) totalCount / rowCount);
			startIdx = (page - 1) * rowCount;
			
			params.put("reqType", "view");
			params.put("startIdx", startIdx);
			params.put("rowCount", rowCount);
			
			List<Map<String, Object>> list = service.getSalesforceList(params);
			
			for (Map<String,Object> rmap:list) {
				// 분류명 
				if ("1".equals(rmap.get("pathType"))) 
				{
					rmap.put("pathName", "Contact");
				} 
				else if ("2".equals(rmap.get("pathType"))) 
				{
					rmap.put("pathName", "E-Book");
				}
				else if ("3".equals(rmap.get("pathType"))) 
				{
					rmap.put("pathName", "Whitepapers");
				}
				
				// 관심항목명
				if ("1".equals(rmap.get("concern"))) 
				{
					rmap.put("concernName", "이디스커버리 서비스");
				} 
				else if ("2".equals(rmap.get("concern"))) 
				{
					rmap.put("concernName", "조사서비스");
				}
				else if ("3".equals(rmap.get("concern"))) 
				{
					rmap.put("concernName", "AI 컨설팅 서비스");
				} else if ("4".equals(rmap.get("concern"))) 
				{
					rmap.put("concernName", "조사분석 플랫폼(Lit-i-View)");
				}
				else if ("5".equals(rmap.get("concern"))) 
				{
					rmap.put("concernName", "AI Kibit");
				} else if ("6".equals(rmap.get("concern"))) 
				{
					rmap.put("concernName", "채용");
				}
				else if ("7".equals(rmap.get("pathType"))) 
				{
					rmap.put("concernName", "세미나 및 이벤트");
				}
			}
			
			model.addAttribute("data", list);
			model.addAttribute("totalCnt", totalCount);
			model.addAttribute("page", page);
			model.addAttribute("totalPage", totalPage);
			model.addAttribute("regFromDate", params.get("regFromDate"));
			model.addAttribute("regToDate", params.get("regToDate"));
			model.addAttribute("rowCount", params.get("rowCount").toString());
			model.addAttribute("pathType", params.get("pathType"));
		} catch (Exception ex) {
			params.put("STATUS", "FAIL");
			ex.printStackTrace();
		}
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("salesforce/salesforceList");
		
		return modelAndView;
	}
	
	
	@RequestMapping(value = "salesforce/salesforceDetail", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView salesforceDetail(@RequestParam Map<String,Object> params
			,Model model
			,HttpServletRequest req
            ,HttpServletResponse res) {
		try {
			Map<String, Object> map = service.getSalesforceDetail(params);
			
			
			// 분류명 
			if ("1".equals(map.get("pathType"))) 
			{
				map.put("pathName", "Contact");
			} 
			else if ("2".equals(map.get("pathType"))) 
			{
				map.put("pathName", "E-Book");
			}
			else if ("3".equals(map.get("pathType"))) 
			{
				map.put("pathName", "Whitepapers");
			}
			
			// 관심항목명
			if ("1".equals(map.get("concern"))) 
			{
				map.put("concernName", "이디스커버리 서비스");
			} 
			else if ("2".equals(map.get("concern"))) 
			{
				map.put("concernName", "조사서비스");
			}
			else if ("3".equals(map.get("concern"))) 
			{
				map.put("concernName", "AI 컨설팅 서비스");
			} else if ("4".equals(map.get("concern"))) 
			{
				map.put("concernName", "조사분석 플랫폼(Lit-i-View)");
			}
			else if ("5".equals(map.get("concern"))) 
			{
				map.put("concernName", "AI Kibit");
			} else if ("6".equals(map.get("concern"))) 
			{
				map.put("concernName", "채용");
			}
			else if ("7".equals(map.get("pathType"))) 
			{
				map.put("concernName", "세미나 및 이벤트");
			}
		
			
			model.addAttribute("data", map);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
			
			
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("salesforce/salesforceDetail");
		
		return modelAndView;
	}
}
