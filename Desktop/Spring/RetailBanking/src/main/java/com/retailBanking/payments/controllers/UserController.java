package com.retailBanking.payments.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.retailBanking.payments.domain.User;
import com.retailBanking.payments.services.UserService;
import com.retailBanking.payments.validators.UserValidator;


@Controller
public class UserController {
	
	@Autowired
	UserService userService;
	
	
	@RequestMapping(value = "/newUserDataForm", method = RequestMethod.GET)
	public ModelAndView newStudentDataForm() {
		ModelAndView modelView;

		modelView = new ModelAndView("user/userDataForm");
		modelView.addObject("user", new User());
		return modelView;
	}

	@RequestMapping(value = "/processNewUserProfile", method = RequestMethod.POST)
	public ModelAndView processNewStudentForm(@Valid User user, BindingResult result, HttpSession session) 
	{
		ModelAndView modelView;
		User existingUser=null;
		UserValidator userValidator=new UserValidator();

		existingUser=userService.getUser(user.getUserName());
		if(existingUser!=null){
			result.rejectValue("userName", "userNameExists");
		}else{
			userValidator.validate(user, result);
		}

		if (result.hasErrors()) {
			modelView = new ModelAndView("user/userDataForm", "user", user);
			return modelView;
		}

		userService.addNewUser(user);
		modelView = new ModelAndView("user/userDataSucess");
		session.setAttribute("user", user);
		modelView.addObject("user", user);

		return modelView;
	}


		


}
