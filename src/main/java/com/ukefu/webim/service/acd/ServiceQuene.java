package com.ukefu.webim.service.acd;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;

import org.apache.commons.lang.StringUtils;

import com.hazelcast.aggregation.Aggregators;
import com.hazelcast.core.IMap;
import com.hazelcast.query.PagingPredicate;
import com.hazelcast.query.SqlPredicate;
import com.ukefu.core.UKDataContext;
import com.ukefu.util.UKTools;
import com.ukefu.util.WebIMReport;
import com.ukefu.util.client.NettyClients;
import com.ukefu.webim.service.cache.CacheHelper;
import com.ukefu.webim.service.quene.AgentStatusBusyOrgiFilterPredicate;
import com.ukefu.webim.service.quene.AgentStatusOrgiFilterPredicate;
import com.ukefu.webim.service.quene.AgentUserOrgiFilterPredicate;
import com.ukefu.webim.service.repository.AgentReportRepository;
import com.ukefu.webim.service.repository.AgentServiceRepository;
import com.ukefu.webim.service.repository.AgentStatusRepository;
import com.ukefu.webim.service.repository.AgentUserRepository;
import com.ukefu.webim.service.repository.AgentUserTaskRepository;
import com.ukefu.webim.service.repository.OnlineUserRepository;
import com.ukefu.webim.service.repository.SessionConfigRepository;
import com.ukefu.webim.service.repository.WorkMonitorRepository;
import com.ukefu.webim.util.router.OutMessageRouter;
import com.ukefu.webim.web.model.AgentReport;
import com.ukefu.webim.web.model.AgentService;
import com.ukefu.webim.web.model.AgentStatus;
import com.ukefu.webim.web.model.AgentUser;
import com.ukefu.webim.web.model.AgentUserTask;
import com.ukefu.webim.web.model.AiUser;
import com.ukefu.webim.web.model.MessageOutContent;
import com.ukefu.webim.web.model.OnlineUser;
import com.ukefu.webim.web.model.SessionConfig;
import com.ukefu.webim.web.model.WorkMonitor;

public class ServiceQuene {
	
	/**
	 * 载入坐席 ACD策略配置
	 * @param orgi
	 * @return
	 */
	public static SessionConfig initSessionConfig(String orgi){
		SessionConfig sessionConfig = null;
		if(UKDataContext.getContext() != null && (sessionConfig = (SessionConfig) CacheHelper.getSystemCacheBean().getCacheObject(UKDataContext.SYSTEM_CACHE_SESSION_CONFIG+"_"+orgi, orgi)) == null){
			SessionConfigRepository agentUserRepository = UKDataContext.getContext().getBean(SessionConfigRepository.class) ;
			sessionConfig = agentUserRepository.findByOrgi(orgi) ;
			if(sessionConfig != null){
				CacheHelper.getSystemCacheBean().put(UKDataContext.SYSTEM_CACHE_SESSION_CONFIG+"_"+orgi,sessionConfig, orgi) ;
			}
		}
		if(sessionConfig == null) {
			sessionConfig = new SessionConfig() ;
		}
		return sessionConfig ;
	}
	
	/**
	 * 载入坐席 ACD策略配置
	 * @param orgi
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<SessionConfig> initSessionConfigList(){
		List<SessionConfig> sessionConfigList = null;
		if(UKDataContext.getContext() != null && (sessionConfigList = (List<SessionConfig>) CacheHelper.getSystemCacheBean().getCacheObject(UKDataContext.SYSTEM_CACHE_SESSION_CONFIG_LIST, UKDataContext.SYSTEM_ORGI)) == null){
			SessionConfigRepository agentUserRepository = UKDataContext.getContext().getBean(SessionConfigRepository.class) ;
			sessionConfigList = agentUserRepository.findAll();
			if(sessionConfigList != null && sessionConfigList.size() > 0){
				CacheHelper.getSystemCacheBean().put(UKDataContext.SYSTEM_CACHE_SESSION_CONFIG_LIST,sessionConfigList, UKDataContext.SYSTEM_ORGI) ;
			}
		}
		return sessionConfigList ;
	}
	/**
	 * 获得 当前服务状态
	 * @param orgi
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static AgentReport getAgentReport(String orgi){
		/**
		 * 统计当前在线的坐席数量
		 */
		AgentReport report = new AgentReport() ;
		IMap agentStatusMap = (IMap<String, Object>) CacheHelper.getAgentStatusCacheBean().getCache() ;
		Long agents = (Long) agentStatusMap.aggregate(Aggregators.<Map.Entry<String, AgentStatus>>count(), new AgentStatusOrgiFilterPredicate(orgi)) ;
		report.setAgents(agents.intValue());
		
		Long busyAgent = (Long) agentStatusMap.aggregate(Aggregators.<Map.Entry<String, AgentStatus>>count(), new AgentStatusBusyOrgiFilterPredicate(orgi)) ;
		report.setBusy(busyAgent.intValue());
		report.setOrgi(orgi);
		
		/**
		 * 统计当前服务中的用户数量
		 */
		IMap agentUserMap = (IMap<String, Object>) CacheHelper.getAgentUserCacheBean().getCache() ;
		Long users = (Long) agentUserMap.aggregate(Aggregators.<Map.Entry<String, AgentUser>>count(), new AgentUserOrgiFilterPredicate(orgi,UKDataContext.AgentUserStatusEnum.INSERVICE.toString())) ;
		report.setUsers(users.intValue());
		
		Long queneUsers = (Long) agentUserMap.aggregate(Aggregators.<Map.Entry<String, AgentUser>>count(), new AgentUserOrgiFilterPredicate(orgi,UKDataContext.AgentUserStatusEnum.INQUENE.toString())) ;
		report.setInquene(queneUsers.intValue());
		
		return report;
	}
	
	public static int getQueneIndex(String userid , String orgi , long ordertime){
		
//		IList<AgentUser> queneUserList = (IList<AgentUser>) CacheHelper.getQueneUserCacheBean().getCache() ;
		int queneUsers = 0 ;
//		for(AgentUser agentUser : queneUserList){
//			if(agentUser.getOrgi().equals(orgi) && agentUser.getUserid().equals(userid)){
//				queneUsers ++ ;
//			}
//		}
		
		return queneUsers;
	}
	
	@SuppressWarnings("unchecked")
	public static int getQueneIndex(String agent , String orgi , String skill){
		
		int queneUsers = 0 ;
		
		PagingPredicate<String, AgentUser> pagingPredicate = null ;
		if(!StringUtils.isBlank(skill)){
			pagingPredicate = new PagingPredicate<String, AgentUser>(  new SqlPredicate( "status = 'inquene' AND skill = '" + skill + "'  AND orgi = '" + orgi +"'") , 100 );
		}else if(!StringUtils.isBlank(agent)){
			pagingPredicate = new PagingPredicate<String, AgentUser>(  new SqlPredicate( "status = 'inquene' AND agent = '"+agent+"' AND orgi = '" + orgi +"'") , 100 );
		}else{
			pagingPredicate = new PagingPredicate<String, AgentUser>(  new SqlPredicate( "status = 'inquene' AND orgi = '" + orgi +"'") , 100 );
		}
		queneUsers = ((IMap<String , AgentUser>) CacheHelper.getAgentUserCacheBean().getCache()).values(pagingPredicate) .size();
		return queneUsers;
	}
	
	@SuppressWarnings("unchecked")
	public static int getAgentUsers(String agent , String orgi){
		/**
		 * agentno自动是 服务的坐席， agent 是请求的坐席
		 */
		PagingPredicate<String, AgentUser> pagingPredicate = new PagingPredicate<String, AgentUser>(  new SqlPredicate( "status = 'inservice' AND agentno = '" + agent + "'  AND orgi = '" + orgi +"'") , 100 ) ;
		List<AgentUser> agentUserList = new ArrayList<AgentUser>();
		agentUserList.addAll(((IMap<String , AgentUser>) CacheHelper.getAgentUserCacheBean().getCache()).values(pagingPredicate)) ;
		return agentUserList.size();
	}
	

	@SuppressWarnings("unchecked")
	public static List<AgentStatus> getAgentStatus(String skill , String orgi){
		PagingPredicate<String, AgentStatus> pagingPredicate = new PagingPredicate<String, AgentStatus>(  new SqlPredicate( "orgi = '" + orgi +"'") , 100 ) ;
		
		if(!StringUtils.isBlank(skill)) {
			pagingPredicate = new PagingPredicate<String, AgentStatus>(  new SqlPredicate( "skill = '"+skill+"' AND orgi = '" + orgi +"'") , 100 ) ;
		}
		List<AgentStatus> agentList = new ArrayList<AgentStatus>();
		agentList.addAll(((IMap<String , AgentStatus>) CacheHelper.getAgentStatusCacheBean().getCache()).values(pagingPredicate)) ;
		return agentList;
	}
	/**
	 * 为坐席批量分配用户
	 * @param agentStatus
	 */
	@SuppressWarnings("unchecked")
	public static void allotAgent(String agentno , String orgi){
		AgentStatus agentStatus = (AgentStatus) CacheHelper.getAgentStatusCacheBean().getCacheObject(agentno, orgi) ;
		List<AgentUser> agentStatusList = new ArrayList<AgentUser>();
		PagingPredicate<String, AgentUser> pagingPredicate = null ;
		if(agentStatus!=null && !StringUtils.isBlank(agentStatus.getSkill())){
			pagingPredicate = new PagingPredicate<String, AgentUser>(  new SqlPredicate( "status = 'inquene' AND ((agent = null AND skill = null) OR (skill = '" + agentStatus.getSkill() + "' AND agent = null) OR agent = '"+agentno+"') AND orgi = '" + orgi +"'") , 10 );
		}else{
			pagingPredicate = new PagingPredicate<String, AgentUser>(  new SqlPredicate( "status = 'inquene' AND ((agent = null AND skill = null) OR agent = '"+agentno+"') AND orgi = '" + orgi +"'") , 10 );
		}
		agentStatusList.addAll(((IMap<String , AgentUser>) CacheHelper.getAgentUserCacheBean().getCache()).values(pagingPredicate)) ;
		for(AgentUser agentUser : agentStatusList){
			SessionConfig sessionConfig = ServiceQuene.initSessionConfig(orgi) ;
			long maxusers = sessionConfig!=null ? sessionConfig.getMaxuser() : UKDataContext.AGENT_STATUS_MAX_USER ;
			if(agentStatus != null && agentStatus.getUsers() < maxusers){		//坐席未达到最大咨询访客数量
				CacheHelper.getAgentUserCacheBean().delete(agentUser.getUserid(), orgi);		//从队列移除，进入正在处理的队列， 避免使用 分布式锁
				try{
					AgentService agentService = processAgentService(agentStatus, agentUser, orgi) ;

					MessageOutContent outMessage = new MessageOutContent() ;
					outMessage.setMessage(ServiceQuene.getSuccessMessage(agentService , agentUser.getChannel(),orgi));
					outMessage.setMessageType(UKDataContext.MediaTypeEnum.TEXT.toString());
					outMessage.setCalltype(UKDataContext.CallTypeEnum.IN.toString());
					outMessage.setNickName(agentStatus.getUsername());
					outMessage.setCreatetime(UKTools.dateFormate.format(new Date()));

					if(!StringUtils.isBlank(agentUser.getUserid())){
						OutMessageRouter router = null ; 
						router  = (OutMessageRouter) UKDataContext.getContext().getBean(agentUser.getChannel()) ;
						if(router!=null){
							router.handler(agentUser.getUserid(), UKDataContext.MessageTypeEnum.STATUS.toString(), agentUser.getAppid(), outMessage);
						}
					}

					NettyClients.getInstance().sendAgentEventMessage(agentService.getAgentno(), UKDataContext.MessageTypeEnum.NEW.toString(), agentUser);
				}catch(Exception ex){
					ex.printStackTrace();
				}
			}else{
				break ;
			}
		}
		publishMessage(orgi , "agent" , "success" , agentno);
	}
	
	/**
	 * 为坐席批量分配用户
	 * @param agentStatus
	 * @throws Exception 
	 */
	public static void serviceFinish(AgentUser agentUser , String orgi , String endby) throws Exception{
		if(agentUser!=null){
			AgentStatus agentStatus = null;
			if(UKDataContext.AgentUserStatusEnum.INSERVICE.toString().equals(agentUser.getStatus()) && agentUser.getAgentno()!=null){
				agentStatus = (AgentStatus) CacheHelper.getAgentStatusCacheBean().getCacheObject(agentUser.getAgentno(), orgi) ;
			}
			CacheHelper.getAgentUserCacheBean().delete(agentUser.getUserid(), orgi);
			
			AgentUserRepository agentUserRepository = UKDataContext.getContext().getBean(AgentUserRepository.class) ;
			
			AgentUser agentUseDataBean = agentUserRepository.findByIdAndOrgi(agentUser.getId() , agentUser.getOrgi()) ;
			SessionConfig sessionConfig = ServiceQuene.initSessionConfig(orgi) ;
			if(agentUseDataBean!=null){
				agentUseDataBean.setStatus(UKDataContext.AgentUserStatusEnum.END.toString()) ;
				if(agentUser.getServicetime()!=null){
					agentUseDataBean.setSessiontimes(System.currentTimeMillis() - agentUser.getServicetime().getTime()) ;
				}
				
				agentUserRepository.save(agentUseDataBean) ;
				
				/**
				 * 更新OnlineUser对象，变更为服务中，不可邀请 , WebIM渠道专用
				 */
				if(UKDataContext.ChannelTypeEnum.WEBIM.toString().equals(agentUser.getChannel())){
					OnlineUserRepository onlineUserRes = UKDataContext.getContext().getBean(OnlineUserRepository.class) ;
					List<OnlineUser> onlineUserList = onlineUserRes.findByUseridAndOrgi(agentUser.getUserid() , agentUser.getOrgi());
					if (onlineUserList.size() > 0) {
						OnlineUser onlineUser = onlineUserList.get(0) ;
						onlineUser.setInvitestatus(UKDataContext.OnlineUserInviteStatus.DEFAULT.toString());
						onlineUserRes.save(onlineUser) ;
					}
				}
			}
			
			AgentServiceRepository agentServiceRes = UKDataContext.getContext().getBean(AgentServiceRepository.class) ;
			AgentService service = 	null ;
			if(!StringUtils.isBlank(agentUser.getAgentserviceid())){
				service = 	agentServiceRes.findByIdAndOrgi(agentUser.getAgentserviceid() , agentUser.getOrgi()) ;
			}
			if(service == null) {//当做留言处理
				service = processAgentService(agentStatus, agentUser, orgi , true) ;
			}
			if(service!=null){
				service.setStatus(UKDataContext.AgentUserStatusEnum.END.toString());
				service.setEndtime(new Date());
				service.setEndby(endby);
				if(service.getServicetime()!=null){
					service.setSessiontimes(System.currentTimeMillis() - service.getServicetime().getTime());
				}
				AgentUserTaskRepository agentUserTaskRes = UKDataContext.getContext().getBean(AgentUserTaskRepository.class) ;
				List<AgentUserTask> agentUserTaskList = agentUserTaskRes.findByIdAndOrgi(agentUser.getId() , agentUser.getOrgi()) ;
				if(agentUserTaskList.size() > 0){
					AgentUserTask agentUserTask = agentUserTaskList.get(0) ;
					service.setAgentreplyinterval(agentUserTask.getAgentreplyinterval());
					service.setAgentreplytime(agentUserTask.getAgentreplytime());
					service.setAvgreplyinterval(agentUserTask.getAvgreplyinterval());
					service.setAvgreplytime(agentUserTask.getAvgreplytime());

					service.setUserasks(agentUserTask.getUserasks());
					service.setAgentreplys(agentUserTask.getAgentreplys());
					
					service.setFirstreplytime(agentUserTask.getFirstreplytime());
				}


				/**
				 * 启用了质检任务，开启质检
				 */
				 if(sessionConfig.isQuality() && service.getUserasks() > 0) {	//开启了质检，并且是有效对话
					 service.setQualitystatus(UKDataContext.QualityStatus.NODIS.toString());	//未分配质检任务
				 }else {
					 service.setQualitystatus(UKDataContext.QualityStatus.NO.toString());	//未开启质检 或无效对话无需质检
				 }
				 agentServiceRes.save(service) ;
			}
			
			
			if(agentStatus!=null){
				NettyClients.getInstance().sendAgentEventMessage(agentUser.getAgentno(), UKDataContext.MessageTypeEnum.END.toString(), agentUser);
			}
			
			OutMessageRouter router = null ; 
    		router  = (OutMessageRouter) UKDataContext.getContext().getBean(agentUser.getChannel()) ;
    		if(router!=null){
    			MessageOutContent outMessage = new MessageOutContent() ;
				outMessage.setMessage(ServiceQuene.getServiceFinishMessage(agentUser.getChannel(),orgi));
				outMessage.setMessageType(UKDataContext.AgentUserStatusEnum.END.toString());
				outMessage.setCalltype(UKDataContext.CallTypeEnum.IN.toString());
				if(agentStatus!=null){
					outMessage.setNickName(agentStatus.getUsername());
				}else{
					outMessage.setNickName(agentUser.getUsername());
				}
				outMessage.setCreatetime(UKTools.dateFormate.format(new Date()));
				outMessage.setAgentserviceid(agentUser.getAgentserviceid());
				
    			router.handler(agentUser.getUserid(), UKDataContext.MessageTypeEnum.STATUS.toString(), agentUser.getAppid(), outMessage);
    		}
			
    		if(agentStatus!=null){
				updateAgentStatus(agentStatus  , agentUser , orgi , false) ;
				
				long maxusers = sessionConfig!=null ? sessionConfig.getMaxuser() : UKDataContext.AGENT_STATUS_MAX_USER ;
				if(agentStatus.getUsers() < maxusers){
					allotAgent(agentStatus.getAgentno(), orgi);
				}
			}
			publishMessage(orgi, "end" , "success" ,agentUser!=null ? agentUser.getId() : null);
		}
	}
	
	/**
	 * 更新坐席当前服务中的用户状态，需要分布式锁
	 * @param agentStatus
	 * @param agentUser
	 * @param orgi
	 */
	public synchronized static void updateAgentStatus(AgentStatus agentStatus , AgentUser agentUser , String orgi , boolean in){
		int users = getAgentUsers(agentStatus.getAgentno(), orgi) ;
		Lock lock = CacheHelper.getAgentStatusCacheBean().getLock("LOCK", orgi) ;
		lock.lock();
		try{
			agentStatus.setUsers(users);
			agentStatus.setUpdatetime(new Date());
			CacheHelper.getAgentStatusCacheBean().put(agentStatus.getAgentno(), agentStatus, orgi);
		}finally{
			lock.unlock();
		}
	}
	
	public static void publishMessage(String orgi ,String worktype,String workresult ,String dataid){
		/**
		 * 坐席状态改变，通知监测服务
		 */
		AgentReport agentReport = ServiceQuene.getAgentReport(orgi) ;
		AgentReportRepository agentReportRes = UKDataContext.getContext().getBean(AgentReportRepository.class) ;
		if(agentReportRes!=null) {
			agentReport.setOrgi(orgi);
			agentReport.setWorktype(worktype);
			agentReport.setWorkresult(workresult);
			agentReport.setDataid(dataid);
			
			agentReportRes.save(agentReport) ;
		}
		NettyClients.getInstance().published("agentNamespace", "status", agentReport);
	}
	/**
	 * 
	 * @param agent  坐席
	 * @param skill	 技能组
	 * @param userid 用户ID  
	 * @param status	工作状态
	 * @param worktype  类型 ： 语音OR 文本
	 * @param orgi
	 * @param lasttime
	 */
	public static void recordAgentStatus(String agent,String username,String extno,String skill,boolean admin,String userid, String status ,String current ,String worktype ,String orgi , Date lasttime){
		WorkMonitorRepository workMonitorRes = UKDataContext.getContext().getBean(WorkMonitorRepository.class) ;
		WorkMonitor workMonitor = new WorkMonitor() ;
		if(!StringUtils.isBlank(agent) && !StringUtils.isBlank(status)) {
			workMonitor.setAgent(agent);
			workMonitor.setAgentno(agent);
			workMonitor.setStatus(status);
			workMonitor.setAdmin(admin);
			workMonitor.setUsername(username);
			workMonitor.setExtno(extno);
			workMonitor.setWorktype(worktype);
			if(lasttime!=null) {
				workMonitor.setDuration((int) (System.currentTimeMillis() - lasttime.getTime())/1000);
			}
			if(status.equals(UKDataContext.AgentStatusEnum.BUSY.toString())) {
				workMonitor.setBusy(true);
			}
			if(status.equals(UKDataContext.AgentStatusEnum.READY.toString())) {
				int count = workMonitorRes.countByAgentAndDatestrAndStatusAndOrgi(agent,UKTools.simpleDateFormat.format(new Date()), UKDataContext.AgentStatusEnum.READY.toString(), orgi) ;
				if(count == 0) {
					workMonitor.setFirsttime(true);
				}
			}
			if(current.equals(UKDataContext.AgentStatusEnum.NOTREADY.toString())) {
				List<WorkMonitor> workMonitorList = workMonitorRes.findByOrgiAndAgentAndDatestrAndFirsttime(orgi , agent , UKTools.simpleDateFormat.format(new Date()) , true);
				if(workMonitorList.size() > 0) {
					WorkMonitor firstWorkMonitor = workMonitorList.get(0) ;
					if(firstWorkMonitor.getFirsttimes() == 0) {
						firstWorkMonitor.setFirsttimes((int) (System.currentTimeMillis() - firstWorkMonitor.getCreatetime().getTime())/1000);
						workMonitorRes.save(firstWorkMonitor) ;
					}
				}
			}
			workMonitor.setCreatetime(new Date());
			workMonitor.setDatestr(UKTools.simpleDateFormat.format(new Date()));
			
			workMonitor.setName(agent);
			workMonitor.setOrgi(orgi);
			workMonitor.setSkill(skill);
			workMonitor.setUserid(userid);
			
			workMonitorRes.save(workMonitor) ;
		}
	}
	/**
	 * 为用户分配坐席
	 * @param agentUser
	 */
	@SuppressWarnings("unchecked")
	public static AgentService allotAgent(AgentUser agentUser , String orgi){
		/**
		 * 查询条件，当前在线的 坐席，并且 未达到最大 服务人数的坐席
		 */
		SessionConfig config = initSessionConfig(orgi) ;
		List<AgentStatus> agentStatusList = new ArrayList<AgentStatus>();
		PagingPredicate<String, AgentStatus> pagingPredicate = null ;
		AgentStatus agentStatus = null ;
		/**
		 * 处理ACD 的 技能组请求和 坐席请求
		 */
		if(!StringUtils.isBlank(agentUser.getAgentno()) && config.isEnablersession() && CacheHelper.getAgentStatusCacheBean().getCacheObject(agentUser.getAgentno(), agentUser.getOrgi()) != null){
			agentStatusList.add((AgentStatus) CacheHelper.getAgentStatusCacheBean().getCacheObject(agentUser.getAgentno(), agentUser.getOrgi())) ;
		}
		if(agentStatusList.size() == 0) {
			if(!StringUtils.isBlank(agentUser.getAgent()) && CacheHelper.getAgentStatusCacheBean().getCacheObject(agentUser.getAgent(), agentUser.getOrgi()) != null){
				pagingPredicate = new PagingPredicate<String, AgentStatus>(  new SqlPredicate( " busy = false AND agentno = '" + agentUser.getAgent()+"' AND orgi = '" + orgi +"'") , 1 );
			}else if(!StringUtils.isBlank(agentUser.getSkill())){
				pagingPredicate = new PagingPredicate<String, AgentStatus>(  new SqlPredicate( " busy = false AND skill = '" + agentUser.getSkill()+"' AND orgi = '" + orgi +"'") , 1 );
			}else{
				pagingPredicate = new PagingPredicate<String, AgentStatus>(  new SqlPredicate( " busy = false AND orgi = '" + orgi +"'") , 1 );
			}
			agentStatusList.addAll(((IMap<String , AgentStatus>) CacheHelper.getAgentStatusCacheBean().getCache()).values(pagingPredicate)) ;
		}
		AgentService agentService = null ;	//放入缓存的对象
		if(agentStatusList!=null && agentStatusList.size() > 0){
			agentStatus = agentStatusList.get(0) ;
			if(agentStatus !=null && config !=null && agentStatus.getUsers() >= config.getMaxuser()){
				agentStatus = null ;
				/**
				 * 判断当前有多少人排队中 ， 分三种情况：1、请求技能组的，2、请求坐席的，3，默认请求的
				 * 
				 */
				
			}
		}
		try {
			agentService = processAgentService(agentStatus, agentUser, orgi) ;
			if(agentService!=null && agentService.getStatus().equals(UKDataContext.AgentUserStatusEnum.INQUENE.toString())){
				agentService.setQueneindex(getQueneIndex(agentUser.getAgent(), orgi, agentUser.getSkill()));
			}
			
		}catch(Exception ex){
			ex.printStackTrace(); 
		}
		publishMessage(orgi, "user" , agentService!=null && agentService.getStatus().equals(UKDataContext.AgentUserStatusEnum.INSERVICE.toString()) ? "inservice" : "inquene" , agentUser.getId());
		return agentService;
	}
	/**
	 * 邀请访客进入当前对话，如果当前操作的 坐席是已就绪状态，则直接加入到当前坐席的 对话列表中，如果未登录，则分配给其他坐席
	 * @param agentno
	 * @param agentUser
	 * @param orgi
	 * @return
	 * @throws Exception
	 */
	public static AgentService allotAgentForInvite(String agentno , AgentUser agentUser , String orgi) throws Exception{
		AgentStatus agentStatus = (AgentStatus) CacheHelper.getAgentStatusCacheBean().getCacheObject(agentno, orgi) ;
		AgentService agentService = null ;
		if(agentStatus!=null){
			agentService = processAgentService(agentStatus, agentUser, orgi) ;
			publishMessage(orgi , "invite" , "success" , agentno);
			NettyClients.getInstance().sendAgentEventMessage(agentService.getAgentno(), UKDataContext.MessageTypeEnum.NEW.toString(), agentUser);
		}else{
			agentService = allotAgent(agentUser, orgi) ;
		}
		return agentService;
	}
	
	/**
	 * 为访客 分配坐席， ACD策略，此处 AgentStatus 是建议 的 坐席，  如果启用了  历史服务坐席 优先策略， 则会默认检查历史坐席是否空闲，如果空闲，则分配，如果不空闲，则 分配当前建议的坐席
	 * @param agentStatus
	 * @param agentUser
	 * @param orgi
	 * @return
	 * @throws Exception
	 */
	private static AgentService processAgentService(AgentStatus agentStatus , AgentUser agentUser , String orgi) throws Exception{
		return processAgentService(agentStatus, agentUser, orgi , false) ;
	}
	
	/**
	 * 为访客 分配坐席， ACD策略，此处 AgentStatus 是建议 的 坐席，  如果启用了  历史服务坐席 优先策略， 则会默认检查历史坐席是否空闲，如果空闲，则分配，如果不空闲，则 分配当前建议的坐席
	 * @param agentStatus
	 * @param agentUser
	 * @param orgi
	 * @return
	 * @throws Exception
	 */
	public static AgentService processAiService(AiUser aiUser , String orgi ,String endby) throws Exception{
		AgentService agentService = new AgentService();	//放入缓存的对象
		AgentServiceRepository agentServiceRes  = UKDataContext.getContext().getBean(AgentServiceRepository.class);
		if(!StringUtils.isBlank(aiUser.getAgentserviceid())) {
			agentService = agentServiceRes.findByIdAndOrgi(aiUser.getAgentserviceid(), orgi) ;
			agentService.setEndtime(new Date());
			if(agentService.getServicetime()!=null) {
				agentService.setSessiontimes(System.currentTimeMillis() - agentService.getServicetime().getTime());
			}
			agentService.setStatus(UKDataContext.AgentUserStatusEnum.END.toString());
		}else {
			agentService.setServicetime(new Date());
			agentService.setLogindate(new Date());
			agentService.setOrgi(orgi);
			agentService.setOwner(aiUser.getContextid());
			agentService.setSessionid(aiUser.getSessionid());
			if(aiUser.getIpdata()!=null) {
				agentService.setRegion(aiUser.getIpdata().getRegion());
			}
			
			agentService.setUsername(aiUser.getUsername());
			agentService.setChannel(aiUser.getChannel());
			
			if(!StringUtils.isBlank(aiUser.getContextid())) {
				agentService.setContextid(aiUser.getContextid());
			}else {
				agentService.setContextid(aiUser.getSessionid());
			}
			
			agentService.setUserid(aiUser.getUserid());
			
			agentService.setAiid(aiUser.getAiid());
			agentService.setAiservice(true);
			agentService.setStatus(UKDataContext.AgentUserStatusEnum.INSERVICE.toString());
			
			agentService.setAppid(aiUser.getAppid());
			agentService.setLeavemsg(false);
		}
		if(agentService!=null) {
			agentService.setEndby(endby);
		}
		agentServiceRes.save(agentService) ;
		return agentService ;
	}
	
	/**
	 * 为访客 分配坐席， ACD策略，此处 AgentStatus 是建议 的 坐席，  如果启用了  历史服务坐席 优先策略， 则会默认检查历史坐席是否空闲，如果空闲，则分配，如果不空闲，则 分配当前建议的坐席
	 * @param agentStatus
	 * @param agentUser
	 * @param orgi
	 * @return
	 * @throws Exception
	 */
	private static AgentService processAgentService(AgentStatus agentStatus , AgentUser agentUser , String orgi , boolean finished) throws Exception{
		AgentService agentService = new AgentService();	//放入缓存的对象
		if(!StringUtils.isBlank(agentUser.getAgentserviceid())) {
			agentService.setId(agentUser.getAgentserviceid());
		}
		agentService.setOrgi(orgi);
		
		UKTools.copyProperties(agentUser, agentService); //复制属性
		
		agentService.setChannel(agentUser.getChannel());
		
		agentService.setSessionid(agentUser.getSessionid());
		OnlineUserRepository onlineUserRes = UKDataContext.getContext().getBean(OnlineUserRepository.class) ;
		agentUser.setLogindate(new Date());
		List<OnlineUser> onlineUserList = onlineUserRes.findByUseridAndOrgi(agentUser.getUserid() , agentUser.getOrgi());
		OnlineUser onlineUser = null ;
		if (onlineUserList.size() > 0) {
			onlineUser = onlineUserList.get(0) ;
		}
		
		if(agentStatus!=null){	
			SessionConfig sessionConfig = initSessionConfig(orgi) ;
			
			agentService.setAgent(agentStatus.getAgentno());
			agentService.setSkill(agentUser.getSkill());
			
			if(sessionConfig.isLastagent()){	//启用了历史坐席优先 ， 查找 历史服务坐席
				List<WebIMReport> webIMaggList = UKTools.getWebIMDataAgg(onlineUserRes.findByOrgiForDistinctAgent(orgi, agentUser.getUserid())) ;
				if(webIMaggList.size()>0){
					for(WebIMReport report : webIMaggList){
						if(report.getData().equals(agentStatus.getAgentno())){
							break ;
						}else{
							AgentStatus hisAgentStatus = (AgentStatus) CacheHelper.getAgentStatusCacheBean().getCacheObject(report.getData(), orgi) ;
							if(hisAgentStatus!=null && hisAgentStatus.getUsers() < hisAgentStatus.getMaxusers()){
								agentStatus = hisAgentStatus ;	//变更为 历史服务坐席
								break ;
							}
						}
						
					}
				}
			}
			
			agentUser.setStatus(UKDataContext.AgentUserStatusEnum.INSERVICE.toString());
			agentService.setStatus(UKDataContext.AgentUserStatusEnum.INSERVICE.toString());
			
			agentService.setSessiontype(UKDataContext.AgentUserStatusEnum.INSERVICE.toString());

			agentService.setAgentno(agentStatus.getUserid());
			agentService.setAgentusername(agentStatus.getUsername());	//agent
		}else{
			if(finished == true) {
				agentUser.setStatus(UKDataContext.AgentUserStatusEnum.END.toString());
				agentService.setStatus(UKDataContext.AgentUserStatusEnum.END.toString());
				agentService.setSessiontype(UKDataContext.AgentUserStatusEnum.END.toString());
				if(agentStatus==null) {
					agentService.setLeavemsg(true); //是留言
					agentService.setLeavemsgstatus(UKDataContext.LeaveMsgStatus.NOTPROCESS.toString()); //未处理的留言
				}
			}else {
				agentUser.setStatus(UKDataContext.AgentUserStatusEnum.INQUENE.toString());
				agentService.setStatus(UKDataContext.AgentUserStatusEnum.INQUENE.toString());
				
				agentService.setSessiontype(UKDataContext.AgentUserStatusEnum.INQUENE.toString());
			}
		}
		if(finished || agentStatus!=null) {
			//			agentService.setId(null);
	
			agentService.setAgentuserid(agentUser.getId());
			
			agentService.setInitiator(UKDataContext.ChatInitiatorType.USER.toString());
	
			long waittingtime = 0  ;
			if(agentUser.getWaittingtimestart()!=null){
				waittingtime = System.currentTimeMillis() - agentUser.getWaittingtimestart().getTime() ;
			}else if(agentUser.getCreatetime()!=null){
				waittingtime = System.currentTimeMillis() - agentUser.getCreatetime().getTime() ;
			}
			agentUser.setWaittingtime((int)waittingtime);
	
			agentUser.setServicetime(new Date());
	
			agentService.setOwner(agentUser.getOwner());
			
			agentService.setTimes(0);
			agentUser.setAgentno(agentService.getAgentno());
	
			AgentServiceRepository agentServiceRes = UKDataContext.getContext().getBean(AgentServiceRepository.class) ;
	
			if(!StringUtils.isBlank(agentUser.getName())){
				agentService.setName(agentUser.getName());
			}
			if(!StringUtils.isBlank(agentUser.getPhone())){
				agentService.setPhone(agentUser.getPhone());
			}
			if(!StringUtils.isBlank(agentUser.getEmail())){
				agentService.setEmail(agentUser.getEmail());
			}
			if(!StringUtils.isBlank(agentUser.getResion())){
				agentService.setResion(agentUser.getResion());
			}
	
			if(!StringUtils.isBlank(agentUser.getSkill())) {
				agentService.setAgentskill(agentUser.getSkill());
			}else if(agentStatus!=null) {
				agentService.setAgentskill(agentStatus.getSkill());
			}
	
			agentService.setServicetime(new Date());
			if(agentUser.getCreatetime()!=null){
				agentService.setWaittingtime((int) (System.currentTimeMillis() - agentUser.getCreatetime().getTime()));
				agentUser.setWaittingtime(agentService.getWaittingtime());
			}
			if (onlineUser != null ) {
				agentService.setOsname(onlineUser.getOpersystem());
				agentService.setBrowser(onlineUser.getBrowser());
				agentService.setDataid(onlineUser.getId());		//记录  onlineuser 的id
			}
			agentService.setLogindate(agentUser.getCreatetime());
			agentServiceRes.save(agentService) ;
			agentUser.setAgentserviceid(agentService.getId());
			agentUser.setLastgetmessage(new Date());
			agentUser.setLastmessage(new Date());
		}
		
		agentService.setDataid(agentUser.getId());
		/**
		 * 分配成功以后， 将用户 和坐席的对应关系放入到 缓存
		 */
		/**
		 * 将 AgentUser 放入到 当前坐席的 服务队列
		 */
		AgentUserRepository agentUserRepository = UKDataContext.getContext().getBean(AgentUserRepository.class) ;
		
		/**
		 * 更新OnlineUser对象，变更为服务中，不可邀请
		 */
		
		if(onlineUser!=null){
			onlineUser.setInvitestatus(UKDataContext.OnlineUserInviteStatus.INSERV.toString());
			onlineUserRes.save(onlineUser) ;
		}
		
		/**
		 * 
		 */
		agentUserRepository.save(agentUser);

		CacheHelper.getAgentUserCacheBean().put(agentUser.getUserid(), agentUser , UKDataContext.SYSTEM_ORGI) ;
		
		if(agentStatus != null){
			updateAgentStatus(agentStatus  , agentUser , orgi , true) ;
		}
		
		return agentService ;
	}
	
	public static AgentUser deleteAgentUser(AgentUser agentUser, String orgi ,String endby)
			throws Exception {
		if (agentUser!=null) {
			if (!UKDataContext.AgentUserStatusEnum.END.toString().equals(
					agentUser.getStatus())) {
				serviceFinish(agentUser, orgi , endby);
			}
			if(!StringUtils.isBlank(agentUser.getId())){
				AgentUserRepository agentUserRes = UKDataContext.getContext().getBean(AgentUserRepository.class) ;
				agentUser = agentUserRes.findByIdAndOrgi(agentUser.getId(), orgi);
				if(agentUser!=null){
					agentUserRes.delete(agentUser);
				}
			}
		}
		return agentUser;
	}
	
	
	/**
	 * 
	 * @param agentStatus
	 * @return
	 */
	public static String getSuccessMessage(AgentService agentService ,String channel,String orgi){
		String queneTip = "<span id='agentno'>"+agentService.getAgentusername()+"</span>" ;
		if(!UKDataContext.ChannelTypeEnum.WEBIM.toString().equals(channel)){
			queneTip = agentService.getAgentusername() ;
		}
		SessionConfig sessionConfig = initSessionConfig(orgi) ;
		String successMsg = "坐席分配成功，"+queneTip+"为您服务。"  ;
		if(!StringUtils.isBlank(sessionConfig.getSuccessmsg())){
			successMsg = sessionConfig.getSuccessmsg().replaceAll("\\{agent\\}", queneTip) ;
		}
		return successMsg ;
	}
	/**
	 * 转接提示信息
	 * @param agentStatus
	 * @return
	 */
	public static String getTransMessage(AgentService agentService ,String channel,String orgi){
		String queneTip = "<span id='agentno'>"+agentService.getAgentusername()+"</span>" ;
		if(!UKDataContext.ChannelTypeEnum.WEBIM.toString().equals(channel)){
			queneTip = agentService.getAgentusername() ;
		}
		SessionConfig sessionConfig = initSessionConfig(orgi) ;
		String successMsg = "已为你转接给坐席"+queneTip;
		if(!StringUtils.isBlank(sessionConfig.getTransmsg())){
			successMsg = sessionConfig.getTransmsg().replaceAll("\\{agent\\}", queneTip) ;
		}
		return successMsg ;
	}
	/**
	 * 
	 * @param agentStatus
	 * @return
	 */
	public static String getServiceFinishMessage(String channel,String orgi){
		SessionConfig sessionConfig = initSessionConfig(orgi) ;
		String queneTip = "坐席已断开和您的对话" ;
		if(!StringUtils.isBlank(sessionConfig.getFinessmsg())){
			queneTip = sessionConfig.getFinessmsg();
		}
		return queneTip ;
	}
	
	public static String getNoAgentMessage(int queneIndex , String channel,String orgi){
		if(queneIndex < 0){
			queneIndex = 0 ;
		}
		String queneTip = "<span id='queneindex'>"+queneIndex+"</span>" ;
		if(!UKDataContext.ChannelTypeEnum.WEBIM.toString().equals(channel)){
			queneTip = String.valueOf(queneIndex) ;
		}
		SessionConfig sessionConfig = initSessionConfig(orgi) ;
		String noAgentTipMsg = "坐席全忙，已进入等待队列，您也可以在其他时间再来咨询。"  ;
		if(!StringUtils.isBlank(sessionConfig.getNoagentmsg())){
			noAgentTipMsg = sessionConfig.getNoagentmsg().replaceAll("\\{num\\}", queneTip) ;
		}
		return noAgentTipMsg;
	}
	public static String getQueneMessage(int queneIndex , String channel,String orgi){
		
		String queneTip = "<span id='queneindex'>"+queneIndex+"</span>" , indexTip = "<span id='queneindex'>"+(queneIndex + 1)+"</span>";
		if(!UKDataContext.ChannelTypeEnum.WEBIM.toString().equals(channel)){
			queneTip = String.valueOf(queneIndex) ;
			indexTip = String.valueOf(queneIndex+1) ;
		}
		SessionConfig sessionConfig = initSessionConfig(orgi) ;
		String agentBusyTipMsg = "正在排队，请稍候,在您之前，还有  "+queneTip+" 位等待用户。"  ;
		if(!StringUtils.isBlank(sessionConfig.getAgentbusymsg())){
			agentBusyTipMsg = sessionConfig.getAgentbusymsg().replaceAll("\\{num\\}", queneTip).replaceAll("\\{index\\}", indexTip) ;
		}
		return agentBusyTipMsg;
	}
	
	/**
	 * 坐席离线 
	 * @param userid
	 * @param status
	 */
	public static void deleteAgentStatus(String userid , String orgi , boolean isAdmin) {
		AgentStatusRepository agentStatusRes = UKDataContext.getContext().getBean(AgentStatusRepository.class) ;
		List<AgentStatus> agentStatusList = agentStatusRes.findByAgentnoAndOrgi(userid , orgi);
		for(AgentStatus agentStatus : agentStatusList){
			ServiceQuene.recordAgentStatus(agentStatus.getAgentno(),agentStatus.getUsername() , agentStatus.getAgentno(), agentStatus.getSkill(),isAdmin, agentStatus.getAgentno(), agentStatus.isBusy() ? UKDataContext.AgentStatusEnum.BUSY.toString():UKDataContext.AgentStatusEnum.NOTREADY.toString(), UKDataContext.AgentStatusEnum.NOTREADY.toString(), UKDataContext.AgentWorkType.MEIDIACHAT.toString() , agentStatus.getOrgi() , agentStatus.getUpdatetime());
			agentStatusRes.delete(agentStatus);
		}
    	CacheHelper.getAgentStatusCacheBean().delete(userid,orgi);
    	ServiceQuene.publishMessage(orgi , "agent" , "leave" , userid);
	}
}
