<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<%@page import="java.io.FileOutputStream"%>
<%@page import="java.io.OutputStream"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.util.UUID"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.io.File"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="org.apache.commons.fileupload.FileItem"%>
<%@page import="java.util.List"%>
<%@page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<%@page import="org.springframework.web.multipart.MultipartHttpServletRequest"%>
<%@page import="java.util.Iterator"%>
<%@page import="org.springframework.web.multipart.MultipartFile"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="com.fronteo.cms.common.Const"%>
 
<%
String return1="";
String return2="";
String return3="";
String name = "";
String filepath = "";
 
if (ServletFileUpload.isMultipartContent(request)){
	Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	
/* 	try { 
		String path = Const.BANNER_FILE_UPLOAD_PATH;
		
		String filename = "";
		
		MultipartHttpServletRequest mhsr = (MultipartHttpServletRequest) request; 
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
	 */
	
	
	
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
                String[] allow_file = {"jpg","png","bmp","gif"};
                int cnt = 0;
                for(int i=0; i<allow_file.length; i++) {
                    if(filename_ext.equals(allow_file[i])){
                        cnt++;
                    }
                }
                if(cnt == 0) {
                    return3 = "&errstr="+name;
                } else {
                     
                    //파일 기본경로
                    String dftFilePath = request.getSession().getServletContext().getRealPath("/");
                    //파일 기본경로 _ 상세경로
                    //String filePath = dftFilePath + "resources" + File.separator + "uploadimages" + File.separator;
                    String filePath = Const.BANNER_FILE_UPLOAD_PATH;
        			//String filePath = "/data/cms_resources/smarteditor/photo_uploader/uploadImg/";
                     
                    File file = null;
                    file = new File(filePath);
                    if(!file.exists()) {
                        file.mkdirs();
                    }
                     
                    String realFileNm = "";
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
                    String today= formatter.format(new java.util.Date());
                    name = name.replace("[", "").replace("]", "");
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
                    return3 += "&sFileURL="+Const.BANNER_SERVER_PATH+realFileNm;
                }
            }else {
                  return3 += "&errstr=error";
            }
        }
    } 
}
response.sendRedirect(request.getContextPath()+return1+return2+return3);
 
%>
