package com.fronteo.cms.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fronteo.cms.common.Const;
import com.fronteo.cms.common.Util;
import com.fronteo.cms.service.BoardService;

@Controller
public class BoardController {
	@Inject
	private BoardService service;
	
	@RequestMapping(value = "board/press", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView press(@RequestParam Map<String,Object> params
			,Model model
			,HttpServletRequest req
            ,HttpServletResponse res) {
		try {
			params.put("bbsType", "P");
			Map<String, Object> totalmap = service.getTotalBbsCount(params);
			long totalCount = Long.parseLong(totalmap.get("cnt").toString());
			model.addAttribute("rowCount", Const.DEFAULT_RESULT_COUNT);
			model.addAttribute("totalCnt", totalCount);
			model.addAttribute("rows", Const.ARR_RESULT_COUNT);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
			
			
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("board/press");
		
		return modelAndView;
	}
	
	@RequestMapping(value = "board/pressList", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView pressList(@RequestParam Map<String,Object> params, 
			Model model
			,HttpServletRequest req
            ,HttpServletResponse res) {
		try {
			params.put("bbsType", "P");
			int rowCount = Integer.parseInt(params.get("rowCount").toString());
			int startIdx = 0;
			int page = Integer.parseInt(Util.checkNull(params.get("page"), "1"));
			
			Map<String, Object> map = service.getBbsCount(params);
			
			long totalCount = Long.parseLong(map.get("cnt").toString());
			int totalPage = (int) Math.ceil((double) totalCount / rowCount);
			startIdx = (page - 1) * rowCount;
			
			params.put("reqType", "view");
			params.put("startIdx", startIdx);
			params.put("rowCount", rowCount);
			
			List<Map<String, Object>> list = service.getBbsList(params);
			
			for (Map<String,Object> rmap:list) {
				if ("1".equals(rmap.get("subType"))) 
				{
					rmap.put("subTypeName", "eDiscovery");
				} 
				else if ("2".equals(rmap.get("subType"))) 
				{
					rmap.put("subTypeName", "Business Solution");
				}
				else if ("3".equals(rmap.get("subType"))) 
				{
					rmap.put("subTypeName", "AI Consulting");
				}
				else if ("4".equals(rmap.get("subType"))) 
				{
					rmap.put("subTypeName", "Corporate");
				}

			}
			
			model.addAttribute("data", list);
			model.addAttribute("totalCnt", totalCount);
			model.addAttribute("page", page);
			model.addAttribute("totalPage", totalPage);
			model.addAttribute("regFromDate", params.get("regFromDate"));
			model.addAttribute("regToDate", params.get("regToDate"));
			model.addAttribute("rowCount", params.get("rowCount").toString());
		} catch (Exception ex) {
			params.put("STATUS", "FAIL");
			ex.printStackTrace();
		}
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("board/pressList");
		
		return modelAndView;
	}
	
	
	@RequestMapping(value = "board/pressAdd", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView pressAdd(@RequestParam Map<String,Object> params
			,Model model
			,HttpServletRequest req
            ,HttpServletResponse res) {
		try {
		} catch (Exception ex) {
			ex.printStackTrace();
		}
			
			
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("board/pressAdd");
		
		return modelAndView;
	}
	
	@RequestMapping(value = "board/bbsInsert", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public String bbsInsert(@RequestParam Map<String,Object> params
			,HttpServletRequest req
            ,HttpServletResponse res) {
		String msg = "";
		try {
			int result = service.insertBbs(params);
			
			if (result > 0) {
				msg = "ok";
			} else {
				msg = "fail";
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return msg;
	}
}
