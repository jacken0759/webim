package com.ukefu.webim.service.quene;

import org.apache.commons.lang.StringUtils;

import com.hazelcast.mapreduce.KeyPredicate;
import com.ukefu.core.UKDataContext;
import com.ukefu.webim.service.cache.CacheHelper;
import com.ukefu.webim.web.model.StatusEvent;

@SuppressWarnings("deprecation")
public class CallAgentFilter implements KeyPredicate<String>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1236581634096258855L;
	private String agent ;
	/**
	 * 
	 */
	public CallAgentFilter(String agent){
		this.agent = agent ;
	}
	public boolean evaluate(String key) {
		StatusEvent statusEvent = (StatusEvent) CacheHelper.getCallCenterCacheBean().getCacheObject(key, UKDataContext.SYSTEM_ORGI);
		return agent!=null && !StringUtils.isBlank(agent) && agent.equals(statusEvent.getAgent());
	}
}