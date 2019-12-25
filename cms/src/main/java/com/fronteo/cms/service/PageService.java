package com.fronteo.cms.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.fronteo.cms.dao.PageDao;
import com.fronteo.cms.dto.Ebook;

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
	
	public Map<String, Object> getTotalEbookCount(Map<String, Object> map) throws Exception {
		return dao.getTotalEbookCount(map);
	}
	
	public List<Map<String, Object>> getEbookList(Map<String, Object> map) throws Exception {
		return dao.getEbookList(map);
	} 
	
	public Map<String, Object> getEbookCount(Map<String, Object> map) throws Exception {
		return dao.getEbookCount(map);
	}
	
	public Map<String, Object> getEbookDetail(Map<String, Object> map) throws Exception {
		return dao.getEbookDetail(map);
	}
	
	public Map<String, Object> getMaxEbookSeq(Map<String, Object> map) throws Exception {
		return dao.getMaxEbookSeq(map);
	}
	
	public int insertEbook(Ebook ebook) throws Exception {
		return dao.insertEbook(ebook);
	}
	
	public int updateEbook(Map<String, Object> map) throws Exception {
		return dao.updateEbook(map);
	}
	
	public int deleteEbook(Map<String, Object> map) throws Exception {
		return dao.deleteEbook(map);
	}
	
	public List<Map<String, Object>> getEbookContentList(Map<String, Object> map) throws Exception {
		return dao.getEbookContentList(map);
	} 
	
	public Map<String, Object> getEbookContentDetail(Map<String, Object> map) throws Exception {
		return dao.getEbookContentDetail(map);
	}
	
	public Map<String, Object> getMaxEbookContentOrder(Map<String, Object> map) throws Exception {
		return dao.getMaxEbookContentOrder(map);
	}
	
	public int insertEbookContent(Map<String, Object> map) throws Exception {
		return dao.insertEbookContent(map);
	}
	
	public int updateEbookContent(Map<String, Object> map) throws Exception {
		return dao.updateEbookContent(map);
	}
	
	public int deleteEbookContent(Map<String, Object> map) throws Exception {
		return dao.deleteEbookContent(map);
	}
	
	public int deleteEbookAllContentByChapterSeq(Map<String, Object> map) throws Exception {
		return dao.deleteEbookAllContentByChapterSeq(map);
	}
	
	public int updateEbookContentOrder(Map<String, Object> map) throws Exception {
		return dao.updateEbookContentOrder(map);
	}
}
