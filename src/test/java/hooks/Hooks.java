package hooks;

import io.appium.java_client.AppiumDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;

import java.net.MalformedURLException;

import static tests.TestBase.setup;
import static tests.TestBase.tearDown;

public class Hooks{

    @Before
    public void beforeScenario() throws MalformedURLException {
        setup();
    }

    @After
    public void afterScenario() {
        tearDown();
    }
}
