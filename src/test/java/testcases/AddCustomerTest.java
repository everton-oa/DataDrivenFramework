package testcases;

import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.TestBase;
import utilities.TestUtil;

public class AddCustomerTest extends TestBase{
	
	@Test(dataProviderClass=TestUtil.class, dataProvider = "dp")
	public void addCustomerTest(String firstName, String lastName, String postCode, String alertText) {
		click("addCustomerBtn_CSS");
		type("firstNameField_CSS", firstName);
		type("lastNameField_XPATH", lastName);
		type("postCodeField_CSS", postCode);
		click("addBtn_CSS");
		
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		
		Assert.assertTrue(alert.getText().contains(alertText));
		alert.accept();
	}
}
