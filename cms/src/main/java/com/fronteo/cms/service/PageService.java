package com.fronteo.cms.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.fronteo.cms.dao.PageDao;

@Service
public class PageService {
	@Inject
	private PageDao dao;
	
	public Map<String, Object> getTotalBannerCount(Map<String, Object> map) throws Exception {
		return dao.getTotalBannerCount(map);
	}
	
	public List<Map<String, Object>> getBannerList(Map<String, Object> map) throws Exception {
		return dao.getBannerList(map);
	} 
	
	public Map<String, Object> getBannerCount(Map<String, Object> map) throws Exception {
		return dao.getBannerCount(map);
	}
	
	public Map<String, Object> getBannerDetail(Map<String, Object> map) throws Exception {
		return dao.getBannerDetail(map);
	}
	
	public int insertBanner(Map<String, Object> map) throws Exception {
		return dao.insertBanner(map);
	}
	
	public int updateBannerExposure(Map<String, Object> map) throws Exception {
		int result =  0;
		result = dao.updateBannerExposureNtoAll(map);
		result = dao.updateBannerExposureY(map);
		return result;
	}
	
	public int deleteBanner(Map<String, Object> map) throws Exception {
		return dao.deleteBanner(map);
	}
}
