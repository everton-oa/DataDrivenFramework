package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import utilities.ExcelReader;
import utilities.ExtentManager;

public class TestBase {

	/*
	 * 
	 * WebDriver 
	 * Properties 
	 * Logs 
	 * ExtentReports
	 * ReportNG 
	 * DB 
	 * Excel 
	 * Mail
	 * Jenkins
	 * 
	 */

	public static WebDriver driver;
	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis;
	public static Logger log = Logger.getLogger("devpinoyLogger");
	public static ExcelReader excel = new ExcelReader(System.getProperty("user.dir") + "/src/test/resources/excel/testdata.xlsx");
	public static WebDriverWait wait;
	public ExtentReports rep = ExtentManager.getInstance();
	public static ExtentTest test;

	@BeforeSuite
	public void setUp() {
		if (driver == null) {
			try {
				fis = new FileInputStream(
						System.getProperty("user.dir") + "/src/test/resources/properties/Config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				config.load(fis);
				log.debug("Config file loaded");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				fis = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/properties/OR.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				OR.load(fis);
				log.debug("OR file loaded");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (config.getProperty("browser").equals("chrome")) {
				driver = new ChromeDriver();
				log.debug("Chrome launched");
			} else if (config.getProperty("browser").equals("firefox")) {
				driver = new FirefoxDriver();
				log.debug("Firefox launched");
			}
		}
		driver.get(config.getProperty("url"));
		log.debug("URL opened");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")),
				TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 5);

	}

	@AfterSuite
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
		log.debug("Test execution completed");
	}
	
	public void click(String locator) {
		if (locator.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(OR.getProperty(locator))).click();
		} else if (locator.endsWith("_XPATH")) {
			driver.findElement(By.xpath(OR.getProperty(locator))).click();
		} else if (locator.endsWith("_ID")) {
			driver.findElement(By.id(OR.getProperty(locator))).click();
		}
		test.log(LogStatus.INFO, "Clicked on " + locator);
	}
	
	public void type(String locator, String value) {
		if (locator.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(OR.getProperty(locator))).sendKeys(value);
		} else if (locator.endsWith("_XPATH")) {
			driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(value);
		} else if (locator.endsWith("_ID")) {
			driver.findElement(By.id(OR.getProperty(locator))).sendKeys(value);
		}
		test.log(LogStatus.INFO, "Typed "+value+" on " +locator);
	}
	
	public boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
}
