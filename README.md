# TESTING repository

## Overview

This project is dedicated to testing the functionality of [Skillo Training](http://training.skillo-bg.com). It includes automated tests to ensure the reliability of certain functionalities on the site.

The project is organized following the Page Object Model (POM) and Page Factory design patterns.

## Test Scenarios

The project contains the following test scenarios:

### Positive Scenarios

* **User Registration**: Verify that users can successfully register on the site, and validate that basic error messages are displayed appropriately.
* **Login**: Ensure that registered users can log in to the site, and verify that basic error messages are displayed correctly.
* **Profile Update**: Validate that users can update their profile information, and validate error handling when updating profile information with invalid data.
* **Post Creation**: Verify that users can create public/private posts on the site, and validate that appropriate confirmation/error messages are displayed.
* **Like/Dislike Posts**: Test the functionality of liking and disliking posts through the Homepage and Profile page.

### Negative Scenarios

* **User Registration**: Verify that users cannot register on the site with invalid or incomplete information.
* **Login**: Ensure that login with incorrect credentials is impossible.
* **Profile Update**: Verify that users cannot update profile information with invalid data.
* **Post Creation**: Ensure that users cannot create a public/private post with invalid or empty content.

## Automation Framework

The automation tests in this project are created using the [TestNG testing framework](https://testng.org/) and [Selenium WebDriver automation framework](https://www.selenium.dev/).

## Getting Started

### Prerequisites

Before you begin, ensure you have the following installed:

- [Java Development Kit 11 (JDK 11)](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) - Required for running Java-based applications and TestNG.
- [Maven](https://maven.apache.org/download.cgi) - Build automation tool used for managing dependencies and building the project.
- *(optional)* [Git](https://git-scm.com/downloads) - Version control system used for cloning the project repository.
- *(optional)* [IntelliJ IDEA](https://www.jetbrains.com/idea/download/?section=windows) or other JAVA IDE for software test development.

### Setup Steps

1. **Clone the Repository:**
   ```bash
   git clone https://github.com/DinevaTsvetelina/TsDSkilloTask.git
   ```

2. **Navigate to the Project Directory:**
   ```bash
   cd TsDSkilloTask
   ```

3. **Run the Tests**:
- to clean the target folder and run the tests:
   ```bash
   mvn clean test
   ```

- to clean the target folder, run the tests, and generate a report:
   ```bash
   mvn clean test site
   ```

4. **Go and check the folder with surefire reports:**
   ```bash
   cd target\surefire-reports
   ```

5. **If Site lifecycle was executed, go and check the folder with the project's site documentation:**
   ```bash
   cd target\site
   ```

6. **Go and check the folder with screenshots of failed tests:**
   ```bash
   cd target\failed-tests-screenshots
   ```