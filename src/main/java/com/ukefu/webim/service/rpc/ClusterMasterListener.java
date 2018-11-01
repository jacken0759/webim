package com.ukefu.webim.service.rpc;

import org.apache.commons.lang3.StringUtils;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.Message;
import com.hazelcast.core.MessageListener;
import com.ukefu.core.ClusterContext;
import com.ukefu.core.UKDataContext;
import com.ukefu.util.rpc.RPCDataBean;

public class ClusterMasterListener implements MessageListener<Object>{
	@Override
    public void onMessage(Message<Object> message) {
		RPCDataBean rpcDataBean = (RPCDataBean) message.getMessageObject() ;
		HazelcastInstance hazelcastInstance = UKDataContext.getContext().getBean(HazelcastInstance.class) ;
		if(hazelcastInstance!=null && rpcDataBean!=null && !StringUtils.isBlank(rpcDataBean.getHost())) {
			if(hazelcastInstance.getCluster().getLocalMember().getStringAttribute("id").equals(rpcDataBean.getId())) {
				ClusterContext.setMaster(true);
			}else {
				ClusterContext.setMaster(false);
			}
		}
    }
}
