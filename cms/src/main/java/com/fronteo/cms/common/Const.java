package com.fronteo.cms.common;

public class Const {
	public static final int		DEFAULT_RESULT_COUNT	= 10;
	public static final int[]	ARR_RESULT_COUNT 		= new int[] {10, 20, 50, 100};
	
	// 테스트
/*	
 	public static final String 	UPLOAD_PATH					= "D:\\\\FRONTEO\\\\";
	public static final String	PREVIEW_URL					= "http://localhost:8080/resources/ediscovery/preview";
	
	public static final String	BBS_FILE_UPLOAD_PATH		= UPLOAD_PATH + "upload\\\\files\\\\";
	public static final String  BBS_SERVER_PATH				= "/upload/files/";
	public static final String	CONTENTS_FILE_UPLOAD_PATH	= UPLOAD_PATH + "upload\\\\pdf\\\\";
	public static final String  CONTENTS_SERVER_PATH		= "/upload/pdf/";
	public static final String	BANNER_FILE_UPLOAD_PATH		= UPLOAD_PATH + "upload\\\\image\\\\";
	public static final String  BANNER_SERVER_PATH			= "/upload/image/";
*/	
	
	
	// 가동
	public static final String UPLOAD_PATH					= "/";
	public static final String PREVIEW_URL					= "http://ec2-13-124-226-238.ap-northeast-2.compute.amazonaws.com/resources/ediscovery/preview";
	
	public static final String	BBS_FILE_UPLOAD_PATH		= UPLOAD_PATH + "upload/files/";
	public static final String  BBS_SERVER_PATH				= "/upload/files/";
	public static final String	CONTENTS_FILE_UPLOAD_PATH	= UPLOAD_PATH + "upload/pdf/";
	public static final String  CONTENTS_SERVER_PATH		= "/upload/pdf/";
	public static final String	BANNER_FILE_UPLOAD_PATH		= UPLOAD_PATH + "upload/image/";
	public static final String  BANNER_SERVER_PATH			= "/upload/image/";
	
}
