package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.json.JSONObject;
import utils.Utils;
import java.io.IOException;
import static tests.TestBase.*;

public class Hooks{

    @Before
    public void beforeScenario() throws IOException {
        String platform = System.getProperty("platform", "android");
        if (platform.equalsIgnoreCase("ios")) {
            setUpIOS();
        } else if (platform.equalsIgnoreCase("android")) {
            setUpAndroid();
        } else {
            throw new IllegalStateException("No valid active profile. Must be 'iOS' or 'android'.");
        }
    }

    private void setUpIOS() throws IOException {
        JSONObject cap = Utils.readCapabilities("ios");
        String deviceName = System.getProperty("device.name", "iPhone 15");
        String iOSAppiumPort = System.getProperty("appium.port", "4723");
        String iOSHeadless = System.getProperty("headless", "false");
        iOS_setUp(cap, deviceName, iOSAppiumPort, iOSHeadless);
    }

    private void setUpAndroid() throws IOException {
        JSONObject cap = Utils.readCapabilities("android");
        String apiLevel = System.getProperty("api.level", "34");
        String androidAppiumPort = System.getProperty("appiumPort", "4723");
        Android_setUp(cap, apiLevel, androidAppiumPort);
    }

    @After
    public void afterScenario() {
        tearDown();
    }
}