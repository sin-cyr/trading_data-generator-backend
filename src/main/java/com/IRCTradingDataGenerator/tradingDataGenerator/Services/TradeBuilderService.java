package com.IRCTradingDataGenerator.tradingDataGenerator.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.IRCTradingDataGenerator.tradingDataGenerator.Models.Collateral;
import com.IRCTradingDataGenerator.tradingDataGenerator.Models.Counterparty;
import com.IRCTradingDataGenerator.tradingDataGenerator.Models.Principle;
import com.IRCTradingDataGenerator.tradingDataGenerator.Models.Trade;
import com.IRCTradingDataGenerator.tradingDataGenerator.Models.Transaction;

@Service
public class TradeBuilderService {
	
	@Autowired
	TradeService tradeService;

	@Autowired
	DataChangeService dataChangeService;

	Random rand = new Random();

	public List<Trade> buildTradeProducts(String tradeType, int numberOfTrades, String actionType, int percentage) {
		for (int i = 1; i <= numberOfTrades; i++) {
			Trade trade = buildCleanTradeProduct(tradeType, actionType);
			dirtyTradeCheck(trade, percentage);

			tradeService.save(trade);
		}

		List<Trade> newTrades = tradeService.getTrades();
		return newTrades;
	}
	
	private Trade buildCleanTradeProduct(String tradeType, String actionType) {
		Trade trade = tradeService.createTrade(tradeType, "Clean", actionType);

		Transaction transaction = tradeService.createTransaction(trade);
		trade.setTrade_transaction(transaction);

		Counterparty counterparty = tradeService.createCounterparty(trade);
		trade.setTrade_counterparty(counterparty);

		Principle principle = tradeService.createPrinciple(trade);
		trade.setTrade_principle(principle);

		Collateral collateral = tradeService.createCollateral(trade);
		trade.setTrade_collateral(collateral);

		return trade;
	}

	private void dirtyTradeCheck(Trade trade, int userPercentage) {
		int comparisonPercentage = rand.nextInt(100) + 1;  // avoids zero (0-99)

		if (comparisonPercentage <= userPercentage) {
			trade.setTrade_quality("Dirty");
			IDataChange[] dataChanges = dataChangeService.dataChangeList;
			applyDataChanges(dataChanges, trade);
		}
	}

	public void applyDataChanges(IDataChange[] dataChanges, Trade trade) {
		if (dataChanges.length == 0) {
			return;
		}

		int numberOfErrors = rand.nextInt(dataChanges.length) + 1;
		List<Integer> usedIndexes = new ArrayList<>();

		for (int i = 0; i < numberOfErrors; i++) {
			boolean uniqueDataChange = false;
			int currentIndex = 0;

			while (!uniqueDataChange) {
				currentIndex = rand.nextInt(dataChanges.length);

				if (!usedIndexes.contains(currentIndex)) {
					uniqueDataChange = true;
				}
			}
			usedIndexes.add(currentIndex);
			dataChanges[currentIndex].change(trade);
		}
	}
}
