package net.octacomm.sample.controller;

import javax.servlet.http.HttpSession;

import net.octacomm.sample.dao.mapper.ConstructionMapper;
import net.octacomm.sample.dao.mapper.GroupMapper;
import net.octacomm.sample.domain.Construction;
import net.octacomm.sample.domain.Group;
import net.octacomm.sample.exceptions.InvalidPasswordException;
import net.octacomm.sample.exceptions.NotFoundUserException;
import net.octacomm.sample.service.LoginService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {
	
	public static final String LOGIN_URL = "/login";
	
	public static final String DEFAULT_GROUP_TARGET_URL = "/construction/list";
	
	public static final String DEFAULT_ADMIN_TARGET_URL = "/group/list";
	
	public static final String DEFAULT_FC_TARGET_URL = "/franchise/list";
	
	public static final String DEFAULT_TARGET_URL = "/device/list";

	@Autowired
	private LoginService loginService;
	
	@Autowired
	private ConstructionMapper conMapper;
	
	@Autowired
	private GroupMapper groupMapper;

	@RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
	public String index() {
		return "redirect:" + LOGIN_URL;
	}
	/**
	 * 로그인
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		model.addAttribute("domain", new Construction());
		return LOGIN_URL;
	}
	/**
	 * 로그인 진행
	 * 
	 * @param model
	 * @param session
	 * @param user
	 * @param errors
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(Model model, HttpSession session, Construction construction, Errors errors) {
		try {
			Construction result = loginService.login(construction, session);
			if(result != null) {
				System.err.println("result.getRole() : " + result.getRole());
				if(result.getRole() == 0) {
					return "redirect:" + DEFAULT_ADMIN_TARGET_URL;
				}else if(result.getRole() == 2) {
					Group group = groupMapper.selectByUserId(result.getUserId());
					return "redirect:" + DEFAULT_GROUP_TARGET_URL + "?groupIdx=" + group.getIdx();
				}else if(result.getRole() == 3) {
					
					return "redirect:" + DEFAULT_FC_TARGET_URL + "?groupIdx=";
				}
				return "redirect:" + DEFAULT_TARGET_URL + "?constructionIdx=" + result.getId();
			}
			model.addAttribute("errorMessage", "아이디가 비밀번호를 확인하세요.");
			model.addAttribute("domain", new Construction());
			return LOGIN_URL;
		} catch (NotFoundUserException nfe) {
			model.addAttribute("domain", new Construction());
			model.addAttribute("errorMessage", "아이디가 존재하지 않습니다.");
			errors.reject("test", "아이디가 존재하지 않습니다.");
		} catch (InvalidPasswordException ipe) {
			model.addAttribute("domain", new Construction());
			model.addAttribute("errorMessage", "비밀번호가 일치하지 않습니다.");
			errors.reject("test", "비밀번호가 일치하지 않습니다.");
		}
		return LOGIN_URL;
	}
	/**
	 * 로그아웃
	 * 
	 * @param httpSession
	 * @return
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession httpSession) {
		httpSession.invalidate();
		return "redirect:" + LOGIN_URL;
	}
}
