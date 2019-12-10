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
//			if (null != params.get("filePath")) {
//				String filepath = ContentFileUpload(req, res);
//				params.put("filePath", filepath);
//			}
			
			String filepath = ContentFileUpload(req, res);
			params.put("filePath", filepath);
			
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
	
	@ResponseBody
	public String ContentFileUpload(HttpServletRequest req, HttpServletResponse res) {
		String filepath = "";
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		try { 
			String path = Const.BBS_FILE_UPLOAD_PATH;
			
			/*String subpath = "//uploads//banner//";
			String path = servletContext.getRealPath("/") + subpath;*/
			System.out.println("_____________" + path);
			String filename = "";
			
			MultipartHttpServletRequest mhsr = (MultipartHttpServletRequest) req; 
			Iterator iter = mhsr.getFileNames(); 
			//Iterator iter = mhsr.getFileNames(); 
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
				//String saveFileName = origName;
				String extension = "";
				int lastIndexOf = origName.lastIndexOf(".");
			    if (lastIndexOf == -1) {
			        
			    }
			    extension = origName.substring(lastIndexOf);
			    
				String saveFileName = "bbs_" + timestamp.getTime() + extension;
				filename = saveFileName;
				
				File serverFile = new File(path + File.separator + saveFileName); 
				mfile.transferTo(serverFile); 
				Map file = new HashMap(); 
				file.put("origName", origName); 
				file.put("sfile", serverFile); 
				resultList.add(file); 
			}
			
			filepath = Const.BBS_SERVER_PATH+filename;
			
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return filepath;
		/*String return1="";
		String return2="";
		String return3="";
		String name = "";
		
		try {
			if (ServletFileUpload.isMultipartContent(request)){
				ServletFileUpload uploadHandler = new ServletFileUpload(new DiskFileItemFactory());
			    uploadHandler.setHeaderEncoding("UTF-8");
			    List<FileItem> items = uploadHandler.parseRequest(request);
			    for (FileItem item : items) {
			        if(item.getFieldName().equals("callback")) {
			            return1 = item.getString("UTF-8");
			        } else if(item.getFieldName().equals("callback_func")) {
			            return2 = "?callback_func="+item.getString("UTF-8");
			        } else if(item.getFieldName().equals("Filedata")) {
			            if(item.getSize() > 0) {
			      
			                name = item.getName().substring(item.getName().lastIndexOf(File.separator)+1);
			                String filename_ext = name.substring(name.lastIndexOf(".")+1);
			                filename_ext = filename_ext.toLowerCase();
//			                String[] allow_file = {"jpg","png","bmp","gif"};
//			                int cnt = 0;
//			                for(int i=0; i<allow_file.length; i++) {
//			                    if(filename_ext.equals(allow_file[i])){
//			                        cnt++;
//			                    }
//			                }
//			                if(cnt == 0) {
//			                    return3 = "&errstr="+name;
//			                } else {
			                     
			                    //파일 기본경로
			                    String dftFilePath = request.getSession().getServletContext().getRealPath("/");
			                    //파일 기본경로 _ 상세경로
			                    String filePath = dftFilePath + "resources" + File.separator + "attaches" + File.separator;
			        			//String filePath = "/data/cms_resources/smarteditor/photo_uploader/uploadImg/";
			                     
			                    File file = null;
			                    file = new File(filePath);
			                    if(!file.exists()) {
			                        file.mkdirs();
			                    }
			                     
			                    String realFileNm = "";
			                    SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
			                    String today= formatter.format(new java.util.Date());
			                    realFileNm = today+UUID.randomUUID().toString() + name.substring(name.lastIndexOf("."));
			                     
			                    String rlFileNm = filePath + realFileNm;
			                    ///////////////// 서버에 파일쓰기 ///////////////// 
			                    InputStream is = item.getInputStream();
			                    OutputStream os=new FileOutputStream(rlFileNm);
			                    int numRead;
			                    byte b[] = new byte[(int)item.getSize()];
			                    while((numRead = is.read(b,0,b.length)) != -1){
			                        os.write(b,0,numRead);
			                    }
			                    if(is != null) {
			                        is.close();
			                    }
			                    os.flush();
			                    os.close();
			                    ///////////////// 서버에 파일쓰기 /////////////////
			                     
			                    return3 += "&bNewLine=true";
			                                // img 태그의 title 옵션에 들어갈 원본파일명
			                    return3 += "&sFileName="+ name;
			                    //return3 += "&sFileURL=/resources/smarteditor/photo_uploader/uploadImg/"+realFileNm;
			                    //return3 += "&sFileURL=/aimg/smarteditor/photo_uploader/uploadImg/"+realFileNm;
			                    return3 += "&sFileURL=/resources/attaches/"+realFileNm;
			                }
			            }else {
			                  return3 += "&errstr=error";
			            }
//			        }
			    }
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return return1+return2+return3;*/
	}
	
//	@SuppressWarnings("unchecked")
//	@RequestMapping(value = "banner/contentFileUpload") 
//	@ResponseBody
//	public Map<String, Object> ContentFileUpload2(HttpServletRequest req, HttpServletResponse rep) {
//		Map returnObject = new HashMap();
//		Map<String, Object> rmap = new HashMap<String, Object>();
//		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//		try { 
//			
//
//			String dftFilePath = req.getSession().getServletContext().getRealPath("/");
//            //파일 기본경로 _ 상세경로
//            String path = dftFilePath + "resources" + File.separator + "attaches" + File.separator;
//			
//			/*String subpath = "//uploads//banner//";
//			String path = servletContext.getRealPath("/") + subpath;*/
//			System.out.println("_____________" + path);
//			String filename = "";
//			
//			MultipartHttpServletRequest mhsr = (MultipartHttpServletRequest) req; 
//			Iterator iter = mhsr.getFileNames(); 
//			//Iterator iter = mhsr.getFileNames(); 
//			MultipartFile mfile = null; 
//			String fieldName = ""; 
//			String origName = ""; 
//			List resultList = new ArrayList(); 
//			
//			File dir = new File(path); 
//			if (!dir.isDirectory()) { 
//				dir.mkdirs(); 
//			}  
//			while (iter.hasNext()) { 
//				fieldName = (String) iter.next(); 
//				mfile = mhsr.getFile(fieldName); 
//				
//				origName = new String(mfile.getOriginalFilename().getBytes(), "UTF-8"); 
//				
//				if ("".equals(origName)) { continue; } 
//				//String saveFileName = origName;
//				String extension = "";
//				int lastIndexOf = origName.lastIndexOf(".");
//			    if (lastIndexOf == -1) {
//			        
//			    }
//			    extension = origName.substring(lastIndexOf);
//			    
//				String saveFileName = "banner_" + timestamp.getTime() + extension;
//				filename = saveFileName;
//				
//				File serverFile = new File(path + File.separator + saveFileName); 
//				mfile.transferTo(serverFile); 
//				Map file = new HashMap(); 
//				file.put("origName", origName); 
//				file.put("sfile", serverFile); 
//				resultList.add(file); 
//			} 
//			
//			returnObject.put("files", resultList); 
//			returnObject.put("params", mhsr.getParameterMap()); 
//			
//			System.out.println("______________________________" + Const.IMAGE_SERVER_PATH+filename);
//			
//			returnObject.put("imgsrc", ".." + Const.IMAGE_SERVER_PATH+filename);
//		
//			returnObject.put("STATUS", "SUCCESS");
//			
//		} catch (Exception ex) {
//			ex.printStackTrace();
//			returnObject.put("STATUS", "FAIL");
//		}
//		
//		return returnObject;
//	}
}
