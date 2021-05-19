package yzkf.test;

import java.util.Date;

public class Dbmodel2 {
	private Long id;
	private Long resourceID;
	private String resLink;
	private String activityName;
	private String activityArea;
	private String activityNumber;
	private String content;
	private int auditState;
	private int auditResult;
	private int isNotify;
	private int isInvite;
	private int isReceive;
	private String address;
	private Date beginTime; 
	private Date endTime;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getResourceID() {
		return resourceID;
	}
	public void setResourceID(Long resourceID) {
		this.resourceID = resourceID;
	}
	public String getResLink() {
		return resLink;
	}
	public void setResLink(String resLink) {
		this.resLink = resLink;
	}
	public String getActivityName() {
		return activityName;
	}
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	public String getActivityArea() {
		return activityArea;
	}
	public void setActivityArea(String activityArea) {
		this.activityArea = activityArea;
	}
	public String getActivityNumber() {
		return activityNumber;
	}
	public void setActivityNumber(String activityNumber) {
		this.activityNumber = activityNumber;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getAuditState() {
		return auditState;
	}
	public void setAuditState(int auditState) {
		this.auditState = auditState;
	}
	public int getAuditResult() {
		return auditResult;
	}
	public void setAuditResult(int auditResult) {
		this.auditResult = auditResult;
	}
	public int getIsNotify() {
		return isNotify;
	}
	public void setIsNotify(int isNotify) {
		this.isNotify = isNotify;
	}
	public int getIsInvite() {
		return isInvite;
	}
	public void setIsInvite(int isInvite) {
		this.isInvite = isInvite;
	}
	public int getIsReceive() {
		return isReceive;
	}
	public void setIsReceive(int isReceive) {
		this.isReceive = isReceive;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	
}
