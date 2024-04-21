package pageObjects.ios;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import utils.UtilFunctions;

public class CreateNewTaskPage extends PageBase {

    public CreateNewTaskPage(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    @iOSXCUITFindBy(iOSNsPredicate = "value == 'Title'")
    WebElement titleElement;

    @iOSXCUITFindBy(iOSNsPredicate = "value == 'Description'")
    WebElement descriptionElement;

    @iOSXCUITFindBy(accessibility = "Save")
    WebElement saveButton;
    //XCUIElementTypeStaticText[@name="29"]
    String dueDateString = "//XCUIElementTypeTextField[@value='%s']";

    @iOSXCUITFindBy(iOSNsPredicate = "value == 'Due Time'")
    WebElement dueTimeElement;

    @iOSXCUITFindBy(accessibility = "Scheduled")
    WebElement scheduledOptionElement;

    @iOSXCUITFindBy(accessibility = "Anytime")
    WebElement anytimeOptionElement;

    public void enterText(WebElement element, String text) {
        clear(element);
        sendText(element, text);
    }

    public void enterTitle(String taskTittle) {
        enterText(titleElement, taskTittle);
    }

    public String getCurrentDate() {
        return UtilFunctions.getCurrentDate("yyyy-MM-dd");
    }

    public void enterDueDate(String date) {
        //String currentDate = UtilFunctions.getCurrentDate("yyyy-MM-dd");
        WebElement dueDateElement = UtilFunctions.createElement(driver, dueDateString, getCurrentDate());
        clear(dueDateElement);
        sendText(dueDateElement, date);
    }

    public void enterDueTime(String time) {
        sendText(dueTimeElement, time);
    }

    public void enterTaskDescription(String description) {
        sendText(descriptionElement, description);
    }
}
