package com.ukefu.webim.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hazelcast.aggregation.Aggregators;
import com.hazelcast.core.IMap;
import com.hazelcast.query.PagingPredicate;
import com.hazelcast.query.SqlPredicate;
import com.ukefu.util.freeswitch.model.CallCenterAgent;
import com.ukefu.webim.service.cache.CacheHelper;
import com.ukefu.webim.service.quene.AgentCallOutFilterPredicate;
import com.ukefu.webim.service.quene.AiCallOutFilterPredicate;
import com.ukefu.webim.service.quene.CallCenterAgentOrgiFilterPredicate;
import com.ukefu.webim.service.quene.CallCenterAgentReadyOrgiFilterPredicate;
import com.ukefu.webim.service.quene.CallCenterInCallOrgiFilterPredicate;
import com.ukefu.webim.web.model.AgentReport;
import com.ukefu.webim.web.model.CallOutNames;

@Service("calloutquene")
public class CallOutQuene {
	/**
	 * 为外呼坐席分配名单
	 * @param agentStatus
	 */
	@SuppressWarnings("unchecked")
	public static List<CallCenterAgent> service(){
		List<CallCenterAgent> agentList = new ArrayList<CallCenterAgent>();
		if(CacheHelper.getCallCenterAgentCacheBean()!=null && CacheHelper.getCallCenterAgentCacheBean().getCache()!=null) {
			PagingPredicate<String, CallCenterAgent> pagingPredicate = new PagingPredicate<String, CallCenterAgent>(  new SqlPredicate( "workstatus = 'callout'") , 10 ) ;
			agentList.addAll(((IMap<String , CallCenterAgent>) CacheHelper.getCallCenterAgentCacheBean().getCache()).values(pagingPredicate)) ;
		}
		return agentList ;
	}
	
	/**
	 * 为外呼坐席分配名单
	 * @param agentStatus
	 */
	@SuppressWarnings("unchecked")
	public static List<CallCenterAgent> service(String sip){
		List<CallCenterAgent> agentList = new ArrayList<CallCenterAgent>();
		if(CacheHelper.getCallCenterAgentCacheBean()!=null && CacheHelper.getCallCenterAgentCacheBean().getCache()!=null) {
			PagingPredicate<String, CallCenterAgent> pagingPredicate = new PagingPredicate<String, CallCenterAgent>(  new SqlPredicate( "siptrunk = '"+sip+"'") , 10 ) ;
			agentList.addAll(((IMap<String , CallCenterAgent>) CacheHelper.getCallCenterAgentCacheBean().getCache()).values(pagingPredicate)) ;
		}
		return agentList ;
	}
	
	
	/**
	 * 获得 当前服务状态
	 * @param orgi
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static AgentReport getCallCenterAgentReport(String orgi){
		/**
		 * 统计当前在线的坐席数量
		 */
		AgentReport report = new AgentReport() ;
		IMap callCenterAgentMap = (IMap<String, Object>) CacheHelper.getCallCenterAgentCacheBean().getCache() ;
		Long agents = (Long) callCenterAgentMap.aggregate(Aggregators.<Map.Entry<String, CallCenterAgent>>count(), new CallCenterAgentOrgiFilterPredicate(orgi)) ;
		report.setAgents(agents.intValue());
		
		Long readyAgent = (Long) callCenterAgentMap.aggregate(Aggregators.<Map.Entry<String, CallCenterAgent>>count(), new CallCenterAgentReadyOrgiFilterPredicate(orgi)) ;
		report.setReadyagents(readyAgent.intValue());
		
		Long inCallAgent = (Long) callCenterAgentMap.aggregate(Aggregators.<Map.Entry<String, CallCenterAgent>>count(), new CallCenterInCallOrgiFilterPredicate(orgi)) ;
		report.setIncall(inCallAgent.intValue());
		
		report.setOrgi(orgi);
		return report;
	}
	
	/**
	 * 为外呼坐席分配名单
	 * @param agentStatus
	 */
	@SuppressWarnings("unchecked")
	public static List<CallCenterAgent> extention(String extno){
		List<CallCenterAgent> agentList = new ArrayList<CallCenterAgent>();
		if(CacheHelper.getCallCenterAgentCacheBean()!=null && CacheHelper.getCallCenterAgentCacheBean().getCache()!=null) {
			PagingPredicate<String, CallCenterAgent> pagingPredicate = new PagingPredicate<String, CallCenterAgent>(  new SqlPredicate( "extno = '"+extno+"'") , 10 ) ;
			agentList.addAll(((IMap<String , CallCenterAgent>) CacheHelper.getCallCenterAgentCacheBean().getCache()).values(pagingPredicate)) ;
		}
		return agentList ;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static int countAiCallOut(String orgi) {
		/**
		 * 统计当前在线的坐席数量
		 */
		IMap callOutMap = (IMap<String, Object>) CacheHelper.getCallOutCacheBean().getCache() ;
		Long names = (Long) callOutMap.aggregate(Aggregators.<Map.Entry<String, CallOutNames>>count(), new AiCallOutFilterPredicate(orgi)) ;
		return names!=null ? names.intValue() : 0 ;
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static int countAiCallOut(String orgi,String ownerai) {
		/**
		 * 统计当前在线的坐席数量
		 */
		IMap callOutMap = (IMap<String, Object>) CacheHelper.getCallOutCacheBean().getCache() ;
		Long names = (Long) callOutMap.aggregate(Aggregators.<Map.Entry<String, CallOutNames>>count(), new AiCallOutFilterPredicate(orgi,ownerai)) ;
		return names!=null ? names.intValue() : 0 ;
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static int countAgentCallOut(String orgi) {
		/**
		 * 统计当前在线的坐席数量
		 */
		IMap callOutMap = (IMap<String, Object>) CacheHelper.getCallOutCacheBean().getCache() ;
		Long names = (Long) callOutMap.aggregate(Aggregators.<Map.Entry<String, CallOutNames>>count(), new AgentCallOutFilterPredicate(orgi)) ;
		return names!=null ? names.intValue() : 0 ;
	}
	
	
	/**
	 * 外呼监控，包含机器人和人工两个部分
	 * @param agentStatus
	 */
	@SuppressWarnings("unchecked")
	public static List<CallOutNames> callOutNames(String calltype , int p , int ps){
		List<CallOutNames> callOutNamesList = new ArrayList<CallOutNames>();
		if(CacheHelper.getCallOutCacheBean()!=null && CacheHelper.getCallOutCacheBean().getCache()!=null) {
			PagingPredicate<String, CallOutNames> pagingPredicate = new PagingPredicate<String, CallOutNames>(  new SqlPredicate( "calltype = '"+calltype+"'") , 10 ) ;
			pagingPredicate.setPage(p);
			callOutNamesList.addAll(((IMap<String , CallOutNames>) CacheHelper.getCallOutCacheBean().getCache()).values(pagingPredicate)) ;
		}
		return callOutNamesList ;
	}
}
