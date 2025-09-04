# API Automation Project

This project is an API automation testing suite using Cucumber, Rest Assured, and Java.

## Project Structure
```
├── .github
│   └── workflows
│       └── java-tests.yml
├── src
│   ├── main
│   │   └── java
│   │       ├── pageobjects
│   │       │   └── Endpoints.java
│   │       └── utils
│   └── test
│       ├── java
│       │   ├── stepdefinitions
│       │   │   ├── ShowUsersSteps.java
│       │   │   └── UserSteps.java
│       │   ├── support
│       │   └── RunCucumberTest.java
│       └── resources
│           └── features
│               ├── DeleteUserWithId.feature
│               ├── DeleteUserWithIdNonExistent.feature
│               ├── PostCreateNewAdminUser.feature
│               ├── GetShowAllUsers.feature
│               ├── GetShowUserWithId.feature
│               └── PostCreateNewUser.feature
├── .gitignore
├── pom.xml
└── README.md
```

## Dependencies

This project uses the following main dependencies:

- Java 8+
- Maven
- Cucumber
- Rest Assured
- JUnit
- Jackson (for JSON processing)
- JavaFaker (for generating test data)

For a complete list of dependencies and their versions, please refer to the `pom.xml` file.

## Installation

1. Ensure you have Java 8 or higher installed on your system.
2. Install Maven if you haven't already.
3. Clone this repository:
   git clone https://github.com/your-username/api-automation-project.git

4. Navigate to the project directory:
   cd api-automation-project

5. Install the dependencies:
   mvn clean install

## Running Tests

To run a test open the feature file and cli on play.

Replace `YourFeatureFile.feature` with the name of the feature file you want to run.

## CI/CD

This project includes a GitHub Actions workflow (`.github/workflows/java-tests.yml`) that runs the tests on push and pull requests to the main branch.

## Reporting

After running the tests, you can find the Cucumber HTML report in the `target/cucumber-reports` directory.

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## Contact

Henrique de Carvalho - www.linkedin.com/in/henrique-carvalho-467960b3