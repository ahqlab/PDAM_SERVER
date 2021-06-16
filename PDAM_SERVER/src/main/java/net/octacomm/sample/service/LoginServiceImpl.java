package net.octacomm.sample.service;

import net.octacomm.sample.dao.mapper.UserMapper;
import net.octacomm.sample.domain.Construction;
import net.octacomm.sample.exceptions.InvalidPasswordException;
import net.octacomm.sample.exceptions.NotFoundUserException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService{
	
	@Autowired
	UserMapper userMapper;

	@Override
	public Construction login(Construction construction, HttpSession session) throws NotFoundUserException, InvalidPasswordException {
		if (userMapper.getUser(construction) == null) {
			throw new NotFoundUserException();
		}
		Construction result1 = userMapper.getUserForAuth(construction);
		Construction result2 = userMapper.findByHiddenManagerPassword(construction);
		if(result1 != null) {
			session.setAttribute("userId", result1.getUserId());
			session.setAttribute("constructionIdx", result1.getId());
			session.setAttribute("role", result1.getRole());
			session.setAttribute("isHiddenManager", false);
			session.setAttribute("groupIdx", result1.getGroupIdx());
			return result1;
		}else if(result2 != null) {
			session.setAttribute("userId", result2.getUserId());
			session.setAttribute("constructionIdx", result2.getId());
			session.setAttribute("role", result2.getRole());
			session.setAttribute("isHiddenManager", true);
			session.setAttribute("groupIdx", result2.getGroupIdx());
			return result2;
		}
		throw new InvalidPasswordException();
	}
	
	@Override
	public Construction loginforHiddenManager(Construction construction) throws NotFoundUserException, InvalidPasswordException {
		if (userMapper.getUser(construction) == null) {
			throw new NotFoundUserException();
		}
		Construction result = userMapper.findByHiddenManagerPassword(construction);
		System.err.println("result 2 : " + result);
		if (result == null) {
			throw new InvalidPasswordException();
		}
		return result;
	}

	@Override
	public Construction login(Construction construction) throws NotFoundUserException, InvalidPasswordException {
		if (userMapper.getUser(construction) == null) {
			throw new NotFoundUserException();
		}
		Construction result = userMapper.getUserForAuth(construction);
		if(result == null) {
			throw new InvalidPasswordException();
		}
		return result;
	}

}
