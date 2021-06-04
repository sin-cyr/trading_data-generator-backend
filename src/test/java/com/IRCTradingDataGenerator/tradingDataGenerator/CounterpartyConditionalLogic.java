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
import com.IRCTradingDataGenerator.tradingDataGenerator.Models.Trade;
import com.IRCTradingDataGenerator.tradingDataGenerator.Services.DataService;
import com.IRCTradingDataGenerator.tradingDataGenerator.Services.TradeService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class CounterpartyConditionalLogic {

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
	public void testThat_CreateCounterparty_Works_ForNEWT() {
		Trade newTrade = new Trade();
		newTrade.setTrade_type("Margin Lending");
		newTrade.setTrade_quality("Clean");
		newTrade.setTrade_level("TCTN");
		newTrade.setAction_type("NEWT");
		
		Counterparty counterparty = tradeService.createCounterparty(newTrade);
		Assertions.assertNotNull(counterparty.getReporting_counterparty());
		Assertions.assertNotNull(counterparty.getReporting_timestamp());
		Assertions.assertNotNull(counterparty.getOther_counterparty());
		Assertions.assertNotNull(counterparty.getEntity_responsible_for_report());
		Assertions.assertNotNull(counterparty.getReport_SubmittingEntity());
		Assertions.assertNotNull(counterparty.getBranch_reporting_counterparty());
		Assertions.assertNotNull(counterparty.getBranch_other_counterparty());
		Assertions.assertNotNull(counterparty.getOther_counterparty_country());
		Assertions.assertNotNull(counterparty.getCounterparty_nature());
		Assertions.assertNotNull(counterparty.getSector_of_reporting_counterparty());
		Assertions.assertNotNull(counterparty.getCounterparty_side());
	}
	
	@Test
	public void testThat_CreateCounterparty_Works_ForMODI() {
		Trade newTrade = new Trade();
		newTrade.setTrade_type("Margin Lending");
		newTrade.setTrade_quality("Clean");
		newTrade.setTrade_level("TCTN");
		newTrade.setAction_type("MODI");
		
		Counterparty counterparty = tradeService.createCounterparty(newTrade);
		Assertions.assertNotNull(counterparty.getReporting_counterparty());
		Assertions.assertNotNull(counterparty.getReporting_timestamp());
		Assertions.assertNotNull(counterparty.getOther_counterparty());
		Assertions.assertNotNull(counterparty.getEntity_responsible_for_report());
		Assertions.assertNotNull(counterparty.getReport_SubmittingEntity());
		Assertions.assertNotNull(counterparty.getBranch_reporting_counterparty());
		Assertions.assertNotNull(counterparty.getBranch_other_counterparty());
		Assertions.assertNotNull(counterparty.getOther_counterparty_country());
		Assertions.assertNotNull(counterparty.getCounterparty_nature());
		Assertions.assertNotNull(counterparty.getSector_of_reporting_counterparty());
		Assertions.assertNotNull(counterparty.getCounterparty_side());
	}
	
	@Test
	public void testThat_CreateCounterparty_Works_ForEROR() {
		Trade newTrade = new Trade();
		newTrade.setTrade_type("Margin Lending");
		newTrade.setTrade_quality("Clean");
		newTrade.setTrade_level("TCTN");
		newTrade.setAction_type("EROR");
		
		Counterparty counterparty = tradeService.createCounterparty(newTrade);
		Assertions.assertNotNull(counterparty.getReporting_counterparty());
		Assertions.assertNotNull(counterparty.getReporting_timestamp());
		Assertions.assertNotNull(counterparty.getOther_counterparty());
		Assertions.assertNull(counterparty.getEntity_responsible_for_report());
		Assertions.assertNotNull(counterparty.getReport_SubmittingEntity());
		Assertions.assertNull(counterparty.getBranch_reporting_counterparty());
		Assertions.assertNull(counterparty.getBranch_other_counterparty());
		Assertions.assertNull(counterparty.getOther_counterparty_country());
		Assertions.assertEquals(counterparty.getCounterparty_nature(), "");
		Assertions.assertEquals(counterparty.getSector_of_reporting_counterparty(), "");
		Assertions.assertNull(counterparty.getCounterparty_side());
	}
	
	@Test
	public void testThat_CreateCounterparty_Works_ForETRM() {
		Trade newTrade = new Trade();
		newTrade.setTrade_type("Margin Lending");
		newTrade.setTrade_quality("Clean");
		newTrade.setTrade_level("TCTN");
		newTrade.setAction_type("ETRM");
		
		Counterparty counterparty = tradeService.createCounterparty(newTrade);
		Assertions.assertNotNull(counterparty.getReporting_counterparty());
		Assertions.assertNotNull(counterparty.getReporting_timestamp());
		Assertions.assertNotNull(counterparty.getOther_counterparty());
		Assertions.assertNull(counterparty.getEntity_responsible_for_report());
		Assertions.assertNotNull(counterparty.getReport_SubmittingEntity());
		Assertions.assertNull(counterparty.getBranch_reporting_counterparty());
		Assertions.assertNull(counterparty.getBranch_other_counterparty());
		Assertions.assertNull(counterparty.getOther_counterparty_country());
		Assertions.assertEquals(counterparty.getCounterparty_nature(), "");
		Assertions.assertEquals(counterparty.getSector_of_reporting_counterparty(), "");
		Assertions.assertNull(counterparty.getCounterparty_side());
	}
	
	@Test
	public void testThat_CreateCounterparty_Works_ForPOSC() {
		Trade newTrade = new Trade();
		newTrade.setTrade_type("Margin Lending");
		newTrade.setTrade_quality("Clean");
		newTrade.setTrade_level("TCTN");
		newTrade.setAction_type("POSC");
		
		Counterparty counterparty = tradeService.createCounterparty(newTrade);
		Assertions.assertNotNull(counterparty.getReporting_counterparty());
		Assertions.assertNotNull(counterparty.getReporting_timestamp());
		Assertions.assertNotNull(counterparty.getOther_counterparty());
		Assertions.assertNotNull(counterparty.getEntity_responsible_for_report());
		Assertions.assertNotNull(counterparty.getReport_SubmittingEntity());
		Assertions.assertNotNull(counterparty.getBranch_reporting_counterparty());
		Assertions.assertNotNull(counterparty.getBranch_other_counterparty());
		Assertions.assertNotNull(counterparty.getOther_counterparty_country());
		Assertions.assertNotNull(counterparty.getCounterparty_nature());
		Assertions.assertNotNull(counterparty.getSector_of_reporting_counterparty());
		Assertions.assertNotNull(counterparty.getCounterparty_side());
	}
	
	@Test
	public void testThat_CreateCounterparty_Works_ForCOLU() {
		Trade newTrade = new Trade();
		newTrade.setTrade_type("Margin Lending");
		newTrade.setTrade_quality("Clean");
		newTrade.setTrade_level("TCTN");
		newTrade.setAction_type("COLU");
		
		Counterparty counterparty = tradeService.createCounterparty(newTrade);
		Assertions.assertNotNull(counterparty.getReporting_counterparty());
		Assertions.assertNotNull(counterparty.getReporting_timestamp());
		Assertions.assertNotNull(counterparty.getOther_counterparty());
		Assertions.assertNull(counterparty.getEntity_responsible_for_report());
		Assertions.assertNotNull(counterparty.getReport_SubmittingEntity());
		Assertions.assertNotNull(counterparty.getBranch_reporting_counterparty());
		Assertions.assertNotNull(counterparty.getBranch_other_counterparty());
		Assertions.assertNull(counterparty.getOther_counterparty_country());
		Assertions.assertEquals(counterparty.getCounterparty_nature(), "");
		Assertions.assertEquals(counterparty.getSector_of_reporting_counterparty(), "");
		Assertions.assertNull(counterparty.getCounterparty_side());
	}
	
	@Test
	public void testThat_CreateCounterparty_Works_ForCORR() {
		Trade newTrade = new Trade();
		newTrade.setTrade_type("Margin Lending");
		newTrade.setTrade_quality("Clean");
		newTrade.setTrade_level("TCTN");
		newTrade.setAction_type("CORR");
		
		Counterparty counterparty = tradeService.createCounterparty(newTrade);
		Assertions.assertNotNull(counterparty.getReporting_counterparty());
		Assertions.assertNotNull(counterparty.getReporting_timestamp());
		Assertions.assertNotNull(counterparty.getOther_counterparty());
		Assertions.assertNotNull(counterparty.getEntity_responsible_for_report());
		Assertions.assertNotNull(counterparty.getReport_SubmittingEntity());
		Assertions.assertNotNull(counterparty.getBranch_reporting_counterparty());
		Assertions.assertNotNull(counterparty.getBranch_other_counterparty());
		Assertions.assertNotNull(counterparty.getOther_counterparty_country());
		Assertions.assertNotNull(counterparty.getCounterparty_nature());
		Assertions.assertNotNull(counterparty.getSector_of_reporting_counterparty());
		Assertions.assertNotNull(counterparty.getCounterparty_side());
	}
	
	
}
