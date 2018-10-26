package com.ukefu.webim.service.es;

import java.util.List;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ukefu.webim.web.model.WorkOrders;

public interface WorkOrdersEsCommonRepository {
	
	public Page<WorkOrders> findByQuery(BoolQueryBuilder boolQueryBuilder , boolean haschild , boolean includeDeleteData  ,String q , String creater , Pageable page) ;
	
	public List<WorkOrders> findByContactsidAndOrgi(String contactsid , String creater , String orgi) ;
	
	public Page<WorkOrders> countByCon(BoolQueryBuilder boolQueryBuilder , String field,boolean includeDeleteData , String q, int p,int ps);
	
	public Page<WorkOrders> findByAllQuery(BoolQueryBuilder boolQueryBuilder , boolean datastatus , String orgi, Pageable page);
	
	public WorkOrders findByIdAndOrgi(String id , String orgi) ;
	
	public List<WorkOrders> findByTemplateidAndQualitystatus(String templateid,String qualitystatus) ;
}
