package net.octacomm.sample.controller;

import java.util.List;

import javax.mail.Session;
import javax.servlet.http.HttpSession;

import net.octacomm.logger.Log;
import net.octacomm.sample.dao.CRUDMapper;
import net.octacomm.sample.dao.mapper.PenetrationMapper;
import net.octacomm.sample.dao.mapper.PieceMapper;
import net.octacomm.sample.domain.Domain;
import net.octacomm.sample.domain.DomainParam;
import net.octacomm.sample.domain.SessionInfo;
import net.octacomm.sample.utils.Pagination;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * CRUD (Create, Retrieve, Update, Delete) 기능을 수행하는 추상 클래스.
 * 도메인 클래스에 대해 CRUD 기능을 하려면, 본 추상 클래스를 상속받아 사용한다. 
 * 
 * @author taeyo
 *
 * @param <M> CRUDMapper 를 상속받은 SQL Mapper 인터페이스
 * @param <D> Domain 을 상속받은 클래스
 * @param <P> DomainParam 을 상속받은 클래스 (검색에 사용할 클래스)
 */
@SessionAttributes("domain")
public abstract class AbstractReportCRUDController<M extends CRUDMapper<D, P, PK>, D extends Domain, P extends DomainParam, PK> {

	@Log
	private Logger logger;
	
	protected static final String URL_LIST = "/list";
	protected static final String URL_REGIST = "/regist";
	protected static final String URL_DETAIL = "/detail";
	protected static final String URL_UPDATE = "/update";
	protected static final String URL_DELETE = "/delete";
	
	@Autowired
	protected PenetrationMapper penetrationMapper;
	
	@Autowired
	protected PieceMapper pieceMapper;

	protected M mapper;

	/**
	 * 반드시 CRUDMapper 를 지정해야 한다.
	 * @param mapper
	 */
	public abstract void setCRUDMapper(M mapper);

	@RequestMapping(value = URL_LIST)
	public void list(Model model, @ModelAttribute("domainParam") P param, BindingResult result, HttpSession session) {
	
		logger.debug("Search Param : {}", param);
		
		param.setRole((int) session.getAttribute("role"));
		param.setConstructionIdx((int) session.getAttribute("constructionIdx"));
		
		int totalCount = mapper.getCountByParam(param);
		
		logger.debug("Total Count : {}", totalCount);
	
		Pagination page;
		
		if (param.getPageSize() > 0 && param.getPageGroupSize() > 0) {
			page = new Pagination(param.getPageSize(), param.getPageGroupSize(), totalCount, param.getCurrentPage());
		} else {
			page = new Pagination(totalCount, param.getCurrentPage());
		}
		
		List<D> domainList = mapper.getListByParam(page.getStartRow(), page.getPageSize(), param);
		for (D d : domainList) {
			
		}
		
		logger.debug("Domain List Size : {}", domainList.size());
		
		model.addAttribute("page", page);		
		model.addAttribute("domainList", domainList);
	}

	@RequestMapping(value = URL_REGIST, method = RequestMethod.GET)
	public void regist(Model model) throws InstantiationException, IllegalAccessException {
		model.addAttribute("domain", getDomainClass().newInstance());
	}

	protected abstract Class<D> getDomainClass();

	@RequestMapping(value = URL_REGIST, method = RequestMethod.POST)
	public String regist(@ModelAttribute("domain") D domain, SessionStatus sessionStatus) {
	
		logger.debug("domain : {}", domain);
	
		if (mapper.insert(domain) == 1) {
			sessionStatus.setComplete();
			return getRedirectUrl();
		} else {
			return URL_REGIST;
		}
	}

	protected abstract String getRedirectUrl();

	@RequestMapping(value = { URL_UPDATE, URL_DETAIL }, method = RequestMethod.GET)
	public void form(Model model, @RequestParam PK id) {
		model.addAttribute("domain", mapper.get(id));
	}

	@RequestMapping(value = URL_UPDATE, method = RequestMethod.POST)
	public String update(@ModelAttribute("domain") D domain, RedirectAttributes redirectAttributes) {
	
		logger.debug("domain : {}", domain);
	
		if (mapper.update(domain) == 1) {
			return getRedirectUrl();
		} else {
			return URL_UPDATE;
		}
	}

	@RequestMapping(value = URL_DELETE, method = RequestMethod.GET)
	public String delete(@RequestParam PK id, RedirectAttributes redirectAttributes) {
	
		if (mapper.delete(id) == 1) {
			return getRedirectUrl();
		} else {
			throw new RuntimeException("삭제중 오류");
		}
	}
	
	@ModelAttribute
	public void setSessionInfo(Model model, HttpSession session) {
		SessionInfo sessionInfo = new SessionInfo();
		sessionInfo.setUserId((String) session.getAttribute("userId"));
		sessionInfo.setRole((Integer) session.getAttribute("role"));
		sessionInfo.setConstructionIdx((Integer) session.getAttribute("constructionIdx"));
		sessionInfo.setHiddenManager((Boolean) session.getAttribute("isHiddenManager"));
		sessionInfo.setGroupIdx((Integer) session.getAttribute("groupIdx"));	
	    model.addAttribute("sessionInfo", sessionInfo);
	}

}