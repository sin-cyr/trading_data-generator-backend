package com.IRCTradingDataGenerator.tradingDataGenerator.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.IRCTradingDataGenerator.tradingDataGenerator.Models.TradeRequest;
import com.IRCTradingDataGenerator.tradingDataGenerator.Models.Trade;
import com.IRCTradingDataGenerator.tradingDataGenerator.Services.TradeBuilderService;
import com.IRCTradingDataGenerator.tradingDataGenerator.Services.TradeService;

@Controller
@RequestMapping("/tradeController")
@CrossOrigin(origins = "http://localhost:3000")
public class TradeController {
	
	@Autowired
	TradeBuilderService tradeBuilderService;
	
	@Autowired
	TradeService tradeService;
	
	@PostMapping("/CreateTradeProduct")
	public ResponseEntity<List<Trade>> createTradeProduct(@RequestBody TradeRequest tradeRequest) {
		List<Trade> tradesCreated = tradeBuilderService.buildTradeProducts(tradeRequest.getTradeType(),
				tradeRequest.getQuantity(), tradeRequest.getActionType(), tradeRequest.getPercentage());

		return ResponseEntity.ok(tradesCreated);
	}

	@GetMapping("/GetAllTradeProducts") 
	public ResponseEntity<List<Trade>> getAllTradeProducts() {
		List<Trade> tradeProductsForFront = tradeService.getTrades();
		return ResponseEntity.ok(tradeProductsForFront);
	}
	
	@GetMapping("/GetTradeProductById/{trade_Id}") 
	public ResponseEntity<Optional<Trade>> getTradeProductById(@PathVariable("trade_Id")int trade_Id) {
		return ResponseEntity.ok(tradeService.getTradeById(trade_Id));
	}

}
