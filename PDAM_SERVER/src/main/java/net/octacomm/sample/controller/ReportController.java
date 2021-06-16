package net.octacomm.sample.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;

import net.octacomm.sample.dao.mapper.DeviceMapper;
import net.octacomm.sample.dao.mapper.PenetrationMapper;
import net.octacomm.sample.dao.mapper.PieceMapper;
import net.octacomm.sample.dao.mapper.ReportMapper;
import net.octacomm.sample.domain.Device;
import net.octacomm.sample.domain.Penetration;
import net.octacomm.sample.domain.Piece;
import net.octacomm.sample.domain.Report;
import net.octacomm.sample.domain.ReportParam;
import net.octacomm.sample.domain.SessionInfo;
import net.octacomm.sample.domain.UpdateReport;
import net.octacomm.sample.utils.MathUtil;
import net.octacomm.sample.utils.Pagination;
import net.octacomm.sample.utils.Utill;

@RequestMapping("/report")
@Controller
public class ReportController{
		
	
	@Autowired
	private DeviceMapper deviceMapper;
	
	@Autowired
	protected PenetrationMapper penetrationMapper;
	
	@Autowired
	protected PieceMapper pieceMapper;
	
	@Autowired
	protected ReportMapper mapper;
	
	@RequestMapping(value = "/list")
	public void list(Model model, @ModelAttribute("domainParam") ReportParam param, BindingResult result, HttpSession session) {
		
		System.err.println("Search Param : {}" +  param);
		
		param.setRole((int) session.getAttribute("role"));
		param.setConstructionIdx((int) session.getAttribute("constructionIdx"));
		
		int totalCount = mapper.getCountByParam(param);
		
		System.err.println("Total Count : {}" +  totalCount);
	
		Pagination page;
		
		if (param.getPageSize() > 0 && param.getPageGroupSize() > 0) {
			page = new Pagination(param.getPageSize(), param.getPageGroupSize(), totalCount, param.getCurrentPage());
		} else {
			page = new Pagination(totalCount, param.getCurrentPage());
		}
		Device device = deviceMapper.get(param.getId());
		
		//System.err.println("page.getStartRow() : " + page.getStartRow());
		//System.err.println("page.getPageSize() : " + page.getPageSize());
		
		List<Report> domainList = mapper.getListByParam(page.getStartRow(), page.getPageSize(), param);
		int rownum = (totalCount + 1) - page.getStartRow();
		for (Report d : domainList) {
			d.setRownum(rownum = rownum - 1);
			d.setPiece(pieceMapper.getListByReportIdx(d.getId()));
			d.setPenetrations(penetrationMapper.getListByReportIdx(d.getId()));
		}
		
		model.addAttribute("device", device);
		model.addAttribute("param", param);
		model.addAttribute("page", page);		
		model.addAttribute("domainList", domainList);
	}
	
	/*
	 * @Autowired
	 * 
	 * @Override public void setCRUDMapper(ReportMapper mapper) { this.mapper =
	 * mapper; }
	 * 
	 * @Override protected Class<Report> getDomainClass() { return Report.class; }
	 * 
	 * @Override protected String getRedirectUrl() { return "redirect:/report/list";
	 * }
	 */
	
	@ModelAttribute
	public void setActiveMenu(Model model, HttpSession session) {
		int role = (Integer) session.getAttribute("role");
		if(role > 0) {
			model.addAttribute("menuIndex", 1);
		}else{
			model.addAttribute("menuIndex", 2);
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
	
	@RequestMapping(value = "/download/excel")
	public String downLoadExcel(Model model, @ModelAttribute("domainParam") ReportParam param, BindingResult result, HttpSession session) {
		
		List<Report> domainList = mapper.getListByParamExcel(param);
		for (Report d : domainList) {
			d.setPiece(pieceMapper.getListByReportIdx(d.getId()));
			d.setPenetrations(penetrationMapper.getListByReportIdx(d.getId()));
		}
		model.addAttribute("domainList", domainList);
		return "connectStatsExcelView";
	}
	
	@ResponseBody
	@RequestMapping(value = "/update/report", method = RequestMethod.POST)
	public boolean updateReport(@RequestBody UpdateReport report) {
		System.err.println("report : " + report);
		/*
		 * Report rp = mapper.get(report.getId()); System.err.println("rp : " +
		 * rp.getTotalConnectWidth());
		 */
		report.setUltimateBearingCapacity(String.valueOf(calDanish(report)));
		//report.setUltimateBearingCapacity(report.getUltimateBearingCapacity());
		int result = mapper.update(report);
		if(result > 0) {
			for (Piece piese : report.getPiece()) {
				//if(piese.getValue().toString().length() > 0) {
					if(pieceMapper.get(piese.getId()) != null) {
						if(pieceMapper.update(piese) == 0) {
							return false;
						}
					}else {
						piese.setName("중단");
						if(pieceMapper.insert(piese) == 0) {
							return false;
						}
					}
				//}
			}
			if(report.getPenetrations() != null) {
				for (Penetration penetration : report.getPenetrations()) {
					if(penetrationMapper.get(penetration.getId()) != null) {
						penetrationMapper.update(penetration);
					}else {
						penetrationMapper.insert(penetration);
					}
				}
			}
		}
		return true;
	}
	
	@ResponseBody
	@RequestMapping(value = "/test/report", method = RequestMethod.GET)
	public List<Report> test( @ModelAttribute("domainParam") ReportParam param){
		Pagination page;
		
		int totalCount = mapper.getCountByParam(param);
		
		if (param.getPageSize() > 0 && param.getPageGroupSize() > 0) {
			page = new Pagination(param.getPageSize(), param.getPageGroupSize(), totalCount, param.getCurrentPage());
		} else {
			page = new Pagination(totalCount, param.getCurrentPage());
		}
		
		List<Report> domainList = mapper.getListByParam(page.getStartRow(), page.getPageSize(), param);
		for (Report d : domainList) {
			d.setPiece(pieceMapper.getListByReportIdx(d.getId()));
			d.setPenetrations(penetrationMapper.getListByReportIdx(d.getId()));
		}
		return domainList;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/doDelete", method = RequestMethod.POST)
	public boolean doDelete(@RequestParam("id") int id) {
		return mapper.doDelete(id) > 0;
	}
	
//	private double calDanish(float S){
//
//        if(S < 0){
//            S = Math.abs(S);
//        }
//
//        float EH = Float.parseFloat(Utill.stringNullCheck(binding.hammaEfficiency.getText().toString()) ? binding.hammaEfficiency.getText().toString() : "0");
//        float WR = Float.parseFloat(Utill.stringNullCheck(binding.hammaT.getText().toString()) ? binding.hammaT.getText().toString() : "0");
//        float H =  Float.parseFloat(Utill.stringNullCheck(binding.fallMeter.getText().toString()) ? binding.fallMeter.getText().toString() : "0");
//        float L =  Float.parseFloat(Utill.stringNullCheck(binding.totalConnectWidth.getText().toString()) ? binding.totalConnectWidth.getText().toString() : "0");
//        float A =  Float.parseFloat(Utill.stringNullCheck(binding.crossSection.getText().toString()) ? binding.crossSection.getText().toString() : "0");
//        float E =  Float.parseFloat(Utill.stringNullCheck(binding.modulusElasticity.getText().toString()) ? binding.modulusElasticity.getText().toString() : "0");
//
//        double RU = MathUtill.calDanish(EH, WR, (H * 100), (L * 100) ,A , E, (S / 10));
//        if(Double.isInfinite(RU)){
//            return 0;
//        }else if(Double.isNaN(RU)){
//            return  0;
//        }else{
//            return RU;
//        }
//    }
	
	private int calDanish(UpdateReport report){

        float EH = Float.parseFloat(Utill.stringNullCheck(report.getHammaEfficiency()) ? report.getHammaEfficiency() : "0");
        float WR = Float.parseFloat(Utill.stringNullCheck(report.getHammaT()) ? report.getHammaT() : "0");
        float H =  Float.parseFloat(Utill.stringNullCheck(report.getFallMeter()) ? report.getFallMeter() : "0");
        float L =  Float.parseFloat(Utill.stringNullCheck(report.getIntrusionDepth()) ? report.getIntrusionDepth() : "0");
        float A =  Float.parseFloat(Utill.stringNullCheck(report.getCrossSection()) ? report.getCrossSection() : "0");
        float E =  Float.parseFloat(Utill.stringNullCheck(report.getModulusElasticity()) ? report.getModulusElasticity() : "0");
        float S =  Float.parseFloat(Utill.stringNullCheck(report.getAvgPenetrationValue()) ? report.getAvgPenetrationValue() : "0");

        double RU = MathUtil.calDanish(EH, WR, ( H * 100), (L * 100) ,A , E, (S / 10));
        if(Double.isInfinite(RU)){
            return 0;
        }else if(Double.isNaN(RU)){
            return  0;
        }else{
            return (int) RU;
        }
    }
	
	
	@RequestMapping(value = "/regist", method = RequestMethod.GET)
	public void regist(Model model) throws InstantiationException, IllegalAccessException {
		model.addAttribute("domain", new Report());
	}

	@RequestMapping(value = "/regist", method = RequestMethod.POST)
	public String regist(@ModelAttribute("domain") Report domain, SessionStatus sessionStatus) {
		System.err.println("domain : {} " +  domain);
		if (mapper.insert(domain) == 1) {
			return "/report/regist";
		}
		return "/report/regist";
	}
}
