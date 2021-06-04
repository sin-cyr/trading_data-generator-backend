package com.IRCTradingDataGenerator.tradingDataGenerator.Services;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataService {
	
	@Autowired
	DataService dataService;
	
	@Autowired
	TradeService tradeService;
	
	// REGION DATE/TIME
	/* What about 3 options
	 * 0 = dirty data
	 * 1 = no data
	 * 2 = clean data i.e. do nothing to it*/
	public String createDirtyDate() {
		String dirtyDate = "";
		int num = dataService.randomNumber(2);
		if (num == 0) {
			dirtyDate = dataService.createDateFromTodayDirtyFormat();
		}else {
			dirtyDate = dataService.getMissingData();
		}
		return dirtyDate;
	}
	
	public String createDirtyDateTime() {
		String dirtyDateTime;
		int num = dataService.randomNumber(2);
		if (num == 0) {
			dirtyDateTime = dataService.createDateAndTimeFromTodayDirty();
		}else {
			dirtyDateTime = dataService.getMissingData();
		}
		return dirtyDateTime;
	}
	
	public String createDateFromToday() {
		DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
		String formattedDate = formatter.format(LocalDate.now());
		return formattedDate;
	}
	
	public String createDateFromTodayDirtyFormat() {
		DateTimeFormatter formatter = DateTimeFormatter.ISO_ORDINAL_DATE;
		String formattedDate = formatter.format(LocalDate.now());
		return formattedDate;
	}
	
	// still needs work on format
	public String createDateAndTimeFromToday() {
//		DateTimeFormatter formatter = DateTimeFormatter.ISO_INSTANT.withZone(ZoneId.systemDefault());
//		String formattedDate = formatter.format(Instant.now());
//		return formattedDate;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss").withZone(ZoneId.systemDefault());
		String formattedDate = formatter.format(LocalDateTime.now());
		return formattedDate;
	}
	
	public String createDateAndTimeFromTodayDirty() {
		DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
		String formattedDate = formatter.format(LocalDateTime.now());
		return formattedDate;
	}

	
	// REGION STRINGS
	
	public String createDirtyString(String data) {
		String dirtyString;
		int num = dataService.randomNumber(2);
		if(num == 0) {
			dirtyString = dataService.createStringError(data);
		}else {
			dirtyString = dataService.getMissingData();
		}
		return dirtyString;
	}
	
	public String createStringError(String data) {
		Random rand = new Random();
		int num =  rand.nextInt(4);
		String dirtyData = "";
		if(num == 0) {
			dirtyData = dataService.spellingMistake(data);
		}else if (num == 1) {
			dirtyData = dataService.extraLetter(data).toUpperCase();
		} else if (num == 2) {
			dirtyData = dataService.missingLetter(data);
		} else if (num == 3) {
			dirtyData = dataService.lowerCase(data);
		}
		return dirtyData;
	}
	
	public String spellingMistake(String data) {
		Random rand = new Random();
		int index = rand.nextInt(data.length());
		char letterToRemove = data.charAt(index);
		char randomLetter = dataService.randomLetter();
		return data.replace(letterToRemove, randomLetter).toUpperCase();
	}
	
	public String extraLetter(String data) {
		char randomChar = dataService.randomLetter();
		data = data + randomChar;
		return data;
	}
	
	public String missingLetter(String data) {
		Random rand = new Random();
		int index = rand.nextInt(data.length());
		String front = data.substring(0,  index);
		String back = data.substring(index+1);
		return front + back;
	}
	
	public String lowerCase(String data) {
		return data.toLowerCase();
	}
	
	
	// REGION NUMBERS
	
	public String createDirtyNumeric(BigDecimal data) {
		String dirtyNumber;
		int num = dataService.randomNumber(2);
		if(num == 0) {
			dirtyNumber = dataService.createNumberError(data);
		}else {
			dirtyNumber = dataService.getMissingData();
		}
		return dirtyNumber;
	}

	public String createNumberError(BigDecimal data) {
		int num =  dataService.randomNumber(2);
		BigDecimal dirtyData = new BigDecimal("00.00");
		if(num == 0) {
			dirtyData = dataService.multiply(data);
		}else if (num == 1) {
			dirtyData = dataService.divide(data);
		}
		return dirtyData.toString();
	}
	

	public BigDecimal multiply(BigDecimal data) {
		return data.multiply(new BigDecimal("1000"));
	}
	
	public BigDecimal divide(BigDecimal data) {
		return data.divide(new BigDecimal("1000"));
	}
	
	public BigDecimal createNumeric3() {
		SecureRandom random = new SecureRandom();
		String num = Integer.toString(random.nextInt(1000));
		BigDecimal num2 = new BigDecimal(num);
		return num2;
	}
	
	public BigDecimal createNumeric5() {
		SecureRandom random = new SecureRandom();
		String num = Integer.toString(random.nextInt(100000));
		BigDecimal num2 = new BigDecimal(num);
		return num2;
	}

	public BigDecimal createNumeric11() {
		float min = 0.0f;
		float max = 100.0f;
		String randomFloat = String.valueOf((float) (Math.random() * (max - min + 1) + min));
		BigDecimal num2 = new BigDecimal(randomFloat);
		return num2;
	}

	public BigDecimal createNumeric18() {
		BigDecimal min = new BigDecimal("9999.99999");
		BigDecimal max = new BigDecimal("9999999999999.99999");
		BigDecimal randomBigDecimal = min.add(new BigDecimal(Math.random()).multiply(max.subtract(min)));
	    DecimalFormat df = new DecimalFormat("#############.#####");
	    String num =  df.format(randomBigDecimal);
		BigDecimal num2 = new BigDecimal(num);
		return num2;
	}

	
	// REGION GENERAL METHODS
	
	public char randomLetter() {
		Random r = new Random();
		char c = (char)(r.nextInt(26) + 'a');
		return c;
	}

	public String getMissingData() {
		String missingData = "  ";
		return missingData;
	}

	public int randomNumber(int max) {
		Random rand = new Random();
		int num = rand.nextInt(max);
		return num;
	}

	public String createISIN(String LEI) {
		String countryCode = tradeService.getCountry(LEI);
		String idNumbers= "";
		for (int i = 0; i < 9; i++) {
			int randomNum = dataService.randomNumber(9);
			idNumbers += randomNum;
		}
		int checkDigit = dataService.randomNumber(9);
		String isin = countryCode + idNumbers + checkDigit; 
		return isin;
	}
	
	public String createCFI() {
		String cfi = "";
		for (int i = 0; i < 6; i++) {
			cfi += dataService.randomLetter();
		}
		return cfi.toUpperCase();
	}

	public boolean getTrueOrFlase() {
		boolean choice;
		int num = dataService.randomNumber(2);
		if(num == 0) {
			choice = true;
		}else {
			choice = false;
		}
		return choice;
	}
	
}
