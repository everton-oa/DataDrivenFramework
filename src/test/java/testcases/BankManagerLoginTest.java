package testcases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import base.TestBase;

public class BankManagerLoginTest extends TestBase{
	
	@Test
	public void login() throws InterruptedException {
		log.debug("Starting login test");
		click("bankManagerLoginBtn_CSS");
		Thread.sleep(2000);
		Assert.assertTrue(isElementPresent(By.cssSelector(OR.getProperty("addCustomerBtn_CSS"))), "Could not find addCustomerBtn");
		log.debug("Login test successfully executed");
		Reporter.log("Login test successfully executed");
		Assert.fail();
	}

}
