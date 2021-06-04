package com.IRCTradingDataGenerator.tradingDataGenerator.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.IRCTradingDataGenerator.tradingDataGenerator.Models.Counterparty;

public interface CounterPartyDao extends JpaRepository<Counterparty, Integer>{

}
