package testcases;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import base.TestBase;

public class LoginTest extends TestBase{
	
	@Test
	public void login() throws InterruptedException {
		log.debug("Starting login test");
		driver.findElement(By.cssSelector(OR.getProperty("bankManagerLoginBtn"))).click();
		Thread.sleep(2000);
		log.debug("Login test successfully executed");
		
	}

}
