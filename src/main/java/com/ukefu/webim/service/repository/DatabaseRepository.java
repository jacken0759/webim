package com.ukefu.webim.service.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.Database;

public abstract interface DatabaseRepository extends JpaRepository<Database, String> {
	
	public Database findByIdAndOrgi(String id,String orgi);
	
	public abstract Page<Database> findByOrgi(String orgi , Pageable page);
	
}
