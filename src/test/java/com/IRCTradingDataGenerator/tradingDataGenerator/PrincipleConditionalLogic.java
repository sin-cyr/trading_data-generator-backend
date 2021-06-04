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

import com.IRCTradingDataGenerator.tradingDataGenerator.Models.Counterparty;
import com.IRCTradingDataGenerator.tradingDataGenerator.Models.Principle;
import com.IRCTradingDataGenerator.tradingDataGenerator.Models.Trade;
import com.IRCTradingDataGenerator.tradingDataGenerator.Services.DataService;
import com.IRCTradingDataGenerator.tradingDataGenerator.Services.TradeService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class PrincipleConditionalLogic {

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
	public void testThatCreatePrinciple_WorksForNEWT_ForMarginLending() {
		Trade newTrade = new Trade();
		newTrade.setTrade_type("Margin Lending");
		newTrade.setTrade_quality("Clean");
		newTrade.setTrade_level("TCTN");
		newTrade.setAction_type("NEWT");
		Counterparty counterparty = tradeService.createCounterparty(newTrade);
		newTrade.setTrade_counterparty(counterparty);
		
		Principle principle = tradeService.createPrinciple(newTrade);
		Assertions.assertNotNull(principle.getDay_count_convention());
		Assertions.assertNotNull(principle.getFloat_ref_period_time());
		Assertions.assertNotNull(principle.getFloat_pay_freq_time());
		Assertions.assertNotNull(principle.getFloat_reset_freq_time());
		Assertions.assertNotNull(principle.getMargin_currency());
		Assertions.assertNotNull(principle.getOutstanding_ML_base_currency());
		Assertions.assertNotNull(principle.getFloating_rate());
		Assertions.assertNotNull(principle.getFixed_rate());
		Assertions.assertNotNull(principle.getOutstanding_margin_loan());
		Assertions.assertNotNull(principle.getShort_market_value());
		Assertions.assertNotNull(principle.getMargin_currency_amount());
		Assertions.assertNotNull(principle.getFloat_ref_period_multi());
		Assertions.assertNotNull(principle.getFloat_pay_freq_multi());
		Assertions.assertNotNull(principle.getFloat_reset_freq_multi());
		Assertions.assertNotNull(principle.getSpread());
	}
	
	@Test
	public void testThatCreatePrinciple_WorksForMODI_ForMarginLending() {
		Trade newTrade = new Trade();
		newTrade.setTrade_type("Margin Lending");
		newTrade.setTrade_quality("Clean");
		newTrade.setTrade_level("TCTN");
		newTrade.setAction_type("MODI");
		Counterparty counterparty = tradeService.createCounterparty(newTrade);
		newTrade.setTrade_counterparty(counterparty);
		
		Principle principle = tradeService.createPrinciple(newTrade);
		Assertions.assertNotNull(principle.getDay_count_convention());
		Assertions.assertNotNull(principle.getFloat_ref_period_time());
		Assertions.assertNotNull(principle.getFloat_pay_freq_time());
		Assertions.assertNotNull(principle.getFloat_reset_freq_time());
		Assertions.assertNotNull(principle.getMargin_currency());
		Assertions.assertNotNull(principle.getOutstanding_ML_base_currency());
		Assertions.assertNotNull(principle.getFloating_rate());
		Assertions.assertNotNull(principle.getFixed_rate());
		Assertions.assertNotNull(principle.getOutstanding_margin_loan());
		Assertions.assertNotNull(principle.getShort_market_value());
		Assertions.assertNotNull(principle.getMargin_currency_amount());
		Assertions.assertNotNull(principle.getFloat_ref_period_multi());
		Assertions.assertNotNull(principle.getFloat_pay_freq_multi());
		Assertions.assertNotNull(principle.getFloat_reset_freq_multi());
		Assertions.assertNotNull(principle.getSpread());
	}
	
	@Test
	public void testThatCreatePrinciple_WorksForEROR_ForMarginLending() {
		Trade newTrade = new Trade();
		newTrade.setTrade_type("Margin Lending");
		newTrade.setTrade_quality("Clean");
		newTrade.setTrade_level("TCTN");
		newTrade.setAction_type("EROR");
		Counterparty counterparty = tradeService.createCounterparty(newTrade);
		newTrade.setTrade_counterparty(counterparty);
		
		Principle principle = tradeService.createPrinciple(newTrade);
		Assertions.assertNull(principle.getDay_count_convention());
		Assertions.assertNull(principle.getFloat_ref_period_time());
		Assertions.assertNull(principle.getFloat_pay_freq_time());
		Assertions.assertNull(principle.getFloat_reset_freq_time());
		Assertions.assertNull(principle.getMargin_currency());
		Assertions.assertNull(principle.getOutstanding_ML_base_currency());
		Assertions.assertNull(principle.getFloating_rate());
		Assertions.assertNull(principle.getFixed_rate());
		Assertions.assertNull(principle.getOutstanding_margin_loan());
		Assertions.assertNull(principle.getShort_market_value());
		Assertions.assertNull(principle.getMargin_currency_amount());
		Assertions.assertNull(principle.getFloat_ref_period_multi());
		Assertions.assertNull(principle.getFloat_pay_freq_multi());
		Assertions.assertNull(principle.getFloat_reset_freq_multi());
		Assertions.assertNull(principle.getSpread());
	}
	
	@Test
	public void testThatCreatePrinciple_WorksForETRM_ForMarginLending() {
		Trade newTrade = new Trade();
		newTrade.setTrade_type("Margin Lending");
		newTrade.setTrade_quality("Clean");
		newTrade.setTrade_level("TCTN");
		newTrade.setAction_type("ETRM");
		Counterparty counterparty = tradeService.createCounterparty(newTrade);
		newTrade.setTrade_counterparty(counterparty);
		
		Principle principle = tradeService.createPrinciple(newTrade);
		Assertions.assertNull(principle.getDay_count_convention());
		Assertions.assertNull(principle.getFloat_ref_period_time());
		Assertions.assertNull(principle.getFloat_pay_freq_time());
		Assertions.assertNull(principle.getFloat_reset_freq_time());
		Assertions.assertNull(principle.getMargin_currency());
		Assertions.assertNull(principle.getOutstanding_ML_base_currency());
		Assertions.assertNull(principle.getFloating_rate());
		Assertions.assertNull(principle.getFixed_rate());
		Assertions.assertNull(principle.getOutstanding_margin_loan());
		Assertions.assertNull(principle.getShort_market_value());
		Assertions.assertNull(principle.getMargin_currency_amount());
		Assertions.assertNull(principle.getFloat_ref_period_multi());
		Assertions.assertNull(principle.getFloat_pay_freq_multi());
		Assertions.assertNull(principle.getFloat_reset_freq_multi());
		Assertions.assertNull(principle.getSpread());
	}
	
	@Test
	public void testThatCreatePrinciple_WorksForPOSC_ForMarginLending() {
		Trade newTrade = new Trade();
		newTrade.setTrade_type("Margin Lending");
		newTrade.setTrade_quality("Clean");
		newTrade.setTrade_level("TCTN");
		newTrade.setAction_type("POSC");
		Counterparty counterparty = tradeService.createCounterparty(newTrade);
		newTrade.setTrade_counterparty(counterparty);
		
		Principle principle = tradeService.createPrinciple(newTrade);
		Assertions.assertNotNull(principle.getDay_count_convention());
		Assertions.assertNotNull(principle.getFloat_ref_period_time());
		Assertions.assertNotNull(principle.getFloat_pay_freq_time());
		Assertions.assertNotNull(principle.getFloat_reset_freq_time());
		Assertions.assertNull(principle.getMargin_currency());
		Assertions.assertNull(principle.getOutstanding_ML_base_currency());
		Assertions.assertNotNull(principle.getFloating_rate());
		Assertions.assertNotNull(principle.getFixed_rate());
		Assertions.assertNull(principle.getOutstanding_margin_loan());
		Assertions.assertNull(principle.getShort_market_value());
		Assertions.assertNull(principle.getMargin_currency_amount());
		Assertions.assertNotNull(principle.getFloat_ref_period_multi());
		Assertions.assertNotNull(principle.getFloat_pay_freq_multi());
		Assertions.assertNotNull(principle.getFloat_reset_freq_multi());
		Assertions.assertNotNull(principle.getSpread());
	}
	
	@Test
	public void testThatCreatePrinciple_WorksForCOLU_ForMarginLending() {
		Trade newTrade = new Trade();
		newTrade.setTrade_type("Margin Lending");
		newTrade.setTrade_quality("Clean");
		newTrade.setTrade_level("TCTN");
		newTrade.setAction_type("COLU");
		Counterparty counterparty = tradeService.createCounterparty(newTrade);
		newTrade.setTrade_counterparty(counterparty);
		
		Principle principle = tradeService.createPrinciple(newTrade);
		Assertions.assertNull(principle.getDay_count_convention());
		Assertions.assertNull(principle.getFloat_ref_period_time());
		Assertions.assertNull(principle.getFloat_pay_freq_time());
		Assertions.assertNull(principle.getFloat_reset_freq_time());
		Assertions.assertNull(principle.getMargin_currency());
		Assertions.assertNull(principle.getOutstanding_ML_base_currency());
		Assertions.assertNull(principle.getFloating_rate());
		Assertions.assertNull(principle.getFixed_rate());
		Assertions.assertNull(principle.getOutstanding_margin_loan());
		Assertions.assertNull(principle.getShort_market_value());
		Assertions.assertNull(principle.getMargin_currency_amount());
		Assertions.assertNull(principle.getFloat_ref_period_multi());
		Assertions.assertNull(principle.getFloat_pay_freq_multi());
		Assertions.assertNull(principle.getFloat_reset_freq_multi());
		Assertions.assertNull(principle.getSpread());
	}
	
	@Test
	public void testThatCreatePrinciple_WorksForCORR_ForMarginLending() {
		Trade newTrade = new Trade();
		newTrade.setTrade_type("Margin Lending");
		newTrade.setTrade_quality("Clean");
		newTrade.setTrade_level("TCTN");
		newTrade.setAction_type("CORR");
		Counterparty counterparty = tradeService.createCounterparty(newTrade);
		newTrade.setTrade_counterparty(counterparty);
		
		Principle principle = tradeService.createPrinciple(newTrade);
		Assertions.assertNotNull(principle.getDay_count_convention());
		Assertions.assertNotNull(principle.getFloat_ref_period_time());
		Assertions.assertNotNull(principle.getFloat_pay_freq_time());
		Assertions.assertNotNull(principle.getFloat_reset_freq_time());
		Assertions.assertNotNull(principle.getMargin_currency());
		Assertions.assertNotNull(principle.getOutstanding_ML_base_currency());
		Assertions.assertNotNull(principle.getFloating_rate());
		Assertions.assertNotNull(principle.getFixed_rate());
		Assertions.assertNotNull(principle.getOutstanding_margin_loan());
		Assertions.assertNotNull(principle.getShort_market_value());
		Assertions.assertNotNull(principle.getMargin_currency_amount());
		Assertions.assertNotNull(principle.getFloat_ref_period_multi());
		Assertions.assertNotNull(principle.getFloat_pay_freq_multi());
		Assertions.assertNotNull(principle.getFloat_reset_freq_multi());
		Assertions.assertNotNull(principle.getSpread());
	}
	
	@Test
	public void testThatCreatePrinciple_WorksForVALU_ForMarginLending() {
		Trade newTrade = new Trade();
		newTrade.setTrade_type("Margin Lending");
		newTrade.setTrade_quality("Clean");
		newTrade.setTrade_level("TCTN");
		newTrade.setAction_type("VALU");
		Counterparty counterparty = tradeService.createCounterparty(newTrade);
		newTrade.setTrade_counterparty(counterparty);
		
		Principle principle = tradeService.createPrinciple(newTrade);
		Assertions.assertNull(principle.getDay_count_convention());
		Assertions.assertNull(principle.getFloat_ref_period_time());
		Assertions.assertNull(principle.getFloat_pay_freq_time());
		Assertions.assertNull(principle.getFloat_reset_freq_time());
		Assertions.assertNull(principle.getMargin_currency());
		Assertions.assertNull(principle.getOutstanding_ML_base_currency());
		Assertions.assertNull(principle.getFloating_rate());
		Assertions.assertNull(principle.getFixed_rate());
		Assertions.assertNull(principle.getOutstanding_margin_loan());
		Assertions.assertNull(principle.getShort_market_value());
		Assertions.assertNull(principle.getMargin_currency_amount());
		Assertions.assertNull(principle.getFloat_ref_period_multi());
		Assertions.assertNull(principle.getFloat_pay_freq_multi());
		Assertions.assertNull(principle.getFloat_reset_freq_multi());
		Assertions.assertNull(principle.getSpread());
	}
	
}
