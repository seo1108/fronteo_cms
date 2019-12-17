package com.fronteo.cms.dao;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public class LoginDao {
	@Autowired 
	@Resource(name="sqlSession")
	private SqlSession sqlSession;
	
	private static String namespace = "login";
	
	public boolean existAdmin(Map<String, Object> map) throws Exception {
		boolean isExist = sqlSession.selectOne(namespace + ".existAdmin", map);
		return isExist;
	}
	
	public Map<String, Object> getAdminInfo(Map<String, Object> map) throws Exception {
		Map<String, Object> rmap = sqlSession.selectOne(namespace + ".getAdminInfo", map);
		return rmap;
	}
}
