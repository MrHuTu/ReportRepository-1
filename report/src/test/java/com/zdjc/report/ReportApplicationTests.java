package com.zdjc.report;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zdjc.report.service.EcxelModelService;
import com.zdjc.report.service.WhoService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReportApplicationTests {
	
	@Autowired
	WhoService whoService;
	@Autowired
	EcxelModelService ecxelModelService;

	@Test
	public void contextLoads() {
					
		System.out.println(whoService.getWho());
	}
	public static void getTime(Date date) {
        if(date == null) date=new Date();
        Calendar calendar=Calendar.getInstance();
        
        //本月最大的天数
        int max = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        
        System.out.println("本月的天数天"+max);
        
        calendar.setTime(date);
        
        int dow=calendar.get(Calendar.DAY_OF_MONTH);
        
        System.out.println("当月第"+dow+"天");
       
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        //本月第一天
        calendar.add(Calendar.DAY_OF_YEAR, -dow+1);
        
        System.out.println("当月的第一天"+sdf.format(calendar.getTime()));
        
        //本月最后一天
        calendar.add(Calendar.DAY_OF_YEAR, max-1);
        
        System.out.println("当月的第后一天"+sdf.format(calendar.getTime()));
    }
	
	/*public static void main(String[] args) {
	
		 
	}*/
	@Test

	public void selectEcxelModelByProjectId(){
		//List<EcxelModel> a= ecxelModelService.selectEcxelModelByProjectId("176", "2018-08-73", "2018-08-74");
	}
	
}
