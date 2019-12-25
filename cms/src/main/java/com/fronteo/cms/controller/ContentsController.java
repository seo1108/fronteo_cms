package com.fronteo.cms.controller;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
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
import com.fronteo.cms.service.ContentsService;

@Controller
public class ContentsController {
	@Inject
	private ContentsService service;
	
	@RequestMapping(value = "contents/brochure", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView brochure(@RequestParam Map<String,Object> params
			,Model model
			,HttpServletRequest req
            ,HttpServletResponse res) {
		try {
			params.put("contentType", "C");
			Map<String, Object> totalmap = service.getTotalContentsCount(params);
			long totalCount = Long.parseLong(totalmap.get("cnt").toString());
			model.addAttribute("rowCount", Const.DEFAULT_RESULT_COUNT);
			model.addAttribute("totalCnt", totalCount);
			model.addAttribute("rows", Const.ARR_RESULT_COUNT);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
			
			
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("contents/brochure");
		
		return modelAndView;
	}
	
	@RequestMapping(value = "contents/brochureList", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView brochureList(@RequestParam Map<String,Object> params, 
			Model model
			,HttpServletRequest req
            ,HttpServletResponse res) {
		try {
			params.put("contentType", "C");
			int rowCount = Integer.parseInt(params.get("rowCount").toString());
			int startIdx = 0;
			int page = Integer.parseInt(Util.checkNull(params.get("page"), "1"));
			
			Map<String, Object> map = service.getContentsCount(params);
			
			long totalCount = Long.parseLong(map.get("cnt").toString());
			int totalPage = (int) Math.ceil((double) totalCount / rowCount);
			startIdx = (page - 1) * rowCount;
			
			params.put("reqType", "view");
			params.put("startIdx", startIdx);
			params.put("rowCount", rowCount);
			
			List<Map<String, Object>> list = service.getContentsList(params);
			
			for (Map<String,Object> rmap:list) {
				String filename = rmap.get("filePath").toString().substring(rmap.get("filePath").toString().lastIndexOf("/")+1);
				rmap.put("filename", filename);
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
		modelAndView.setViewName("contents/brochureList");
		
		return modelAndView;
	}
	
	
	@RequestMapping(value = "contents/brochureAdd", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView brochureAdd(@RequestParam Map<String,Object> params
			,Model model
			,HttpServletRequest req
            ,HttpServletResponse res) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();

			if (null == params.get("type") || "".equals(params.get("type"))) {
				map = service.getMaxContentsSeq(params);
			} else if ("edit".equals(params.get("type"))) {
				map = service.getContentsDetail(params);
			}
			
			map.put("type", params.get("type"));
			model.addAttribute("data", map);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
			
			
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("contents/brochureAdd");
		
		return modelAndView;
	}
	
	@RequestMapping(value = "contents/briefs", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView briefs(@RequestParam Map<String,Object> params
			,Model model
			,HttpServletRequest req
            ,HttpServletResponse res) {
		try {
			params.put("contentType", "P");
			Map<String, Object> totalmap = service.getTotalContentsCount(params);
			long totalCount = Long.parseLong(totalmap.get("cnt").toString());
			model.addAttribute("rowCount", Const.DEFAULT_RESULT_COUNT);
			model.addAttribute("totalCnt", totalCount);
			model.addAttribute("rows", Const.ARR_RESULT_COUNT);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
			
			
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("contents/briefs");
		
		return modelAndView;
	}
	
	@RequestMapping(value = "contents/briefsList", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView briefsList(@RequestParam Map<String,Object> params, 
			Model model
			,HttpServletRequest req
            ,HttpServletResponse res) {
		try {
			params.put("contentType", "P");
			int rowCount = Integer.parseInt(params.get("rowCount").toString());
			int startIdx = 0;
			int page = Integer.parseInt(Util.checkNull(params.get("page"), "1"));
			
			Map<String, Object> map = service.getContentsCount(params);
			
			long totalCount = Long.parseLong(map.get("cnt").toString());
			int totalPage = (int) Math.ceil((double) totalCount / rowCount);
			startIdx = (page - 1) * rowCount;
			
			params.put("reqType", "view");
			params.put("startIdx", startIdx);
			params.put("rowCount", rowCount);
			
			List<Map<String, Object>> list = service.getContentsList(params);
			
			for (Map<String,Object> rmap:list) {
				String filename = rmap.get("filePath").toString().substring(rmap.get("filePath").toString().lastIndexOf("/")+1);
				rmap.put("filename", filename);
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
		modelAndView.setViewName("contents/briefsList");
		
		return modelAndView;
	}
	
	
	@RequestMapping(value = "contents/briefsAdd", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView briefsAdd(@RequestParam Map<String,Object> params
			,Model model
			,HttpServletRequest req
            ,HttpServletResponse res) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();

			if (null == params.get("type") || "".equals(params.get("type"))) {
				map = service.getMaxContentsSeq(params);
			} else if ("edit".equals(params.get("type"))) {
				map = service.getContentsDetail(params);
			}
			
			map.put("type", params.get("type"));
			model.addAttribute("data", map);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
			
			
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("contents/briefsAdd");
		
		return modelAndView;
	}
	
	@RequestMapping(value = "contents/video", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView video(@RequestParam Map<String,Object> params
			,Model model
			,HttpServletRequest req
            ,HttpServletResponse res) {
		try {
			params.put("contentType", "V");
			Map<String, Object> totalmap = service.getTotalContentsCount(params);
			long totalCount = Long.parseLong(totalmap.get("cnt").toString());
			model.addAttribute("rowCount", Const.DEFAULT_RESULT_COUNT);
			model.addAttribute("totalCnt", totalCount);
			model.addAttribute("rows", Const.ARR_RESULT_COUNT);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
			
			
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("contents/video");
		
		return modelAndView;
	}
	
	@RequestMapping(value = "contents/videoList", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView videoList(@RequestParam Map<String,Object> params, 
			Model model
			,HttpServletRequest req
            ,HttpServletResponse res) {
		try {
			params.put("contentType", "V");
			int rowCount = Integer.parseInt(params.get("rowCount").toString());
			int startIdx = 0;
			int page = Integer.parseInt(Util.checkNull(params.get("page"), "1"));
			
			Map<String, Object> map = service.getContentsCount(params);
			
			long totalCount = Long.parseLong(map.get("cnt").toString());
			int totalPage = (int) Math.ceil((double) totalCount / rowCount);
			startIdx = (page - 1) * rowCount;
			
			params.put("reqType", "view");
			params.put("startIdx", startIdx);
			params.put("rowCount", rowCount);
			
			List<Map<String, Object>> list = service.getContentsList(params);
			
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
		modelAndView.setViewName("contents/videoList");
		
		return modelAndView;
	}
	
	
	@RequestMapping(value = "contents/videoAdd", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView videoAdd(@RequestParam Map<String,Object> params
			,Model model
			,HttpServletRequest req
            ,HttpServletResponse res) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();

			if (null == params.get("type") || "".equals(params.get("type"))) {
				map = service.getMaxContentsSeq(params);
			} else if ("edit".equals(params.get("type"))) {
				map = service.getContentsDetail(params);
			}
			
			map.put("type", params.get("type"));
			model.addAttribute("data", map);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
			
			
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("contents/videoAdd");
		
		return modelAndView;
	}
	
	@RequestMapping(value = "contents/ebook", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView ebook(@RequestParam Map<String,Object> params
			,Model model
			,HttpServletRequest req
            ,HttpServletResponse res) {
		try {
			params.put("contentType", "E");
			Map<String, Object> totalmap = service.getTotalContentsCount(params);
			long totalCount = Long.parseLong(totalmap.get("cnt").toString());
			model.addAttribute("rowCount", Const.DEFAULT_RESULT_COUNT);
			model.addAttribute("totalCnt", totalCount);
			model.addAttribute("rows", Const.ARR_RESULT_COUNT);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
			
			
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("contents/ebook");
		
		return modelAndView;
	}
	
	@RequestMapping(value = "contents/ebookList", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView ebookList(@RequestParam Map<String,Object> params, 
			Model model
			,HttpServletRequest req
            ,HttpServletResponse res) {
		try {
			params.put("contentType", "E");
			int rowCount = Integer.parseInt(params.get("rowCount").toString());
			int startIdx = 0;
			int page = Integer.parseInt(Util.checkNull(params.get("page"), "1"));
			
			Map<String, Object> map = service.getContentsCount(params);
			
			long totalCount = Long.parseLong(map.get("cnt").toString());
			int totalPage = (int) Math.ceil((double) totalCount / rowCount);
			startIdx = (page - 1) * rowCount;
			
			params.put("reqType", "view");
			params.put("startIdx", startIdx);
			params.put("rowCount", rowCount);
			
			List<Map<String, Object>> list = service.getContentsList(params);
			
			for (Map<String,Object> rmap:list) {
				if (null == rmap.get("filePath") || "".equals(rmap.get("filePath"))) {
					rmap.put("filename", "");
				} else {
					String filename = rmap.get("filePath").toString().substring(rmap.get("filePath").toString().lastIndexOf("/")+1);
					rmap.put("filename", filename);
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
		modelAndView.setViewName("contents/ebookList");
		
		return modelAndView;
	}
	
	
	@RequestMapping(value = "contents/ebookAdd", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView ebookAdd(@RequestParam Map<String,Object> params
			,Model model
			,HttpServletRequest req
            ,HttpServletResponse res) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();

			if (null == params.get("type") || "".equals(params.get("type"))) {
				map = service.getMaxContentsSeq(params);
			} else if ("edit".equals(params.get("type"))) {
				map = service.getContentsDetail(params);
			}
			
			map.put("type", params.get("type"));
			model.addAttribute("data", map);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
			
			
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("contents/ebookAdd");
		
		return modelAndView;
	}
	
	@RequestMapping(value = "contents/whitepaper", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView whitepaper(@RequestParam Map<String,Object> params
			,Model model
			,HttpServletRequest req
            ,HttpServletResponse res) {
		try {
			params.put("contentType", "W");
			Map<String, Object> totalmap = service.getTotalContentsCount(params);
			long totalCount = Long.parseLong(totalmap.get("cnt").toString());
			model.addAttribute("rowCount", Const.DEFAULT_RESULT_COUNT);
			model.addAttribute("totalCnt", totalCount);
			model.addAttribute("rows", Const.ARR_RESULT_COUNT);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
			
			
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("contents/whitepaper");
		
		return modelAndView;
	}
	
	@RequestMapping(value = "contents/whitepaperList", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView whitepaperList(@RequestParam Map<String,Object> params, 
			Model model
			,HttpServletRequest req
            ,HttpServletResponse res) {
		try {
			params.put("contentType", "W");
			int rowCount = Integer.parseInt(params.get("rowCount").toString());
			int startIdx = 0;
			int page = Integer.parseInt(Util.checkNull(params.get("page"), "1"));
			
			Map<String, Object> map = service.getContentsCount(params);
			
			long totalCount = Long.parseLong(map.get("cnt").toString());
			int totalPage = (int) Math.ceil((double) totalCount / rowCount);
			startIdx = (page - 1) * rowCount;
			
			params.put("reqType", "view");
			params.put("startIdx", startIdx);
			params.put("rowCount", rowCount);
			
			List<Map<String, Object>> list = service.getContentsList(params);
			
			for (Map<String,Object> rmap:list) {
				if (null == rmap.get("filePath") || "".equals(rmap.get("filePath"))) {
					rmap.put("filename", "");
				} else {
					String filename = rmap.get("filePath").toString().substring(rmap.get("filePath").toString().lastIndexOf("/")+1);
					rmap.put("filename", filename);
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
		modelAndView.setViewName("contents/whitepaperList");
		
		return modelAndView;
	}
	
	
	@RequestMapping(value = "contents/whitepaperAdd", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView whitepaperAdd(@RequestParam Map<String,Object> params
			,Model model
			,HttpServletRequest req
            ,HttpServletResponse res) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();

			if (null == params.get("type") || "".equals(params.get("type"))) {
				map = service.getMaxContentsSeq(params);
			} else if ("edit".equals(params.get("type"))) {
				map = service.getContentsDetail(params);
			}
			
			map.put("type", params.get("type"));
			model.addAttribute("data", map);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
			
			
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("contents/whitepaperAdd");
		
		return modelAndView;
	}
	
	@RequestMapping(path = "contents/contentsInsert", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public String contentsInsert(@RequestParam Map<String,Object> params
			,HttpServletRequest req
            ,HttpServletResponse res) {
		String msg = "";
		try {
			if (null != params.get("contentType") && !"V".equals(params.get("contentType"))) {
				String filepath = ContentFileUpload(req, res);
				
				String[] s_file = filepath.split("#@#");
				
				params.put("filePath", s_file[0]);
				if (s_file.length > 1) {
					params.put("thumbUrl", Const.CONTENTS_SERVER_PATH+s_file[1]);
				}
			} else {
//				String filepath = ContentFileUpload(req, res);
//				if ("".equals(filepath)) {
//					msg = "fail";
//					return msg;
//				}
			}
			
			
			int result = 0;
			
			if (null != params.get("updateType") && "edit".equals(params.get("updateType"))) 
			{
				result = service.updateContents(params);
			}
			else 
			{
				result = service.insertContents(params);
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
	
	
	@RequestMapping(value = "json/deleteContents", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deleteContents(@RequestParam Map<String, Object> params,
			Model model,
			HttpServletRequest req, 
			HttpServletResponse res) {
		try {
			String[] targets = params.get("code").toString().split(",");
			params.put("code", targets);
			int cnt = service.deleteContents(params);
			
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
			String path = Const.CONTENTS_FILE_UPLOAD_PATH;
			
			String filename = "";
			
			MultipartHttpServletRequest mhsr = (MultipartHttpServletRequest) req; 
			Iterator iter = mhsr.getFileNames(); 
			MultipartFile mfile = null; 
			String fieldName = ""; 
			String origName = ""; 
			String thumb = "";
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
				
				System.out.println("_________________________________________________________" + saveFileName.substring(saveFileName.lastIndexOf(".")));
				
				if ("pdf".equals(saveFileName.substring(saveFileName.lastIndexOf(".")+1)) ||
						"PDF".equals(saveFileName.substring(saveFileName.lastIndexOf(".")+1))) {
					thumb = pdfbox_thumbnail(serverFile);
			    }
			    
			}
			
			if (!"".equals(filename)) {
				if (!"".equals(thumb)) {
					filepath = Const.CONTENTS_SERVER_PATH+filename + "#@#" + thumb;
					System.out.println("_____________________" + filepath + "\n" + "__________________"  + thumb);
				} else {
					filepath = Const.CONTENTS_SERVER_PATH+filename;
				}
			}
			
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return filepath;
	}
	
	public String pdfbox_thumbnail(File file) {
		String thumbnailFile = "";
		try {
			PDDocument document = PDDocument.load( file );
			PDFRenderer pdfRenderer = new PDFRenderer( document );
	
			System.out.println( "전체페이지 수 : " + document.getNumberOfPages() );
			BufferedImage bim = pdfRenderer.renderImageWithDPI( 0, 100, ImageType.RGB );
	
			// suffix in filename will be used as the file format
			//ImageIO.writeImage( bim, pdfFilename + ".png", 100 );
			//File imageFile = new File(file.toString().substring(0, file.toString().lastIndexOf(".")) + ".png");
			File imageFile = changeExtension(file, ".png");
			ImageIO.write(bim, "png", imageFile);
			
			System.out.println("__________________________" + imageFile);
			
			document.close();
			
			thumbnailFile = imageFile.getName();
		} catch (Exception ex) {
			ex.printStackTrace();
		} 
		
		return thumbnailFile;
	}
	
	public File changeExtension(File f, String newExtension) {
		int i = f.getName().lastIndexOf('.');
		String name = f.getName().substring(0,i);
		return new File(f.getParent() + "/" + name + newExtension);
	}
	
	public File multipartToFile(MultipartFile multipart) throws IllegalStateException, IOException { 
		File convFile = new File( multipart.getOriginalFilename()); 
		multipart.transferTo(convFile); 
		System.out.println("__________________________" + convFile);
		return convFile; 
	}

}
