package net.octacomm.sample.controller;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.octacomm.sample.dao.mapper.AuthCodeMapper;
import net.octacomm.sample.dao.mapper.ReportMapper;
import net.octacomm.sample.domain.AuthResult;
import net.octacomm.sample.domain.ApiReport;
import net.octacomm.sample.domain.AuthCode;
import net.octacomm.sample.domain.Construction;
import net.octacomm.sample.domain.Group;
import net.octacomm.sample.domain.Report;
import net.octacomm.sample.exceptions.InvalidPasswordException;
import net.octacomm.sample.exceptions.NotFoundUserException;
import net.octacomm.sample.service.LoginService;

@RequestMapping("/api")
@Controller
public class ApiController {
	
	public static final String SUCCESS = "SUCCESS"; 
	
	public static final String FAILED = "FAILED"; 
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private AuthCodeMapper authCodeMapper;
	
	@Autowired
	private ReportMapper reportMapper;
	
	@ResponseBody
	@RequestMapping(value = "/get/auth/check", method = RequestMethod.POST)
	public AuthResult getReportList(Construction construction) {
		AuthResult apiResult = new AuthResult();
		try {
			loginService.login(construction);
			apiResult.setStatus(SUCCESS);
			AuthCode ad = authCodeMapper.getAuthCode(construction);
			if(ad != null) {
				apiResult.setAuthCode(ad.getAuthCode());
			}else {
				String authCode =  createAuthCode();
				authCodeMapper.insert(construction.getUserId(), authCode);
				apiResult.setAuthCode(authCode);  
			}
			return apiResult;
		} catch (NotFoundUserException nfe) {
			apiResult.setStatus(FAILED);
		} catch (InvalidPasswordException ipe) {
			apiResult.setStatus(FAILED);
		}
		return apiResult;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/get/report/list", method = RequestMethod.POST)
	public List<ApiReport> getReportList(AuthCode authCode) {
		AuthCode ad = authCodeMapper.getAuthCode(authCode);
		if(ad != null) {
			List<ApiReport> report = reportMapper.getApiReport();
			return report;
		}
		return null;
	}
	    
	
	public String createAuthCode() {
		Random rnd =new Random();
		StringBuffer buf =new StringBuffer();
		for(int i=0;i<20;i++){
		    if(rnd.nextBoolean()){
		        buf.append((char)((int)(rnd.nextInt(26))+97));
		    }else{
		        buf.append((rnd.nextInt(10)));
		    }
		}
		return buf.toString();
	}
}
