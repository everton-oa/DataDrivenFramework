package base;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class TestBase {

	/*
	 * 
	 * WebDriver Properties Logs ExtentReports DB Excel Mail
	 * 
	 */

	public static WebDriver driver;
	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis;

	@BeforeMethod
	@BeforeSuite
	public void setUp() throws IOException {
		if (driver == null) {
			fis = new FileInputStream(
					System.getProperty("user.dir") + "/src/test/resources/properties/Config.properties");
			config.load(fis);

			fis = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/properties/OR.properties");
			OR.load(fis);

			if (config.getProperty("browser").equals("chrome")) {
				driver = new ChromeDriver();
			} else if (config.getProperty("browser").equals("firefox")) {
				driver = new FirefoxDriver();
			}
		}
		driver.get(config.getProperty("url"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")),
				TimeUnit.SECONDS);

	}

	@AfterMethod
	@AfterSuite
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}

	}
}
