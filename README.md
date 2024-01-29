
# BDD Framework with NBA Project

This project is designed to demonstrate a Behavior-Driven Development (BDD) framework implemented within the context of a sample NBA Project. It showcases how BDD methodologies can be applied to a Java-based test automation project.

**Features:**
- Behavior-Driven Development (BDD) approach to test automation.
- Integration of Cucumber framework to support BDD for POD specific Test development.
- Implemented GitHub Actions to generate Maven site and JavaDocs automation upon push to the master branch.

**Technologies:**
The project is built using Java and leverages the following technologies:
- Maven for project management and build automation.
- Cucumber as the BDD framework for writing tests in Gherkin language.
- Selenium WebDriver for browser automation.
- Allure for reporting.
- log4j for reporting.

For more details, see [Dependencies](https://nataraaj-shanmugam.github.io/BDD-framework-with-NBA-Project/dependencies.html).

## Getting Started

### Prerequisites
Before running the project, ensure you have the following installed:
- Java JDK 11 or higher.
- Maven for handling project dependencies and running the framework.

### Installation
To set up the project locally, follow these steps:
1. Clone the repository:
   ```
   git clone https://github.com/nataraaj-shanmugam/BDD-framework-with-NBA-Project.git
   ```
2. Navigate to the cloned directory and run the following command to install dependencies and build the project:
   ```
   mvn clean install
   ```
3. Run tests with tags by updating 'cucumber.filter.tags' parameter. For example:
   ```
   mvn test -Dcucumber.filter.tags="@smoke"
   ```
4. Generate report through allure command (install allure, if not)
   ```
   allure serve allure-results
   ```

## Documentation
Further documentation is available on the Maven site:
- [Project Information](https://nataraaj-shanmugam.github.io/BDD-framework-with-NBA-Project/project-info.html)

JavaDoc for the source code is available here:
- [BDD Framework JavaDoc](https://nataraaj-shanmugam.github.io/BDD-framework-with-NBA-Project/BDD-Framework/index.html)
- [NBA Project JavaDoc](https://nataraaj-shanmugam.github.io/BDD-framework-with-NBA-Project/NBA-Project/index.html)
  
  
## Architecture diagram

   ![](https://github.com/Nataraaj-Shanmugam/BDD-framework-with-NBA-Project/blob/master/architecture%20diagram.png)

## Next Phase
Below are the next set of enhancements planned
1. Generate Dynamic feature file examples, based on externals source Testdata
2. Auto-healing [Healenium](https://healenium.io/)
3. Integrate with [Google Lighthouse](https://chromewebstore.google.com/detail/lighthouse/blipmdconlkpinefehnmjammfjpmpbjk?pli=1) for web page performance Testing
4. Adding capabilities
    1. Support for Selenium 4 Relative locators
    2. Network Interceptor
    3. Runtime mocking
  

## Contact
If you have any questions or comments about the project, please open an issue in the GitHub repository.
