package com.zdjc.report.model.fictitious;

import java.util.ArrayList;
import java.util.List;

import com.zdjc.report.model.AuthorityReport;

public class AuthorityReportMassgae {
	
	private List<AuthorityReport> day;
	
	private List<AuthorityReport> week;
	
	private List<AuthorityReport> month;
	
	public AuthorityReportMassgae() {
		
		day = new ArrayList<AuthorityReport>();
		week = new ArrayList<AuthorityReport>();
		month = new ArrayList<AuthorityReport>();
		// TODO Auto-generated constructor stub
	}

	public List<AuthorityReport> getDay() {
		return day;
	}

	public void addDay(AuthorityReport day) {
		this.day.add(day);
	}

	public List<AuthorityReport> getWeek() {
		return week;
	}

	public void addWeek(AuthorityReport authorityReport) {
		this.week.add(authorityReport) ;
	}

	public List<AuthorityReport> getMonth() {
		return month;
	}

	public void addMonth(AuthorityReport month) {
		this.month.add(month);
	}
	
	

}
