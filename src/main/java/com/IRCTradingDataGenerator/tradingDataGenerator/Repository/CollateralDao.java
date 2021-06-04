package com.IRCTradingDataGenerator.tradingDataGenerator.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.IRCTradingDataGenerator.tradingDataGenerator.Models.Collateral;

public interface CollateralDao extends JpaRepository<Collateral, Integer>{

}
