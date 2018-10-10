package com.ukefu.webim.service.quene;

import org.apache.commons.lang.StringUtils;

import com.hazelcast.mapreduce.KeyPredicate;
import com.ukefu.util.freeswitch.model.CallCenterAgent;
import com.ukefu.webim.service.cache.CacheHelper;

@SuppressWarnings("deprecation")
public class CallCenterAgentOrgiFilter implements KeyPredicate<String>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1236581634096258855L;
	private String orgi ;
	/**
	 * 
	 */
	public CallCenterAgentOrgiFilter(String orgi){
		this.orgi = orgi ;
	}
	public boolean evaluate(String key) {
		CallCenterAgent agent = (CallCenterAgent) CacheHelper.getCallCenterAgentCacheBean().getCacheObject(key, orgi);
		return agent!=null && !StringUtils.isBlank(orgi) && orgi.equals(agent.getOrgi());
	}
}