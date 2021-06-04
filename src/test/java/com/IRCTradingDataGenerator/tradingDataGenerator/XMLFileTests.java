package com.IRCTradingDataGenerator.tradingDataGenerator;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.IRCTradingDataGenerator.tradingDataGenerator.Models.Trade;
import com.IRCTradingDataGenerator.tradingDataGenerator.Services.FileService;
import com.IRCTradingDataGenerator.tradingDataGenerator.Services.TradeService;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class XMLFileTests {
	
	@Autowired
	TradeService tradeService;
	
	@Autowired
	FileService fileService;

	@Mock
	Trade cleanTrade, dirtyTrade;

	@Test
	public void testThatAnObjectCanBeExported() {
		Trade trade = tradeService.getTradeById(1).get();
		String response = fileService.exportTradeToFile(trade);
		Assertions.assertEquals("File written", response);
	}

	@Test
	public void testThat_AListOfObjects_CanBeExported() {
		List<Trade> allTrades = tradeService.getTrades();
		String response = fileService.exportTradesToFile(allTrades);
		Assertions.assertEquals("Files written", response);
	}

	@Test
	public void testThat_TradeQualityTagNotShowingCleanOrDirty() {
		String cleanXml = "";
		String dirtyXml = "";

		cleanTrade.setTrade_quality("Clean");
		dirtyTrade.setTrade_quality("Dirty");

		try {
			cleanXml = fileService.convertTradeToXMLString(cleanTrade);
			dirtyXml = fileService.convertTradeToXMLString(dirtyTrade);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Assertions.assertTrue(!cleanXml.contains("<trade_quality>Clean"));
		Assertions.assertTrue(!dirtyXml.contains("<trade_quality>Dirty"));
	}

}
