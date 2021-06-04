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

import com.IRCTradingDataGenerator.tradingDataGenerator.Models.Collateral;
import com.IRCTradingDataGenerator.tradingDataGenerator.Models.Trade;
import com.IRCTradingDataGenerator.tradingDataGenerator.Services.DataService;
import com.IRCTradingDataGenerator.tradingDataGenerator.Services.TradeService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class CollateralConditionalLogic {

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
	public void testThat_CreateCollateral_Works_ForNEWT() {
		Trade newTrade = new Trade();
		newTrade.setTrade_type("Margin Lending");
		newTrade.setTrade_quality("Clean");
		newTrade.setTrade_level("TCTN");
		newTrade.setAction_type("NEWT");
		
		Collateral collateral = tradeService.createCollateral(newTrade);
		Assertions.assertNull(collateral.getReused_collateral_currency());
		Assertions.assertNotNull(collateral.getCollateral_component());
		Assertions.assertNotNull(collateral.getMethod_to_provide_collateral());
	}
	
	@Test
	public void testThat_CreateCollateral_Works_ForMODI() {
		Trade newTrade = new Trade();
		newTrade.setTrade_type("Margin Lending");
		newTrade.setTrade_quality("Clean");
		newTrade.setTrade_level("TCTN");
		newTrade.setAction_type("MODI");
		
		Collateral collateral = tradeService.createCollateral(newTrade);
		Assertions.assertNull(collateral.getReused_collateral_currency());
		Assertions.assertEquals(collateral.getCollateral_component(), "");
		Assertions.assertNotNull(collateral.getMethod_to_provide_collateral());
		Assertions.assertNull(collateral.getValue_of_reused_collateral());
	}
	
	@Test
	public void testThat_CreateCollateral_Works_ForEROR() {
		Trade newTrade = new Trade();
		newTrade.setTrade_type("Margin Lending");
		newTrade.setTrade_quality("Clean");
		newTrade.setTrade_level("TCTN");
		newTrade.setAction_type("EROR");
		
		Collateral collateral = tradeService.createCollateral(newTrade);
		Assertions.assertNull(collateral.getReused_collateral_currency());
		Assertions.assertEquals(collateral.getCollateral_component(), "");
		Assertions.assertNull(collateral.getMethod_to_provide_collateral());
		Assertions.assertNull(collateral.getValue_of_reused_collateral());
	}
	
	@Test
	public void testThat_CreateCollateral_Works_ForETRM() {
		Trade newTrade = new Trade();
		newTrade.setTrade_type("Margin Lending");
		newTrade.setTrade_quality("Clean");
		newTrade.setTrade_level("TCTN");
		newTrade.setAction_type("ETRM");
		
		Collateral collateral = tradeService.createCollateral(newTrade);
		Assertions.assertNull(collateral.getReused_collateral_currency());
		Assertions.assertEquals(collateral.getCollateral_component(), "");
		Assertions.assertNull(collateral.getMethod_to_provide_collateral());
	}
	
	@Test
	public void testThat_CreateCollateral_Works_ForPOSC() {
		Trade newTrade = new Trade();
		newTrade.setTrade_type("Margin Lending");
		newTrade.setTrade_quality("Clean");
		newTrade.setTrade_level("TCTN");
		newTrade.setAction_type("POSC");
		
		Collateral collateral = tradeService.createCollateral(newTrade);
		Assertions.assertNull(collateral.getReused_collateral_currency());
		Assertions.assertNotNull(collateral.getCollateral_component());
		Assertions.assertNotNull(collateral.getMethod_to_provide_collateral());
		Assertions.assertNull(collateral.getValue_of_reused_collateral());
	}
	
	@Test
	public void testThat_CreateCollateral_Works_ForCOLU() {
		Trade newTrade = new Trade();
		newTrade.setTrade_type("Margin Lending");
		newTrade.setTrade_quality("Clean");
		newTrade.setTrade_level("TCTN");
		newTrade.setAction_type("COLU");
		
		Collateral collateral = tradeService.createCollateral(newTrade);
		Assertions.assertNull(collateral.getReused_collateral_currency());
		Assertions.assertNotNull(collateral.getCollateral_component());
		Assertions.assertNull(collateral.getMethod_to_provide_collateral());
		Assertions.assertNull(collateral.getValue_of_reused_collateral());
	}
	
	@Test
	public void testThat_CreateCollateral_Works_ForCORR() {
		Trade newTrade = new Trade();
		newTrade.setTrade_type("Margin Lending");
		newTrade.setTrade_quality("Clean");
		newTrade.setTrade_level("TCTN");
		newTrade.setAction_type("CORR");
		
		Collateral collateral = tradeService.createCollateral(newTrade);
		Assertions.assertNull(collateral.getReused_collateral_currency());
		Assertions.assertNotNull(collateral.getCollateral_component());
		Assertions.assertNotNull(collateral.getMethod_to_provide_collateral());
	}
}
