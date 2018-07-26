package com.zdjc.report.configclass.filldata.impl.weekreport;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zdjc.report.configclass.ReportConfig;
import com.zdjc.report.configclass.filldata.FillBasics;
import com.zdjc.report.model.fictitious.ReportWeekData_Head;
import com.zdjc.report.service.SideTableDataService;
import com.zdjc.report.util.FillWordMapUtils;


@Component("Project260FillImpl_Week")
public class Project260FillImpl_Week implements FillBasics{
	
	//private  Logger logger = LoggerFactory.getLogger(Project176FillImpl_Week.class);
	
	@Autowired
	SideTableDataService sideTableDataService;
	@Override
	public void fillData(XWPFDocument doc2,String pojoId,String time) {		
		
		Lock lock = new  ReentrantLock();
		
		lock.lock();
		
		List<ReportWeekData_Head> reportWeekDatas = sideTableDataService.selectReportOfWeek(pojoId,time);
	
		FillWordMapUtils.verticalDisplacement_Week(doc2,reportWeekDatas,"${tableb}","16",ReportConfig.WEEK_SEDIMENTATION_BANK);
		
		lock.unlock();
		
	}
	
	

}
