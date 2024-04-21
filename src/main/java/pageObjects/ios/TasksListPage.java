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

    @iOSXCUITFindBy(accessibility = "calendar")
    WebElement calendarButton;

    @iOSXCUITFindBy(accessibility = "Edit")
    WebElement editButton;

    String removeOption = "Xpath //XCUIElementTypeButton[@name='Remove %s']";

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Delete']")
    WebElement deleteButton;

    String textCreatedElement = "//XCUIElementTypeStaticText[@name='%s']";

    public void clickAddTaskBtn() {
        click(addTaskBtn);
    }

    public boolean isTaskTitleDisplayed(String title) {
        try {
            return isElementDisplayed(UtilFunctions.createElement(driver, textCreatedElement, title));
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isDailyPageOpenned() {
        return isElementDisplayed(dailyCheckPage);
    }

    public void clickOnCalendarButton() {
        click(calendarButton);
    }

    public void clickOnEditButton() {
        click(editButton);
    }

    public void clickOnRemoveOption(String taskName) {
        click(UtilFunctions.createElement(driver, removeOption, taskName));
    }

    public void clickOnDeleteButton() {
        click(deleteButton);
    }
}
