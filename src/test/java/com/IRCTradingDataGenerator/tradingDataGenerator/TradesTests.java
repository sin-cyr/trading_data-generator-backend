package com.IRCTradingDataGenerator.tradingDataGenerator;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

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
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.SharedHttpSessionConfigurer;
import org.springframework.web.context.WebApplicationContext;

import com.IRCTradingDataGenerator.tradingDataGenerator.Models.Collateral;
import com.IRCTradingDataGenerator.tradingDataGenerator.Models.Counterparty;
import com.IRCTradingDataGenerator.tradingDataGenerator.Models.Principle;
import com.IRCTradingDataGenerator.tradingDataGenerator.Models.TradeRequest;
import com.IRCTradingDataGenerator.tradingDataGenerator.Models.Trade;
import com.IRCTradingDataGenerator.tradingDataGenerator.Models.Transaction;
import com.IRCTradingDataGenerator.tradingDataGenerator.Services.DataService;
import com.IRCTradingDataGenerator.tradingDataGenerator.Services.TradeService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class TradesTests {
	
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
	
	final static String TRADE_CONTROLLER_URI = "/tradeController";
	
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
	public void testThatTradeCanBeCreated() {
		String tradeType = "Margin Lending";
		Trade trade = new Trade();
		trade.setTrade_level("TCTN");
		trade.setTrade_quality("Clean");
		trade.setReporting_timestamp(dataService.createDateAndTimeFromToday());
		
		Transaction transaction = new Transaction();
		transaction.setType_Of_Sft(tradeService.getSFT(tradeType));
		transaction.setEvent_date(dataService.createDateFromToday());
		transaction.setTermination_date(dataService.createDateFromToday());
		transaction.setExecution_timestamp(dataService.createDateAndTimeFromToday());
		tradeService.save(transaction);
		
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
		
		Collateral collateral = new Collateral();
		collateral.setLEI_of_issuer(counterParty.getReporting_counterparty());
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
		collateral.setEstimated_reuse_of_collateral(dataService.createNumeric18().toString());
		collateral.setValue_of_reused_collateral(dataService.createNumeric18().toString());
		collateral.setMaturity_date_of_the_security(dataService.createDateFromToday());
		collateral.setClassification_of_security_used_as_collateral(dataService.createISIN(collateral.getLEI_of_issuer())); 
		collateral.setIdentification_of_security_as_collateral(dataService.createCFI());
		collateral.setAvailability_for_collateral_reuse(dataService.getTrueOrFlase());
		tradeService.save(collateral);
		
		Principle principle = new Principle();
		principle.setDay_count_convention(tradeService.getDayCountConvention());
		principle.setFloat_ref_period_time(tradeService.getTimePeriod());
		principle.setFloat_pay_freq_time(tradeService.getTimePeriod());
		principle.setFloat_reset_freq_time(tradeService.getTimePeriod());
		principle.setMargin_currency(tradeService.getCurrency(collateral.getJurisdiction_of_issuer()));
		principle.setOutstanding_ML_base_currency(tradeService.getCurrency(collateral.getJurisdiction_of_issuer()));
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
		
		trade.setTrade_transaction(transaction);
		trade.setTrade_counterparty(counterParty);
		trade.setTrade_collateral(collateral);
		trade.setTrade_principle(principle);
		trade.setTrade_level(tradeService.getLevel());
		trade.setTrade_quality("Dirty");
		trade.setTrade_type(tradeType);
		tradeService.save(trade);
		
		Assertions.assertNotNull(trade);
	}
	
	@Test
	public void testThat_TradeCanBeCreated_ViaController() throws Exception {
		
		TradeRequest tradeRequest = new TradeRequest("Margin Lending", 10, "");

		ResultActions result = this.mockMvc.perform(post(TRADE_CONTROLLER_URI + "/CreateTradeProduct")
				.session(session)
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(tradeRequest)))
			.andExpect(status().isOk());
		Assertions.assertNotNull(result);
	}
	
	@Test 
	public void testThat_AllTrades_CanBeRetrieved() {
		List<Trade> allTrades = tradeService.getTrades();
		Assertions.assertTrue(allTrades.size() > 0);
	}
	
	@Test
	public void testThat_AllTrades_CanBeretrieved_Viacontroller() throws Exception {
		
		ResultActions result = this.mockMvc.perform(get(TRADE_CONTROLLER_URI + "/GetAllTradeProducts")
				.session(session)
				.contentType("application/json"))
			.andExpect(status().isOk());
		Assertions.assertNotNull(result);
	}
	
	@Test 
	public void testThat_SingleTrade_CanBeRetrieved() {
		List<Trade> allTrades = tradeService.getTrades();
		int tradeId = allTrades.get(0).getTrade_Id();
		Trade tradeFromDb = tradeService.getTradeById(tradeId).get();
	
		Assertions.assertEquals(tradeId, tradeFromDb.getTrade_Id());
		
	}
	
	@Test 
	public void testThat_SingleTrade_CanBeRetrieved_ViaController() throws Exception {

		ResultActions result = this.mockMvc.perform(get(TRADE_CONTROLLER_URI + "/GetTradeProductById/" + 1)
				.session(session)
				.contentType("application/json"))
			.andExpect(status().isOk());

		Assertions.assertNotNull(result);
	}

}
