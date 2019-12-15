package com.fronteo.cms.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public class SalesforceDao {
	@Autowired 
	@Resource(name="sqlSession")
	private SqlSession sqlSession;
	
	private static String namespace = "salesforce";
	
	public Map<String, Object> getTotalSalesforceCount(Map<String, Object> map) throws Exception {
		Map<String, Object> rmap = sqlSession.selectOne(namespace + ".getTotalSalesforceCount", map);
		return rmap;
	}
	
	public List<Map<String, Object>> getSalesforceList(Map<String, Object> map) throws Exception {
		List<Map<String, Object>> list = sqlSession.selectList(namespace + ".getSalesforceList", map);
		return list;
	}
	
	public Map<String, Object> getSalesforceCount(Map<String, Object> map) throws Exception {
		Map<String, Object> rmap = sqlSession.selectOne(namespace + ".getSalesforceCount", map);
		return rmap;
	}
	
	public Map<String, Object> getSalesforceDetail(Map<String, Object> map) throws Exception {
		Map<String, Object> rmap = sqlSession.selectOne(namespace + ".getSalesforceDetail", map);
		return rmap;
	}
}
