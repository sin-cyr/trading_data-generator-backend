package com.IRCTradingDataGenerator.tradingDataGenerator.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.IRCTradingDataGenerator.tradingDataGenerator.Models.Trade;
import com.IRCTradingDataGenerator.tradingDataGenerator.Services.FileService;
import com.IRCTradingDataGenerator.tradingDataGenerator.Services.TradeService;

@Controller
@RequestMapping("/fileController")
@CrossOrigin(origins = "http://localhost:3000")
public class FileController {
	
	@Autowired
	FileService fileService;
	
	@Autowired
	TradeService tradeService;
	
	@PostMapping("/ExportToFile/{trade_id}")
	public ResponseEntity<String> exportTradeProductToFile(@PathVariable("trade_id")int trade_id) {
		Trade trade = tradeService.getTradeById(trade_id).get();
		 return ResponseEntity.ok(fileService.exportTradeToFile(trade));
	}
	
	@PostMapping("/ExportAllTradesToFile/")
	public ResponseEntity<String> exportAllTradesProductToFile() {
		List<Trade> allTrades = tradeService.getTrades();
		 return ResponseEntity.ok(fileService.exportTradesToFile(allTrades));
	}

}
