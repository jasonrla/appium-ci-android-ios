package steps;

import io.cucumber.java.bs.A;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import pageObjects.ios.CalendarPage;
import pageObjects.ios.CreateNewTaskPage;
import pageObjects.ios.TasksListPage;
import tests.TestBase;

public class IOS_CreateNewTaskSteps extends TestBase {

    CreateNewTaskPage createNewTaskPage;
    TasksListPage tasksListPage;
    CalendarPage calendarPage;

    public IOS_CreateNewTaskSteps() {
        tasksListPage = new TasksListPage(driver);
        createNewTaskPage = new CreateNewTaskPage(driver);
        calendarPage = new CalendarPage(driver);
    }

    @Given("the user is on the Daily Tasks screen")
    public void theUserIsOnTheDailyTasksScreen() {
        Assert.assertTrue(tasksListPage.isDailyPageOpenned());
    }

    @And("enters a dueDate as current date")
    public void entersADueDateAsCurrentDate() {
        createNewTaskPage.enterDueDate(createNewTaskPage.getCurrentDate());
    }

    @And("selects a dueTime")
    public void selectsADueTime() {
        createNewTaskPage.enterDueTime("12:00 PM");
    }

    @And("selects {string} as the description field")
    public void selectsAsTheDescriptionField(String arg0) {
        createNewTaskPage.enterTaskDescription(arg0);
    }


    @And("enters a {string} in the Title field")
    public void entersAInTheTitleField(String title) {
        createNewTaskPage.enterTitle(title);
    }


    @Then("the new task should appear on the DailyTasks screen and display {string}")
    public void theNewTaskShouldAppearOnTheDailyTasksScreenAndDisplayAnd(String title) throws InterruptedException {
        Assert.assertTrue(tasksListPage.isTaskTitleDisplayed(title));
    }

    @And("enters a {string} in the DueDate field")
    public void entersAInTheDueDateField(String dueDate) {
        createNewTaskPage.enterDueDate(dueDate);
    }

    @Then("the new task {string} should not appear on the DailyTasks screen")
    public void theNewTaskShouldNotAppearOnTheDailyTasksScreen(String title) {
        Assert.assertFalse(tasksListPage.isTaskTitleDisplayed(title));
    }

    @And("the new task {string} should appear on the Schedule screen on {string}")
    public void theNewTaskShouldAppearOnTheScheduleScreenOn(String title, String dueDate) {
        tasksListPage.clickOnCalendarButton();
        calendarPage.selectDate(dueDate);
        Assert.assertTrue(calendarPage.isTaskTitleDisplayed(title));
    }
}
