package pageObjects.android;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import utils.UtilFunctions;

public class TasksListPage extends PageBase {
    public TasksListPage(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    @AndroidFindBy(id = "fab")
    @iOSXCUITFindBy(accessibility = "plus.circle")
    WebElement addTaskBtn;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Today']")
    WebElement pageText;

    @AndroidFindBy(id = "textViewNothing")
    WebElement noTaskText;

    @AndroidFindBy(id = "layoutNothing")
    WebElement taskLayout;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@resource-id='com.jeffprod.todo:id/toolbar']/android.widget.TextView")
    WebElement toolbarTitle;

    @AndroidFindBy(id = "android:id/content")
    WebElement frameLayout;

    @AndroidFindBy(accessibility = "Open navigation drawer")
    WebElement navDrawerButton;

    @AndroidFindBy(id = "android:id/button2")
    WebElement noThanksButton;

    @AndroidFindBy(id = "com.jeffprod.todo:id/alertTitle")
    WebElement alertTitle;

    String navOption = "//android.widget.CheckedTextView[@resource-id='com.jeffprod.todo:id/design_menu_item_text' and @text='%s']";

    String taskItemElement = "//android.widget.ListView[@resource-id='com.jeffprod.todo:id/lv']/android.widget.RelativeLayout[%s]";
    String titleTaskElement = "(//android.widget.TextView[@resource-id='com.jeffprod.todo:id/textViewListView'])[%s]";
    String startDateTaskElement = "(//android.widget.TextView[@resource-id='com.jeffprod.todo:id/textViewDateStart'])[%s]";
    String tagTaskElement = "(//android.widget.TextView[@resource-id='com.jeffprod.todo:id/textViewTag'])[%s]";

    public boolean isAlertDisplayed() {
        return isElementDisplayed(alertTitle);
    }

    public void clickNoThanksButton() {
        click(noThanksButton);
    }

    public void handleRateThisAppAlert() {
        if(isAlertDisplayed()) {
            clickNoThanksButton();
        }
    }

    public void clickAddTaskBtn() {
        click(addTaskBtn);
    }

    public boolean isTasksListPageDisplayed() {
        return isElementDisplayed(frameLayout);
    }

    public String getToolbarTitle() {
        return getAttribute(toolbarTitle, "text");
    }

    public boolean isFirstItemDisplayed() {
        WebElement element = UtilFunctions.createElement(driver, taskItemElement, "1");
        return isElementDisplayed(element);
    }

    public boolean isTitleCorrect(String titleText) {
        return UtilFunctions.getFromTaskItemElement(driver, titleTaskElement, "1").equals(titleText);
    }

    public boolean isStartDateCorrect(String startDateText) {
        String newDate = startDateText;
        if(!startDateText.equals("Today") && !startDateText.equals("Tomorrow") && !startDateText.equals("None")) {
            newDate = UtilFunctions.formatDateString(startDateText, "EEE d MMM");
        }

        String startDate = UtilFunctions.getFromTaskItemElement(driver, startDateTaskElement, "1");
        String startDateFormatted = startDate.substring(0, startDate.length() - 2).trim();
        return startDateFormatted.equals(newDate);
    }

    public boolean isTagCorrect(String tagText) {
        return UtilFunctions.getFromTaskItemElement(driver, tagTaskElement, "1").equals(tagText);
    }

    public void clickOnNavDrawerButton() {
        click(navDrawerButton);
    }

    public void clickOnNavOption(String screen) {
        WebElement element = UtilFunctions.createElement(driver, navOption, screen);
        click(element);
    }
}
