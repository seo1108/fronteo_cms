package com.fronteo.cms.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fronteo.cms.dto.Ebook;

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
	
	
	
	public Map<String, Object> getTotalEbookCount(Map<String, Object> map) throws Exception {
		Map<String, Object> rmap = sqlSession.selectOne(namespace + ".getTotalEbookCount", map);
		return rmap;
	}
	
	public List<Map<String, Object>> getEbookList(Map<String, Object> map) throws Exception {
		List<Map<String, Object>> list = sqlSession.selectList(namespace + ".getEbookList", map);
		return list;
	}
	
	public Map<String, Object> getEbookCount(Map<String, Object> map) throws Exception {
		Map<String, Object> rmap = sqlSession.selectOne(namespace + ".getEbookCount", map);
		return rmap;
	}
	
	public Map<String, Object> getEbookDetail(Map<String, Object> map) throws Exception {
		Map<String, Object> rmap = sqlSession.selectOne(namespace + ".getEbookDetail", map);
		return rmap;
	}
	
	public Map<String, Object> getMaxEbookSeq(Map<String, Object> map) throws Exception {
		Map<String, Object> rmap = sqlSession.selectOne(namespace + ".getMaxEbookSeq", map);
		return rmap;
	}
	
	public int insertEbook(Ebook ebook) throws Exception {
		int cnt;
		sqlSession.update(namespace + ".insertEbook", ebook);
		cnt = ebook.getChapterSeq();
		return cnt;
	}
	
	public int updateEbook(Map<String, Object> map) throws Exception {
		int cnt = sqlSession.update(namespace + ".updateEbook", map);
		return cnt;
	}
	
	public int deleteEbook(Map<String, Object> map) throws Exception {
		int cnt = sqlSession.update(namespace + ".deleteEbook", map);
		return cnt;
	}
	
	
	
	public List<Map<String, Object>> getEbookContentList(Map<String, Object> map) throws Exception {
		List<Map<String, Object>> list = sqlSession.selectList(namespace + ".getEbookContentList", map);
		return list;
	}
	
	public Map<String, Object> getEbookContentDetail(Map<String, Object> map) throws Exception {
		Map<String, Object> rmap = sqlSession.selectOne(namespace + ".getEbookContentDetail", map);
		return rmap;
	}
	
	public Map<String, Object> getMaxEbookContentOrder(Map<String, Object> map) throws Exception {
		Map<String, Object> rmap = sqlSession.selectOne(namespace + ".getMaxEbookContentOrder", map);
		return rmap;
	}
	
	public int insertEbookContent(Map<String, Object> map) throws Exception {
		int cnt = sqlSession.update(namespace + ".insertEbookContent", map);
		return cnt;
	}
	
	public int updateEbookContent(Map<String, Object> map) throws Exception {
		int cnt = sqlSession.update(namespace + ".updateEbookContent", map);
		return cnt;
	}
	
	public int deleteEbookContent(Map<String, Object> map) throws Exception {
		int cnt = sqlSession.update(namespace + ".deleteEbookContent", map);
		return cnt;
	}
	
	public int deleteEbookAllContentByChapterSeq(Map<String, Object> map) throws Exception {
		int cnt = sqlSession.update(namespace + ".deleteEbookAllContentByChapterSeq", map);
		return cnt;
	}
	
	public int updateEbookContentOrder(Map<String, Object> map) throws Exception {
		int cnt = sqlSession.update(namespace + ".updateEbookContentOrder", map);
		return cnt;
	}
}
