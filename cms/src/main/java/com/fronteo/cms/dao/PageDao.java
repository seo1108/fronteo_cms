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
public class PageDao {
	@Autowired 
	@Resource(name="sqlSession")
	private SqlSession sqlSession;
	
	private static String namespace = "page";
	
	public Map<String, Object> getTotalBannerCount(Map<String, Object> map) throws Exception {
		Map<String, Object> rmap = sqlSession.selectOne(namespace + ".getTotalBannerCount", map);
		return rmap;
	}
	
	public List<Map<String, Object>> getBannerList(Map<String, Object> map) throws Exception {
		List<Map<String, Object>> list = sqlSession.selectList(namespace + ".getBannerList", map);
		return list;
	}
	
	public Map<String, Object> getBannerCount(Map<String, Object> map) throws Exception {
		Map<String, Object> rmap = sqlSession.selectOne(namespace + ".getBannerCount", map);
		return rmap;
	}
	
	public Map<String, Object> getBannerDetail(Map<String, Object> map) throws Exception {
		Map<String, Object> rmap = sqlSession.selectOne(namespace + ".getBannerDetail", map);
		return rmap;
	}
	
	public int insertBanner(Map<String, Object> map) throws Exception {
		int cnt = sqlSession.update(namespace + ".insertBanner", map);
		return cnt;
	}
	
	public int updateBannerExposureNtoAll(Map<String, Object> map) throws Exception {
		int cnt = sqlSession.update(namespace + ".updateBannerExposureNtoAll", map);
		return cnt;
	}
	
	public int updateBannerExposureY(Map<String, Object> map) throws Exception {
		int cnt = sqlSession.update(namespace + ".updateBannerExposureY", map);
		return cnt;
	}
	
	public int deleteBanner(Map<String, Object> map) throws Exception {
		int cnt = sqlSession.update(namespace + ".deleteBanner", map);
		return cnt;
	}
}
