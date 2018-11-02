package com.ukefu.core;

public class ClusterContext implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static ClusterContext instance = new ClusterContext();
	private boolean master = false ;
	private String host ;
	private int port ;
	
	public static ClusterContext getInstance() {
		return instance ;
	}
	
	public void setMaster(boolean master) {
		this.master = master ;
		System.out.println("选举产生了新的Master服务 ： " +  this.master + " host:"+this.getHost() +" port:" +this.getPort() );
	}
	
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public boolean isMaster() {
		return master;
	}
}
