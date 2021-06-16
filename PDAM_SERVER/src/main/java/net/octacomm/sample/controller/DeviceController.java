package net.octacomm.sample.controller;

import java.util.List;

import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.octacomm.sample.dao.mapper.ConstructionMapper;
import net.octacomm.sample.dao.mapper.DeviceMapper;
import net.octacomm.sample.domain.Construction;
import net.octacomm.sample.domain.ConstructionParam;
import net.octacomm.sample.domain.Device;
import net.octacomm.sample.domain.DeviceParam;
import net.octacomm.sample.domain.SessionInfo;

@RequestMapping("/device")
@Controller
public class DeviceController extends AbstractCRUDController<DeviceMapper, Device, DeviceParam, Integer>{
	
	@Autowired
	private ConstructionMapper ConstructionMapper;

	
	@Autowired
	@Override
	public void setCRUDMapper(DeviceMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	protected Class<Device> getDomainClass() {
		return Device.class;
	}
	
	@Override
	protected String getRedirectUrl(HttpServletRequest request, HttpSession session) {
		// TODO Auto-generated method stub
		return "redirect:/construction/list";
	}
	
	@ModelAttribute
	public void setActiveMenu(Model model, HttpSession session) {
		int role = (Integer) session.getAttribute("role");
		if(role > 0) {
			model.addAttribute("menuIndex", 0);
		}else{
			model.addAttribute("menuIndex", 1);
		}
	}
	

	
	@RequestMapping(value = "/regist2", method = RequestMethod.GET)
	public void regist(Model model, @RequestParam("constructionIdx") int constructionIdx){
		model.addAttribute("constructionIdx", constructionIdx);
		model.addAttribute("domain", new Device());
	}
	
	
	
	@ResponseBody
	@RequestMapping(value = "/duplicate/tabletNo/confirm", method = RequestMethod.POST)
	public List<Device> duplicateContactConfirm(@RequestParam("tabletNo") String tabletNo) {
		return mapper.getFindByTabletNo(tabletNo);
	}
		
	@ResponseBody
	@RequestMapping(value = "/doDelete", method = RequestMethod.POST)
	public boolean doDelete(@RequestParam("id") int id) {
		return mapper.doDelete(id) > 0;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/update/conduct", method = RequestMethod.POST)
	public boolean updateConduct(@RequestParam("id") int id, @RequestParam("conduct") int conduct) {
		return mapper.updateConduct(id, conduct) > 0;
	}
	
}
