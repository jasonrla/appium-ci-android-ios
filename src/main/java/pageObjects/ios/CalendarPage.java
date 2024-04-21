package pageObjects.ios;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.UtilFunctions;

import java.util.List;

public class CalendarPage extends PageBase {

    public CalendarPage(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    @iOSXCUITFindBy(accessibility = "Done")
    WebElement doneButton;

    @iOSXCUITFindBy(accessibility = "Week")
    WebElement weekButton;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeWindow/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeCollectionView")
    WebElement monthToSwipeElement;

    String element = "//XCUIElementTypeStaticText[@name='%s']";

    public void clickDoneButton() {
        click(doneButton);
    }

    public void clickWeekButton() {
        click(weekButton);
    }

    public boolean isMonthDisplayed(String month) {
        return isElementDisplayed(UtilFunctions.createElement(driver, element, UtilFunctions.convertStringMonthNumberToMonthName(month) + " " + UtilFunctions.getCurrentYear()));
    }

    public boolean isDayDisplayed(String day) {
        return isElementDisplayed(UtilFunctions.createElement(driver, element, UtilFunctions.convertDay(day)));
    }

    public boolean isTaskDisplayed(String task) {
        return isElementDisplayed(UtilFunctions.createElement(driver, element, task));
    }

    public void selectDate(String dueDate) {
        String monthNumber = UtilFunctions.splitStringMonthNumberFromDate(dueDate);
        String nextMonthNumber = String.valueOf(Integer.parseInt(monthNumber) + 1);
        String nextMonthName = UtilFunctions.convertStringMonthNumberToMonthName(nextMonthNumber);
        String currentYear = UtilFunctions.getCurrentYear();
        String monthYearToFind = nextMonthName + " " + currentYear;

        String elementToFind = String.format(element, monthYearToFind);

        UtilFunctions.swipeLeftUntilElementFound(driver, monthToSwipeElement, elementToFind);

        click(UtilFunctions.createElement(driver, element, UtilFunctions.splitStringDayNumberFromDate(dueDate)));

    }

    public boolean isTaskTitleDisplayed(String title) {
        return isTaskDisplayed(title);
    }

}
