package com.zdjc.report.model.fictitious;

import java.util.List;
/**
 * 处理分页的类
 * @author 胡超
 *
 * 
 */
public class MyPageMode<T> {
	//总夜数
	private String count;
	
	private List<T> dataList;

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public List<T> getDataList() {
		return dataList;
	}

	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}

	@Override
	public String toString() {
		return "MyPageMode [count=" + count + ", dataList=" + dataList + "]";
	}

	
	
	
	

}
