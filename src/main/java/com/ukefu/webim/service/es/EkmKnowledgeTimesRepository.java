package com.ukefu.webim.service.es;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.ukefu.webim.web.model.EkmKnowledgeTimes;

public abstract interface EkmKnowledgeTimesRepository extends  ElasticsearchRepository<EkmKnowledgeTimes, String>, EkmKnowledgeTimesESRepository
{

}
