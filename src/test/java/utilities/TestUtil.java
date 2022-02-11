package utilities;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import base.TestBase;

public class TestUtil extends TestBase {
	
	public static String screenShotPath;
	public static String screenShotName;

	public static void captureScreenShot() throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		Date currentDate = new Date();
		screenShotName = currentDate.toString().replace(":", "_").replace(" ", "_")+".jpg";
		FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+"/target/surefire-reports/html/"+screenShotName));
	}

}
