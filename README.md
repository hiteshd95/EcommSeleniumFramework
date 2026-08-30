# OpenCart Automation Test Suite

A Selenium + TestNG automation framework for validating core user flows on the Automation Exercise demo e-commerce application. The project follows the Page Object Model (POM), supports browser parameterization, and includes data-driven login testing, rich logging, screenshots, and Allure reporting.

## Why this project

This repository is designed to help teams automate critical end-to-end UI checks such as:

- New user registration
- User login and logout
- Data-driven login validation
- Browser and environment flexibility
- CI-friendly test execution

## Tech stack

- Java 17
- Maven
- Selenium WebDriver 4
- TestNG
- Apache POI for Excel-based test data
- Log4j 2 for execution logs
- Allure for test reporting
- Page Object Model architecture

## Project structure

```text
OpenCart/
├── src/
│   ├── main/java/
│   │   └── com/org/orangehrm/App.java
│   └── test/
│       ├── java/
│       │   ├── pageObjects/
│       │   │   ├── BasePage.java
│       │   │   ├── HomePage.java
│       │   │   └── LoginRegistrationPage.java
│       │   ├── testBase/
│       │   │   └── BaseClass.java
│       │   ├── testcases/
│       │   │   ├── TC001_AccountRegistrationTest.java
│       │   │   ├── TC002_LoginTest.java
│       │   │   └── TC003_LoginDDT.java
│       │   └── utilities/
│       │       ├── AllureListener.java
│       │       ├── DataProviders.java
│       │       └── ExcelUtility.java
│       └── resources/
│           ├── config.properties
│           ├── docker-compose.yaml
│           └── log4j2.xml
├── testdata/
│   └── LoginData.xlsx
├── screenshots/
├── logs/
├── allure-results/
├── pom.xml
├── testng.xml
├── .gitignore
└── README.md
```

## Features included

- Cross-browser support: Chrome, Edge, Firefox
- Local and remote execution support via Selenium Grid
- Data-driven login test with Excel files
- Automatic screenshot capture on failure or test events
- Centralized configuration through `config.properties`
- Test listeners for Allure integration
- Clean Page Object implementation for maintainability

## Prerequisites

Before running the project, ensure you have:

- Java 17 or higher
- Maven 3.8+
- A compatible browser installed locally (Chrome / Edge / Firefox)
- Internet access to the target application URL

## Configuration

The project configuration is managed in:

- `src/test/resources/config.properties`

Sample settings include:

```properties
URL = https://automationexercise.com/
execution_env = local
# execution_env = remote
```

You can change the target URL, browser, or execution environment there.

## Running the tests

Run the full suite:

```bash
mvn clean test
```

Run a specific test class:

```bash
mvn test -Dtest=TC001_AccountRegistrationTest
mvn test -Dtest=TC002_LoginTest
mvn test -Dtest=TC003_LoginDDT
```

The default suite is configured in `testng.xml` and runs the following test classes:

- `testcases.TC001_AccountRegistrationTest`
- `testcases.TC002_LoginTest`
- `testcases.TC003_LoginDDT`

## Browser and OS parameters

The suite supports parameterized execution using `testng.xml`:

```xml
<parameter name="os" value="windows" />
<parameter name="browser" value="edge" />
```

You can change these values for local runs or remote execution.

## Remote execution with Selenium Grid

This project is prepared for remote execution through Selenium Grid. Set:

```properties
execution_env = remote
```

Then run a Grid node locally or through Docker. The default remote WebDriver URL is:

```text
http://localhost:4444/wd/hub
```

## Reports and artifacts

After execution, you can inspect:

- `allure-results/` for raw Allure execution data
- `screenshots/` for captured UI screenshots
- `logs/` for runtime logs
- `target/surefire-reports/` for TestNG reports

Generate an HTML Allure report:

```bash
mvn allure:generate
mvn allure:open
```

If `allure:open` is unavailable in your environment, open the generated report in the browser from the target folder.

## GitHub push steps

If you want to publish this project on GitHub, run:

```bash
git init
git add .
git commit -m "Initial project setup"
git branch -M main
git remote add origin <your-github-repository-url>
git push -u origin main
```

Example:

```bash
git remote add origin https://github.com/your-username/your-repo-name.git
```

## Notes

- This project is designed for automated testing and is not intended for production deployment.
- If you use a different target website or environment, update `config.properties` and any page locators as needed.
- The Excel file in `testdata/LoginData.xlsx` is used for the data-driven login scenario.

## License

This project is currently distributed without a formal license file. Add a license if you plan to open-source it publicly.

## Contributing

Pull requests are welcome. Before submitting changes, please ensure:

- tests still pass locally
- new code follows the existing Page Object Model structure
- logs and screenshots remain meaningful and non-sensitive
