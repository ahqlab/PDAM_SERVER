package net.octacomm.sample.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import net.octacomm.sample.dao.mapper.ErpAdminMapper;
import net.octacomm.sample.domain.ErpAdmin;
import net.octacomm.sample.domain.ErpAdminParam;


@Controller
@RequestMapping("/erpAdmin")
public class ErpAdminController extends AbstractCRUDController<ErpAdminMapper, ErpAdmin, ErpAdminParam, Integer>{
	
	@Autowired
	@Override
	public void setCRUDMapper(ErpAdminMapper mapper) {
		this.mapper = mapper;	
	}

	@Override
	protected Class<ErpAdmin> getDomainClass() {
		return ErpAdmin.class;
	}

	@Override
	protected String getRedirectUrl(HttpServletRequest request, HttpSession session) {
		return "redirect:/erpAdmin/list";
	}
	

}
