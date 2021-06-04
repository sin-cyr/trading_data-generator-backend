package com.IRCTradingDataGenerator.tradingDataGenerator;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.SharedHttpSessionConfigurer;
import org.springframework.web.context.WebApplicationContext;

import com.IRCTradingDataGenerator.tradingDataGenerator.Models.Trade;
import com.IRCTradingDataGenerator.tradingDataGenerator.Models.Transaction;
import com.IRCTradingDataGenerator.tradingDataGenerator.Services.DataService;
import com.IRCTradingDataGenerator.tradingDataGenerator.Services.TradeService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class TransactionConditionalLogic {
	
	@Autowired
	TradeService tradeService;
	
	@Autowired
	DataService dataService;

	@Autowired
	WebApplicationContext webApplicationContext;
	
	@Autowired
	ObjectMapper objectMapper;

	MockMvc mockMvc;

	MockHttpSession session;
	
	@BeforeEach
	public void setUp() {
		this.session = new MockHttpSession();
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
				.apply(SharedHttpSessionConfigurer.sharedHttpSession())
				.build();
	}

	@AfterEach
	public void test() {
		this.session = null;
		this.mockMvc = null;
	}
	
	@Test
	public void testThat_CreateTransaction_Works_ForNEWT() {
		Trade newTrade = new Trade();
		newTrade.setTrade_type("Margin Lending");
		newTrade.setTrade_quality("Clean");
		newTrade.setTrade_level("TCTN");
		newTrade.setAction_type("NEWT");
		
		Transaction newTransaction = tradeService.createTransaction(newTrade);
		Assertions.assertNotNull(newTransaction.getEvent_date());
		Assertions.assertNotNull(newTransaction.getType_Of_Sft());
		Assertions.assertNotNull(newTransaction.getExecution_timestamp());
		Assertions.assertNull(newTransaction.getTermination_date());
	}
	
	@Test
	public void testThat_CreateTransaction_Works_ForMODI() {
		Trade newTrade = new Trade();
		newTrade.setTrade_type("Margin Lending");
		newTrade.setTrade_quality("Clean");
		newTrade.setTrade_level("TCTN");
		newTrade.setAction_type("MODI");
		
		Transaction newTransaction = tradeService.createTransaction(newTrade);
		Assertions.assertNotNull(newTransaction.getEvent_date());
		Assertions.assertNotNull(newTransaction.getType_Of_Sft());
		Assertions.assertNotNull(newTransaction.getExecution_timestamp());
		Assertions.assertNull(newTransaction.getTermination_date());
	}
	
	@Test
	public void testThat_CreateTransaction_Works_ForEROR() {
		Trade newTrade = new Trade();
		newTrade.setTrade_type("Margin Lending");
		newTrade.setTrade_quality("Clean");
		newTrade.setTrade_level("TCTN");
		newTrade.setAction_type("EROR");
		
		Transaction newTransaction = tradeService.createTransaction(newTrade);
		Assertions.assertNull(newTransaction.getEvent_date());
		Assertions.assertNull(newTransaction.getType_Of_Sft());
		Assertions.assertNull(newTransaction.getExecution_timestamp());
		Assertions.assertNull(newTransaction.getTermination_date());
	}
	
	@Test
	public void testThat_CreateTransaction_Works_ForETRM() {
		Trade newTrade = new Trade();
		newTrade.setTrade_type("Margin Lending");
		newTrade.setTrade_quality("Clean");
		newTrade.setTrade_level("TCTN");
		newTrade.setAction_type("ETRM");
		
		Transaction newTransaction = tradeService.createTransaction(newTrade);
		Assertions.assertNotNull(newTransaction.getEvent_date());
		Assertions.assertNull(newTransaction.getType_Of_Sft());
		Assertions.assertNull(newTransaction.getExecution_timestamp());
		Assertions.assertNotNull(newTransaction.getTermination_date());
	}
	
	@Test
	public void testThat_CreateTransaction_Works_ForCOLU() {
		Trade newTrade = new Trade();
		newTrade.setTrade_type("Margin Lending");
		newTrade.setTrade_quality("Clean");
		newTrade.setTrade_level("TCTN");
		newTrade.setAction_type("COLU");
		
		Transaction newTransaction = tradeService.createTransaction(newTrade);
		Assertions.assertNotNull(newTransaction.getEvent_date());
		Assertions.assertNotNull(newTransaction.getType_Of_Sft());
		Assertions.assertNull(newTransaction.getExecution_timestamp());
		Assertions.assertNull(newTransaction.getTermination_date());
	}
	
	@Test
	public void testThat_CreateTransaction_Works_ForCORR() {
		Trade newTrade = new Trade();
		newTrade.setTrade_type("Margin Lending");
		newTrade.setTrade_quality("Clean");
		newTrade.setTrade_level("TCTN");
		newTrade.setAction_type("CORR");
		
		Transaction newTransaction = tradeService.createTransaction(newTrade);
		Assertions.assertNotNull(newTransaction.getEvent_date());
		Assertions.assertNotNull(newTransaction.getType_Of_Sft());
		Assertions.assertNotNull(newTransaction.getExecution_timestamp());
	}
	
}
