package com.zdjc.report.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ReportDateUtils {
	
	/**
	 * 取当前日期的一周起始时间，和结束时间。对应星期一到星期日的日期
	 * @param date
	 * @return
	 */
	public  static Map<String,String> getTimeInterval(Date date) {  
		
		Map<String,String> dataMap =  new HashMap<String,String>();
		
		
		  DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
	     Calendar cal = Calendar.getInstance();  
	     
	     cal.setTime(date);  
	     
	     // 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了  
	     int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天  
	     
	     if (1 == dayWeek) {  
	    	 
	        cal.add(Calendar.DAY_OF_MONTH, -1);  
	        
	     }  
	     
	     // System.out.println("要计算日期为:" + sdf.format(cal.getTime())); // 输出要计算日期  
	     // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一  
	     cal.setFirstDayOfWeek(Calendar.MONDAY);  
	     // 获得当前日期是一个星期的第几天  
	     int day = cal.get(Calendar.DAY_OF_WEEK);  
	     // 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值  
	     cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);  
	     
	  
	    
		String imptimeBegin = sdf.format(cal.getTime());  
		
	     // System.out.println("所在周星期一的日期：" + imptimeBegin);  
	     cal.add(Calendar.DATE, 6);  
	     
	     String imptimeEnd = sdf.format(cal.getTime());  
	     
	     
	     dataMap.put("begin", imptimeBegin+" 00:00:00");
	     dataMap.put("end", imptimeEnd+" 23:59:59");
	     
	     // System.out.println("所在周星期日的日期：" + imptimeEnd);  
	     return dataMap;  
	}  
	
	/**
	 * 取date是当前一周的第几天，对应星期几
	 * @return
	 */
	public static int getDayOfWeek(Date date){
		
		  Calendar cal = Calendar.getInstance();  
		  
		  cal.setTime(date);
					
		  int dayWeek = cal.get(Calendar.DAY_OF_WEEK)-1;
		  
		  if(dayWeek==0) dayWeek=7;
		  
		  return dayWeek;
	}
	/**
	 * 取出当前日期所在月的起始日期和结束日期
	 * @param date
	 * @return Map<String,Date> key =stime-->起始时间，key =etime-->结束时间
	 */
	public static Map<String,Date> getTimeQuantumOfMonth(Date date){
		
			Map<String, Date> map =  new HashMap<String,Date>();
			
			if(date == null) date=new Date();
			
	        Calendar calendar=Calendar.getInstance();
	        
	        //本月最大的天数
	        int max = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	     	        
	        calendar.setTime(date);
	        
	        int dow=calendar.get(Calendar.DAY_OF_MONTH);
	        	  
	        //本月第一天
	        calendar.add(Calendar.DAY_OF_YEAR, -dow+1);
	        	     	        
	        map.put("stime",calendar.getTime());
	        
	        //本月最后一天
	        calendar.add(Calendar.DAY_OF_YEAR, max-1);
	        
	        map.put("etime",  calendar.getTime());
	        	       	        	      
		return map;
		
	}
	
	
}
