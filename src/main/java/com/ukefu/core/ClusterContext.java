package com.ukefu.core;

public class ClusterContext {
	private static boolean master = false ;
	
	public static void setMaster(boolean master) {
		ClusterContext.master = master ;
	}
	
	public static boolean isMaster() {
		return master ;
	}
}
