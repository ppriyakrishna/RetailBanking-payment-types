package com.retailBanking.payments.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.retailBanking.payments.domain.User;
import com.retailBanking.payments.services.UserService;

@Controller
public class LoginController {
	
	@Autowired
	UserService userService;
	
	
	@RequestMapping(value = "/loginAccount", method = RequestMethod.GET)
	public ModelAndView processLoginAccount(HttpSession session){

		ModelAndView modelView=new ModelAndView("login/login");
		
		
		User user=(User)session.getAttribute("user");
		if(user!=null){
			modelView.addObject("user",user);
		}else{
			modelView.addObject("user",new User());
		}
		return modelView;

	}

	@RequestMapping(value = "/loginSuccess", method = RequestMethod.POST  )
	public ModelAndView processLoginSuccess(String userName,String password,HttpSession  session) 
	{

		User existingUser=null;
		User newUser;
		ModelAndView modelView;

		existingUser=userService.getUser(userName);		
		newUser=new User(userName,password);

		if(existingUser!=null && existingUser.equals(newUser)) {
			modelView=new ModelAndView("login/loginSuccess");
			session.setAttribute("user", existingUser);
			modelView.addObject("user", existingUser);
		}else{

			modelView=new ModelAndView("login/login");
			modelView.addObject("user", newUser);
			
			modelView.addObject("message", "Invalid Credentials");
		
		}
		
		return modelView;


	}
	


}
