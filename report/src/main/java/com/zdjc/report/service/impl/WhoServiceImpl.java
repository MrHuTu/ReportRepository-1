package com.zdjc.report.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdjc.report.mapper.report.WhoMapper;
import com.zdjc.report.model.Who;
import com.zdjc.report.service.WhoService;

@Service
public class WhoServiceImpl implements WhoService {
	
	@Autowired
	WhoMapper whoMapper;
	
	@Override
	public List<Who> getWho() {
		// TODO Auto-generated method stub
		return whoMapper.getWho();
	}


	
}
