package com.ukefu.webim.web.handler.apps.notice;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ukefu.util.Menu;
import com.ukefu.webim.service.repository.NoticeMsgRepository;
import com.ukefu.webim.web.handler.Handler;
import com.ukefu.webim.web.model.NoticeMsg;
import com.ukefu.webim.web.model.User;

/**
 * 公告信息-信息列表
 *
 */
@Controller
@RequestMapping("/apps/notice/msg")
public class NoticeMsgController  extends Handler{

	@Autowired
	private NoticeMsgRepository NoticeMsgRes ;
	
	@RequestMapping("/index")
	@Menu(type = "notice", subtype = "noticemsg")
	public ModelAndView index(ModelMap map , HttpServletRequest request ,HttpServletResponse response ,@Valid String msg) {
		final String orgi = super.getOrgi(request);
		final User user = super.getUser(request) ;
		Page<NoticeMsg> noticeMsgList = NoticeMsgRes.findAll(new Specification<NoticeMsg>(){
			@Override
			public Predicate toPredicate(Root<NoticeMsg> root, CriteriaQuery<?> query,CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();  

				list.add(cb.equal(root.get("orgi").as(String.class), orgi));
				list.add(cb.equal(root.get("target").as(String.class), user.getId()));

				Predicate[] p = new Predicate[list.size()];  
				return cb.and(list.toArray(p));   
			}}, new PageRequest(super.getP(request), super.getPs(request), Sort.Direction.DESC, new String[] { "createtime" }));
		
		
		map.addAttribute("noticeMsgList",noticeMsgList) ;
		map.addAttribute("msg",msg) ;
		return request(super.createAppsTempletResponse("/apps/notice/msg/index")) ;
	}
	
	@RequestMapping("/delete")
    @Menu(type = "notice" , subtype = "noticemsg" )
    public ModelAndView delete(ModelMap map , HttpServletRequest request , @Valid String id) throws SQLException {
		String msg = "" ;
		if (!StringUtils.isBlank(id)) {
			NoticeMsg noticemsg = NoticeMsgRes.findByIdAndOrgi(id,super.getOrgi(request)) ;
			if (noticemsg != null) {
				NoticeMsgRes.delete(noticemsg);
			}else {
				msg="delf" ;
			}
		}else {
			msg="delf" ;
		}
    	return request(super.createRequestPageTempletResponse("redirect:/apps/notice/msg/index.html?msg="+msg+"&p="+super.getP(request)));
    }
	
	@RequestMapping("/batdelete")
    @Menu(type = "notice" , subtype = "noticemsg" )
    public ModelAndView batdelete(ModelMap map , HttpServletRequest request , @Valid String[] ids) throws SQLException {
    	String msg = "bat_delete_success";
    	if(ids!=null && ids.length>0){
    		NoticeMsgRes.delete(NoticeMsgRes.findAll(Arrays.asList(ids)) );
    	}else {
    		msg = "bat_delete_faild";
    	}
    	return request(super.createRequestPageTempletResponse("redirect:/apps/notice/msg/index.html?msg="+msg));
    }
	
}
