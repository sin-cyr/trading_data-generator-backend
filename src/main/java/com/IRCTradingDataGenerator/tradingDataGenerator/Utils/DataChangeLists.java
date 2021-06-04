package com.IRCTradingDataGenerator.tradingDataGenerator.Utils;

import com.IRCTradingDataGenerator.tradingDataGenerator.Services.TradeService;
import com.IRCTradingDataGenerator.tradingDataGenerator.Services.DataService;
import org.springframework.beans.factory.annotation.Autowired;

public class DataChangeLists {

    @Autowired
    TradeService tradeService;

    @Autowired
    DataService dataService;

    public IDataChange[] dataChangeList = new IDataChange[] {
        trade -> trade.getTrade_transaction().setType_Of_Sft(dataService.createDirtyString(tradeService.getSFT(trade.getTrade_type()))),
        trade -> trade.getTrade_transaction().setEvent_date(dataService.createDirtyDate()),
        trade -> trade.getTrade_transaction().setTermination_date(dataService.createDirtyDate()),
        trade -> trade.getTrade_transaction().setExecution_timestamp(dataService.createDirtyDateTime()),

        trade -> trade.getTrade_counterparty().setEntity_responsible_for_report(dataService.createDirtyString(trade.getTrade_counterparty().getReport_SubmittingEntity())),
        trade -> trade.getTrade_counterparty().setReporting_counterparty(dataService.createDirtyString(trade.getTrade_counterparty().getReport_SubmittingEntity())),
        trade -> trade.getTrade_counterparty().setReporting_timestamp(dataService.createDateAndTimeFromTodayDirty()),
        trade -> trade.getTrade_counterparty().setBranch_reporting_counterparty(tradeService.getCountryDirty(trade.getTrade_counterparty().getReporting_counterparty())),
        trade -> trade.getTrade_counterparty().setOther_counterparty_country(tradeService.getCountryDirty(trade.getTrade_counterparty().getOther_counterparty())),
        trade -> trade.getTrade_counterparty().setSector_of_reporting_counterparty(tradeService.getSectorClassDirty(trade.getTrade_counterparty().getCounterparty_nature())),

        trade -> trade.getTrade_principle().setDay_count_convention(dataService.createDirtyString(tradeService.getDayCountConvention())),
        trade -> trade.getTrade_principle().setFloat_ref_period_time(dataService.createDirtyString(tradeService.getTimePeriod())),
        trade -> trade.getTrade_principle().setFloat_pay_freq_time(dataService.createDirtyString(tradeService.getTimePeriod())),
        trade -> trade.getTrade_principle().setFloat_reset_freq_time(dataService.createDirtyString(tradeService.getTimePeriod())),
        trade -> trade.getTrade_principle().setMargin_currency(tradeService.getCurrencyDirty(trade.getTrade_counterparty().getBranch_reporting_counterparty())),
        trade -> trade.getTrade_principle().setOutstanding_ML_base_currency(tradeService.getCurrencyDirty(trade.getTrade_counterparty().getBranch_reporting_counterparty())),
        trade -> trade.getTrade_principle().setFloating_rate(dataService.createDirtyString(tradeService.getFloatingRate())),
        trade -> trade.getTrade_principle().setFixed_rate(dataService.createDirtyNumeric(dataService.createNumeric11())),
        trade -> trade.getTrade_principle().setOutstanding_margin_loan(dataService.createDirtyNumeric(dataService.createNumeric18())),
        trade -> trade.getTrade_principle().setShort_market_value(dataService.createDirtyNumeric(dataService.createNumeric18())),
        trade -> trade.getTrade_principle().setMargin_currency_amount(dataService.createDirtyNumeric(dataService.createNumeric18())),
        trade -> trade.getTrade_principle().setFloat_ref_period_multi(dataService.createDirtyNumeric(dataService.createNumeric3())),
        trade -> trade.getTrade_principle().setFloat_pay_freq_multi(dataService.createDirtyNumeric(dataService.createNumeric3())),
        trade -> trade.getTrade_principle().setFloat_reset_freq_multi(dataService.createDirtyNumeric(dataService.createNumeric3())),
        trade -> trade.getTrade_principle().setSpread(dataService.createDirtyNumeric(dataService.createNumeric5())),

       trade -> trade.getTrade_collateral().setJurisdiction_of_issuer(tradeService.getCountryDirty(trade.getTrade_collateral().getLEI_of_issuer())),
       trade -> trade.getTrade_collateral().setReused_collateral_currency(tradeService.getCurrencyDirty(trade.getTrade_collateral().getJurisdiction_of_issuer())),
       trade -> trade.getTrade_collateral().setFunding_source_currency(tradeService.getCurrencyDirty(trade.getTrade_collateral().getJurisdiction_of_issuer())),
       trade -> trade.getTrade_collateral().setFunding_source(dataService.createDirtyString(tradeService.getFundingSource())),
       trade -> trade.getTrade_collateral().setPrice_currency(tradeService.getCurrencyDirty(trade.getTrade_collateral().getJurisdiction_of_issuer())),
       trade -> trade.getTrade_collateral().setCurrency_collateral_nom_amount(tradeService.getCurrencyDirty(trade.getTrade_collateral().getJurisdiction_of_issuer())),
       trade -> trade.getTrade_collateral().setCollateral_component(dataService.createDirtyString(tradeService.getCollateralComponent())),
       trade -> trade.getTrade_collateral().setCollateral_quality(dataService.createDirtyString(tradeService.getCollateralQuality())),
       trade -> trade.getTrade_collateral().setCollateral_type(dataService.createDirtyString(tradeService.getCollteralType())),
       trade -> trade.getTrade_collateral().setMethod_to_provide_collateral(dataService.createDirtyString(tradeService.getMethodToProvideCollateral())),
       trade -> trade.getTrade_collateral().setCollateral_market_value(dataService.createDirtyNumeric(dataService.createNumeric11())),
       trade -> trade.getTrade_collateral().setPrice_per_unit(dataService.createDirtyNumeric(dataService.createNumeric11())),
       trade -> trade.getTrade_collateral().setCollateral_quantity_or_nom_amount(dataService.createDirtyNumeric(dataService.createNumeric18())),
       trade -> trade.getTrade_collateral().setHaircut_or_margin(dataService.createDirtyNumeric(dataService.createNumeric11())),
       trade -> trade.getTrade_collateral().setMarket_value_of_funding_sources(dataService.createDirtyNumeric(dataService.createNumeric18())),
       trade -> trade.getTrade_collateral().setEstimated_reuse_of_collateral(dataService.createDirtyNumeric(dataService.createNumeric18())),
       trade -> trade.getTrade_collateral().setValue_of_reused_collateral(dataService.createDirtyNumeric(dataService.createNumeric18())),
       trade -> trade.getTrade_collateral().setMaturity_date_of_the_security(dataService.createDirtyDate()),
       trade -> trade.getTrade_collateral().setClassification_of_security_used_as_collateral(dataService.createDirtyString(dataService.createISIN(trade.getTrade_collateral().getLEI_of_issuer()))),
       trade -> trade.getTrade_collateral().setIdentification_of_security_as_collateral(dataService.createDirtyString(dataService.createCFI()))
    };
}
