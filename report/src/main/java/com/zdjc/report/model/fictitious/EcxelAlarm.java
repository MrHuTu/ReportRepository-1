package com.zdjc.report.model.fictitious;
/**
 * 这里承载Ecxel表格里面的告警内容
 * 
 * @author 胡超
 *
 */
public class EcxelAlarm {
	//告警联系人
	String userName;
	
	//告警内容
	String alarmContext;
	
	//告警产生时间
	String createTime;
	
	//告警状态
	String  alarmStatus;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAlarmContext() {
		return alarmContext;
	}

	public void setAlarmContext(String alarmContext) {
		this.alarmContext = alarmContext;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getAlarmStatus() {
		return alarmStatus;
	}

	public void setAlarmStatus(String alarmStatus) {
		if(alarmStatus.equals("0")){
			this.alarmStatus = "未确认";
		}else{
			this.alarmStatus = "已经确认";
		}
		
	}

	@Override
	public String toString() {
		return "EcxelAlarm [userName=" + userName + ", alarmContext="
				+ alarmContext + ", createTime=" + createTime
				+ ", alarmStatus=" + alarmStatus + "]";
	}
	
	

}
