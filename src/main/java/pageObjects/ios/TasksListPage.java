package pageObjects.ios;

import io.appium.java_client.AppiumDriver;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import utils.UtilFunctions;

public class TasksListPage extends PageBase {

    public TasksListPage(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    @iOSXCUITFindBy(accessibility = "plus.circle")
    WebElement addTaskBtn;

    @iOSXCUITFindBy(accessibility = "DailyCheck")
    WebElement dailyCheckPage;

    @iOSXCUITFindBy(accessibility = "Passport Renewal 3")
    WebElement test1;

    @iOSXCUITFindBy(accessibility = "Check necessary documents  2")
    WebElement test2;

    String textCreatedElement = "//XCUIElementTypeStaticText[@name='%s']";

    public void clickAddTaskBtn() {
        click(addTaskBtn);
    }

    public boolean isTaskTitleDisplayed(String title) {
        return isElementDisplayed(UtilFunctions.createElement(driver, textCreatedElement, title));
    }

    public boolean isDailyPageOpenned() {
        return isElementDisplayed(dailyCheckPage);
    }

}
