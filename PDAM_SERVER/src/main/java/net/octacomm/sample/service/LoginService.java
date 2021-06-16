package net.octacomm.sample.service;

import javax.servlet.http.HttpSession;

import net.octacomm.sample.domain.Construction;
import net.octacomm.sample.domain.User;
import net.octacomm.sample.exceptions.InvalidPasswordException;
import net.octacomm.sample.exceptions.NotFoundUserException;

public interface LoginService {

	Construction login(Construction construction, HttpSession session) throws NotFoundUserException, InvalidPasswordException;

	Construction loginforHiddenManager(Construction construction) throws NotFoundUserException, InvalidPasswordException;
	
	Construction login(Construction construction) throws NotFoundUserException, InvalidPasswordException;
}
