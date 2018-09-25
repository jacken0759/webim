package com.ukefu.webim.service.es;

import java.util.List;

import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.HasParentQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Component;

import com.ukefu.core.UKDataContext;
import com.ukefu.webim.web.model.EkmKnowledgeCollect;
import com.ukefu.webim.web.model.EkmKnowledgeTimes;

@Component
public class EkmKnowledgeCollectRepositoryImpl implements EkmKnowledgeCollectESRepository{

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

	@Override
	public List<EkmKnowledgeCollect> findByCreaterAndOrgi(String creater,
			String orgi) {
		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
		BoolQueryBuilder bq = QueryBuilders.boolQuery() ; 
		bq.must(QueryBuilders.termQuery("creater", creater)) ;
		bq.must(QueryBuilders.termQuery("orgi", orgi)) ;
		boolQueryBuilder.must(bq); 
		
		NativeSearchQueryBuilder searchQueryBuilder = new NativeSearchQueryBuilder().withQuery(boolQueryBuilder) ;
		Page<EkmKnowledgeCollect> knowledgeCollectList = null ;
		if(elasticsearchTemplate.indexExists(EkmKnowledgeCollect.class)){
			knowledgeCollectList = elasticsearchTemplate.queryForPage(searchQueryBuilder.build() , EkmKnowledgeCollect.class ) ;
	    }
		
		return knowledgeCollectList.getContent();
	}

	@Override
	public List<EkmKnowledgeCollect> findByIdAndOrgi(String id, String orgi) {
		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
		BoolQueryBuilder bq = QueryBuilders.boolQuery() ; 
		bq.must(QueryBuilders.termQuery("id", id)) ;
		bq.must(QueryBuilders.termQuery("orgi", orgi)) ;
		boolQueryBuilder.must(bq); 
		
		NativeSearchQueryBuilder searchQueryBuilder = new NativeSearchQueryBuilder().withQuery(boolQueryBuilder) ;
		Page<EkmKnowledgeCollect> knowledgeCollectList = null ;
		if(elasticsearchTemplate.indexExists(EkmKnowledgeCollect.class)){
			knowledgeCollectList = elasticsearchTemplate.queryForPage(searchQueryBuilder.build() , EkmKnowledgeCollect.class ) ;
	    }
		
		return knowledgeCollectList.getContent();
	}

	@Override
	public Page<EkmKnowledgeCollect> findByCreaterAndStatusAndOrgi(
			String creater, String status, String orgi, Pageable pageable) {
		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
		boolQueryBuilder.must(QueryBuilders.termQuery("orgi", orgi)) ;
		HasParentQueryBuilder hasParentQueryBuilder=QueryBuilders.hasParentQuery("uk_ekm_kb_master",QueryBuilders.termQuery("datastatus", false));
		NativeSearchQueryBuilder searchQueryBuilder = new NativeSearchQueryBuilder().withQuery(boolQueryBuilder).withSort(new FieldSortBuilder("createtime").unmappedType("date").order(SortOrder.DESC)).withQuery(hasParentQueryBuilder);
		searchQueryBuilder.withPageable(pageable) ;
		Page<EkmKnowledgeCollect> knowledgeCollectList = null ;
		if(elasticsearchTemplate.indexExists(EkmKnowledgeTimes.class)){
			knowledgeCollectList = elasticsearchTemplate.queryForPage(searchQueryBuilder.build() , EkmKnowledgeCollect.class ) ;
	    }
		
		return knowledgeCollectList;
	}

	@Override
	public List<EkmKnowledgeCollect> findByStatusAndCreaterAndOrgi(
			String status, String creater, String orgi) {
		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
		BoolQueryBuilder bq = QueryBuilders.boolQuery() ; 
		bq.must(QueryBuilders.termQuery("status", status)) ;
		bq.must(QueryBuilders.termQuery("orgi", orgi)) ;
		bq.must(QueryBuilders.termQuery("creater", creater)) ;
		boolQueryBuilder.must(bq); 
		HasParentQueryBuilder hasParentQueryBuilder=QueryBuilders.hasParentQuery("uk_ekm_kb_master",QueryBuilders.termQuery("datastatus", false));
		NativeSearchQueryBuilder searchQueryBuilder = new NativeSearchQueryBuilder().withQuery(boolQueryBuilder).withQuery(hasParentQueryBuilder) ;
		Page<EkmKnowledgeCollect> knowledgeCollectList = null ;
		if(elasticsearchTemplate.indexExists(EkmKnowledgeCollect.class)){
			knowledgeCollectList = elasticsearchTemplate.queryForPage(searchQueryBuilder.build() , EkmKnowledgeCollect.class ) ;
	    }
		
		return knowledgeCollectList.getContent();
	}

	@Override
	public Page<EkmKnowledgeCollect> findByKnowledgeowerAndStatusAndOrgi(
			String knowledgeower, String status, String orgi, Pageable pageable) {
		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
		BoolQueryBuilder bq = QueryBuilders.boolQuery() ; 
		bq.must(QueryBuilders.termQuery("status", status)) ;
		bq.must(QueryBuilders.termQuery("orgi", orgi)) ;
		bq.must(QueryBuilders.termQuery("knowledgeower", knowledgeower)) ;
		boolQueryBuilder.must(bq); 
		HasParentQueryBuilder hasParentQueryBuilder=QueryBuilders.hasParentQuery("uk_ekm_kb_master",QueryBuilders.termQuery("datastatus", false));
		NativeSearchQueryBuilder searchQueryBuilder = new NativeSearchQueryBuilder().withQuery(boolQueryBuilder).withQuery(hasParentQueryBuilder) ;
		searchQueryBuilder.withPageable(pageable) ;
		Page<EkmKnowledgeCollect> knowledgeCollectList = null ;
		if(elasticsearchTemplate.indexExists(EkmKnowledgeCollect.class)){
			knowledgeCollectList = elasticsearchTemplate.queryForPage(searchQueryBuilder.build() , EkmKnowledgeCollect.class ) ;
	    }
		
		return knowledgeCollectList ;
	}

	@Override
	public List<EkmKnowledgeCollect> findByKnowledgeowerAndStatusAndOrgi(
			String knowledgeower, String status, String orgi) {
		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
		BoolQueryBuilder bq = QueryBuilders.boolQuery() ; 
		bq.must(QueryBuilders.termQuery("knowledgeower", knowledgeower)) ;
		bq.must(QueryBuilders.termQuery("orgi", orgi)) ;
		bq.must(QueryBuilders.termQuery("status", status)) ;
		boolQueryBuilder.must(bq); 
		
		NativeSearchQueryBuilder searchQueryBuilder = new NativeSearchQueryBuilder().withQuery(boolQueryBuilder) ;
		Page<EkmKnowledgeCollect> knowledgeCollectList = null ;
		if(elasticsearchTemplate.indexExists(EkmKnowledgeCollect.class)){
			knowledgeCollectList = elasticsearchTemplate.queryForPage(searchQueryBuilder.build() , EkmKnowledgeCollect.class ) ;
	    }
		
		return knowledgeCollectList.getContent();
	}

	@Override
	public List<EkmKnowledgeCollect> findByCreaterAndStatusAndFolderidAndOrgi(
			String creater, String status, String folderid, String orgi) {
		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
		BoolQueryBuilder bq = QueryBuilders.boolQuery() ; 
		bq.must(QueryBuilders.termQuery("creater", creater)) ;
		bq.must(QueryBuilders.termQuery("orgi", orgi)) ;
		bq.must(QueryBuilders.termQuery("status", status)) ;
		bq.must(QueryBuilders.termQuery("folderid", folderid)) ;
		boolQueryBuilder.must(bq); 
		
		NativeSearchQueryBuilder searchQueryBuilder = new NativeSearchQueryBuilder().withQuery(boolQueryBuilder) ;
		Page<EkmKnowledgeCollect> knowledgeCollectList = null ;
		if(elasticsearchTemplate.indexExists(EkmKnowledgeCollect.class)){
			knowledgeCollectList = elasticsearchTemplate.queryForPage(searchQueryBuilder.build() , EkmKnowledgeCollect.class ) ;
	    }
		
		return knowledgeCollectList.getContent();
	}

	@Override
	public List<EkmKnowledgeCollect> findByCreaterAndStatusAndOrgi(
			String creater, String status, String orgi) {
		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
		BoolQueryBuilder bq = QueryBuilders.boolQuery() ; 
		bq.must(QueryBuilders.termQuery("creater", creater)) ;
		bq.must(QueryBuilders.termQuery("orgi", orgi)) ;
		bq.must(QueryBuilders.termQuery("status", status)) ;
		boolQueryBuilder.must(bq); 
		
		NativeSearchQueryBuilder searchQueryBuilder = new NativeSearchQueryBuilder().withQuery(boolQueryBuilder) ;
		Page<EkmKnowledgeCollect> knowledgeCollectList = null ;
		if(elasticsearchTemplate.indexExists(EkmKnowledgeCollect.class)){
			knowledgeCollectList = elasticsearchTemplate.queryForPage(searchQueryBuilder.build() , EkmKnowledgeCollect.class ) ;
	    }
		
		return knowledgeCollectList.getContent();
	}

	@Override
	public EkmKnowledgeCollect findByCreaterAndKnowledgeidAndStatusAndOrgi(
			String creater, String knowledgeid, String status, String orgi) {
		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
		BoolQueryBuilder bq = QueryBuilders.boolQuery() ; 
		bq.must(QueryBuilders.termQuery("creater", creater)) ;
		bq.must(QueryBuilders.termQuery("orgi", orgi)) ;
		bq.must(QueryBuilders.termQuery("status", status)) ;
		bq.must(QueryBuilders.termQuery("knowledgeid", knowledgeid)) ;
		boolQueryBuilder.must(bq); 
		
		NativeSearchQueryBuilder searchQueryBuilder = new NativeSearchQueryBuilder().withQuery(boolQueryBuilder) ;
		Page<EkmKnowledgeCollect> knowledgeCollectList = null ;
		if(elasticsearchTemplate.indexExists(EkmKnowledgeCollect.class)){
			knowledgeCollectList = elasticsearchTemplate.queryForPage(searchQueryBuilder.build() , EkmKnowledgeCollect.class ) ;
	    }
		
		if (knowledgeCollectList!=null && knowledgeCollectList.getContent().size()>0) {
			return knowledgeCollectList.getContent().get(0);
		}else {
			return null ;
		}
	}

	@Override
	public EkmKnowledgeCollect findByCreaterAndKbidAndStatusAndOrgi(
			String creater, String kbid, String status, String orgi) {
		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
		BoolQueryBuilder bq = QueryBuilders.boolQuery() ; 
		bq.must(QueryBuilders.termQuery("creater", creater)) ;
		bq.must(QueryBuilders.termQuery("orgi", orgi)) ;
		bq.must(QueryBuilders.termQuery("status", status)) ;
		bq.must(QueryBuilders.termQuery("kbid", kbid)) ;
		boolQueryBuilder.must(bq); 
		
		NativeSearchQueryBuilder searchQueryBuilder = new NativeSearchQueryBuilder().withQuery(boolQueryBuilder) ;
		Page<EkmKnowledgeCollect> knowledgeCollectList = null ;
		if(elasticsearchTemplate.indexExists(EkmKnowledgeCollect.class)){
			knowledgeCollectList = elasticsearchTemplate.queryForPage(searchQueryBuilder.build() , EkmKnowledgeCollect.class ) ;
	    }
		if (knowledgeCollectList!=null && knowledgeCollectList.getContent().size()>0) {
			return knowledgeCollectList.getContent().get(0);
		}else {
			return null ;
		}
	}

	@Override
	public List<EkmKnowledgeCollect> findByKnowledgeidAndOrgi(
			String knowledgeid, String orgi) {
		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
		BoolQueryBuilder bq = QueryBuilders.boolQuery() ; 
		bq.must(QueryBuilders.termQuery("knowledgeid", knowledgeid)) ;
		bq.must(QueryBuilders.termQuery("orgi", orgi)) ;
		boolQueryBuilder.must(bq); 
		
		NativeSearchQueryBuilder searchQueryBuilder = new NativeSearchQueryBuilder().withQuery(boolQueryBuilder) ;
		Page<EkmKnowledgeCollect> knowledgeCollectList = null ;
		if(elasticsearchTemplate.indexExists(EkmKnowledgeCollect.class)){
			knowledgeCollectList = elasticsearchTemplate.queryForPage(searchQueryBuilder.build() , EkmKnowledgeCollect.class ) ;
	    }
		
		return knowledgeCollectList.getContent();
	}
}
