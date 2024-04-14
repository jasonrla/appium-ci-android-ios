package PageObjects;

import io.appium.java_client.AppiumDriver;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.cucumber.java.bs.A;
import io.cucumber.java.en.And;
import org.openqa.selenium.WebElement;

public class TasksListPage extends PageBase {
    public TasksListPage(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    @AndroidFindBy(id = "fab")
    @iOSXCUITFindBy(accessibility = "plus.circle")
    WebElement addTaskBtn;

    @AndroidFindBy(accessibility = "More options")
    WebElement moreOptionsBtn;

    @AndroidFindBy(accessibility = "Open navigation drawer")
    WebElement openNavDrawerBtn;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Today']")
    WebElement pageText;

    @AndroidFindBy(id = "textViewNothing")
    WebElement noTaskText;

    @AndroidFindBy(id = "layoutNothing")
    WebElement taskLayout;



    public void clickAddTaskBtn() {
        click(addTaskBtn);
    }

}
