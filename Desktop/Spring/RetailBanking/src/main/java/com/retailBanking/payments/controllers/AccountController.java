package com.retailBanking.payments.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.retailBanking.payments.domain.Account;
import com.retailBanking.payments.domain.User;
import com.retailBanking.payments.services.AccountService;
import com.retailBanking.payments.services.BankService;
import com.retailBanking.payments.services.BranchService;
import com.retailBanking.payments.validators.AccountValidator;

@Controller
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	@Autowired
	private BranchService branchService;
	@Autowired
	private BankService bankService;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
		
	@RequestMapping(value ="/account", method = RequestMethod.GET,params="home")
	public String home() {
	

		return "home/home";
	}
	
	@RequestMapping(value ="/account", method = RequestMethod.GET,params="accounts")
	public String accountPage() {
	

		return "account/account";
	}
	
	
	@RequestMapping(value ="/processNewAccount", method = RequestMethod.GET)
	public ModelAndView newAccount(Model model,HttpSession session) {
		ModelAndView modelView;
		List<String>branchNameList=new ArrayList<String>();
		List<String>currencyList=new ArrayList<String>();
		
		modelView = new ModelAndView("account/newAccountDetails");
		
		branchNameList=branchService.findAllBranchNames();
		session.setAttribute("branchNameList", branchNameList);
		modelView.addObject("branchNameList", branchNameList);
		
		currencyList=bankService.getAllBankCurrencies();
		modelView.addObject("currencyList", currencyList);
		session.setAttribute("currencyList", currencyList);
		
		modelView.addObject("account", new Account());
		
		
		return modelView;
		
	}
	

	@RequestMapping(value ="/processNewAccountProfile", method = RequestMethod.POST)
	public ModelAndView processNewAccount(@Valid Account account, BindingResult result, HttpSession session) {
		ModelAndView modelView;
				
		Account existingAccount;
		AccountValidator validator=new AccountValidator();
		
		existingAccount=accountService.getAccountDetails(account.getAccount_Name());
		if(existingAccount!=null){
			result.rejectValue("account_Name", "AccountNameExists");
		}else{
			validator.validate(account, result);
		}
		
		if (result.hasErrors()) {
			modelView = new ModelAndView("account/newAccountDetails", "account", account);
			return modelView;
		}
		User user=(User)session.getAttribute("user");
		

		accountService.insertAccount(account,user.getUser_Id());
		modelView = new ModelAndView("account/accountSuccess");		
		modelView.addObject("account", account);

		return modelView;
		
	}
	
	@RequestMapping(value ="/viewAccountDetails", method = RequestMethod.GET)
	public ModelAndView viewAccountdetailsAccount(Model model,HttpSession session) {
		ModelAndView modelView;
		List<Account> existingAccount;
		
		modelView = new ModelAndView("account/viewAccountDetails");
		User user=(User)session.getAttribute("user");
		
		existingAccount=accountService.getAllAccountDetailsFromUser(user.getUser_Id());
	
		modelView.addObject("accountList", existingAccount);
		
		
		return modelView;
		
	}
	
	

}
