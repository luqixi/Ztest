package com.linkstec.mot.bean.msg;

import java.io.Serializable;
import java.util.Date;

public class EventParamDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// E_EVENT_SEND_FLOW事件流水ID
	private Long eventFlowId;
	// E_EVENT_SEND_FLOW事件ID
	private Long eventId;
	// E_EVENT_SEND_FLOW系统客户号
	private String customerId;
	// E_EVENT_SEND_FLOW附加信息
	private String appendInformation;
	// E_EVENT_SEND_FLOW
	private String branchId;
	// E_EVENT_SEND_FLOW
	private String eventDesc;
	// E_EVENT_SEND_FLOW系统业务日期
	private Long bizDate;
	// E_EVENT_SEND_FLOW触发日期
	private Date eventTrgTime;
	// E_EVENT_SEND_FLOW处理期限
	private Date dealDeadline;
	// MOT_CST_FUNDACC_MAIN_D
	private Long branchCode;
	// MOT_CST_FUNDACC_MAIN_D经济客户号
	private String brokId;
	// MOT_CST_FUNDACC_MAIN_D客户姓名
	private String custName;
	// MOT_CST_FUNDACC_MAIN_D客户等级
	private String custLevel;
	// MOT_CST_FUNDACC_MAIN_D是否公司统一服务
	private String sfgstyfw;
	// MOT_CST_FUNDACC_MAIN_D个人or机构
	private String pOrO;
	// 手工推送的requestId
	private Long requestId;

	/**
	 * 
	 * @return
	 */
	public Long getEventFlowId() {
		return eventFlowId;
	}

	/**
	 * 
	 * @param eventFlowId
	 */
	public void setEventFlowId(Long eventFlowId) {
		this.eventFlowId = eventFlowId;
	}

	/**
	 * 
	 * @return
	 */
	public Long getEventId() {
		return eventId;
	}

	/**
	 * 
	 * @param eventId
	 */
	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

	/**
	 * 
	 * @return
	 */
	public String getCustomerId() {
		return customerId;
	}

	/**
	 * 
	 * @param customerId
	 */
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	/**
	 * 
	 * @return
	 */
	public String getAppendInformation() {
		return appendInformation;
	}

	/**
	 * 
	 * @param appendInformation
	 */
	public void setAppendInformation(String appendInformation) {
		this.appendInformation = appendInformation;
	}

	/**
	 * 
	 * @return
	 */
	public String getBranchId() {
		return branchId;
	}

	/**
	 * 
	 * @param branchId
	 */
	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	/**
	 * 
	 * @return
	 */
	public String getEventDesc() {
		return eventDesc;
	}

	/**
	 * 
	 * @param eventDesc
	 */
	public void setEventDesc(String eventDesc) {
		this.eventDesc = eventDesc;
	}

	/**
	 * 
	 * @return
	 */
	public Long getBizDate() {
		return bizDate;
	}

	/**
	 * 
	 * @param bizDate
	 */
	public void setBizDate(Long bizDate) {
		this.bizDate = bizDate;
	}

	/**
	 * 
	 * @return
	 */
	public Date getEventTrgTime() {
		return eventTrgTime;
	}

	/**
	 * 
	 * @param eventTrgTime
	 */
	public void setEventTrgTime(Date eventTrgTime) {
		this.eventTrgTime = eventTrgTime;
	}

	/**
	 * 
	 * @return
	 */
	public Date getDealDeadline() {
		return dealDeadline;
	}

	/**
	 * 
	 * @param dealDeadline
	 */
	public void setDealDeadline(Date dealDeadline) {
		this.dealDeadline = dealDeadline;
	}

	/**
	 * 
	 * @return
	 */
	public Long getBranchCode() {
		return branchCode;
	}

	/**
	 * 
	 * @param branchCode
	 */
	public void setBranchCode(Long branchCode) {
		this.branchCode = branchCode;
	}

	/**
	 * 
	 * @return
	 */
	public String getBrokId() {
		return brokId;
	}

	/**
	 * 
	 * @param brokId
	 */
	public void setBrokId(String brokId) {
		this.brokId = brokId;
	}

	/**
	 * 
	 * @return
	 */
	public String getCustName() {
		return custName;
	}

	/**
	 * 
	 * @param custName
	 */
	public void setCustName(String custName) {
		this.custName = custName;
	}

	/**
	 * 
	 * @return
	 */
	public String getCustLevel() {
		return custLevel;
	}

	/**
	 * 
	 * @param custLevel
	 */
	public void setCustLevel(String custLevel) {
		this.custLevel = custLevel;
	}

	/**
	 * 
	 * @return
	 */
	public String getSfgstyfw() {
		return sfgstyfw;
	}

	/**
	 * 
	 * @param sfgstyfw
	 */
	public void setSfgstyfw(String sfgstyfw) {
		this.sfgstyfw = sfgstyfw;
	}

	/**
	 * 
	 * @return
	 */
	public String getpOrO() {
		return pOrO;
	}

	/**
	 * 
	 * @param pOrO
	 */
	public void setpOrO(String pOrO) {
		this.pOrO = pOrO;
	}

	public Long getRequestId() {
		return requestId;
	}

	public void setRequestId(Long requestId) {
		this.requestId = requestId;
	}

	@Override
	public String toString() {
		return "EventParamDto [eventFlowId=" + eventFlowId + ", eventId="
				+ eventId + ", customerId=" + customerId
				+ ", appendInformation=" + appendInformation + ", branchId="
				+ branchId + ", eventDesc=" + eventDesc + ", bizDate="
				+ bizDate + ", eventTrgTime=" + eventTrgTime
				+ ", dealDeadline=" + dealDeadline + ", branchCode="
				+ branchCode + ", brokId=" + brokId + ", custName=" + custName
				+ ", custLevel=" + custLevel + ", sfgstyfw=" + sfgstyfw
				+ ", pOrO=" + pOrO + ", requestId=" + requestId + "]";
	}

}
