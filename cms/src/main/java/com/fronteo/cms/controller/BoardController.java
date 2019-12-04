package com.fronteo.cms.controller;

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
import com.fronteo.cms.service.BoardService;

@Controller
public class BoardController {
	@Inject
	private BoardService service;
	
	@RequestMapping(value = "board/press", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView pressList(@RequestParam Map<String,Object> params
			,Model model
			,HttpServletRequest req
            ,HttpServletResponse res) {
		try {
//			Map<String, Object> totalmap = service.getTotalMemberCount(params);
//			long totalMemberCount = Long.parseLong(totalmap.get("cnt").toString());
//			model.addAttribute("rowCount", Const.DEFAULT_RESULT_COUNT);
//			model.addAttribute("totalMemberCnt", totalMemberCount);
			model.addAttribute("rows", Const.ARR_RESULT_COUNT);
			model.addAttribute("total", 0);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
			
			
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("board/press");
		
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
}
