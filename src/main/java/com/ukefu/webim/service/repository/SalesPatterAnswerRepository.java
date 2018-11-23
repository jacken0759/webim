package com.ukefu.webim.service.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.QueSurveyAnswer;
import com.ukefu.webim.web.model.SalesPatterAnswer;

public abstract interface SalesPatterAnswerRepository extends JpaRepository<SalesPatterAnswer, String>{

  public abstract Page<SalesPatterAnswer> findByOrgi(String orgi ,Pageable paramPageable);

  public abstract List<SalesPatterAnswer> findByOrgi(String orgi);
  
  public abstract SalesPatterAnswer findByIdAndOrgi(String id , String orgi);
  
  public abstract List<SalesPatterAnswer> findByQuestionidAndOrgi(String questionid , String orgi);
  
  public abstract List<SalesPatterAnswer> findAll(Specification<SalesPatterAnswer> spec) ;
}
