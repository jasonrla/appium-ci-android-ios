Feature: Task Management
#  Scenario: The user can add new task
#    Given Click Add new Task
#    Given Enter TaskName
#    Given Enter TaskDesc
#    When Click Save
#    Then Task added successfully

  Scenario Outline: Creating a New Task for Today

    Given the user is on the "Today" screen
    When the user clicks the plus button to add a task
    And enters "<Title>" in the Title field
    And enters "<Note>" in the Note field
    And selects "<Start Date>" as the start date
    And selects "<Due Date>" as the due date
    And chooses "<Tag>" from the tag list
    And selects "<Priority>" as the priority
    And the user clicks on the Save button
    Then the new task should appear on the correct "<Start Date>" screen
    And the task should display "<Title>", "<Start Date>", and "<Tag>" (last selected tag)

    Examples:
      | Title     | Start Date | Due Date   | Note                      | Tag       | Priority |
      | Buy bread | Today      | Tomorrow   | for family                | Personnal | None     |
      | Dentist   | Tomorrow   | 30/04/2024 | do not forget credit card | Shopping  | Low      |
      | Pay rent  | 18/04/2024 | None       | only one month            | To read   | Medium   |
      | Pay tv    | 19/07/2026 | None       | 3rd part                  | Work      | High     |
