package com.ukefu.webim.service.es;

import java.util.List;

import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.delete.DeleteRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Component;

import com.ukefu.core.UKDataContext;
import com.ukefu.webim.web.model.EkmKnowledgeCollect;

@Component
public class EkmKnowledgeCollectRepositoryImpl {

	private ElasticsearchTemplate elasticsearchTemplate;
	
	@Autowired
	public void setElasticsearchTemplate(ElasticsearchTemplate elasticsearchTemplate) {
		this.elasticsearchTemplate = elasticsearchTemplate;
    }
	
	public void delete(List<EkmKnowledgeCollect> ekmKnowledgeCollectList){
		BulkRequestBuilder bulkRequest = elasticsearchTemplate.getClient().prepareBulk();  
	    if(ekmKnowledgeCollectList != null && ekmKnowledgeCollectList.size()>0) {
		    for(EkmKnowledgeCollect collect : ekmKnowledgeCollectList){  
		        bulkRequest.add(new DeleteRequest().index(UKDataContext.SYSTEM_INDEX).type("uk_ekm_kb_collect").id(collect.getId()).routing(collect.getKbid()));  
		    }  
		    bulkRequest.get();  
	    }
	}
}
