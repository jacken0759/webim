package com.ukefu.webim.service.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.QualityTemplateItem;

public abstract interface QualityTemplateItemRepository extends JpaRepository<QualityTemplateItem,String>{
	
	public abstract Page<QualityTemplateItem> findByOrgi(String orgi , Pageable page) ;
	
	public abstract List<QualityTemplateItem> findByOrgi(String orgi) ;
	
	public abstract QualityTemplateItem findByIdAndOrgi(String id, String orgi);
	
	public abstract QualityTemplateItem findByNameAndOrgi(String name, String orgi);
	
	public abstract List<QualityTemplateItem> findByParentidAndOrgi(String parentid,String orgi , Pageable page) ;
	
	public abstract Page<QualityTemplateItem> findByTemplateidAndOrgi(String templateid,String orgi , Pageable page) ;
}
