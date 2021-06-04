package com.IRCTradingDataGenerator.tradingDataGenerator.Services;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

import org.springframework.stereotype.Service;

import com.IRCTradingDataGenerator.tradingDataGenerator.Models.Trade;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

@Service
public class FileService {

	String response = "";

	public String exportTradeToFile(Trade trade) {
		File checkDirectory = new File("C:\\XML_Files");
		if (!checkDirectory.exists()) {
			checkDirectory.mkdir();
		}

		try {
			String fileName = "C:\\XML_Files\\SFTR_" + trade.getTrade_type() + "_Trade_ID_" + trade.getTrade_Id() + ".xml";
			String xml = convertTradeToXMLString(trade);

			writeXMLStringToSystem(xml, fileName);
			response = "File written";
		} catch (Exception e) {
			e.printStackTrace();
			response = "Error writing file";
		}
		return response;
	}

	public String exportTradesToFile(List<Trade> allTrades) {
		File checkDirectory = new File("C:\\XML_Files");
		if (!checkDirectory.exists()) {
			checkDirectory.mkdir();
		}

		try {
			for (Trade trade : allTrades) {
				String fileName = "C:\\XML_Files\\SFTR_" + trade.getTrade_type() + "_Trade_ID_" + trade.getTrade_Id() + ".xml";
				String xml = convertTradeToXMLString(trade);

				writeXMLStringToSystem(xml, fileName);
			}
			response = "Files written";
		} catch (Exception e) {
			e.printStackTrace();
			response = "Error writing files";
		}
		return response;
	}

	public String convertTradeToXMLString(Trade trade) throws Exception {
		ObjectMapper mapper = new XmlMapper();
		String xml = "";
		trade.setTrade_quality(null);

		try {
			xml = mapper.writeValueAsString(trade);
		} catch (Exception e) {
			throw e;
		}
		return xml;
	}

	public void writeXMLStringToSystem(String xml, String fileName) throws Exception {
		try {
			FileWriter fw = new FileWriter(fileName);
			fw.write(xml);
			fw.close();
		} catch (Exception e) {
			throw e;
		}
	}

}
	

