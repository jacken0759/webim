package com.ukefu.webim.service.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.QualityActivity;

public abstract interface QualityActivityRepository extends JpaRepository<QualityActivity, String> {
	
	public abstract QualityActivity findByIdAndOrgi(String id, String orgi);

	public List<QualityActivity> findByOrgi(String orgi);
	
	public abstract Page<QualityActivity> findAll(Specification<QualityActivity> spec, Pageable page) ;
	
}
