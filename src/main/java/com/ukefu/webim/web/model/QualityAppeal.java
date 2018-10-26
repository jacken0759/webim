package com.ukefu.webim.web.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * QC质检 - 质检申诉表
 *
 */
@Entity
@Table(name = "uk_qc_appeal")
@org.hibernate.annotations.Proxy(lazy = false)
public class QualityAppeal implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8414535274110731827L;

	
	private String id ;
	private String orgi ;		//租户ID
	private String creater ;//创建人
	private String resultid ;//质检结果id
	private String dataid ;//数据ID（通话记录ID/工单记录ID/会话记录ID）
	private String remarks ;//质检备注
	private Date createtime ;	//创建时间
	private String appealuser ; //申诉人id
	private String status ;		//状态
	
	
	@Id
	@Column(length = 32)
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrgi() {
		return orgi;
	}
	public void setOrgi(String orgi) {
		this.orgi = orgi;
	}
	public String getCreater() {
		return creater;
	}
	public void setCreater(String creater) {
		this.creater = creater;
	}
	public String getResultid() {
		return resultid;
	}
	public void setResultid(String resultid) {
		this.resultid = resultid;
	}
	public String getDataid() {
		return dataid;
	}
	public void setDataid(String dataid) {
		this.dataid = dataid;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public String getAppealuser() {
		return appealuser;
	}
	public void setAppealuser(String appealuser) {
		this.appealuser = appealuser;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
