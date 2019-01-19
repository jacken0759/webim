package com.ukefu.webim.web.handler.apps.notice;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ukefu.util.Menu;
import com.ukefu.webim.service.repository.NoticeRepository;
import com.ukefu.webim.service.repository.NoticeTargetRepository;
import com.ukefu.webim.service.repository.UserRepository;
import com.ukefu.webim.web.handler.Handler;
import com.ukefu.webim.web.model.Notice;
import com.ukefu.webim.web.model.QualityTemplate;
import com.ukefu.webim.web.model.User;

/**
 * 公告信息-公告管理
 *
 */
@Controller
@RequestMapping("/apps/notice")
public class NoticeController extends Handler{

	@Autowired
	private NoticeRepository NoticeRes ;
	
	@Autowired
	private NoticeTargetRepository NoticeTargetRes ;
	
	@Autowired
	private UserRepository userRes ;
	
	@RequestMapping("/index")
	@Menu(type = "notice", subtype = "notice")
	public ModelAndView index(ModelMap map , HttpServletRequest request ,HttpServletResponse response ,@Valid String msg) {
		final String orgi = super.getOrgi(request);
		Page<Notice> noticeList = NoticeRes.findAll(new Specification<Notice>(){
			@Override
			public Predicate toPredicate(Root<Notice> root, CriteriaQuery<?> query,CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();  

				list.add(cb.equal(root.get("orgi").as(String.class), orgi));

				Predicate[] p = new Predicate[list.size()];  
				return cb.and(list.toArray(p));   
			}}, new PageRequest(super.getP(request), super.getPs(request), Sort.Direction.DESC, new String[] { "createtime" }));
		
		
		map.addAttribute("noticeList",noticeList) ;
		map.addAttribute("msg",msg) ;
		map.addAttribute("userList",userRes.findByOrgi(orgi)) ;
		return request(super.createAppsTempletResponse("/apps/notice/index")) ;
	}
	
	@RequestMapping(value = "/add")
    @Menu(type = "notice" , subtype = "notice")
    public ModelAndView add(ModelMap map , HttpServletRequest request ) {
		return request(super.createAppsTempletResponse("/apps/notice/add"));
    }
	
	@RequestMapping(value = "/add/save")
    @Menu(type = "notice" , subtype = "notice")
    public ModelAndView addsave(ModelMap map , HttpServletRequest request ,@Valid Notice notice , @RequestParam(value = "files", required = false) MultipartFile[] files)  throws Exception {
		final User user = super.getUser(request) ;
		String msg="add_failure";
		if (notice != null) {
			notice.setCreater(user.getId());
			notice.setCreatetime(new Date());
			notice.setOrgi(super.getOrgi(request));
			NoticeRes.save(notice) ;
			msg = "add_success" ;
		}
		return request(super.createRequestPageTempletResponse("redirect:/apps/notice/index.html?msg="+msg));
    }
	
	@RequestMapping(value = "/edit")
    @Menu(type = "notice" , subtype = "notice")
    public ModelAndView edit(ModelMap map , HttpServletRequest request ,@Valid String templateid) {
    	return request(super.createRequestPageTempletResponse("/apps/notice/edit"));
    }
	
	@RequestMapping(value = "/edit/save")
    @Menu(type = "notice" , subtype = "notice")
    public ModelAndView editsave(ModelMap map , HttpServletRequest request , @Valid String id ,@Valid QualityTemplate qualityTemplate ) {
		String msg="";
		return request(super.createRequestPageTempletResponse("redirect:/apps/qc/template/detail.html?templateid="+id+"&msg="+msg));
    }
	
	@RequestMapping("/delete")
    @Menu(type = "notice" , subtype = "notice" )
    public ModelAndView delete(ModelMap map , HttpServletRequest request , @Valid String id) throws SQLException {
		String msg="";
    	return request(super.createRequestPageTempletResponse("redirect:/apps/notice/msg/index.html?msg="+msg+"&p="+super.getP(request)));
    }
	
	@RequestMapping("/batdelete")
    @Menu(type = "notice" , subtype = "noticemsg" )
    public ModelAndView batdelete(ModelMap map , HttpServletRequest request , @Valid String[] ids) throws SQLException {
    	String msg = "bat_delete_success";
    	if(ids!=null && ids.length>0){
    		NoticeRes.delete(NoticeRes.findAll(Arrays.asList(ids)) );
    	}else {
    		msg = "bat_delete_faild";
    	}
    	return request(super.createRequestPageTempletResponse("redirect:/apps/notice/index.html?msg="+msg));
    }
}
