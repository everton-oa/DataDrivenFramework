package testcases;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.TestBase;

public class AddCustomerTest extends TestBase{
	
	@Test(dataProvider="getData")
	public void addCustomerTest(String firstName, String lastName, String postCode) {
		driver.findElement(By.cssSelector(OR.getProperty("addCustomerBtn"))).click();
		driver.findElement(By.cssSelector(OR.getProperty("firstNameField"))).sendKeys(firstName);
		driver.findElement(By.cssSelector(OR.getProperty("lastNameField"))).sendKeys(lastName);
		driver.findElement(By.cssSelector(OR.getProperty("postCodeField"))).sendKeys(postCode);
		driver.findElement(By.cssSelector(OR.getProperty("addBtn"))).click();
	}
	
	@DataProvider
	public Object[][] getData() {
		String sheetName = "AddCustomerTest";
		int rows = excel.getRowCount(sheetName);
		int cols = excel.getColumnCount(sheetName);
		Object[][] data = new Object[rows-1][cols];
		for (int rowNum=2; rowNum<=rows; rowNum++) {
			for (int colNum=0; colNum<cols; colNum++) {
				data[rowNum-2][colNum] = excel.getCellData(sheetName, colNum, rowNum);
			}
		}
		return data;
	}

}
