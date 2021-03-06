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

  @TrainingHubTechCentreTraineeCheck
  Scenario: Bootcamp Trainee check
    Given I have the Sparta Trainee Simulator
    When A training centre is created
    And I run the simulation for a month
    When There are less than 25 trainees
    Then The training centre is closed down and trainees are moved to another available centre

  @SimulationOutputCheck
  Scenario: Output simulation result
    Given I have the Sparta Trainee Simulator
    And I run the simulation
    When The simulation finishes
    Then The program outputs the result of centres and trainees and their course type

  @TraineeBenchCheck
  Scenario: Trainee bench state
    Given I have the Sparta Trainee Simulator
    And I run the simulation
    When A trainee has been in training for a year
    Then The trainee is moved to a benched state

  @ClientCreationCheck
  Scenario: Creation of a client
    Given I have the Sparta Trainee Simulator
    And I run the simulation
    When A year runs in the simulation
    Then A number of clients will be randomly created every month

  @ClientNumberOfTrainees
  Scenario: Set requirement for a created client
    Given I have the Sparta Trainee Simulator
    And I run the simulation
    When A client is created
    Then A random number of trainees (>=15) will be set as required and a random course type

  @ClientMaximumTrainees
  Scenario: Number of trainees for a client
    Given I have the Sparta Trainee Simulator
    And I run the simulation
    When A client is created
    And A month runs in the simulation
    But Client??s trainees capacity is full
    Then trainee will remain in bench

  @ClientUnhappyState
  Scenario: Client does not have enough trainees after a year
    Given I have the Sparta Trainee Simulator
    And I run the simulation
    When A client is created
    And A year runs in the simulation
    But Client has not enough trainees
    Then Client will leave unhappy

  @ClientHappyState
  Scenario: Client ha enough trainees after a year
    Given I have the Sparta Trainee Simulator
    And I run the simulation
    When A client is created
    And A year runs in the simulation
    And Client has full capacity of trainees
    Then Client will leave happy and return next year with same requirements



