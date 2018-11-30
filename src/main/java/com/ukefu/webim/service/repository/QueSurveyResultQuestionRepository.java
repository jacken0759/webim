package com.ukefu.webim.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.QueSurveyResultQuestion;

public abstract interface QueSurveyResultQuestionRepository extends JpaRepository<QueSurveyResultQuestion, String>{

  public abstract QueSurveyResultQuestion findByIdAndOrgi(String id, String orgi);
  
}
