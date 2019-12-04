package com.fronteo.cms.dao;

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
}
