package com.ukefu.webim.service.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.SalesPatterQuestion;

public abstract interface SalesPatterQuestionRepository extends JpaRepository<SalesPatterQuestion, String>{

  public abstract Page<SalesPatterQuestion> findByOrgi(String orgi ,Pageable paramPageable);

  public abstract List<SalesPatterQuestion> findByOrgi(String orgi);
  
  public abstract SalesPatterQuestion findByIdAndOrgi(String id , String orgi);
  
  public abstract List<SalesPatterQuestion> findByProcessidAndOrgi(String processid , String orgi);
  
  public abstract List<SalesPatterQuestion> findByProcessidAndOrgiOrderBySortindexAsc(String processid , String orgi);
  
}
