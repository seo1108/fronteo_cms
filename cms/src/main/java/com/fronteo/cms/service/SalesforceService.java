package com.fronteo.cms.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.fronteo.cms.dao.SalesforceDao;

@Service
public class SalesforceService {
	@Inject
	private SalesforceDao dao;
	
	public Map<String, Object> getTotalSalesforceCount(Map<String, Object> map) throws Exception {
		return dao.getTotalSalesforceCount(map);
	}
	
	public List<Map<String, Object>> getSalesforceList(Map<String, Object> map) throws Exception {
		return dao.getSalesforceList(map);
	} 
	
	public Map<String, Object> getSalesforceCount(Map<String, Object> map) throws Exception {
		return dao.getSalesforceCount(map);
	}
	
	public Map<String, Object> getSalesforceDetail(Map<String, Object> map) throws Exception {
		return dao.getSalesforceDetail(map);
	}
}
