Feature: Task Management

  Scenario Outline: [IOS] Creating a New Task
    Given the user is on the Daily Tasks screen
    When the user clicks the plus button to add a task
    And enters a "<Title>" in the Title field
    And enters a dueDate as current date
    And selects a dueTime
    And selects "<Description>" as the description field
    And the user clicks on the Save button
    Then the new task should appear on the DailyTasks screen and display "<Title>"

    Examples:
      | Title                  | Description                                  |
      | Passport Renewal 3     | Check necessary documents  2                 |
      | Buy bread              | for family                                   |
      | Annual Medical Checkup | Confirm health insurance details             |
      | Course Enrollment      | Pay by the 25th for a discount               |
      | Relatives Birthday     | Buy a gift and organize a party              |
      | Car Maintenance        | Change oil and check brakes                  |
      | Dentist                | do not forget credit card                    |
      | Pay rent               | only one month                               |
      | Pay tv                 | 3rd part                                     |
      | Annual Tax Filing      | Gather all financial statements and receipts |
      | Update Office Software | Ensure backup before update                  |

