package com.zdjc.report.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ProxyDate {
	

	private Date  target;
	
	public ProxyDate(Date target){
		
		this.target = target;
		
	}



	public String toString() {
		
		
		String format = "yyyy-MM-dd";
        
		return this.toString(format);
	}
	/**
	 * 格式化日期
	 * @param format
	 * @return
	 */
	public String toString(String format) {
		
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		  
		return sdf.format(this.target);
	}
}
