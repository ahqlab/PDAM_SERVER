package net.octacomm.sample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.octacomm.sample.dao.mapper.DeviceMapper;
import net.octacomm.sample.dao.mapper.PenetrationMapper;
import net.octacomm.sample.dao.mapper.PieceMapper;
import net.octacomm.sample.dao.mapper.ReportMapper;
import net.octacomm.sample.domain.CommonListResponse;
import net.octacomm.sample.domain.CommonResponse;
import net.octacomm.sample.domain.Construction;
import net.octacomm.sample.domain.Device;
import net.octacomm.sample.domain.Penetration;
import net.octacomm.sample.domain.Piece;
import net.octacomm.sample.domain.Report;
import net.octacomm.sample.exceptions.InvalidPasswordException;
import net.octacomm.sample.exceptions.NotFoundUserException;
import net.octacomm.sample.service.DeviceService;
import net.octacomm.sample.service.LoginService;

@RequestMapping("/mobile")
@Controller
public class MobileController {
	
	@Autowired
	private DeviceService deviceService;
	
	@Autowired
	private DeviceMapper deviceMapper;
	
	@Autowired
	private ReportMapper reportMapper;
	
	@Autowired
	private PieceMapper pieceMapper;
	
	@Autowired
	private PenetrationMapper penetrationMapper;
	
	@ResponseBody
	@RequestMapping(value = "/regist/report", method = RequestMethod.POST)
	public CommonResponse<Boolean> registReport(@RequestBody Report report, BindingResult result){
		System.err.println("report : " + report);
		CommonResponse<Boolean> response = new CommonResponse<Boolean>();
		try{
			if(report.getCreateDate() != null) {
				reportMapper.insert2(report);
			}else {
				reportMapper.insert(report);
			}
		}catch (Exception e) {
			System.err.println("insert  오류");
		}
		
		for (Piece piece : report.getPiece()) {
			piece.setReportIdx(report.getId());
			try {
				pieceMapper.insert(piece);
			}catch (Exception e) {
				response.setDomain(false);
			}
		}
		for (Penetration penetration : report.getPenetrations()) {
			penetration.setReportIdx(report.getId());
			try {
				penetrationMapper.insert(penetration);
			}catch (Exception e) {
				response.setDomain(false);
			}
		}
		response.setDomain(true);
		return response;
	}
	
	@ResponseBody
	@RequestMapping(value = "/regist/report2", method = RequestMethod.POST)
	public CommonResponse<Boolean> registReport2(@RequestBody List<Report> report, BindingResult result){
		System.err.println("report : " + report.size());
		CommonResponse<Boolean> response = new CommonResponse<Boolean>();
		for (Report report2 : report) {
			System.err.println("report value : " + report2);
			try{
				if(report2.getCreateDate() != null) {
					reportMapper.insert2(report2);
				}else {
					reportMapper.insert(report2);
				}
			}catch (Exception e) {
				System.err.println("insert  오류");
				response.setDomain(false);
				return response;
			}
			
			for (Piece piece : report2.getPiece()) {
				piece.setReportIdx(report2.getId());
				try {
					pieceMapper.insert(piece);
				}catch (Exception e) {
					response.setDomain(false);
				}
			}
			for (Penetration penetration : report2.getPenetrations()) {
				penetration.setReportIdx(report2.getId());
				try {
					penetrationMapper.insert(penetration);
				}catch (Exception e) {
					response.setDomain(false);
				}
			}
			response.setDomain(true);
		}
		
		return response;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/device/login", method = RequestMethod.POST)
	public CommonResponse<Device> mobileLogin(@RequestBody Device device) {
		CommonResponse<Device> response = new CommonResponse<Device>();
		try {
			Device result = deviceService.login(device);
			response.setDomain(result);
			response.setResultMessage("성공");
		} catch (NotFoundUserException nfe) {
			response.setDomain(null);
			response.setResultMessage("아이디가 존재하지 않습니다.");
		} catch (InvalidPasswordException ipe) {
			response.setDomain(null);
			response.setResultMessage("아이디 비밀번호가 일치하지 않습니다.");
		}
		return response;
	}
	
	
	
	@ResponseBody
	@RequestMapping(value = "/device/all/list", method = RequestMethod.GET)
	public CommonListResponse<Device> allList(){
		CommonListResponse<Device> response = new CommonListResponse<Device>();
		response.setDomain(deviceMapper.getList());
		response.setResultMessage("성공");
		return response;
	}
}
