package com.fronteo.cms.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.fronteo.cms.dao.BoardDao;

@Service
public class BoardService {
	@Inject
	private BoardDao dao;
}
