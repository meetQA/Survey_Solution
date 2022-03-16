package Listener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;


import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import io.appium.java_client.android.AndroidDriver;

public class Extra_Screen {
	public void Screenshot(AndroidDriver driver,String Classname,String methodname) {
		//log.info("*** Test execution " + result.getMethod().getMethodName() + " failed...");
				//log.info((result.getMethod().getMethodName() + " failed!"));

				//ITestContext context = result.getTestContext();
				//WebDriver driver = (WebDriver) context.getAttribute("driver");
		        // WebDriver driver = null;
		         //driver = (WebDriver) context.getAttribute("WebDriver");
				String targetLocation = null;

				String testClassName = Classname;
				//String timeStamp = Util.getCurrentTimeStamp(); // get timestamp
				String testMethodName = methodname;
				String screenShotName = testMethodName  + ".png";
				String fileSeperator = System.getProperty("file.separator");
				String reportsPath = System.getProperty("user.dir") + fileSeperator + "TestReport" + fileSeperator
						+ "screenshots-pass";
				//log.info("Screen shots reports path - " + reportsPath);
				try {
					File file = new File(reportsPath + fileSeperator + testClassName); // Set
																						// screenshots
																						// folder
					if (!file.exists()) {
						if (file.mkdirs()) {
							//log.info("Directory: " + file.getAbsolutePath() + " is created!");
						} else {
							//log.info("Failed to create directory: " + file.getAbsolutePath());
						}

					}

					File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
					targetLocation = reportsPath + fileSeperator + testClassName + fileSeperator + screenShotName;// define
																													// location
					File targetFile = new File(targetLocation);
					//log.info("Screen shot file location - " + screenshotFile.getAbsolutePath());
					//log.info("Target File location - " + targetFile.getAbsolutePath());
					FileHandler.copy(screenshotFile, targetFile);

				} catch (FileNotFoundException e) {
					//log.info("File not found exception occurred while taking screenshot " + e.getMessage());
				} catch (Exception e) {
					//log.info("An exception occurred while taking screenshot " + e.getCause());
				}

				// attach screenshots to report
				try {
					ExtentTestManager.getTest().pass("Screenshot",
							MediaEntityBuilder.createScreenCaptureFromPath(targetLocation).build());
				} catch (IOException e) {
					//log.info("An exception occured while taking screenshot " + e.getCause());
				}
				ExtentTestManager.getTest().log(Status.PASS, "Test Pass");
//		System.out.println("*** Executed " + result.getMethod().getMethodName() + " test successfully...");
//		ExtentTestManager.getTest().log(Status.PASS, "Test passed");
	}
}
