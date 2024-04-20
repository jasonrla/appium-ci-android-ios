package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.android.CreateNewTaskPage;
import pageObjects.android.TasksListPage;
import tests.TestBase;

import java.net.MalformedURLException;

public class CommonSteps extends TestBase {

    CreateNewTaskPage createNewTaskPage;
    TasksListPage tasksListPage;

    public CommonSteps() {
        tasksListPage = new TasksListPage(driver);
        createNewTaskPage = new CreateNewTaskPage(driver);
    }

    @Given("Click Add new Task")
    public void clickAddNewTask() throws MalformedURLException {
        tasksListPage.clickAddTaskBtn();
    }

    @Given("Enter TaskName")
    public void enterTaskName() {
        createNewTaskPage.enterTitle("Task 1");
    }

    @Given("Enter TaskDesc")
    public void enterTaskDesc() {
        createNewTaskPage.enterTaskDescription("Desc 1");
    }

    @When("Click Save")
    public void clickSave() {
        createNewTaskPage.clickSaveBtn();
    }

    @And("the user clicks on the Save button")
    public void theUserClicksOnTheSaveButton() {
        createNewTaskPage.clickOnSaveButton();
    }


    @When("the user clicks the plus button to add a task")
    public void theUserClicksThePlusButtonToAddATask() {
        tasksListPage.clickAddTaskBtn();
    }

    @Then("Task added successfully")
    public void taskAddedSuccessfully() {
    }
}
