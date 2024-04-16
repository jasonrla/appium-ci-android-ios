Feature: Task Management

  Scenario Outline: [Android] Creating a New Task
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
      | Title                   | Start Date | Due Date   | Note                                         | Tag       | Priority |
      | Passport Renewal        | Today      | None       | Check necessary documents                    | Personnal | None     |
      | Buy bread               | Today      | Tomorrow   | for family                                   | To read   | Low      |
      | Annual Medical Check-up | Today      | Today      | Confirm health insurance details             | Work      | Medium   |
      | Course Enrollment       | Tomorrow   | None       | Pay by the 25th for a discount               | Shopping  | High     |
      | Relative's Birthday     | Tomorrow   | Tomorrow   | Buy a gift and organize a party              | Work      | None     |
      | Car Maintenance         | Today      | 30/04/2024 | Change oil and check brakes                  | Shopping  | Low      |
      | Dentist                 | Tomorrow   | 30/04/2025 | do not forget credit card                    | Shopping  | Medium   |
      | Pay rent                | 18/04/2024 | None       | only one month                               | To read   | High     |
      | Pay tv                  | 19/07/2026 | None       | 3rd part                                     | Work      | Low      |
      | Annual Tax Filing       | 20/04/2024 | 22/06/2024 | Gather all financial statements and receipts | Shopping  | High     |
      | Update Office Software  | 25/04/2024 | 20/05/2027 | Ensure backup before update                  | Work      | Medium   |

