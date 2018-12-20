package com.ukefu.webim.web.model;

import java.util.LinkedList;

import com.ukefu.util.IP;

public class AiUser implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id ;
	private String userid ;
	private long time ;
	private IP ipdata ;
	private String orgi;
	private String agentserviceid ;
	private String sessionid ;
	
	private String contextid ;
	private String appid ;
	private String channel ;
	private String username ;
	private String aiid ;
	
	private String skill ;
	
	private String busstype ;
	private String aitype ;
	private String bussid ;
	private String dataid ;
	private boolean bussend;
	
	private int userask ;	//访客提问数量
	private boolean agent ; //直接转人工
	
	private int timeoutnums ;	//超时次数
	private int retimes ;		//重复次数
	private int errortimes ;	//错误次数
	private String queresultid ;//问卷结果主表ID
	
	private boolean interrupt = false;//是否允许打断
	private int interrupttime = 3000;//打断开始时间
	
	private int maxspreak = 10000;//最大说话时长
	
	private boolean bridge = false;//转接
	private String trans;//转接号码
	
	private LinkedList<QueSurveyResultQuestion> questionList;
	
	public AiUser(String id , String userid, long time,String orgi , IP ipdata){
		this.id = id.replace("-", "") ;
		this.userid = userid ;
		this.time = time ;
		this.ipdata = ipdata ;
		this.orgi = orgi;
		this.questionList = new LinkedList<>();
	}
	
	public String getOrgi() {
		return orgi;
	}

	public void setOrgi(String orgi) {
		this.orgi = orgi;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}

	public IP getIpdata() {
		return ipdata;
	}

	public void setIpdata(IP ipdata) {
		this.ipdata = ipdata;
	}

	public String getAgentserviceid() {
		return agentserviceid;
	}

	public void setAgentserviceid(String agentserviceid) {
		this.agentserviceid = agentserviceid;
	}

	public String getSessionid() {
		return sessionid;
	}

	public void setSessionid(String sessionid) {
		this.sessionid = sessionid;
	}

	public int getUserask() {
		return userask;
	}

	public void setUserask(int userask) {
		this.userask = userask;
	}

	public boolean isAgent() {
		return agent;
	}

	public void setAgent(boolean agent) {
		this.agent = agent;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getAiid() {
		return aiid;
	}

	public void setAiid(String aiid) {
		this.aiid = aiid;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getContextid() {
		return contextid;
	}

	public void setContextid(String contextid) {
		this.contextid = contextid;
	}

	public String getBusstype() {
		return busstype;
	}

	public void setBusstype(String busstype) {
		this.busstype = busstype;
	}

	public String getAitype() {
		return aitype;
	}

	public void setAitype(String aitype) {
		this.aitype = aitype;
	}

	public String getBussid() {
		return bussid;
	}

	public void setBussid(String bussid) {
		this.bussid = bussid;
	}

	public String getDataid() {
		return dataid;
	}

	public void setDataid(String dataid) {
		this.dataid = dataid;
	}

	public boolean isBussend() {
		return bussend;
	}

	public void setBussend(boolean bussend) {
		this.bussend = bussend;
	}

	public int getTimeoutnums() {
		return timeoutnums;
	}

	public void setTimeoutnums(int timeoutnums) {
		this.timeoutnums = timeoutnums;
	}

	public int getRetimes() {
		return retimes;
	}

	public void setRetimes(int retimes) {
		this.retimes = retimes;
	}

	public int getErrortimes() {
		return errortimes;
	}

	public void setErrortimes(int errortimes) {
		this.errortimes = errortimes;
	}

	public String getQueresultid() {
		return queresultid;
	}

	public void setQueresultid(String queresultid) {
		this.queresultid = queresultid;
	}

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public LinkedList<QueSurveyResultQuestion> getQuestionList() {
		return questionList;
	}

	public void setQuestionList(LinkedList<QueSurveyResultQuestion> questionList) {
		this.questionList = questionList;
	}
	
	public boolean isInterrupt() {
		return interrupt;
	}

	public void setInterrupt(boolean interrupt) {
		this.interrupt = interrupt;
	}

	public int getMaxspreak() {
		return maxspreak;
	}

	public void setMaxspreak(int maxspreak) {
		this.maxspreak = maxspreak;
	}

	public boolean isBridge() {
		return bridge;
	}

	public void setBridge(boolean bridge) {
		this.bridge = bridge;
	}

	public String getTrans() {
		return trans;
	}

	public void setTrans(String trans) {
		this.trans = trans;
	}

	public int getInterrupttime() {
		return interrupttime;
	}

	public void setInterrupttime(int interrupttime) {
		this.interrupttime = interrupttime;
	}
	
	
}
