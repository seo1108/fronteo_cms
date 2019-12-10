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
public class BoardDao {
	@Autowired 
	@Resource(name="sqlSession")
	private SqlSession sqlSession;
	
	private static String namespace = "board";
	
	public Map<String, Object> getTotalBbsCount(Map<String, Object> map) throws Exception {
		Map<String, Object> rmap = sqlSession.selectOne(namespace + ".getTotalBbsCount", map);
		return rmap;
	}
	
	public List<Map<String, Object>> getBbsList(Map<String, Object> map) throws Exception {
		List<Map<String, Object>> list = sqlSession.selectList(namespace + ".getBbsList", map);
		return list;
	}
	
	public Map<String, Object> getBbsCount(Map<String, Object> map) throws Exception {
		Map<String, Object> rmap = sqlSession.selectOne(namespace + ".getBbsCount", map);
		return rmap;
	}
	
	public int insertBbs(Map<String, Object> map) throws Exception {
		int cnt = sqlSession.update(namespace + ".insertBbs", map);
		return cnt;
	}
}
