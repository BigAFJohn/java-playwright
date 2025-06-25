# Playwright Java Test Automation Framework

This is an end-to-end test automation framework built using [Playwright for Java](https://playwright.dev/java/), integrated with [TestNG](https://testng.org/), [Allure Reports](https://docs.qameta.io/allure/), and Docker support for local and CI execution.

## 🔧 Tech Stack

- Java 21
- Maven
- Playwright for Java
- TestNG
- Allure Reports
- Docker
- GitHub Actions

---

## 📁 Project Structure

```
.
├── base/                   # Base classes for setup and teardown
├── config/                 # Configuration files
├── core/                   # PlaywrightManager for browser handling
├── listeners/              # TestNG Listeners for logging and screenshots
├── pages/                  # Page Object Models
├── tests/                  # Test classes (Login, Checkout, Sorting, etc.)
├── utils/                  # Helper and utility classes (e.g., DataFakerUtil)
├── resources/              # Environment properties
├── target/                 # Test output and Allure reports
├── Dockerfile              # Docker setup
├── .github/workflows/      # GitHub Actions workflows
├── pom.xml                 # Maven dependencies and build config
└── testng.xml              # TestNG suite configuration
```

---

## ✅ Features

- Parallel test execution using TestNG
- POM (Page Object Model) structure
- Custom assertions with AssertJ
- Data generation with Java Faker
- Allure reporting with automatic screenshots on failure
- Docker support for headless execution
- GitHub Actions CI pipeline

---

## ▶️ How to Run Tests Locally

### Prerequisites
- Java 21+
- Maven
- Node.js (Playwright dependency)
- Allure CLI

### Run in local browser
```bash
mvn clean test -Dsuite=parallel
```

### Run in headless mode
```bash
mvn clean test -Dsuite=parallel -Dheadless=true
```

---

## 🐳 Run Tests in Docker

Build and run using Docker:
```bash
docker build -t playwright-tests .
docker run --rm playwright-tests
```

---

## 🧪 GitHub Actions Pipeline

The test suite runs automatically on every push to the `main` branch via GitHub Actions:

```yaml
on:
  push:
    branches: [main]
```

Steps include:
- Checkout code
- Install Java and Maven
- Install Allure CLI
- Run tests
- Generate and serve Allure report

---

## 📊 Allure Reports

To generate and view Allure reports locally:
```bash
allure generate target/allure-results --clean -o allure-report
allure serve allure-report
```

---

## 🧰 Test Data

DataFaker as the faker data generation strategy
```

```
---

## 🔍 Sample Test Cases

- **Login Test:** Verifies login scenarios for standard and locked out users.
- **Checkout Test:** Selects random products, adds to cart, completes checkout, and verifies confirmation.
- **Sorting Test:** Verifies Z–A sorting functionality.

---

## 👤 Author (John Ige)

This framework was created as part of an advanced QA automation setup to test my knowledge of Playwright + Java.

Feel free to fork and adapt it to your project!

---

## 📜 License

[MIT](LICENSE)

---

## 🤝 Contributions

PRs and issues welcome!