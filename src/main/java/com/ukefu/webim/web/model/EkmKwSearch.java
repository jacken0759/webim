package com.ukefu.webim.web.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.ukefu.util.UKTools;
/**
 * EKM-知识-搜索历史表
 *
 */
@Entity
@Table(name = "uk_ekm_search")
@org.hibernate.annotations.Proxy(lazy = false)
public class EkmKwSearch implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1115593425069549681L;
	
	private String id  = UKTools.getUUID();
	private String conditions ;//搜索内容
	private int times ;//搜索次数
	private String orgi ;
	private Date updatetime;
	private String type ;

	@Id
	@Column(length = 32)
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "assigned")	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getConditions() {
		return conditions;
	}
	public void setConditions(String conditions) {
		this.conditions = conditions;
	}
	public int getTimes() {
		return times;
	}
	public void setTimes(int times) {
		this.times = times;
	}
	public String getOrgi() {
		return orgi;
	}
	public void setOrgi(String orgi) {
		this.orgi = orgi;
	}
	public Date getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
