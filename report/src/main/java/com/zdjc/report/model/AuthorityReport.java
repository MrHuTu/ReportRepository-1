package com.zdjc.report.model;
/**
 * 用来映射report_authority表数据
 * report_authority用来记录报表管理用户上传的文件信息
 * @author huchao
 *
 */
public class AuthorityReport {
	//组建，该报告的唯一表示
	Integer id;
	//项目Id
	String projectId;
	
	//报告类型
	String reportTyp;
	
	//文件储存路径
	String reportPath;
	
	//文件名
	String reportName;
	
	//报告提交者
	String commit_user;
	
	//报告提交时间
	String commit_time;
	
	//报告从属的时间段
	String timeof_Report;
	
	//报告负责人
	String personIn_charge;

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getReportTyp() {
		return reportTyp;
	}

	public void setReportTyp(String reportTyp) {
		this.reportTyp = reportTyp;
	}

	public String getReportPath() {
		return reportPath;
	}

	public void setReportPath(String reportPath) {
		this.reportPath = reportPath;
	}

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	public String getCommit_user() {
		return commit_user;
	}

	public void setCommit_user(String commit_user) {
		this.commit_user = commit_user;
	}

	public String getCommit_time() {
		return commit_time;
	}

	public void setCommit_time(String commit_time) {
		this.commit_time = commit_time;
	}

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTimeof_Report() {
		return timeof_Report;
	}

	public void setTimeof_Report(String timeof_Report) {
		this.timeof_Report = timeof_Report;
	}

	public String getPersonIn_charge() {
		return personIn_charge;
	}

	public void setPersonIn_charge(String personIn_charge) {
		this.personIn_charge = personIn_charge;
	}

	@Override
	public String toString() {
		return "AuthorityReport [id=" + id + ", projectId=" + projectId
				+ ", reportTyp=" + reportTyp + ", reportPath=" + reportPath
				+ ", reportName=" + reportName + ", commit_user=" + commit_user
				+ ", commit_time=" + commit_time + ", timeof_Report="
				+ timeof_Report + ", personIn_charge=" + personIn_charge + "]";
	}

	

	
	

}
