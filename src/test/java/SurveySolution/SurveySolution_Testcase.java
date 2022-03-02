package SurveySolution;

import java.lang.reflect.Method;
import java.net.URL;
import java.nio.charset.MalformedInputException;
import java.util.Arrays;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.mobile.NetworkConnection;
import org.openqa.selenium.mobile.NetworkConnection.ConnectionType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.google.common.collect.ImmutableMap;

import Listener.ExtentTestManager;
import Listener.Extra_Screen;
import SurveySolution_R.SurveySolution_Testcase_R;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.connection.ConnectionState;
import io.appium.java_client.android.connection.ConnectionStateBuilder;
import io.appium.java_client.android.connection.HasNetworkConnection;
import io.appium.java_client.appmanagement.ApplicationState;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.MobileCapabilityType;

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

		context.setAttribute("AppiumDriver", driver);
	}
    @Test(description="To verify that application Installed successfully.")
	public void SSQPad_01() throws Exception{
    	 ExtentTestManager.getTest().log(Status.INFO, "<b>Result:</b> SSQPad application should be available at newly installed app.");
    	 String appId="com.IGiS.QPadSS";
         System.out.println(driver.isAppInstalled(appId));
         Assert.assertEquals(true, driver.isAppInstalled(appId));
		
    }
	
    @Test(description="To verify that application Uninstalled successfully.")
	public void SSQPad_02() throws Exception{
    	 ExtentTestManager.getTest().log(Status.INFO, "<b>Step-1:</b> Tap on Mobile settings -> App -> SSQPad application -> Uninstall.");
    	 String appId="com.IGiS.QPadSS";
    	 driver.removeApp(appId);
    	 ExtentTestManager.getTest().log(Status.INFO, "<b>Result:</b> SSQPad application should get uninstalled.");
         System.out.println(driver.isAppInstalled(appId));
         Assert.assertEquals(false, driver.isAppInstalled(appId));
		
    }
    
    @Test(description="To verify the Icon of application.")
	public void SSQPad_03() throws Exception{
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
    
    @Test(description="To verify the Splash screen.")
	public void SSQPad_04() throws Exception{
    	 ExtentTestManager.getTest().log(Status.INFO, "<b>Result:</b> Splash screen should be visible for 2 seconds with following information,\r\n"
    	 		+ "1) IGiS Qpad logo\r\n"
    	 		+ "2) Scanpoint Geomatics Limited logo.");
          System.out.print(driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/imageView2")).isDisplayed());
		  Assert.assertEquals(true, driver.findElement(MobileBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widge"
		  		+ "t.FrameLayout/android.view.ViewGroup/android.widget.LinearLayout/android.widget.ImageView")).isDisplayed() );	 	
    }
	
    @Test(description="To verify that Location access permission dialogs pops up.")
	public void SSQPad_05() throws Exception{
    	
       ExtentTestManager.getTest().log(Status.INFO, "<b>Result:</b> 1)Splash screen should be visible for 2 seconds.\r\n"
    			+ "2) Sign in page open with \"Location access permission\" dialog.");
   	   Thread.sleep(4000);
   	   System.out.println(driver.findElement(MobileBy.id("com.android.packageinstaller:id/permission_message")).getText());
   	   String Location_Message =  "Allow QPad- Survey Solution to access this device's location?";
	   Assert.assertEquals(Location_Message, driver.findElement(MobileBy.id("com.android.packageinstaller:id/permission_message")).getText() );	
    }
    
    /* Permission Access */
    
    @Test(description="To verify that user is notified with Dialog box on denying permissions for first time.")
	public void SSQPad_06() throws Exception{
    	Thread.sleep(4000);
    	ExtentTestManager.getTest().log(Status.INFO, "<b>Step-1:</b> Tap on \"Deny\" button from \"Location\" access permission dialog box.");
    	driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_deny_Location)).click();
    	Thread.sleep(2000);
    	ExtentTestManager.getTest().log(Status.INFO, "<b>Step-2:</b> Tap on \"Deny\" button from \"Photos, Media and Files\" access permission dialog box.");
    	driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_deny_Location)).click();
       ExtentTestManager.getTest().log(Status.INFO, "<b>Result:</b> 1) User should be able deny permission for \"Photos, Media and Files\".\r\n"
       		+ "2) User should get \"Permission\" dialog box open with \"Ok\" button and message \"Application needs some permissions to work properly. It includes writing to external storage (to save data on card) and access to location service\".");
   	   Thread.sleep(4000);
   	   System.out.println(driver.findElement(MobileBy.id("android:id/message")).getText());
   	   String permission =  "Application needs some permissions to work properly. It includes writing to external storage (to save data on card) and access to location services.";
	   Assert.assertEquals(permission, driver.findElement(MobileBy.id("android:id/message")).getText() );	
    }
    
    @Test(description="To verify that user is able to get Permission dialog open second time by tapping on \"Ok\" button from \"Permission\" dialog box.")
	public void SSQPad_07() throws Exception{
    	Thread.sleep(4000);
    	ExtentTestManager.getTest().log(Status.INFO, "<b>Step-1:</b> Tap on \"Deny\" button from \"Location\" access permission dialog box.");
    	driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_deny_Location)).click();
    	Thread.sleep(2000);
    	ExtentTestManager.getTest().log(Status.INFO, "<b>Step-2:</b> Tap on \"Deny\" button from \"Photos, Media and Files\" access permission dialog box.");
    	driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_deny_Location)).click();
    	Thread.sleep(2000);
    	ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Tap on \"Ok\" button from \"Permission\" dialog box.");
    	driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Ok_permission)).click();
        ExtentTestManager.getTest().log(Status.INFO, "<b>Result:</b> Sign in page open with \"Location access permission\" dialog.");
   	    Thread.sleep(4000);
	    System.out.println(driver.findElement(MobileBy.id("com.android.packageinstaller:id/permission_message")).getText());
	    String Location_Message =  "Allow QPad- Survey Solution to access this device's location?";
	    Assert.assertEquals(Location_Message, driver.findElement(MobileBy.id("com.android.packageinstaller:id/permission_message")).getText() );	
    }
    
    @Test(description="To verify that user is notified with Warning Dialog on denial of permissions for second time.")
	public void SSQPad_08() throws Exception{
    	Thread.sleep(4000);
    	ExtentTestManager.getTest().log(Status.INFO, "<b>Step-1:</b> Tap on \"Deny\" button from \"Location\" access permission dialog box.");
    	driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_deny_Location)).click();
    	Thread.sleep(2000);
    	ExtentTestManager.getTest().log(Status.INFO, "<b>Step-2:</b> Tap on \"Deny\" button from \"Photos, Media and Files\" access permission dialog box.");
    	driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_deny_Location)).click();
    	Thread.sleep(2000);
    	ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Tap on \"Ok\" button from \"Permission\" dialog box.");
    	driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Ok_permission)).click();
    	ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Tap on \"Deny\" button from \"Location\" access permission dialog box.");
    	driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_deny_Location)).click();
    	Thread.sleep(2000);
    	ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Deny\" button from \"Photos, Media and Files\" access permission dialog box.");
    	driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_deny_Location)).click();
    	Thread.sleep(2000);
        ExtentTestManager.getTest().log(Status.INFO, "<b>Result:</b> \"Permission Required\" dialog should open with \"Open Setting\" and \"Ok\" button.");
   	    Thread.sleep(4000);
	    System.out.println(driver.findElement(MobileBy.id("android:id/message")).getText());
	    String Location_Message = "You can't proceed further without mandatory location and storage permissions";
	    Assert.assertEquals(Location_Message, driver.findElement(MobileBy.id("android:id/message")).getText() );	
    }
    
    @Test(description="To verify that Application Closes by tapping on \"OK\" button of \"Permission Required\" Dialog.")
	public void SSQPad_09() throws Exception{
    	Thread.sleep(4000);
    	ExtentTestManager.getTest().log(Status.INFO, "<b>Step-1:</b> Tap on \"Deny\" button from \"Location\" access permission dialog box.");
    	driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_deny_Location)).click();
    	Thread.sleep(2000);
    	ExtentTestManager.getTest().log(Status.INFO, "<b>Step-2:</b> Tap on \"Deny\" button from \"Photos, Media and Files\" access permission dialog box.");
    	driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_deny_Location)).click();
    	Thread.sleep(2000);
    	ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Tap on \"Ok\" button from \"Permission\" dialog box.");
    	driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Ok_permission)).click();
    	ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Tap on \"Deny\" button from \"Location\" access permission dialog box.");
    	driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_deny_Location)).click();
    	Thread.sleep(2000);
    	ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Deny\" button from \"Photos, Media and Files\" access permission dialog box.");
    	driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_deny_Location)).click();
    	Thread.sleep(2000);
    	ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Ok\" button from \"Permission Required\" dialog.");
    	driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Ok_permission)).click();
    	Thread.sleep(2000);
        ExtentTestManager.getTest().log(Status.INFO, "<b>Result:</b> Application should get closed by tapping on \"Ok\" button from \"Permission Required\" dialog.");
   	    Thread.sleep(4000);
   	    ApplicationState aaa = driver.queryAppState("com.IGiS.QPadSS");
   	    System.out.println(aaa);
   	    String ss=aaa.toString();  
   	    Assert.assertEquals(ss, "RUNNING_IN_BACKGROUND");
    }
    
    @Test(description="To verify that user is allowed to access Sign in page.")
	public void SSQPad_13() throws Exception{
    	Thread.sleep(4000);
    	ExtentTestManager.getTest().log(Status.INFO, "<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
    	driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
    	Thread.sleep(2000);
    	ExtentTestManager.getTest().log(Status.INFO, "<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
    	driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
    	Thread.sleep(2000);
        ExtentTestManager.getTest().log(Status.INFO, "<b>Result:</b> 1) User should be able to allow \"Photos, media and files\" permission.\r\n"
       		+ "2) User should get \"Sign in\" page open with,\r\n"
       		+ "User ID text bar\r\n"
     		+ "Password text bar\r\n"
        		+ "\"Sign in \" button.");
   	    Thread.sleep(4000);
	    Assert.assertEquals(true, driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/main_toolbar")).isDisplayed() );	 	
    }
    
    @Test(description="To verify that user can not  login without entering credentials.")
	public void SSQPad_14() throws Exception{
    	Thread.sleep(4000);
    	//ExtentTestManager.getTest().log(Status.INFO, "<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
    	driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
    	Thread.sleep(2000);
    //	ExtentTestManager.getTest().log(Status.INFO, "<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
    	driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
    	Thread.sleep(2000);
    //	ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Tap on \"Sign in\" button.");
    	driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
    	Thread.sleep(2000);
    //   ExtentTestManager.getTest().log(Status.INFO, "<b>Result:</b> User should get validation message of \"Please Enter User ID\" at User ID text bar.");
    	MobileElement el = (MobileElement) driver.findElement(MobileBy.id("com.IGiS.QPadSS:id/edt_username"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Object o = js.executeScript("mobile: backdoor", ImmutableMap.of("target", "element", "elementId", el.getId(), "methods", Arrays.asList(ImmutableMap.of("name", "getError"))));
        System.out.println(String.valueOf(o)); 
    }
    
    @Test(description="To verify password visibility toggle functionality.")
	public void SSQPad_15(Method method) throws Exception{
    	Thread.sleep(4000);
    	//ExtentTestManager.getTest().log(Status.INFO, "<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
    	driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
    	Thread.sleep(2000);
    //	ExtentTestManager.getTest().log(Status.INFO, "<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
    	driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
    	Thread.sleep(2000);
    //	ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
    	driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
    	Thread.sleep(2000);
    //	ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
    	driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
    	Thread.sleep(2000);
    //	ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Show password\" (eye) icon from \"Password\" text bar.");
    	driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_toggle_Passwoprd)).click();
    	Thread.sleep(2000);
  //      ExtentTestManager.getTest().log(Status.INFO, "<b>Result:</b> User should be able to read entered password.");
        Assert.assertEquals("false", driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).getAttribute("password"));
    //    ll.Screenshot(driver, i ,method.getName()+"_01" );
 //       ExtentTestManager.getTest().log(Status.INFO, "<b>Step-6:</b> Tap on \"Hide password\" (eye) icon from \"Password\" text bar.");
    	driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_toggle_Passwoprd)).click();
    	Thread.sleep(2000);
  //      ExtentTestManager.getTest().log(Status.INFO, "<b>Result:</b> User should not be able to read entered password.");
        Assert.assertEquals("true", driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).getAttribute("password"));	  
    }
    
    @Test(description="To verify that user get warning dialog for sign in without internet.")
	public void SSQPad_16() throws Exception{
    	Thread.sleep(4000);
    	//ExtentTestManager.getTest().log(Status.INFO, "<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
    	driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
    	Thread.sleep(2000);
    //	ExtentTestManager.getTest().log(Status.INFO, "<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
    	driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
    	Thread.sleep(2000);
    //	ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at \"User ID\" text bar.");
    	driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
    	Thread.sleep(2000);
    //	ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
    	driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
    	Thread.sleep(2000);
    //	ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
    	ConnectionState state = driver.setConnection(new
    			ConnectionStateBuilder().withDataDisabled().build());
    	ConnectionState state1 = driver.setConnection(new
    			ConnectionStateBuilder().withWiFiDisabled().build());
    	Thread.sleep(2000);
    	driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
    	Thread.sleep(2000);	 
   // 	ExtentTestManager.getTest().log(Status.INFO, "<b>Result:</b> User should get dialog open for \"Please check your internet connection\" and \"Ok\" button.");
        Assert.assertEquals("Please check your Internet connection.", driver.findElement(MobileBy.className("android.widget.TextView")).getText());
        
        ConnectionState state2 = driver.setConnection(new
    			ConnectionStateBuilder().withDataEnabled().build());
    	ConnectionState state3 = driver.setConnection(new
    			ConnectionStateBuilder().withWiFiEnabled().build());
    	Thread.sleep(2000);
    }
    
    @Test(description="To verify that user can not login with wrong Credentials.")
	public void SSQPad_17() throws Exception{
    	Thread.sleep(4000);
    	//ExtentTestManager.getTest().log(Status.INFO, "<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
    	driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
    	Thread.sleep(2000);
    //	ExtentTestManager.getTest().log(Status.INFO, "<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
    	driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
    	Thread.sleep(2000);
    //	ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter invalid user ID at "User ID" text bar.");
    	driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_U178");
    	Thread.sleep(2000);
    //	ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter invalid password at Password text bar.");
    	driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sFEgl_igis");
    	Thread.sleep(2000);
    //	ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
    	driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
    	Thread.sleep(2000);	 
    //	ExtentTestManager.getTest().log(Status.INFO, "<b>Result:</b> 1) User should get toast message of \"Invalid User ID or Password\".\r\n"
    //			+ "2) User should not get signed in.");
        Assert.assertEquals("Please check your Internet connection.", driver.findElement(MobileBy.className("android.widget.TextView")).getText());
        
    }
    
    @Test(description="To verify that user can not login if can not connect to server or server is off.")
	public void SSQPad_18() throws Exception{
    	Thread.sleep(4000);
    	//ExtentTestManager.getTest().log(Status.INFO, "<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
    	driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
    	Thread.sleep(2000);
    //	ExtentTestManager.getTest().log(Status.INFO, "<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
    	driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
    	Thread.sleep(2000);
    //	ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at "User ID" text bar.");
    	driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
    	Thread.sleep(2000);
    //	ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
    	driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
    	Thread.sleep(2000);
    //	ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
    	driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
    	Thread.sleep(61000);	 
    //	ExtentTestManager.getTest().log(Status.INFO, "<b>Result:</b> 1) User should get toast message of \"Unable to connect to server\".\r\n"
    	//		+ "2) User should not get signed in.");
        Assert.assertEquals("Please check your Internet connection.", driver.findElement(MobileBy.className("android.widget.TextView")).getText());
        
    }
    
    @Test(description="To verify that user can login successfully.")
	public void SSQPad_19() throws Exception{
    	Thread.sleep(4000);
    	//ExtentTestManager.getTest().log(Status.INFO, "<b>Step-1:</b> Tap on \"Allow\" button from \"Location\" access permission dialog box.");
    	driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
    	Thread.sleep(2000);
    //	ExtentTestManager.getTest().log(Status.INFO, "<b>Step-2:</b> Tap on \"Allow\" button from \"Photos, Media and Files\" access permission dialog box.");
    	driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_allow_Location)).click();
    	Thread.sleep(2000);
    //	ExtentTestManager.getTest().log(Status.INFO, "<b>Step-3:</b> Enter user ID at "User ID" text bar.");
    	driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_User_Id)).sendKeys("SGL_PSU178");
    	Thread.sleep(2000);
    //	ExtentTestManager.getTest().log(Status.INFO, "<b>Step-4:</b> Enter password at Password text bar.");
    	driver.findElement(MobileBy.id(SurveySolution_Testcase_R.txt_Password)).sendKeys("sgl_igis");
    	Thread.sleep(2000);
    //	ExtentTestManager.getTest().log(Status.INFO, "<b>Step-5:</b> Tap on \"Sign in\" button.");
    	driver.findElement(MobileBy.id(SurveySolution_Testcase_R.btn_Sign_In)).click();
    	Thread.sleep(61000);	 
		/*
		 * ExtentTestManager.getTest().log(Status.INFO,
		 * "<b>Result:</b> 1) User should get dialog of \"Signing in…\"\r\n" +
		 * "2) User should get logged in.\r\n" +
		 * "3) User should get \"Loading Project\" dialog till loading projects.\r\n" +
		 * "4) User should get Home page of SSQpad application.");
		 */
        Assert.assertEquals("Please check your Internet connection.", driver.findElement(MobileBy.className("android.widget.TextView")).getText());
        
    }
    
}
