package com.ukefu.webim.web.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "uk_act_config")
@org.hibernate.annotations.Proxy(lazy = false)
public class CallOutConfig implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3932323765657445180L;
	private String id;
	private String name;
	private String orgi;
	private String creater ;
	private String type;
	
	private String dataid ;		//部门ID，将来按照部门扩展，每个部门可以有团队长独立设置 部门策略或其他标识
	
	private Date createtime = new Date();
	private Date updatetime = new Date();
	private String username ;
	private boolean enablecallout ;	//启用自动外呼
	private int countdown ;			//外呼倒计时时长
	private boolean enabletagentthreads ;	//启用人工坐席并发控制
	private int agentthreads ;				//人工坐席外呼并发数量
	
	private boolean enabletaithreads ;		//启用机器人并发外呼限制
	private int aithreads ;					//机器人外呼并发数量
	
	private boolean enableauto ;			//启用预览式外呼全自动功能
	
	private boolean forecast ;				//是否启用预测式外呼功能
	private int forecastratio ;				//预测阀值
	
	private int fmaxavgtime ;			//预计最大平均通话时长
	private int fminavgtime ;			//预计最小平均通话时长
	private int favgaftertime ;			//预计平均后处理时长
	
	
	private String defaultvalue ;	//默认 allow
	private String strategy;		//策略
	
	private boolean previewautocallout ;	//启用主动预览下的自动外呼功能
	
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public Date getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
	public String getDefaultvalue() {
		return defaultvalue;
	}
	public void setDefaultvalue(String defaultvalue) {
		this.defaultvalue = defaultvalue;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStrategy() {
		return strategy;
	}
	public void setStrategy(String strategy) {
		this.strategy = strategy;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public boolean isEnablecallout() {
		return enablecallout;
	}
	public void setEnablecallout(boolean enablecallout) {
		this.enablecallout = enablecallout;
	}
	public int getCountdown() {
		return countdown;
	}
	public void setCountdown(int countdown) {
		this.countdown = countdown;
	}
	public boolean isEnabletagentthreads() {
		return enabletagentthreads;
	}
	public void setEnabletagentthreads(boolean enabletagentthreads) {
		this.enabletagentthreads = enabletagentthreads;
	}
	public int getAgentthreads() {
		return agentthreads;
	}
	public void setAgentthreads(int agentthreads) {
		this.agentthreads = agentthreads;
	}
	public boolean isEnabletaithreads() {
		return enabletaithreads;
	}
	public void setEnabletaithreads(boolean enabletaithreads) {
		this.enabletaithreads = enabletaithreads;
	}
	public int getAithreads() {
		return aithreads;
	}
	public void setAithreads(int aithreads) {
		this.aithreads = aithreads;
	}
	public String getDataid() {
		return dataid;
	}
	public void setDataid(String dataid) {
		this.dataid = dataid;
	}
	public boolean isPreviewautocallout() {
		return previewautocallout;
	}
	public void setPreviewautocallout(boolean previewautocallout) {
		this.previewautocallout = previewautocallout;
	}
	public boolean isForecast() {
		return forecast;
	}
	public void setForecast(boolean forecast) {
		this.forecast = forecast;
	}
	public int getForecastratio() {
		return forecastratio;
	}
	public void setForecastratio(int forecastratio) {
		this.forecastratio = forecastratio;
	}
	public int getFmaxavgtime() {
		return fmaxavgtime;
	}
	public void setFmaxavgtime(int fmaxavgtime) {
		this.fmaxavgtime = fmaxavgtime;
	}
	public int getFminavgtime() {
		return fminavgtime;
	}
	public void setFminavgtime(int fminavgtime) {
		this.fminavgtime = fminavgtime;
	}
	public int getFavgaftertime() {
		return favgaftertime;
	}
	public void setFavgaftertime(int favgaftertime) {
		this.favgaftertime = favgaftertime;
	}
	public boolean isEnableauto() {
		return enableauto;
	}
	public void setEnableauto(boolean enableauto) {
		this.enableauto = enableauto;
	}
}
