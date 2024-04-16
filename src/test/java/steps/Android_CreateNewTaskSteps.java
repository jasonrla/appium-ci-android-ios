package steps;

import pageObjects.android.CreateNewTaskPage;
import pageObjects.android.TasksListPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import tests.TestBase;
import java.net.MalformedURLException;

public class Android_CreateNewTaskSteps extends TestBase {

    CreateNewTaskPage createNewTaskPage;
    TasksListPage tasksListPage;

    public Android_CreateNewTaskSteps() {
        tasksListPage = new TasksListPage(driver);
        createNewTaskPage = new CreateNewTaskPage(driver);
    }

//    @Given("Click Add new Task")
//    public void clickAddNewTask() throws MalformedURLException {
//        tasksListPage.clickAddTaskBtn();
//    }
//
//    @Given("Enter TaskName")
//    public void enterTaskName() {
//        createNewTaskPage.enterTitle("Task 1");
//    }
//
//    @Given("Enter TaskDesc")
//    public void enterTaskDesc() {
//        createNewTaskPage.enterTaskDescription("Desc 1");
//    }
//
//    @When("Click Save")
//    public void clickSave() {
//        createNewTaskPage.clickSaveBtn();
//    }

//    @Then("Task added successfully")
//    public void taskAddedSuccessfully() {
//    }

//    @Given("the user is on the {string} screen")
//    public void theUserIsOnTheScreen(String toolbarText) {
//        Assert.assertTrue(tasksListPage.isTasksListPageDisplayed());
//        Assert.assertEquals(tasksListPage.getToolbarTitle(), toolbarText);
//        System.out.printf("The user is on the %s screen\n", toolbarText);
//    }

//    @When("the user clicks the plus button to add a task")
//    public void theUserClicksThePlusButtonToAddATask() {
//        tasksListPage.clickAddTaskBtn();
//    }

    @And("enters {string} in the Title field")
    public void entersInTheTitleField(String title) {
        createNewTaskPage.enterTitle(title);
        Assert.assertEquals(createNewTaskPage.getTaskTitle(), title);
    }

    @And("selects {string} as the start date")
    public void selectsAsTheStartDate(String date) {
        if(date.equals("Today")) {
            Assert.assertEquals(createNewTaskPage.getStartDateValue(), date);
        } else if (date.equals("Tomorrow")){
            createNewTaskPage.clickStartDateOption();
            createNewTaskPage.selectTomorrowDate();
            createNewTaskPage.clickOnOKButton();
            Assert.assertEquals(createNewTaskPage.getStartDateFormValue(), date);
        } else {
            createNewTaskPage.clickStartDateOption();
            createNewTaskPage.selectDate(date);
            createNewTaskPage.clickOnOKButton();
            Assert.assertEquals(createNewTaskPage.getStartDateFormValue(), createNewTaskPage.getDateValue(date));
        }
    }

    @And("selects {string} as the due date")
    public void selectsAsTheDueDate(String date) {
        if(!date.equals("None" )){
            createNewTaskPage.clickDueDate();
            if(date.equals("Today")) {
                createNewTaskPage.selectCurrentDate();
            } else if (date.equals("Tomorrow")){
                createNewTaskPage.selectTomorrowDate();
            } else {
                createNewTaskPage.selectDate(date);
            }
            createNewTaskPage.clickOnOKButton();
        }

        Assert.assertEquals(createNewTaskPage.getDueDateFormValue(), createNewTaskPage.getDateValue(date));

    }

    @And("enters {string} in the Note field")
    public void entersInTheNoteFieldOptional(String note) {
        createNewTaskPage.enterTaskDescription(note);
    }

    @And("chooses {string} from the tag list")
    public void choosesFromTheTagList(String value) {
        createNewTaskPage.clickOnTagOption(value);
        Assert.assertTrue(createNewTaskPage.isModalDisplayed());
        createNewTaskPage.selectTag(value);
        createNewTaskPage.clickOnOKButton();
    }

    @And("selects {string} as the priority")
    public void selectsAsThePriority(String value) {
        createNewTaskPage.clickOnPriorityOption(value);
        Assert.assertTrue(createNewTaskPage.isModalDisplayed());
        createNewTaskPage.selectPriority(value);
        createNewTaskPage.clickOnOKButton();
    }



    @Then("the new task should appear on the correct {string} screen")
    public void theNewTaskShouldAppearOnTheCorrectScreen(String startDate) {
        if(startDate.equals("Today")){
            Assert.assertEquals(tasksListPage.getToolbarTitle(), startDate);
            Assert.assertTrue(tasksListPage.isFirstItemDisplayed());
        } else if (startDate.equals("Tomorrow")){
            tasksListPage.clickOnNavDrawerButton();
            tasksListPage.clickOnNavOption(startDate);
            Assert.assertTrue(tasksListPage.isFirstItemDisplayed());
        } else {
            tasksListPage.clickOnNavDrawerButton();
            tasksListPage.clickOnNavOption("Scheduled");
            Assert.assertTrue(tasksListPage.isFirstItemDisplayed());
        }
    }

    @And("the task should display {string}, {string}, and {string} \\(last selected tag)")
    public void theTaskShouldDisplayAndLastSelectedTag(String titleText, String startDateText, String tagText) {
        Assert.assertTrue(tasksListPage.isTitleCorrect(titleText));
        Assert.assertTrue(tasksListPage.isStartDateCorrect(startDateText));
        Assert.assertTrue(tasksListPage.isTagCorrect(tagText));

    }

    @Given("the user is on the {string} screen")
    public void theUserIsOnTheScreen(String toolbarText) {
        Assert.assertTrue(tasksListPage.isTasksListPageDisplayed());
        Assert.assertEquals(tasksListPage.getToolbarTitle(), toolbarText);
        System.out.printf("The user is on the %s screen\n", toolbarText);
    }

}
