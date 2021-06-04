package com.IRCTradingDataGenerator.tradingDataGenerator.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.IRCTradingDataGenerator.tradingDataGenerator.Models.Trade;

public interface TradeDao extends JpaRepository<Trade, Integer> {
	
	@Query(value="SELECT * FROM trade WHERE trade_id = ?", nativeQuery = true)
	public Optional<Trade> findByTrade_Id(int trade_Id);

	@Query(value="SELECT * FROM trade", nativeQuery = true)
	public List<Trade> findAllTrades(); 

	@Query(value="SELECT LEI FROM company", nativeQuery = true)
	public List<String> getLEI();
	
	@Query(value="SELECT LEI FROM company WHERE LEI <> ?", nativeQuery = true)
	public List<String> getLEI(String report_SubmittingEntity);
	
	@Query(value="SELECT country_code FROM country WHERE country_id = (SELECT country_Code FROM company WHERE LEI = ?)", nativeQuery = true)
	public String getCountry(String LEI);
	
	@Query(value="SELECT country_code FROM country WHERE country_id = ?", nativeQuery = true)
	public String getCountry2(String id);
	
	@Query(value="SELECT country_Code FROM company WHERE LEI <> ?", nativeQuery = true)
	public List<String> getCountryDirty(String LEI);
	
	@Query(value="SELECT sector_name FROM sector WHERE sector_class = ?", nativeQuery = true)
	public List<String> getSector(String counterpartyNature);
	
	@Query(value="SELECT sector_name FROM sector WHERE sector_class <> ?", nativeQuery = true)
	public List<String> getSectorDirty(String counterpartyNature);
	
	@Query(value="SELECT currency FROM country WHERE country_code = ?", nativeQuery = true)
	public String getCurrency(String jurisdiction);
	
	@Query(value="SELECT currency FROM country WHERE country_code <> ?", nativeQuery = true)
	public List<String> getCurrencyDirty(String jurisdiction);
	
	@Query(value="SELECT nature_name FROM nature", nativeQuery = true)
	public List<String> getNatureById();

	@Query(value="SELECT sft_symbol FROM sft WHERE sft_name = ?", nativeQuery = true)
	public List<String> getSFT(String tradeType);

	@Query(value="SELECT classification FROM sector_classification", nativeQuery = true)
	public List<String> getAddSectorClass();

	@Query(value="SELECT side_name FROM side", nativeQuery = true)
	public List<String> getCounterPartySide();

	@Query(value="SELECT funding_source_name FROM funding_source", nativeQuery = true)
	public List<String> getFundingSource();

	@Query(value="SELECT col_comp_type FROM collateral_component", nativeQuery = true)
	public List<String> getCollateralComponents();

	@Query(value="SELECT col_qual_type FROM collateral_quality", nativeQuery = true)
	public List<String> getCollateralQuality();

	@Query(value="SELECT col_type FROM collateral_type", nativeQuery = true)
	public List<String> getCollateralTypes();

	@Query(value="SELECT action_type_name FROM action_type", nativeQuery = true)
	public List<String> getActionTypes();
	
	@Query(value="SELECT action_type_name FROM action_type WHERE action_type_name NOT IN (?, ?)", nativeQuery = true)
	public List<String> getActionTypesExcluding(String string, String string2);

	@Query(value="SELECT convention FROM day_count", nativeQuery = true)
	public List<String> getDayCountConventions();

	@Query(value="SELECT period FROM time_period", nativeQuery = true)
	public List<String> getTimePeriod();

	@Query(value="SELECT method_name FROM collateral_method", nativeQuery = true)
	public List<String> getMethods();

	@Query(value="SELECT floating_rate_index FROM floating_rate", nativeQuery = true)
	public List<String> getFloatingRates();

	@Query(value="SELECT level_name FROM trade_level", nativeQuery = true)
	public List<String> getLevels();

	
}
