package SurveySolution;

import java.lang.reflect.Method;
import java.net.URL;
import java.nio.charset.MalformedInputException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import Listener.ExtentTestManager;
import Listener.Extra_Screen;
import SurveySolution_R.SurveySolution_Testcase_R;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.connection.ConnectionState;
import io.appium.java_client.android.connection.ConnectionStateBuilder;
import io.appium.java_client.appmanagement.ApplicationState;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.offset.PointOption;

@Test
public class SurveySolution_Testcase {

	private static final Object RUNNING_IN_BACKGROUND = null;
	AndroidDriver driver;
	Extra_Screen ll = new Extra_Screen();
	String i = "SurveySolution_Testcase_extra";

	@BeforeMethod
	public void beforeClass(ITestContext context) throws MalformedInputException, Exception {

		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "9");
		caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel 3 XL API 28");
		caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "appium");
		caps.setCapability(MobileCapabilityType.UDID, "emulator-5554");
		caps.setCapability("newCommandTimeout", 10000);

		caps.setCapability("app",
				"C:\\Users\\meet.g\\eclipse-workspace\\Survey_Solution\\src\\main\\resourse\\SSQPad_18102021_v1.0_rev1.3_QA.apk");
		URL url = new URL("http://0.0.0.0:4723/wd/hub");
		driver = new AndroidDriver(url, caps);

		context.setAttribute("AndroidDriver", driver);
	}

	@Test(description = "To verify that application Installed successfully.")
	public void SSQPad_01() throws Exception {
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> SSQPad application should be available at newly installed app.");
		String appId = "com.IGiS.QPadSS";
		System.out.println(driver.isAppInstalled(appId));
		Assert.assertEquals(true, driver.isAppInstalled(appId));

	}

	@Test(description = "To verify that application Uninstalled successfully.")
	public void SSQPad_02() throws Exception {
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on Mobile settings -> App -> SSQPad application -> Uninstall.");
		String appId = "com.IGiS.QPadSS";
		driver.removeApp(appId);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Result:</b> SSQPad application should get uninstalled.");
		System.out.println(driver.isAppInstalled(appId));
		Assert.assertEquals(false, driver.isAppInstalled(appId));

	}

	@Test(description = "To verify the Icon of application.")
	public void SSQPad_03() throws Exception {
		/*
		 * ExtentTestManager.getTest().log(Status.INFO,
		 * "<b>Step-1:</b> Open Application section on mobile."); String
		 * appId="com.IGiS.QPadSS"; driver.removeApp(appId);
		 * ExtentTestManager.getTest().log(Status.INFO,
		 * "<b>Result:</b> User should be able to view SSQPad application icon.");
		 * System.out.println(driver.isAppInstalled(appId)); Assert.assertEquals(false,
		 * driver.isAppInstalled(appId));
		 */

	}

	@Test(description = "To verify the Splash screen.")
	public void SSQPad_04() throws Exception {
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> Splash screen should be visible for 2 seconds with following information,\r\n"
						+ "1) IGiS Qpad logo\r\n" + "2) Scanpoint Geomatics Limited logo.");
		System.out.print(driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/imageView2")).isDisplayed());
		Assert.assertEquals(true, driver.findElement(MobileBy.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widge"
						+ "t.FrameLayout/android.view.ViewGroup/android.widget.LinearLayout/android.widget.ImageView"))
				.isDisplayed());
	}

	@Test(description = "To verify that Location access permission dialogs pops up.")
	public void SSQPad_05() throws Exception {

		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> 1)Splash screen should be visible for 2 seconds.\r\n"
						+ "2) Sign in page open with \"Location access permission\" dialog.");
		Thread.sleep(4000);
		System.out.println(
				driver.findElement(MobileBy.id("com.android.packageinstaller:id/permission_message")).getText());
		String Location_Message = "Allow QPad- Survey Solution to access this device's location?";
		Assert.assertEquals(Location_Message,
				driver.findElement(MobileBy.id("com.android.packageinstaller:id/permission_message")).getText());
	}

	/* Permission Access */

	@Test(description = "To verify that user is notified with Dialog box on denying permissions for first time.")
	public void SSQPad_06() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Deny\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_deny_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Deny\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_deny_Location)).click();
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> 1) User should be able deny permission for \"Photos, Media and Files\".\r\n"
						+ "2) User should get \"Permission\" dialog box open with \"Ok\" button and message \"Application needs some permissions to work properly. It includes writing to external storage (to save data on card) and access to location service\".");
		Thread.sleep(4000);
		System.out.println(driver.findElement(MobileBy.id("android:id/message")).getText());
		String permission = "Application needs some permissions to work properly. It includes writing to external storage (to save data on card) and access to location services.";
		Assert.assertEquals(permission, driver.findElement(MobileBy.id("android:id/message")).getText());
	}

	@Test(description = "To verify that user is able to get Permission dialog open second time by tapping on \"Ok\" button from \"Permission\" dialog box.")
	public void SSQPad_07() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Deny\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_deny_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Deny\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_deny_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-3:</b> Tap on \"Ok\" button from \"Permission\" dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Ok_permission)).click();
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> Sign in page open with \"Location access permission\" dialog.");
		Thread.sleep(4000);
		System.out.println(
				driver.findElement(MobileBy.id("com.android.packageinstaller:id/permission_message")).getText());
		String Location_Message = "Allow QPad- Survey Solution to access this device's location?";
		Assert.assertEquals(Location_Message,
				driver.findElement(MobileBy.id("com.android.packageinstaller:id/permission_message")).getText());
	}

	@Test(description = "To verify that user is notified with Warning Dialog on denial of permissions for second time.")
	public void SSQPad_08() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Deny\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_deny_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Deny\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_deny_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-3:</b> Tap on \"Ok\" button from \"Permission\" dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Ok_permission)).click();
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-4:</b> Tap on \"Deny\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_deny_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-5:</b> Tap on \"Deny\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_deny_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> \"Permission Required\" dialog should open with \"Open Setting\" and \"Ok\" button.");
		Thread.sleep(4000);
		System.out.println(driver.findElement(MobileBy.id("android:id/message")).getText());
		String Location_Message = "You can't proceed further without mandatory location and storage permissions";
		Assert.assertEquals(Location_Message, driver.findElement(MobileBy.id("android:id/message")).getText());
	}

	@Test(description = "To verify that Application Closes by tapping on \"OK\" button of \"Permission Required\" Dialog.")
	public void SSQPad_09() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Deny\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_deny_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Deny\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_deny_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-3:</b> Tap on \"Ok\" button from \"Permission\" dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Ok_permission)).click();
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-4:</b> Tap on \"Deny\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_deny_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-5:</b> Tap on \"Deny\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_deny_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-6:</b> Tap on \"Ok\" button from \"Permission Required\" dialog.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Ok_permission)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> Application should get closed by tapping on \"Ok\" button from \"Permission Required\" dialog.");
		Thread.sleep(4000);
		ApplicationState aaa = driver.queryAppState("com.IGiS.QPadSS");
		System.out.println(aaa);
		String ss = aaa.toString();
		Assert.assertEquals(ss, "RUNNING_IN_BACKGROUND");
	}

	@Test(description = "To verify that user is allowed to access Sign in page.")
	public void SSQPad_13() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> 1) User should be able to allow \"Photos, media and files\" permission.\r\n"
						+ "2) User should get \"Sign in\" page open with,\r\n" + "User ID text bar\r\n"
						+ "Password text bar\r\n" + "\"Sign in \" button.");
		Thread.sleep(4000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/main_toolbar")).isDisplayed());
	}

	@Test(description = "To verify that user can not  login without entering credentials.")
	public void SSQPad_14() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> User should get validation message of \"Please Enter User ID\" at User ID text bar.");
		driver.getPageSource();
	}

	@Test(description = "To verify password visibility toggle functionality.")
	public void SSQPad_15(Method method) throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-5:</b> Tap on \"Show password\" (eye) icon from \"Password\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_toggle_Passwoprd)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Result:</b> User should be able to read entered password.");
		Assert.assertEquals("false",
				driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).getAttribute("password"));
		ll.Screenshot(driver, i, method.getName() + "_01");
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-6:</b> Tap on \"Hide password\" (eye) icon from \"Password\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_toggle_Passwoprd)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> User should not be able to read entered password.");
		Assert.assertEquals("true",
				driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).getAttribute("password"));
	}

	@Test(description = "To verify that user get warning dialog for sign in without internet.")
	public void SSQPad_16() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		ConnectionState state = driver.setConnection(new ConnectionStateBuilder().withDataDisabled().build());
		ConnectionState state1 = driver.setConnection(new ConnectionStateBuilder().withWiFiDisabled().build());
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> User should get dialog open for \"Please check your internet connection\" and \"Ok\" button.");
		Assert.assertEquals("Please check your Internet connection.",
				driver.findElement(MobileBy.className("android.widget.TextView")).getText());

		ConnectionState state2 = driver.setConnection(new ConnectionStateBuilder().withDataEnabled().build());
		ConnectionState state3 = driver.setConnection(new ConnectionStateBuilder().withWiFiEnabled().build());
		Thread.sleep(2000);
	}

	@Test(description = "To verify that user can not login with wrong Credentials.")
	public void SSQPad_17() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter invalid user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_U178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter invalid password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sFEgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(61000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> 1) User should get toast message of \"Invalid User ID or Password\".\r\n"
						+ "2) User should not get signed in.");
		Assert.assertEquals("Please check your Internet connection.",
				driver.findElement(MobileBy.className("android.widget.TextView")).getText());

	}

	@Test(description = "To verify that user can not login if can not connect to server or server is off.")
	public void SSQPad_18() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(61000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> 1) User should get toast message of \"Unable to connect to server\".\r\n"
						+ "2) User should not get signed in.");
		Assert.assertEquals("Please check your Internet connection.",
				driver.findElement(MobileBy.className("android.widget.TextView")).getText());

	}

	@Test(description = "To verify that user can login successfully.")
	public void SSQPad_19() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(6000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> 1) User should get dialog of \"Signing in…\"\r\n" + "2) User should get logged in.\r\n"
						+ "3) User should get \"Loading Project\" dialog till loading projects.\r\n"
						+ "4) User should get Home page of SSQpad application.");
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();

	}

	@Test(description = "To verify \"Home\" page of SSQpad mobile application.")
	public void SSQPad_20() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> 1) User should get signed in.\r\n"
						+ "2) User should get following buttons on \"Home\" page of application,\r\n"
						+ "- Zoom in /Out controller\r\n" + "- Edit tool\r\n" + "- Top panel");
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Zoom_In)).isDisplayed());
		Assert.assertEquals(true,
				driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Zoom_Out)).isDisplayed());
		Assert.assertEquals(true,
				driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Edit_Tool)).isDisplayed());
		Assert.assertEquals(true, driver.findElement(MobileBy.id(SurveySolution_Testcase_R.Top_Panel)).isDisplayed());

	}

	@Test(description = "To verify the view of \"Top panel\" of SSQ pad application.")
	public void SSQPad_21() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> User should get following functionality available at Top panel,\r\n"
						+ "Hamburger menu\r\n" + "Application title\r\n" + "Locate button\r\n"
						+ "Sync all data button\r\n" + "Start new track\r\n" + "Refresh\r\n" + "Setting\r\n" + "Help");
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		Assert.assertEquals(true,
				driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).isDisplayed());
		Assert.assertEquals(true, driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Locate)).isDisplayed());
		Assert.assertEquals(true, driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sync)).isDisplayed());
		Assert.assertEquals(true,
				driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_More_Option)).isDisplayed());
	}

	@Test(description = "To verify that user is able to expand Hamburger menu.")
	public void SSQPad_22() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Hamburger menu\" symbol from top panel.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> User should get \"Hamburger menu\" open with following section,\r\n"
						+ "1) Base maps\r\n" + "2) Track\r\n" + "3) Projects\r\n" + "4) [+] icon.");
		Assert.assertEquals(true,
				driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Base_Maps)).isDisplayed());
		Assert.assertEquals(true,
				driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Track)).isDisplayed());
		Assert.assertEquals(true,
				driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_New_Layer)).isDisplayed());
		Assert.assertEquals(true,
				driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Projects)).isDisplayed());
	}

	@Test(description = "To verify user is able to close \"Hamburger menu\".")
	public void SSQPad_23() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Hamburger menu\" symbol from top panel.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-7:</b> Tap outside the expanded \"Hamburger menu\" section.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Zoom_In)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Result:</b> Hamburger menu should get collapsed.");
		Assert.assertEquals(true,
				driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).isDisplayed());

	}

	@Test(description = "To verify that user is able to get back to the current location by \"Locate\" functionality.")
	public void SSQPad_24() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Move map screen to view another location on map.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Zoom_In)).click();
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Zoom_In)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"Locate\" icon from top panel.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Locate)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> User should be able to view current location in centre on map with settled default zoom level.");

	}

	@Test(description = "To verify that user is able to sync all pending data.")
	public void SSQPad_25() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-6:</b> Tap on Top panel \"More option\" -> Setting -> General.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_More_Option)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Settings)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_General)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Deselect \"Auto Sync\" checkbox.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.chk_AutoSync)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-8:</b> Tap on \"Back\" icon from general setting section.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Back)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on \"Back\" icon from setting section.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Back)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-10:</b> Tap on \"Hamburger menu\".");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-11:</b> Tap on \"More option\" -> Edit.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More_Project)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Edit_P)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-12:</b> Tap on \"Add geometry\" (+) icon from bottom panel.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Add_new_geometry)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-13:</b> Tap on \"Save\" icon from top panel of \"Action\" mode.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Save)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-14:</b> Tap on Camera icon from \"Photo\" field.");
		TouchAction swipe = new TouchAction(driver).press(PointOption.point(13, 1553))
				.moveTo(PointOption.point(13, 225)).release().perform();
		Thread.sleep(2000);
		TouchAction swipe1 = new TouchAction(driver).press(PointOption.point(13, 1553))
				.moveTo(PointOption.point(13, 225)).release().perform();
		Thread.sleep(2000);
		TouchAction swipe2 = new TouchAction(driver).press(PointOption.point(13, 1553))
				.moveTo(PointOption.point(13, 225)).release().perform();
		Thread.sleep(2000);
		TouchAction swipe3 = new TouchAction(driver).press(PointOption.point(13, 1553))
				.moveTo(PointOption.point(13, 225)).release().perform();
		Thread.sleep(2000);
		TouchAction swipe4 = new TouchAction(driver).press(PointOption.point(13, 1553))
				.moveTo(PointOption.point(13, 225)).release().perform();
		Thread.sleep(2000);
		TouchAction swipe5 = new TouchAction(driver).press(PointOption.point(13, 1553))
				.moveTo(PointOption.point(13, 225)).release().perform();
		Thread.sleep(2000);
		TouchAction swipe6 = new TouchAction(driver).press(PointOption.point(13, 1553))
				.moveTo(PointOption.point(13, 225)).release().perform();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Camera)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-15:</b> Tap on \"Add photo\" -> Pick photo from Gallery.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Pick_Photo)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-16:</b> Select photo from Gallery.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Capture)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-17:</b> Tap on \"Apply\" icon from \"Set attributes\" form.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Apply)).click();
		Thread.sleep(10000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-18:</b> Tap on \"Sync all Data\" icon from top panel.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sync)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> User should get validation message of \"All Data Synced\".");
		Assert.assertEquals("All Data Synced",
				driver.findElement(MobileBy.className("android.widget.TextView")).getText());

	}

	@Test(description = "To verify that user is able to start new track.")
	public void SSQPad_26() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-6:</b> Tap on Top panel \"More option\" -> Start new Track.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_More_Option)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Start_New_Track)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> 1) User should be able to view Current location symbol changes to arrow.\r\n"
						+ "2) Line feature drawn on map as per user walking around location.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_More_Option)).click();
		Thread.sleep(2000);
		Assert.assertEquals("Stop track",
				driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Start_New_Track)).getAttribute("text"));

	}

	@Test(description = "To verify that user is able to stop recording track.")
	public void SSQPad_27() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-6:</b> Tap on Top panel \"More option\" -> Start new Track.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_More_Option)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Start_New_Track)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-7:</b> Tap on Top panel \"More option\" -> \r\n" + "Stop Track.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_More_Option)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Start_New_Track)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> Tracking by walk functionality should get closed.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_More_Option)).click();
		Thread.sleep(2000);
		Assert.assertEquals("Start new track",
				driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Start_New_Track)).getAttribute("text"));

	}

	@Test(description = "To verify that user is able to \"Refresh\".")
	public void SSQPad_28() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		// ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on Top panel
		// \"More option\" -> Refresh.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_More_Option)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Refresh)).click();
		Thread.sleep(2000);

		// ExtentTestManager.getTest().log(Status.INFO,"<b>Result:</b> Map screen should
		// get reload.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_More_Option)).click();
		Thread.sleep(2000);
		Assert.assertEquals("Refresh",
				driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Refresh)).getAttribute("text"));
	}

	@Test(description = "To verify that user is able to view \"Help\" section.")
	public void SSQPad_29() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		// ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on Top panel
		// \"More option\" -> Refresh.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_More_Option)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Help)).click();
		Thread.sleep(2000);

		/*
		 * ExtentTestManager.getTest().log(Status.
		 * INFO,"<b>Result:</b> 1) User should get Help section open with\r\n" +
		 * "Support sub-section\r\n" + "About sub-section\r\n" + "IGiS Qpad link");
		 */
		Assert.assertEquals(true,
				driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Support_Section)).isDisplayed());
		Assert.assertEquals(true,
				driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_About_Section)).isDisplayed());
		Assert.assertEquals(true,
				driver.findElement(MobileBy.id(SurveySolution_Testcase_R.lnk_IGisQpad)).isDisplayed());
	}

	@Test(description = "To verify that user is able to \"Support\" help section.")
	public void SSQPad_30() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		// ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on Top panel
		// \"More option\" -> Refresh.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_More_Option)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Help)).click();
		Thread.sleep(2000);
		/*
		 * ExtentTestManager.getTest().log(Status.
		 * INFO,"<b>Result:</b> 1) User should get Help section open with Support and About sub-sections.\r\n"
		 * + "2) User should be able to view following support links,\r\n" + "Email\r\n"
		 * + "Telegram\r\n" + "IGiS\r\n" + "Website");
		 */
		Assert.assertEquals(true, driver.findElement(MobileBy.id(SurveySolution_Testcase_R.lnk_Email)).isDisplayed());
		Assert.assertEquals(true,
				driver.findElement(MobileBy.id(SurveySolution_Testcase_R.lnk_Telegram)).isDisplayed());
		Assert.assertEquals(true, driver.findElement(MobileBy.id(SurveySolution_Testcase_R.lnk_IGiS)).isDisplayed());
		Assert.assertEquals(true, driver.findElement(MobileBy.id(SurveySolution_Testcase_R.lnk_Website)).isDisplayed());
	}

	@Test(description = "To verify that user is able to open Email link.")
	public void SSQPad_31() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		// ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on Top panel
		// \"More option\" -> Refresh.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_More_Option)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Help)).click();
		Thread.sleep(2000);
		// ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"Email\"
		// link.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.lnk_Email)).click();
		Thread.sleep(2000);

		// ExtentTestManager.getTest().log(Status.INFO,"<b>Result:</b> User should be
		// able to open Email link at Gmail application.");

		ApplicationState aaa = driver.queryAppState("com.google.android.gm");
		System.out.println(aaa);
		String ss = aaa.toString();
		Assert.assertEquals(ss, "RUNNING_IN_FOREGROUND");
	}

	@Test(description = "To verify that user is able to open Telegram link.")
	public void SSQPad_32() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		// ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on Top panel
		// \"More option\" -> Refresh.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_More_Option)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Help)).click();
		Thread.sleep(2000);
		// ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on
		// \"Telegram\" link.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.lnk_Telegram)).click();
		Thread.sleep(2000);

		// ExtentTestManager.getTest().log(Status.INFO,"<b>Result:</b> User should be
		// able to open Telegram link.");

	}

	@Test(description = "To verify that user is able to open IGiS link.")
	public void SSQPad_33() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		// ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on Top panel
		// \"More option\" -> Refresh.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_More_Option)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Help)).click();
		Thread.sleep(2000);
		// ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"IGiS\"
		// link.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.lnk_IGiS)).click();
		Thread.sleep(2000);

		// ExtentTestManager.getTest().log(Status.INFO,"<b>Result:</b> User should be
		// able to open IGiS link at browser.");
		ApplicationState aaa = driver.queryAppState("com.android.chrome");
		System.out.println(aaa);
		String ss = aaa.toString();
		Assert.assertEquals(ss, "RUNNING_IN_FOREGROUND");

	}

	@Test(description = "To verify that user is able to open Website link.")
	public void SSQPad_34() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		// ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on Top panel
		// \"More option\" -> Refresh.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_More_Option)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Help)).click();
		Thread.sleep(2000);
		// ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"IGiS\"
		// link.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.lnk_Website)).click();
		Thread.sleep(4000);

		// ExtentTestManager.getTest().log(Status.INFO,"<b>Result:</b> User should be
		// able to open IGiS link at browser.");
		ApplicationState aaa = driver.queryAppState("com.android.chrome");
		System.out.println(aaa);
		String ss = aaa.toString();
		Assert.assertEquals(ss, "RUNNING_IN_FOREGROUND");

	}

	@Test(description = "To verify that user is able to view \"About\" help section.")
	public void SSQPad_35() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		// ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on Top panel
		// \"More option\" -> Refresh.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_More_Option)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Help)).click();
		Thread.sleep(2000);
		// ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"About\"
		// sub section.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_About_Section)).click();
		Thread.sleep(4000);

		// ExtentTestManager.getTest().log(Status.INFO,"<b>Result:</b> User should be
		// able to open IGiS link at browser.");
		Assert.assertEquals(true,
				driver.findElement(MobileBy.id(SurveySolution_Testcase_R.lnk_ContactUS)).isDisplayed());
		Assert.assertEquals(true, driver.findElement(MobileBy.id(SurveySolution_Testcase_R.lnk_Credits)).isDisplayed());
		Assert.assertEquals(true,
				driver.findElement(MobileBy.id(SurveySolution_Testcase_R.lnk_Test_Version)).isDisplayed());

	}

	@Test(description = "To verify that user is able to view Credits dialog open by tapping on \"Credits\" button.")
	public void SSQPad_36() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		// ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on Top panel
		// \"More option\" -> Refresh.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_More_Option)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Help)).click();
		Thread.sleep(2000);
		// ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"About\"
		// sub section.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_About_Section)).click();
		Thread.sleep(2000);
		// ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on
		// \"Credits\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.lnk_Credits)).click();
		Thread.sleep(2000);

		// ExtentTestManager.getTest().log(Status.INFO,"<b>Result:</b> User should get
		// Credits dialog open.");
		Assert.assertEquals(true, driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/parentPanel")).isDisplayed());

		// ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on \"Ok\"
		// button from Credit dialog.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		// ExtentTestManager.getTest().log(Status.INFO,"<b>Result:</b> User should get
		// Credits dialog closed.");
		Assert.assertEquals(true, driver.findElement(MobileBy.id(SurveySolution_Testcase_R.lnk_Credits)).isDisplayed());

	}

	@Test(description = "To verify that  user is able to view Test version location.")
	public void SSQPad_37() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		// ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on Top panel
		// \"More option\" -> Refresh.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_More_Option)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Help)).click();
		Thread.sleep(2000);
		// ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"About\"
		// sub section.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_About_Section)).click();
		Thread.sleep(2000);
		// ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Test
		// Version - Location\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.lnk_Test_Version)).click();
		Thread.sleep(4000);
		// ExtentTestManager.getTest().log(Status.INFO,"<b>Result:</b> User should get
		// test version location open at chrome browser.");
		ApplicationState aaa = driver.queryAppState("com.android.chrome");
		System.out.println(aaa);
		String ss = aaa.toString();
		Assert.assertEquals(ss, "RUNNING_IN_FOREGROUND");

	}

	@Test(description = "To verify that user is able to get SGL IGiS web site's customer support section open by tapping on \"Contact us\" link.")
	public void SSQPad_38() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		// ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on Top panel
		// \"More option\" -> Refresh.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_More_Option)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Help)).click();
		Thread.sleep(2000);
		// ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"About\"
		// sub section.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_About_Section)).click();
		Thread.sleep(2000);
		// ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Contact
		// us\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.lnk_ContactUS)).click();
		Thread.sleep(4000);
		// ExtentTestManager.getTest().log(Status.INFO,"<b>Result:</b> User should get
		// test version location open at chrome browser.");
		ApplicationState aaa = driver.queryAppState("com.android.chrome");
		System.out.println(aaa);
		String ss = aaa.toString();
		Assert.assertEquals(ss, "RUNNING_IN_FOREGROUND");

	}

	@Test(description = "To verify that user is able to view SGL IGiS web site by tapping on \"IGiSQPad\" link from bottom of \"Help\" section.")
	public void SSQPad_39() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		// ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on Top panel
		// \"More option\" -> Refresh.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_More_Option)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Help)).click();
		Thread.sleep(2000);
		// ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"About\"
		// sub section.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_About_Section)).click();
		Thread.sleep(2000);
		// ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on
		// \"IGiSQPad\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.lnk_IGisQpad)).click();
		Thread.sleep(4000);
		// ExtentTestManager.getTest().log(Status.INFO,"<b>Result:</b> User should get
		// test version location open at chrome browser.");
		ApplicationState aaa = driver.queryAppState("com.android.chrome");
		System.out.println(aaa);
		String ss = aaa.toString();
		Assert.assertEquals(ss, "RUNNING_IN_FOREGROUND");

	}

	@Test(description = "To verify that user is able to close \"Help\" section.")
	public void SSQPad_40() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		// ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on Top panel
		// \"More option\" -> Refresh.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_More_Option)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Help)).click();
		Thread.sleep(2000);
		// ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"About\"
		// sub section.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_About_Section)).click();
		Thread.sleep(2000);
		// ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on Back (<-)
		// symbol from Help page.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Back)).click();
		Thread.sleep(4000);
		// ExtentTestManager.getTest().log(Status.INFO,"<b>Result:</b> User should be
		// able to get back on \"Home\" page of SSQPad application.");
		Assert.assertEquals(true, driver.findElement(MobileBy.id(SurveySolution_Testcase_R.Top_Panel)).isDisplayed());
	}

	@Test(description = "To verify that user is able to view settings section open from top panel of home page.")
	public void SSQPad_41() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		// ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on Top panel
		// \"More option\" -> Setting.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_More_Option)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Settings)).click();
		Thread.sleep(2000);
		/*
		 * ExtentTestManager.getTest().log(Status.
		 * INFO,"<b>Result:</b> User should get \"Setting\" section open with following setting options,\r\n"
		 * + "General\r\n" + "Map\r\n" + "Location\r\n" + "My tracks.");
		 */
		Assert.assertEquals(true,
				driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_General)).isDisplayed());
		Assert.assertEquals(true, driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Map)).isDisplayed());
		Assert.assertEquals(true,
				driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Location)).isDisplayed());
		Assert.assertEquals(true,
				driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_My_Tracks)).isDisplayed());
	}

	@Test(description = "To verify that user is able to get back on home page from settings section.")
	public void SSQPad_42() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		// ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on Top panel
		// \"More option\" -> Setting.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_More_Option)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Settings)).click();
		Thread.sleep(2000);
		/*
		 * ExtentTestManager.getTest().log(Status.
		 * INFO,"<b>Result:</b> User should get \"Setting\" section open with following setting options,\r\n"
		 * + "General\r\n" + "Map\r\n" + "Location\r\n" + "My tracks.");
		 */
		Assert.assertEquals(true,
				driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_General)).isDisplayed());
		Assert.assertEquals(true, driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Map)).isDisplayed());
		Assert.assertEquals(true,
				driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Location)).isDisplayed());
		Assert.assertEquals(true,
				driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_My_Tracks)).isDisplayed());
		// ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on Back (<-)
		// symbol from Help page.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Back)).click();
		Thread.sleep(4000);
		// ExtentTestManager.getTest().log(Status.INFO,"<b>Result:</b> User should be
		// able to get back on \"Home\" page of SSQPad application.");
		Assert.assertEquals(true, driver.findElement(MobileBy.id(SurveySolution_Testcase_R.Top_Panel)).isDisplayed());
	}

	@Test(description = "To verify the view of \"General - Setting\" page.")
	public void SSQPad_43() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		// ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on Top panel
		// \"More option\" -> Setting.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_More_Option)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Settings)).click();
		Thread.sleep(2000);
		// ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on Top panel
		// \"More option\" -> Setting -> General.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_General)).click();
		Thread.sleep(2000);
		/*
		 * ExtentTestManager.getTest().log(Status.
		 * INFO,"<b>Result:</b> User should be able to view General Setting page with,\r\n"
		 * + "Account\r\n" + "Data Sync\r\n" + "Interface\r\n" +
		 * "Compass - Show true north checkbox\r\n" +
		 * "Compass - Show magnetic checkbox\r\n" +
		 * "Compass - Show Vibrate on bezel touch checkbox\r\n" +
		 * "Compass - Show keep compass screen on checkbox\r\n" + "Media attachment");
		 */
		Assert.assertEquals(true,
				driver.findElement(MobileBy.id(SurveySolution_Testcase_R.label_UserId)).isDisplayed());

		Assert.assertEquals(true,
				driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.chk_AutoSync)).isDisplayed());
		Assert.assertEquals(true,
				driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Reset_Defaults)).isDisplayed());
		Assert.assertEquals(true,
				driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.chk_Show_true_north)).isDisplayed());
		Assert.assertEquals(true,
				driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.chk_Show_magnetic)).isDisplayed());
		Assert.assertEquals(true,
				driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.chk_Vibrate_on_bezel_touch)).isDisplayed());
		Assert.assertEquals(true,
				driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.chk_Keep_compass_Screen)).isDisplayed());
		Assert.assertEquals(true,
				driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.label_Media_Attachment)).isDisplayed());

	}

	@Test(description = "To verify that user is able to view user ID.")
	public void SSQPad_44() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		// ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on Top panel
		// \"More option\" -> Setting.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_More_Option)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Settings)).click();
		Thread.sleep(2000);
		// ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on Top panel
		// \"More option\" -> Setting -> General.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_General)).click();
		Thread.sleep(2000);

		// ExtentTestManager.getTest().log(Status. INFO,"<b>Result:</b> User should be
		// able to view user id at \"Account\".");

		Assert.assertEquals(true,
				driver.findElement(MobileBy.id(SurveySolution_Testcase_R.label_UserId)).isDisplayed());
	}

	@Test(description = "To verify that user is able to Disable Auto Sync option for upload survey data.")
	public void SSQPad_45() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		// ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on Top panel
		// \"More option\" -> Setting -> General.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_More_Option)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Settings)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_General)).click();
		Thread.sleep(2000);
		// ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Deselect \"Auto
		// Sync\" checkbox.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.chk_AutoSync)).click();
		Thread.sleep(2000);
		// ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Back\"
		// icon from general setting section.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Back)).click();
		Thread.sleep(2000);
		// ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on \"Back\"
		// icon from setting section.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Back)).click();
		Thread.sleep(2000);
		// ExtentTestManager.getTest().log(Status.INFO, "<b>Step-10:</b> Tap on
		// \"Hamburger menu\".");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		// ExtentTestManager.getTest().log(Status.INFO, "<b>Step-11:</b> Tap on \"More
		// option\" -> Edit.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More_Project)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Edit_P)).click();
		Thread.sleep(2000);
		// ExtentTestManager.getTest().log(Status.INFO, "<b>Step-12:</b> Tap on \"Add
		// geometry\" (+) icon from bottom panel.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Add_new_geometry)).click();
		Thread.sleep(2000);
		// ExtentTestManager.getTest().log(Status.INFO, "<b>Step-13:</b> Tap on \"Save\"
		// icon from top panel of \"Action\" mode.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Save)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		// ExtentTestManager.getTest().log(Status.INFO, "<b>Step-14:</b> Tap on Camera
		// icon from \"Photo\" field.");
		TouchAction swipe = new TouchAction(driver).press(PointOption.point(13, 1553))
				.moveTo(PointOption.point(13, 225)).release().perform();
		Thread.sleep(2000);
		TouchAction swipe1 = new TouchAction(driver).press(PointOption.point(13, 1553))
				.moveTo(PointOption.point(13, 225)).release().perform();
		Thread.sleep(2000);
		TouchAction swipe2 = new TouchAction(driver).press(PointOption.point(13, 1553))
				.moveTo(PointOption.point(13, 225)).release().perform();
		Thread.sleep(2000);
		TouchAction swipe3 = new TouchAction(driver).press(PointOption.point(13, 1553))
				.moveTo(PointOption.point(13, 225)).release().perform();
		Thread.sleep(2000);
		TouchAction swipe4 = new TouchAction(driver).press(PointOption.point(13, 1553))
				.moveTo(PointOption.point(13, 225)).release().perform();
		Thread.sleep(2000);
		TouchAction swipe5 = new TouchAction(driver).press(PointOption.point(13, 1553))
				.moveTo(PointOption.point(13, 225)).release().perform();
		Thread.sleep(2000);
		TouchAction swipe6 = new TouchAction(driver).press(PointOption.point(13, 1553))
				.moveTo(PointOption.point(13, 225)).release().perform();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Camera)).click();
		Thread.sleep(2000);
		// ExtentTestManager.getTest().log(Status.INFO, "<b>Step-15:</b> Tap on \"Add
		// photo\" -> Pick photo from Gallery.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Pick_Photo)).click();
		Thread.sleep(2000);
		// ExtentTestManager.getTest().log(Status.INFO, "<b>Step-16:</b> Select photo
		// from Gallery.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Capture)).click();
		Thread.sleep(2000);
		// ExtentTestManager.getTest().log(Status.INFO, "<b>Step-17:</b> Tap on
		// \"Apply\" icon from \"Set attributes\" form.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Apply)).click();
		Thread.sleep(10000);

	}

	@Test(description = "To verify that user is able to on Auto Sync option for upload survey data.")
	public void SSQPad_46() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		// ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on Top panel
		// \"More option\" -> Setting -> General.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_More_Option)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Settings)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_General)).click();
		Thread.sleep(2000);
		// ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> select \"Auto
		// Sync\" checkbox.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.chk_AutoSync)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.chk_AutoSync)).click();
		Thread.sleep(2000);
		// ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Back\"
		// icon from general setting section.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Back)).click();
		Thread.sleep(2000);
		// ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on \"Back\"
		// icon from setting section.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Back)).click();
		Thread.sleep(2000);
		// ExtentTestManager.getTest().log(Status.INFO, "<b>Step-10:</b> Tap on
		// \"Hamburger menu\".");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		// ExtentTestManager.getTest().log(Status.INFO, "<b>Step-11:</b> Tap on \"More
		// option\" -> Edit.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More_Project)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Edit_P)).click();
		Thread.sleep(2000);
		// ExtentTestManager.getTest().log(Status.INFO, "<b>Step-12:</b> Tap on \"Add
		// geometry\" (+) icon from bottom panel.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Add_new_geometry)).click();
		Thread.sleep(2000);
		// ExtentTestManager.getTest().log(Status.INFO, "<b>Step-13:</b> Tap on \"Save\"
		// icon from top panel of \"Action\" mode.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Save)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		// ExtentTestManager.getTest().log(Status.INFO, "<b>Step-14:</b> Tap on Camera
		// icon from \"Photo\" field.");
		TouchAction swipe = new TouchAction(driver).press(PointOption.point(13, 1553))
				.moveTo(PointOption.point(13, 225)).release().perform();
		Thread.sleep(2000);
		TouchAction swipe1 = new TouchAction(driver).press(PointOption.point(13, 1553))
				.moveTo(PointOption.point(13, 225)).release().perform();
		Thread.sleep(2000);
		TouchAction swipe2 = new TouchAction(driver).press(PointOption.point(13, 1553))
				.moveTo(PointOption.point(13, 225)).release().perform();
		Thread.sleep(2000);
		TouchAction swipe3 = new TouchAction(driver).press(PointOption.point(13, 1553))
				.moveTo(PointOption.point(13, 225)).release().perform();
		Thread.sleep(2000);
		TouchAction swipe4 = new TouchAction(driver).press(PointOption.point(13, 1553))
				.moveTo(PointOption.point(13, 225)).release().perform();
		Thread.sleep(2000);
		TouchAction swipe5 = new TouchAction(driver).press(PointOption.point(13, 1553))
				.moveTo(PointOption.point(13, 225)).release().perform();
		Thread.sleep(2000);
		TouchAction swipe6 = new TouchAction(driver).press(PointOption.point(13, 1553))
				.moveTo(PointOption.point(13, 225)).release().perform();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Camera)).click();
		Thread.sleep(2000);
		// ExtentTestManager.getTest().log(Status.INFO, "<b>Step-15:</b> Tap on \"Add
		// photo\" -> Pick photo from Gallery.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Pick_Photo)).click();
		Thread.sleep(2000);
		// ExtentTestManager.getTest().log(Status.INFO, "<b>Step-16:</b> Select photo
		// from Gallery.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Capture)).click();
		Thread.sleep(2000);
		// ExtentTestManager.getTest().log(Status.INFO, "<b>Step-17:</b> Tap on
		// \"Apply\" icon from \"Set attributes\" form.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Apply)).click();
		Thread.sleep(10000);
	}

	@Test(description = "To verify that user is able to reset interface to defaults.")
	public void SSQPad_47() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		// ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on Top panel
		// \"More option\" -> Setting.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_More_Option)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Settings)).click();
		Thread.sleep(2000);
		// ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on Top panel
		// \"More option\" -> Setting -> General.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_General)).click();
		Thread.sleep(2000);
		// ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Reset
		// to defaults\" from Interface setting.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Reset_Defaults)).click();
		Thread.sleep(2000);
		// ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on \"Ok\"
		// option from validation message.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		// ExtentTestManager.getTest().log(Status.INFO, "<b>Step-10</b> Tap on \"Back\"
		// icon from general setting section.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Back)).click();
		Thread.sleep(2000);
		// ExtentTestManager.getTest().log(Status.INFO, "<b>Step-11:</b> Tap on \"Back\"
		// icon from setting section.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Back)).click();
		Thread.sleep(4000);
		// ExtentTestManager.getTest().log(Status. INFO,"<b>Result:</b> User should not
		// be able to view assigned layers.");

		Assert.assertEquals(true, driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/parentPanel")).isDisplayed());
	}

	@Test(description = "To verify that all layers are not deleted by tapping on \"Cancel\" button from Confirm dialog box.")
	public void SSQPad_48() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		// ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on Top panel
		// \"More option\" -> Setting.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_More_Option)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Settings)).click();
		Thread.sleep(2000);
		// ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on Top panel
		// \"More option\" -> Setting -> General.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_General)).click();
		Thread.sleep(2000);
		// ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Reset
		// to defaults\" from Interface setting.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Reset_Defaults)).click();
		Thread.sleep(2000);
		// ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on
		// \"Cancel\" option from validation message.");
		driver.findElement(MobileBy.id("android:id/button2")).click();
		Thread.sleep(2000);

		// ExtentTestManager.getTest().log(Status. INFO,"<b>Result:</b> \"Confirm?\"
		// dialog should get closed.");

		Assert.assertEquals(true,
				driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Reset_Defaults)).isDisplayed());
	}

	@Test(description = "To verify that user is able to hide true north on compass.")
	public void SSQPad_49() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		// ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on Top panel
		// \"More option\" -> Setting.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_More_Option)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Settings)).click();
		Thread.sleep(2000);
		// ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on Top panel
		// \"More option\" -> Setting -> General.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_General)).click();
		Thread.sleep(2000);
		// ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap to deselect
		// checkbox of \"Show true north\".");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.chk_Show_true_north)).click();
		Thread.sleep(2000);
		// ExtentTestManager.getTest().log(Status. INFO,"<b>Result:</b> User should be
		// able to deselect checkbox of \"Show true north\".");
		Assert.assertEquals("false", driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.chk_Show_true_north))
				.getAttribute("checked"));
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Back)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Back)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Campass)).click();
		Thread.sleep(2000);
	}

	@Test(description = "To verify that user is able to show magnetic on compass.")
	public void SSQPad_50() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		// ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on Top panel
		// \"More option\" -> Setting.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_More_Option)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Settings)).click();
		Thread.sleep(2000);
		// ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on Top panel
		// \"More option\" -> Setting -> General.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_General)).click();
		Thread.sleep(2000);
		// ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap to select
		// checkbox of \"Show magnetic\".");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.chk_Show_magnetic)).click();
		Thread.sleep(2000);
		// ExtentTestManager.getTest().log(Status. INFO,"<b>Result:</b> User should be
		// able to select checkbox of \"Show magnetic\".");
		Assert.assertEquals("true", driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.chk_Show_magnetic))
				.getAttribute("checked"));
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Back)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Back)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Campass)).click();
		Thread.sleep(2000);
	}

	@Test(description = "To verify that user is able to turn off \"Vibrate on bezel touch\" functionality from compass.")
	public void SSQPad_51() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		// ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on Top panel
		// \"More option\" -> Setting.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_More_Option)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Settings)).click();
		Thread.sleep(2000);
		// ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on Top panel
		// \"More option\" -> Setting -> General.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_General)).click();
		Thread.sleep(2000);
		// ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap to deselect
		// checkbox of \"Vibrate on bezel touch\".");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.chk_Vibrate_on_bezel_touch)).click();
		Thread.sleep(2000);
		// ExtentTestManager.getTest().log(Status. INFO,"<b>Result:</b> User should be
		// able to deselect checkbox of \"Vibrate on bezel touch\".");
		Assert.assertEquals("false",
				driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.chk_Vibrate_on_bezel_touch))
						.getAttribute("checked"));
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Back)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Back)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Campass)).click();
		Thread.sleep(2000);
	}

	@Test(description = "To verify that user is able to keep compass screen on.")
	public void SSQPad_52() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		// ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on Top panel
		// \"More option\" -> Setting.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_More_Option)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Settings)).click();
		Thread.sleep(2000);
		// ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on Top panel
		// \"More option\" -> Setting -> General.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_General)).click();
		Thread.sleep(2000);
		// ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap to deselect
		// checkbox of \"Keep compass screen on\".");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.chk_Keep_compass_Screen)).click();
		Thread.sleep(2000);
		// ExtentTestManager.getTest().log(Status. INFO,"<b>Result:</b> User should be
		// able to deselect checkbox of \"Keep compass screen on\".");
		Assert.assertEquals("false",
				driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.chk_Keep_compass_Screen))
						.getAttribute("checked"));
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Back)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Back)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Campass)).click();
		Thread.sleep(2000);
	}

	@Test(description = "To verify that user is able to set size for attaching media files between 25 to 100 mb.")
	public void SSQPad_53() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		// ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on Top panel
		// \"More option\" -> Setting.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_More_Option)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Settings)).click();
		Thread.sleep(2000);
		// ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on Top panel
		// \"More option\" -> Setting -> General.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_General)).click();
		Thread.sleep(2000);
		// ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Media
		// file max size (in MB)\" field.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.label_Media_Attachment)).click();
		Thread.sleep(2000);
		// ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Enter value
		// between 25 to 100 at text bar.");
		driver.findElement(MobileBy.id("android:id/edit")).clear();
		driver.findElement(MobileBy.id("android:id/edit")).sendKeys("50");
		Thread.sleep(2000);
		// ExtentTestManager.getTest().log(Status.INFO, "<b>Step-10:</b> Tap on \"Ok\"
		// button from \"Media file max size (in MB)\" dialog.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		// ExtentTestManager.getTest().log(Status. INFO,"<b>Result:</b> User should be
		// able to enter value between 25 to 100 at text bar.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.label_Media_Attachment)).click();
		Thread.sleep(2000);
		Assert.assertEquals("50", driver.findElement(MobileBy.id("android:id/edit")).getAttribute("text"));
	}

	@Test(description = "To verify that \"Media file max size\" is not changed by tapping on \"Cancel\" button from dialog box.")
	public void SSQPad_54() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		// ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on Top panel
		// \"More option\" -> Setting.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_More_Option)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Settings)).click();
		Thread.sleep(2000);
		// ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on Top panel
		// \"More option\" -> Setting -> General.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_General)).click();
		Thread.sleep(2000);
		// ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Media
		// file max size (in MB)\" field.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.label_Media_Attachment)).click();
		Thread.sleep(2000);
		// ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Enter value
		// between 25 to 100 at text bar.");
		driver.findElement(MobileBy.id("android:id/edit")).sendKeys("50");
		Thread.sleep(2000);
		// ExtentTestManager.getTest().log(Status.INFO, "<b>Step-10:</b> Tap on
		// \"Cancel\" button from \"Media file max size (in MB)\" dialog.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Cancel)).click();
		Thread.sleep(2000);
		// ExtentTestManager.getTest().log(Status. INFO,"<b>Result:</b> Media file max
		// size should not changed.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.label_Media_Attachment)).click();
		Thread.sleep(2000);
		Assert.assertEquals("25", driver.findElement(MobileBy.id("android:id/edit")).getAttribute("text"));
	}

	@Test(description = "To verify that user is able to view map settings section.")
	public void SSQPad_55() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		// ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on Top panel
		// \"More option\" -> Setting.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_More_Option)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Settings)).click();
		Thread.sleep(2000);
		// ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on Top panel
		// \"More option\" -> Setting -> Map.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Map)).click();
		Thread.sleep(2000);
		/*
		 * ExtentTestManager.getTest().log(Status.
		 * INFO,"<b>Result:</b> User should be able to view Map Setting page with following settings,\r\n"
		 * + "Show status info panel\r\n" + "Show current location\r\n" +
		 * "Show mini compass\r\n" + "Keep map screen on\r\n" + "Show zoom controls\r\n"
		 * + "Measurement system\r\n" + "Default zoom level\r\n" +
		 * "Show scale ruler\r\n" + "Show zoom level\r\n" + "Coordination format\r\n" +
		 * "Decimal places\r\n" + "Map background\r\n" + "Map path");
		 */
		Assert.assertEquals(true,
				driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Show_status_info_Panel)).isDisplayed());
		Assert.assertEquals(true,
				driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Show_Current_Location)).isDisplayed());
		Assert.assertEquals(true,
				driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.chk_Show_Mini_Compass)).isDisplayed());
		Assert.assertEquals(true,
				driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.chk_Keep_Map_Screen_On)).isDisplayed());
		Assert.assertEquals(true,
				driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.chk_Zoom_Level_Control)).isDisplayed());
		Assert.assertEquals(true,
				driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Measurement_System)).isDisplayed());
		Assert.assertEquals(true,
				driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Default_Zoom_Level)).isDisplayed());
		Assert.assertEquals(true,
				driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.chk_Show_scale_ruler)).isDisplayed());
		Assert.assertEquals(true,
				driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.chk_Show_zoom_Level)).isDisplayed());
		Assert.assertEquals(true,
				driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Coordinates_Format)).isDisplayed());
		TouchAction swipe = new TouchAction(driver).longPress(PointOption.point(252, 2512))
				.moveTo(PointOption.point(252, 1882)).release().perform();
		Thread.sleep(2000);

		Assert.assertEquals(true,
				driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Decimal_places)).isDisplayed());
		Assert.assertEquals(true,
				driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Map_background)).isDisplayed());
	}

	@Test(description = "To verify that user is able to view status info panel.")
	public void SSQPad_56() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		// ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on Top panel
		// \"More option\" -> Setting.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_More_Option)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Settings)).click();
		Thread.sleep(2000);
		// ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on Top panel
		// \"More option\" -> Setting -> Map.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Map)).click();
		Thread.sleep(2000);
		// ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Show
		// status info panel\".");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Show_status_info_Panel)).click();
		Thread.sleep(2000);
		// ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap to select
		// \"Always show\".");
		driver.findElement(MobileBy.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.F"
						+ "rameLayout/android.widget.FrameLayout/androidx.appcompat.widget.LinearLayoutCompat/android.widget.FrameLayout/android.widget.ListVi"
						+ "ew/android.widget.CheckedTextView[3]"))
				.click();
		Thread.sleep(2000);
		// ExtentTestManager.getTest().log(Status.INFO, "<b>Step-10</b> Tap on \"Back\"
		// icon from general setting section.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Back)).click();
		Thread.sleep(2000);
		// ExtentTestManager.getTest().log(Status.INFO, "<b>Step-11:</b> Tap on \"Back\"
		// icon from setting section.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Back)).click();
		Thread.sleep(4000);

		/*
		 * ExtentTestManager.getTest().log(Status.
		 * INFO,"<b>Result:</b> 1) User should get \"Home\" page SSQPad application.\r\n"
		 * +
		 * "2) User should be able to view Status info panel at bottom panel with following information,\r\n"
		 * + "Latitude and Longitude for current location\r\n" + "Location accuracy\r\n"
		 * + "Wi-Fi connectivity band\r\n" + "Speedometer\r\n" + "Elevation meter");
		 */

		Assert.assertEquals(true, driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/tv_latitude")).isDisplayed());
		Assert.assertEquals(true, driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/tv_longitude")).isDisplayed());
		Assert.assertEquals(true, driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/tv_source")).isDisplayed());
		Assert.assertEquals(true, driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/tv_accuracy")).isDisplayed());
		Assert.assertEquals(true, driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/tv_altitude")).isDisplayed());
		Assert.assertEquals(true, driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/tv_speed")).isDisplayed());

	}

	@Test(description = "To verify that user is able to hide status info panel.")
	public void SSQPad_57() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		String aa = driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Edit_Tool)).getAttribute("bounds");
		System.out.println(aa);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on Top panel \"More option\" -> Setting.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_More_Option)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Settings)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-7:</b> Tap on Top panel \"More option\" -> Setting -> Map.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Map)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Show status info panel\".");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Show_status_info_Panel)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap to select \"Do not show info panel\".");
		driver.findElement(MobileBy.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.F"
						+ "rameLayout/android.widget.FrameLayout/androidx.appcompat.widget.LinearLayoutCompat/android.widget.FrameLayout/android.widget.ListVi"
						+ "ew/android.widget.CheckedTextView[1]"))
				.click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-10</b> Tap on \"Back\" icon from general setting section.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Back)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-11:</b> Tap on \"Back\" icon from setting section.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Back)).click();
		Thread.sleep(4000);
		String ab = driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Edit_Tool)).getAttribute("bounds");
		System.out.println(ab);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> Status info panel from bottom panel should get hidden.");
		Assert.assertEquals(aa, ab);

	}

	@Test(description = "To verify that user is able to view status info panel while edit mode is on.")
	public void SSQPad_58() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on Top panel \"More option\" -> Setting.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_More_Option)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Settings)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-7:</b> Tap on Top panel \"More option\" -> Setting -> Map.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Map)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Show status info panel\".");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Show_status_info_Panel)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap to select \"Show out edit mode\".");
		driver.findElement(MobileBy.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.F"
						+ "rameLayout/android.widget.FrameLayout/androidx.appcompat.widget.LinearLayoutCompat/android.widget.FrameLayout/android.widget.ListVi"
						+ "ew/android.widget.CheckedTextView[2]"))
				.click();
		Thread.sleep(2000);
		Assert.assertEquals("Show out edit mode", driver.findElement(MobileBy.xpath("	\r\n"
				+ "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget."
				+ "FrameLayout/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget."
				+ "FrameLayout/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[1]/android.widget.RelativeLayout/android.widget."
				+ "TextView[2]")).getAttribute("text"));
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-10</b> Tap on \"Back\" icon from general setting section.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Back)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-11:</b> Tap on \"Back\" icon from setting section.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Back)).click();
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> User should be able to view status info panel with \"Edit\" panel at bottom panel.");
		Assert.assertEquals(true, driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/tv_latitude")).isDisplayed());
		Assert.assertEquals(true, driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/tv_longitude")).isDisplayed());
		Assert.assertEquals(true, driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/tv_source")).isDisplayed());
		Assert.assertEquals(true, driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/tv_accuracy")).isDisplayed());
		Assert.assertEquals(true, driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/tv_altitude")).isDisplayed());
		Assert.assertEquals(true, driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/tv_speed")).isDisplayed());

	}

	@Test(description = "To verify that current location setting is set to \"Show marker and accuracy radius\" by default.")
	public void SSQPad_59() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on Top panel \"More option\" -> Setting.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_More_Option)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Settings)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-7:</b> Tap on Top panel \"More option\" -> Setting -> Map.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Map)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Show current location\".");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Show_Current_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> User should get \"Show current location\" dialog open with \"Show marker and accuracy radius\" option selected by default.");
		Assert.assertEquals("true", driver.findElement(
				MobileBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget."
						+ "FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.appcompat.widget.LinearLayoutCompat/android.widget."
						+ "FrameLayout/android.widget.ListView/android.widget.CheckedTextView[3]"))
				.getAttribute("checked"));

	}

	@Test(description = "To verify that user is able to hide current location marker from map.")
	public void SSQPad_60() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on Top panel \"More option\" -> Setting.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_More_Option)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Settings)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-7:</b> Tap on Top panel \"More option\" -> Setting -> Map.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Map)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Show current location\".");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Show_Current_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap to select \"Do not show current location\".");
		driver.findElement(MobileBy.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.F"
						+ "rameLayout/android.widget.FrameLayout/androidx.appcompat.widget.LinearLayoutCompat/android.widget.FrameLayout/android.widget.ListVi"
						+ "ew/android.widget.CheckedTextView[1]"))
				.click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> User should not be able to view current location marker on map.");
		Assert.assertEquals("Do not show current location", driver.findElement(
				MobileBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget."
						+ "FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.RelativeLayout/android."
						+ "widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.recyclerview.widget.RecyclerView/android.widget."
						+ "LinearLayout[2]/android.widget.RelativeLayout/android.widget.TextView[2]"))
				.getAttribute("text"));
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Back)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Back)).click();
		Thread.sleep(2000);

	}

	@Test(description = "To verify that user is able to show marker only on map.")
	public void SSQPad_61() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on Top panel \"More option\" -> Setting.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_More_Option)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Settings)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-7:</b> Tap on Top panel \"More option\" -> Setting -> Map.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Map)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"show marker only\".");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Show_Current_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap to select \"Do not show current location\".");
		driver.findElement(MobileBy.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.F"
						+ "rameLayout/android.widget.FrameLayout/androidx.appcompat.widget.LinearLayoutCompat/android.widget.FrameLayout/android.widget.ListVi"
						+ "ew/android.widget.CheckedTextView[2]"))
				.click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> User should be able to view current location marker on map without accuracy radius.");
		Assert.assertEquals("Show marker only",
				driver.findElement(MobileBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/"
						+ "android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget."
						+ "RelativeLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.recyclerview.widget."
						+ "RecyclerView/android.widget.LinearLayout[2]/android.widget.RelativeLayout/android.widget.TextView[2]"))
						.getAttribute("text"));
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Back)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Back)).click();
		Thread.sleep(2000);

	}

	@Test(description = "To verify that user is able to hide mini compass from map page.")
	public void SSQPad_62() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on Top panel \"More option\" -> Setting.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_More_Option)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Settings)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-7:</b> Tap on Top panel \"More option\" -> Setting -> Map.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Map)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-8:</b> Tap to deselect checkbox of \"Show mini compass\".");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.chk_Show_Mini_Compass)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> Mini compass icon from map screen should not be visible.");
		Assert.assertEquals("false", driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.chk_Show_Mini_Compass))
				.getAttribute("checked"));
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Back)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Back)).click();
		Thread.sleep(2000);

	}

	@Test(description = "To verify that user is able to hide \"Zoom controls\" from map.")
	public void SSQPad_63() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on Top panel \"More option\" -> Setting.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_More_Option)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Settings)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-7:</b> Tap on Top panel \"More option\" -> Setting -> Map.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Map)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-8:</b> Tap to deselect checkbox of \"Show Zoom controls\".");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.chk_Zoom_Level_Control)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> Zoom controls from map screen should not be visible.");
		Assert.assertEquals("false", driver
				.findElement(MobileBy.xpath(SurveySolution_Testcase_R.chk_Zoom_Level_Control)).getAttribute("checked"));
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Back)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Back)).click();
		Thread.sleep(2000);

	}

	@Test(description = "To verify that user is able to keep map screen on.")
	public void SSQPad_64() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on Top panel \"More option\" -> Setting.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_More_Option)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Settings)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-7:</b> Tap on Top panel \"More option\" -> Setting -> Map.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Map)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-8:</b> Tap to deselect checkbox of \"Keep map screen on\".");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.chk_Keep_Map_Screen_On)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> Map screen should not off by settled screen off timer of device.");
		Assert.assertEquals("true", driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.chk_Zoom_Level_Control))
				.getAttribute("checked"));
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Back)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Back)).click();
		Thread.sleep(2000);

	}

	@Test(description = "To verify that user is able to change measurement system.")
	public void SSQPad_65() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on Top panel \"More option\" -> Setting.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_More_Option)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Settings)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-7:</b> Tap on Top panel \"More option\" -> Setting -> Map.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Map)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Measurement system\".");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Measurement_System)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap to select required measurement system.");
		driver.findElement(MobileBy.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.F"
						+ "rameLayout/android.widget.FrameLayout/androidx.appcompat.widget.LinearLayoutCompat/android.widget.FrameLayout/android.widget.ListVi"
						+ "ew/android.widget.CheckedTextView[2]"))
				.click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-10</b> Tap on \"Back\" icon from general setting section.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Back)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-11:</b> Tap on \"Back\" icon from setting section.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Back)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-12:</b> Tap on \"Floating Action (+) \" button from home page.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Edit_Tool)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-13:</b> Tap on \"Measure\" icon.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Mesaure)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-14:</b> Tap on map to activate measure functionality.");
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-15:</b> Tap on second location to get length between two selected points.");
		TouchAction swipe = new TouchAction(driver).press(PointOption.point(250, 450)).release().perform();
		Thread.sleep(2000);
		TouchAction swipe1 = new TouchAction(driver).press(PointOption.point(350, 800)).release().perform();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> 1) User should get the polygon drawn within selected points.\r\n"
						+ "2) User should be able to view area in selected measurement unit within drawn polygon at top panel");
		String aa = driver.findElement(MobileBy.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.TextView\r\n"
						+ ""))
				.getAttribute("text");
		System.out.println(aa);
		Assert.assertEquals(true, aa.contains("ft"));

	}

	@Test(description = "To verify that Default zoom level is 17.")
	public void SSQPad_66() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on Top panel \"More option\" -> Setting.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_More_Option)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Settings)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-7:</b> Tap on Top panel \"More option\" -> Setting -> Map.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Map)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Default zoom level\".");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Default_Zoom_Level)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> User should get \"Default zoom level\" list with \"17\" value selected by default.");
		String aa = driver.findElement(MobileBy.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.appcompat.widget.LinearLayoutCompat/android.widget.FrameLayout/android.widget.ListView/android.widget.CheckedTextView[8]"))
				.getAttribute("text");
		System.out.println(aa);
		Assert.assertEquals("true", driver.findElement(MobileBy.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.appcompat.widget.LinearLayoutCompat/android.widget.FrameLayout/android.widget.ListView/android.widget.CheckedTextView[8]"))
				.getAttribute("checked"));

	}

	@Test(description = "To verify that user is able to change default zoom level.")
	public void SSQPad_67() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on Top panel \"More option\" -> Setting.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_More_Option)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Settings)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-7:</b> Tap on Top panel \"More option\" -> Setting -> Map.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Map)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Default zoom level\".");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Default_Zoom_Level)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-9:</b> Tap to select required value for default zoom level from \"Default zoom level\" list.");
		driver.findElement(MobileBy.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.appcompat.widget.LinearLayoutCompat/android.widget.FrameLayout/android.widget.ListView/android.widget.CheckedTextView[3]"))
				.click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-10:</b> Tap on \"Locate\" icon from top panel.");
		driver.closeApp();
		Thread.sleep(2000);
		driver.activateApp("com.IGiS.QPadSS");
		Thread.sleep(10000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> User should get \"Default zoom level\" list with \"17\" value selected by default.");
		Assert.assertEquals("10.0 km",
				driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/tv_ruler")).getAttribute("text"));

	}

	@Test(description = "To verify that user is able to hide scale ruler on map.")
	public void SSQPad_68() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on Top panel \"More option\" -> Setting.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_More_Option)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Settings)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-7:</b> Tap on Top panel \"More option\" -> Setting -> Map.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Map)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-8:</b> Tap to deselect checkbox of \"Show scale ruler\".");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.chk_Show_scale_ruler)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Result:</b>Scale ruler should not visible to user.");
		Assert.assertEquals("false", driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.chk_Show_scale_ruler))
				.getAttribute("checked"));
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Back)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Back)).click();
		Thread.sleep(2000);

	}

	@Test(description = "To verify that user is able to show scale ruler on map.")
	public void SSQPad_69() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on Top panel \"More option\" -> Setting.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_More_Option)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Settings)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-7:</b> Tap on Top panel \"More option\" -> Setting -> Map.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Map)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap to select checkbox of \"Show scale ruler\".");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.chk_Show_scale_ruler)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.chk_Show_scale_ruler)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Result:</b>Scale ruler should visible to user.");
		Assert.assertEquals("true", driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.chk_Show_scale_ruler))
				.getAttribute("checked"));
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Back)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Back)).click();
		Thread.sleep(2000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/iv_ruler")).isDisplayed());

	}

	@Test(description = "To verify that user is able to show Zoom level on map.")
	public void SSQPad_70() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on Top panel \"More option\" -> Setting.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_More_Option)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Settings)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-7:</b> Tap on Top panel \"More option\" -> Setting -> Map.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Map)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap to select checkbox of \"Show scale ruler\".");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.chk_Show_zoom_Level)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Result:</b> Zoom level should be visible to user.");
		Assert.assertEquals("true", driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.chk_Show_zoom_Level))
				.getAttribute("checked"));
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Back)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Back)).click();
		Thread.sleep(2000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/tv_zoom_level")).isDisplayed());

	}

	@Test(description = "To verify that user is able to change coordination format for status info panel.")
	public void SSQPad_71() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on Top panel \"More option\" -> Setting.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_More_Option)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Settings)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-7:</b> Tap on Top panel \"More option\" -> Setting -> Map.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Map)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Coordinates format\".");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Coordinates_Format)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap to select required coordinates format.");
		driver.findElement(MobileBy.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget."
						+ "FrameLayout/android.widget.FrameLayout/androidx.appcompat.widget.LinearLayoutCompat/android.widget.FrameLayout/android.widget.ListView"
						+ "/android.widget.CheckedTextView[2]"))
				.click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-10:</b> Tap on \"Show status info panel\".");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Show_status_info_Panel)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-11:</b> Tap to select \"Always show\".");
		driver.findElement(MobileBy.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.F"
						+ "rameLayout/android.widget.FrameLayout/androidx.appcompat.widget.LinearLayoutCompat/android.widget.FrameLayout/android.widget.ListVi"
						+ "ew/android.widget.CheckedTextView[3]"))
				.click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> User should be able to view Status info panel at bottom panel with selected coordinates format of Coordinates.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Back)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Back)).click();
		Thread.sleep(2000);
		Assert.assertEquals("37°25.32' Lat",
				driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/tv_latitude")).getAttribute("text"));

	}

	@Test(description = "To verify that user is able to change decimal places for viewing coordinates.")
	public void SSQPad_72() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on Top panel \"More option\" -> Setting.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_More_Option)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Settings)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-7:</b> Tap on Top panel \"More option\" -> Setting -> Map.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Map)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Show status info panel\".");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Show_status_info_Panel)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap to select \"Always show\".");
		driver.findElement(MobileBy.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.F"
						+ "rameLayout/android.widget.FrameLayout/androidx.appcompat.widget.LinearLayoutCompat/android.widget.FrameLayout/android.widget.ListVi"
						+ "ew/android.widget.CheckedTextView[3]"))
				.click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-10:</b> Tap on \"Decimal places\".");
		TouchAction swipe = new TouchAction(driver).longPress(PointOption.point(252, 2512))
				.moveTo(PointOption.point(252, 1882)).release().perform();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Decimal_places)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-11:</b> Enter required decimal digits between 2 to 8 at Decimal places text bar.");
		driver.findElement(MobileBy.id("android:id/edit")).clear();
		driver.findElement(MobileBy.id("android:id/edit")).sendKeys("5");
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> User should be able to view Status info panel at bottom panel with required coordinates decimal digits..");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Back)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Back)).click();
		Thread.sleep(2000);
		Assert.assertEquals("37.42200° Lat",
				driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/tv_latitude")).getAttribute("text"));

	}

	@Test(description = "To verify that user is able to change map background.")
	public void SSQPad_73() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on Top panel \"More option\" -> Setting.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_More_Option)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Settings)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-7:</b> Tap on Top panel \"More option\" -> Setting -> Map.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Map)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Map background\".");
		TouchAction swipe = new TouchAction(driver).longPress(PointOption.point(252, 2512))
				.moveTo(PointOption.point(252, 1882)).release().perform();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Map_background)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Select any option from list.");
		driver.findElement(MobileBy.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.F"
						+ "rameLayout/android.widget.FrameLayout/androidx.appcompat.widget.LinearLayoutCompat/android.widget.FrameLayout/android.widget.ListVi"
						+ "ew/android.widget.CheckedTextView[3]"))
				.click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> User should be able to view selected map background.");
		Assert.assertEquals("Dark", driver.findElement(MobileBy.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[11]/android.widget.RelativeLayout/android.widget.TextView[2]"))
				.getAttribute("text"));
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Back)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Back)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Zoom_Out)).click();
		Thread.sleep(2000);

	}

	@Test(description = "To verify that user is able to view Location Setting section.")
	public void SSQPad_75() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on Top panel \"More option\" -> Setting.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_More_Option)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Settings)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-7:</b> Tap on Top panel \"More option\" -> Setting -> Location.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> User should be able to view Location Setting page with following settings,\r\n"
						+ "Location accuracy\r\n" + "Minimum update time\r\n" + "Minimum update distance\r\n"
						+ "Count of GPS measure");
		Assert.assertEquals(true,
				driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Location_Accuracy)).isDisplayed());
		Assert.assertEquals(true,
				driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Minimum_Update_Time)).isDisplayed());
		Assert.assertEquals(true, driver
				.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Minimum_Update_Distance)).isDisplayed());
		Assert.assertEquals(true,
				driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Count_Of_GPS_m)).isDisplayed());

	}

	@Test(description = "To verify that user is able to set location accuracy to \"GPS\".")
	public void SSQPad_76() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on Top panel \"More option\" -> Setting.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_More_Option)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Settings)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-7:</b> Tap on Top panel \"More option\" -> Setting -> Location.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Location accuracy\" setting.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Location_Accuracy)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap to select \"GPS\" option.");
		driver.findElement(MobileBy.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.appcompat.widget.LinearLayoutCompat/android.widget.FrameLayout/android.widget.ListView/android.widget.CheckedTextView[1]"))
				.click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> 1) User should be able to select \"GPS\" as Location accuracy setting.\r\n"
						+ "2) \"Location accuracy\" dialog should get closed.");
		Assert.assertEquals("GPS", driver.findElement(MobileBy.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[1]/android.widget.RelativeLayout/android.widget.TextView[2]"))
				.getAttribute("text"));

	}

	@Test(description = "To verify that user is able to set location accuracy to \"Other networks\".")
	public void SSQPad_77() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on Top panel \"More option\" -> Setting.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_More_Option)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Settings)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-7:</b> Tap on Top panel \"More option\" -> Setting -> Location.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Location accuracy\" setting.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Location_Accuracy)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap to select \"Other networks\" option.");
		driver.findElement(MobileBy.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.appcompat.widget.LinearLayoutCompat/android.widget.FrameLayout/android.widget.ListView/android.widget.CheckedTextView[2]"))
				.click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> 1) User should be able to select \"Other networks\" as Location accuracy setting.\r\n"
						+ "2) \"Location accuracy\" dialog should get closed.");
		Assert.assertEquals("Other networks", driver.findElement(MobileBy.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[1]/android.widget.RelativeLayout/android.widget.TextView[2]"))
				.getAttribute("text"));

	}

	@Test(description = "To verify that user is able to set location accuracy to \"GPS & Other networks\".")
	public void SSQPad_78() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on Top panel \"More option\" -> Setting.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_More_Option)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Settings)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-7:</b> Tap on Top panel \"More option\" -> Setting -> Location.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Location accuracy\" setting.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Location_Accuracy)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap to select \"GPS & Other networks\" option.");
		driver.findElement(MobileBy.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.appcompat.widget.LinearLayoutCompat/android.widget.FrameLayout/android.widget.ListView/android.widget.CheckedTextView[3]"))
				.click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> 1) User should be able to select \"Other networks\" as Location accuracy setting.\r\n"
						+ "2) \"Location accuracy\" dialog should get closed.");
		Assert.assertEquals("GPS & Other networks", driver.findElement(MobileBy.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[1]/android.widget.RelativeLayout/android.widget.TextView[2]"))
				.getAttribute("text"));

	}

	@Test(description = "To verify that user is able to set minimum update time of location.")
	public void SSQPad_79() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on Top panel \"More option\" -> Setting.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_More_Option)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Settings)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-7:</b> Tap on Top panel \"More option\" -> Setting -> Location.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"minimum update time\" setting.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Minimum_Update_Time)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-9:</b> Tap to select required value for Location update time.");
		driver.findElement(MobileBy.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.appcompat.widget.LinearLayoutCompat/android.widget.FrameLayout/android.widget.ListView/android.widget.CheckedTextView[6]"))
				.click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> User should be able to select required minimum time for updating location.");
		Assert.assertEquals("45 sec", driver.findElement(MobileBy.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[2]/android.widget.RelativeLayout/android.widget.TextView[2]"))
				.getAttribute("text"));

	}

	@Test(description = "To verify that user is able to set minimum update distance of location.")
	public void SSQPad_80() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on Top panel \"More option\" -> Setting.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_More_Option)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Settings)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-7:</b> Tap on Top panel \"More option\" -> Setting -> Location.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"minimum update distance\" setting.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Minimum_Update_Distance)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-9:</b> Tap to select required value for distance update.");
		driver.findElement(MobileBy.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.appcompat.widget.LinearLayoutCompat/android.widget.FrameLayout/android.widget.ListView/android.widget.CheckedTextView[6]"))
				.click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> User should be able to select required minimum distance for updating location.");
		Assert.assertEquals("50 m", driver.findElement(MobileBy.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[3]/android.widget.RelativeLayout/android.widget.TextView[2]"))
				.getAttribute("text"));

	}

	@Test(description = "To verify that user is able to set count of GPS measure of location.")
	public void SSQPad_81() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on Top panel \"More option\" -> Setting.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_More_Option)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Settings)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-7:</b> Tap on Top panel \"More option\" -> Setting -> Location.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Count of GPS measures\" setting.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Count_Of_GPS_m)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-9:</b> Enter required count for measuring GPS at \"Count of GPS measure\" text bar.");
		driver.findElement(MobileBy.id("android:id/edit")).clear();
		driver.findElement(MobileBy.id("android:id/edit")).sendKeys("5434");
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> User should be able to enter required count for measuring GPS at \"Count of GPS measure\" text bar.");
		Assert.assertEquals("5434", driver.findElement(MobileBy.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[4]/android.widget.RelativeLayout/android.widget.TextView[2]"))
				.getAttribute("text"));

	}

	@Test(description = "To verify that user is able to view Tracks Setting section.")
	public void SSQPad_82() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on Top panel \"More option\" -> Setting.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_More_Option)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Settings)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-7:</b> Tap on Top panel \"More option\" -> Setting -> My tracks.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_My_Tracks)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> User should be able to view My tracking Setting page with following settings,\r\n"
						+ "Location accuracy\r\n" + "Minimum update time\r\n" + "Minimum update distance\r\n"
						+ "Count of GPS measure");
		Assert.assertEquals(true,
				driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Location_Accuracy)).isDisplayed());
		Assert.assertEquals(true,
				driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Minimum_Update_Time)).isDisplayed());
		Assert.assertEquals(true, driver
				.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Minimum_Update_Distance)).isDisplayed());
		Assert.assertEquals(true,
				driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.chk_Restore_Current_TAR)).isDisplayed());

	}

	@Test(description = "To verify that user is able to set location accuracy to \"GPS\" for My tracks.")
	public void SSQPad_83() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on Top panel \"More option\" -> Setting.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_More_Option)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Settings)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-7:</b> Tap on Top panel \"More option\" -> Setting -> My tracks.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_My_Tracks)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Location accuracy\" setting.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Location_Accuracy)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap to select \"GPS\" option.");
		driver.findElement(MobileBy.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.appcompat.widget.LinearLayoutCompat/android.widget.FrameLayout/android.widget.ListView/android.widget.CheckedTextView[1]"))
				.click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> 1) User should be able to select \"GPS\" as Location accuracy setting.\r\n"
						+ "2) \"Location accuracy\" dialog should get closed.");
		Assert.assertEquals("GPS", driver.findElement(MobileBy.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[1]/android.widget.RelativeLayout/android.widget.TextView[2]"))
				.getAttribute("text"));

	}

	@Test(description = "To verify that user is able to set location accuracy to \"Other networks\" for My tracks.")
	public void SSQPad_84() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on Top panel \"More option\" -> Setting.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_More_Option)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Settings)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-7:</b> Tap on Top panel \"More option\" -> Setting -> My tracks.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_My_Tracks)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Location accuracy\" setting.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Location_Accuracy)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap to select \"Other networks\" option.");
		driver.findElement(MobileBy.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.appcompat.widget.LinearLayoutCompat/android.widget.FrameLayout/android.widget.ListView/android.widget.CheckedTextView[2]"))
				.click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> 1) User should be able to select \"Other networks\" as Location accuracy setting.\r\n"
						+ "2) \"Location accuracy\" dialog should get closed.");
		Assert.assertEquals("Other networks", driver.findElement(MobileBy.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[1]/android.widget.RelativeLayout/android.widget.TextView[2]"))
				.getAttribute("text"));

	}

	@Test(description = "To verify that user is able to set location accuracy to \"GPS & Other networks\" for My tracks.")
	public void SSQPad_85() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on Top panel \"More option\" -> Setting.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_More_Option)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Settings)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-7:</b> Tap on Top panel \"More option\" -> Setting -> My tracks.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_My_Tracks)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Location accuracy\" setting.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Location_Accuracy)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap to select \"GPS & Other networks\" option.");
		driver.findElement(MobileBy.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.appcompat.widget.LinearLayoutCompat/android.widget.FrameLayout/android.widget.ListView/android.widget.CheckedTextView[3]"))
				.click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> 1) User should be able to select \"Other networks\" as Location accuracy setting.\r\n"
						+ "2) \"Location accuracy\" dialog should get closed.");
		Assert.assertEquals("GPS & Other networks", driver.findElement(MobileBy.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[1]/android.widget.RelativeLayout/android.widget.TextView[2]"))
				.getAttribute("text"));

	}

	@Test(description = "To verify that user is able to set minimum update time of location for My tracks.")
	public void SSQPad_86() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on Top panel \"More option\" -> Setting.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_More_Option)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Settings)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-7:</b> Tap on Top panel \"More option\" -> Setting -> My tracks.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_My_Tracks)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"minimum update time\" setting.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Minimum_Update_Time)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-9:</b> Tap to select required value for Location update time.");
		driver.findElement(MobileBy.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.appcompat.widget.LinearLayoutCompat/android.widget.FrameLayout/android.widget.ListView/android.widget.CheckedTextView[6]"))
				.click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> User should be able to select required minimum time for updating location.");
		Assert.assertEquals("45 sec", driver.findElement(MobileBy.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[2]/android.widget.RelativeLayout/android.widget.TextView[2]"))
				.getAttribute("text"));

	}

	@Test(description = "To verify that user is able to set minimum update distance of location for My tracks.")
	public void SSQPad_87() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on Top panel \"More option\" -> Setting.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_More_Option)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Settings)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-7:</b> Tap on Top panel \"More option\" -> Setting -> My tracks.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_My_Tracks)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"minimum update distance\" setting.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Minimum_Update_Distance)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-9:</b> Tap to select required value for distance update.");
		driver.findElement(MobileBy.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.appcompat.widget.LinearLayoutCompat/android.widget.FrameLayout/android.widget.ListView/android.widget.CheckedTextView[6]"))
				.click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> User should be able to select required minimum distance for updating location.");
		Assert.assertEquals("50 m", driver.findElement(MobileBy.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[3]/android.widget.RelativeLayout/android.widget.TextView[2]"))
				.getAttribute("text"));

	}

	@Test(description = "To verify that user is able to restore current track after reboot.")
	public void SSQPad_88() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on Top panel \"More option\" -> Setting.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_More_Option)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Settings)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-7:</b> Tap on Top panel \"More option\" -> Setting -> My tracks.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_My_Tracks)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-8:</b> Tap to select \"Restore current track after reboot\" checkbox.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.chk_Restore_Current_TAR)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-9:</b> Tap on Top panel \"More option\" -> Start new Track.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Back)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Back)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_More_Option)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Start_New_Track)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-10:</b> Reboot Device.");
		driver.closeApp();
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-11:</b> Restart SSQPad mobile application.");
		driver.activateApp("com.IGiS.QPadSS");
		Thread.sleep(10000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> 1) User should get \"Home\" page SSQPad application.\r\n"
						+ "2) User should be able to view that track functionality resumes recording track.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_More_Option)).click();
		Thread.sleep(2000);
		Assert.assertEquals("Stop track",
				driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Start_New_Track)).getAttribute("text"));

	}

	@Test(description = "To verify that user is able to view mini compass expand.")
	public void SSQPad_89() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on Mini compass icon.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Campass)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Result:</b>User should get Compass page.");
		Assert.assertEquals("Compass", driver.findElement(MobileBy.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.TextView"))
				.getAttribute("text"));

	}

	@Test(description = "To verify that user is able to zoom in by \"Zoom controller\".")
	public void SSQPad_90() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on Top panel \"More option\" -> Setting.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_More_Option)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Settings)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-7:</b> Tap on Top panel \"More option\" -> Setting -> Map.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Map)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap to select checkbox of \"Show scale ruler\".");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.chk_Show_zoom_Level)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Result:</b> Zoom level should be visible to user.");
		Assert.assertEquals("true", driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.chk_Show_zoom_Level))
				.getAttribute("checked"));
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Back)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Back)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on (+) Zoom controller.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Zoom_In)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Zoom_In)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Result:</b> User should be able to view map zoomed in.");
		Assert.assertEquals("Level: 19",
				driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/tv_zoom_level")).getAttribute("text"));

	}

	@Test(description = "To verify that user is able to zoom out by \"Zoom controller\".")
	public void SSQPad_91() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on Top panel \"More option\" -> Setting.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_More_Option)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Settings)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-7:</b> Tap on Top panel \"More option\" -> Setting -> Map.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Map)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap to select checkbox of \"Show scale ruler\".");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.chk_Show_zoom_Level)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Result:</b> Zoom level should be visible to user.");
		Assert.assertEquals("true", driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.chk_Show_zoom_Level))
				.getAttribute("checked"));
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Back)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Back)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on (-) Zoom controller.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Zoom_Out)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Zoom_Out)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Result:</b> User should be able to view map zoomed out.");
		Assert.assertEquals("Level: 15",
				driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/tv_zoom_level")).getAttribute("text"));

	}

	@Test(description = "To verify \"Base Maps\" section.")
	public void SSQPad_92() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Hamburger menu\" -> Base Maps.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Base_Maps)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> User should able to view Base maps with following functionalities,\r\n"
						+ "1) Visibility toggle\r\n" + "2) Download tiles\r\n" + "3) Delete\r\n" + "4) Setting");
		Assert.assertEquals(true,
				driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Visibility_toggle)).isDisplayed());
		Assert.assertEquals(true,
				driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_more_option_B)).isDisplayed());

	}

	@Test(description = "To verify that user is able to perform \"Visibility toggle\" functionality from base maps.")
	public void SSQPad_93() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Hamburger menu\" -> Base Maps.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Base_Maps)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"Show\" icon from base map.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Visibility_toggle)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> User should able to view selected base map for viewing.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Zoom_Out)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Base_Maps)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Hide\" icon from base map.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Visibility_toggle)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> User should able to view selected base map for viewing.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Zoom_Out)).click();
		Thread.sleep(2000);

	}

	@Test(description = "To verify that user is able to perform \"Download tiles\" functionality from Base maps.")
	public void SSQPad_94() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Hamburger menu\" -> Base Maps.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Base_Maps)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"More Option\" from selective base map.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_more_option_B)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-8:</b> Tap on \"Download tile\" from More option for base map.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Download_Tiles)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-9:</b> Tap on \"Start\" button from zoom selection window.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> 1) User should be able to view \"Loading…\" bar for downloading.\r\n"
						+ "2) User should get toast message of \"Download start successfully.\".");
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/message")).isDisplayed());

	}

	@Test(description = "To verify that user is able to cancel download tile for base map.")
	public void SSQPad_95() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Hamburger menu\" -> Base Maps.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Base_Maps)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"More Option\" from selective base map.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_more_option_B)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-8:</b> Tap on \"Download tile\" from More option for base map.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Download_Tiles)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-9:</b> Tap on \"Cancel\" button from zoom selection window.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Cancel)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Result:</b> User should able to view Base maps section.");
		Assert.assertEquals(true,
				driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_more_option_B)).isDisplayed());

	}

	@Test(description = "To verify that user is able to perform \"Delete\" functionality from Base maps.")
	public void SSQPad_96() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Hamburger menu\" -> Base Maps.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Base_Maps)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"More Option\" from selective base map.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_more_option_B)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Delete\" from More option for base map.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Delete)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-9:</b> Tap on \"Yes\" button from confirmation validation message.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> 1)User should get \"Layer Deleted\" message with \"Undo\" button in snack bar form for 3 seconds.\r\n"
						+ "2) Deleted base map should not available at \"Base map\" section.");
		Assert.assertEquals("Google Satellite", driver.findElement(MobileBy.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.LinearLayout/androidx.viewpager.widget.ViewPager/android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout[1]/android.widget.TextView"))
				.getAttribute("text"));

	}

	@Test(description = "To verify that user is able to get back deleted base map by \"Undo\" button from snack bar.")
	public void SSQPad_97() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Hamburger menu\" -> Base Maps.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Base_Maps)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"More Option\" from selective base map.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_more_option_B)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Delete\" from More option for base map.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Delete)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-9:</b> Tap on \"Yes\" button from confirmation validation message.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Cancel)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-10:</b> Tap on \"Undo\" button within 3 seconds.");
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> User should be able to view deleted Base map available at \"Base map\" section.");
		Assert.assertEquals("OpenStreetMap", driver.findElement(MobileBy.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.LinearLayout/androidx.viewpager.widget.ViewPager/android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout[1]/android.widget.TextView"))
				.getAttribute("text"));

	}

	@Test(description = "To verify that user is able to open \"Setting\" page from Base maps.")
	public void SSQPad_98() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Hamburger menu\" -> Base Maps.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Base_Maps)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"More Option\" from selective base map.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_more_option_B)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-8:</b> Tap on \"Setting\" from More option for base map.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_settings)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> 1) User should able to view Setting section open.\r\n"
						+ "2) selected base map's name should be the Title of section.\r\n"
						+ "3) Setting section should have following subsections,\r\n" + "STYLE\r\n" + "GENERAL\r\n"
						+ "CACHE");
		Assert.assertEquals(true,
				driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Style_section)).isDisplayed());
		Assert.assertEquals(true,
				driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_General_section)).isDisplayed());
		Assert.assertEquals(true,
				driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Cache_section)).isDisplayed());

	}

	@Test(description = "To verify that user is able to get back on \"Base maps\" pan from \"Setting\" page.")
	public void SSQPad_99() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Hamburger menu\" -> Base Maps.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Base_Maps)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"More Option\" from selective base map.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_more_option_B)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-8:</b> Tap on \"Setting\" from More option for base map.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_settings)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on Back (<-) symbol from Setting page.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Back)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> user should be able to get back on \"Base maps\".");
		Assert.assertEquals(true,
				driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_more_option_B)).isDisplayed());

	}

	@Test(description = "To verify that user is able to control opacity of \"Base maps\".")
	public void SSQPad_100() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Hamburger menu\" -> Base Maps.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Base_Maps)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"More Option\" from selective base map.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_more_option_B)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-8:</b> Tap on \"Setting\" from More option for base map.");

		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_settings)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-9:</b> Set opacity for selected base map by opacity controller.");
		TouchAction swipe = new TouchAction(driver).tap(PointOption.point(500, 700)).perform();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> 1) User should be able to set required opacity from opacity controller bar.\r\n"
						+ "2) Selected base maps opacity should changed as per setted from opacity controller.");
		Assert.assertEquals("Opacity: 32%",
				driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/alpha_seek")).getAttribute("text"));

	}

	@Test(description = "To verify that user is able to control Contrast of \"Base maps\".")
	public void SSQPad_101() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Hamburger menu\" -> Base Maps.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Base_Maps)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"More Option\" from selective base map.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_more_option_B)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-8:</b> Tap on \"Setting\" from More option for base map.");

		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_settings)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-9:</b> Set contrast for elected base map by contrast controller.");
		TouchAction swipe = new TouchAction(driver).tap(PointOption.point(500, 925)).perform();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> 1) User should be able to set required contrast from contrast controller bar.\r\n"
						+ "2) Selected base maps contrast should changed as per settled from contrast controller.");
		Assert.assertEquals("Contrast: 3.3",
				driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/contrast_seek")).getAttribute("text"));

	}

	@Test(description = "To verify that user is able to control Brightness of \"Base maps\".")
	public void SSQPad_102() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Hamburger menu\" -> Base Maps.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Base_Maps)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"More Option\" from selective base map.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_more_option_B)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-8:</b> Tap on \"Setting\" from More option for base map.");

		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_settings)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-9:</b> Set brightness for elected base map by brightness controller.");
		TouchAction swipe = new TouchAction(driver).tap(PointOption.point(500, 1150)).perform();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> 1) User should be able to set required brightness from brightness controller bar.\r\n"
						+ "2) Selected base maps brightness should changed as per settled from brightness controller.");
		Assert.assertEquals("Brightness: -34%",
				driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/brightness_seek")).getAttribute("text"));

	}

	@Test(description = "To verify that user is able to turn on \"Grayscale\" for \"Base maps\".")
	public void SSQPad_103() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Hamburger menu\" -> Base Maps.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Base_Maps)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"More Option\" from selective base map.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_more_option_B)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-8:</b> Tap on \"Setting\" from More option for base map.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_settings)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-9:</b> \"On\" grey scale from Style sub section of Base map setting.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/make_grayscale")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> 1) User should be able to set required brightness from brightness controller bar.\r\n"
						+ "2) Selected base maps brightness should changed as per settled from brightness controller.");
		Assert.assertEquals("true",
				driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/make_grayscale")).getAttribute("checked"));

	}

	@Test(description = "To verify \"General\" sub section from Base map setting.")
	public void SSQPad_104() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Hamburger menu\" -> Base Maps.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Base_Maps)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"More Option\" from selective base map.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_more_option_B)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-8:</b> Tap on \"Setting\" from More option for base map.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_settings)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on \"GENERAL\" sub section.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_General_section)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> User should get \"General\" sub section open with,\r\n" + "1) Layer info\r\n"
						+ "2) Layer name setting\r\n" + "3) Zoom level to show layer controller.");
		Assert.assertEquals(true, driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/layer_local_lath")).isDisplayed());
		Assert.assertEquals(true, driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/layer_name")).isDisplayed());
		Assert.assertEquals(true, driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/rangebar")).isDisplayed());

	}

	@Test(description = "To verify user is able to change Base map Layer name.")
	public void SSQPad_105() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Hamburger menu\" -> Base Maps.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Base_Maps)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"More Option\" from selective base map.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_more_option_B)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-8:</b> Tap on \"Setting\" from More option for base map.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_settings)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on \"GENERAL\" sub section.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_General_section)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-10:</b> Edit layer name from \"Layer Name\" text bar.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/layer_name")).clear();
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/layer_name")).sendKeys("Automation");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> User should be able to edit layer name by \"Layer Name\" text bar.");
		Assert.assertEquals(true, driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/layer_local_lath")).isDisplayed());
		Assert.assertEquals("Automation",
				driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/layer_name")).getAttribute("text"));
		Assert.assertEquals(true, driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/rangebar")).isDisplayed());

	}

	@Test(description = "To verify that user is able to control \"Zoom level to show layer\".")
	public void SSQPad_106() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Hamburger menu\" -> Base Maps.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Base_Maps)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"More Option\" from selective base map.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_more_option_B)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-8:</b> Tap on \"Setting\" from More option for base map.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_settings)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on \"GENERAL\" sub section.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_General_section)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-10:</b> Set zoom level to show layer on home page for map.");
		TouchAction swipe = new TouchAction(driver).tap(PointOption.point(500, 1750)).perform();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> User should be able to Set zoom level to show layer as per required.");
		String aa = driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/rightIndexValue")).getAttribute("text");
		Assert.assertEquals(aa.contains("22"), true);

	}

	@Test(description = "To verify user is able to \"Clear cache\" for Base map.")
	public void SSQPad_107() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Hamburger menu\" -> Base Maps.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Base_Maps)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"More Option\" from selective base map.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_more_option_B)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-8:</b> Tap on \"Setting\" from More option for base map.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_settings)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on \"Cache\" sub section.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Cache_section)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-10:</b> Tap on \"Clear Cache\" button from \"Cache\" sub - section.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/clear_cache")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> User should be able to view \"Please wait\" validation message with clearing cache process.");

	}

	@Test(description = "To verify \"Track\" section.")
	public void SSQPad_108() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Hamburger menu\" -> Track.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Track)).click();
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> 1) User should able to view Track section open with following options,\r\n"
						+ "Settings");
		Assert.assertEquals(true,
				driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Visibility_toggle)).isDisplayed());
		Assert.assertEquals(true,
				driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_more_option_B)).isDisplayed());

	}

	@Test(description = "To verify that user is able to perform \"Visibility toggle\" functionality from base maps.")
	public void SSQPad_109() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Hamburger menu\" -> Track.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Track)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"Show\" icon from base map.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Visibility_toggle)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> User should able to view selected base map for viewing.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Zoom_Out)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Track)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Hide\" icon from base map.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Visibility_toggle)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> User should able to view selected base map for viewing.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Zoom_Out)).click();
		Thread.sleep(2000);

	}

	@Test(description = "To verify that user is able to open \"My tracks\" page.")
	public void SSQPad_113() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_More_Option)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Start_New_Track)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_More_Option)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Start_New_Track)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Hamburger menu\" -> Track.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Track)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"More Option\" from selective base map.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_more_option_B)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-8:</b> Tap on \"Setting\" from More option for base map.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Download_Tiles)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Result:</b> User should get \"My tracks\" page open with,\r\n"
				+ "Recorded track list\r\n" + "Checkbox for edit track\r\n" + "visibility toggle.");
		Assert.assertEquals("My tracks", driver.findElement(MobileBy.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.view.ViewGroup/android.widget.TextView"))
				.getAttribute("text"));

	}

	@Test(description = "To verify that user is able to get back to the \"My tracks\" menu.")
	public void SSQPad_114() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Hamburger menu\" -> Track.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Track)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"More Option\" from selective base map.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_more_option_B)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-8:</b> Tap on \"Setting\" from More option for base map.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Download_Tiles)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-9:</b> Tap on back (<-) icon from top panel of \"My Track\" page.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Back)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> User should get back to \"My Track\" section of Hamburger menu.");
		Assert.assertEquals(true,
				driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Visibility_toggle)).isDisplayed());
		Assert.assertEquals(true,
				driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_more_option_B)).isDisplayed());

	}

	@Test(description = "To verify that user is able to get more options for selected track.")
	public void SSQPad_115() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_More_Option)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Start_New_Track)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_More_Option)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Start_New_Track)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Hamburger menu\" -> Track.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Track)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"More Option\" from selective base map.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_more_option_B)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-8:</b> Tap on \"Setting\" from More option for base map.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Download_Tiles)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on checkbox to edit track.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/tv_name")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-10:</b> Tap on \"More option\".");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_More_Option)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> User should get following options available at more option menu,\r\n" + "Show\r\n"
						+ "Hide\r\n" + "Delete\r\n" + "Select all");
		Assert.assertEquals(true,
				driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Delete_T)).isDisplayed());
		Assert.assertEquals(true,
				driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Select_All_T)).isDisplayed());
		Assert.assertEquals(true,
				driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Show_T)).isDisplayed());
		Assert.assertEquals(true,
				driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hide_T)).isDisplayed());

	}

	@Test(description = "To verify visibility toggle functionality for recorded track.")
	public void SSQPad_116() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_More_Option)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Start_New_Track)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_More_Option)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Start_New_Track)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Hamburger menu\" -> Track.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Track)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"More Option\" from selective base map.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_more_option_B)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-8:</b> Tap on \"Setting\" from More option for base map.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Download_Tiles)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on Deselect visibility toggle.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/iv_visibility")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> User should  not be able to view recorded tracks on map.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Back)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Zoom_Out)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-10:</b> Tap on select visibility toggle.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_more_option_B)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Download_Tiles)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/iv_visibility")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> User should be able to view recorded tracks on map.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Back)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Zoom_Out)).click();
		Thread.sleep(2000);

	}

	@Test(description = "To verify that user is able to change colour for selected track.")
	public void SSQPad_117() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_More_Option)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Start_New_Track)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_More_Option)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Start_New_Track)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Hamburger menu\" -> Track.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Track)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"More Option\" from selective base map.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_more_option_B)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-8:</b> Tap on \"Setting\" from More option for base map.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Download_Tiles)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on checkbox to edit track.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/tv_name")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-10:</b> Tap on \"Select colour\" icon from top panel.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/menu_color")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-11:</b> Select required colour for selected track and tap on \"Ok\".");
		TouchAction swipe = new TouchAction(driver).tap(PointOption.point(500, 800)).perform();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> User should be able to view selected track's colour on map page is changed as per colour selection.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Back)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Back)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Zoom_Out)).click();
		Thread.sleep(2000);

	}

	@Test(description = "To verify that user is able to share selected track.")
	public void SSQPad_118() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_More_Option)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Start_New_Track)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_More_Option)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Start_New_Track)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Hamburger menu\" -> Track.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Track)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"More Option\" from selective base map.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_more_option_B)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-8:</b> Tap on \"Setting\" from More option for base map.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Download_Tiles)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on checkbox to edit track.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/tv_name")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-10:</b> Tap on \"Share\" option from top panel.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/menu_share")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> User should get Share window open at bottom panel.");
		Assert.assertEquals("Share", driver.findElement(MobileBy.id("android:id/title")).getAttribute("text"));

	}

	@Test(description = "To verify that user is able to \"Show\" selected track.")
	public void SSQPad_119() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_More_Option)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Start_New_Track)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_More_Option)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Start_New_Track)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Hamburger menu\" -> Track.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Track)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"More Option\" from selective base map.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_more_option_B)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-8:</b> Tap on \"Setting\" from More option for base map.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Download_Tiles)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on checkbox to edit track.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/tv_name")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-10:</b> Tap on \"More option\".");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_More_Option)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-11:</b> Tap on \"Show\".");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Show_T)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> User should be able to view recorded track on map.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Back)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Back)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Zoom_Out)).click();
		Thread.sleep(2000);

	}

	@Test(description = "To verify that user is able to \"hide\" selected track.")
	public void SSQPad_120() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_More_Option)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Start_New_Track)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_More_Option)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Start_New_Track)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Hamburger menu\" -> Track.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Track)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"More Option\" from selective base map.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_more_option_B)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-8:</b> Tap on \"Setting\" from More option for base map.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Download_Tiles)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on checkbox to edit track.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/tv_name")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-10:</b> Tap on \"More option\".");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_More_Option)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-11:</b> Tap on \"Hide\".");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hide_T)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> User should not be able to view recorded track on map.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Back)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Back)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Zoom_Out)).click();
		Thread.sleep(2000);

	}

	@Test(description = "To verify that user is able to \"Delete\" selected track.")
	public void SSQPad_121() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_More_Option)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Start_New_Track)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_More_Option)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Start_New_Track)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Hamburger menu\" -> Track.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Track)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"More Option\" from selective base map.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_more_option_B)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-8:</b> Tap on \"Setting\" from More option for base map.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Download_Tiles)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on checkbox to edit track.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/tv_name")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-10:</b> Tap on \"More option\".");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_More_Option)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-11:</b> Tap on \"Delete\".");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Delete_T)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Result:</b> Selected record should get deleted.");
		Assert.assertEquals("There are no recorded tracks yet.",
				driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/tv_empty_list")).getAttribute("text"));

	}

	@Test(description = "To verify that user is able to select all tracks.")
	public void SSQPad_122() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_More_Option)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Start_New_Track)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_More_Option)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Start_New_Track)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Hamburger menu\" -> Track.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Track)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"More Option\" from selective base map.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_more_option_B)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-8:</b> Tap on \"Setting\" from More option for base map.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Download_Tiles)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on checkbox to edit track.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/tv_name")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-10:</b> Tap on \"More option\".");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_More_Option)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-11:</b> Tap on \"Select All\".");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Select_All_T)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> User should be able to view that all tracks are selected.");
		Assert.assertEquals("true",
				driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/tv_name")).getAttribute("checked"));

	}

	@Test(description = "To verify \"Projects\" section.")
	public void SSQPad_123() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Hamburger menu\" -> Track.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> User should get \"Project\" section open by default.");
		Assert.assertEquals("11ProjectABC",
				driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/tvItem")).getAttribute("text"));

	}

	@Test(description = "Verify That dialog opens on Project Expand.")
	public void SSQPad_124() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Hamburger menu\" -> Track.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"Expand project\" icon.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> User should get validation message of \"Start Project\" with 'Yes' and 'No' options.");
		Assert.assertEquals("Are you sure, You want to start this project?",
				driver.findElement(MobileBy.id("android:id/message")).getAttribute("text"));

	}

	@Test(description = "To Verify That dialog is not Displayed on Expand/Collapse on Active Project Expand.")
	public void SSQPad_125() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Hamburger menu\" -> Track.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"Expand project\" icon.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Yes\" from validation message.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap outside the hamburger menu section.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Zoom_Out)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-10:</b> Reopen Hamburger menu section.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-11:</b> Tap on \"Expand project\" icon.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> User should get validation message of \"Start Project\" with 'Yes' and 'No' options.");
		Assert.assertEquals("LAYER1234",
				driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/tvLayerName")).getAttribute("text"));

	}

	@Test(description = "To verify that user is able to view assigned Layer in \"Project\" section.")
	public void SSQPad_126() throws Exception {
		Thread.sleep(4000);
		// EThread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Hamburger menu\" -> Track.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"Expand project\" icon.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Yes\" from validation message.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> User should get validation message of \"Start Project\" with 'Yes' and 'No' options.");
		Assert.assertEquals("LAYER1234",
				driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/tvLayerName")).getAttribute("text"));

	}

	@Test(description = "To verify that user is able to perform \"Visibility toggle\" functionality from Layers.")
	public void SSQPad_127() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Hamburger menu\" -> Track.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"Expand project\" icon.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Yes\" from validation message.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on select visibility toggle.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/btShow")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> User should be able to view assigned layer on map.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Zoom_Out)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on deselect visibility toggle.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/btShow")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> User should  not be able to view assigned layer on map.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Zoom_Out)).click();
		Thread.sleep(2000);

	}

	@Test(description = "To Verify the menu options available in layers of Projects sections.")
	public void SSQPad_128() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Hamburger menu\" -> Track.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"Expand project\" icon.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Yes\" from validation message.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on \"More option\" for assigned layer.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/btMore")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> User should get following options at more options menu,\r\n" + "Zoom to extent\r\n"
						+ "Zoom to boundary\r\n" + "Feature table\r\n" + "Rejected data only (Checkbox)\r\n"
						+ "Share\r\n" + "Edit\r\n" + "Settings\r\n" + "Report");
		Assert.assertEquals(true,
				driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Zoom_To_Extent)).isDisplayed());
		Assert.assertEquals(true,
				driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Zoom_To_Boundary)).isDisplayed());
		Assert.assertEquals(true,
				driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Features_table)).isDisplayed());
		Assert.assertEquals(true,
				driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Rejected_Data)).isDisplayed());
		Assert.assertEquals(true,
				driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Share)).isDisplayed());
		Assert.assertEquals(true, driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Edit)).isDisplayed());
		Assert.assertEquals(true,
				driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Settings_P)).isDisplayed());
		Assert.assertEquals(true,
				driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Reports)).isDisplayed());

	}

	@Test(description = "To verify that user is able to perform \"Zoom to extent\" functionality for assigned layer.")
	public void SSQPad_129() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Hamburger menu\" -> Track.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"Expand project\" icon.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Yes\" from validation message.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on \"More option\" for assigned layer.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/btMore")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-10:</b> Tap on \"Zoom to extent\" for assigned layer.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Zoom_To_Extent)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> User should be able to view every features from selected layer on centre position on map.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_More_Option)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Settings)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Map)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.chk_Show_zoom_Level)).click();
		Thread.sleep(2000);
		Assert.assertEquals("true", driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.chk_Show_zoom_Level))
				.getAttribute("checked"));
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Back)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Back)).click();
		Thread.sleep(2000);
		Assert.assertEquals("Level: 22",
				driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/tv_zoom_level")).getAttribute("text"));

	}

	@Test(description = "To verify that user is able to perform \"Zoom to boundary\" functionality for assigned layer.")
	public void SSQPad_130() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Hamburger menu\" -> Track.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"Expand project\" icon.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Yes\" from validation message.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on \"More option\" for assigned layer.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/btMore")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-10:</b> Tap on \"Zoom to boundary\" for assigned layer.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Zoom_To_Boundary)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> User should be able to view assigned boundary zoomed at Zoom level 17.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_More_Option)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Settings)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Map)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.chk_Show_zoom_Level)).click();
		Thread.sleep(2000);
		Assert.assertEquals("true", driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.chk_Show_zoom_Level))
				.getAttribute("checked"));
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Back)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Back)).click();
		Thread.sleep(2000);
		Assert.assertEquals("Level: 17",
				driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/tv_zoom_level")).getAttribute("text"));

	}

	@Test(description = "To verify that user is able to view \"Rejected\" data only on map.")
	public void SSQPad_131() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Hamburger menu\" -> Track.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"Expand project\" icon.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Yes\" from validation message.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on \"More option\" for assigned layer.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/btMore")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-10:</b> Select checkbox of \"Rejected data only\".");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Rejected_Data)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> User should be able to view only rejected data in 'Red triangle' symbol on map.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/btMore")).click();
		Thread.sleep(2000);
		Assert.assertEquals("true", driver.findElement(MobileBy.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout[4]/android.widget.LinearLayout/android.widget.CheckBox"))
				.getAttribute("checked"));

	}

	@Test(description = "To verify that user is able to view \"Feature Table\" for selected layer.")
	public void SSQPad_132() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Hamburger menu\" -> Track.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"Expand project\" icon.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Yes\" from validation message.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on \"More option\" for assigned layer.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/btMore")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-10:</b> Tap on \"Feature table\".");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Features_table)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> User should be able to view feature table for selected layer.");
		Assert.assertEquals("Features table", driver.findElement(MobileBy.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.view.ViewGroup[1]/android.widget.TextView[1]"))
				.getAttribute("text"));

	}

	@Test(description = "To verify that user is able to get back to \"Home page\" from feature table page..")
	public void SSQPad_133() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Hamburger menu\" -> Track.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"Expand project\" icon.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Yes\" from validation message.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on \"More option\" for assigned layer.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/btMore")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-10:</b> Tap on \"Feature table\".");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Features_table)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-11:</b> Tab on (<-) Back icon from 'Feature table'.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Back)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> User should get \"Home\" page SSQPad application.");
		Assert.assertEquals(true,
				driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).isDisplayed());

	}

	@Test(description = "To verify that user is able to open \"Query Builder\" window.")
	public void SSQPad_134() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Hamburger menu\" -> Track.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"Expand project\" icon.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Yes\" from validation message.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on \"More option\" for assigned layer.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/btMore")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-10:</b> Tap on \"Feature table\".");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Features_table)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-11:</b> Tap on \"Search\" icon from Feature table.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Action_Query)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> User should be able to view \"Query Builder\" window with Layer name.");
		Assert.assertEquals("Query Builder", driver.findElement(MobileBy.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.TextView[1]"))
				.getAttribute("text"));

	}

	@Test(description = "To verify that user is able to close \"Query Builder\" window.")
	public void SSQPad_135() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Hamburger menu\" -> Track.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"Expand project\" icon.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Yes\" from validation message.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on \"More option\" for assigned layer.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/btMore")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-10:</b> Tap on \"Feature table\".");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Features_table)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-11:</b> Tap on \"Search\" icon from Feature table.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Action_Query)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-12:</b> Tap on \"CLOSE\" button from \"Query Builder\" window.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/btnClose")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Result:</b> \"Query Builder\" window should get closed.");
		Assert.assertEquals("Features table", driver.findElement(MobileBy.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.view.ViewGroup[1]/android.widget.TextView[1]"))
				.getAttribute("text"));

	}

	@Test(description = "To verify that user is able to perform Query for feature table.")
	public void SSQPad_136() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Hamburger menu\" -> Track.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"Expand project\" icon.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Yes\" from validation message.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on \"More option\" for assigned layer.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/btMore")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-10:</b> Tap on \"Feature table\".");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Features_table)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-11:</b> Tap on \"Search\" icon from Feature table.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Action_Query)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-12:</b> Tap on \"Fields\" menu.");
		driver.findElement(MobileBy.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.Spinner[1]/android.widget.TextView"))
				.click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-13:</b> Select required field for searching data.");
		driver.findElement(MobileBy.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.ListView/android.widget.LinearLayout[2]/android.widget.TextView"))
				.click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-14:</b> Tap on \"Operations\" menu.");
		driver.findElement(MobileBy.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.Spinner[2]/android.widget.TextView"))
				.click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-15:</b> Select required mathematical operation for searching data.");
		driver.findElement(MobileBy.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.ListView/android.widget.LinearLayout[1]/android.widget.TextView"))
				.click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-16:</b> Enter required value according's to selected field at \"Value\" text bar.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/edtValue")).sendKeys("7");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-16:</b> Tap on \"APPLY\" button from \"Query Builder\" window.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/btnApply")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> User should get queried result data at \"Feature table\".");
		Assert.assertEquals(true, driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/attributes")).isDisplayed());

	}

	@Test(description = "To verify that user is able to get back default feature table from filtered result by Query.")
	public void SSQPad_137() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Hamburger menu\" -> Track.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"Expand project\" icon.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Yes\" from validation message.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on \"More option\" for assigned layer.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/btMore")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-10:</b> Tap on \"Feature table\".");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Features_table)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-11:</b> Tap on \"Search\" icon from Feature table.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Action_Query)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-12:</b> Tap on \"Fields\" menu.");
		driver.findElement(MobileBy.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.Spinner[1]/android.widget.TextView"))
				.click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-13:</b> Select required field for searching data.");
		driver.findElement(MobileBy.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.ListView/android.widget.LinearLayout[2]/android.widget.TextView"))
				.click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-14:</b> Tap on \"Operations\" menu.");
		driver.findElement(MobileBy.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.Spinner[2]/android.widget.TextView"))
				.click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-15:</b> Select required mathematical operation for searching data.");
		driver.findElement(MobileBy.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.ListView/android.widget.LinearLayout[1]/android.widget.TextView"))
				.click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-16:</b> Enter required value according's to selected field at \"Value\" text bar.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/edtValue")).sendKeys("0");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-16:</b> Tap on \"APPLY\" button from \"Query Builder\" window.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/btnApply")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-17:</b> Tap on \"Cancel\" icon from queried feature table.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/action_cancel")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> User should get back to default Feature data table.");
		Assert.assertEquals("7", driver.findElement(MobileBy.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.view.ViewGroup[2]/android.widget.TextView[6]"))
				.getAttribute("text"));

	}

	@Test(description = "To verify that user is able to view feature ID for selected feature from \"Feature table\".")
	public void SSQPad_138() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Hamburger menu\" -> Track.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"Expand project\" icon.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Yes\" from validation message.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on \"More option\" for assigned layer.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/btMore")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-10:</b> Tap on \"Feature table\".");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Features_table)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-11:</b> Tap on one of the feature data.");
		driver.findElement(MobileBy.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.view.ViewGroup[2]/android.widget.TextView[6]"))
				.click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> User should be able to view Feature ID at bottom panel with following options,\r\n"
						+ "Zoom to extent\r\n" + "Delete\r\n" + "Edit attributes");
		Assert.assertEquals(true, driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/menu_zoom")).isDisplayed());
		Assert.assertEquals(true, driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/menu_delete")).isDisplayed());
		Assert.assertEquals(true, driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/menu_edit")).isDisplayed());

	}

	@Test(description = "To verify \"Zoom to extent\" functionality for selected feature.")
	public void SSQPad_139() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Hamburger menu\" -> Track.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"Expand project\" icon.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Yes\" from validation message.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on \"More option\" for assigned layer.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/btMore")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-10:</b> Tap on \"Feature table\".");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Features_table)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-11:</b> Tap on one of the feature data.");
		driver.findElement(MobileBy.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.view.ViewGroup[2]/android.widget.TextView[6]"))
				.click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-12:</b> Tap on \"Zoom to extent\" icon from bottom panel.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/menu_zoom")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> User should get selected feature in centre of map.");
		Assert.assertEquals("Level: 17",
				driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/tv_zoom_level")).getAttribute("text"));

	}

	@Test(description = "To verify \"Delete\" functionality for selected feature.")
	public void SSQPad_140() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Hamburger menu\" -> Track.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"Expand project\" icon.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Yes\" from validation message.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on \"More option\" for assigned layer.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/btMore")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-10:</b> Tap on \"Feature table\".");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Features_table)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-11:</b> Tap on one of the feature data.");
		driver.findElement(MobileBy.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.view.ViewGroup[2]/android.widget.TextView[6]"))
				.click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-12:</b> Tap on \"Delete\" icon from bottom panel.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/menu_delete")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-12:</b> Tap on \"Yes\" option from validation box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> User should get selected feature in centre of map.");
		Assert.assertEquals(true, driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/attributes")).isDisplayed());

	}

	@Test(description = "To verify \"Edit attributes\" functionality for selected feature.")
	public void SSQPad_141() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Hamburger menu\" -> Track.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"Expand project\" icon.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Yes\" from validation message.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on \"More option\" for assigned layer.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/btMore")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-10:</b> Tap on \"Feature table\".");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Features_table)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-11:</b> Tap on one of the feature data.");
		driver.findElement(MobileBy.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.view.ViewGroup[2]/android.widget.TextView[6]"))
				.click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-12:</b> Tap on \"Edit attribute\" icon from bottom panel.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/menu_edit")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> User should get \"Set attributes\"  form open for selected feature.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		Assert.assertEquals("Set attributes", driver.findElement(MobileBy.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.view.ViewGroup/android.widget.TextView"))
				.getAttribute("text"));

	}

	@Test(description = "To verify that user is able to deselect ,selected feature.")
	public void SSQPad_142() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Hamburger menu\" -> Track.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"Expand project\" icon.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Yes\" from validation message.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on \"More option\" for assigned layer.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/btMore")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-10:</b> Tap on \"Feature table\".");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Features_table)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-11:</b> Tap on one of the feature data.");
		driver.findElement(MobileBy.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.view.ViewGroup[2]/android.widget.TextView[6]"))
				.click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-12:</b> Tap on \"Cancel\" (X) icon from \"Bottom Panel\".");
		driver.findElement(MobileBy.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.view.ViewGroup[3]/android.widget.ImageButton"))
				.click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> User should get \"Set attributes\"  form open for selected feature.");
		Assert.assertEquals(true, driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/attributes")).isDisplayed());

	}

	@Test(description = "To verify that user is able to  \"Share\" selected layer with surveyed data.")
	public void SSQPad_143() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Hamburger menu\" -> Track.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"Expand project\" icon.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Yes\" from validation message.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on \"More option\" for assigned layer.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/btMore")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-10:</b> Tap on \"Share\".");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Share)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> User should get Share window open at bottom panel.");
		Assert.assertEquals("Share", driver.findElement(MobileBy.id("android:id/titles")).getAttribute("text"));

	}

	@Test(description = "To verify that user is able to open \"Edit\" mode.")
	public void SSQPad_144() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Hamburger menu\" -> Track.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"Expand project\" icon.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Yes\" from validation message.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on \"More option\" for assigned layer.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/btMore")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-10:</b> Tap on \"Edit\".");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Edit)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> User should get Share window open at bottom panel.");
		Assert.assertEquals(true, driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/menu_feature_add")).isDisplayed());

	}

	@Test(description = "To verify that user is able to close \"Edit\" mode.")
	public void SSQPad_145() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Hamburger menu\" -> Track.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"Expand project\" icon.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Yes\" from validation message.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on \"More option\" for assigned layer.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/btMore")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-10:</b> Tap on \"Edit\".");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Edit)).click();
		Thread.sleep(2000);
		String aa = driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/iv_ruler")).getAttribute("bounds");
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-10:</b> Tap on \"Cancel\" (X) icon from \"Bottom Panel\".");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Close_Edit)).click();
		Thread.sleep(2000);
		String ab = driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/iv_ruler")).getAttribute("bounds");
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> User should get Share window open at bottom panel.");
		Assert.assertNotEquals(aa, ab);

	}

	@Test(description = "To verify that user is able to perform \"Add new Geometry\"\" functionality.")
	public void SSQPad_146() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Hamburger menu\" -> Track.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"Expand project\" icon.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Yes\" from validation message.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on \"More option\" for assigned layer.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/btMore")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-10:</b> Tap on \"Edit\".");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Edit)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-11:</b> Tap on \"Add geometry\" (+) icon from bottom panel.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Add_new_geometry)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> User should be able to view feature added on map with movable pin.");
		Assert.assertEquals("New feature", driver.findElement(MobileBy.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.FrameLayout/android.view.ViewGroup[1]/android.widget.TextView[1]"))
				.getAttribute("text"));

	}

	@Test(description = "To verify that user is able to get point at \"Centre\" of base map.")
	public void SSQPad_147() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Hamburger menu\" -> Track.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"Expand project\" icon.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Yes\" from validation message.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on \"More option\" for assigned layer.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/btMore")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-10:</b> Tap on \"Edit\".");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Edit)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-11:</b> Tap on \"Add geometry\" (+) icon from bottom panel.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Add_new_geometry)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-12:</b> Tap on \"Point to display centre\" icon from \"Action\" mode bottom panel.");
		TouchAction swipe = new TouchAction(driver).longPress(PointOption.point(1000, 500))
				.moveTo(PointOption.point(13, 225)).release().perform();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_move_point_to_center)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> Added feature on map should get located to centre of current view of map.");
		Assert.assertEquals("New feature", driver.findElement(MobileBy.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.FrameLayout/android.view.ViewGroup[1]/android.widget.TextView[1]"))
				.getAttribute("text"));

	}

	@Test(description = "To verify that user is able to get point at \"Current location\" of surveyor user on base map.")
	public void SSQPad_148() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Hamburger menu\" -> Track.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"Expand project\" icon.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Yes\" from validation message.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on \"More option\" for assigned layer.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/btMore")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-10:</b> Tap on \"Edit\".");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Edit)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-11:</b> Tap on \"Add geometry\" (+) icon from bottom panel.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Add_new_geometry)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-12:</b> Tap on \"Point to current location\" icon from \"Action\" mode bottom panel.");
		TouchAction swipe = new TouchAction(driver).longPress(PointOption.point(1000, 500))
				.moveTo(PointOption.point(13, 225)).release().perform();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_move_point_to_current_location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> Added feature on map should get located to current location of user.");
		Assert.assertEquals("New feature", driver.findElement(MobileBy.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.FrameLayout/android.view.ViewGroup[1]/android.widget.TextView[1]"))
				.getAttribute("text"));

	}

	@Test(description = "To verify that user is able to perform \"Undo\" for geometry location on base map.")
	public void SSQPad_149() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Hamburger menu\" -> Track.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"Expand project\" icon.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Yes\" from validation message.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on \"More option\" for assigned layer.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/btMore")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-10:</b> Tap on \"Edit\".");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Edit)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-11:</b> Tap on \"Add geometry\" (+) icon from bottom panel.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Add_new_geometry)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-12:</b> Move feature on map by pin.");
		TouchAction swipe = new TouchAction(driver).longPress(PointOption.point(720, 1480))
				.moveTo(PointOption.point(13, 225)).release().perform();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-13:</b> Tap on \"UNDO\" icon from \"Action\" mode top panel.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Undo)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> Added feature should get to it's previous location.");
		Assert.assertEquals("true",
				driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Redo)).getAttribute("enabled"));

	}

	@Test(description = "To verify that user is able to perform \"Redo\" for geometry location on base map.")
	public void SSQPad_150() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Hamburger menu\" -> Track.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"Expand project\" icon.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Yes\" from validation message.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on \"More option\" for assigned layer.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/btMore")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-10:</b> Tap on \"Edit\".");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Edit)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-11:</b> Tap on \"Add geometry\" (+) icon from bottom panel.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Add_new_geometry)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-12:</b> Move feature on map by pin.");
		TouchAction swipe = new TouchAction(driver).longPress(PointOption.point(720, 1480))
				.moveTo(PointOption.point(13, 225)).release().perform();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-13:</b> Tap on \"UNDO\" icon from \"Action\" mode top panel.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Undo)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-14:</b> Tap on \"REDO\" icon from \"Action\" mode top panel.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Redo)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> Added feature should get to it's next position on map compared to it's previous location.");
		Assert.assertEquals("true",
				driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Undo)).getAttribute("enabled"));

	}

	@Test(description = "To verify that \"Set attributes\" form opened by tapping on \"Save\" button from \"Edit Geometry\" mode.")
	public void SSQPad_154() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Hamburger menu\" -> Track.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"Expand project\" icon.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Yes\" from validation message.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on \"More option\" for assigned layer.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/btMore")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-10:</b> Tap on \"Edit\".");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Edit)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-11:</b> Tap on \"Add geometry\" (+) icon from bottom panel.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Add_new_geometry)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-12:</b> Tap on \"Save\" icon from top panel of \"Action\" mode.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Save)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> User should be able to view \"Set attributes\" form for added feature.");
		Assert.assertEquals(true,
				driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).isDisplayed());

	}

	@Test(description = "To verify that user is able get options by tapping on \"Photo\" symbol from \"Set attributes\" form.")
	public void SSQPad_155() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Hamburger menu\" -> Track.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"Expand project\" icon.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Yes\" from validation message.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on \"More option\" for assigned layer.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/btMore")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-10:</b> Tap on \"Edit\".");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Edit)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-10:</b> Tap on \"Add geometry\" (+) icon from bottom panel.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Add_new_geometry)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-12:</b> Tap on \"Save\" icon from top panel of \"Action\" mode.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Save)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-13:</b> Tap on Camera icon from \"Photo\" field.");
		TouchAction swipe = new TouchAction(driver).press(PointOption.point(13, 1553))
				.moveTo(PointOption.point(13, 225)).release().perform();
		Thread.sleep(2000);
		TouchAction swipe1 = new TouchAction(driver).press(PointOption.point(13, 1553))
				.moveTo(PointOption.point(13, 225)).release().perform();
		Thread.sleep(2000);
		TouchAction swipe2 = new TouchAction(driver).press(PointOption.point(13, 1553))
				.moveTo(PointOption.point(13, 225)).release().perform();
		Thread.sleep(2000);
		TouchAction swipe3 = new TouchAction(driver).press(PointOption.point(13, 1553))
				.moveTo(PointOption.point(13, 225)).release().perform();
		Thread.sleep(2000);
		TouchAction swipe4 = new TouchAction(driver).press(PointOption.point(13, 1553))
				.moveTo(PointOption.point(13, 225)).release().perform();
		Thread.sleep(2000);
		TouchAction swipe5 = new TouchAction(driver).press(PointOption.point(13, 1553))
				.moveTo(PointOption.point(13, 225)).release().perform();
		Thread.sleep(2000);
		TouchAction swipe6 = new TouchAction(driver).press(PointOption.point(13, 1553))
				.moveTo(PointOption.point(13, 225)).release().perform();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Camera)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> User should get \"Add photo\" dialog open with following options,\r\n"
						+ "1) Take new photo\r\n" + "2) Pick Photo from Gallery\r\n" + "3) Capture new Video");
		Assert.assertEquals("Add Media Attachment",
				driver.findElement(MobileBy.id("android:id/alertTitle")).getAttribute("text"));

	}

	@Test(description = "To verify that user is able to take new photo for \"Set attributes\" form.")
	public void SSQPad_156() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Hamburger menu\" -> Track.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"Expand project\" icon.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Yes\" from validation message.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on \"More option\" for assigned layer.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/btMore")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-10:</b> Tap on \"Edit\".");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Edit)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-10:</b> Tap on \"Add geometry\" (+) icon from bottom panel.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Add_new_geometry)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-12:</b> Tap on \"Save\" icon from top panel of \"Action\" mode.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Save)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-13:</b> Tap on Camera icon from \"Photo\" field.");
		TouchAction swipe = new TouchAction(driver).press(PointOption.point(13, 1553))
				.moveTo(PointOption.point(13, 225)).release().perform();
		Thread.sleep(2000);
		TouchAction swipe1 = new TouchAction(driver).press(PointOption.point(13, 1553))
				.moveTo(PointOption.point(13, 225)).release().perform();
		Thread.sleep(2000);
		TouchAction swipe2 = new TouchAction(driver).press(PointOption.point(13, 1553))
				.moveTo(PointOption.point(13, 225)).release().perform();
		Thread.sleep(2000);
		TouchAction swipe3 = new TouchAction(driver).press(PointOption.point(13, 1553))
				.moveTo(PointOption.point(13, 225)).release().perform();
		Thread.sleep(2000);
		TouchAction swipe4 = new TouchAction(driver).press(PointOption.point(13, 1553))
				.moveTo(PointOption.point(13, 225)).release().perform();
		Thread.sleep(2000);
		TouchAction swipe5 = new TouchAction(driver).press(PointOption.point(13, 1553))
				.moveTo(PointOption.point(13, 225)).release().perform();
		Thread.sleep(2000);
		TouchAction swipe6 = new TouchAction(driver).press(PointOption.point(13, 1553))
				.moveTo(PointOption.point(13, 225)).release().perform();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Camera)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-14</b> Tap on \"Add photo\" -> Take new photo.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Pick_Photo)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-15:</b> Tap on \"Capture\" icon from Camera section.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Capture)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> User should be able to view captured photo in thumbnail form at \"Photo\" field of \"Set attributes\" form.");
		Assert.assertEquals(true, driver.findElement(MobileBy.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.ScrollView/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout[2]/android.widget.FrameLayout"))
				.isDisplayed());

	}

	@Test(description = "To verify that user is able to pick photo from Gallery for \"Set attributes\" form.")
	public void SSQPad_157() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Hamburger menu\" -> Track.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"Expand project\" icon.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Yes\" from validation message.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on \"More option\" for assigned layer.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/btMore")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-10:</b> Tap on \"Edit\".");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Edit)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-11:</b> Tap on \"Add geometry\" (+) icon from bottom panel.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Add_new_geometry)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-12:</b> Tap on \"Save\" icon from top panel of \"Action\" mode.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Save)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-13:</b> Tap on Camera icon from \"Photo\" field.");
		TouchAction swipe = new TouchAction(driver).press(PointOption.point(13, 1553))
				.moveTo(PointOption.point(13, 225)).release().perform();
		Thread.sleep(2000);
		TouchAction swipe1 = new TouchAction(driver).press(PointOption.point(13, 1553))
				.moveTo(PointOption.point(13, 225)).release().perform();
		Thread.sleep(2000);
		TouchAction swipe2 = new TouchAction(driver).press(PointOption.point(13, 1553))
				.moveTo(PointOption.point(13, 225)).release().perform();
		Thread.sleep(2000);
		TouchAction swipe3 = new TouchAction(driver).press(PointOption.point(13, 1553))
				.moveTo(PointOption.point(13, 225)).release().perform();
		Thread.sleep(2000);
		TouchAction swipe4 = new TouchAction(driver).press(PointOption.point(13, 1553))
				.moveTo(PointOption.point(13, 225)).release().perform();
		Thread.sleep(2000);
		TouchAction swipe5 = new TouchAction(driver).press(PointOption.point(13, 1553))
				.moveTo(PointOption.point(13, 225)).release().perform();
		Thread.sleep(2000);
		TouchAction swipe6 = new TouchAction(driver).press(PointOption.point(13, 1553))
				.moveTo(PointOption.point(13, 225)).release().perform();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Camera)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-14:</b> Tap on \"Add photo\" -> Pick photo from Gallery.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Pick_Photo_2)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> User should be able to view selected photo in thumbnail form at \"Photo\" field of \"Set attributes\" form.");
		Assert.assertEquals("Recent", driver.findElement(MobileBy.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.support.v4.widget.DrawerLayout/android.widget.LinearLayout/android.view.ViewGroup/android.widget.TextView"))
				.getAttribute("text"));

	}

	@Test(description = "To verify that user is able to Capture new video for \"Set attributes\" form.")
	public void SSQPad_158() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Hamburger menu\" -> Track.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"Expand project\" icon.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Yes\" from validation message.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on \"More option\" for assigned layer.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/btMore")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-10:</b> Tap on \"Edit\".");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Edit)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-11:</b> Tap on \"Add geometry\" (+) icon from bottom panel.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Add_new_geometry)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-12:</b> Tap on \"Save\" icon from top panel of \"Action\" mode.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Save)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-13:</b> Tap on Camera icon from \"Photo\" field.");
		TouchAction swipe = new TouchAction(driver).press(PointOption.point(13, 1553))
				.moveTo(PointOption.point(13, 225)).release().perform();
		Thread.sleep(2000);
		TouchAction swipe1 = new TouchAction(driver).press(PointOption.point(13, 1553))
				.moveTo(PointOption.point(13, 225)).release().perform();
		Thread.sleep(2000);
		TouchAction swipe2 = new TouchAction(driver).press(PointOption.point(13, 1553))
				.moveTo(PointOption.point(13, 225)).release().perform();
		Thread.sleep(2000);
		TouchAction swipe3 = new TouchAction(driver).press(PointOption.point(13, 1553))
				.moveTo(PointOption.point(13, 225)).release().perform();
		Thread.sleep(2000);
		TouchAction swipe4 = new TouchAction(driver).press(PointOption.point(13, 1553))
				.moveTo(PointOption.point(13, 225)).release().perform();
		Thread.sleep(2000);
		TouchAction swipe5 = new TouchAction(driver).press(PointOption.point(13, 1553))
				.moveTo(PointOption.point(13, 225)).release().perform();
		Thread.sleep(2000);
		TouchAction swipe6 = new TouchAction(driver).press(PointOption.point(13, 1553))
				.moveTo(PointOption.point(13, 225)).release().perform();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Camera)).click();
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-14:</b> Tap on \"Add photo\" -> Capture new video.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Capture_Video)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-15:</b> \"Capture\" video from Camera section.");
		driver.findElement(MobileBy.id("com.android.camera2:id/shutter_button")).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id("com.android.camera2:id/shutter_button")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-16:</b> Tap on \"confirm\" symbol from captured video.");
		driver.findElement(MobileBy.id("com.android.camera2:id/done_button")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> User should be able to view selected photo in thumbnail form at \"Photo\" field of \"Set attributes\" form.");
		Assert.assertEquals(true, driver
				.findElement(MobileBy.xpath("//android.widget.VideoView[@content-desc=\"Add Video\"]")).isDisplayed());

	}

	@Test(description = "To verify that user is able to cancel captured video.")
	public void SSQPad_159() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Hamburger menu\" -> Track.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"Expand project\" icon.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Yes\" from validation message.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on \"More option\" for assigned layer.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/btMore")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-10:</b> Tap on \"Edit\".");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Edit)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-11:</b> Tap on \"Add geometry\" (+) icon from bottom panel.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Add_new_geometry)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-12:</b> Tap on \"Save\" icon from top panel of \"Action\" mode.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Save)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-13:</b> Tap on Camera icon from \"Photo\" field.");
		TouchAction swipe = new TouchAction(driver).press(PointOption.point(13, 1553))
				.moveTo(PointOption.point(13, 225)).release().perform();
		Thread.sleep(2000);
		TouchAction swipe1 = new TouchAction(driver).press(PointOption.point(13, 1553))
				.moveTo(PointOption.point(13, 225)).release().perform();
		Thread.sleep(2000);
		TouchAction swipe2 = new TouchAction(driver).press(PointOption.point(13, 1553))
				.moveTo(PointOption.point(13, 225)).release().perform();
		Thread.sleep(2000);
		TouchAction swipe3 = new TouchAction(driver).press(PointOption.point(13, 1553))
				.moveTo(PointOption.point(13, 225)).release().perform();
		Thread.sleep(2000);
		TouchAction swipe4 = new TouchAction(driver).press(PointOption.point(13, 1553))
				.moveTo(PointOption.point(13, 225)).release().perform();
		Thread.sleep(2000);
		TouchAction swipe5 = new TouchAction(driver).press(PointOption.point(13, 1553))
				.moveTo(PointOption.point(13, 225)).release().perform();
		Thread.sleep(2000);
		TouchAction swipe6 = new TouchAction(driver).press(PointOption.point(13, 1553))
				.moveTo(PointOption.point(13, 225)).release().perform();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Camera)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-14:</b> Tap on \"Add photo\" -> Capture new video.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Capture_Video)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-15:</b> \"Capture\" video from Camera section.");
		driver.findElement(MobileBy.id("com.android.camera2:id/shutter_button")).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id("com.android.camera2:id/shutter_button")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-16:</b> Tap on \"cancel\" symbol from captured video.");
		driver.findElement(MobileBy.id("com.android.camera2:id/cancel_button")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Result:</b> Camera should open.");
		Assert.assertEquals(true, driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Camera)).isDisplayed());

	}

	@Test(description = "To verify that user gets \"20 Seconds\" timer by staring capturing video.")
	public void SSQPad_160() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Hamburger menu\" -> Track.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"Expand project\" icon.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Yes\" from validation message.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on \"More option\" for assigned layer.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/btMore")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-10:</b> Tap on \"Edit\".");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Edit)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-11:</b> Tap on \"Add geometry\" (+) icon from bottom panel.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Add_new_geometry)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-12:</b> Tap on \"Save\" icon from top panel of \"Action\" mode.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Save)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-13:</b> Tap on Camera icon from \"Photo\" field.");
		TouchAction swipe = new TouchAction(driver).press(PointOption.point(13, 1553))
				.moveTo(PointOption.point(13, 225)).release().perform();
		Thread.sleep(2000);
		TouchAction swipe1 = new TouchAction(driver).press(PointOption.point(13, 1553))
				.moveTo(PointOption.point(13, 225)).release().perform();
		Thread.sleep(2000);
		TouchAction swipe2 = new TouchAction(driver).press(PointOption.point(13, 1553))
				.moveTo(PointOption.point(13, 225)).release().perform();
		Thread.sleep(2000);
		TouchAction swipe3 = new TouchAction(driver).press(PointOption.point(13, 1553))
				.moveTo(PointOption.point(13, 225)).release().perform();
		Thread.sleep(2000);
		TouchAction swipe4 = new TouchAction(driver).press(PointOption.point(13, 1553))
				.moveTo(PointOption.point(13, 225)).release().perform();
		Thread.sleep(2000);
		TouchAction swipe5 = new TouchAction(driver).press(PointOption.point(13, 1553))
				.moveTo(PointOption.point(13, 225)).release().perform();
		Thread.sleep(2000);
		TouchAction swipe6 = new TouchAction(driver).press(PointOption.point(13, 1553))
				.moveTo(PointOption.point(13, 225)).release().perform();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Camera)).click();
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-14:</b> Tap on \"Add photo\" -> Capture new video.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Capture_Video)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-15:</b> Tap on \"Start Recording\" from camera.");
		driver.findElement(MobileBy.id("com.android.camera2:id/shutter_button")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> User should be able to view \"20 seconds\" timer at top of camera section.");

	}

	@Test(description = "To verify that user is able to detach, selected media file from \"Set attributes\" form.")
	public void SSQPad_161() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Hamburger menu\" -> Track.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"Expand project\" icon.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Yes\" from validation message.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on \"More option\" for assigned layer.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/btMore")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-10:</b> Tap on \"Edit\".");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Edit)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-11:</b> Tap on \"Add geometry\" (+) icon from bottom panel.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Add_new_geometry)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-12:</b> Tap on \"Save\" icon from top panel of \"Action\" mode.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Save)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-13:</b> Tap on Camera icon from \"Photo\" field.");
		TouchAction swipe = new TouchAction(driver).press(PointOption.point(13, 1553))
				.moveTo(PointOption.point(13, 225)).release().perform();
		Thread.sleep(2000);
		TouchAction swipe1 = new TouchAction(driver).press(PointOption.point(13, 1553))
				.moveTo(PointOption.point(13, 225)).release().perform();
		Thread.sleep(2000);
		TouchAction swipe2 = new TouchAction(driver).press(PointOption.point(13, 1553))
				.moveTo(PointOption.point(13, 225)).release().perform();
		Thread.sleep(2000);
		TouchAction swipe3 = new TouchAction(driver).press(PointOption.point(13, 1553))
				.moveTo(PointOption.point(13, 225)).release().perform();
		Thread.sleep(2000);
		TouchAction swipe4 = new TouchAction(driver).press(PointOption.point(13, 1553))
				.moveTo(PointOption.point(13, 225)).release().perform();
		Thread.sleep(2000);
		TouchAction swipe5 = new TouchAction(driver).press(PointOption.point(13, 1553))
				.moveTo(PointOption.point(13, 225)).release().perform();
		Thread.sleep(2000);
		TouchAction swipe6 = new TouchAction(driver).press(PointOption.point(13, 1553))
				.moveTo(PointOption.point(13, 225)).release().perform();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Camera)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-14</b> Tap on \"Add photo\" -> Take new photo.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Pick_Photo)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-15:</b> Tap on \"Capture\" icon from Camera section.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Capture)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-16:</b> Tap on (X)  cancel icon from attached media file thumbnail.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/ib_remove")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> User should be able to view captured photo in thumbnail form at \"Photo\" field of \"Set attributes\" form.");
		Assert.assertEquals(true, driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Camera)).isDisplayed());

	}

	@Test(description = "To verify that user gets toast message for \"Maximum attachment\" reached.")
	public void SSQPad_162() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Hamburger menu\" -> Track.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"Expand project\" icon.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Yes\" from validation message.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on \"More option\" for assigned layer.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/btMore")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-10:</b> Tap on \"Edit\".");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Edit)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-11:</b> Tap on \"Add geometry\" (+) icon from bottom panel.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Add_new_geometry)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-12:</b> Tap on \"Save\" icon from top panel of \"Action\" mode.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Save)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-13:</b> Tap on Camera icon from \"Photo\" field.");
		TouchAction swipe = new TouchAction(driver).press(PointOption.point(13, 1553))
				.moveTo(PointOption.point(13, 225)).release().perform();
		Thread.sleep(2000);
		TouchAction swipe1 = new TouchAction(driver).press(PointOption.point(13, 1553))
				.moveTo(PointOption.point(13, 225)).release().perform();
		Thread.sleep(2000);
		TouchAction swipe2 = new TouchAction(driver).press(PointOption.point(13, 1553))
				.moveTo(PointOption.point(13, 225)).release().perform();
		Thread.sleep(2000);
		TouchAction swipe3 = new TouchAction(driver).press(PointOption.point(13, 1553))
				.moveTo(PointOption.point(13, 225)).release().perform();
		Thread.sleep(2000);
		TouchAction swipe4 = new TouchAction(driver).press(PointOption.point(13, 1553))
				.moveTo(PointOption.point(13, 225)).release().perform();
		Thread.sleep(2000);
		TouchAction swipe5 = new TouchAction(driver).press(PointOption.point(13, 1553))
				.moveTo(PointOption.point(13, 225)).release().perform();
		Thread.sleep(2000);
		TouchAction swipe6 = new TouchAction(driver).press(PointOption.point(13, 1553))
				.moveTo(PointOption.point(13, 225)).release().perform();
		Thread.sleep(2000);
		for (int i = 0; i < 5; i++) {

			driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Camera)).click();
			Thread.sleep(2000);
			ExtentTestManager.getTest().log(Status.INFO, "<b>Step-14</b> Tap on \"Add photo\" -> Take new photo.");
			driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Pick_Photo)).click();
			Thread.sleep(2000);
			ExtentTestManager.getTest().log(Status.INFO,
					"<b>Step-15:</b> Tap on \"Capture\" icon from Camera section.");
			driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Capture)).click();
			Thread.sleep(2000);

		}
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Camera)).click();
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> User should get toast message of \"Maximum attachment is 5\".");
		List<MobileElement> els2 = (List<MobileElement>) driver.findElementsByXPath("//android.widget.Toast[1]");
		for (int i = 0; i < els2.size(); i++) {

			// prints the elements of the List
			System.out.println(els2.get(i));
		}
		Assert.assertEquals(false, els2.isEmpty());
		System.out.println(driver.findElement(By.xpath("//android.widget.Toast[1]")).getText());
		Assert.assertEquals("Maximum attachment is 5",
				driver.findElement(By.xpath("//android.widget.Toast[1]")).getText());

	}

	@Test(description = "To verify that user gets validation message for saving attributes form without media attachments.")
	public void SSQPad_163() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Hamburger menu\" -> Track.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"Expand project\" icon.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Yes\" from validation message.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on \"More option\" for assigned layer.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/btMore")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-10:</b> Tap on \"Edit\".");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Edit)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-11:</b> Tap on \"Add geometry\" (+) icon from bottom panel.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Add_new_geometry)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-12:</b> Tap on \"Save\" icon from top panel of \"Action\" mode.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Save)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-13:</b> Tap on \"Apply\" icon from \"Set attributes\" form.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Apply)).click();
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> User should get validation message of \"Image/ Video Attachment is Mandatory\".");
		Assert.assertEquals(true, driver.findElementsByXPath("/hierarchy/android.widget.Toast"));
		List<MobileElement> els2 = (List<MobileElement>) driver.findElementsByXPath("//android.widget.Toast[1]");
		for (int i = 0; i < els2.size(); i++) {
			// prints the elements of the List
			System.out.println(els2.get(i));

		}
		Assert.assertEquals(false, els2.isEmpty());
		System.out.println(driver.findElement(By.xpath("//android.widget.Toast[1]")).getText());
		Assert.assertEquals("Image/ Video Attachment is Mandatory",
				driver.findElement(By.xpath("//android.widget.Toast[1]")).getText());

	}

	@Test(description = "To verify that user gets validation message of \"Data saved and synced successfully.\"")
	public void SSQPad_164() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Hamburger menu\" -> Track.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"Expand project\" icon.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Yes\" from validation message.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on \"More option\" for assigned layer.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/btMore")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-10:</b> Tap on \"Edit\".");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Edit)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-11:</b> Tap on \"Add geometry\" (+) icon from bottom panel.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Add_new_geometry)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-12:</b> Tap on \"Save\" icon from top panel of \"Action\" mode.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Save)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-13:</b> Tap on Camera icon from \"Photo\" field.");
		TouchAction swipe = new TouchAction(driver).press(PointOption.point(13, 1553))
				.moveTo(PointOption.point(13, 225)).release().perform();
		Thread.sleep(2000);
		TouchAction swipe1 = new TouchAction(driver).press(PointOption.point(13, 1553))
				.moveTo(PointOption.point(13, 225)).release().perform();
		Thread.sleep(2000);
		TouchAction swipe2 = new TouchAction(driver).press(PointOption.point(13, 1553))
				.moveTo(PointOption.point(13, 225)).release().perform();
		Thread.sleep(2000);
		TouchAction swipe3 = new TouchAction(driver).press(PointOption.point(13, 1553))
				.moveTo(PointOption.point(13, 225)).release().perform();
		Thread.sleep(2000);
		TouchAction swipe4 = new TouchAction(driver).press(PointOption.point(13, 1553))
				.moveTo(PointOption.point(13, 225)).release().perform();
		Thread.sleep(2000);
		TouchAction swipe5 = new TouchAction(driver).press(PointOption.point(13, 1553))
				.moveTo(PointOption.point(13, 225)).release().perform();
		Thread.sleep(2000);
		TouchAction swipe6 = new TouchAction(driver).press(PointOption.point(13, 1553))
				.moveTo(PointOption.point(13, 225)).release().perform();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Camera)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-14</b> Tap on \"Add photo\" -> Take new photo.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Pick_Photo)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-15:</b> Tap on \"Capture\" icon from Camera section.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Capture)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-16:</b> Tap on \"Apply\" icon from \"Set attributes\" form.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Apply)).click();
		Thread.sleep(10000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> User should get validation message of \"Data saved & Synced Successfully\".");
		List<MobileElement> els2 = (List<MobileElement>) driver.findElementsByXPath("//android.widget.Toast[1]");
		for (int i = 0; i < els2.size(); i++) {
			// prints the elements of the List
			System.out.println(els2.get(i));

		}
		Assert.assertEquals(false, els2.isEmpty());
		System.out.println(driver.findElement(By.xpath("//android.widget.Toast[1]")).getText());
		Assert.assertEquals("Data saved & Synced Successfully",
				driver.findElement(By.xpath("//android.widget.Toast[1]")).getText());

	}

	@Test(description = "To verify that user gets warning message for tapping back button from \"Save attributes\" form.")
	public void SSQPad_165() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Hamburger menu\" -> Track.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"Expand project\" icon.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Yes\" from validation message.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on \"More option\" for assigned layer.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/btMore")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-10:</b> Tap on \"Edit\".");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Edit)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-10:</b> Tap on \"Add geometry\" (+) icon from bottom panel.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Add_new_geometry)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-12:</b> Tap on \"Save\" icon from top panel of \"Action\" mode.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Save)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-13:</b> Tap on Camera icon from \"Photo\" field.");
		TouchAction swipe = new TouchAction(driver).press(PointOption.point(13, 1553))
				.moveTo(PointOption.point(13, 225)).release().perform();
		Thread.sleep(2000);
		TouchAction swipe1 = new TouchAction(driver).press(PointOption.point(13, 1553))
				.moveTo(PointOption.point(13, 225)).release().perform();
		Thread.sleep(2000);
		TouchAction swipe2 = new TouchAction(driver).press(PointOption.point(13, 1553))
				.moveTo(PointOption.point(13, 225)).release().perform();
		Thread.sleep(2000);
		TouchAction swipe3 = new TouchAction(driver).press(PointOption.point(13, 1553))
				.moveTo(PointOption.point(13, 225)).release().perform();
		Thread.sleep(2000);
		TouchAction swipe4 = new TouchAction(driver).press(PointOption.point(13, 1553))
				.moveTo(PointOption.point(13, 225)).release().perform();
		Thread.sleep(2000);
		TouchAction swipe5 = new TouchAction(driver).press(PointOption.point(13, 1553))
				.moveTo(PointOption.point(13, 225)).release().perform();
		Thread.sleep(2000);
		TouchAction swipe6 = new TouchAction(driver).press(PointOption.point(13, 1553))
				.moveTo(PointOption.point(13, 225)).release().perform();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Camera)).click();
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-14</b> Tap on \"Add photo\" -> Take new photo.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Pick_Photo)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-15:</b> Tap on \"Capture\" icon from Camera section.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Capture)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-16:</b> Tap on (<-) \"Back\" icon from \"Set attributes\" form.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Back)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> User should get validation message of \"Data saved & Synced Successfully\".");
		Assert.assertEquals("There are unsaved edits. Do you want to save them?",
				driver.findElement(MobileBy.id("android:id/message")).getAttribute("text"));

	}

	@Test(description = "To verify that user redirects to \"Edit\" mode for current geometry by tapping on \"Discard\" button from \"Save\" validation message.")
	public void SSQPad_166() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Hamburger menu\" -> Track.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"Expand project\" icon.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Yes\" from validation message.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on \"More option\" for assigned layer.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/btMore")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-10:</b> Tap on \"Edit\".");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Edit)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-11:</b> Tap on \"Add geometry\" (+) icon from bottom panel.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Add_new_geometry)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-12:</b> Tap on \"Save\" icon from top panel of \"Action\" mode.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Save)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-13:</b> Tap on Camera icon from \"Photo\" field.");
		TouchAction swipe = new TouchAction(driver).press(PointOption.point(13, 1553))
				.moveTo(PointOption.point(13, 225)).release().perform();
		Thread.sleep(2000);
		TouchAction swipe1 = new TouchAction(driver).press(PointOption.point(13, 1553))
				.moveTo(PointOption.point(13, 225)).release().perform();
		Thread.sleep(2000);
		TouchAction swipe2 = new TouchAction(driver).press(PointOption.point(13, 1553))
				.moveTo(PointOption.point(13, 225)).release().perform();
		Thread.sleep(2000);
		TouchAction swipe3 = new TouchAction(driver).press(PointOption.point(13, 1553))
				.moveTo(PointOption.point(13, 225)).release().perform();
		Thread.sleep(2000);
		TouchAction swipe4 = new TouchAction(driver).press(PointOption.point(13, 1553))
				.moveTo(PointOption.point(13, 225)).release().perform();
		Thread.sleep(2000);
		TouchAction swipe5 = new TouchAction(driver).press(PointOption.point(13, 1553))
				.moveTo(PointOption.point(13, 225)).release().perform();
		Thread.sleep(2000);
		TouchAction swipe6 = new TouchAction(driver).press(PointOption.point(13, 1553))
				.moveTo(PointOption.point(13, 225)).release().perform();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Camera)).click();
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-14</b> Tap on \"Add photo\" -> Take new photo.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Pick_Photo)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-15:</b> Tap on \"Capture\" icon from Camera section.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Capture)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-16:</b> Tap on (<-) \"Back\" icon from \"Set attributes\" form.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Back)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-17:</b> Tap on \"Discard\" option from Save validation message.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Cancel)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> User should get back to the \"Action\" mode for current feature.");
		Assert.assertEquals("New feature", driver.findElement(MobileBy.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.FrameLayout/android.view.ViewGroup[1]/android.widget.TextView[1]"))
				.getAttribute("text"));

	}

	@Test(description = "To verify that feature saved by tapping on \"Save\" button from \"Save\" warning dialog.")
	public void SSQPad_167() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Hamburger menu\" -> Track.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"Expand project\" icon.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Yes\" from validation message.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on \"More option\" for assigned layer.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/btMore")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-10:</b> Tap on \"Edit\".");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Edit)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-11:</b> Tap on \"Add geometry\" (+) icon from bottom panel.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Add_new_geometry)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-12:</b> Tap on \"Save\" icon from top panel of \"Action\" mode.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Save)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-13:</b> Tap on Camera icon from \"Photo\" field.");
		TouchAction swipe = new TouchAction(driver).press(PointOption.point(13, 1553))
				.moveTo(PointOption.point(13, 225)).release().perform();
		Thread.sleep(2000);
		TouchAction swipe1 = new TouchAction(driver).press(PointOption.point(13, 1553))
				.moveTo(PointOption.point(13, 225)).release().perform();
		Thread.sleep(2000);
		TouchAction swipe2 = new TouchAction(driver).press(PointOption.point(13, 1553))
				.moveTo(PointOption.point(13, 225)).release().perform();
		Thread.sleep(2000);
		TouchAction swipe3 = new TouchAction(driver).press(PointOption.point(13, 1553))
				.moveTo(PointOption.point(13, 225)).release().perform();
		Thread.sleep(2000);
		TouchAction swipe4 = new TouchAction(driver).press(PointOption.point(13, 1553))
				.moveTo(PointOption.point(13, 225)).release().perform();
		Thread.sleep(2000);
		TouchAction swipe5 = new TouchAction(driver).press(PointOption.point(13, 1553))
				.moveTo(PointOption.point(13, 225)).release().perform();
		Thread.sleep(2000);
		TouchAction swipe6 = new TouchAction(driver).press(PointOption.point(13, 1553))
				.moveTo(PointOption.point(13, 225)).release().perform();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Camera)).click();
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-14</b> Tap on \"Add photo\" -> Take new photo.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Pick_Photo)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-15:</b> Tap on \"Capture\" icon from Camera section.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Capture)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-16:</b> Tap on (<-) \"Back\" icon from \"Set attributes\" form.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Back)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-17:</b> Tap on \"Save\" option from Save validation message.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> User should get validation message of \"Data saved & Synced Successfully\"");
		List<MobileElement> els2 = (List<MobileElement>) driver.findElementsByXPath("//android.widget.Toast[1]");
		for (int i = 0; i < els2.size(); i++) {
			// prints the elements of the List
			System.out.println(els2.get(i));

		}
		Assert.assertEquals(false, els2.isEmpty());

	}

	@Test(description = "To verify that user is able to view \"Action\" mode for available feature on map.")
	public void SSQPad_168() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		driver.closeApp();
		Thread.sleep(2000);
		driver.activateApp("com.IGiS.QPadSS");
		Thread.sleep(10000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Hamburger menu\" -> Track.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"Expand project\" icon.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Yes\" from validation message.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on \"More option\" for assigned layer.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/btMore")).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Features_table)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.view.ViewGroup[2]/android.widget.TextView[6]"))
				.click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/menu_zoom")).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/btMore")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-10:</b> Tap on \"Edit\".");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Edit)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-11:</b> Tap on available feature on map.");
		TouchAction swipe = new TouchAction(driver).press(PointOption.point(720, 1480)).release().perform();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-12:</b> Tap on \"Edit\" icon from bottom panel.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Edit_Menu_Features)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Result:</b> User should be able to view \"Action\" mode open for selected feature from map.");
		Assert.assertEquals(true, driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Save)).isDisplayed());

	}

	@Test(description = "To verify that user is able to close \"Action\" mode.")
	public void SSQPad_169() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		driver.closeApp();
		Thread.sleep(2000);
		driver.activateApp("com.IGiS.QPadSS");
		Thread.sleep(10000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Hamburger menu\" -> Track.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"Expand project\" icon.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Yes\" from validation message.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on \"More option\" for assigned layer.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/btMore")).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Features_table)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.view.ViewGroup[2]/android.widget.TextView[6]"))
				.click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/menu_zoom")).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/btMore")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-10:</b> Tap on \"Edit\".");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Edit)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-11:</b> Tap on available feature on map.");
		TouchAction swipe = new TouchAction(driver).press(PointOption.point(720, 1480)).release().perform();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-12:</b> Tap on \"Edit\" icon from bottom panel.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Edit_Menu_Features)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,"<b>Step-13:</b> Tap on (X) \"Close\" icon from top panel of Action m ode.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Close_Menu_Features)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,"<b>Result:</b> 1) User should be able to close \"Action\" mode for selected feature.\r\n"
		  + "2) User should get redirect to \"Edit\" mode.");
		 Assert.assertEquals(true,driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Edit_Menu_Features)).isDisplayed());
	
	}

	@Test(description = "To verify that user is able to \"Delete\" selected feature from map by \"Action\" mode.")
	public void SSQPad_170() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		driver.closeApp();
		Thread.sleep(2000);
		driver.activateApp("com.IGiS.QPadSS");
		Thread.sleep(10000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Hamburger menu\" -> Track.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
	    ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"Expand project\" icon.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More)).click();
		Thread.sleep(2000);
	    ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Yes\" from validation message.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on \"More option\" for assigned layer.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/btMore")).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Features_table)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.view.ViewGroup[2]/android.widget.TextView[6]"))
				.click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/menu_zoom")).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/btMore")).click();
		Thread.sleep(2000);
	    ExtentTestManager.getTest().log(Status.INFO, "<b>Step-10:</b> Tap on \"Edit\".");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Edit)).click();
		Thread.sleep(2000);
	    ExtentTestManager.getTest().log(Status.INFO, "<b>Step-11:</b> Tap on available feature on map.");
		TouchAction swipe = new TouchAction(driver).press(PointOption.point(720, 1480)).release().perform();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-12:</b> Tap on \"Delete\" icon from bottom panel.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Delete_Menu_Features)).click();
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,"<b>Result:</b> 1) User should be able to close \"Action\" mode for selected feature.\r\n"
		  + "2) User should get redirect to \"Edit\" mode.");
		Assert.assertEquals("false", driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Edit_Menu_Features)).getAttribute("enabled"));
	
	}

	@Test(description = "To verify that user is able to view attributes page by \"Attributes\" from Action mode.")
	public void SSQPad_171() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		driver.closeApp();
		Thread.sleep(2000);
		driver.activateApp("com.IGiS.QPadSS");
		Thread.sleep(10000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Hamburger menu\" -> Track.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"Expand project\" icon.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Yes\" from validation message.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on \"More option\" for assigned layer.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/btMore")).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Features_table)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.view.ViewGroup[2]/android.widget.TextView[6]"))
				.click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/menu_zoom")).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/btMore")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-10:</b> Tap on\"Edit\".");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Edit)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-11:</b> Tap on available feature on map.");
		TouchAction swipe = new TouchAction(driver).press(PointOption.point(720, 1480)).release().perform();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-12:</b> Tap on \"Attributes\" icon from bottom panel.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Menu_Features_Attribute)).click();
		Thread.sleep(4000);
        ExtentTestManager.getTest().log(Status.INFO,"<b>Result:</b> User should be able to view attributes data for selected feature." );
		Assert.assertEquals("LAYER1234", driver.findElement(MobileBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.FrameLayout/android.view.ViewGroup[1]/android.widget.TextView[1]"))
				.getAttribute("text"));
	
	}

	@Test(description = "To verify that user is able to get back to the \"Action\" mode from Attributes page.")
	public void SSQPad_172() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		driver.closeApp();
		Thread.sleep(2000);
		driver.activateApp("com.IGiS.QPadSS");
		Thread.sleep(10000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on\"Hamburger menu\" -> Track.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"Expand project\" icon.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More)).click();
		Thread.sleep(2000);
	    ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Yes\" from validation message.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on \"More option\" for assigned layer.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/btMore")).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Features_table)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.view.ViewGroup[2]/android.widget.TextView[6]"))
				.click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/menu_zoom")).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/btMore")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-10:</b> Tap on \"Edit\".");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Edit)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-11:</b> Tap on available feature on map.");
		TouchAction swipe = new TouchAction(driver).press(PointOption.point(720, 1480)).release().perform();
		Thread.sleep(2000);
	    ExtentTestManager.getTest().log(Status.INFO, "<b>Step-12:</b> Tap on \"Attributes\" icon from bottom panel.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Menu_Features_Attribute)).click();
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-13:</b> Tap on \"Back\" icon from general setting section.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Back)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,"<b>Result:</b> User should be able to view attributes data for selected feature." );
		Assert.assertEquals("true", driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Edit_Menu_Features)).getAttribute("enabled"));
	
	}

	@Test(description = "To verify that user is able to edit \"Attributes form\".")
	public void SSQPad_173() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		driver.closeApp();
		Thread.sleep(2000);
		driver.activateApp("com.IGiS.QPadSS");
		Thread.sleep(10000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Hamburger menu\" -> Track.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"Expand project\" icon.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Yes\" from validation message.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on \"More option\" for assigned layer.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/btMore")).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Features_table)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.view.ViewGroup[2]/android.widget.TextView[6]"))
				.click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/menu_zoom")).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/btMore")).click();
		Thread.sleep(2000);
	    ExtentTestManager.getTest().log(Status.INFO, "<b>Step-10:</b> Tap on \"Edit\".");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Edit)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-11:</b> Tap on available feature on map.");
		TouchAction swipe = new TouchAction(driver).press(PointOption.point(720, 1480)).release().perform();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-12:</b> Tap on \"Attributes\" icon from bottom panel.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Menu_Features_Attribute)).click();
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-13:</b> Tap on \"Edit Attributes\" icon from bottom panel of viewed attribute form.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/menu_edit_attributes")).click();
		Thread.sleep(2000);
        ExtentTestManager.getTest().log(Status.INFO,"<b>Result:</b> User should get \"Set attributes\" page open for selected feature.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
        Assert.assertEquals("Set attributes", driver.findElement(MobileBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.view.ViewGroup/android.widget.TextView"))
				.getAttribute("text"));
	
	}

	@Test(description = "To verify that user is able to view attributes page for more than one features from common layer.")
	public void SSQPad_174() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		driver.closeApp();
		Thread.sleep(2000);
		driver.activateApp("com.IGiS.QPadSS");
		Thread.sleep(10000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Hamburger menu\" -> Track.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"Expand project\" icon.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Yes\" from validation message.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on \"More option\" for assigned layer.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/btMore")).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Features_table)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.view.ViewGroup[2]/android.widget.TextView[6]"))
				.click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/menu_zoom")).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/btMore")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-10:</b> Tap on \"Edit\".");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Edit)).click();
		Thread.sleep(2000);
	    ExtentTestManager.getTest().log(Status.INFO, "<b>Step-11:</b> Tap on available feature on map.");
		TouchAction swipe = new TouchAction(driver).press(PointOption.point(720, 1480)).release().perform();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-12:</b> Tap on \"Attributes\" icon from bottom panel.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Menu_Features_Attribute)).click();
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-13:</b> Tap on \"Next\" icon from bottom panel of viewed attribute form.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/menu_next")).click();
		Thread.sleep(2000);
        ExtentTestManager.getTest().log(Status.INFO,"<b>Result:</b> User should be able to view attributes data for next feature from layer.");
        Assert.assertEquals("2 of 5", driver.findElement(MobileBy.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.FrameLayout/android.view.ViewGroup[1]/android.widget.TextView[2]"))
				.getAttribute("text"));
	    ExtentTestManager.getTest().log(Status.INFO, "<b>Step-14:</b> Tap on \"Previous\" icon from bottom panel of viewed attribute form.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/menu_prev")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,"<b>Result:</b> User should be able to view attributes data for previously opened feature from layer.");
        Assert.assertEquals("1 of 5", driver.findElement(MobileBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.FrameLayout/android.view.ViewGroup[1]/android.widget.TextView[2]"))
				.getAttribute("text"));
	
	}

	@Test(description = "To verify that user gets \"Setting\" page open from  attributes page.")
	public void SSQPad_175() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		driver.closeApp();
		Thread.sleep(2000);
		driver.activateApp("com.IGiS.QPadSS");
		Thread.sleep(10000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Hamburger menu\" -> Track.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"Expand project\" icon.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Yes\" from validation message.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on \"More option\" for assigned layer.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/btMore")).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Features_table)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.view.ViewGroup[2]/android.widget.TextView[6]"))
				.click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/menu_zoom")).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/btMore")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-10:</b> Tap on \"Edit\".");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Edit)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-11:</b> Tap on available feature on map.");
		TouchAction swipe = new TouchAction(driver).press(PointOption.point(720, 1480)).release().perform();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-12:</b> Tap on \"Attributes\" icon from bottom panel.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Menu_Features_Attribute)).click();
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-13:</b> Tap on \"more option\" -> Setting.");
		driver.findElement(MobileBy.xpath("//android.widget.ImageView[@content-desc=\"More options\"]")).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout[1]/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.TextView"))
				.click();
		Thread.sleep(2000);
    	ExtentTestManager.getTest().log(Status.INFO,"<b>Result:</b> User should get setting page open.");
		Assert.assertEquals("Settings", driver.findElement(MobileBy.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.RelativeLayout/android.view.ViewGroup/android.widget.TextView"))
				.getAttribute("text"));
	}

	@Test(description = "To verify that user gets \"Help\" section open from  attributes page.")
	public void SSQPad_176() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		driver.closeApp();
		Thread.sleep(2000);
		driver.activateApp("com.IGiS.QPadSS");
		Thread.sleep(10000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Hamburger menu\" -> Track.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"Expand project\" icon.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Yes\" from validation message.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on \"More option\" for assigned layer.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/btMore")).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Features_table)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.view.ViewGroup[2]/android.widget.TextView[6]"))
				.click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/menu_zoom")).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/btMore")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-10:</b> Tap on \"Edit\".");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Edit)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-11:</b> Tap on available feature on map.");
		TouchAction swipe = new TouchAction(driver).press(PointOption.point(720, 1480)).release().perform();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-12:</b> Tap on \"Attributes\" icon from bottom panel.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Menu_Features_Attribute)).click();
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-13:</b> Tap on \"more option\" -> Help.");
		driver.findElement(MobileBy.xpath("//android.widget.ImageView[@content-desc=\"More options\"]")).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout[2]/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.TextView"))
				.click();
		Thread.sleep(2000);
    	ExtentTestManager.getTest().log(Status.INFO,"<b>Result:</b> User should get setting page open.");
		Assert.assertEquals("Help", driver.findElement(MobileBy.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.LinearLayout/android.widget.LinearLayout/android.view.ViewGroup/android.widget.TextView"))
				.getAttribute("text"));
	}

	@Test(description = "To verify that user is able to view \"report\" page open.")
	public void SSQPad_177() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
	    ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Hamburger menu\" -> Track.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"Expand project\" icon.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Yes\" from validation message.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on \"More option\" for assigned layer.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/btMore")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-10:</b> Tap on \"Report\".");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Reports)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,"<b>Result:</b> User should get \"Report\" open for selected Layer with following information,\r\n"
		  + "1) Layer name\r\n" + "2) Total count of available feature of layer\r\n" +
		  "3) No. of fields\r\n" + "4) Names of fields.");
		Assert.assertEquals("LAYER1234 Layer Report", driver.findElement(MobileBy.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout[1]/android.view.ViewGroup/android.widget.TextView"))
				.getAttribute("text"));
	
	}

	@Test(description = "To verify that user is able to view \"report\" page close.")
	public void SSQPad_178() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
        ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Hamburger menu\" -> Track.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"Expand project\" icon.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Yes\" from validation message.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
	    ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on \"More option\" for assigned layer.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/btMore")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-10:</b> Tap on \"Report\".");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Reports)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-11</b> Tap on (<-) \"Back\" icon from \"Report' page..");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Back)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,"<b>Result:</b> User should get redirect to \"Home\" page of SSQPad application.");
        Assert.assertEquals(true,
				driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).isDisplayed());
	
	}

	@Test(description = "To verify that user is able to share \"Report\" file.")
	public void SSQPad_179() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Hamburger menu\" -> Track.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"Expandproject\" icon.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Yes\" from validation message.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on \"More option\" for assigned layer.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/btMore")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-10:</b> Tap on \"Report\".");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Reports)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-11</b> Tap on \"Share\" icon from top panel of \"Report\" page.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/menu_share")).click();
		Thread.sleep(2000);
        ExtentTestManager.getTest().log(Status.INFO,"<b>Result:</b> User should get Share window open at bottom panel.");
        Assert.assertEquals("Share file with",
	    driver.findElement(MobileBy.id("android:id/titles")).getAttribute("text"));
	
	}

	@Test(description = "To verify that user is able to change size of added feature.")
	public void SSQPad_180() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Hamburger menu\" -> Track.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"Expand project\" icon.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Yes\" from validation message.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on \"More option\" for assigned layer.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/btMore")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-10:</b> Tap on \"Setting\".");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Settings_P)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,"<b>Result:</b> User should get \"Setting\" section open with following sub sections,\r\n"
		  + "STYLE\r\n" + "FIELDS\r\n" + "GENERAL\r\n" + "CACHE");
	    Assert.assertEquals(true,driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Style_Section)).isDisplayed());
		Assert.assertEquals(true,driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_fields_Section)).isDisplayed());
		Assert.assertEquals(true,driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_General_Section)).isDisplayed());
		Assert.assertEquals(true,driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Cache_Section)).isDisplayed());
	
	}

	@Test(description = "To verify that user is able to change type of added point feature on map.")
	public void SSQPad_181() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Hamburger menu\" -> Track.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"Expand project\" icon.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Yes\" from validation message.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on \"Moreoption\" for assigned layer.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/btMore")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-10:</b> Tap on \"Setting\".");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Settings_P)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-11:</b> Enter value forsize of feature between 1 to 10 at \"Size\" text bar.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/size")).clear();
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/size")).sendKeys("8");
		Thread.sleep(2000);
        ExtentTestManager.getTest().log(Status.INFO, "<b>Step-12</b> Tap on (<-) \"Back\" icon from \"Setting' page.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Back)).click();
		Thread.sleep(2000);
        ExtentTestManager.getTest().log(Status.INFO,"<b>Result:</b> 1) User should get redirect to \"Home\" page of SSQPad application.\r\n"
		  + "2) User should be able to view changes of size made for feature.");
		Assert.assertEquals(true,driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).isDisplayed());

	}

	@Test(description = "To verify that user is able to change \"Width\" of stroke for added feature on map.")
	public void SSQPad_182() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Hamburger menu\" -> Track.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"Expand project\" icon.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Yes\"from validation message.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on \"More option\" for assigned layer.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/btMore")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-10:</b> Tap on\"Setting\".");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Settings_P)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-11:</b> Enter value forsize of feature between 0 to 3 at \"Width\" text bar.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/width")).clear();
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/width")).sendKeys("3");
		Thread.sleep(2000);
        ExtentTestManager.getTest().log(Status.INFO, "<b>Step-12</b> Tap on (<-) \"Back\" icon from \"Setting' page.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Back)).click();
		Thread.sleep(2000);
        ExtentTestManager.getTest().log(Status.INFO,"<b>Result:</b> 1) User should get redirect to \"Home\" page of SSQPad application.\r\n"
		  + "2) User should be able to view changes of width made for feature.");
		Assert.assertEquals(true,driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).isDisplayed());

	}

	@Test(description = "To verify that user is able to change \"Fill colour\" and \"stroke colour\" for added feature on map.")
	public void SSQPad_183() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Hamburger menu\" -> Track.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"Expand project\" icon.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Yes\" from validation message.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on \"More option\" for assigned layer.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/btMore")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-10:</b> Tap on \"Setting\".");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Settings_P)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-11:</b> Select required fill colour for feature.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/color_fill_name")).click();
		Thread.sleep(2000);
		TouchAction swipe = new TouchAction(driver).tap(PointOption.point(500, 800)).perform();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-12:</b> Select required stroke colour for feature.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/color_stroke_name")).click();
		Thread.sleep(2000);
		TouchAction swipe1 = new TouchAction(driver).tap(PointOption.point(500, 800)).perform();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
        ExtentTestManager.getTest().log(Status.INFO, "<b>Step-13</b> Tap on (<-) \"Back\" icon from \"Setting' page.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Back)).click();
		Thread.sleep(2000);
        ExtentTestManager.getTest().log(Status.INFO,"<b>Result:</b> 1) User should get redirect to \"Home\" page of SSQPad application.\r\n"
		  +
		  "2) User should be able to view changes of fill colour and stroke colour made for feature.");
	    Assert.assertEquals(true,driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).isDisplayed());

	}

	@Test(description = "To verify that user is able to view annotation on map for added feature.")
	public void SSQPad_184() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Hamburger menu\" -> Track.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"Expand project\" icon.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Yes\" from validation message.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on \"Moreoption\" for assigned layer.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/btMore")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-10:</b> Tap on\"Setting\".");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Settings_P)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-11:</b> Select required field to view as annotation of feature.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/text_enabled")).click();
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-13:</b> Enter requiredconstant text for viewing annotation of feature.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/text")).sendKeys("abc");
		Thread.sleep(2000);
        ExtentTestManager.getTest().log(Status.INFO, "<b>Step-14</b> Tap on (<-) \"Back\" icon from \"Setting' page.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Back)).click();
		Thread.sleep(2000);
        ExtentTestManager.getTest().log(Status.INFO,"<b>Result:</b> 1) User should get redirect to \"Home\" page of SSQPad application.\r\n"
		  +
		  "2) User should be able to view text annotation of selected field on feature on map." );
		Assert.assertEquals(true,driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).isDisplayed());

	}

	@Test(description = "To verify that user is able set \"Constant text\" as annotation for feature.")
	public void SSQPad_185() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Hamburger menu\" -> Track.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"Expand project\" icon.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Yes\" from validation message.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on \"More option\" for assigned layer.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/btMore")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-10:</b> Tap on \"Setting\".");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Settings_P)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-11:</b> Select checkbox of \"Text\" field.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/text_enabled")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-12:</b> Deselect \"Fields\" for viewing annotations.");
		driver.findElement(MobileBy.id("ccom.IGiS.QPadSS:id/not_hardcoded")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-13:</b> Enter required constant text for viewing annotation of feature.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/text")).sendKeys("abc");
		Thread.sleep(2000);
        ExtentTestManager.getTest().log(Status.INFO, "<b>Step-13</b> Tap on (<-) \"Back\" icon from \"Setting' page.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Back)).click();
		Thread.sleep(2000);
        ExtentTestManager.getTest().log(Status.INFO,"<b>Result:</b> 1) User should get redirect to \"Home\" page of SSQPad application.\r\n"
		  +
		  "2) User should be able to view constant text annotation with feature on map." );
	    Assert.assertEquals(true,driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).isDisplayed());

	}

	@Test(description = "To verify that user is able to change \"Size\" of annotation on map.")
	public void SSQPad_186() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Hamburger menu\" -> Track.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"Expand project\" icon.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Yes\"from validation message.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on \"More option\" for assigned layer.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/btMore")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-10:</b> Tap on \"Setting\".");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Settings_P)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-11:</b> Select checkbox of \"Text\" field.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/text_enabled")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-12:</b> Tap on expand \"Size\" menu icon.");
		TouchAction swipe = new TouchAction(driver).longPress(PointOption.point(252, 2512))
				.moveTo(PointOption.point(252, 1402)).release().perform();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/androidx.viewpager.widget.ViewPager/android.widget.ScrollView/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout[3]/android.widget.LinearLayout[2]/android.widget.Spinner[1]/android.widget.TextView"))
				.click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-13:</b> Select required size for annotation.");
		driver.findElement(MobileBy.xpath("	\r\n"
				+ "/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.TextView[2]")).click();
		Thread.sleep(2000);
        ExtentTestManager.getTest().log(Status.INFO, "<b>Step-14</b> Tap on (<-) \"Back\" icon from \"Setting' page.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Back)).click();
		Thread.sleep(2000);
        ExtentTestManager.getTest().log(Status.INFO,"<b>Result:</b> 1) User should get redirect to \"Home\" page of SSQPad application.\r\n"
		  +
		  "2) User should be able to view annotation of feature as per selected size.");
		Assert.assertEquals(true,driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).isDisplayed());

	}

	@Test(description = "To verify that user is able to change \"Alignment\" of annotation on map.")
	public void SSQPad_187() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Hamburger menu\" -> Track.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"Expand project\" icon.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Yes\" from validation message.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on \"More option\" for assigned layer.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/btMore")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-10:</b> Tap on \"Setting\".");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Settings_P)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-11:</b> Select checkbox of \"Text\" field.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/text_enabled")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-12:</b> Tap on expand \"Alignment\" menu icon.");
		TouchAction swipe = new TouchAction(driver).longPress(PointOption.point(252, 2512))
				.moveTo(PointOption.point(252, 1402)).release().perform();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/androidx.viewpager.widget.ViewPager/android.widget.ScrollView/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout[3]/android.widget.LinearLayout[2]/android.widget.Spinner[2]/android.widget.TextView"))
				.click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-13:</b> Select required alignment for annotation.");
		driver.findElement(MobileBy.xpath("	\r\n"
				+ "/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.TextView[2]"))
				.click();
		Thread.sleep(2000);
        ExtentTestManager.getTest().log(Status.INFO, "<b>Step-14</b> Tap on (<-) \"Back\" icon from \"Setting' page.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Back)).click();
		Thread.sleep(2000);
        ExtentTestManager.getTest().log(Status.INFO,"<b>Result:</b> 1) User should get redirect to \"Home\" page of SSQPad application.\r\n"
		  +
		  "2) User should be able to view annotation with feature on map as per selected alignment.");		 
		Assert.assertEquals(true,driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).isDisplayed());

	}

	@Test(description = "To verify that user is able to change \"Text colour\" of annotation on map.")
	public void SSQPad_188() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Hamburger menu\" -> Track.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"Expand project\" icon.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Yes\" from validation message.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on \"More option\" for assigned layer.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/btMore")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-10:</b> Tap on \"Setting\".");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Settings_P)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-11:</b> Select checkbox of \"Text\" field.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/text_enabled")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-12:</b> Tap on colour icon from \"Text colour\" field.");
		TouchAction swipe = new TouchAction(driver).longPress(PointOption.point(252, 2512))
				.moveTo(PointOption.point(252, 1402)).release().perform();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/color_text_name")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-13:</b> Select required Text colour for feature annotation.");
		TouchAction swipe1 = new TouchAction(driver).tap(PointOption.point(500, 800)).perform();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
        ExtentTestManager.getTest().log(Status.INFO, "<b>Step-14</b> Tap on (<-) \"Back\" icon from \"Setting' page.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Back)).click();
		Thread.sleep(2000);
        ExtentTestManager.getTest().log(Status.INFO,"<b>Result:</b> 1) User should get redirect to \"Home\" page of SSQPad application.\r\n"
		  +
		  "2) User should be able to view annotation with feature on map as per selected alignment..");
		Assert.assertEquals(true,driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).isDisplayed());

	}

	@Test(description = "To verify that user is able to select annotation field from \"Field\" section of \"Layer settings\".")
	public void SSQPad_189() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on\"Hamburger menu\" -> Track.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"Expand	project\" icon.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Yes\" from validation message.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on \"More option\" for assigned layer.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/btMore")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-10:</b> Tap on \"Setting\".");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Settings_P)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-11:</b> Tap on \" Fields\".");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_fields_Section)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-12:</b>Select one of the field for viewing as annotation of feature.");
		driver.findElement(MobileBy.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/androidx.viewpager.widget.ViewPager/android.widget.ListView/android.widget.CheckedTextView[5]"))
				.click();
		Thread.sleep(2000);
        ExtentTestManager.getTest().log(Status.INFO,"<b>Result:</b> 1) User should get redirect to \"Home\" page of SSQPad application.\r\n"
		  +
		  "2) User should be able to view annotation as per selected field with feature on map.");		
		Assert.assertEquals(true,driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).isDisplayed());

	}

	@Test(description = "To verify that user is able to get \"General\" section from Layer settings.")
	public void SSQPad_190() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Hamburger menu\" -> Track.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"Expand project\" icon.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Yes\" from validation message.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on \"More option\" for assigned layer.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/btMore")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-10:</b> Tap on \"Setting\".");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Settings_P)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-11:</b> Tap on \"General\".");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_General_Section)).click();
		Thread.sleep(2000);
        ExtentTestManager.getTest().log(Status.INFO,"<b>Result:</b> User should be able to view General sub section with following functionalities,\r\n"
		  + "Layer info\r\n" + "Layer name setting\r\n" +
		  "Zoom levels to show layer\r\n" + "Delete all features");		 
		Assert.assertEquals(true, driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/layer_local_lath")).isDisplayed());
		Assert.assertEquals(true, driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/layer_name")).isDisplayed());
		Assert.assertEquals(true, driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/rangebar")).isDisplayed());
	}

	@Test(description = "To verify that user is able to change assigned Layer's name.")
	public void SSQPad_191() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Hamburger menu\" -> Track.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"Expand project\" icon.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Yes\"from validation message.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on \"More option\" for assigned layer.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/btMore")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-10:</b> Tap on\"Setting\".");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Settings_P)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-11:</b> Tap on \"General\".");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_General_Section)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-12:</b> Edit layer namefrom \"Layer Name\" text bar.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/layer_name")).clear();
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/layer_name")).sendKeys("Automation");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-13</b> Tap on (<-) \"Back\" icon from \"Setting' page.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Back)).click();
		Thread.sleep(2000);
        ExtentTestManager.getTest().log(Status.INFO,"<b>Result:</b> User should be able to view changed layer name at assigned layer from Project.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		Assert.assertEquals("Automation",driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/tvLayerName")).getAttribute("text"));

	}

	@Test(description = "To verify that user is able to perform \"Delete All Feature\" from General section of Layer Settings.")
	public void SSQPad_192() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Hamburger menu\" -> Track.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"Expand project\" icon.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Yes\" from validation message.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on \"More option\" for assigned layer.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/btMore")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-10:</b> Tap on \"Setting\".");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Settings_P)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-11:</b> Tap on \"General\".");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_General_Section)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-12:</b> Tap on \"Delete All Feature\" button.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/delete_features")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-13</b> Tap on \"Ok\" option from confirmation validation message box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,"<b>Result:</b> All features from assigned layer should get deleted from map and feature table.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Back)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/btMore")).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Features_table)).click();
		Thread.sleep(2000);
	}

	@Test(description = "To verify that features are not deleted by tapping on \"Cancel\" button from Confirmation validation message of \"Delete All Features\".")
	public void SSQPad_193() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Hamburger menu\" -> Track.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"Expand project\" icon.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Yes\" from validation message.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on \"More option\" for assigned layer.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/btMore")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-10:</b> Tap on \"Setting\".");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Settings_P)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-11:</b> Tap on \"General\".");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_General_Section)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-12:</b> Tap on \"Delete All Feature\" button.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/delete_features")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-13</b> Tap on \"Cancel\" option from confirmation validation message box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Cancel)).click();
		Thread.sleep(2000);
        ExtentTestManager.getTest().log(Status.INFO,"<b>Result:</b> 1) Validation message box should get closed.\r\n" +
		  "2) Features should not deleted from assigned layer.");		 
		Assert.assertEquals(true, driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/delete_features")).isDisplayed());
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Back)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/btMore")).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Features_table)).click();
		Thread.sleep(2000);
	
	}

	@Test(description = "To verify user is able to control \"Zoom level to show layer\" for select layer.")
	public void SSQPad_194() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Hamburger menu\" -> Track.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"Expand project\" icon.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Yes\" from validation message.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on \"More option\" for assigned layer.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/btMore")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-10:</b> Tap on \"Setting\".");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Settings_P)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-11:</b> Tap on \"General\".");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_General_Section)).click();
		Thread.sleep(2000);
        ExtentTestManager.getTest().log(Status.INFO, "<b>Step-12:</b> Select required zoom level to show layer from \"Zoom level\" controller.");
		TouchAction swipe = new TouchAction(driver).tap(PointOption.point(500, 1620)).perform();
		Thread.sleep(2000);
        ExtentTestManager.getTest().log(Status.INFO,"<b>Result:</b> UUser should be able to view zoom level of layer as per settled from \"Zoom level to show layer\" controller.");
		String aa = driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/rightIndexValue")).getAttribute("text");
		Assert.assertEquals(aa.contains("22"), true);
	
	}

	@Test(description = " To verify that user is able to Rebuild cache for selected layer.")
	public void SSQPad_195() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on\"Hamburger menu\" -> Track.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"Expand project\" icon.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Yes\" from validation message.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on \"More option\" for assigned layer.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/btMore")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-10:</b> Tap on \"Setting\".");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Settings_P)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-11:</b> Tap on \"Cache\".");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Cache_Section)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,"<b>Result:</b> User should be able to view Cache sub section open.");
		Assert.assertEquals(driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/rebuild_cache")).isDisplayed(), true);
	
	}

	@Test(description = "To verify that user gets \"Add Local Layer\" options.")
	public void SSQPad_197() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Hamburger menu\" -> Track.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"+\" icon.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_New_Layer)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,"<b>Result:</b>User should get following options open,\r\n" +
		  "Create layer\r\n" + "Open local\r\n" + "Add geoservice");
		Assert.assertEquals(driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Create_Layer)).isDisplayed(), true);
		Assert.assertEquals(driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Open_Local)).isDisplayed(),true);

    }

	@Test(description = "To verify that user is able to create local layer.")
	public void SSQPad_198() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Hamburger menu\" -> Track.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"+\" icon.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_New_Layer)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Create layer\" icon.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Create_Layer)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,"<b>Result:</b> User should get following options for creating local layer,\r\n"
		  + "Point\r\n" + "Linestring\r\n" + "Polygon\r\n" + "Multipoint\r\n" +
		  "Multilinestring\r\n" + "Multipolygon");
		Assert.assertEquals(driver.findElement(MobileBy.xpath("	\r\n"
				+ "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.TextView"))
				.getAttribute("text"), "Create layer");
	}

	@Test(description = "To verify that user is able to create local layer with fields.")
	public void SSQPad_199() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Hamburger menu\" -> Track.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"+\" icon.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_New_Layer)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Create layer\" icon.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Create_Layer)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Enter layer name at \"Layer name\" text bar.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/et_layer_name")).sendKeys("automation");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-10:</b> Tap on \"Geometry type\" from create layer form.");
		driver.findElement(MobileBy.id("android:id/text1")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-11:</b> Select required geometry for creating local layer.");
		driver.findElement(MobileBy.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.TextView[3]")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-12:</b> Tap on \"Add Fields (+)\" from Create layer form.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/ib_add_field")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-13:</b> Enter required field name for creating form.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/et_field_name")).sendKeys("Testing");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-14:</b> Tap on expand Field type dropdown menu.");
		driver.findElement(MobileBy.id("android:id/text1")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-15:</b> Select requiredfield type from field type list.");
		driver.findElement(MobileBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.TextView[5]")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-16:</b> Tap on \"Apply\" icon from \"Create layer\" top panel.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/menu_apply")).click();
		Thread.sleep(2000);
        ExtentTestManager.getTest().log(Status.INFO,"<b>Result:</b> 1)User should get toast message of \"Layer Created\".\r\n"
		  +
		  "2) User should get redirect to Project section with created local layer.");
		Assert.assertEquals(driver.findElement(MobileBy.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.LinearLayout/androidx.viewpager.widget.ViewPager/android.widget.FrameLayout/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout/android.widget.ListView/android.widget.LinearLayout[1]/android.widget.TextView"))
				.getAttribute("text"), "automation");
	}

	@Test(description = "To Verify that user can Add layer from Open Local Option.")
	public void SSQPad_200() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Hamburger menu\" -> Track.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"+\"icon.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_New_Layer)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Openlocal\" icon.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Open_Local)).click();
		Thread.sleep(2000);
        ExtentTestManager.getTest().log(Status.INFO,"<b>Result:</b> User should be able view that selected file's vector layer is added on Project section.");
		Assert.assertEquals("Recent", driver.findElement(MobileBy.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.support.v4.widget.DrawerLayout/android.widget.LinearLayout/android.view.ViewGroup/android.widget.TextView"))
				.getAttribute("text"));
	
	}

	@Test(description = "To verify That User can Perform Add Geoservice Option.")
	public void SSQPad_201() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Hamburger menu\" -> Track.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"+\" icon.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_New_Layer)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Add geoservice\" icon.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Add_Geoservice)).click();
		Thread.sleep(5000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap to selected rquired geoservice map.");
		driver.findElement(MobileBy.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.appcompat.widget.LinearLayoutCompat/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.ListView/android.widget.CheckedTextView[5]"))
				.click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-10:</b> Tap on \"Add\" button from \"Add geoservice\" dialog.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(5000);
        ExtentTestManager.getTest().log(Status.INFO,"<b>Result:</b> User should be able to view that added geoservice maps are added to \"Base Maps\" section.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Base_Maps)).click();
		Thread.sleep(2000);
		Assert.assertEquals("4UMaps", driver.findElement(MobileBy.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.LinearLayout/androidx.viewpager.widget.ViewPager/android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout[1]/android.widget.TextView"))
				.getAttribute("text"));
	
	}

	@Test(description = "Verify That User can not Perform Add Geoservice Option with TMS layer.")
	public void SSQPad_202() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Hamburger menu\" -> Track.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"+\"icon.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_New_Layer)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Add geoservice\" icon.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Add_Geoservice)).click();
		Thread.sleep(5000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on \"New\" button from \"Add geoservice\" dialog.");
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-10:</b> Enter Requiredlayer name at \"Layer name\" textbar.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/layer_name")).sendKeys("Testing");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-11:</b> Enter valid Layer URL at \"Layer URL\" field.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/layer_url")).sendKeys("http://{a,b,c}.tile.openstreetmap.org/{z}/{x}/{y}.png");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-12:</b> Tap on\"CREATE\" button from \"Create TMS layer\" dialog.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(5000);
		ExtentTestManager.getTest().log(Status.INFO,"<b>Result:</b> User should beable to view that added geoservice maps are added to \"Base Maps\" section.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Base_Maps)).click();
		Thread.sleep(2000);
		Assert.assertEquals("Testing", driver.findElement(MobileBy.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.LinearLayout/androidx.viewpager.widget.ViewPager/android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout[1]/android.widget.TextView"))
				.getAttribute("text"));
	}

	@Test(description = "To verify that user is able to explore \"Floating Action (+) \" button.")
	public void SSQPad_203() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-6:</b> Tap on \"Floating Action (+) \" button from home page.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Edit_Tool)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Result:</b> User should get following actions open,\r\n"
				+ "Identify\r\n" + "Edit by walk\r\n" + "Edit\r\n" + "Set attributes\r\n" + "Measurements");
		Assert.assertEquals(true,
				driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_add_feature_identity)).isDisplayed());
		Assert.assertEquals(true,
				driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_add_geometry_by_walk)).isDisplayed());
		Assert.assertEquals(true,
				driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_add_new_geometry)).isDisplayed());
		Assert.assertEquals(true,
				driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_add_current_location)).isDisplayed());
		Assert.assertEquals(true,
				driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_action_ruler)).isDisplayed());
	}

	@Test(description = "To verify that user is able to collapse \"Floating Action (+) \" button.")
	public void SSQPad_204() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Floating Action (+) \" button from home page.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Edit_Tool)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on (X) closefloating button icon.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Close_Edit_Tool)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,"<b>Result:</b> Floating actions icons should get collapsed.");
        Assert.assertEquals(true,driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Edit_Tool)).isDisplayed());
	
	}

	@Test(description = "To verify that user is able to perform \"Identify\" functionality.")
	public void SSQPad_205() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_New_Layer)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Create_Layer)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/et_layer_name")).sendKeys("automation");
		Thread.sleep(2000);
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/menu_apply")).click();
		Thread.sleep(2000);

		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Edit_Tool)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Floating Action (+) \" button from home page.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Edit_Tool)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"Identifier\" icon.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_add_feature_identity)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,"<b>Result:</b> User should get attribute form open for selected layer.");
        Assert.assertEquals("Tap feature for info", driver.findElement(MobileBy.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.FrameLayout/android.view.ViewGroup[2]/android.widget.TextView"))
				.getAttribute("text"));
	
	}

	@Test(description = "To verify that user is able to close \"Identify\" functionality.")
	public void SSQPad_206() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_New_Layer)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Create_Layer)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/et_layer_name")).sendKeys("automation");
		Thread.sleep(2000);
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/menu_apply")).click();
		Thread.sleep(2000);

		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Edit_Tool)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Floating Action (+) \" button from home page.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Edit_Tool)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"Identifier\" icon.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_add_feature_identity)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on (X) closeicon from \"Tap feature for info\" bottom panel.");
		driver.findElement(MobileBy.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.FrameLayout/android.view.ViewGroup[2]/android.widget.ImageButton"))
				.click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,"<b>Result:</b> Identifier functionality should get closed.");
		Assert.assertEquals(true,driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Edit_Tool)).isDisplayed());
	
	}

	@Test(description = "To verify that user is able to open \"Edit\" mode.")
	public void SSQPad_208() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_New_Layer)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Create_Layer)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/et_layer_name")).sendKeys("automation");
		Thread.sleep(2000);
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/menu_apply")).click();
		Thread.sleep(2000);

		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Edit_Tool)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Floating Action (+) \" button from home page.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Edit_Tool)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"Edit\" icon.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_add_new_geometry)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,"<b>Result:</b> User should get \"Edit\" mode open for selected layer.");
		Assert.assertEquals(true, driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/menu_feature_add")).isDisplayed());
	
	}

	@Test(description = "To verify that user is able to close \"Edit\" mode.")
	public void SSQPad_209() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_New_Layer)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Create_Layer)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/et_layer_name")).sendKeys("automation");
		Thread.sleep(2000);
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/menu_apply")).click();
		Thread.sleep(2000);

		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Edit_Tool)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Floating Action (+) \" button from home page.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Edit_Tool)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"Edit\" icon.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_add_new_geometry)).click();
		Thread.sleep(2000);
        ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Cancel\" (X) icon from \"Bottom Panel\".");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Close_Edit)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,"<b>Result:</b> User should get Share window open at bottom panel." );
		Assert.assertEquals(true,driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Edit_Tool)).isDisplayed());
	
	}

	@Test(description = "To verify that user is able to perform \"Add new Geometry\" functionality.")
	public void SSQPad_210() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_New_Layer)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Create_Layer)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/et_layer_name")).sendKeys("automation");
		Thread.sleep(2000);
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/menu_apply")).click();
		Thread.sleep(2000);

		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Edit_Tool)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Floating Action (+) \" button from home page.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Edit_Tool)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"Edit\" icon.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_add_new_geometry)).click();
		Thread.sleep(2000);
        ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Add geometry\" (+) icon from bottom panel.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Add_new_geometry)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,"<b>Result:</b> User should be able to view feature added on map with movable pin." );
		Assert.assertEquals("New feature", driver.findElement(MobileBy.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.FrameLayout/android.view.ViewGroup[1]/android.widget.TextView[1]"))
				.getAttribute("text"));

	}

	@Test(description = "To verify that user is able to get point at \"Centre\" of base map.")
	public void SSQPad_211() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_New_Layer)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Create_Layer)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/et_layer_name")).sendKeys("automation");
		Thread.sleep(2000);
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/menu_apply")).click();
		Thread.sleep(2000);

		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Edit_Tool)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Floating Action (+) \" button from home page.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Edit_Tool)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"Edit\" icon.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_add_new_geometry)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Add geometry\" (+) icon from bottom panel.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Add_new_geometry)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on \"Point to display centre\" icon from \"Action\" mode bottom panel.");
		TouchAction swipe = new TouchAction(driver).longPress(PointOption.point(1000, 500)).moveTo(PointOption.point(13, 225)).release().perform();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_move_point_to_center)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,"<b>Result:</b> Added feature on map should get located to centre of current view of map." );
		Assert.assertEquals("New feature", driver.findElement(MobileBy.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.FrameLayout/android.view.ViewGroup[1]/android.widget.TextView[1]"))
				.getAttribute("text"));

	}

	@Test(description = "To verify that user is able to get point at \"Current location\" of surveyor user on base map.")
	public void SSQPad_212() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_New_Layer)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Create_Layer)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/et_layer_name")).sendKeys("automation");
		Thread.sleep(2000);
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/menu_apply")).click();
		Thread.sleep(2000);

		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Edit_Tool)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Floating Action (+) \" button from home page.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Edit_Tool)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"Edit\" icon.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_add_new_geometry)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Add geometry\" (+) icon from bottom panel.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Add_new_geometry)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on \"Point to current location\" icon from \"Action\" mode bottom panel.");
		TouchAction swipe = new TouchAction(driver).longPress(PointOption.point(1000, 500))
				.moveTo(PointOption.point(13, 225)).release().perform();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_move_point_to_current_location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,"<b>Result:</b> Added feature on map should get located to current location of user." );
		Assert.assertEquals("New feature", driver.findElement(MobileBy.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.FrameLayout/android.view.ViewGroup[1]/android.widget.TextView[1]"))
				.getAttribute("text"));

	}

	@Test(description = "To verify that user is able to perform \"Undo\" for geometry location on base map.")
	public void SSQPad_213() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_New_Layer)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Create_Layer)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/et_layer_name")).sendKeys("automation");
		Thread.sleep(2000);
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/menu_apply")).click();
		Thread.sleep(2000);
        driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Edit_Tool)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Floating Action (+) \" button from home page.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Edit_Tool)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"Edit\" icon.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_add_new_geometry)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Add geometry\" (+) icon from bottom panel.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Add_new_geometry)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Move feature onmap by pin.");
		TouchAction swipe = new TouchAction(driver).longPress(PointOption.point(720, 1480))
				.moveTo(PointOption.point(13, 225)).release().perform();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-10:</b> Tap on \"UNDO\" icon from \"Action\" mode top panel.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Undo)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,"<b>Result:</b> Added feature should get to it's previous location." );
		Assert.assertEquals("true",driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Redo)).getAttribute("enabled"));

	}

	@Test(description = "To verify that user is able to perform \"Redo\" for geometry location on base map.")
	public void SSQPad_214() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_New_Layer)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Create_Layer)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/et_layer_name")).sendKeys("automation");
		Thread.sleep(2000);
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/menu_apply")).click();
		Thread.sleep(2000);
        driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Edit_Tool)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Floating Action (+) \" button from home page.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Edit_Tool)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"Edit\" icon.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_add_new_geometry)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Add geometry\" (+) icon from bottom panel.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Add_new_geometry)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Move feature on map by pin.");
		TouchAction swipe = new TouchAction(driver).longPress(PointOption.point(720, 1480))
				.moveTo(PointOption.point(13, 225)).release().perform();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-10:</b> Tap on \"UNDO\" icon from \"Action\" mode top panel.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Undo)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-11:</b> Tap on \"REDO\" icon from \"Action\" mode top panel.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Redo)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,"<b>Result:</b> Added feature should get to it's next position on map compared to it's previous location." );
		Assert.assertEquals("true",driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Undo)).getAttribute("enabled"));

	}

	@Test(description = "To verify that \"Set attributes\" form opened by tapping on \"Save\" button from \"Edit Geometry\" mode.")
	public void SSQPad_218() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_New_Layer)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Create_Layer)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/et_layer_name")).sendKeys("automation");
		Thread.sleep(2000);
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/menu_apply")).click();
		Thread.sleep(2000);
        driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Edit_Tool)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Floating Action (+) \" button from home page.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Edit_Tool)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"Edit\"icon.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_add_new_geometry)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Add geometry\" (+) icon from bottom panel.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Add_new_geometry)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on \"Save\"icon from top panel of \"Action\" mode.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Save)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,"<b>Result:</b> User should be able to view \"Set attributes\" form for added feature." );
		Assert.assertEquals(true,driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).isDisplayed());

	}

	@Test(description = "To verify that user is able get options by tapping on \"Photo\" symbol from \"Set attributes\" form.")
	public void SSQPad_219() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_New_Layer)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Create_Layer)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/et_layer_name")).sendKeys("automation");
		Thread.sleep(2000);
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/menu_apply")).click();
		Thread.sleep(2000);
        driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Edit_Tool)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on\"Floating Action (+) \" button from home page.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Edit_Tool)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"Edit\" icon.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_add_new_geometry)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Add geometry\" (+) icon from bottom panel.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Add_new_geometry)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on \"Save\" icon from top panel of \"Action\" mode.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Save)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-10:</b> Tap on Camera icon from \"Photo\" field.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Camera)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,"<b>Result:</b> User should get \"Add photo\" dialog open with following options,\r\n"
		  + "1) Take new photo\r\n" + "2) Pick Photo from Gallery\r\n" +
		  "3) Capture new Video" );
		Assert.assertEquals("Add Media Attachment",driver.findElement(MobileBy.id("android:id/alertTitle")).getAttribute("text"));

	}

	@Test(description = "To verify that user is able to take new photo for \"Set attributes\" form.")
	public void SSQPad_220() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_New_Layer)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Create_Layer)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/et_layer_name")).sendKeys("automation");
		Thread.sleep(2000);
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/menu_apply")).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Edit_Tool)).click();
		Thread.sleep(2000);
	    ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Floating Action (+) \" button from home page.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Edit_Tool)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"Edit\" icon.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_add_new_geometry)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Add geometry\" (+) icon from bottom panel.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Add_new_geometry)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on \"Save\"icon from top panel of \"Action\" mode.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Save)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-10:</b> Tap on Cameraicon from \"Photo\" field.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Camera)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-11</b> Tap on \"Addphoto\" -> Take new photo.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Pick_Photo)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-12:</b> Tap on\"Capture\" icon from Camera section.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Capture)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,"<b>Result:</b> User should beable to view captured photo in thumbnail form at \"Photo\" field of \"Setattributes\" form.");
         Assert.assertEquals(true, driver.findElement(MobileBy.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.ScrollView/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout[2]/android.widget.FrameLayout"))
				.isDisplayed());

	}

	@Test(description = "To verify that user is able to pick photo from Gallery for \"Set attributes\" form.")
	public void SSQPad_221() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_New_Layer)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Create_Layer)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/et_layer_name")).sendKeys("automation");
		Thread.sleep(2000);
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/menu_apply")).click();
		Thread.sleep(2000);
    	driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Edit_Tool)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Floating Action (+) \" button from home page.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Edit_Tool)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"Edit\" icon.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_add_new_geometry)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Add geometry\" (+) icon from bottom panel.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Add_new_geometry)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on \"Save\" icon from top panel of \"Action\" mode.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Save)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-10:</b> Tap on Camera icon from \"Photo\" field.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Camera)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-11:</b> Tap on \"Add photo\" -> Pick photo from Gallery.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Pick_Photo_2)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,"<b>Result:</b> User should beable to view selected photo in thumbnail form at \"Photo\" field of \"Setattributes\" form.");
		Assert.assertEquals("Recent", driver.findElement(MobileBy.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.support.v4.widget.DrawerLayout/android.widget.LinearLayout/android.view.ViewGroup/android.widget.TextView"))
				.getAttribute("text"));

	}

	@Test(description = "To verify that user is able to Capture new video for \"Set attributes\" form.")
	public void SSQPad_222() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_New_Layer)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Create_Layer)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/et_layer_name")).sendKeys("automation");
		Thread.sleep(2000);
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/menu_apply")).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Edit_Tool)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Floating Action (+) \" button from home page.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Edit_Tool)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"Edit\" icon.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_add_new_geometry)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Add geometry\" (+) icon from bottom panel.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Add_new_geometry)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on \"Save\" icon from top panel of \"Action\" mode.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Save)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-10:</b> Tap on Camera icon from \"Photo\" field.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Camera)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-11:</b> Tap on \"Add photo\" -> Capture new video.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Capture_Video)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-12:</b> \"Capture\" video from Camera section.");
		driver.findElement(MobileBy.id("com.android.camera2:id/shutter_button")).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id("com.android.camera2:id/shutter_button")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-13:</b> Tap on \"confirm\" symbol from captured video.");
		driver.findElement(MobileBy.id("com.android.camera2:id/done_button")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,"<b>Result:</b> User should be able to view selected photo in thumbnail form at \"Photo\" field of \"Set attributes\" form.");
		Assert.assertEquals(true, driver.findElement(MobileBy.xpath("//android.widget.VideoView[@content-desc=\"Add Video\"]")).isDisplayed());
	
	}

	@Test(description = "To verify that user is able to cancel captured video.")
	public void SSQPad_223() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_New_Layer)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Create_Layer)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/et_layer_name")).sendKeys("automation");
		Thread.sleep(2000);
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/menu_apply")).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Edit_Tool)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Floating Action (+) \" button from home page.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Edit_Tool)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"Edit\" icon.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_add_new_geometry)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Add geometry\" (+) icon from bottom panel.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Add_new_geometry)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on \"Save\"icon from top panel of \"Action\" mode.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Save)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-10:</b> Tap on Cameraicon from \"Photo\" field.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Camera)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-11:</b> Tap on \"Add photo\" -> Capture new video.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Capture_Video)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-12:</b> \"Capture\" video from Camera section.");
		driver.findElement(MobileBy.id("com.android.camera2:id/shutter_button")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-13:</b> Tap on \"cancel\" symbol from captured video.");
		driver.findElement(MobileBy.id("com.android.camera2:id/cancel_button")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,"<b>Result:</b> Camera should open.");
		Assert.assertEquals(true, driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Camera)).isDisplayed());
	
	}

	@Test(description = "To verify that user gets \"20 Seconds\" timer by staring capturing video.")
	public void SSQPad_224() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_New_Layer)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Create_Layer)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/et_layer_name")).sendKeys("automation");
		Thread.sleep(2000);
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/menu_apply")).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Edit_Tool)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Floating Action (+) \" button from home page.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Edit_Tool)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"Edit\" icon.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_add_new_geometry)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Addgeometry\" (+) icon from bottom panel.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Add_new_geometry)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on \"Save\"icon from top panel of \"Action\" mode.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Save)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-10:</b> Tap on Camera icon from \"Photo\" field.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Camera)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-11:</b> Tap on \"Add photo\" -> Capture new video.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Capture_Video)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-12:</b> \"Capture\" video from Camera section.");
		driver.findElement(MobileBy.id("com.android.camera2:id/shutter_button")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,"<b>Result:</b> User should beable to view \"20 seconds\" timer at top of camera section.");
	
	}

	@Test(description = "To verify that user is able to detach, selected media file from \"Set attributes\" form.")
	public void SSQPad_225() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_New_Layer)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Create_Layer)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/et_layer_name")).sendKeys("automation");
		Thread.sleep(2000);
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/menu_apply")).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Edit_Tool)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Floating Action (+) \" button from home page.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Edit_Tool)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"Edit\" icon.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_add_new_geometry)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Add geometry\" (+) icon from bottom panel.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Add_new_geometry)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on \"Save\" icon from top panel of \"Action\" mode.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Save)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-10:</b> Tap on Camera icon from \"Photo\" field.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Camera)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-11</b> Tap on \"Add photo\" -> Take new photo.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Pick_Photo)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-12:</b> Tap on \"Capture\" icon from Camera section.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Capture)).click();
		Thread.sleep(5000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-13:</b> Tap on (X) cancel icon from attached media file thumbnail.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/ib_remove")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,"<b>Result:</b> User should be able to view captured photo in thumbnail form at \"Photo\" field of \"Set attributes\" form.");
		Assert.assertEquals(true, driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Camera)).isDisplayed());
	
	}

	@Test(description = "To verify that user gets toast message for \"Maximum attachment\" reached.")
	public void SSQPad_226() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_New_Layer)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Create_Layer)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/et_layer_name")).sendKeys("automation");
		Thread.sleep(2000);
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/menu_apply")).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Edit_Tool)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Floating Action (+) \" button from home page.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Edit_Tool)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"Edit\" icon.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_add_new_geometry)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Add geometry\" (+) icon from bottom panel.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Add_new_geometry)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on \"Save\" icon from top panel of \"Action\" mode.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Save)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-10:</b> Tap on Camera icon from \"Photo\" field.");
		for (int i = 0; i < 5; i++) {
			driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Camera)).click();
			Thread.sleep(2000);
			ExtentTestManager.getTest().log(Status.INFO, "<b>Step-11</b> Tap on \"Add photo\" -> Take new photo.");
			driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Pick_Photo)).click();
			Thread.sleep(2000);
			ExtentTestManager.getTest().log(Status.INFO, "<b>Step-12:</b> Tap on \"Capture\" icon from Camera section.");
			driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Capture)).click();
			Thread.sleep(2000);
		}
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Camera)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,"<b>Result:</b> User should gettoast message of \"Maximum attachment is 5\".");
		List<MobileElement> els2 = (List<MobileElement>) driver.findElementsByXPath("//android.widget.Toast[1]");
		for (int i = 0; i < els2.size(); i++) {
			// prints the elements of the List
			System.out.println(els2.get(i));
		}
		Assert.assertEquals(false, els2.isEmpty());
		System.out.println(driver.findElement(By.xpath("//android.widget.Toast[1]")).getText());
		Assert.assertEquals("Maximum attachment is 5",driver.findElement(By.xpath("//android.widget.Toast[1]")).getText());

	}

	@Test(description = "To verify that user gets validation message for saving attributes form without media attachments.")
	public void SSQPad_227() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_New_Layer)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Create_Layer)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/et_layer_name")).sendKeys("automation");
		Thread.sleep(2000);
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/menu_apply")).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Edit_Tool)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Floating Action (+) \" button from home page.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Edit_Tool)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"Edit\"icon.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_add_new_geometry)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Add geometry\" (+) icon from bottom panel.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Add_new_geometry)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on \"Save\"icon from top panel of \"Action\" mode.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Save)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-10:</b> Tap on \"Apply\" icon from \"Set attributes\" form.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Apply)).click();
		ExtentTestManager.getTest().log(Status.INFO,"<b>Result:</b> User should get validation message of \"Image/ Video Attachment is Mandatory\".");
		List<MobileElement> els2 = (List<MobileElement>) driver.findElementsByXPath("//android.widget.Toast[1]");
		for (int i = 0; i < els2.size(); i++) {
			// prints the elements of the List
			System.out.println(els2.get(i));
		}
		Assert.assertEquals(false, els2.isEmpty());
		System.out.println(driver.findElement(By.xpath("//android.widget.Toast[1]")).getText());
		Assert.assertEquals("Image/ Video Attachment is Mandatory",
				driver.findElement(By.xpath("//android.widget.Toast[1]")).getText());
	}

	@Test(description = "To verify that user gets validation message of \"Data saved and synced successfully.\"")
	public void SSQPad_228() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_New_Layer)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Create_Layer)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/et_layer_name")).sendKeys("automation");
		Thread.sleep(2000);
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/menu_apply")).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Edit_Tool)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Floating Action (+) \" button from home page.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Edit_Tool)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"Edit\"icon.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_add_new_geometry)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Add geometry\" (+) icon from bottom panel.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Add_new_geometry)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on \"Save\"icon from top panel of \"Action\" mode.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Save)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-10:</b> Tap on Camera icon from \"Photo\" field.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Camera)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-11</b> Tap on \"Add photo\" -> Take new photo.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Pick_Photo)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-12:</b> Tap on \"Capture\" icon from Camera section.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Capture)).click();
		Thread.sleep(5000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-13:</b> Tap on \"Apply\" icon from \"Set attributes\" form.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Apply)).click();
		Thread.sleep(10000);
		ExtentTestManager.getTest().log(Status.INFO,"<b>Result:</b> User should get validation message of \"Data saved & Synced Successfully\".");
		List<MobileElement> els2 = (List<MobileElement>) driver.findElementsByXPath("//android.widget.Toast[1]");
		for (int i = 0; i < els2.size(); i++) {
			// prints the elements of the List
			System.out.println(els2.get(i));
		}
		Assert.assertEquals(false, els2.isEmpty());
		System.out.println(driver.findElement(By.xpath("//android.widget.Toast[1]")).getText());
		Assert.assertEquals("Saved Successfully", driver.findElement(By.xpath("//android.widget.Toast[1]")).getText());
	
	}

	@Test(description = "To verify that user gets warning message for tapping back button from \"Save attributes\" form.")
	public void SSQPad_229() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_New_Layer)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Create_Layer)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/et_layer_name")).sendKeys("automation");
		Thread.sleep(2000);
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/menu_apply")).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Edit_Tool)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Floating Action (+) \" button from home page.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Edit_Tool)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"Edit\" icon.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_add_new_geometry)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Add geometry\" (+) icon from bottom panel.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Add_new_geometry)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on \"Save\" icon from top panel of \"Action\" mode.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Save)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-10:</b> Tap on Camera icon from \"Photo\" field.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Camera)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-11</b> Tap on \"Add photo\" -> Take new photo.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Pick_Photo)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-12:</b> Tap on \"Capture\" icon from Camera section.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Capture)).click();
		Thread.sleep(5000);
        ExtentTestManager.getTest().log(Status.INFO, "<b>Step-13:</b> Tap on (<-) \"Back\" icon from \"Set attributes\" form.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Back)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,"<b>Result:</b> User should get validation message of \"Data saved & Synced Successfully\".");
		Assert.assertEquals("There are unsaved edits. Do you want to save them?",driver.findElement(MobileBy.id("android:id/message")).getAttribute("text"));
	
	}

	@Test(description = "To verify that user redirects to \"Edit\" mode for current geometry by tapping on \"Discard\" button from \"Save\" validation message.")
	public void SSQPad_230() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_New_Layer)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Create_Layer)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/et_layer_name")).sendKeys("automation");
		Thread.sleep(2000);
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/menu_apply")).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Edit_Tool)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Floating Action (+) \" button from home page.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Edit_Tool)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"Edit\" icon.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_add_new_geometry)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Add geometry\" (+) icon from bottom panel.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Add_new_geometry)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on \"Save\" icon from top panel of \"Action\" mode.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Save)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-10:</b> Tap on Camera icon from \"Photo\" field.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Camera)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-11</b> Tap on \"Add photo\" -> Take new photo.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Pick_Photo)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-12:</b> Tap on \"Capture\" icon from Camera section.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Capture)).click();
		Thread.sleep(5000);
        ExtentTestManager.getTest().log(Status.INFO, "<b>Step-13:</b> Tap on (<-) \"Back\" icon from \"Set attributes\" form.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Back)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-14:</b> Tap on \"Discard\" option from Save validation message.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Cancel)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,"<b>Result:</b> User should get back to the \"Action\" mode for current feature.");
		Assert.assertEquals("New feature", driver.findElement(MobileBy.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.FrameLayout/android.view.ViewGroup[1]/android.widget.TextView[1]"))
				.getAttribute("text"));
	}

	@Test(description = "To verify that feature saved by tapping on \"Save\" button from \"Save\" warning dialog.")
	public void SSQPad_231() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_New_Layer)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Create_Layer)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/et_layer_name")).sendKeys("automation");
		Thread.sleep(2000);
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/menu_apply")).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Edit_Tool)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Floating Action (+) \" button from home page.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Edit_Tool)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"Edit\" icon.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_add_new_geometry)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on \"Add geometry\" (+) icon from bottom panel.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Add_new_geometry)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on \"Save\" icon from top panel of \"Action\" mode.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Save)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-10:</b> Tap on Cameraicon from \"Photo\" field.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Camera)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-11</b> Tap on \"Add photo\" -> Take new photo.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Pick_Photo)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-12:</b> Tap on \"Capture\" icon from Camera section.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Capture)).click();
		Thread.sleep(5000);
        ExtentTestManager.getTest().log(Status.INFO, "<b>Step-13:</b> Tap on (<-) \"Back\" icon from \"Set attributes\" form.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Back)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-14:</b> Tap on \"Save\" option from Save validation message.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,"<b>Result:</b> User should get validation message of \"Data saved & Synced Successfully\"");
		List<MobileElement> els2 = (List<MobileElement>) driver.findElementsByXPath("//android.widget.Toast[1]");
		System.out.println(driver.findElement(By.xpath("//android.widget.Toast[1]")).getText());
		Assert.assertEquals("Saved Successfully", driver.findElement(By.xpath("//android.widget.Toast[1]")).getText());
		for (int i = 0; i < els2.size(); i++) {
			// prints the elements of the List
			System.out.println(els2.get(i));
		}
		Assert.assertEquals(false, els2.isEmpty());
		System.out.println(driver.findElement(By.xpath("//android.widget.Toast[1]")).getText());
		Assert.assertEquals("Saved Successfully", driver.findElement(By.xpath("//android.widget.Toast[1]")).getText());

	}

	@Test(description = "To verify that user is able to view \"Action\" mode for available feature on map.")
	public void SSQPad_232() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_New_Layer)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Create_Layer)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/et_layer_name")).sendKeys("automation");
		Thread.sleep(2000);
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/menu_apply")).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Edit_Tool)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Floating Action (+) \" button from home page.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Edit_Tool)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"Edit\" icon.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_add_new_geometry)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on available feature on map.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Add_new_geometry)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Save)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Camera)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Pick_Photo)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Capture)).click();
		Thread.sleep(5000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Back)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on \"Edit\" icon from bottom panel.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Edit_Menu_Features)).click();
		Thread.sleep(2000);
        ExtentTestManager.getTest().log(Status.INFO,"<b>Result:</b> User should be able to view \"Action\" mode open for selected feature from map.");
		Assert.assertEquals(true, driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Save)).isDisplayed());
	
	}

	@Test(description = "To verify that user is able to close \"Action\" mode.")
	public void SSQPad_233() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_New_Layer)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Create_Layer)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/et_layer_name")).sendKeys("automation");
		Thread.sleep(2000);
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/menu_apply")).click();
		Thread.sleep(2000);
        driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Edit_Tool)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Floating Action (+) \" button from home page.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Edit_Tool)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"Edit\" icon.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_add_new_geometry)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on availablefeature on map.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Add_new_geometry)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Save)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Camera)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Pick_Photo)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Capture)).click();
		Thread.sleep(5000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Back)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on \"Edit\" icon from bottom panel.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Edit_Menu_Features)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-10:</b> Tap on (X) \"Close\" icon from top panel of Action m ode.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Close_Menu_Features)).click();
		Thread.sleep(2000);
        ExtentTestManager.getTest().log(Status.INFO,"<b>Result:</b> 1) User should be able to close \"Action\" mode for selected feature.\r\n"
		  + "2) User should get redirect to \"Edit\" mode.");
		Assert.assertEquals(true,driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Edit_Menu_Features)).isDisplayed());

	}

	@Test(description = "To verify that user is able to \"Delete\" selected feature from map by \"Action\" mode.")
	public void SSQPad_234() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_New_Layer)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Create_Layer)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/et_layer_name")).sendKeys("automation");
		Thread.sleep(2000);
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/menu_apply")).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Edit_Tool)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Floating Action (+) \" button from home page.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Edit_Tool)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"Edit\" icon.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_add_new_geometry)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on available feature on map.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Add_new_geometry)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Save)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Camera)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Pick_Photo)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Capture)).click();
		Thread.sleep(5000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Back)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on \"Delete\" icon from bottom panel.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Delete_Menu_Features)).click();
		Thread.sleep(4000);
	    ExtentTestManager.getTest().log(Status.INFO,"<b>Result:</b> 1) User should be able to close \"Action\" mode for selected feature.\r\n"
		  + "2) User should get redirect to \"Edit\" mode.");		 
		Assert.assertEquals("false", driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Edit_Menu_Features)).getAttribute("enabled"));
	
	}

	@Test(description = "To verify that user is able to view attributes page by \"Attributes\" from Action mode.")
	public void SSQPad_235() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_New_Layer)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Create_Layer)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/et_layer_name")).sendKeys("automation");
		Thread.sleep(2000);
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/menu_apply")).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Edit_Tool)).click();
		Thread.sleep(2000);
		 ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Floating Action (+) \" button from home page.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Edit_Tool)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"Edit\" icon.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_add_new_geometry)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on available feature on map.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Add_new_geometry)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Save)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Camera)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Pick_Photo)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Capture)).click();
		Thread.sleep(5000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Back)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on \"Attributes\" icon from bottom panel.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Menu_Features_Attribute)).click();
		Thread.sleep(4000);
        ExtentTestManager.getTest().log(Status.INFO,"<b>Result:</b> User should be able to view attributes data for selected feature.");
		Assert.assertEquals("automation", driver.findElement(MobileBy.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.FrameLayout/android.view.ViewGroup[1]/android.widget.TextView[1]"))
				.getAttribute("text"));
	}

	@Test(description = "To verify that user is able to get back to the \"Action\" mode from Attributes page.")
	public void SSQPad_236() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_New_Layer)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Create_Layer)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/et_layer_name")).sendKeys("automation");
		Thread.sleep(2000);
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/menu_apply")).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Edit_Tool)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Floating Action (+) \" button from home page.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Edit_Tool)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"Edit\" icon.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_add_new_geometry)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on availablefeature on map.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Add_new_geometry)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Save)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Camera)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Pick_Photo)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Capture)).click();
		Thread.sleep(5000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Back)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on \"Attributes\" icon from bottom panel.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Menu_Features_Attribute)).click();
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-10:</b> Tap on \"Back\" icon from general setting section.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Back)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,"<b>Result:</b> User should be able to view attributes data for selected feature.");		 
		Assert.assertEquals("true", driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Edit_Menu_Features)).getAttribute("enabled"));
	
	}

	@Test(description = "To verify that user is able to edit \"Attributes form\".")
	public void SSQPad_237() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_New_Layer)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Create_Layer)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/et_layer_name")).sendKeys("automation");
		Thread.sleep(2000);
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/menu_apply")).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Edit_Tool)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Floating Action (+) \" button from home page.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Edit_Tool)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"Edit\" icon.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_add_new_geometry)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on available feature on map.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Add_new_geometry)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Save)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Camera)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Pick_Photo)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Capture)).click();
		Thread.sleep(5000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Back)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on \"Attributes\" icon from bottom panel.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Menu_Features_Attribute)).click();
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-10:</b> Tap on \"Edit Attributes\" icon from bottom panel of viewed attribute form.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/menu_edit_attributes")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,"<b>Result:</b> User should get \"Set attributes\" page open for selected feature.");
		Assert.assertEquals("Set attributes", driver.findElement(MobileBy.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.view.ViewGroup/android.widget.TextView"))
				.getAttribute("text"));

	}

	@Test(description = "To verify that user gets \"Setting\" page open from  attributes page.")
	public void SSQPad_239() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_New_Layer)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Create_Layer)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/et_layer_name")).sendKeys("automation");
		Thread.sleep(2000);
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/menu_apply")).click();
		Thread.sleep(2000);
        driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Edit_Tool)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Floating Action (+) \" button from home page.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Edit_Tool)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"Edit\" icon.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_add_new_geometry)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on available feature on map.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Add_new_geometry)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Save)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Camera)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Pick_Photo)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Capture)).click();
		Thread.sleep(5000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Back)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on \"Attributes\" icon from bottom panel.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Menu_Features_Attribute)).click();
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-10:</b> Tap on \"more option\" -> Setting.");
		driver.findElement(MobileBy.xpath("//android.widget.ImageView[@content-desc=\"More options\"]")).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout[1]/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.TextView"))
				.click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,"<b>Result:</b> User should get setting page open.");
		Assert.assertEquals("Settings", driver.findElement(MobileBy.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.RelativeLayout/android.view.ViewGroup/android.widget.TextView"))
				.getAttribute("text"));

	}

	@Test(description = "To verify that user gets \"Help\" section open from  attributes page.")
	public void SSQPad_240() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_New_Layer)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Create_Layer)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/et_layer_name")).sendKeys("automation");
		Thread.sleep(2000);
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/menu_apply")).click();
		Thread.sleep(2000);
        driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Edit_Tool)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Floating Action (+) \" button from home page.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Edit_Tool)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"Edit\" icon.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_add_new_geometry)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on available feature on map.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Add_new_geometry)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Save)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Camera)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Pick_Photo)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Capture)).click();
		Thread.sleep(5000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Back)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on \"Attributes\" icon from bottom panel.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Menu_Features_Attribute)).click();
		Thread.sleep(4000);
        ExtentTestManager.getTest().log(Status.INFO, "<b>Step-10:</b> Tap on \"more option\" -> Help.");
		driver.findElement(MobileBy.xpath("//android.widget.ImageView[@content-desc=\"More options\"]")).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout[2]/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.TextView"))
				.click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,"<b>Result:</b> User should get setting page open.");
		Assert.assertEquals("Help", driver.findElement(MobileBy.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.LinearLayout/android.widget.LinearLayout/android.view.ViewGroup/android.widget.TextView"))
				.getAttribute("text"));
	
	}

	@Test(description = "To verify that user is able to set attributes for current location.")
	public void SSQPad_241() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_New_Layer)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Create_Layer)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/et_layer_name")).sendKeys("automation");
		Thread.sleep(2000);
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/menu_apply")).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Edit_Tool)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Floating Action (+) \" button from home page.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Edit_Tool)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"Current Location\" icon.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_add_current_location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Fill required form data.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Camera)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Pick_Photo)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Capture)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on \"Apply\" icon from top panel of \"Set attributes\" form.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Apply)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,"<b>Result:</b> User should get validation message of \"Data saved & synced successfully.\".");
		List<MobileElement> els2 = (List<MobileElement>) driver.findElementsByXPath("//android.widget.Toast[1]");
		for (int i = 0; i < els2.size(); i++) {
			// prints the elements of the List
			System.out.println(els2.get(i));
		}
		Assert.assertEquals(false, els2.isEmpty());
		System.out.println(driver.findElement(By.xpath("//android.widget.Toast[1]")).getText());
		Assert.assertEquals("Saved Successfully", driver.findElement(By.xpath("//android.widget.Toast[1]")).getText());
	}

	@Test(description = "To verify that user is able to set attributes for current location with Accurate location.")
	public void SSQPad_242() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Hamburger_Menu)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_More)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Yes)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_New_Layer)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Create_Layer)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/et_layer_name")).sendKeys("automation");
		Thread.sleep(2000);
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/menu_apply")).click();
		Thread.sleep(2000);

		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Edit_Tool)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Floating Action (+) \" button from home page.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Edit_Tool)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"Current Location\" icon.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_add_current_location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Fill required form data.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Camera)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Pick_Photo)).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Capture)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on \"Accuracy location\" checkbox.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/accurate_location")).click();
		Thread.sleep(20000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-10:</b> Tap on \"Apply\" icon from top panel of \"Set attributes\" form.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Apply)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,"<b>Result:</b> User should getvalidation message of \"Data saved & synced successfully.\".");
		List<MobileElement> els2 = (List<MobileElement>) driver.findElementsByXPath("//android.widget.Toast[1]");
		for (int i = 0; i < els2.size(); i++) {
			// prints the elements of the List
			System.out.println(els2.get(i));
		}
		Assert.assertEquals(false, els2.isEmpty());
		System.out.println(driver.findElement(By.xpath("//android.widget.Toast[1]")).getText());
		Assert.assertEquals("Saved Successfully", driver.findElement(By.xpath("//android.widget.Toast[1]")).getText());
	}

	@Test(description = "To verify that user is able to measure length and area on map.")
	public void SSQPad_243() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Floating Action (+) \" button from home page.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Edit_Tool)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"Measure\" icon.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_action_ruler)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on map to activate measure functionality.");
		TouchAction swipe = new TouchAction(driver).tap(PointOption.point(500, 200)).perform();
		Thread.sleep(2000);
		TouchAction swipe1 = new TouchAction(driver).tap(PointOption.point(1200, 800)).perform();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,"<b>Result:</b> 1) User should get the polygon drawn within selected points.\r\n"
		  + "2) User should be able to view area in \r\n" +
		  "(m square) unit within drawn polygon at top panel.");
		Assert.assertEquals("789.603 m", driver.findElement(By.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.TextView"))
				.getAttribute("text"));
	}

	@Test(description = "To verify that user is able to close measure functionality.")
	public void SSQPad_244() throws Exception {
		Thread.sleep(4000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO,
				"<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
		Thread.sleep(8000);
		Assert.assertEquals(true, driver.findElement(MobileBy.id("android:id/content")).isDisplayed());
		driver.findElement(MobileBy.id("android:id/button3")).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Floating Action (+) \" button from home page.");
		driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Edit_Tool)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-7:</b> Tap on \"Measure\" icon.");
		driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_action_ruler)).click();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-8:</b> Tap on map to activate measure functionality.");
		TouchAction swipe = new TouchAction(driver).tap(PointOption.point(500, 200)).perform();
		Thread.sleep(2000);
		TouchAction swipe1 = new TouchAction(driver).tap(PointOption.point(1200, 800)).perform();
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.INFO, "<b>Step-9:</b> Tap on \"Right\" icon at floating button.");
		driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/add_point_by_tap")).click();
		Thread.sleep(2000);
        ExtentTestManager.getTest().log(Status.INFO,"<b>Result:</b> User should be able to view that Measure functionality is closed.");
		Assert.assertEquals(true,driver.findElement(MobileBy.xpath(SurveySolution_Testcase_R.btn_Edit_Tool)).isDisplayed());
	}

}
