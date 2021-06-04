package com.IRCTradingDataGenerator.tradingDataGenerator.Services;

import com.IRCTradingDataGenerator.tradingDataGenerator.Models.Trade;

public interface IDataChange {
    void change(Trade trade);
};
