@Demo_Suite
Feature: Demo Application Features

  @Demo_1
  Scenario Outline: Demo_1
    * Demo Application is Launched
    * Traverse to Simple Form Demo
    * Check Single Input Field Functionality <SampleMessage>
    * Check Two Input Fields Functionality <Input#1> <Input#2>
    * Close The Application
  Examples:
    | SampleMessage               | Input#1 | Input#2 |
    | "Hello, my name is Mahesh!" | 10      | 20      |

    @Demo_2
    Scenario: Demo2
      * Demo Application is Launched