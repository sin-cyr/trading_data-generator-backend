package com.IRCTradingDataGenerator.tradingDataGenerator.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.IRCTradingDataGenerator.tradingDataGenerator.Models.Transaction;

public interface TransactionDao extends JpaRepository<Transaction, Integer>{

}
