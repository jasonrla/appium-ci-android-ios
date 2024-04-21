package tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.json.JSONObject;
import utils.Utils;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;

public class TestBase {

    public static AppiumDriver driver;

    public static void Android_setUp(JSONObject cap, String apiLevel, String androidAppiumPort) throws IOException {

        //String apiLevel = System.getProperty("api.level");
        //String androidAppiumPort = System.getProperty("appiumPort");

        UiAutomator2Options opt = new UiAutomator2Options();
        opt.setPlatformName((String) cap.get("platformName"));
        opt.setPlatformVersion(Utils.getPlatformVersion(apiLevel));
        opt.setDeviceName((String) cap.get("deviceName"));
        opt.setAutomationName((String) cap.get("automationName"));
        if(Integer.parseInt(apiLevel)<30){
            opt.setCapability("unicodeKeyboard", cap.get("unicodeKeyboard"));
            opt.setCapability("resetKeyboard", cap.get("resetKeyboard"));
        }
        opt.setUiautomator2ServerInstallTimeout(Duration.ofSeconds(cap.getInt("uiautomator2ServerInstallTimeout")));
        opt.setAdbExecTimeout(Duration.ofSeconds(cap.getInt("adbExecTimeout")));
        opt.setApp(System.getProperty("user.dir") + cap.get("app"));

        driver = new AndroidDriver(new URL("http://localhost:"+ androidAppiumPort +"/"), opt);
    }


    public static void iOS_setUp(JSONObject cap, String deviceName, String iOSAppiumPort, String iOSHeadless) throws IOException {
//        String deviceName = System.getProperty("device.name");
//        String iOSAppiumPort = System.getProperty("appium.port");
//        String iOSHeadless = System.getProperty("headless");
//
        XCUITestOptions opt = new XCUITestOptions();
        opt.setPlatformName((String) cap.get("platformName"));
        opt.setDeviceName(deviceName);
        opt.setAutomationName((String) cap.get("automationName"));
        opt.setIsHeadless(Boolean.parseBoolean(iOSHeadless));
        opt.setWdaLaunchTimeout(Duration.ofSeconds(cap.getInt("wdaLaunchTimeout")));
        opt.setApp(System.getProperty("user.dir") + cap.get("app"));

        driver = new IOSDriver(new URL("http://localhost:"+ iOSAppiumPort +"/"), opt);
    }

    public static void tearDown() {
        if (null != driver) {
            driver.quit();
        }
    }
}