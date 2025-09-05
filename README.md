# API Automation Project

This project is an API automation testing suite using Cucumber, Rest Assured, and Java.

## Project Structure
```
project-root/
├── .github/
│   └── workflows/
│       └── java-tests.yml
├── src/
│   ├── main/
│   │   └── java/
│   │       ├── pageobjects/
│   │       │   └── Endpoints.java
│   │       └── utils/
│   └── test/
│       ├── java/
│       │   ├── stepdefinitions/
│       │   │   ├── ShowUsersSteps.java
│       │   │   └── UserSteps.java
│       │   ├── support/
│       │   │   ├── AllureHelper.java
│       │   │   └── TestContext.java
│       │   └── RunCucumberTest.java
│       └── resources/
│           └── features/
│               ├── GetShowAllUsers.feature
│               ├── DeleteUserWithId.feature
│               ├── DeleteUserWithIdNonExistent.feature
│               ├── GetShowUserWithId.feature
│               ├── PostCreateNewAdminUser.feature
│               └── PostCreateNewUser.feature
├── .gitignore
├── package.json
├── pom.xml
└── README.md
```

## Prerequisites

- Java JDK 11 or higher
- Maven
- Node.js and npm (for running the test-and-report script)

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

There are two ways to run the tests:

1. To run a specific test, open the feature file in your IDE and click on the play button next to the scenario you want to run.

2. To run all tests and generate a report, use the following command:

npm run test-and-report

This command will execute all tests and generate an Allure report.

## CI/CD

This project includes a GitHub Actions workflow (`.github/workflows/java-tests.yml`) that runs the tests on push and pull requests to the main branch.

## Reporting

After running the tests using `npm run test-and-report`, you can find the Allure report in the `target/site/allure-report` directory. Open the `index.html` file in this directory to view the report in your browser.

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## Contact

Henrique de Carvalho - www.linkedin.com/in/henrique-carvalho-467960b3