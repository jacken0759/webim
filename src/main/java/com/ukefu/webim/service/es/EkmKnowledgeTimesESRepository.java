package com.ukefu.webim.service.es;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ukefu.webim.web.model.EkmKnowledgeTimes;

public abstract interface EkmKnowledgeTimesESRepository 
{
	
	public abstract List<EkmKnowledgeTimes> findByKbidAndVersionAndOrgi(String kbid ,int version , String orgi );
	
	public abstract Page<EkmKnowledgeTimes> findByOrgi(String orgi ,Pageable pageable);
	
	public void delete(List<EkmKnowledgeTimes> ekmKnowledgeTimes) ;
	
}

