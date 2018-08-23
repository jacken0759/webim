package com.ukefu.webim.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.CalloutSaleCount;

public interface CalloutSaleCountRepository extends JpaRepository<CalloutSaleCount,String>{
	
	public List<CalloutSaleCount> findByOrgi(String orgi);
}
