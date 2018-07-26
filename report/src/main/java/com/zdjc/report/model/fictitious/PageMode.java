package com.zdjc.report.model.fictitious;

import java.util.List;

import com.zdjc.report.model.AuthorityReport;
/**
 * 处理分页的类
 * @author 胡超
 *
 * 
 */
public class PageMode {
	//总夜数
	private String count;
	
	private List<AuthorityReport> dataList;

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public List<AuthorityReport> getDataList() {
		return dataList;
	}

	public void setDataList(List<AuthorityReport> dataList) {
		this.dataList = dataList;
	}

	
	
	

}
