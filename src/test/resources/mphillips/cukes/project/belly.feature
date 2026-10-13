Feature: Belly

  Scenario: a few cukes
    Given I have 42 cukes in my belly
    When I wait 1 second
    Then my belly should growl


#  Scenario: a few less cukes
#    Given I have 21 cukes in my belly
#    When I wait 5 seconds
#    Then my belly should yum
