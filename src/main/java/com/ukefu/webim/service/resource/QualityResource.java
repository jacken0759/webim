package com.ukefu.webim.service.resource;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.ukefu.core.UKDataContext;
import com.ukefu.util.es.UKDataBean;
import com.ukefu.webim.service.repository.JobDetailRepository;
import com.ukefu.webim.service.repository.MetadataRepository;
import com.ukefu.webim.service.repository.QualityActivityTaskRepository;
import com.ukefu.webim.service.repository.QualityFilterRepository;
import com.ukefu.webim.service.repository.QualityFormFilterItemRepository;
import com.ukefu.webim.service.repository.QualityFormFilterRepository;
import com.ukefu.webim.web.model.JobDetail;
import com.ukefu.webim.web.model.MetadataTable;
import com.ukefu.webim.web.model.QualityActivityTask;
import com.ukefu.webim.web.model.QualityAgent;
import com.ukefu.webim.web.model.QualityFilter;
import com.ukefu.webim.web.model.QualityFormFilter;
import com.ukefu.webim.web.model.QualityFormFilterItem;

public class QualityResource extends Resource{
	
	private JobDetail jobDetail;
	private JobDetailRepository jobDetailRes;
	
	private QualityFormFilter qcFormFilter = null;
	private QualityFormFilterRepository qcFormFilterRes;
	
	private QualityFormFilterItem qcFormFilterItem;
	private QualityFormFilterItemRepository qcFormFilterItemRes;
	
	private MetadataTable metadataTable;
	private MetadataRepository metadataRes;
	
	private QualityFilter qcFilter;
	private QualityFilterRepository qcFilterRes;
	
	private List<QualityAgent> qcAgentList;
	private QualityAgent qcAgent;
	
	private QualityActivityTask qcActTask;
	private QualityActivityTaskRepository qcActTaskRes;
	
	private AtomicInteger assignorganInt = new AtomicInteger() /***分配到坐席***/, assignInt = new AtomicInteger() /***分配到部门***/ , assignAiInt = new AtomicInteger() /***分配到AI***/ ,atomInt = new AtomicInteger() ;
	
	public QualityResource(JobDetail jobDetail) {
		this.jobDetail = jobDetail;
		this.jobDetailRes = UKDataContext.getContext().getBean(JobDetailRepository.class);
		this.qcFilterRes = UKDataContext.getContext().getBean(QualityFilterRepository.class);
		this.qcFormFilterRes = UKDataContext.getContext().getBean(QualityFormFilterRepository.class);
		this.qcFormFilterItemRes = UKDataContext.getContext().getBean(QualityFormFilterItemRepository.class);
		this.qcActTaskRes = UKDataContext.getContext().getBean(QualityActivityTaskRepository.class);
		this.metadataRes = UKDataContext.getContext().getBean(MetadataRepository.class);
		
	}

	@Override
	public void begin() throws Exception {
		
		
	}

	@Override
	public void end(boolean clear) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public JobDetail getJob() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void process(OutputTextFormat meta, JobDetail job) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public OutputTextFormat next() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAvailable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public OutputTextFormat getText(OutputTextFormat object) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void rmResource() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateTask() throws Exception {
		// TODO Auto-generated method stub
		
	}

	
}
