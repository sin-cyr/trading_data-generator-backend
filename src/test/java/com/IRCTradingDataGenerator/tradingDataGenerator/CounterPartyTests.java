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
import com.IRCTradingDataGenerator.tradingDataGenerator.Services.DataService;
import com.IRCTradingDataGenerator.tradingDataGenerator.Services.TradeService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class CounterPartyTests {
	
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
	public void testThatCounterPartyCanBeCreated() {
		
		Counterparty counterParty = new Counterparty();
		// Set CP A
		counterParty.setReport_SubmittingEntity(tradeService.getLEI());
		counterParty.setEntity_responsible_for_report(counterParty.getReport_SubmittingEntity());
		counterParty.setReporting_counterparty(counterParty.getReport_SubmittingEntity());
		// Set CP B
		counterParty.setOther_counterparty(tradeService.getLEI());
		counterParty.setReporting_timestamp(dataService.createDateAndTimeFromToday());
		counterParty.setBranch_reporting_counterparty(tradeService.getCountry(counterParty.getReporting_counterparty()));
		counterParty.setBranch_other_counterparty(tradeService.getCountry(counterParty.getOther_counterparty()));
		counterParty.setOther_counterparty_country(tradeService.getCountry(counterParty.getOther_counterparty()));
		counterParty.setCounterparty_nature(tradeService.getNature());
		counterParty.setSector_of_reporting_counterparty(tradeService.getSectorClass(counterParty.getCounterparty_nature()));
		counterParty.setAdditional_sector_classification(tradeService.getAddSectorClass());
		counterParty.setCounterparty_side(tradeService.getCounterPartySide());
		tradeService.save(counterParty);
		Assertions.assertNotNull(counterParty.getCounterparty_id());
	}
	
}
