package com.skyon.spider.model;

import java.io.Serializable;

/**
 * 工作信息
 * @author 王辉
 *
 */
public class JobInfo implements Serializable{

	private static final long serialVersionUID = 7104185037342814837L;
	
	private String title;//标题
	
	private String salary;//薪水
	
	private String company;//公司
	
	private String publishTime;//发布时间
	
	private String company_address;//公司地址

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}

	public String getCompany_address() {
		return company_address;
	}

	public void setCompany_address(String company_address) {
		this.company_address = company_address;
	}

}
