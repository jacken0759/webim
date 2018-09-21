package com.ukefu.util.rpc;

import java.io.Serializable;

public class RPCDataBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id ;
	
	private String event ;
	
	private Object data ;
	
	public RPCDataBean(String id , String event , Object data) {
		this.id = id ;
		this.event = event ;
		this.data = data ;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
