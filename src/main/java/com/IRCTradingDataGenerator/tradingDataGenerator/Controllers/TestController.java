package com.IRCTradingDataGenerator.tradingDataGenerator.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
	
	 	@GetMapping("/")
	    public String root() {
	 		
		 //TradeField Loan_Amount = new TradeField(1, "Loan_Amount", "0.0");
		 //TradeType  MarginLending = new TradeType("Margin_Lending", "1,2,3,4");

		 //tradeTypeDao.saveAndFlush(MarginLending);
		 //tradeFieldDao.saveAndFlush(Loan_Amount);
		 
	        return "Index";
	    }

}
