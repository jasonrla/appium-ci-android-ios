package steps;

import PageObjects.CreateNewTaskPage;
import PageObjects.TasksListPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import tests.TestBase;

import java.net.MalformedURLException;

public class CreateNewTaskWithDataSteps extends TestBase {

    CreateNewTaskPage createNewTaskPage;
    TasksListPage tasksListPage;

    public CreateNewTaskWithDataSteps() {
        tasksListPage = new TasksListPage(driver);
        createNewTaskPage = new CreateNewTaskPage(driver);
    }

    @Given("Click add new Task")
    public void clickAddNewTask() throws MalformedURLException {
        tasksListPage.clickAddTaskBtn();
    }

    @Given("Enter {string} and {string}")
    public void enterAnd(String taskName, String taskDesc) {
        createNewTaskPage.enterTitle(taskName);
        createNewTaskPage.enterTaskDescription(taskDesc);
    }

    @Then("Task Added Successfully")
    public void taskAddedSuccessfully() {
        //driver.hideKeyboard();
        //tearDown();
    }
}
