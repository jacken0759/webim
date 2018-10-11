package com.ukefu.webim.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.EkmKnowbaseConfig;

public abstract interface EkmKnowbaseConfigRepository  extends JpaRepository<EkmKnowbaseConfig,String>{
	
	public abstract EkmKnowbaseConfig findByBasehost(String basehost);
}
