package com.ukefu.webim.service.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.Scene;
import com.ukefu.webim.web.model.SmsResult;

public interface SmsResultRepository extends JpaRepository<SmsResult, String> {
	
	public SmsResult findByIdAndOrgi(String id , String orgi);
	public List<SmsResult> findByTemplettypeAndOrgi(String templettype , String orgi);
	public List<SmsResult> findByOrgi(String orgi) ;
	
	public Page<Scene> findByOrgi(String orgi ,Pageable paramPageable);
	public Page<Scene> findByTemplettypeAndOrgi(String templettype,String orgi ,Pageable paramPageable);
}
