package net.octacomm.sample.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.octacomm.sample.dao.mapper.GroupMapper;
import net.octacomm.sample.domain.Construction;
import net.octacomm.sample.domain.Group;
import net.octacomm.sample.domain.GroupParam;
import net.octacomm.sample.domain.SessionInfo;

@RequestMapping("/group")
@Controller
public class GroupController extends AbstractCRUDController<GroupMapper, Group, GroupParam, Integer>{
	
	@Autowired
	@Override
	public void setCRUDMapper(GroupMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	protected Class<Group> getDomainClass() {
		return Group.class;
	}
	
	@ResponseBody
	@RequestMapping(value = "/get/list", method = RequestMethod.GET)
	public List<Group> getList(HttpSession session) {
		return mapper.getList();
	}
	
	@Override
	protected String getRedirectUrl(HttpServletRequest request, HttpSession session) {
		return "redirect:/group/list";
	}  
	
	@ModelAttribute
	public void setTotalUseCount(Model model, HttpSession session) {
	    model.addAttribute("deviceCount", mapper.getTotalUseDeviceCount() > 0 ? "총 " + mapper.getTotalUseDeviceCount() + "대" : "총 0 대");
	    model.addAttribute("constructionCount",  mapper.getTotalUseConstructionCount() > 0 ? "" + "총 " + mapper.getTotalUseConstructionCount() + "개" : "총 0 개");
	}
	
	
}
