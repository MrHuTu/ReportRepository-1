package com.zdjc.report.configclass.filldata.impl.dayreport;

import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zdjc.report.configclass.ReportConfig;
import com.zdjc.report.configclass.filldata.FillBasics;
import com.zdjc.report.model.fictitious.SideTableData;
import com.zdjc.report.service.SideTableDataService;
import com.zdjc.report.util.FillWordMapUtils;
@Component("Project176FillImpl_Day")
public class Project176FillImpl_Day implements FillBasics{

private  Logger logger = LoggerFactory.getLogger(Project176FillImpl_Day.class);
	
	@Autowired
	SideTableDataService sideTableDataService;

	@Override
	public void fillData(XWPFDocument doc2, String pojoId, String time) {
		
		synchronized(Project176FillImpl_Day.class){
			
			String downTime = "%"+time+"%";
			
			List<SideTableData> sideTableDatas = sideTableDataService.selectSideTableData(Integer.parseInt(pojoId),downTime);
								
				
			logger.info(pojoId+",sideTableDatas.size()="+sideTableDatas.size());
			
			
			
			//生成表格---竖向位移  16
			FillWordMapUtils.verticalDisplacement(doc2,sideTableDatas,"${tablea}","16",ReportConfig.DAY_COVERGENCE);
			
			
			//生成表格---竖向位移  16
			FillWordMapUtils.verticalDisplacement(doc2,sideTableDatas,"${tableb}","15",ReportConfig.DAY_COVERGENCE);
			
		}
		

	}
}
