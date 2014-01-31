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
import com.retailBanking.payments.domain.Bank;
import com.retailBanking.payments.domain.Branch;
import com.retailBanking.payments.domain.FundsTransfer;
import com.retailBanking.payments.domain.PaymentType;
import com.retailBanking.payments.domain.User;
import com.retailBanking.payments.exceptions.CurrencyIndifferenceException;
import com.retailBanking.payments.exceptions.InsufficentFundsException;
import com.retailBanking.payments.services.AccountService;
import com.retailBanking.payments.services.BankService;
import com.retailBanking.payments.services.BranchService;
import com.retailBanking.payments.services.FundsTransferService;
import com.retailBanking.payments.services.PaymentTypeService;
import com.retailBanking.payments.validators.FundsTransferValidator;

@Controller
public class FundsTransferController {

	@Autowired
	private FundsTransferService fundsTransferService;
	@Autowired
	private PaymentTypeService paymentTypeService;
	@Autowired
	private AccountService accountService;
	@Autowired
	private BranchService branchService;
	@Autowired
	private BankService bankService;

	@RequestMapping(value ="/fundsTransfer", method = RequestMethod.GET)
	public ModelAndView newFundsTransfer(Model model,HttpSession session) {
		ModelAndView modelView;
		List<Long>accountIdList=new ArrayList<Long>();
		List<String>paymentTypeList=new ArrayList<String>();
		List<String>countryNameList=new ArrayList<String>();
		List<String>bankNameList=new ArrayList<String>();
		List<String>currencyList=new ArrayList<String>();

		User user=(User)session.getAttribute("user");

		modelView = new ModelAndView("fundstransfer/fundsTransfer");

		FundsTransfer fundsTransfer=(FundsTransfer)session.getAttribute("fundsTransfer");
		if(fundsTransfer!=null){
			modelView.addObject("fundsTransfer", fundsTransfer);

		}else{

			modelView.addObject("fundsTransfer", new FundsTransfer());
		}

		

			accountIdList=accountService.getAllAccount_Ids(user.getUser_Id());
			session.setAttribute("accountIdList", accountIdList);
			modelView.addObject("accountIdList", accountIdList);

			paymentTypeList=paymentTypeService.findAllPaymentTypesCodes();
			session.setAttribute("paymentTypeList", paymentTypeList);		
			modelView.addObject("paymentTypeList", paymentTypeList);

			countryNameList=bankService.getAllCountryNames();
			session.setAttribute("countryNameList", countryNameList);
			modelView.addObject("countryNameList", countryNameList);

			bankNameList=bankService.getAllBankNames();
			session.setAttribute("bankNameList", bankNameList);
			modelView.addObject("bankNameList", bankNameList);

			currencyList=(List)session.getAttribute("currencyList");
			if(currencyList!=null){
				modelView.addObject("currencyList", currencyList);
			}else{
				currencyList=bankService.getAllBankCurrencies();
				modelView.addObject("currencyList", currencyList);
				session.setAttribute("currencyList", currencyList);
			}


		

		return modelView;

	}

	@RequestMapping(value ="/processFundsTransfer", method = RequestMethod.POST)
	public ModelAndView processFundsTransfer(@Valid FundsTransfer fundsTransfer, BindingResult result, HttpSession session) {
		ModelAndView modelView;
		FundsTransferValidator fundsTransferValidator=new FundsTransferValidator();


		modelView = new ModelAndView("fundstransfer/fundsTransfer");
		modelView.addObject("fundsTransfer", fundsTransfer);

		Account fromAccount=accountService.getAccountDetails(fundsTransfer.getFromAccount_Id());
		fundsTransfer.setFromAccount(fromAccount);

		Branch branch=branchService.findBranchDetails(fromAccount.getBranch_Id());
		Bank fromBank=bankService.getBankDetails(branch.getBank_Id());
		fundsTransfer.setFromBank(fromBank);


		PaymentType paymentType=paymentTypeService.findPaymentTypeByCode(fundsTransfer.getPaymentTypeCode()) ;
		fundsTransfer.setPaymentType(paymentType);


		fundsTransferValidator.validate(fundsTransfer, result);
		if(fundsTransfer.getToAccount()!=null){
			Account toAccount=accountService.getAccountDetails(fundsTransfer.getToAccount_Id());
			if(toAccount==null){
				result.rejectValue("toAccount", "accountNotExists");			
			}else{
				Branch toBranch=branchService.findBranchDetails(toAccount.getBranch_Id());
				Bank toBank=bankService.getBankDetails(toBranch.getBank_Id());				
				if(!toBank.equalsFromDB(fundsTransfer.getToBank())){
					result.rejectValue("toBank", "notCorrectBank");
				}else{
					fundsTransfer.setToBank(toBank);
				}if(!toAccount.equals(fundsTransfer.getToAccount())){
					result.rejectValue("toAccount", "accountNotExists");	
				}


			}
		}


		if (result.hasErrors()) {
			modelView = new ModelAndView("fundstransfer/fundsTransfer", "fundsTransfer", fundsTransfer);
			return modelView;
		}




		try{
			fundsTransferService.fundsTransfer(fundsTransfer);
			modelView.addObject("message", "Funds Transfer was sucessful");
		}catch(InsufficentFundsException e){
			modelView = new ModelAndView("exceptions/inSufficentFundsPage", "fromAccount", fundsTransfer.getFromAccount());	
			session.setAttribute("fundsTransfer", fundsTransfer);
		}catch(CurrencyIndifferenceException e){
			modelView = new ModelAndView("exceptions/dftrule", "dftMessage", e.getMessage());	
			session.setAttribute("fundsTransfer", fundsTransfer);
		}catch(Exception e){
			modelView = new ModelAndView("exceptions/oops", "oopsMessage", e.getMessage());		
		}


		return modelView;

	}



}
