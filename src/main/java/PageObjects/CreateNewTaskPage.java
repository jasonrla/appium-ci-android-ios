package PageObjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CreateNewTaskPage extends PageBase {
    public CreateNewTaskPage(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    @AndroidFindBy(id = "editTextTitre")
    @iOSXCUITFindBy(iOSNsPredicate = "value == 'Title'")
    WebElement titleElement;

    @AndroidFindBy(id = "editTextNote")
    @iOSXCUITFindBy(iOSNsPredicate = "value == 'Description'")
    WebElement descriptionElement;

    @AndroidFindBy(id = "editTextTag")
    WebElement taskTagElement;

    @AndroidFindBy(id = "action_save")
    @iOSXCUITFindBy(accessibility = "Save")
    WebElement saveButton;

    @AndroidFindBy(id = "buttonStartDate")
    WebElement startDateElement;

    @AndroidFindBy(id = "buttonDeadline")
    WebElement dueDateElement;

    @AndroidFindBy(id = "imageButtonBackStartDate")
    WebElement backStartDateButton;

    @AndroidFindBy(id = "imageButtonBackDeadline")
    WebElement backDueDateButton;

    @AndroidFindBy(id = "editTextPriority")
    WebElement priorityElement;

    @AndroidFindBy(id = "android:id/date_picker_header_year")
    WebElement yearTxt;

    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='android:id/text1' and @text='2024']")
    WebElement defaultYearTxt;

    @AndroidFindBy(id = "android:id/date_picker_header_date")
    WebElement dateModalElement;

    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='android:id/text1' and @text='2033']")
    WebElement desiredYearElement;

    @AndroidFindBy(id = "android:id/button1")
    WebElement okModalBtn;

    @AndroidFindBy(id = "android:id/parentPanel")
    WebElement modalElement;

    @AndroidFindBy(accessibility = "Previous month")
    WebElement previousMonthButton;

    @AndroidFindBy(accessibility = "Next month")
    WebElement nextMonthButton;

    @AndroidFindBy(id = "android:id/date_picker_header_date")
    WebElement headerDateTextElement;

    @AndroidFindBy(id = "android:id/date_picker_header_year")
    WebElement headerYearTextElement;

    @AndroidFindBy(xpath = "(//android.view.View)[2]")
    WebElement calendarFirstDayElement;

    @AndroidFindBy(id = "android:id/date_picker_year_picker")
    WebElement yearPickerModal;

    String yearElement = "//android.widget.TextView[@resource-id='android:id/text1' and @text='%s']";

    String tagElementOption = "//android.widget.CheckedTextView[@resource-id='android:id/text1' and @text='%s']";

    String priorityElementOption = "//android.widget.CheckedTextView[@resource-id='android:id/text1' and @text='%s']";

    String dayTextElement = "//android.view.View[@content-desc='%s']";

    public WebElement createTagElement(String elementString, String text) {
        String xpath = String.format(elementString, text);
        return driver.findElement(By.xpath(xpath));
    }

    public boolean isModalDisplayed(){
        return isElementDisplayed(modalElement);
    }

    public void enterTitle(String taskTittle) {
        System.out.printf("Entering task title: %s\n", taskTittle);
        enterText(titleElement, taskTittle);
    }

    public void enterTaskDescription(String descText) {

        System.out.printf("Entering task description: %s\n", descText);
        enterText(descriptionElement, descText);
    }

    public void clickOnTagOption(String value) {
        click(taskTagElement);
    }

    public void selectTag(String text){
        WebElement element = createTagElement(tagElementOption, text);
        element.click();
    }

    public void enterText(WebElement element, String text) {
        clear(element);
        sendText(element, text);
    }

    public void clickSaveBtn() {
        click(saveButton);
    }

    public String getTaskTitle() {
        return getAttribute(titleElement, "text");
    }

    public String getStartDateValue(){
        return getAttribute(startDateElement, "text");
    }

    public String getDueDateFormValue(){
        return getAttribute(dueDateElement, "text");
    }

    public String getStartDateFormValue(){
        return getAttribute(startDateElement, "text");
    }

    public String getDateValue(String date){
        if (!date.equals("None") && !date.equals("Today") && !date.equals("Tomorrow")) {
            System.out.println(formatDateString(date, "EEE d MMM"));
            return formatDateString(date, "EEE d MMM");
        } else {
            return date;
        }
    }

    public void clickStartDateOption(){
        click(startDateElement);
    }

    public void clickDueDate(){
        click(dueDateElement);
    }

    public void clickBackStartDate(){
        click(backStartDateButton);
    }

    public void clickBackDueDate(){
        click(backDueDateButton);
    }

    public void clickOnPriorityOption(String value) {
        click(priorityElement);
    }

    public void selectPriority(String text){
        WebElement element = createTagElement(priorityElementOption, text);
        element.click();
    }

    public void clickOnOKButton() {
        click(okModalBtn);

    }

    public void clickOnSaveButton() {
        click(saveButton);
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

    public WebElement createElement(String elementString, String text) {
        String xpath = String.format(elementString, text);
        return driver.findElement(By.xpath(xpath));
    }

    public String formatMonthString(String inputMonth) {
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("MMMM", Locale.ENGLISH);
            SimpleDateFormat outputFormat = new SimpleDateFormat("MMM", Locale.ENGLISH);
            return outputFormat.format(inputFormat.parse(inputMonth));
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid month", e);
        }
    }

    public void selectTomorrowDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        date.setTime(date.getTime() + 86400000);
        String formattedDate = sdf.format(date);
        selectDate(formattedDate);
    }

    public void selectDate(String date) {
        String formattedDate = formatDateString(date, "dd MMMM yyyy"); //04 April 2024

        String[] dateParts = formattedDate.split(" ");
        String month = dateParts[1];
        String year = dateParts[2];

        if(!headerYearTextElement.getAttribute("text").equals(year)){
            click(headerYearTextElement);
            WebElement desiredYear = createTagElement(yearElement, year);
            desiredYear.click();
        }

        while (!getAttribute( calendarFirstDayElement, "content-desc").contains(formatMonthString(month))) {
            click(nextMonthButton);
        }

        WebElement element = createElement(dayTextElement, formattedDate);
        element.click();
    }

    public void selectCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        String formattedDate = sdf.format(date);
        System.out.printf("Selecting current date: %s\n", formattedDate);
        selectDate(formattedDate);
    }
}
