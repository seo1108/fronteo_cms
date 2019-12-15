package com.fronteo.cms.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
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
			model.addAttribute("keyword", params.get("keyword"));
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
			Map<String, Object> map = new HashMap<String, Object>();

			if (null == params.get("type") || "".equals(params.get("type"))) {
				map = service.getMaxBbsSeq(params);
			} else if ("edit".equals(params.get("type"))) {
				map = service.getBbsDetail(params);
			}
			
			map.put("type", params.get("type"));
			model.addAttribute("data", map);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
			
			
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("board/pressAdd");
		
		return modelAndView;
	}
	
	@RequestMapping(value = "board/event", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView event(@RequestParam Map<String,Object> params
			,Model model
			,HttpServletRequest req
            ,HttpServletResponse res) {
		try {
			params.put("bbsType", "E");
			Map<String, Object> totalmap = service.getTotalBbsCount(params);
			long totalCount = Long.parseLong(totalmap.get("cnt").toString());
			model.addAttribute("rowCount", Const.DEFAULT_RESULT_COUNT);
			model.addAttribute("totalCnt", totalCount);
			model.addAttribute("rows", Const.ARR_RESULT_COUNT);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
			
			
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("board/event");
		
		return modelAndView;
	}
	
	@RequestMapping(value = "board/eventList", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView eventList(@RequestParam Map<String,Object> params, 
			Model model
			,HttpServletRequest req
            ,HttpServletResponse res) {
		try {
			params.put("bbsType", "E");
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
					rmap.put("subTypeName", "Seminar");
				} 
				else if ("2".equals(rmap.get("subType"))) 
				{
					rmap.put("subTypeName", "Event");
				}
				else if ("3".equals(rmap.get("subType"))) 
				{
					rmap.put("subTypeName", "Others");
				}
			}
			
			model.addAttribute("data", list);
			model.addAttribute("totalCnt", totalCount);
			model.addAttribute("page", page);
			model.addAttribute("totalPage", totalPage);
			model.addAttribute("regFromDate", params.get("regFromDate"));
			model.addAttribute("regToDate", params.get("regToDate"));
			model.addAttribute("keyword", params.get("keyword"));
			model.addAttribute("rowCount", params.get("rowCount").toString());
		} catch (Exception ex) {
			params.put("STATUS", "FAIL");
			ex.printStackTrace();
		}
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("board/eventList");
		
		return modelAndView;
	}
	
	
	@RequestMapping(value = "board/eventAdd", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView eventAdd(@RequestParam Map<String,Object> params
			,Model model
			,HttpServletRequest req
            ,HttpServletResponse res) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();

			if (null == params.get("type") || "".equals(params.get("type"))) {
				map = service.getMaxBbsSeq(params);
			} else if ("edit".equals(params.get("type"))) {
				map = service.getBbsDetail(params);
			}
			
			map.put("type", params.get("type"));
			model.addAttribute("data", map);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
			
			
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("board/eventAdd");
		
		return modelAndView;
	}
	
	@RequestMapping(value = "board/career", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView career(@RequestParam Map<String,Object> params
			,Model model
			,HttpServletRequest req
            ,HttpServletResponse res) {
		try {
			params.put("bbsType", "C");
			Map<String, Object> totalmap = service.getTotalBbsCount(params);
			long totalCount = Long.parseLong(totalmap.get("cnt").toString());
			model.addAttribute("rowCount", Const.DEFAULT_RESULT_COUNT);
			model.addAttribute("totalCnt", totalCount);
			model.addAttribute("rows", Const.ARR_RESULT_COUNT);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
			
			
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("board/career");
		
		return modelAndView;
	}
	
	@RequestMapping(value = "board/careerList", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView careerList(@RequestParam Map<String,Object> params, 
			Model model
			,HttpServletRequest req
            ,HttpServletResponse res) {
		try {
			params.put("bbsType", "C");
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
					rmap.put("subTypeName", "신입");
				} 
				else if ("2".equals(rmap.get("subType"))) 
				{
					rmap.put("subTypeName", "경력");
				}
			}
			
			model.addAttribute("data", list);
			model.addAttribute("totalCnt", totalCount);
			model.addAttribute("page", page);
			model.addAttribute("totalPage", totalPage);
			model.addAttribute("regFromDate", params.get("regFromDate"));
			model.addAttribute("regToDate", params.get("regToDate"));
			model.addAttribute("keyword", params.get("keyword"));
			model.addAttribute("rowCount", params.get("rowCount").toString());
		} catch (Exception ex) {
			params.put("STATUS", "FAIL");
			ex.printStackTrace();
		}
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("board/careerList");
		
		return modelAndView;
	}
	
	
	@RequestMapping(value = "board/careerAdd", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView careerAdd(@RequestParam Map<String,Object> params
			,Model model
			,HttpServletRequest req
            ,HttpServletResponse res) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();

			if (null == params.get("type") || "".equals(params.get("type"))) {
				map = service.getMaxBbsSeq(params);
			} else if ("edit".equals(params.get("type"))) {
				map = service.getBbsDetail(params);
			}
			
			map.put("type", params.get("type"));
			model.addAttribute("data", map);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
			
			
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("board/careerAdd");
		
		return modelAndView;
	}
	
	@RequestMapping(value = "board/faq", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView faq(@RequestParam Map<String,Object> params
			,Model model
			,HttpServletRequest req
            ,HttpServletResponse res) {
		try {
			params.put("bbsType", "F");
			Map<String, Object> totalmap = service.getTotalBbsCount(params);
			long totalCount = Long.parseLong(totalmap.get("cnt").toString());
			model.addAttribute("rowCount", Const.DEFAULT_RESULT_COUNT);
			model.addAttribute("totalCnt", totalCount);
			model.addAttribute("rows", Const.ARR_RESULT_COUNT);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
			
			
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("board/faq");
		
		return modelAndView;
	}
	
	@RequestMapping(value = "board/faqList", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView faqList(@RequestParam Map<String,Object> params, 
			Model model
			,HttpServletRequest req
            ,HttpServletResponse res) {
		try {
			params.put("bbsType", "F");
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
			
			model.addAttribute("data", list);
			model.addAttribute("totalCnt", totalCount);
			model.addAttribute("page", page);
			model.addAttribute("totalPage", totalPage);
			model.addAttribute("regFromDate", params.get("regFromDate"));
			model.addAttribute("regToDate", params.get("regToDate"));
			model.addAttribute("keyword", params.get("keyword"));
			model.addAttribute("rowCount", params.get("rowCount").toString());
		} catch (Exception ex) {
			params.put("STATUS", "FAIL");
			ex.printStackTrace();
		}
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("board/faqList");
		
		return modelAndView;
	}
	
	
	@RequestMapping(value = "board/faqAdd", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView faqAdd(@RequestParam Map<String,Object> params
			,Model model
			,HttpServletRequest req
            ,HttpServletResponse res) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();

			if (null == params.get("type") || "".equals(params.get("type"))) {
				map = service.getMaxBbsSeq(params);
			} else if ("edit".equals(params.get("type"))) {
				map = service.getBbsDetail(params);
			}
			
			map.put("type", params.get("type"));
			model.addAttribute("data", map);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
			
			
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("board/faqAdd");
		
		return modelAndView;
	}
	
	@RequestMapping(value = "board/bbsInsert", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public String bbsInsert(@RequestParam Map<String,Object> params
			,HttpServletRequest req
            ,HttpServletResponse res) {
		String msg = "";
		try {
//			if (null != params.get("filePath")) {
//				String filepath = ContentFileUpload(req, res);
//				params.put("filePath", filepath);
//			}
			
			
			// 파일이 첨부되지 않았거나, 파일 변경이 없을때
			if ("P".equals(params.get("bbsType")) || "E".equals(params.get("bbsType"))) { 
				if ("".equals(params.get("file_route")) || params.get("file_route").toString().contains(Const.BBS_SERVER_PATH)) {
					params.put("filePath", params.get("file_route"));
				} else {
					String filepath = ContentFileUpload(req, res);
					params.put("filePath", filepath);	
				}
			}
			
			if ("C".equals(params.get("bbsType"))) {
				if (null == params.get("isAnytime") || "".equals(params.get("isAnytime"))) {
					params.put("isAnytime", "N");
				}
			}
			
			
			int result = 0;
			
			if (null != params.get("updateType") && "edit".equals(params.get("updateType"))) 
			{
				result = service.updateBbs(params);
			}
			else 
			{
				result = service.insertBbs(params);
			}
			
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
	
	
	@RequestMapping(value = "json/deleteBbs", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deleteBbs(@RequestParam Map<String, Object> params,
			Model model,
			HttpServletRequest req, 
			HttpServletResponse res) {
		try {
			String[] targets = params.get("code").toString().split(",");
			params.put("code", targets);
			int cnt = service.deleteBbs(params);
			
			params.put("STATUS", (cnt > 0) ? "SUCCESS" : "FAIL");
			params.put("SavedCount", cnt);
			params.put("RequestCount", targets.length);
		} catch (Exception e) {
			
			params.put("STATUS", "FAIL");
			e.printStackTrace();
		}
		
		return params;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ResponseBody
	public String ContentFileUpload(HttpServletRequest req, HttpServletResponse res) {
		String filepath = "";
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		try { 
			String path = Const.BBS_FILE_UPLOAD_PATH;
			
			System.out.println("_____________" + path);
			String filename = "";
			
			MultipartHttpServletRequest mhsr = (MultipartHttpServletRequest) req; 
			Iterator iter = mhsr.getFileNames(); 
			MultipartFile mfile = null; 
			String fieldName = ""; 
			String origName = ""; 
			List resultList = new ArrayList(); 
			
			File dir = new File(path); 
			if (!dir.isDirectory()) { 
				dir.mkdirs(); 
			}  
			while (iter.hasNext()) { 
				fieldName = (String) iter.next(); 
				mfile = mhsr.getFile(fieldName); 
				
				origName = new String(mfile.getOriginalFilename().getBytes(), "UTF-8"); 
				
				if ("".equals(origName)) { continue; } 
				String extension = "";
				int lastIndexOf = origName.lastIndexOf(".");
			    if (lastIndexOf == -1) {
			        
			    }
			    extension = origName.substring(lastIndexOf);
			    
				String saveFileName = origName.substring(0, origName.lastIndexOf(".")) + "_" + timestamp.getTime() + extension;
				filename = saveFileName;
				
				File serverFile = new File(path + File.separator + saveFileName); 
				mfile.transferTo(serverFile); 
				Map file = new HashMap(); 
				file.put("origName", origName); 
				file.put("sfile", serverFile); 
				resultList.add(file); 
			}
			
			if (!"".equals(filename)) {
				filepath = Const.BBS_SERVER_PATH+filename;
			}
			
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return filepath;
	}
}
