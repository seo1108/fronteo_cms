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
public class ContentsDao {
	@Autowired 
	@Resource(name="sqlSession")
	private SqlSession sqlSession;
	
	private static String namespace = "contents";
	
	public Map<String, Object> getTotalContentsCount(Map<String, Object> map) throws Exception {
		Map<String, Object> rmap = sqlSession.selectOne(namespace + ".getTotalContentsCount", map);
		return rmap;
	}
	
	public List<Map<String, Object>> getContentsList(Map<String, Object> map) throws Exception {
		List<Map<String, Object>> list = sqlSession.selectList(namespace + ".getContentsList", map);
		return list;
	}
	
	public Map<String, Object> getContentsCount(Map<String, Object> map) throws Exception {
		Map<String, Object> rmap = sqlSession.selectOne(namespace + ".getContentsCount", map);
		return rmap;
	}
	
	public Map<String, Object> getContentsDetail(Map<String, Object> map) throws Exception {
		Map<String, Object> rmap = sqlSession.selectOne(namespace + ".getContentsDetail", map);
		return rmap;
	}
	
	public Map<String, Object> getMaxContentsSeq(Map<String, Object> map) throws Exception {
		Map<String, Object> rmap = sqlSession.selectOne(namespace + ".getMaxContentsSeq", map);
		return rmap;
	}
	
	public int insertContents(Map<String, Object> map) throws Exception {
		int cnt = sqlSession.update(namespace + ".insertContents", map);
		return cnt;
	}
	
	public int updateContents(Map<String, Object> map) throws Exception {
		int cnt = sqlSession.update(namespace + ".updateContents", map);
		return cnt;
	}
	
	public int deleteContents(Map<String, Object> map) throws Exception {
		int cnt = sqlSession.update(namespace + ".deleteContents", map);
		return cnt;
	}
}
