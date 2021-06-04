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

import com.IRCTradingDataGenerator.tradingDataGenerator.Models.Principle;
import com.IRCTradingDataGenerator.tradingDataGenerator.Services.DataService;
import com.IRCTradingDataGenerator.tradingDataGenerator.Services.TradeService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class PrincipleTests {
	
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
	public void testThatFullPrincipleCanBeCreated()  {
		String LEI = tradeService.getLEI();
		String country = tradeService.getCountry(LEI);
		
		Principle principle = new Principle();
		principle.setDay_count_convention(tradeService.getDayCountConvention());
		principle.setFloat_ref_period_time(tradeService.getTimePeriod());
		principle.setFloat_pay_freq_time(tradeService.getTimePeriod());
		principle.setFloat_reset_freq_time(tradeService.getTimePeriod());
		principle.setMargin_currency(tradeService.getCurrency(country));
		principle.setOutstanding_ML_base_currency(tradeService.getCurrency(country));
		principle.setFloating_rate(tradeService.getFloatingRate());
		principle.setFixed_rate(dataService.createNumeric11().toString());
		principle.setOutstanding_margin_loan(dataService.createNumeric18().toString());
		principle.setShort_market_value(dataService.createNumeric18().toString());
		principle.setMargin_currency_amount(dataService.createNumeric18().toString());
		principle.setFloat_ref_period_multi(dataService.createNumeric3().toString());
		principle.setFloat_pay_freq_multi(dataService.createNumeric3().toString());
		principle.setFloat_reset_freq_multi(dataService.createNumeric3().toString());
		principle.setSpread(dataService.createNumeric5().toString());
		tradeService.save(principle);
		Assertions.assertNotNull(principle);
	}

}

