package com.fronteo.cms.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.fronteo.cms.dao.BoardDao;

@Service
public class BoardService {
	@Inject
	private BoardDao dao;
	
	public Map<String, Object> getTotalBbsCount(Map<String, Object> map) throws Exception {
		return dao.getTotalBbsCount(map);
	}
	
	public List<Map<String, Object>> getBbsList(Map<String, Object> map) throws Exception {
		return dao.getBbsList(map);
	} 
	
	public Map<String, Object> getBbsCount(Map<String, Object> map) throws Exception {
		return dao.getBbsCount(map);
	}
	
	public Map<String, Object> getBbsDetail(Map<String, Object> map) throws Exception {
		return dao.getBbsDetail(map);
	}
	
	public Map<String, Object> getMaxBbsSeq(Map<String, Object> map) throws Exception {
		return dao.getMaxBbsSeq(map);
	}
	
	public int insertBbs(Map<String, Object> map) throws Exception {
		return dao.insertBbs(map);
	}
	
	public int updateBbs(Map<String, Object> map) throws Exception {
		return dao.updateBbs(map);
	}
	
	public int deleteBbs(Map<String, Object> map) throws Exception {
		return dao.deleteBbs(map);
	}
}
