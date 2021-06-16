package net.octacomm.sample.controller;

import java.util.List;

import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.octacomm.sample.dao.mapper.ConstructionMapper;
import net.octacomm.sample.dao.mapper.DeviceMapper;
import net.octacomm.sample.domain.Construction;
import net.octacomm.sample.domain.ConstructionParam;
import net.octacomm.sample.domain.Device;
import net.octacomm.sample.domain.SessionInfo;

@RequestMapping("/construction")
@Controller
public class ConstructionController extends AbstractConstructionCRUDController<ConstructionMapper, Construction, ConstructionParam, Integer>{
	
	@Autowired
	private DeviceMapper deviceMapper;
	
	@Autowired
	@Override
	public void setCRUDMapper(ConstructionMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	protected Class<Construction> getDomainClass() {
		return Construction.class;
	}

	@Override
	protected String getRedirectUrl(HttpServletRequest request, HttpSession session) {
		return "redirect:/construction/list";
	}
	
	@ModelAttribute
	public void setActiveMenu(Model model) {
	    model.addAttribute("menuIndex", 0);
	}
	
	
	
	@ResponseBody
	@RequestMapping(value = "/duplicate/contact/confirm", method = RequestMethod.POST)
	public List<Construction> duplicateContactConfirm(@RequestParam("userId") String userId) {
		return mapper.getFindByContact(userId);
	}
	
	@ResponseBody
	@RequestMapping(value = "/doDelete", method = RequestMethod.POST)
	public boolean doDelete(@RequestParam("id") int id) {
		List<Device> list = deviceMapper.getFindByConstructionIdx(id);
		for (Device device : list) {
			deviceMapper.doDelete(device.getId());
		}
		return mapper.doDelete(id) > 0;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/update/conduct", method = RequestMethod.POST)
	public boolean updateConduct(@RequestParam("id") int id, @RequestParam("conduct") int conduct) {
		return mapper.updateConduct(id, conduct) > 0;
	}
	
	@ResponseBody
	@RequestMapping(value = "/get/list", method = RequestMethod.GET)
	public List<Construction> getList(HttpSession session) {
		int role = (Integer) session.getAttribute("role");
		int constructionIdx = (Integer) session.getAttribute("constructionIdx");
		if(role > 0) {
			return mapper.getListByConstructionIdx(constructionIdx);
		}
		return mapper.getList();
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/get/name",  produces = "application/text; charset=utf8", method = RequestMethod.POST)
	public String getName(@RequestParam("id") int id) {
		Construction domain = mapper.getFullName(id);
		return domain.getName();
	}
	
}
