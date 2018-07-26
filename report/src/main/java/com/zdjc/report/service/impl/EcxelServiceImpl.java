package com.zdjc.report.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdjc.report.mapper.monitor.AlarmLinkMan;
import com.zdjc.report.mapper.monitor.EcxelAlarmMapper;
import com.zdjc.report.model.fictitious.EcxelAlarm;
import com.zdjc.report.model.fictitious.EcxelData;
import com.zdjc.report.model.fictitious.EcxelModel;
import com.zdjc.report.model.fictitious.Result;
import com.zdjc.report.service.EcxelModelService;
import com.zdjc.report.service.EcxelService;
import com.zdjc.report.util.DownloadUtils;
import com.zdjc.report.util.GitYmlParaUtils;

@Service
public class EcxelServiceImpl implements EcxelService {
	@Autowired
	private GitYmlParaUtils gitYmlParaUtils;

	@Autowired
	private EcxelModelService ecxelModelService;
	
	@Autowired
	private AlarmLinkMan alarmLinkMan;
	
	@Autowired
	private	EcxelAlarmMapper  ecxelAlarmMapper;

	private static final Logger logger = LoggerFactory.getLogger(EcxelServiceImpl.class);
	
	

	/**
	 * 构建Ecxel表格
	 * 
	 * @param path
	 * @return Ecxel文件路径
	 */
	private String createEcxel(String projectId, String beginTime,String endTime) {

		String path = null;

		List<EcxelData> ecxelModels = ecxelModelService.selectEcxelModelByProjectId(projectId, beginTime, endTime);

		if (ecxelModels != null && ecxelModels.size() > 0) {
			String path_ = gitYmlParaUtils.accordingOsGetParm("ecxel")+ projectId + "Ecxel/";

			File Directory = new File(path_);

			if (!Directory.isDirectory()) Directory.mkdir();
			// 创建一个工作簿
			path = path_ + ecxelModels.get(0).getProjectName() + ".xlsx";
			
			File xlsFile = new File(path);
			// 创建一个工作簿
			WritableWorkbook workbook = null;
			try {
				workbook = Workbook.createWorkbook(xlsFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			for (int i = 0; i < ecxelModels.size(); i++) {

				EcxelData ecxelData = ecxelModels.get(i);
				// 创建一个工作表
				createSheel(workbook, ecxelData, i, projectId,beginTime,endTime);
			}
			try {
				workbook.write();
				workbook.close();
			} catch (IOException | WriteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {

			return null;
		}
		return path;
	}

	/**
	 * 创建一个工作表
	 */
	private void createSheel(WritableWorkbook workbook, EcxelData ecxelData,int sheetIndex,String projectId,String beginTime,String endTime) {

		// 创建一个工作表
		WritableSheet sheet = workbook.createSheet(ecxelData.getMonitorPoint(),sheetIndex);
		 EcxelAlarm ecxelAlarm = null;
		if(sheetIndex==0)  ecxelAlarm = selectLinkMan(projectId);

		createHead(sheet, ecxelData);
		
		createAram(sheet,ecxelAlarm,ecxelData,beginTime,endTime);

		createBody(sheet, ecxelData);

	}

	private void createAram(WritableSheet sheet,EcxelAlarm ecxelAlarm,EcxelData ecxelData,String beginTime,String endTime) {
					
	 	WritableFont writableFont = new WritableFont(WritableFont.createFont("宋体"), 12);
	 	
		WritableCellFormat writableCellFormat_BLUE = new WritableCellFormat(writableFont);
		
		WritableCellFormat writableCellFormat_RED = new WritableCellFormat(writableFont);
	
	
		try {
			writableCellFormat_BLUE.setBackground(Colour.BLUE);
			 
			writableCellFormat_BLUE.setBorder(Border.ALL, BorderLineStyle.THIN,Colour.BLACK);
			//水平居中显示
			writableCellFormat_BLUE.setAlignment(Alignment.LEFT);
			
			writableCellFormat_RED.setBackground(Colour.RED);
			 
			writableCellFormat_RED.setBorder(Border.ALL, BorderLineStyle.THIN,Colour.BLACK);
			//水平居中显示
			writableCellFormat_RED.setAlignment(Alignment.LEFT);
			
			 sheet.mergeCells(4, 0, 6, 0);			
			 sheet.setColumnView(4, 20);
			 sheet.setColumnView(5, 100);
			 sheet.setColumnView(6, 20);			
			 sheet.setRowView(4, 500);						 
			if(ecxelAlarm!=null) sheet.addCell(new Label(4, 0, "告警联系人:"+ecxelAlarm.getUserName() ,writableCellFormat_BLUE));			 
			 sheet.addCell(new Label(4, 1, "告警时间",writableCellFormat_BLUE));
			 sheet.addCell(new Label(5, 1, "告警内容",writableCellFormat_BLUE));
			 sheet.addCell(new Label(6, 1, "告警状态",writableCellFormat_BLUE));
			 
			List<EcxelAlarm> ecxelAlarms =  selectAlarms(ecxelData.getSensorNumber(), ecxelData.getSmuNumber(),ecxelData.getSmuChannel(), beginTime,  endTime);
			WritableCellFormat writableCellFormat = null;
			for(int i=0;i<ecxelAlarms.size();i++){
			EcxelAlarm ecxelAlarm_ =	ecxelAlarms.get(i);
				
				 for(int rank = 4;rank<7;rank++){
					 if(ecxelAlarm_.getAlarmStatus().equals("未确认")){
						 writableCellFormat = writableCellFormat_RED;
					}else{
						 writableCellFormat = writableCellFormat_BLUE;
					}
					if(rank==4) sheet.addCell(new Label(rank, i+2, ecxelAlarm_.getCreateTime(),writableCellFormat));
					if(rank==5) sheet.addCell(new Label(rank, i+2, ecxelAlarm_.getAlarmContext(),writableCellFormat));
					if(rank==6) sheet.addCell(new Label(rank, i+2, ecxelAlarm_.getAlarmStatus(),writableCellFormat));
				 }
				
			}
			 
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

	/**
	 * 组装数据体
	 * 
	 * @param sheet
	 * @param ecxelData
	 */
	private void createBody(WritableSheet sheet, EcxelData ecxelData) {
		// 字体样式
		WritableFont writableFont = new WritableFont(WritableFont.createFont("宋体"), 12);

		// 样式对象
		WritableCellFormat writableCellFormat = new WritableCellFormat(
				writableFont);

		// writableCellFormat.setBackground(Colour.YELLOW);
	

		try {
			writableCellFormat.setBorder(Border.ALL, BorderLineStyle.THIN,Colour.BLACK);
			//水平居中显示
			writableCellFormat.setAlignment(Alignment.LEFT);
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		List<EcxelModel> ecxelModels = ecxelData.getDatas();

		for (int i = 0; i < ecxelModels.size(); i++) {
			EcxelModel ecxelModel = ecxelModels.get(i);

			for (int rank = 0; rank < 4; rank++) {// 控制列
				try {
					if(rank==0)sheet.addCell(new Label(rank, i+6, ecxelModel.getCurrentTimes() ,writableCellFormat));
					if(rank==1)sheet.addCell(new Label(rank, i+6, ecxelModel.getTotalLaserChange() ,writableCellFormat));
					if(rank==2)sheet.addCell(new Label(rank, i+6, ecxelModel.getCurrentLaserChange() ,writableCellFormat));
					if(rank==3)sheet.addCell(new Label(rank, i+6, ecxelModel.getSpeedChange() ,writableCellFormat));
				} catch (RowsExceededException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (WriteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}

		}

	}

	/**
	 * 创建表格头，格式固定
	 */
	private void createHead(WritableSheet sheet, EcxelData ecxelData) {
		sheet.getSettings().setShowGridLines(true);

		// 合并单元格，第一个参数：要合并的单元格最左上角的列号，第二个参数：要合并的单元格最左上角的行号，第三个参数：要合并的单元格最右角的列号，第四个参数：要合并的单元格最右下角的行号，
		// 行合并
		try {
			//数据头信息
			sheet.mergeCells(0, 0, 3, 0);
			sheet.mergeCells(0, 1, 3, 1);

			sheet.mergeCells(0, 2, 1, 2);
			sheet.mergeCells(2, 2, 3, 2);

			sheet.mergeCells(0, 3, 1, 3);
			sheet.mergeCells(2, 3, 3, 3);

			sheet.mergeCells(0, 4, 1, 4);
			sheet.mergeCells(2, 4, 3, 4);
			sheet.setColumnView(0, 20);
			sheet.setColumnView(1, 20);
			sheet.setColumnView(2, 20);
			sheet.setColumnView(3, 20);
			sheet.setRowView(0, 500);
			sheet.setRowView(1, 500);
			sheet.setRowView(2, 500);
			sheet.setRowView(3, 500);
			sheet.setRowView(4, 500);
			sheet.setRowView(5, 500);
			
			// 字体样式
			WritableFont writableFont = new WritableFont(
					WritableFont.createFont("宋体"), 12);

			// 样式对象
			WritableCellFormat writableCellFormat = new WritableCellFormat(
					writableFont);

			// writableCellFormat.setBackground(Colour.YELLOW);

			writableCellFormat.setBorder(Border.ALL, BorderLineStyle.THIN,
					Colour.BLACK);

			writableCellFormat.setWrap(true);

			// 表头内容水平居中显示
			writableCellFormat.setAlignment(Alignment.LEFT);

			sheet.addCell(new Label(0, 0, ecxelData.getProjectName(),writableCellFormat));
			sheet.addCell(new Label(0, 1, "检测单位:湖南中大建设工程检测技术有限公司",writableCellFormat));
			sheet.addCell(new Label(0, 2, "初次测试时间:" + ecxelData.getFirstTime(),writableCellFormat));
			sheet.addCell(new Label(2, 2, "监测指标:" + ecxelData.getItemName(),writableCellFormat));

			sheet.addCell(new Label(0, 3, "传感器编号:"+ ecxelData.getSensorNumber(), writableCellFormat));
			sheet.addCell(new Label(2, 3,"测点名称:" + ecxelData.getMonitorPoint(), writableCellFormat));

			sheet.addCell(new Label(0, 4, "终端编号(DTU):"+ ecxelData.getSmuNumber(), writableCellFormat));
			sheet.addCell(new Label(2, 4, "采集器通道:" + ecxelData.getSmuChannel(),writableCellFormat));

			sheet.addCell(new Label(0, 5, "监测时间", writableCellFormat));
			sheet.addCell(new Label(1, 5, "累计变化量", writableCellFormat));
			sheet.addCell(new Label(2, 5, "单次变化量", writableCellFormat));
			sheet.addCell(new Label(3, 5, "变化速率", writableCellFormat));
		} catch (RowsExceededException e) {
			logger.error(e.getMessage());
		} catch (WriteException e) {

			logger.error(e.getMessage());
		}

	}

	@Override
	public Object  downEcxel(String projectId, String beginTime, String endTime) {
		
		Object hint = null;

		String path = createEcxel(projectId, beginTime, endTime);
		if (path == null) {

			return new Result<String>().failure("系统异常,下载失败");

		}
		hint = DownloadUtils.downloadSolve(path);
		
		new File(path).delete();

		return hint;
	}
	/**
	 * 查看告警信息
	 */
	@Override
	public List<EcxelAlarm> selectAlarms(String sensorNumber, String smuNumber,String smuChannel, String beginTime, String endTime) {
		DateTime endTime_ = new DateTime(endTime);	 
		
		String 	trueEndTime = endTime_.plusDays(1).toString("YYYY-MM-dd");
	
		return ecxelAlarmMapper.selectAlarms(sensorNumber, smuNumber, smuChannel, beginTime, trueEndTime);
	}
	
	/**
	 * 查询告警联系人
	 */
	@Override
	public EcxelAlarm selectLinkMan(String projectId) {// TODO Auto-generated method stub
		
		return alarmLinkMan.selectLinkMan(projectId);
	}

}
