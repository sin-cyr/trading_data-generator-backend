package com.IRCTradingDataGenerator.tradingDataGenerator;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

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
public class DataQualityTests {
	
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
	public void testThat_CreateDirtyStringWorks() {
		String data = "Hello";
		String dirtyData = dataService.createDirtyString(data);
		Assertions.assertNotEquals(dirtyData, data);
	}
	
	@Test
	public void testThat_CreateDirtyNumericWorks_ForNumeric3() {
		BigDecimal number = dataService.createNumeric3();
		String dirtyNumber = dataService.createDirtyNumeric(number);
		Assertions.assertNotEquals(number, dirtyNumber);
	}
	
	@Test
	public void testThat_CreateDirtyNumericWorks_ForNumeric5() {
		BigDecimal number = dataService.createNumeric5();
		String dirtyNumber = dataService.createDirtyNumeric(number);
		Assertions.assertNotEquals(number, dirtyNumber);
	}
	
	@Test
	public void testThat_CreateDirtyNumericWorks_ForNumeric11() {
		BigDecimal number = dataService.createNumeric11();
		String dirtyNumber = dataService.createDirtyNumeric(number);
		Assertions.assertNotEquals(number, dirtyNumber);
	}
	
	@Test
	public void testThat_CreateDirtyNumericWorks_ForNumeric18() {
		BigDecimal number = dataService.createNumeric18();
		String dirtyNumber = dataService.createDirtyNumeric(number);
		Assertions.assertNotEquals(number, dirtyNumber);
	}
	
	@Test
	public void test_GetTransaction_Dirty() {
		String tradeType = "Margin Lending";
		
		Transaction transaction = new Transaction();
		transaction.setType_Of_Sft(dataService.createDirtyString(tradeService.getSFT(tradeType))); // typo/ missing data
		transaction.setEvent_date(dataService.createDirtyDate()); // wrong format / missing data
		transaction.setTermination_date(dataService.createDirtyDate()); // wrong format / missing data
		transaction.setExecution_timestamp(dataService.createDirtyDateTime()); // wrong format / missing data
		tradeService.save(transaction);
		Assertions.assertNotEquals(transaction.getType_Of_Sft(), "SLEB");
		Assertions.assertNotEquals(transaction.getType_Of_Sft(), "SBSC");
		Assertions.assertNotEquals(transaction.getType_Of_Sft(), "REPO");
		Assertions.assertNotEquals(transaction.getType_Of_Sft(), "MGLD");
	}
	
	@Test
	public void test_GetTransaction_Clean() {
		String tradeType = "Margin Lending";
		
		Transaction transaction = new Transaction();
		transaction.setType_Of_Sft(tradeService.getSFT(tradeType));
		transaction.setEvent_date(dataService.createDateFromToday());
		transaction.setTermination_date(dataService.createDateFromToday()); 
		transaction.setExecution_timestamp(dataService.createDateAndTimeFromToday());
		tradeService.save(transaction);
		Assertions.assertEquals(transaction.getType_Of_Sft(), "MGLD");
	}

	@Test
	public void test_GetCounterParty_Dirty() {
		Counterparty counterParty = new Counterparty();
		// Set CP A
		counterParty.setReport_SubmittingEntity(tradeService.getLEI());
		counterParty.setEntity_responsible_for_report(dataService.createDirtyString(counterParty.getReport_SubmittingEntity()));
		counterParty.setReporting_counterparty(dataService.createDirtyString(counterParty.getReport_SubmittingEntity()));
		// Set CP B
		counterParty.setOther_counterparty(tradeService.getLEI(counterParty.getReport_SubmittingEntity()));
		counterParty.setReporting_timestamp(dataService.createDateAndTimeFromTodayDirty());
		counterParty.setBranch_reporting_counterparty(tradeService.getCountryDirty(counterParty.getReporting_counterparty()));
		counterParty.setBranch_other_counterparty(tradeService.getCountryDirty(counterParty.getOther_counterparty()));
		counterParty.setOther_counterparty_country(tradeService.getCountryDirty(counterParty.getOther_counterparty()));
		counterParty.setCounterparty_nature(tradeService.getNature());
		counterParty.setSector_of_reporting_counterparty(tradeService.getSectorClassDirty(counterParty.getCounterparty_nature()));
		counterParty.setAdditional_sector_classification(tradeService.getAddSectorClass());
		counterParty.setCounterparty_side(tradeService.getCounterPartySide());
		tradeService.save(counterParty);
		Assertions.assertNotEquals(counterParty.getBranch_reporting_counterparty(), tradeService.getCountry(counterParty.getReporting_counterparty()));
		Assertions.assertNotEquals(counterParty.getBranch_other_counterparty(), tradeService.getCountry(counterParty.getOther_counterparty()));
	
	}
	

	@Test
	public void test_GetCounterParty_Clean() {
		
		Counterparty counterParty = new Counterparty();
		// Set CP A
		counterParty.setReport_SubmittingEntity(tradeService.getLEI());
		counterParty.setEntity_responsible_for_report(counterParty.getReport_SubmittingEntity());
		counterParty.setReporting_counterparty(counterParty.getReport_SubmittingEntity());
		// Set CP B
		counterParty.setOther_counterparty(tradeService.getLEI(counterParty.getReport_SubmittingEntity()));
		counterParty.setReporting_timestamp(dataService.createDateAndTimeFromToday());
		counterParty.setBranch_reporting_counterparty(tradeService.getCountry(counterParty.getReporting_counterparty()));
		counterParty.setBranch_other_counterparty(tradeService.getCountry(counterParty.getOther_counterparty()));
		counterParty.setOther_counterparty_country(tradeService.getCountry(counterParty.getOther_counterparty()));
		counterParty.setCounterparty_nature(tradeService.getNature());
		counterParty.setSector_of_reporting_counterparty(tradeService.getSectorClass(counterParty.getCounterparty_nature()));
		counterParty.setAdditional_sector_classification(tradeService.getAddSectorClass());
		counterParty.setCounterparty_side(tradeService.getCounterPartySide());
		tradeService.save(counterParty);
		Assertions.assertEquals(counterParty.getBranch_reporting_counterparty(), tradeService.getCountry(counterParty.getReporting_counterparty()));
		Assertions.assertEquals(counterParty.getBranch_other_counterparty(), tradeService.getCountry(counterParty.getOther_counterparty()));
	
	}
	
	
	@Test
	public void test_GetPrinciple_Dirty() {
		String LEI = tradeService.getLEI();
		String country = tradeService.getCountry(LEI);
		
		Principle principle = new Principle();
		principle.setDay_count_convention(dataService.createDirtyString(tradeService.getDayCountConvention())); // typo / missing data
		principle.setFloat_ref_period_time(dataService.createDirtyString(tradeService.getTimePeriod())); // typo / missing data
		principle.setFloat_pay_freq_time(dataService.createDirtyString(tradeService.getTimePeriod())); // typo / missing data
		principle.setFloat_reset_freq_time(dataService.createDirtyString(tradeService.getTimePeriod())); // typo / missing data
		principle.setMargin_currency(tradeService.getCurrencyDirty(country)); // ## mismatch / typo / missing data
		principle.setOutstanding_ML_base_currency(tradeService.getCurrencyDirty(country)); // ## mismatch / typo / missing data
		principle.setFloating_rate(dataService.createDirtyString(tradeService.getFloatingRate())); // typo / missing data
		principle.setFixed_rate(dataService.createDirtyNumeric(dataService.createNumeric11())); // wrong format / missing data 
		principle.setOutstanding_margin_loan(dataService.createDirtyNumeric(dataService.createNumeric18())); // wrong format / missing data 
		principle.setShort_market_value(dataService.createDirtyNumeric(dataService.createNumeric18())); // wrong format / missing data 
		principle.setMargin_currency_amount(dataService.createDirtyNumeric(dataService.createNumeric18())); // wrong format / missing data 
		principle.setFloat_ref_period_multi(dataService.createDirtyNumeric(dataService.createNumeric3())); // wrong format / missing data 
		principle.setFloat_pay_freq_multi(dataService.createDirtyNumeric(dataService.createNumeric3())); // wrong format / missing data 
		principle.setFloat_reset_freq_multi(dataService.createDirtyNumeric(dataService.createNumeric3())); // wrong format / missing data 
		principle.setSpread(dataService.createDirtyNumeric(dataService.createNumeric5())); // wrong format / missing data 
		tradeService.save(principle);
		Assertions.assertNotEquals(principle.getMargin_currency(), tradeService.getCurrencyDirty(country));
		Assertions.assertNotEquals(principle.getOutstanding_ML_base_currency(), tradeService.getCurrencyDirty(country));
	}
	
	@Test
	public void test_GetPrinciple_Clean() {
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
		Assertions.assertEquals(principle.getMargin_currency(), tradeService.getCurrency(country));
		Assertions.assertEquals(principle.getOutstanding_ML_base_currency(), tradeService.getCurrency(country));
	
	}
	
	
	@Test
	public void test_GetCollateral_Dirty() {
		
		Collateral collateral = new Collateral();
		collateral.setLEI_of_issuer(tradeService.getLEI()); // ## mismatch / typo / missing data
		collateral.setJurisdiction_of_issuer(tradeService.getCountryDirty(collateral.getLEI_of_issuer())); // ## mismatch / typo / missing data
		collateral.setReused_collateral_currency(tradeService.getCurrencyDirty(collateral.getJurisdiction_of_issuer())); // ## mismatch / typo / missing data
		collateral.setFunding_source_currency(tradeService.getCurrencyDirty(collateral.getJurisdiction_of_issuer())); // ## mismatch / typo / missing data
		collateral.setFunding_source(dataService.createDirtyString(tradeService.getFundingSource())); // typo / missing data
		collateral.setPrice_currency(tradeService.getCurrencyDirty(collateral.getJurisdiction_of_issuer())); // ## mismatch / typo / missing data
		collateral.setCurrency_collateral_nom_amount(tradeService.getCurrencyDirty(collateral.getJurisdiction_of_issuer())); // ## mismatch / typo / missing data
		collateral.setCollateral_component(dataService.createDirtyString(tradeService.getCollateralComponent())); // typo / missing data
		collateral.setCollateral_quality(dataService.createDirtyString(tradeService.getCollateralQuality())); // typo / missing data
		collateral.setCollateral_type(dataService.createDirtyString(tradeService.getCollteralType())); // typo / missing data
		collateral.setMethod_to_provide_collateral(dataService.createDirtyString(tradeService.getMethodToProvideCollateral())); // typo / missing data
		
		collateral.setCollateral_market_value(dataService.createDirtyNumeric(dataService.createNumeric11())); // wrong format / missing data
		collateral.setPrice_per_unit(dataService.createDirtyNumeric(dataService.createNumeric11())); // wrong format / missing data
		collateral.setCollateral_quantity_or_nom_amount(dataService.createDirtyNumeric(dataService.createNumeric18())); // wrong format / missing data
		collateral.setHaircut_or_margin(dataService.createDirtyNumeric(dataService.createNumeric11())); // wrong format / missing data
		collateral.setEstimated_reuse_of_collateral(dataService.createDirtyNumeric(dataService.createNumeric18())); // wrong format / missing data
		collateral.setValue_of_reused_collateral(dataService.createDirtyNumeric(dataService.createNumeric18())); // wrong format / missing data
		collateral.setMaturity_date_of_the_security(dataService.createDirtyDate()); // wrong format / missing data
		collateral.setClassification_of_security_used_as_collateral(dataService.createDirtyString(dataService.createISIN(collateral.getLEI_of_issuer()))); // wrong format / missing data
		collateral.setIdentification_of_security_as_collateral(dataService.createDirtyString(dataService.createCFI()));// wrong format / missing data
		collateral.setAvailability_for_collateral_reuse(dataService.getTrueOrFlase());// true or false
		tradeService.save(collateral);
		Assertions.assertNotEquals(collateral.getJurisdiction_of_issuer(), tradeService.getCountryDirty(collateral.getLEI_of_issuer()));
		Assertions.assertNotEquals(collateral.getReused_collateral_currency(), tradeService.getCurrencyDirty(collateral.getJurisdiction_of_issuer()));
		Assertions.assertNotEquals(collateral.getFunding_source_currency(), tradeService.getCurrencyDirty(collateral.getJurisdiction_of_issuer()));
		Assertions.assertNotEquals(collateral.getPrice_currency(), tradeService.getCurrencyDirty(collateral.getJurisdiction_of_issuer()));
		Assertions.assertNotEquals(collateral.getCurrency_collateral_nom_amount(), tradeService.getCurrencyDirty(collateral.getJurisdiction_of_issuer()));
	}
	
	@Test
	public void test_GetCollateral_Clean() {
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
		collateral.setCollateral_market_value(dataService.createNumeric11().toString()); 
		collateral.setPrice_per_unit(dataService.createNumeric11().toString());
		collateral.setCollateral_quantity_or_nom_amount(dataService.createNumeric18().toString()); 
		collateral.setHaircut_or_margin(dataService.createNumeric11().toString());
		collateral.setMarket_value_of_funding_sources(dataService.createNumeric18().toString());
		collateral.setEstimated_reuse_of_collateral(dataService.createNumeric18().toString());
		collateral.setValue_of_reused_collateral(dataService.createNumeric18().toString());
		collateral.setMaturity_date_of_the_security(dataService.createDateFromToday());
		collateral.setClassification_of_security_used_as_collateral(dataService.createISIN(collateral.getLEI_of_issuer())); // wrong format / missing data
		collateral.setIdentification_of_security_as_collateral(dataService.createCFI());// wrong format / missing data
		collateral.setAvailability_for_collateral_reuse(dataService.getTrueOrFlase());// true or false
		tradeService.save(collateral);
		Assertions.assertEquals(collateral.getJurisdiction_of_issuer(), tradeService.getCountry(collateral.getLEI_of_issuer()));
		Assertions.assertEquals(collateral.getReused_collateral_currency(), tradeService.getCurrency(collateral.getJurisdiction_of_issuer()));
		Assertions.assertEquals(collateral.getFunding_source_currency(), tradeService.getCurrency(collateral.getJurisdiction_of_issuer()));
		Assertions.assertEquals(collateral.getPrice_currency(), tradeService.getCurrency(collateral.getJurisdiction_of_issuer()));
		Assertions.assertEquals(collateral.getCurrency_collateral_nom_amount(), tradeService.getCurrency(collateral.getJurisdiction_of_issuer()));
	
	}
	
	@Test
	public void testThat_FullTrade_CanBeDirty() {
		String tradeType = "Margin Lending";
		Trade trade = new Trade();
		
		Transaction transaction = new Transaction();
		transaction.setType_Of_Sft(dataService.createDirtyString(tradeService.getSFT(tradeType))); // typo / missing data
		transaction.setEvent_date(dataService.createDirtyDate()); // wrong format / missing data
		transaction.setTermination_date(dataService.createDirtyDate()); // wrong format / missing data
		transaction.setExecution_timestamp(dataService.createDirtyDateTime()); // wrong format / missing data
		tradeService.save(transaction);
		
		
		Counterparty counterParty = new Counterparty();
		// Set CP A
		counterParty.setReport_SubmittingEntity(tradeService.getLEI());
		counterParty.setEntity_responsible_for_report(dataService.createDirtyString(counterParty.getReport_SubmittingEntity()));
		counterParty.setReporting_counterparty(dataService.createDirtyString(counterParty.getReport_SubmittingEntity()));
		// Set CP B
		counterParty.setOther_counterparty(tradeService.getLEI(counterParty.getReport_SubmittingEntity()));
		counterParty.setReporting_timestamp(dataService.createDateAndTimeFromTodayDirty());
		counterParty.setBranch_reporting_counterparty(tradeService.getCountryDirty(counterParty.getReporting_counterparty()));
		counterParty.setBranch_other_counterparty(tradeService.getCountryDirty(counterParty.getOther_counterparty()));
		counterParty.setOther_counterparty_country(tradeService.getCountryDirty(counterParty.getOther_counterparty()));
		counterParty.setCounterparty_nature(tradeService.getNature());
		counterParty.setSector_of_reporting_counterparty(tradeService.getSectorClassDirty(counterParty.getCounterparty_nature()));
		counterParty.setAdditional_sector_classification(tradeService.getAddSectorClass());
		counterParty.setCounterparty_side(tradeService.getCounterPartySide());
		tradeService.save(counterParty);
		
		Principle principle = new Principle();
		principle.setDay_count_convention(dataService.createDirtyString(tradeService.getDayCountConvention())); // typo / missing data
		principle.setFloat_ref_period_time(dataService.createDirtyString(tradeService.getTimePeriod())); // typo / missing data
		principle.setFloat_pay_freq_time(dataService.createDirtyString(tradeService.getTimePeriod())); // typo / missing data
		principle.setFloat_reset_freq_time(dataService.createDirtyString(tradeService.getTimePeriod())); // typo / missing data
		principle.setMargin_currency(tradeService.getCurrencyDirty(counterParty.getBranch_reporting_counterparty())); // ## mismatch / typo / missing data
		principle.setOutstanding_ML_base_currency(tradeService.getCurrencyDirty(counterParty.getBranch_reporting_counterparty())); // ## mismatch / typo / missing data
		principle.setFloating_rate(dataService.createDirtyString(tradeService.getFloatingRate())); // typo / missing data
		principle.setFixed_rate(dataService.createDirtyNumeric(dataService.createNumeric11())); // wrong format / missing data 
		principle.setOutstanding_margin_loan(dataService.createDirtyNumeric(dataService.createNumeric18())); // wrong format / missing data 
		principle.setShort_market_value(dataService.createDirtyNumeric(dataService.createNumeric18())); // wrong format / missing data 
		principle.setMargin_currency_amount(dataService.createDirtyNumeric(dataService.createNumeric18())); // wrong format / missing data 
		principle.setFloat_ref_period_multi(dataService.createDirtyNumeric(dataService.createNumeric3())); // wrong format / missing data 
		principle.setFloat_pay_freq_multi(dataService.createDirtyNumeric(dataService.createNumeric3())); // wrong format / missing data 
		principle.setFloat_reset_freq_multi(dataService.createDirtyNumeric(dataService.createNumeric3())); // wrong format / missing data 
		principle.setSpread(dataService.createDirtyNumeric(dataService.createNumeric5())); // wrong format / missing data 
		tradeService.save(principle);
		
		Collateral collateral = new Collateral();
		collateral.setLEI_of_issuer(counterParty.getOther_counterparty()); // ## mismatch / typo / missing data
		collateral.setJurisdiction_of_issuer(tradeService.getCountryDirty(collateral.getLEI_of_issuer())); // ## mismatch / typo / missing data
		collateral.setReused_collateral_currency(tradeService.getCurrencyDirty(collateral.getJurisdiction_of_issuer())); // ## mismatch / typo / missing data
		collateral.setFunding_source_currency(tradeService.getCurrencyDirty(collateral.getJurisdiction_of_issuer())); // ## mismatch / typo / missing data
		collateral.setFunding_source(dataService.createDirtyString(tradeService.getFundingSource())); // typo / missing data
		collateral.setPrice_currency(tradeService.getCurrencyDirty(collateral.getJurisdiction_of_issuer())); // ## mismatch / typo / missing data
		collateral.setCurrency_collateral_nom_amount(tradeService.getCurrencyDirty(collateral.getJurisdiction_of_issuer())); // ## mismatch / typo / missing data
		collateral.setCollateral_component(dataService.createDirtyString(tradeService.getCollateralComponent())); // typo / missing data
		collateral.setCollateral_quality(dataService.createDirtyString(tradeService.getCollateralQuality())); // typo / missing data
		collateral.setCollateral_type(dataService.createDirtyString(tradeService.getCollteralType())); // typo / missing data
		collateral.setMethod_to_provide_collateral(dataService.createDirtyString(tradeService.getMethodToProvideCollateral())); // typo / missing data
		collateral.setCollateral_market_value(dataService.createDirtyNumeric(dataService.createNumeric11())); // wrong format / missing data
		collateral.setPrice_per_unit(dataService.createDirtyNumeric(dataService.createNumeric11())); // wrong format / missing data
		collateral.setCollateral_quantity_or_nom_amount(dataService.createDirtyNumeric(dataService.createNumeric18())); // wrong format / missing data
		collateral.setHaircut_or_margin(dataService.createDirtyNumeric(dataService.createNumeric11())); // wrong format / missing data
		collateral.setMarket_value_of_funding_sources(dataService.createDirtyNumeric(dataService.createNumeric18()));
		collateral.setEstimated_reuse_of_collateral(dataService.createDirtyNumeric(dataService.createNumeric18())); // wrong format / missing data
		collateral.setValue_of_reused_collateral(dataService.createDirtyNumeric(dataService.createNumeric18())); // wrong format / missing data
		collateral.setMaturity_date_of_the_security(dataService.createDirtyDate()); // wrong format / missing data
		collateral.setClassification_of_security_used_as_collateral(dataService.createDirtyString(dataService.createISIN(collateral.getLEI_of_issuer()))); // wrong format / missing data
		collateral.setIdentification_of_security_as_collateral(dataService.createDirtyString(dataService.createCFI()));// wrong format / missing data
		collateral.setAvailability_for_collateral_reuse(dataService.getTrueOrFlase());// true or false
		tradeService.save(collateral);
		
		trade.setTrade_transaction(transaction);
		trade.setTrade_counterparty(counterParty);
		trade.setTrade_collateral(collateral);
		trade.setTrade_principle(principle);
		trade.setTrade_level(tradeService.getLevel());
		trade.setTrade_quality("Dirty");
		trade.setTrade_type(tradeType);
		tradeService.save(trade);
		Assertions.assertNotEquals(trade.getTrade_counterparty().getBranch_reporting_counterparty(), tradeService.getCountry(trade.getTrade_counterparty().getReporting_counterparty()));
		Assertions.assertNotEquals(trade.getTrade_counterparty().getBranch_other_counterparty(), tradeService.getCountry(trade.getTrade_counterparty().getOther_counterparty()));
	
	}
	
	
	@Test
	public void testThat_FullTrade_CanBeClean() {
		String tradeType = "Margin Lending";
		Trade trade = new Trade();
		
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
		counterParty.setOther_counterparty(tradeService.getLEI(counterParty.getReport_SubmittingEntity()));
		counterParty.setReporting_timestamp(dataService.createDateAndTimeFromToday());
		counterParty.setBranch_reporting_counterparty(tradeService.getCountry(counterParty.getReporting_counterparty()));
		counterParty.setBranch_other_counterparty(tradeService.getCountry(counterParty.getOther_counterparty()));
		counterParty.setOther_counterparty_country(tradeService.getCountry(counterParty.getOther_counterparty()));
		counterParty.setCounterparty_nature(tradeService.getNature());
		counterParty.setSector_of_reporting_counterparty(tradeService.getSectorClass(counterParty.getCounterparty_nature()));
		counterParty.setAdditional_sector_classification(tradeService.getAddSectorClass());
		counterParty.setCounterparty_side(tradeService.getCounterPartySide());
		tradeService.save(counterParty);
		
		Principle principle = new Principle();
		principle.setDay_count_convention(tradeService.getDayCountConvention()); 
		principle.setFloat_ref_period_time(tradeService.getTimePeriod());
		principle.setFloat_pay_freq_time(tradeService.getTimePeriod());
		principle.setFloat_reset_freq_time(tradeService.getTimePeriod());
		principle.setMargin_currency(tradeService.getCurrency(counterParty.getBranch_reporting_counterparty())); 
		principle.setOutstanding_ML_base_currency(tradeService.getCurrency(counterParty.getBranch_reporting_counterparty()));
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
		collateral.setCollateral_market_value(dataService.createNumeric11().toString()); 
		collateral.setPrice_per_unit(dataService.createNumeric11().toString());
		collateral.setCollateral_quantity_or_nom_amount(dataService.createNumeric18().toString()); 
		collateral.setHaircut_or_margin(dataService.createNumeric11().toString());
		collateral.setMarket_value_of_funding_sources(dataService.createNumeric18().toString());
		collateral.setEstimated_reuse_of_collateral(dataService.createNumeric18().toString());
		collateral.setValue_of_reused_collateral(dataService.createNumeric18().toString());
		collateral.setMaturity_date_of_the_security(dataService.createDateFromToday());
		collateral.setClassification_of_security_used_as_collateral(dataService.createISIN(collateral.getLEI_of_issuer())); 
		collateral.setIdentification_of_security_as_collateral(dataService.createCFI());
		collateral.setAvailability_for_collateral_reuse(dataService.getTrueOrFlase());
		tradeService.save(collateral);
		
		trade.setTrade_transaction(transaction);
		trade.setTrade_counterparty(counterParty);
		trade.setTrade_collateral(collateral);
		trade.setTrade_principle(principle);
		trade.setTrade_level(tradeService.getLevel());
		trade.setTrade_quality("Dirty");
		trade.setTrade_type(tradeType);
		tradeService.save(trade);
		Assertions.assertEquals(transaction.getType_Of_Sft(), "MGLD");
		Assertions.assertEquals(trade.getTrade_counterparty().getBranch_reporting_counterparty(), tradeService.getCountry(trade.getTrade_counterparty().getReporting_counterparty()));
		Assertions.assertEquals(trade.getTrade_counterparty().getBranch_other_counterparty(), tradeService.getCountry(trade.getTrade_counterparty().getOther_counterparty()));
		Assertions.assertEquals(principle.getMargin_currency(), tradeService.getCurrency(trade.getTrade_counterparty().getBranch_reporting_counterparty()));
		Assertions.assertEquals(principle.getOutstanding_ML_base_currency(), tradeService.getCurrency(trade.getTrade_counterparty().getBranch_reporting_counterparty()));
		Assertions.assertEquals(collateral.getJurisdiction_of_issuer(), tradeService.getCountry(collateral.getLEI_of_issuer()));
		Assertions.assertEquals(collateral.getReused_collateral_currency(), tradeService.getCurrency(collateral.getJurisdiction_of_issuer()));
		Assertions.assertEquals(collateral.getFunding_source_currency(), tradeService.getCurrency(collateral.getJurisdiction_of_issuer()));
		Assertions.assertEquals(collateral.getPrice_currency(), tradeService.getCurrency(collateral.getJurisdiction_of_issuer()));
		Assertions.assertEquals(collateral.getCurrency_collateral_nom_amount(), tradeService.getCurrency(collateral.getJurisdiction_of_issuer()));
	
	
	}
	
	// FAILING
	@Test
	public void test_TradeController_DataQuality_Dirty() throws Exception {
		TradeRequest tradeRequest = new TradeRequest("Margin Lending", 10, "");

		ResultActions result = this.mockMvc.perform(post(TRADE_CONTROLLER_URI + "/CreateTradeProduct")
				.session(session)
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(tradeRequest)))
			.andExpect(status().isOk());
		Assertions.assertNotNull(result);
	}
	//FAILING
	@Test
	public void test_TradeController_DataQuality_Clean() throws Exception {
		TradeRequest tradeRequest = new TradeRequest("Margin Lending", 10, "");

		ResultActions result = this.mockMvc.perform(post(TRADE_CONTROLLER_URI + "/CreateTradeProduct")
				.session(session)
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(tradeRequest)))
			.andExpect(status().isOk());
		Assertions.assertNotNull(result);
	}
	
	@Test
	public void test_MissingData_ForFields() {
		Trade trade = new Trade();
		
		Transaction transaction = new Transaction();
		transaction.setType_Of_Sft(dataService.getMissingData());
		transaction.setEvent_date(dataService.getMissingData());
		transaction.setTermination_date(dataService.getMissingData());
		transaction.setExecution_timestamp(dataService.getMissingData());
		tradeService.save(transaction);
		
		Counterparty counterParty = new Counterparty();
		counterParty.setReport_SubmittingEntity(dataService.getMissingData());
		counterParty.setOther_counterparty(dataService.getMissingData());
		counterParty.setEntity_responsible_for_report(dataService.getMissingData());
		counterParty.setReporting_counterparty(dataService.getMissingData());
		counterParty.setReporting_timestamp(dataService.getMissingData());
		counterParty.setBranch_reporting_counterparty(dataService.getMissingData());
		counterParty.setBranch_other_counterparty(dataService.getMissingData());
		counterParty.setOther_counterparty_country(dataService.getMissingData());
		counterParty.setCounterparty_nature(dataService.getMissingData());
		counterParty.setAdditional_sector_classification(dataService.getMissingData());
		counterParty.setSector_of_reporting_counterparty(dataService.getMissingData());
		counterParty.setCounterparty_side(dataService.getMissingData());
		tradeService.save(counterParty);
		
		Collateral collateral = new Collateral();
		collateral.setLEI_of_issuer(dataService.getMissingData());
		collateral.setJurisdiction_of_issuer(dataService.getMissingData());
		collateral.setReused_collateral_currency(dataService.getMissingData());
		collateral.setFunding_source_currency(dataService.getMissingData());
		collateral.setFunding_source(dataService.getMissingData());
		collateral.setPrice_currency(dataService.getMissingData());
		collateral.setCurrency_collateral_nom_amount(dataService.getMissingData());
		collateral.setCollateral_component(dataService.getMissingData());
		collateral.setCollateral_quality(dataService.getMissingData());
		collateral.setCollateral_type(dataService.getMissingData());
		collateral.setMethod_to_provide_collateral(dataService.getMissingData());
		collateral.setCollateral_market_value(dataService.getMissingData());
		collateral.setPrice_per_unit(dataService.getMissingData());
		collateral.setCollateral_quantity_or_nom_amount(dataService.getMissingData());
		collateral.setHaircut_or_margin(dataService.getMissingData());
		collateral.setMarket_value_of_funding_sources(dataService.getMissingData());
		collateral.setEstimated_reuse_of_collateral(dataService.getMissingData());
		collateral.setValue_of_reused_collateral(dataService.getMissingData());
		collateral.setMaturity_date_of_the_security(dataService.getMissingData());
		collateral.setClassification_of_security_used_as_collateral(dataService.getMissingData());
		collateral.setIdentification_of_security_as_collateral(dataService.getMissingData());
		collateral.setAvailability_for_collateral_reuse(true);// true or false
		tradeService.save(collateral);
		
		Principle principle = new Principle();
		principle.setDay_count_convention(dataService.getMissingData());
		principle.setFloat_ref_period_time(dataService.getMissingData());
		principle.setFloat_pay_freq_time(dataService.getMissingData());
		principle.setFloat_reset_freq_time(dataService.getMissingData());
		principle.setMargin_currency(dataService.getMissingData());
		principle.setOutstanding_ML_base_currency(dataService.getMissingData());
		principle.setFloating_rate(dataService.getMissingData());
		principle.setFixed_rate(dataService.getMissingData());
		principle.setOutstanding_margin_loan(dataService.getMissingData());
		principle.setShort_market_value(dataService.getMissingData());
		principle.setMargin_currency_amount(dataService.getMissingData());
		principle.setFloat_ref_period_multi(dataService.getMissingData());
		principle.setFloat_pay_freq_multi(dataService.getMissingData());
		principle.setFloat_reset_freq_multi(dataService.getMissingData());
		principle.setSpread(dataService.getMissingData());
		tradeService.save(principle);
		
		trade.setTrade_transaction(transaction);
		trade.setTrade_counterparty(counterParty);
		trade.setTrade_collateral(collateral);
		trade.setTrade_principle(principle);
		trade.setTrade_level(tradeService.getLevel());
		trade.setTrade_quality("Dirty");
		trade.setTrade_type("Margin Lending");
		tradeService.save(trade);
		Assertions.assertNotNull(trade);
		Assertions.assertEquals("  ", trade.getTrade_counterparty().getReport_SubmittingEntity());
	}
	

}
