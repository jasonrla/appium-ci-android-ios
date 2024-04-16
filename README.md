# Mobile automation framework with Appium for Android and iOS apps

## Description

This mobile test automation framework is designed to support automated testing for Android and iOS applications. It allows testers to execute scenario-based automated tests to ensure that mobile applications perform as expected on various devices.
Profiles are used to define the different OS for running the tests. The framework is built using Java, Maven, Appium, and Cucumber. It uses the Allure reporting tool to generate detailed test reports.
It implements the Page Object Model (POM) design pattern, which helps to create a more maintainable and scalable test automation framework. A CI pipeline is also included to run the tests automatically after each code commit, using Strategy Pattern to define the different devices for running the tests.

Video demo:
[Android demo](https://drive.google.com/file/d/1t3SmZq7BpuSiGfn8PgYVa9Klq1hZtXai/view?usp=sharing).
[IOS demo](https://drive.google.com/file/d/1E-3qkUD5RP64Xd5O2Hy_xnayv4-U-VnE/view?usp=sharing).

## ToDo App for Android and iOS

The ToDo App is a mobile application that allows users to create, edit, and delete tasks. It is used as a sample application to demonstrate the capabilities of the mobile automation framework.

[Android app image](https://drive.google.com/file/d/1oUmN2vMLJmQMUUGpL_9L-W2wKXC-ROog/view?usp=sharing)

[IOS app image](https://drive.google.com/file/d/1Z_nrdghzKH1QlxmFuXUbQ_zdHTSjSYfL/view?usp=sharing)

## Technologies and Versions Used

- Programming Language: Java 11
- Build Tool: Maven 3.9.6
- Mobile Driver: Appium 2.0.1
- BDD Framework: Cucumber 7.15.0
- Reporting: Allure 2.21.0
- Assertion Library: JUnit 4.13.2

## Project Structure

The project structure implements the POM design pattern and contains the following directories and files:

- `.github/workflows`: Contains the CI pipeline configuration files for each OS.
- `allure-results`: Contains the results of the test execution.
- `apps`: Contains the APK and IPA files for the ToDo App.
- `src/main/java/pageObjects`: Contains the page classes for the framework.
- `src/test/java/hooks`: Contains the hooks classes for the framework.
- `src/test/java/runners`: Contains the test runners for the framework.
- `src/test/resources`: Contains the feature files for the framework.
- `src/test/steps`: Contains the step definitions for the framework.
- `src/test/tests`: Contains the test base where appium capabilities are defined.
- `pom.xml`: Contains the dependencies, plugins and profiles for the framework.

**Github project URL:**

https://github.com/jasonrla/appium-ci-android-ios

## CI Configurations

Github Actions defines a continuous integration (CI) pipeline for building and testing an Android and IOS application using Appium. 
The pipeline is defined in the `.github/workflows` directory. It contains the following files:

- `appium-android.yml`: Contains the CI pipeline configuration for Android.
- `appium-ios.yml`: Contains the CI pipeline configuration for iOS.


# For android:
The workflow is triggered on every push to any branch except master, and on every pull request to the master branch.  The build-and-test job runs on the latest version of Ubuntu. It uses a matrix strategy to run the job with different configurations, each representing a different Android API level, architecture, port, and channel for the emulator.  The fail-fast option is set to false, which means that if one configuration fails, the others will continue to run.  The job consists of several steps:  
- `Checkout code`: This step checks out the repository code using the actions/checkout action.  
- `Setup Java`: This step sets up Java 11 on the runner using the actions/setup-java action.  
- `Enable KVM`: This step enables the Kernel-based Virtual Machine (KVM) on the runner, which is necessary for running and optimizing Android emulators.  
- `Setup Node.js`: This step sets up Node.js version 20 on the runner using the actions/setup-node action.  
- `Setup appium`: This step installs Appium and the UIAutomator2 driver globally using npm, checks the Appium version, and starts an Appium server on a port specified by the matrix configuration.  
- `Run appium android tests`: This step runs the Appium tests on an Android emulator using the reactivecircus/android-emulator-runner action. The emulator's API level, target, architecture, and channel are specified by the matrix configuration. The Maven command to run the tests is also specified here, with properties for the API level, Appium port, and Allure results directory.  
- `Upload Allure results`: This step uploads the Allure results as an artifact using the actions/upload-artifact action. The artifact's name and path are based on the API level and port from the matrix configuration.  

# For iOS:
The workflow is triggered on every push to any branch except master, and on every pull request to the master branch.  The build-and-test job runs on the latest version of macOS. It uses a matrix strategy to run the job with different configurations, each representing a different iPhone device model, Appium port, and WebDriverAgent (WDA) port.  The fail-fast option is set to false, which means that if one configuration fails, the others will continue to run.  The job consists of several steps:  
- `Checkout code`: This step checks out the repository code using the actions/checkout action.  
- `Setup Xcode`: This step sets up the latest stable version of Xcode on the runner using the maxim-lobanov/setup-xcode action.  
- `Setup Java`: This step sets up Java 11 on the runner using the actions/setup-java action.  
- `Cache Maven dependencies`: This step caches the Maven dependencies to speed up subsequent runs using the actions/cache action.  
- `Cache Node.js dependencies`: This step caches the Node.js dependencies to speed up subsequent runs using the actions/cache action.  
- `Check Xcode version`: This step checks the installed Xcode version and lists the available devices.  
- `Start iOS Simulator`: This step starts the iOS simulator for the device specified by the matrix configuration.  
- `Get Booted Simulator UDID`: This step retrieves the Unique Device Identifier (UDID) of the booted simulator and saves it as an environment variable.  
- `Setup Appium`: This step installs Appium and the XCUITest driver globally using npm, checks the Appium version, and starts an Appium server on a port specified by the matrix configuration.  
- `Run Appium iOS tests`: This step runs the Appium tests on the iOS simulator. The Maven command to run the tests is specified here, with properties for the device name, Appium port, and Allure results directory.  
- `Upload Allure results`: This step uploads the Allure results as an artifact using the actions/upload-artifact action. The artifact's name and path are based on the device name and Appium port from the matrix configuration.  

In both cases the report job depends on the build-and-test job. It generates and publishes an Allure report using Github Pages, based on the test results. The steps include checking out the code, loading the test report history, downloading the Allure results, combining the results, building the test report using the simple-elf/allure-report-action action, and publishing the report to the gh-pages branch using the peaceiris/actions-gh-pages action.

## Allure Report

Allure is a test reporting tool that provides clear and concise test reports. To work with Appium in the cloud, you'll need to generate an access token.  

Locally:
- When maven commands are executed, the Allure report is generated in the `allure-results` directory, then uploading the results to Allure cloud it generate the report.

[Allure local image](https://drive.google.com/file/d/1EzLMMvfve_AzJRI28_tDfpKf8i_xVlHu/view?usp=sharing)

CI:
- After the test workflows it calls the pages-build-deployment workflow to send the results into the gh-pages branch, and finally it can be visualized in the GitHub Pages.
https://jasonrla.github.io/appium-ci-android-ios/

[Allure CI image](https://drive.google.com/file/d/1YcqDIj6GxV-Ruzp0f7n_szAJkUa0GWmm/view?usp=sharing)

## Test Execution

The tests can be executed using the following Maven command:

```bash
mvn clean test -Pandroid
mvn clean test -PiOS
```
## Test Execution with parameters
```bash
mvn clean test -Pandroid -Dapi.level=34 -DappiumPort=4723 -Dplatform=android
mvn clean test -PiOS "-Ddevice.name=iPhone 15" -Dappium.port=4723 -Dplatform=ios
```

## Emulators for Android and iOS

The CI implements strategy pattern to define the different devices for running the tests. 
For this project the following devices are set up for Android and iOS:

- API 27 (Android 8.1)
- API 28 (Android 9)
- API 29 (Android 10)
- API 30 (Android 11)
- API 32 (Android 12L)
- API 33 (Android 13)
- API 34 (Android 14)

[Android Github actions log image](https://drive.google.com/file/d/1MtyMDB7RkOWHUpsawTOJJRjA6UdpACc5/view?usp=sharing)

- iPhone 14 Pro Max
- iPhone 14 Pro
- iPhone 14 Plus
- iPhone 14
- iPhone 13 Pro Max
- iPhone 13 Pro
- iPhone 12 mini
- iPhone 11 Pro
- iPhone 8

[IOS Github actions log image](https://drive.google.com/file/d/1YYND_9vLQDAuuF_sfSElRKEwnuBGgTjC/view?usp=sharing)
