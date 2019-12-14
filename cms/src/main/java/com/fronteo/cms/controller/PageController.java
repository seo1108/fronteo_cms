package com.fronteo.cms.controller;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.fronteo.cms.common.Const;
import com.fronteo.cms.common.Util;
import com.fronteo.cms.service.PageService;

@Controller
public class PageController {
	@Inject
	private PageService service;
	
	@RequestMapping(value = "page/banner", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView banner(@RequestParam Map<String,Object> params
			,Model model
			,HttpServletRequest req
            ,HttpServletResponse res) {
		try {
			Map<String, Object> totalmap = service.getTotalBannerCount(params);
			long totalCount = Long.parseLong(totalmap.get("cnt").toString());
			model.addAttribute("rowCount", Const.DEFAULT_RESULT_COUNT);
			model.addAttribute("totalCnt", totalCount);
			model.addAttribute("rows", Const.ARR_RESULT_COUNT);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
			
			
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("page/banner");
		
		return modelAndView;
	}
	
	@RequestMapping(value = "page/bannerList", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView bannerList(@RequestParam Map<String,Object> params, 
			Model model
			,HttpServletRequest req
            ,HttpServletResponse res) {
		try {
			int rowCount = Integer.parseInt(params.get("rowCount").toString());
			int startIdx = 0;
			int page = Integer.parseInt(Util.checkNull(params.get("page"), "1"));
			
			Map<String, Object> map = service.getBannerCount(params);
			
			long totalCount = Long.parseLong(map.get("cnt").toString());
			int totalPage = (int) Math.ceil((double) totalCount / rowCount);
			startIdx = (page - 1) * rowCount;
			
			params.put("reqType", "view");
			params.put("startIdx", startIdx);
			params.put("rowCount", rowCount);
			
			List<Map<String, Object>> list = service.getBannerList(params);
			
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
		modelAndView.setViewName("page/bannerList");
		
		return modelAndView;
	}
	
	
	@RequestMapping(value = "page/bannerAdd", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView bannerAdd(@RequestParam Map<String,Object> params
			,Model model
			,HttpServletRequest req
            ,HttpServletResponse res) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();

			if (null == params.get("type") || "".equals(params.get("type"))) {
				
			} else if ("edit".equals(params.get("type"))) {
				map = service.getBannerDetail(params);
			}
			
			map.put("type", params.get("type"));
			model.addAttribute("data", map);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
			
			
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("page/bannerAdd");
		
		return modelAndView;
	}
	
	@RequestMapping(value = "page/bannerInsert", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public String bannerInsert(@RequestParam Map<String,Object> params
			,HttpServletRequest req
            ,HttpServletResponse res) {
		String msg = "";
		try {
			String filepath = ContentFileUpload(req, res);
			params.put("filePath", filepath);

			int result = 0;
			
			result = service.insertBanner(params);
			
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
	
	@RequestMapping(value = "json/updateBanner", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateBanner(@RequestParam Map<String, Object> params,
			Model model,
			HttpServletRequest req, 
			HttpServletResponse res) {
		try {
			int cnt = service.updateBannerExposure(params);
			params.put("STATUS", (cnt > 0) ? "SUCCESS" : "FAIL");
		} catch (Exception e) {
			
			params.put("STATUS", "FAIL");
			e.printStackTrace();
		}
		
		return params;
	}
	
	@RequestMapping(value = "json/deleteBanner", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deleteBanner(@RequestParam Map<String, Object> params,
			Model model,
			HttpServletRequest req, 
			HttpServletResponse res) {
		try {
			String[] targets = params.get("code").toString().split(",");
			params.put("code", targets);
			int cnt = service.deleteBanner(params);
			
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
			String path = Const.BANNER_FILE_UPLOAD_PATH;
			
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
				filepath = Const.BANNER_SERVER_PATH+filename;
			}
			
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return filepath;
	}
	
}
