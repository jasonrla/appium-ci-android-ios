package PageObjects;

import io.appium.java_client.AppiumDriver;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.cucumber.java.en.And;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@resource-id='com.jeffprod.todo:id/toolbar']/android.widget.TextView")
    WebElement toolbarTitle;

    @AndroidFindBy(id = "android:id/content")
    WebElement frameLayout;

    @AndroidFindBy(accessibility = "Open navigation drawer")
    WebElement navDrawerButton;

    String navOption = "//android.widget.CheckedTextView[@resource-id='com.jeffprod.todo:id/design_menu_item_text' and @text='%s']";

    String taskItemElement = "//android.widget.ListView[@resource-id='com.jeffprod.todo:id/lv']/android.widget.RelativeLayout[%s]";
    String titleTaskElement = "(//android.widget.TextView[@resource-id='com.jeffprod.todo:id/textViewListView'])[%s]";
    String startDateTaskElement = "(//android.widget.TextView[@resource-id='com.jeffprod.todo:id/textViewDateStart'])[%s]";
    String tagTaskElement = "(//android.widget.TextView[@resource-id='com.jeffprod.todo:id/textViewTag'])[%s]";

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
        WebElement element = createElement(taskItemElement, "1");
        return isElementDisplayed(element);
    }

    public WebElement createElement(String elementString, String text) {
        String xpath = String.format(elementString, text);
        return driver.findElement(By.xpath(xpath));
    }

    public boolean isTitleCorrect(String titleText) {
        return getFromTaskItemElement(titleTaskElement, "1").equals(titleText);
    }

    public String formatDateString(String inputDate, String pattern) {
        SimpleDateFormat inputFormat;
        if (inputDate.matches("\\d{1,2}/\\d{1,2}/\\d{4}")) {
            inputFormat = new SimpleDateFormat("d/M/yyyy");
        } else if (inputDate.matches("\\d{1,2}/\\d{1,2}/\\d{2}")) {
            inputFormat = new SimpleDateFormat("d/M/yy");
        } else {
            throw new IllegalArgumentException("Invalid date format");
        }

        try {
            Date date = inputFormat.parse(inputDate);
            SimpleDateFormat outputFormat = new SimpleDateFormat(pattern);
            return outputFormat.format(date);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid date", e);
        }
    }

    public boolean isStartDateCorrect(String startDateText) {
        String newDate = startDateText;
        if(!startDateText.equals("Today") && !startDateText.equals("Tomorrow") && !startDateText.equals("None")) {
            newDate = formatDateString(startDateText, "EEE d MMM");
        }

        String startDate = getFromTaskItemElement(startDateTaskElement, "1");
        String startDateFormatted = startDate.substring(0, startDate.length() - 2).trim();
        return startDateFormatted.equals(newDate);
    }

    public boolean isTagCorrect(String tagText) {
        return getFromTaskItemElement(tagTaskElement, "1").equals(tagText);
    }

    public String getFromTaskItemElement(String elementValue, String elementNumber) {
        WebElement element = createElement(elementValue, elementNumber);
        return element.getText();
    }

    public void clickOnNavDrawerButton() {
        click(navDrawerButton);
    }

    public void clickOnNavOption(String screen) {
        WebElement element = createElement(navOption, screen);
        click(element);
    }
}
