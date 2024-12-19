Feature: try to reproduce wrong report generation

#  Background:
#    Given do something wrong

  Scenario Outline: do something with <something>
    Given do something wrong
    When do something
#    When do something
#    When do something
#    When do something
#    When do something
#    When do something
#    When do something
    Examples:
      | something |
      | "hello"   |
      | "world"   |

