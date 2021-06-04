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
import com.IRCTradingDataGenerator.tradingDataGenerator.Services.DataService;
import com.IRCTradingDataGenerator.tradingDataGenerator.Services.TradeService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ConditionalLogicTests {
	
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
	
	// REGION TRADE LEVEL
	
	@Test
	public void testThat_Conditioanl_Level_CanBeSet_ForMarginLending() {
		// create new trade
		Trade newTrade = new Trade();
		// set trade type
		newTrade.setTrade_type("Margin Lending");
		// set trade quality
		newTrade.setTrade_quality("Clean");
		// set trade level
		newTrade.setTrade_level(tradeService.setTradeLevel(newTrade));
		
		Assertions.assertNotNull(newTrade.getTrade_level());
		Assertions.assertEquals("TCTN", newTrade.getTrade_level());
	}
	
	@Test
	public void testThat_Conditioanl_Level_CanBeSet_ForSecComLending() {
		Trade newTrade = new Trade();
		newTrade.setTrade_type("Sec-Com Lending");
		newTrade.setTrade_quality("Clean");
		newTrade.setTrade_level(tradeService.setTradeLevel(newTrade));
				
		Assertions.assertNotNull(newTrade.getTrade_level());
	}
	
	@Test
	public void testThat_Conditioanl_Level_CanBeSet_ForBuySellBack() {
		Trade newTrade = new Trade();
		newTrade.setTrade_type("Buy/Sell Back");
		newTrade.setTrade_quality("Clean");
		newTrade.setTrade_level(tradeService.setTradeLevel(newTrade));
				
		Assertions.assertNotNull(newTrade.getTrade_level());
	}
	
	@Test
	public void testThat_Conditioanl_Level_CanBeSet_ForRepurchase() {
		Trade newTrade = new Trade();
		newTrade.setTrade_type("Repurchase");
		newTrade.setTrade_quality("Clean");
		newTrade.setTrade_level(tradeService.setTradeLevel(newTrade));
				
		Assertions.assertNotNull(newTrade.getTrade_level());
	}
	
	// REGION TRADE ACTION TYPE
	
	@Test
	public void testThat_Conditional_ActionType_CanBeSet_AtTradeLevel_ForMarginLending() {
		// create new trade
		Trade newTrade = new Trade();
		// set trade type
		newTrade.setTrade_type("Margin Lending");
		// set trade quality
		newTrade.setTrade_quality("Clean");
		// set trade level
		newTrade.setTrade_level("TCTN");
		newTrade.setAction_type(tradeService.setTradeActionType(newTrade));
		
		Assertions.assertNotNull(newTrade.getAction_type());
		// Margin Lending can not have POSC or VALU as action types
		Assertions.assertNotEquals("POSC", newTrade.getAction_type());
		Assertions.assertNotEquals("VALU", newTrade.getAction_type());
	}
	
	@Test
	public void testThat_Conditional_ActionType_CanBeSet_AtTradeLevel_ForSecComLending() {
		// create new trade
		Trade newTrade = new Trade();
		// set trade type
		newTrade.setTrade_type("Sec-Com Lending");
		// set trade quality
		newTrade.setTrade_quality("Clean");
		// set trade level
		newTrade.setTrade_level("TCTN");
		newTrade.setAction_type(tradeService.setTradeActionType(newTrade));
		
		Assertions.assertNotNull(newTrade.getAction_type());
	}
	
	@Test
	public void testThat_Conditional_ActionType_CanBeSet_AtPositionLevel_ForSecComLending() {
		// create new trade
		Trade newTrade = new Trade();
		// set trade type
		newTrade.setTrade_type("Sec-Com Lending");
		// set trade quality
		newTrade.setTrade_quality("Clean");
		// set trade level
		newTrade.setTrade_level("PSTN");
		newTrade.setAction_type(tradeService.setTradeActionType(newTrade));
		
		Assertions.assertNotNull(newTrade.getAction_type());
		Assertions.assertNotEquals("POSC", newTrade.getAction_type());
	}
	
	@Test
	public void testThat_Conditional_ActionType_CanBeSet_AtTradeLevel_ForBuySellBack() {
		// create new trade
		Trade newTrade = new Trade();
		// set trade type
		newTrade.setTrade_type("Buy/Sell Back");
		// set trade quality
		newTrade.setTrade_quality("Clean");
		// set trade level
		newTrade.setTrade_level("TCTN");
		newTrade.setAction_type(tradeService.setTradeActionType(newTrade));
		
		Assertions.assertNotNull(newTrade.getAction_type());
		Assertions.assertNotEquals("VALU", newTrade.getAction_type());
	}
	
	@Test
	public void testThat_Conditional_ActionType_CanBeSet_AtPositionLevel_ForBuySellBack() {
		// create new trade
		Trade newTrade = new Trade();
		// set trade type
		newTrade.setTrade_type("Buy/Sell Back");
		// set trade quality
		newTrade.setTrade_quality("Clean");
		// set trade level
		newTrade.setTrade_level("PSTN");
		newTrade.setAction_type(tradeService.setTradeActionType(newTrade));
		
		Assertions.assertNotNull(newTrade.getAction_type());
		Assertions.assertNotEquals("POSC", newTrade.getAction_type());
		Assertions.assertNotEquals("VALU", newTrade.getAction_type());
	}
	
	@Test
	public void testThat_Conditional_ActionType_CanBeSet_AtTradeLevel_ForRepurchase() {
		// create new trade
		Trade newTrade = new Trade();
		// set trade type
		newTrade.setTrade_type("Repurchase");
		// set trade quality
		newTrade.setTrade_quality("Clean");
		// set trade level
		newTrade.setTrade_level("TCTN");
		newTrade.setAction_type(tradeService.setTradeActionType(newTrade));
		
		Assertions.assertNotNull(newTrade.getAction_type());
		Assertions.assertNotEquals("VALU", newTrade.getAction_type());
	}
	
	@Test
	public void testThat_Conditional_ActionType_CanBeSet_AtPositionLevel_ForRepurchase() {
		// create new trade
		Trade newTrade = new Trade();
		// set trade type
		newTrade.setTrade_type("Repurchase");
		// set trade quality
		newTrade.setTrade_quality("Clean");
		// set trade level
		newTrade.setTrade_level("PSTN");
		newTrade.setAction_type(tradeService.setTradeActionType(newTrade));
		
		Assertions.assertNotNull(newTrade.getAction_type());
		Assertions.assertNotEquals("POSC", newTrade.getAction_type());
		Assertions.assertNotEquals("VALU", newTrade.getAction_type());
	}
	
	// REGION CREATE TRADE
	
	@Test
	public void testThat_CreateTrade_works_ForMarginLending() {
		Trade newTrade = tradeService.createTrade("Margin Lending", "Clean",  "");
		
		Assertions.assertNotNull(newTrade.getTrade_type());
		Assertions.assertNotNull(newTrade.getTrade_quality());
		Assertions.assertNotNull(newTrade.getTrade_level());
		Assertions.assertNotNull(newTrade.getAction_type());
		
		Assertions.assertEquals("Margin Lending", newTrade.getTrade_type());
		Assertions.assertEquals("Clean", newTrade.getTrade_quality());
		Assertions.assertEquals("TCTN", newTrade.getTrade_level());
		Assertions.assertNotEquals("POSC", newTrade.getAction_type());
		Assertions.assertNotEquals("VALU", newTrade.getAction_type());
	}
	
	@Test
	public void testThat_CreateTrade_works_ForBuySellBack() {
		Trade newTrade = tradeService.createTrade("Buy/Sell Back", "Clean", "");
		
		Assertions.assertNotNull(newTrade.getTrade_type());
		Assertions.assertNotNull(newTrade.getTrade_quality());
		Assertions.assertNotNull(newTrade.getTrade_level());
		Assertions.assertNotNull(newTrade.getAction_type());
		
		Assertions.assertEquals("Buy/Sell Back", newTrade.getTrade_type());
		Assertions.assertEquals("Clean", newTrade.getTrade_quality());
		Assertions.assertNotEquals("VALU", newTrade.getAction_type());
	}
	
	@Test
	public void testThat_CreateTrade_works_ForRepurchase() {
		Trade newTrade = tradeService.createTrade("Repurchase", "Clean", "");
		
		Assertions.assertNotNull(newTrade.getTrade_type());
		Assertions.assertNotNull(newTrade.getTrade_quality());
		Assertions.assertNotNull(newTrade.getTrade_level());
		Assertions.assertNotNull(newTrade.getAction_type());
		
		Assertions.assertEquals("Repurchase", newTrade.getTrade_type());
		Assertions.assertEquals("Clean", newTrade.getTrade_quality());
	}
	
	@Test
	public void testThat_CreateTrade_works_ForSecComLending() {
		Trade newTrade = tradeService.createTrade("Sec-Com Lending", "Clean", "");
		
		Assertions.assertNotNull(newTrade.getTrade_type());
		Assertions.assertNotNull(newTrade.getTrade_quality());
		Assertions.assertNotNull(newTrade.getTrade_level());
		Assertions.assertNotNull(newTrade.getAction_type());
		
		Assertions.assertEquals("Sec-Com Lending", newTrade.getTrade_type());
		Assertions.assertEquals("Clean", newTrade.getTrade_quality());
	}

}
