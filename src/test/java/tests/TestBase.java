package tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class TestBase {

    public static AppiumDriver driver;

    public void setup() throws MalformedURLException {
        String platform = System.getProperty("platform", "unknown");

        if (platform.equalsIgnoreCase("ios")) {
            iOS_setUp();
        } else if (platform.equalsIgnoreCase("android")) {
            Android_setUp();
        } else {
            iOS_setUp();
            //throw new IllegalStateException("No valid active profile. Must be 'iOS' or 'android'.");
        }
    }

    public static void Android_setUp() throws MalformedURLException {

        String apiLevel = System.getProperty("api.level");
        if (apiLevel == null) { apiLevel = "34";}
        System.out.printf("API Level: %s\n", apiLevel);

        String appiumPort = System.getProperty("appiumPort");
        if (appiumPort == null) { appiumPort = "4723"; }
        System.out.printf("Appium port: %s\n", appiumPort);

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", getPlatformVersion(apiLevel));
        capabilities.setCapability("deviceName", "Android Emulator");
        capabilities.setCapability("automationName", "uiautomator2");
        if(Integer.valueOf(apiLevel)<30){
            capabilities.setCapability("unicodeKeyboard", true);
            capabilities.setCapability("resetKeyboard", true);
        }
        capabilities.setCapability("uiautomator2ServerInstallTimeout", 60000);
        capabilities.setCapability("adbExecTimeout", 60000);
        capabilities.setCapability("app",
                System.getProperty("user.dir") + "/apps/ToDo.apk");

        driver = new AndroidDriver(new URL("http://localhost:"+appiumPort+"/"), capabilities);
    }


    public static void iOS_setUp() throws MalformedURLException {

        String deviceName = System.getProperty("device.name");
        if (deviceName == null) { deviceName = "iPhone 15";}
        System.out.printf("Device name: %s\n", deviceName);

        String appiumPort = System.getProperty("appium.port");
        if (appiumPort == null) { appiumPort = "4723"; }
        System.out.printf("Appium port: %s\n", appiumPort);

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("deviceName", deviceName);
        capabilities.setCapability("automationName","XCUITest");
        capabilities.setCapability("isHeadless",true);
        capabilities.setCapability("wdaLaunchTimeout", 180000);
        capabilities.setCapability("app",
                System.getProperty("user.dir") + "/apps/DailyCheck.zip");
        driver = new IOSDriver(new URL("http://localhost:"+appiumPort+"/"), capabilities);
    }

    public static String getPlatformVersion(String apiLevel) {
        switch (apiLevel) {
            case "21":
                return "5.0";
            case "22":
                return "5.1";
            case "23":
                return "6.0";
            case "24":
                return "7.0";
            case "25":
                return "7.1";
            case "26":
                return "8.0";
            case "27":
                return "8.1";
            case "28":
                return "9.0";
            case "29":
                return "10.0";
            case "30":
                return "11.0";
            case "31":
                return "12.0";
            case "32":
                return "12L";
            case "33":
                return "13.0";
            case "34":
                return "14.0";
            case "35":
                return "15.0";
            default:
                throw new IllegalArgumentException("API Level " + apiLevel + " is not supported");
        }
    }

    public static void tearDown() {
        if (null != driver) {
            driver.quit();
        }
    }
}