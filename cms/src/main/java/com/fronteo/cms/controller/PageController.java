package com.fronteo.cms.controller;

import java.io.File;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.fronteo.cms.dto.Ebook;
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
			model.addAttribute("keyword", params.get("keyword"));
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
			if ("".equals(params.get("file_route")) || params.get("file_route").toString().contains(Const.BANNER_SERVER_PATH)) {
				params.put("filePath", params.get("file_route"));
			} else {
				String filepath = ContentFileUpload(req, res);
				params.put("filePath", filepath);	
			}
			
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
	
	
	/**
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * eDiscovery Book
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	@RequestMapping(value = "page/edbook", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView edbook(@RequestParam Map<String,Object> params
			,Model model
			,HttpServletRequest req
            ,HttpServletResponse res) {
		try {
			Map<String, Object> totalmap = service.getTotalEbookCount(params);
			long totalCount = Long.parseLong(totalmap.get("cnt").toString());
			model.addAttribute("rowCount", Const.DEFAULT_RESULT_COUNT);
			model.addAttribute("totalCnt", totalCount);
			model.addAttribute("rows", Const.ARR_RESULT_COUNT);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
			
			
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("page/edbook");
		
		return modelAndView;
	}
	
	@RequestMapping(value = "page/edbookList", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView edbookList(@RequestParam Map<String,Object> params, 
			Model model
			,HttpServletRequest req
            ,HttpServletResponse res) {
		try {
			int rowCount = Integer.parseInt(params.get("rowCount").toString());
			int startIdx = 0;
			int page = Integer.parseInt(Util.checkNull(params.get("page"), "1"));
			
			Map<String, Object> map = service.getEbookCount(params);
			
			long totalCount = Long.parseLong(map.get("cnt").toString());
			int totalPage = (int) Math.ceil((double) totalCount / rowCount);
			startIdx = (page - 1) * rowCount;
			
			params.put("reqType", "view");
			params.put("startIdx", startIdx);
			params.put("rowCount", rowCount);
			
			List<Map<String, Object>> list = service.getEbookList(params);
			
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
		modelAndView.setViewName("page/edbookList");
		
		return modelAndView;
	}
	
	
	@RequestMapping(value = "page/edbookAdd", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView edbookAdd(@RequestParam Map<String,Object> params
			,Model model
			,HttpServletRequest req
            ,HttpServletResponse res) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();

			if (null == params.get("type") || "".equals(params.get("type"))) {
				map = service.getMaxEbookSeq(params);
			} else if ("edit".equals(params.get("type"))) {
				map = service.getEbookDetail(params);
				
				List<Map<String, Object>> list = service.getEbookContentList(map);
				model.addAttribute("contentList", list);
			}
			
			if (null != params.get("needOrderUpdate") && "Y".equals(params.get("needOrderUpdate"))) {
				model.addAttribute("needOrderUpdate", "Y");
			}
			
			map.put("previewUrl", Const.PREVIEW_URL);
			map.put("type", params.get("type"));
			model.addAttribute("data", map);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
			
			
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("page/edbookAdd");
		
		return modelAndView;
	}
	
	@RequestMapping(value = "page/edbookInsert", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public String edbookInsert(@RequestParam Map<String,Object> params
			,HttpServletRequest req
            ,HttpServletResponse res) {
		String msg = "fail";
		try {
			int result = 0;
			
			// 노출일이 없으면 오늘 날짜로 
			if (null != params.get("exposuredate") && params.get("exposuredate").toString().length() != 8) 
			{
				SimpleDateFormat format = new SimpleDateFormat ( "yyyy-MM-dd");
						
				Date date = new Date();
						
				String time = format.format(date);
				params.put("exposuredate", time);
			}
			
			if (null != params.get("updateType") && "edit".equals(params.get("updateType"))) 
			{
				result = service.updateEbook(params);
				
				if (result > 0) {
					msg = "ok";
				} else {
					msg = "fail";
				}
			}
			else 
			{
				Ebook ebook = new Ebook();
				ebook.setTitle(params.get("title").toString());
				ebook.setExposure(params.get("exposure").toString());
				ebook.setContents(params.get("contents").toString());
				ebook.setUrl(params.get("url").toString());
				ebook.setExposuredate(params.get("exposuredate").toString());
				
				result = service.insertEbook(ebook);
				
				if (result > 0) {
					msg = String.valueOf(result);
				} else {
					msg = "fail";
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return msg;
	}
	
	@RequestMapping(value = "json/updateEdbook", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateEbook(@RequestParam Map<String, Object> params,
			Model model,
			HttpServletRequest req, 
			HttpServletResponse res) {
		try {
			int cnt = service.updateEbook(params);
			params.put("STATUS", (cnt > 0) ? "SUCCESS" : "FAIL");
		} catch (Exception e) {
			
			params.put("STATUS", "FAIL");
			e.printStackTrace();
		}
		
		return params;
	}
	
	@RequestMapping(value = "json/deleteEdbook", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deleteEbook(@RequestParam Map<String, Object> params,
			Model model,
			HttpServletRequest req, 
			HttpServletResponse res) {
		try {
			String[] targets = params.get("code").toString().split(",");
			params.put("code", targets);
			int cnt = service.deleteEbook(params);
			
			params.put("STATUS", (cnt > 0) ? "SUCCESS" : "FAIL");
			params.put("SavedCount", cnt);
			params.put("RequestCount", targets.length);
		} catch (Exception e) {
			
			params.put("STATUS", "FAIL");
			e.printStackTrace();
		}
		
		return params;
	}
	
	
	@RequestMapping(value = "page/edbookContentAdd", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView edbookContentAdd(@RequestParam Map<String,Object> params
			,Model model
			,HttpServletRequest req
            ,HttpServletResponse res) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();

			if (null == params.get("type") || "".equals(params.get("type"))) {
				//map = service.getMaxEbookContentSeq(params);
			} else if ("edit".equals(params.get("type"))) {
				map = service.getEbookContentDetail(params);
			}
			
			map.put("type", params.get("type"));
			map.put("previewUrl", Const.PREVIEW_URL);
			model.addAttribute("chaptername", params.get("chaptername"));
			model.addAttribute("chapterSeq", params.get("chapterSeq"));
			model.addAttribute("data", map);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
			
			
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("page/edbookContentAdd");
		
		return modelAndView;
	}
	
	
	@RequestMapping(value = "page/edbookContentInsert", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public String edbookContentInsert(@RequestParam Map<String,Object> params
			,HttpServletRequest req
            ,HttpServletResponse res) {
		String msg = "";
		try {
			Map<String, Object> map = service.getMaxEbookContentOrder(params);
			params.put("contentOrder", map.get("contentOrder"));
			
			int result = 0;
			
			if (null != params.get("updateType") && "edit".equals(params.get("updateType"))) 
			{
				result = service.updateEbookContent(params);
			}
			else 
			{
				result = service.insertEbookContent(params);
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
	
	@RequestMapping(value = "json/updateEbookContentOrder", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateEbookContentOrder(@RequestParam Map<String, Object> params,
			Model model,
			HttpServletRequest req, 
			HttpServletResponse res) {
		try {
			String[] targets = params.get("code").toString().split(",");
			params.put("code", targets);
			int cnt = 0;
			
			for (int idx = 0; idx < targets.length; idx++) {
				params.put("contentSeq", targets[idx].split("\\|\\|")[0]);
				params.put("contentOrder", targets[idx].split("\\|\\|")[1]);
				cnt += service.updateEbookContentOrder(params);
			}
			
			params.put("STATUS", (cnt > 0) ? "SUCCESS" : "FAIL");
			params.put("SavedCount", cnt);
			params.put("RequestCount", targets.length);
			
			
		} catch (Exception e) {
			
			params.put("STATUS", "FAIL");
			e.printStackTrace();
		}
		
		return params;
	}
	
	@RequestMapping(value = "json/deleteEdbookContent", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deleteEdbookContent(@RequestParam Map<String, Object> params,
			Model model,
			HttpServletRequest req, 
			HttpServletResponse res) {
		try {
			String[] targets = params.get("code").toString().split(",");
			params.put("code", targets);
			int cnt = service.deleteEbookContent(params);
			
			params.put("STATUS", (cnt > 0) ? "SUCCESS" : "FAIL");
			params.put("SavedCount", cnt);
			params.put("RequestCount", targets.length);
		} catch (Exception e) {
			
			params.put("STATUS", "FAIL");
			e.printStackTrace();
		}
		
		return params;
	}
	
	@RequestMapping(value = "json/deleteEbookAllContentByChapterSeq", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deleteEbookAllContentByChapterSeq(@RequestParam Map<String, Object> params,
			Model model,
			HttpServletRequest req, 
			HttpServletResponse res) {
		try {
			String[] targets = params.get("code").toString().split(",");
			params.put("code", targets);
			int cnt = service.deleteEbookAllContentByChapterSeq(params);
			
			params.put("STATUS", (cnt > 0) ? "SUCCESS" : "FAIL");
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
