package com.IRCTradingDataGenerator.tradingDataGenerator.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.IRCTradingDataGenerator.tradingDataGenerator.Models.Collateral;
import com.IRCTradingDataGenerator.tradingDataGenerator.Models.Counterparty;
import com.IRCTradingDataGenerator.tradingDataGenerator.Models.Principle;
import com.IRCTradingDataGenerator.tradingDataGenerator.Models.Trade;
import com.IRCTradingDataGenerator.tradingDataGenerator.Models.Transaction;
import com.IRCTradingDataGenerator.tradingDataGenerator.Repository.CollateralDao;
import com.IRCTradingDataGenerator.tradingDataGenerator.Repository.CounterPartyDao;
import com.IRCTradingDataGenerator.tradingDataGenerator.Repository.PrincipleDao;
import com.IRCTradingDataGenerator.tradingDataGenerator.Repository.TradeDao;
import com.IRCTradingDataGenerator.tradingDataGenerator.Repository.TransactionDao;

@Service
public class TradeService {
	
	@Autowired
	TradeDao tradeDao;
	
	@Autowired
	TradeService tradeService;
	
	@Autowired
	DataService dataService;
	
	@Autowired
	CollateralDao collateralDao;
	
	@Autowired
	CounterPartyDao counterPartyDao;
	
	@Autowired
	PrincipleDao principleDao;
	
	@Autowired
	TransactionDao transactionDao;
	
	// SAVE METHODS
	public Counterparty save(Counterparty counterParty) {
		return counterPartyDao.save(counterParty);
	}

	public  Collateral save(Collateral collateral) {
		return collateralDao.save(collateral);
	}

	public Trade save(Trade trade) {
		return tradeDao.save(trade);
	}
	
	public Principle save(Principle principle) {
		return principleDao.save(principle);
	}
	
	public Transaction save(Transaction transactions) {
		return transactionDao.save(transactions);
	}

	// GET METHODS
	public List<Trade> getTrades() {
		return tradeDao.findAllTrades();
	}

	public Optional<Trade> getTradeById(int trade_Id) {

		return tradeDao.findByTrade_Id(trade_Id);
	}
	
	public String getLEI() {
		List<String> LEIs = tradeDao.getLEI();
		Random rand = new Random();
		return LEIs.get(rand.nextInt(LEIs.size()));
	}
	
	public String getLEI(String report_SubmittingEntity) {
		List<String> LEIs = tradeDao.getLEI(report_SubmittingEntity);
		Random rand = new Random();
		return LEIs.get(rand.nextInt(LEIs.size()));
	}
	
	public String getCountry(String LEI) {
		return tradeDao.getCountry(LEI);
	}
	
	public String getCountryDirty(String LEI) {
		List<String> countries = tradeDao.getCountryDirty(LEI);
		Random rand = new Random();
		return tradeDao.getCountry2(countries.get(rand.nextInt(countries.size())));
	}
	
	public String getCurrency(String jurisdiction) {
		String currency = tradeDao.getCurrency(jurisdiction);
		return currency;
	}
	
	public String getCurrencyDirty(String jurisdiction) {
		List<String> currencies = tradeDao.getCurrencyDirty(jurisdiction);
		Random rand = new Random();
		return currencies.get(rand.nextInt(currencies.size()));
	}
	
	public String getNature() {
		List<String> natures = tradeDao.getNatureById();
		Random rand = new Random();
		return natures.get(rand.nextInt(natures.size()));
	}
	
	public String getSectorClass(String counterpartyNature) {
		List<String> sectors = tradeDao.getSector(counterpartyNature);
		Random rand = new Random();
		return sectors.get(rand.nextInt(sectors.size()));
	}
	
	public String getSectorClassDirty(String counterpartyNature) {
		List<String> sectors = tradeDao.getSectorDirty(counterpartyNature);
		Random rand = new Random();
		return sectors.get(rand.nextInt(sectors.size()));
	}
	
	public String getAddSectorClass() {
		List<String> sectorClassifications = tradeDao.getAddSectorClass();
		Random rand = new Random();
		return sectorClassifications.get(rand.nextInt(sectorClassifications.size()));
	}
	
	public String getSFT(String tradeType) {
		List<String> SFTs = tradeDao.getSFT(tradeType);
		Random rand = new Random();
		return SFTs.get(rand.nextInt(SFTs.size()));	
	}
	
	public String getCounterPartySide() {
		List<String> cp_sides = tradeDao.getCounterPartySide();
		Random rand = new Random();
		return cp_sides.get(rand.nextInt(cp_sides.size()));
	}
	
	public String getFundingSource() {
		List<String> fundingSources = tradeDao.getFundingSource();
		Random rand = new Random();
		return fundingSources.get(rand.nextInt(fundingSources.size()));
	}

	public String getCollateralComponent() {
		List<String> collateralComponents = tradeDao.getCollateralComponents();
		Random rand = new Random();
		return collateralComponents.get(rand.nextInt(collateralComponents.size()));
	}

	public String getCollateralQuality() {
		List<String> collateralQuality = tradeDao.getCollateralQuality();
		Random rand = new Random();
		return collateralQuality.get(rand.nextInt(collateralQuality.size()));
	}

	public String getCollteralType() {
		List<String> collateralTypes = tradeDao.getCollateralTypes();
		Random rand = new Random();
		return collateralTypes.get(rand.nextInt(collateralTypes.size()));
	}

	public String getActionType() {
		List<String> actionTypes = tradeDao.getActionTypes();
		Random rand = new Random();
		return actionTypes.get(rand.nextInt(actionTypes.size()));
	}
	
	public String getActionTypeExcluding(String string, String string2) {
		List<String> actionTypes = tradeDao.getActionTypesExcluding(string, string2);
		Random rand = new Random();
		return actionTypes.get(rand.nextInt(actionTypes.size()));
	}

	public String getDayCountConvention() {
		List<String> dayCountConventions = tradeDao.getDayCountConventions();
		Random rand = new Random();
		return dayCountConventions.get(rand.nextInt(dayCountConventions.size()));
	}

	public String getTimePeriod() {
		List<String> floatRefTimes = tradeDao.getTimePeriod();
		Random rand = new Random();
		return floatRefTimes.get(rand.nextInt(floatRefTimes.size()));
	}

	public String getMethodToProvideCollateral() {
		List<String> methods = tradeDao.getMethods();
		Random rand = new Random();
		return methods.get(rand.nextInt(methods.size()));
	}

	public String getFloatingRate() {
		List<String> floatingRates = tradeDao.getFloatingRates();
		Random rand = new Random();
		return floatingRates.get(rand.nextInt(floatingRates.size()));
	}

	public String getLevel() {
		List<String> levels = tradeDao.getLevels();
		Random rand = new Random();
		return levels.get(rand.nextInt(levels.size()));
	}
	
	
	// REGION TRADE
	public Trade createTrade(String type, String quality, String actionType) {
		// create new trade
		Trade newTrade = new Trade();
		// set trade type
		newTrade.setTrade_type(type);
		// set trade quality
		newTrade.setTrade_quality(quality);
		newTrade.setTrade_level(tradeService.setTradeLevel(newTrade));
		if(actionType.equals(null)){
			// get random action type
			newTrade.setAction_type(tradeService.setTradeActionType(newTrade));
		} else {
			// get user specified action type
			newTrade.setAction_type(actionType);
		}
		newTrade.setReporting_timestamp(dataService.createDateAndTimeFromToday());
		return newTrade;
	}

	public String setTradeLevel(Trade newTrade) {
		// if ML type, level = trade level (TCTN only?)
		if(newTrade.getTrade_type().equals("Margin Lending")) {
			return "TCTN";
		}
		// if SL, BSB, REPO type, level = trade or position (TCTN or PSTN?)
		else {
			return tradeService.getLevel();
		}
	}
	
	public String setTradeActionType(Trade newTrade) {
		if(newTrade.getTrade_level().equals("TCTN")) {
			// set action type
			if(newTrade.getTrade_type().equals("Margin Lending")) {
				// ML does not exist in types POSC or VALU
				return tradeService.getActionTypeExcluding("POSC", "VALU");
			}
			else if(newTrade.getTrade_type().equals("Buy/Sell Back") || newTrade.getTrade_type().equals("Repurchase")) {
				// BSB and Repurchase do not exsist in VALU
				return tradeService.getActionTypeExcluding("", "VALU");
			}
			else {
				// SL exsists in all action types
				return tradeService.getActionType();
			}
		} 
		// if trade level = position
		else {
			// No ML, does not exsist at position level
			// set action type
			if(newTrade.getTrade_type().equals("Buy/Sell Back") || newTrade.getTrade_type().equals("Repurchase")) {
				return tradeService.getActionTypeExcluding("POSC", "VALU");
			}
			else {
				return tradeService.getActionTypeExcluding("POSC", "");
			}
		}
	}

	public boolean checkActionType(List<String> actionTypes, Trade newTrade) {
		boolean valid = false;
		for (String actionType: actionTypes) {
			if(newTrade.getAction_type().equals(actionType)) {
				valid = true;
			}
		}
		return valid;
	}


	// REGION TRANSACTION
	public Transaction createTransaction(Trade newTrade) {
		// list to store action types we are not looking for
		List<String> actionTypesToCheck = new ArrayList<>();

		Transaction transaction = new Transaction();

		// set Type of SFT
		actionTypesToCheck.add("NEWT");
		actionTypesToCheck.add("MODI");
		actionTypesToCheck.add("POSC");
		actionTypesToCheck.add("COLU");
		actionTypesToCheck.add("CORR");
		if (checkActionType(actionTypesToCheck, newTrade)) {
			transaction.setType_Of_Sft(tradeService.getSFT(newTrade.getTrade_type()));
		}
		actionTypesToCheck.clear();

		// set Event date
		actionTypesToCheck.add("NEWT");
		actionTypesToCheck.add("MODI");
		actionTypesToCheck.add("POSC");
		actionTypesToCheck.add("COLU");
		actionTypesToCheck.add("CORR");
		actionTypesToCheck.add("ETRM");
		actionTypesToCheck.add("VALU");
		if (checkActionType(actionTypesToCheck, newTrade)) {
			transaction.setEvent_date(dataService.createDateFromToday());
		}
		actionTypesToCheck.clear();

		// set Execution Timestamp
		actionTypesToCheck.add("NEWT");
		actionTypesToCheck.add("MODI");
		actionTypesToCheck.add("POSC");
		actionTypesToCheck.add("CORR");
		if(checkActionType(actionTypesToCheck, newTrade)) {
			transaction.setExecution_timestamp(dataService.createDateAndTimeFromToday());
		}
		actionTypesToCheck.clear();

		// set Termination date
		actionTypesToCheck.add("ETRM");
		actionTypesToCheck.add("CORR");
		if (checkActionType(actionTypesToCheck, newTrade)) {
			transaction.setTermination_date(dataService.createDateFromToday());
		} 

		actionTypesToCheck.clear();
		tradeService.save(transaction);
		return transaction;
	}


	// REGION COUNTERPARTY
	public Counterparty createCounterparty(Trade newTrade) {
		// list to store action types we are not looking for
		List<String> actionTypesToCheck = new ArrayList<>();

		Counterparty counterparty = new Counterparty();
		// Mandatory fields for all trades for all action types
		counterparty.setReporting_timestamp(dataService.createDateAndTimeFromToday());
		counterparty.setReport_SubmittingEntity(tradeService.getLEI());
		counterparty.setReporting_counterparty(counterparty.getReport_SubmittingEntity());
		counterparty.setOther_counterparty(tradeService.getLEI(counterparty.getReport_SubmittingEntity()));

		// set Nature of reporting counterparty
		actionTypesToCheck.add("NEWT");
		actionTypesToCheck.add("MODI");
		actionTypesToCheck.add("POSC");
		actionTypesToCheck.add("CORR");
		if(checkActionType(actionTypesToCheck, newTrade)) {
			counterparty.setCounterparty_nature(tradeService.getNature());
		}
		else {
			counterparty.setCounterparty_nature("");
		}
		actionTypesToCheck.clear();

		// set sector of reporting counterparty
		actionTypesToCheck.add("NEWT");
		actionTypesToCheck.add("MODI");
		actionTypesToCheck.add("POSC");
		actionTypesToCheck.add("CORR");
		if(checkActionType(actionTypesToCheck, newTrade)) {
			counterparty.setSector_of_reporting_counterparty(tradeService.getSectorClass(counterparty.getCounterparty_nature()));
		}
		else {
			counterparty.setSector_of_reporting_counterparty("");
		}
		actionTypesToCheck.clear();

		// set additional sector classification
		actionTypesToCheck.add("NEWT");
		actionTypesToCheck.add("MODI");
		actionTypesToCheck.add("POSC");
		actionTypesToCheck.add("CORR");
		if(checkActionType(actionTypesToCheck, newTrade)) {
			if (counterparty.getSector_of_reporting_counterparty().equals("UCIT") || counterparty.getSector_of_reporting_counterparty().equals("AIFD") || counterparty.getSector_of_reporting_counterparty().equals("K") || counterparty.getSector_of_reporting_counterparty().equals("L")) {
				counterparty.setAdditional_sector_classification(tradeService.getAddSectorClass());
			}
		}
		actionTypesToCheck.clear();

		// set branch of reporting counterparty
		actionTypesToCheck.add("NEWT");
		actionTypesToCheck.add("MODI");
		actionTypesToCheck.add("POSC");
		actionTypesToCheck.add("COLU");
		actionTypesToCheck.add("CORR");
		if(checkActionType(actionTypesToCheck, newTrade)) {
			counterparty.setBranch_reporting_counterparty(tradeService.getCountry(counterparty.getReporting_counterparty()));
		}
		actionTypesToCheck.clear();

		// set branch of other counterparty
		actionTypesToCheck.add("NEWT");
		actionTypesToCheck.add("MODI");
		actionTypesToCheck.add("POSC");
		actionTypesToCheck.add("COLU");
		actionTypesToCheck.add("CORR");
		if(checkActionType(actionTypesToCheck, newTrade)) {
			counterparty.setBranch_other_counterparty(tradeService.getCountry(counterparty.getOther_counterparty()));
		}
		actionTypesToCheck.clear();

		// Set counterparty side
		actionTypesToCheck.add("NEWT");
		actionTypesToCheck.add("MODI");
		actionTypesToCheck.add("POSC");
		actionTypesToCheck.add("CORR");
		if(checkActionType(actionTypesToCheck, newTrade)) {
			counterparty.setCounterparty_side(tradeService.getCounterPartySide());
		}
		actionTypesToCheck.clear();

		// set entity responsible for report
		actionTypesToCheck.add("NEWT");
		actionTypesToCheck.add("MODI");
		actionTypesToCheck.add("POSC");
		actionTypesToCheck.add("CORR");
		if(checkActionType(actionTypesToCheck, newTrade)) {
			counterparty.setEntity_responsible_for_report(counterparty.getReport_SubmittingEntity());
		}
		actionTypesToCheck.clear();

		// set country of other counterparty
		actionTypesToCheck.add("NEWT");
		actionTypesToCheck.add("MODI");
		actionTypesToCheck.add("POSC");
		actionTypesToCheck.add("CORR");
		if(checkActionType(actionTypesToCheck, newTrade)) {
			counterparty.setOther_counterparty_country(tradeService.getCountry(counterparty.getOther_counterparty()));
		}
		actionTypesToCheck.clear();

		tradeService.save(counterparty);
		return counterparty;
	}

	
	// REGION PRINCIPLE
	public Principle createPrinciple(Trade newTrade) {
		// list to store action types we are not looking for
		List<String> actionTypesToCheck = new ArrayList<>();

		Principle principle = new Principle();
		// set floating rate
		// Only for ML + REPO
		if(isMarginLending(newTrade.getTrade_type()) || isRepurchase(newTrade.getTrade_type())) {
			actionTypesToCheck.add("NEWT");
			actionTypesToCheck.add("MODI");
			actionTypesToCheck.add("POSC");
			actionTypesToCheck.add("CORR");
			if(checkActionType(actionTypesToCheck, newTrade)) {
				principle.setFloating_rate(tradeService.getFloatingRate());
			}
		}
		actionTypesToCheck.clear();

		// set fixed rate
		// Only for ML + REPO
		if(isMarginLending(newTrade.getTrade_type()) || isRepurchase(newTrade.getTrade_type())) {
			actionTypesToCheck.add("NEWT");
			actionTypesToCheck.add("MODI");
			actionTypesToCheck.add("POSC");
			actionTypesToCheck.add("CORR");
			if(checkActionType(actionTypesToCheck, newTrade)) {
				principle.setFixed_rate(dataService.createNumeric11().toString());
			}
		}
		actionTypesToCheck.clear();

		// set day count convention
		// Only ML + REPO
		if(isMarginLending(newTrade.getTrade_type()) || isRepurchase(newTrade.getTrade_type())) {
			actionTypesToCheck.add("NEWT");
			actionTypesToCheck.add("MODI");
			actionTypesToCheck.add("POSC");
			actionTypesToCheck.add("CORR");
			if(checkActionType(actionTypesToCheck, newTrade)) {
				if(principle.getFixed_rate() != null || principle.getFloating_rate() != null) {
					principle.setDay_count_convention(tradeService.getDayCountConvention());
				}
			}
		}
		actionTypesToCheck.clear();

		// set Float rate reference period - time period
		// Only ML + REPO
		if(isMarginLending(newTrade.getTrade_type()) || isRepurchase(newTrade.getTrade_type())) {
			actionTypesToCheck.add("NEWT");
			actionTypesToCheck.add("MODI");
			actionTypesToCheck.add("POSC");
			actionTypesToCheck.add("CORR");
			if(checkActionType(actionTypesToCheck, newTrade)) {
				if(principle.getFloating_rate() != null) {
					principle.setFloat_ref_period_time(tradeService.getTimePeriod());
				}
			}
		}
		actionTypesToCheck.clear();

		// set Float rate pay frequency period - time period
		// Only ML + REPO
		if(isMarginLending(newTrade.getTrade_type()) || isRepurchase(newTrade.getTrade_type())) {
			actionTypesToCheck.add("NEWT");
			actionTypesToCheck.add("MODI");
			actionTypesToCheck.add("POSC");
			actionTypesToCheck.add("CORR");
			if(checkActionType(actionTypesToCheck, newTrade)) {
				if(principle.getFloating_rate() != null) {
					principle.setFloat_pay_freq_time(tradeService.getTimePeriod());
				}
			}
		}
		actionTypesToCheck.clear();

		// set Float rate reset frequency period - time period
		// Only ML + REPO
		if(isMarginLending(newTrade.getTrade_type()) || isRepurchase(newTrade.getTrade_type())) {
			actionTypesToCheck.add("NEWT");
			actionTypesToCheck.add("MODI");
			actionTypesToCheck.add("POSC");
			actionTypesToCheck.add("CORR");
			if(checkActionType(actionTypesToCheck, newTrade)) {
				if(principle.getFloating_rate() != null) {
					principle.setFloat_reset_freq_time(tradeService.getTimePeriod());
				}
			}
		}
		actionTypesToCheck.clear();

		// set margin lending currency
		// Only for ML
		if(isMarginLending(newTrade.getTrade_type())) {
			actionTypesToCheck.add("NEWT");
			actionTypesToCheck.add("MODI");
			actionTypesToCheck.add("CORR");
			if(checkActionType(actionTypesToCheck, newTrade)) {
				principle.setMargin_currency(tradeService.getCurrency(newTrade.getTrade_counterparty().getBranch_reporting_counterparty()));
			}
		}
		actionTypesToCheck.clear();

		// set base margin lending currency amount
		// Only for ML
		if(isMarginLending(newTrade.getTrade_type())) {
			actionTypesToCheck.add("NEWT");
			actionTypesToCheck.add("MODI");
			actionTypesToCheck.add("CORR");
			if(checkActionType(actionTypesToCheck, newTrade)) {
				principle.setOutstanding_ML_base_currency(tradeService.getCurrency(newTrade.getTrade_counterparty().getBranch_reporting_counterparty()));
			}
		}
		actionTypesToCheck.clear();

		// set outstanding margin loan
		// Only for ML
		if(isMarginLending(newTrade.getTrade_type())) {
			actionTypesToCheck.add("NEWT");
			actionTypesToCheck.add("MODI");
			actionTypesToCheck.add("CORR");
			if(checkActionType(actionTypesToCheck, newTrade)) {
				principle.setOutstanding_margin_loan(dataService.createNumeric18().toString());
			}
		}
		actionTypesToCheck.clear();

		// set short market value
		// Only for ML
		if(isMarginLending(newTrade.getTrade_type())) {
			actionTypesToCheck.add("NEWT");
			actionTypesToCheck.add("MODI");
			actionTypesToCheck.add("CORR");
			if(checkActionType(actionTypesToCheck, newTrade)) {
				principle.setShort_market_value(dataService.createNumeric18().toString());
			}
		}
		actionTypesToCheck.clear();

		// set margin lending currency amount
		// Only for ML
		if(isMarginLending(newTrade.getTrade_type())) {
			actionTypesToCheck.add("NEWT");
			actionTypesToCheck.add("MODI");
			actionTypesToCheck.add("CORR");
			if(checkActionType(actionTypesToCheck, newTrade)) {
				principle.setMargin_currency_amount(dataService.createNumeric18().toString());
			}
		}
		actionTypesToCheck.clear();

		// set float rate refrence period - multiplier
		// Only for ML + REPO
		if(isMarginLending(newTrade.getTrade_type()) || isRepurchase(newTrade.getTrade_type())) {
			actionTypesToCheck.add("NEWT");
			actionTypesToCheck.add("MODI");
			actionTypesToCheck.add("POSC");
			actionTypesToCheck.add("CORR");
			if(checkActionType(actionTypesToCheck, newTrade)) {
				if(principle.getFloating_rate() != null) {
					principle.setFloat_ref_period_multi(dataService.createNumeric3().toString());
				}
			}
		}
		actionTypesToCheck.clear();

		// set float rate pay frequency - multiplier
		// Only for ML + REPO
		if(isMarginLending(newTrade.getTrade_type()) || isRepurchase(newTrade.getTrade_type())) {
			actionTypesToCheck.add("NEWT");
			actionTypesToCheck.add("MODI");
			actionTypesToCheck.add("POSC");
			actionTypesToCheck.add("CORR");
			if(checkActionType(actionTypesToCheck, newTrade)) {
				if(principle.getFloating_rate() != null) {
					principle.setFloat_pay_freq_multi(dataService.createNumeric3().toString());
				}
			}
		}
		actionTypesToCheck.clear();

		// set float rate reset frequency - multiplier
		// Only for ML + REPO
		if(isMarginLending(newTrade.getTrade_type()) || isRepurchase(newTrade.getTrade_type())) {
			actionTypesToCheck.add("NEWT");
			actionTypesToCheck.add("MODI");
			actionTypesToCheck.add("POSC");
			actionTypesToCheck.add("CORR");
			if(checkActionType(actionTypesToCheck, newTrade)) {
				if(principle.getFloating_rate() != null) {
					principle.setFloat_reset_freq_multi(dataService.createNumeric3().toString());
				}
			}
		}
		actionTypesToCheck.clear();

		// set spread
		// Only for ML + REPO
		if(isMarginLending(newTrade.getTrade_type()) || isRepurchase(newTrade.getTrade_type())) {
			actionTypesToCheck.add("NEWT");
			actionTypesToCheck.add("MODI");
			actionTypesToCheck.add("POSC");
			actionTypesToCheck.add("CORR");
			if(checkActionType(actionTypesToCheck, newTrade)) {
				if(principle.getFloating_rate() != null) {
					principle.setSpread(dataService.createNumeric5().toString());
				}
			}
		}
		actionTypesToCheck.clear();

		tradeService.save(principle);
		return principle;
	}
	
	
	// REGION COLLATERAL
	public Collateral createCollateral(Trade newTrade) {
		// list to store action types we are not looking for
		List<String> actionTypesToCheck = new ArrayList<>();

		Collateral collateral = new Collateral();
		// set type of collateral component
		// All trades
		actionTypesToCheck.add("NEWT");
		actionTypesToCheck.add("POSC");
		actionTypesToCheck.add("COLU");
		actionTypesToCheck.add("CORR");
		if(checkActionType(actionTypesToCheck, newTrade)) {
			collateral.setCollateral_component(tradeService.getCollateralComponent());
		}
		else {
			collateral.setCollateral_component("");
		}
		actionTypesToCheck.clear();

		// set LEI of issuer
		actionTypesToCheck.add("NEWT");
		actionTypesToCheck.add("POSC");
		actionTypesToCheck.add("COLU");
		actionTypesToCheck.add("CORR");
		if(checkActionType(actionTypesToCheck, newTrade)) {
			if(collateral.getCollateral_component().equals("SECU")) {
				collateral.setLEI_of_issuer(tradeService.getLEI());
			} else {
				collateral.setLEI_of_issuer("");
			}
		}else {
			collateral.setLEI_of_issuer("");
		}
		actionTypesToCheck.clear();

		// set Jurisdiction of issuer
		actionTypesToCheck.add("NEWT");
		actionTypesToCheck.add("POSC");
		actionTypesToCheck.add("COLU");
		actionTypesToCheck.add("CORR");
		if(checkActionType(actionTypesToCheck, newTrade)) {
			if(collateral.getCollateral_component().equals("SECU")) {
				collateral.setJurisdiction_of_issuer(tradeService.getCountry(collateral.getLEI_of_issuer()));
			}
			else {
				collateral.setJurisdiction_of_issuer("");
			}
		}
		else {
			collateral.setJurisdiction_of_issuer("");
		}
		actionTypesToCheck.clear();

		// set price currency
		actionTypesToCheck.add("NEWT");
		actionTypesToCheck.add("POSC");
		actionTypesToCheck.add("COLU");
		actionTypesToCheck.add("CORR");
		if(checkActionType(actionTypesToCheck, newTrade)) {
			if(collateral.getCollateral_component().equals("SECU") || collateral.getCollateral_component().equals("COMM")) {
				collateral.setPrice_currency(tradeService.getCurrency(collateral.getJurisdiction_of_issuer()));
			}
		}
		actionTypesToCheck.clear();

		// set collateral quality
		actionTypesToCheck.add("NEWT");
		actionTypesToCheck.add("POSC");
		actionTypesToCheck.add("COLU");
		actionTypesToCheck.add("CORR");
		if(checkActionType(actionTypesToCheck, newTrade)) {
			if(collateral.getCollateral_component().equals("SECU")) {
				collateral.setCollateral_quality(tradeService.getCollateralQuality());
			}
		}
		actionTypesToCheck.clear();

		// set collateral type
		actionTypesToCheck.add("NEWT");
		actionTypesToCheck.add("POSC");
		actionTypesToCheck.add("COLU");
		actionTypesToCheck.add("CORR");
		if(checkActionType(actionTypesToCheck, newTrade)) {
			if(collateral.getCollateral_component().equals("SECU")) {
				collateral.setCollateral_type(tradeService.getCollteralType());
			}
		}
		actionTypesToCheck.clear();

		// set method used to provide collateral
		// Only for REPO, SL, ML
		actionTypesToCheck.add("NEWT");
		actionTypesToCheck.add("MODI");
		actionTypesToCheck.add("POSC");
		actionTypesToCheck.add("CORR");
		if(!isBuySellBack(newTrade.getTrade_type())) {
			if(checkActionType(actionTypesToCheck, newTrade)) {
				collateral.setMethod_to_provide_collateral(tradeService.getMethodToProvideCollateral());
			}
		}
		actionTypesToCheck.clear();

		// set currency of collateral nominal amount
		actionTypesToCheck.add("NEWT");
		actionTypesToCheck.add("POSC");
		actionTypesToCheck.add("COLU");
		actionTypesToCheck.add("CORR");
		if(checkActionType(actionTypesToCheck, newTrade)) {
			if(collateral.getCollateral_component().equals("SECU")) {
				collateral.setCurrency_collateral_nom_amount(tradeService.getCurrency(collateral.getJurisdiction_of_issuer()));
			}
		}
		actionTypesToCheck.clear();

		// set collateral market value
		actionTypesToCheck.add("NEWT");
		actionTypesToCheck.add("POSC");
		actionTypesToCheck.add("COLU");
		actionTypesToCheck.add("CORR");
		if(checkActionType(actionTypesToCheck, newTrade)) {
			if(collateral.getCollateral_component().equals("SECU") || collateral.getCollateral_component().equals("COMM")) {
				collateral.setCollateral_market_value(dataService.createNumeric11().toString());
			}
		}
		actionTypesToCheck.clear();

		// set price per unit
		actionTypesToCheck.add("NEWT");
		actionTypesToCheck.add("POSC");
		actionTypesToCheck.add("COLU");
		actionTypesToCheck.add("CORR");
		if(checkActionType(actionTypesToCheck, newTrade)) {
			if (collateral.getCollateral_component().equals("SECU") || collateral.getCollateral_component().equals("COMM")) {
				collateral.setPrice_per_unit(dataService.createNumeric11().toString());
			}
		}
		actionTypesToCheck.clear();

		// set collateral quantity or nominal amount
		actionTypesToCheck.add("NEWT");
		actionTypesToCheck.add("POSC");
		actionTypesToCheck.add("COLU");
		actionTypesToCheck.add("CORR");
		if(checkActionType(actionTypesToCheck, newTrade)) {
			if(collateral.getCollateral_component().equals("SECU") || collateral.getCollateral_component().equals("COMM")) {
				collateral.setCollateral_quantity_or_nom_amount(dataService.createNumeric18().toString());
			}
		}
		actionTypesToCheck.clear();

		// set haircut or margin
		actionTypesToCheck.add("NEWT");
		actionTypesToCheck.add("POSC");
		actionTypesToCheck.add("COLU");
		actionTypesToCheck.add("CORR");
		if(checkActionType(actionTypesToCheck, newTrade)) {
			if(collateral.getCollateral_component().equals("SECU") || collateral.getCollateral_component().equals("CASH")) {
				collateral.setHaircut_or_margin(dataService.createNumeric11().toString());
			}
		}
		actionTypesToCheck.clear();

		// set funding source
		if(collateral.getCollateral_component().equals("SECU")) {
			actionTypesToCheck.add("NEWT");
			actionTypesToCheck.add("CORR");
			if(checkActionType(actionTypesToCheck, newTrade)) {
				collateral.setFunding_source(tradeService.getFundingSource());
			}
		}
		actionTypesToCheck.clear();

		// set market value of funding sources
		if(collateral.getCollateral_component().equals("SECU")) {
			if(collateral.getFunding_source() != null) {
				actionTypesToCheck.add("NEWT");
				actionTypesToCheck.add("CORR");
				if(checkActionType(actionTypesToCheck, newTrade)) {
					collateral.setMarket_value_of_funding_sources(dataService.createNumeric18().toString());
				}
			}
		}
		actionTypesToCheck.clear();

		// set funding source currency
		if(collateral.getCollateral_component().equals("SECU")) {
			if(collateral.getMarket_value_of_funding_sources() != null) {
				actionTypesToCheck.add("NEWT");
				actionTypesToCheck.add("CORR");
				if(checkActionType(actionTypesToCheck, newTrade)) {
					collateral.setFunding_source_currency(tradeService.getCurrency(collateral.getJurisdiction_of_issuer()));
				}
			}
		}
		actionTypesToCheck.clear();

		// set estimated reuse of collateral
		if(collateral.getCollateral_component().equals("SECU")) {
			actionTypesToCheck.add("NEWT");
			actionTypesToCheck.add("CORR");
			if(checkActionType(actionTypesToCheck, newTrade)) {
				collateral.setEstimated_reuse_of_collateral(dataService.createNumeric18().toString());
			}
		}
		actionTypesToCheck.clear();

		// set value of reused collateral
		if(collateral.getCollateral_component().equals("SECU")) {
			actionTypesToCheck.add("NEWT");
			actionTypesToCheck.add("CORR");
			if(checkActionType(actionTypesToCheck, newTrade)) {
				collateral.setValue_of_reused_collateral(dataService.createNumeric18().toString());
			}
		}
		actionTypesToCheck.clear();

		// set maturity date of the security
		actionTypesToCheck.add("NEWT");
		actionTypesToCheck.add("POSC");
		actionTypesToCheck.add("COLU");
		actionTypesToCheck.add("CORR");
		if(checkActionType(actionTypesToCheck, newTrade)) {
			if (collateral.getCollateral_component().equals("SECU")) {
				collateral.setMaturity_date_of_the_security(dataService.createDateFromToday());
			}
		}
		actionTypesToCheck.clear();

		// set classification of security used as collateral
		actionTypesToCheck.add("NEWT");
		actionTypesToCheck.add("POSC");
		actionTypesToCheck.add("COLU");
		actionTypesToCheck.add("CORR");
		if(checkActionType(actionTypesToCheck, newTrade)) {
			if (collateral.getCollateral_component().equals("SECU")) {
				collateral.setClassification_of_security_used_as_collateral(dataService.createCFI());
			}
		}
		actionTypesToCheck.clear();

		// set identification of security as a collateral
		actionTypesToCheck.add("NEWT");
		actionTypesToCheck.add("POSC");
		actionTypesToCheck.add("COLU");
		actionTypesToCheck.add("CORR");
		if(checkActionType(actionTypesToCheck, newTrade)) {
			if (collateral.getCollateral_component().equals("SECU")) {
				collateral.setIdentification_of_security_as_collateral(dataService.createISIN(collateral.getLEI_of_issuer()));
			}
		}
		actionTypesToCheck.clear();

		// set availability for collateral reuse
		actionTypesToCheck.add("NEWT");
		actionTypesToCheck.add("POSC");
		actionTypesToCheck.add("COLU");
		actionTypesToCheck.add("CORR");
		if(checkActionType(actionTypesToCheck, newTrade)) {
			if (collateral.getCollateral_component().equals("SECU")) {
				collateral.setAvailability_for_collateral_reuse(dataService.getTrueOrFlase());
			}
		}
		actionTypesToCheck.clear();

		// set cash collateral currency
		// Only for REPO, BSB, SL
		if(isMarginLending(newTrade.getTrade_type())) {
			actionTypesToCheck.add("NEWT");
			actionTypesToCheck.add("POSC");
			actionTypesToCheck.add("COLU");
			actionTypesToCheck.add("CORR");
			if(checkActionType(actionTypesToCheck, newTrade)) {
				if(collateral.getCollateral_component().equals("CASH")) {
					collateral.setReused_collateral_currency(tradeService.getCurrency(collateral.getJurisdiction_of_issuer()));
				}
			}
		}

		tradeService.save(collateral);
		return collateral;
	}

	private boolean isMarginLending(String tradeType) {
		boolean valid = false;
		if(tradeType.equals("Margin Lending")) {
			valid = true;
		}
		return valid;
	}

	private boolean isBuySellBack(String tradeType) {
		boolean valid = false;
		if(tradeType.equals("Buy/Sell Back")) {
			valid = true;
		}
		return valid;
	}

	private boolean isRepurchase(String tradeType) {
		boolean valid = false;
		if(tradeType.equals("Repurchase")) {
			valid = true;
		}
		return valid;
	}

	private boolean isSecComLending(String tradeType) {
		boolean valid = false;
		if(tradeType.equals("Sec-Com Lending")) {
			valid = true;
		}
		return valid;
	}

}
