package com.ukefu.webim.service.es;

import java.util.List;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Component;

import com.ukefu.webim.web.model.EkmKnowledgeTimes;

@Component
public class EkmKnowledgeTimesRepositoryImpl implements EkmKnowledgeTimesESRepository{

	private ElasticsearchTemplate elasticsearchTemplate;
	
	@Autowired
	public void setElasticsearchTemplate(ElasticsearchTemplate elasticsearchTemplate) {
		this.elasticsearchTemplate = elasticsearchTemplate;
    }
	
	@Override
	public List<EkmKnowledgeTimes> findByKbidAndVersionAndOrgi(String kbid,
			int version, String orgi) {
		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
		BoolQueryBuilder bq = QueryBuilders.boolQuery() ; 
		bq.must(QueryBuilders.termQuery("kbid", kbid)) ;
		bq.must(QueryBuilders.termQuery("version", version)) ;
		bq.must(QueryBuilders.termQuery("orgi", orgi)) ;
		boolQueryBuilder.must(bq); 
		
		NativeSearchQueryBuilder searchQueryBuilder = new NativeSearchQueryBuilder().withQuery(boolQueryBuilder) ;
		Page<EkmKnowledgeTimes> knowledgeTimesList = null ;
		if(elasticsearchTemplate.indexExists(EkmKnowledgeTimes.class)){
			knowledgeTimesList = elasticsearchTemplate.queryForPage(searchQueryBuilder.build() , EkmKnowledgeTimes.class ) ;
	    }
		
		return knowledgeTimesList.getContent();
	}

	@Override
	public Page<EkmKnowledgeTimes> findByOrgi(String orgi, Pageable pageable) {
		
		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
		boolQueryBuilder.must(QueryBuilders.termQuery("orgi", orgi)) ;
		NativeSearchQueryBuilder searchQueryBuilder = new NativeSearchQueryBuilder().withQuery(boolQueryBuilder).withSort(new FieldSortBuilder("viewtimes").unmappedType("long").order(SortOrder.DESC));
		
		searchQueryBuilder.withPageable(pageable) ;
		
		Page<EkmKnowledgeTimes> knowledgeTimesList = null ;
		if(elasticsearchTemplate.indexExists(EkmKnowledgeTimes.class)){
			knowledgeTimesList = elasticsearchTemplate.queryForPage(searchQueryBuilder.build() , EkmKnowledgeTimes.class ) ;
	    }
		
		return knowledgeTimesList;
	}

}
