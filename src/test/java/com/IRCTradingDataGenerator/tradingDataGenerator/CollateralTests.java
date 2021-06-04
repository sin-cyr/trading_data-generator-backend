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
import com.IRCTradingDataGenerator.tradingDataGenerator.Services.DataService;
import com.IRCTradingDataGenerator.tradingDataGenerator.Services.TradeService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class CollateralTests {
	
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
	public void testThatCollateralCanBeCreated() {
		Collateral collateral = new Collateral();
		collateral.setLEI_of_issuer(tradeService.getLEI());
		collateral.setJurisdiction_of_issuer(tradeService.getCountry(collateral.getLEI_of_issuer()));
		collateral.setReused_collateral_currency(tradeService.getCurrency(collateral.getJurisdiction_of_issuer()));
		collateral.setFunding_source_currency(tradeService.getCurrency(collateral.getJurisdiction_of_issuer()));
		collateral.setFunding_source(tradeService.getFundingSource());
		collateral.setPrice_currency(tradeService.getCurrency(collateral.getJurisdiction_of_issuer()));
		collateral.setCurrency_collateral_nom_amount(tradeService.getCurrency(collateral.getJurisdiction_of_issuer()));
		collateral.setCollateral_component(tradeService.getCollateralComponent());
		collateral.setCollateral_quality(tradeService.getCollateralQuality());
		collateral.setCollateral_type(tradeService.getCollteralType());
		collateral.setMethod_to_provide_collateral(tradeService.getMethodToProvideCollateral());
		collateral.setCollateral_market_value(dataService.createNumeric18().toString());
		collateral.setPrice_per_unit(dataService.createNumeric11().toString());
		collateral.setCollateral_quantity_or_nom_amount(dataService.createNumeric18().toString());
		collateral.setHaircut_or_margin(dataService.createNumeric11().toString());
		collateral.setMarket_value_of_funding_sources(dataService.createNumeric18().toString());
		collateral.setEstimated_reuse_of_collateral(dataService.createNumeric18().toString());
		collateral.setValue_of_reused_collateral(dataService.createNumeric18().toString());
		collateral.setMaturity_date_of_the_security(dataService.createDateFromToday());
		collateral.setClassification_of_security_used_as_collateral(dataService.createNumeric18().toString());
		collateral.setIdentification_of_security_as_collateral(dataService.createNumeric18().toString());
		collateral.setAvailability_for_collateral_reuse(dataService.getTrueOrFlase());
		tradeService.save(collateral);
		Assertions.assertNotNull(collateral);
	}

}
