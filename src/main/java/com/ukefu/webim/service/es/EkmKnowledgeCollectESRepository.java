package com.ukefu.webim.service.es;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.ukefu.webim.web.model.EkmKnowledgeCollect;

public abstract interface EkmKnowledgeCollectESRepository  extends ElasticsearchRepository<EkmKnowledgeCollect, String>
{
	
	public abstract List<EkmKnowledgeCollect> findByCreaterAndOrgi(String creater , String orgi );
	
	public abstract List<EkmKnowledgeCollect> findByIdAndOrgi(String id , String orgi );
	
	public abstract Page<EkmKnowledgeCollect> findByCreaterAndStatusAndOrgi(String creater , String status, String orgi, Pageable pageable );
	
	public abstract List<EkmKnowledgeCollect> findByStatusAndCreaterAndOrgi(String status , String creater, String orgi);
	
	public abstract Page<EkmKnowledgeCollect> findByKnowledgeowerAndStatusAndOrgi(String knowledgeower , String status, String orgi, Pageable pageable );
	
	public abstract List<EkmKnowledgeCollect> findByKnowledgeowerAndStatusAndOrgi(String knowledgeower , String status, String orgi);
	
	public abstract List<EkmKnowledgeCollect> findByCreaterAndStatusAndFolderidAndOrgi(String creater , String status,String folderid , String orgi);
	
	public abstract List<EkmKnowledgeCollect> findByCreaterAndStatusAndOrgi(String creater , String status, String orgi);
	
	public abstract EkmKnowledgeCollect findByCreaterAndKnowledgeidAndStatusAndOrgi(String creater , String knowledgeid, String status, String orgi );
	
	public abstract EkmKnowledgeCollect findByCreaterAndKbidAndStatusAndOrgi(String creater , String kbid, String status, String orgi );
}

