package com.ukefu.webim.service.quene;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.hazelcast.query.Predicate;
import com.ukefu.core.UKDataContext;
import com.ukefu.webim.web.model.CallOutNames;

public class ForecastCallOutFilterPredicate implements Predicate<String, CallOutNames> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1236581634096258855L;
	private String orgi ;
	private String ownerforecast = null;
	/**
	 * 
	 */
	public ForecastCallOutFilterPredicate(String orgi){
		this.orgi = orgi ;
	}
	public ForecastCallOutFilterPredicate(String orgi,String ownerforecast){
		this.orgi = orgi ;
		this.ownerforecast = ownerforecast;
	}
	public boolean apply(Map.Entry<String, CallOutNames> mapEntry) {
		boolean flag =  mapEntry.getValue()!=null && !StringUtils.isBlank(orgi) && orgi.equals(mapEntry.getValue().getOrgi()) && UKDataContext.CallOutType.FORECAST.toString().equals(mapEntry.getValue().getCalltype());
		if(StringUtils.isNotBlank(ownerforecast)) {
			return flag && ownerforecast.equals(mapEntry.getValue().getOwnerforecast());
		}
		return flag;
	}
}