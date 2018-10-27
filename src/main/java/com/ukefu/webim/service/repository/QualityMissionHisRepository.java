package com.ukefu.webim.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.QualityMissionHis;

public abstract interface QualityMissionHisRepository  extends JpaRepository<QualityMissionHis,String>{
	
	
	public abstract List<QualityMissionHis> findByOrgi(String Orgi);
}
