package PageObjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

public class CreateTaskPage extends PageBase {
    public CreateTaskPage(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    @AndroidFindBy(id = "editTextTitre")
    @iOSXCUITFindBy(iOSNsPredicate = "value == 'Title'")
    WebElement taskNameTxt;

    @AndroidFindBy(id = "editTextNote")
    @iOSXCUITFindBy(iOSNsPredicate = "value == 'Description'")
    WebElement taskDescTxt;

    @AndroidFindBy(id = "editTextTag")
    WebElement taskTagTxt;

    @AndroidFindBy(id = "action_save")
    @iOSXCUITFindBy(accessibility = "Save")
    WebElement saveBtn;

    @AndroidFindBy(id = "buttonStartDate")
    WebElement startDateBtn;

    @AndroidFindBy(id = "buttonDeadline")
    WebElement dueDateBtn;

    @AndroidFindBy(id = "imageButtonBackStartDate")
    WebElement backStartDateBtn;

    @AndroidFindBy(id = "imageButtonBackDeadline")
    WebElement backDueDateBtn;

    @AndroidFindBy(id = "editTextPriority")
    WebElement priorityTxt;

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


    public void enterTaskName(String taskName) {
        clear(taskNameTxt);
        sendText(taskNameTxt, taskName);
    }

    public void enterTaskDesc(String descText) {
        clear(taskDescTxt);
        sendText(taskDescTxt, descText);
    }

    public void clickSaveBtn() {
        click(saveBtn);
    }


}
