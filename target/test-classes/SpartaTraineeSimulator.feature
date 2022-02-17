Feature: SpartaTraineeSimulator
  Simple SpartaTraineeSimulator which takes commands and outputs data


  @24Months
  Scenario: 24 months run time
    Given I have a Sparta Trainee Simulator
    And I run the simulation
    When I type 24 months into the simulation
    Then The programme should show monthly statistics for 24 months

  @TraineeGeneration
  Scenario: 6 month Trainee Check
    Given I have a Sparta Trainee Simulator
    And I run the simulation
    When The programme reaches 6 months
    Then The number of Trainees should be between 300 and 600

  @CentreCreationCheck
  Scenario: Centre Check
    Given I have the Sparta Trainee Simulator
    And I run the simulation
    When The programme reaches 12 months
    Then The programme shows how many centres of each type have been created

  @WaitingListCheck
  Scenario: Waiting List Check
    Given I have the Sparta Trainee Simulator
    And I run the simulation
    When All Training Centre are full
    Then The remaining trainees should be added to the Waiting List

  @TraineeCourseTypeCheck
  Scenario: Trainee course type check
    Given I have the Sparta Trainee Simulator
    And I run the simulation
    When A trainee is created
    Then A random course type is assigned to them

  @CourseTypeCheck
  Scenario: Course Type Check
    Given I have the Sparta Trainee Simulator
    And I run the simulation
    When The programme creates a training centre
    Then The programme will show which course the trainees are on

  @TrainingCentreOpens
  Scenario: Training Centre Opens Check
    Given I have the Sparta Trainee Simulator
    And I run the simulation for more than 2 months
    When 2 months has passed in the simulation
    Then A new training centre will be created of random type

  @BootcampTraineeCheck
  Scenario: Bootcamp Trainee check
    Given I have the Sparta Trainee Simulator
    And I run the simulation
    When There are less than 25 trainees for 3 consecutive months
    Then The Bootcamp is closed down

  @BootcampMaximumCheck
  Scenario: Bootcamp maximum number check
    Given I have the Sparta Trainee Simulator
    And I run the simulation
    When There are already 2 Bootcamps
    Then A new Bootcamp cannot be created

  @BootcampMaximumTraineesCheck
  Scenario: Bootcamp maximum number of trainees check
    Given I have the Sparta Trainee Simulator
    And I run the simulation
    When A Trainee is added to a Bootcamp
    But There are already 500 Trainees in the Bootcamp
    And No other Training centres are available
    Then No more trainees can be added to the Bootcamp and will be added to the Waiting List instead

  @TechCentreCourseCheck
  Scenario: Tech centre course check
    Given I have the Sparta Trainee Simulator
    And I run the simulation
    When A Tech Centre is created
    Then a random course type is assigned to it

  @TechCentreMaximumTrainees
  Scenario: Tech Centre Trainee number check
    Given I have the Sparta Trainee Simulator
    And I run the simulation
    When A Trainee is added to a Tech Centre
    But There are already 200 Trainees in the Tech centre
    And No other Training Centres are available
    Then No more Trainees can be added to the Tech Centre and will be added to the Waiting List instead

  @TrainingHubCreationNumber
  Scenario: How many Training Hubs are created
    Given I have the Sparta Trainee Simulator
    And I run the simulation
    When A Training Hub selected from the 3 types available
    Then A random amount of Training Hubs (1-3) will be created

  @TrainingHubMaximumTrainees
  Scenario: Number of Trainees in Training Hub
    Given I have the Sparta Trainee Simulator
    And I run the simulation
    When A Trainee is added to the Training Hub
    But There are already 100 Trainees in this Training Hub
    And No other Training Centres are available
    Then No more Trainees can be added to this Training Hub and will be added to the Waiting List




