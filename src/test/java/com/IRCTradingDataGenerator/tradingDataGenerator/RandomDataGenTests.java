package com.IRCTradingDataGenerator.tradingDataGenerator;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.SharedHttpSessionConfigurer;
import org.springframework.web.context.WebApplicationContext;

import com.IRCTradingDataGenerator.tradingDataGenerator.Services.DataService;
import com.IRCTradingDataGenerator.tradingDataGenerator.Services.TradeService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class RandomDataGenTests {
	
	@Autowired
	TradeService tradeService;
	
	@Autowired
	DataService dataService;

	@Autowired
	WebApplicationContext webApplicationContext;
	
	@Autowired
	ObjectMapper objectMapper;

	MockMvc mockMvc;

	MockHttpSession session;
	
	final static String TRADE_CONTROLLER_URI = "/tradeController";
	
	@BeforeEach
	public void setUp() {
		this.session = new MockHttpSession();
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
				.apply(SharedHttpSessionConfigurer.sharedHttpSession())
				.build();
	}

	@AfterEach
	public void test() {
		this.session = null;
		this.mockMvc = null;
	}
	
	
	@Test
	public void testThat_ISIN_CanBeCreated() {
		String LEI = tradeService.getLEI();
		String countryCode = tradeService.getCountry(LEI);
		String idNumbers= "";
		
		for (int i = 0; i < 9; i++) {
			int randomNum = dataService.randomNumber(9);
			idNumbers += randomNum;
		}
		int checkDigit = dataService.randomNumber(9);
		String isin = countryCode + idNumbers + checkDigit;
		Assertions.assertTrue(isin.length() == 12);
		Assertions.assertTrue(isin.substring(2).matches(".*\\d.*"));
		Assertions.assertFalse(isin.substring(0, 2).matches(".*\\d.*"));
	}
	
	@Test 
	public void testThat_CFI_CanBeCreated() {
		String cfi = "";
		for (int i = 0; i < 6; i++) {
			cfi += dataService.randomLetter();
		}
		Assertions.assertTrue(cfi.length() == 6);
	}

	@Test
	public void testThat_UTI_CanBeCreated() {

	}
	
	// Date
	@Test
	public void testToCreateDate_FromTodaysDate() {
		DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
		String formattedDate = formatter.format(LocalDate.now());
		Assertions.assertTrue(formattedDate.matches("([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))"));
	}
	
	
	// Date and Time
	// FAILING
	@Test
	public void testToCreateDateTime() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-DD'T'HH:mm:ss");
		String formattedDate = LocalDateTime.now().format(formatter);
		System.err.println("formattedDate" + formattedDate);
		Assertions.assertTrue(formattedDate.matches("(\\d{4})-(\\d{2})-(\\d{2})T(\\d{2}):(\\d{2}):(\\d{2})"));
	}
	
	
	// Numeric 3
	@Test
	public void testToCreate3DigitNumber() {
		SecureRandom random = new SecureRandom();
		String num = Integer.toString(random.nextInt(1000));
		BigDecimal num2 = new BigDecimal(num);
		Assertions.assertTrue(num2.toString().length() <= 3);
	}
	
	// Numeric 5
	@Test
	public void testToCreate5DigitNumber() {
		SecureRandom random = new SecureRandom();
		String num = Integer.toString(random.nextInt(100000));
		BigDecimal num2 = new BigDecimal(num);
		Assertions.assertTrue(num2.toString().length() <= 5);
	}
	
	// Numeric 11
	@Test 
	public void testToCreate11DigitNumber() {
		float min = 0.0f;
		float max = 100.0f;
		String randomFloat = String.valueOf((float) (Math.random() * (max - min + 1) + min));
		BigDecimal num2 = new BigDecimal(randomFloat);
		Assertions.assertTrue(num2.toString().length() <= 11);
	}
	
	// Numeric 18
	@Test 
	public void testToCreate18DigitNumber() {
		BigDecimal min = new BigDecimal("9999.99999");
		BigDecimal max = new BigDecimal("9999999999999.99999");
		BigDecimal randomBigDecimal = min.add(new BigDecimal(Math.random()).multiply(max.subtract(min)));
	    DecimalFormat df = new DecimalFormat("#############.#####");
	    String num =  df.format(randomBigDecimal);
		BigDecimal num2 = new BigDecimal(num);
		Assertions.assertTrue(num2.toString().length() <= 19);
		Assertions.assertTrue(num2.scale() <= 5);
	}

}
