package com.IRCTradingDataGenerator.tradingDataGenerator;

import com.IRCTradingDataGenerator.tradingDataGenerator.Models.Trade;
import com.IRCTradingDataGenerator.tradingDataGenerator.Services.TradeBuilderService;
import com.IRCTradingDataGenerator.tradingDataGenerator.Services.IDataChange;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class TradeBuilderServiceTests {

	@Autowired
	TradeBuilderService tradeBuilderService;

	@Test
	public void testThatATradeHasFieldsChangedAfterApplyingListOfChanges() {
		Trade trade = new Trade();
		trade.setTrade_level("Yellow");
		trade.setTrade_type("Green");
		trade.setReporting_timestamp("Now");

		IDataChange[] dataChanges = new IDataChange[]{
			selectedTrade -> selectedTrade.setReporting_timestamp(" "),
			selectedTrade -> selectedTrade.setTrade_type(" "),
			selectedTrade -> selectedTrade.setTrade_level(" ")
		};

		String tradeBeforeChanges = trade.toString();
		tradeBuilderService.applyDataChanges(dataChanges, trade);
		String tradeAfterChanges = trade.toString();

		Assertions.assertNotEquals(tradeBeforeChanges, tradeAfterChanges);
	}

	@Test
	public void testThatATradeHasFieldsUnChangedAfterApplyingEmptyListOfChanges() {
		Trade trade = new Trade();
		trade.setTrade_level("Yellow");
		trade.setTrade_type("Green");
		trade.setReporting_timestamp("Now");

		IDataChange[] dataChanges = new IDataChange[]{};

		String tradeBeforeChanges = trade.toString();
		tradeBuilderService.applyDataChanges(dataChanges, trade);
		String tradeAfterChanges = trade.toString();

		Assertions.assertEquals(tradeBeforeChanges, tradeAfterChanges);
	}
}
