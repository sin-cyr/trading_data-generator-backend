package com.IRCTradingDataGenerator.tradingDataGenerator.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.IRCTradingDataGenerator.tradingDataGenerator.Models.Principle;

public interface PrincipleDao extends JpaRepository<Principle, Integer>{

}
