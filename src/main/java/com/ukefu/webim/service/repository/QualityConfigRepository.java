package com.ukefu.webim.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.QualityConfig;

public interface QualityConfigRepository extends JpaRepository<QualityConfig,String>{
	
	public abstract QualityConfig findByIdAndOrgi(String id ,String orgi);
	
	public abstract QualityConfig findByOrgi(String orgi);
}
