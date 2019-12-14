package com.fronteo.cms.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.fronteo.cms.dao.ContentsDao;

@Service
public class ContentsService {
	@Inject
	private ContentsDao dao;
	
	public Map<String, Object> getTotalContentsCount(Map<String, Object> map) throws Exception {
		return dao.getTotalContentsCount(map);
	}
	
	public List<Map<String, Object>> getContentsList(Map<String, Object> map) throws Exception {
		return dao.getContentsList(map);
	} 
	
	public Map<String, Object> getContentsCount(Map<String, Object> map) throws Exception {
		return dao.getContentsCount(map);
	}
	
	public Map<String, Object> getContentsDetail(Map<String, Object> map) throws Exception {
		return dao.getContentsDetail(map);
	}
	
	public Map<String, Object> getMaxContentsSeq(Map<String, Object> map) throws Exception {
		return dao.getMaxContentsSeq(map);
	}
	
	public int insertContents(Map<String, Object> map) throws Exception {
		return dao.insertContents(map);
	}
	
	public int updateContents(Map<String, Object> map) throws Exception {
		return dao.updateContents(map);
	}
	
	public int deleteContents(Map<String, Object> map) throws Exception {
		return dao.deleteContents(map);
	}
}
