Feature: IOS Task Management

  Scenario Outline: [IOS] Adding new Today’s Task
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

  Scenario Outline: [IOS] Adding new Scheduled Task
    Given the user is on the Daily Tasks screen
    When the user clicks the plus button to add a task
    And enters a "<Title>" in the Title field
    And enters a "<DueDate>" in the DueDate field
    And selects a dueTime
    And selects "<Description>" as the description field
    And the user clicks on the Save button
    Then the new task "<Title>" should not appear on the DailyTasks screen
    And the new task "<Title>" should appear on the Schedule screen on "<DueDate>"

    Examples:
      | Title                  | Description                                  |DueDate    |
      | Course Enrollment      | Pay by the 25th for a discount               |2024-08-15 |
      | Course Enrollment      | Pay by the 25th for a discount               |2024-04-29 |
      | Relatives Birthday     | Buy a gift and organize a party              |2024-08-16 |
      | Car Maintenance        | Change oil and check brakes                  |2024-10-20 |

