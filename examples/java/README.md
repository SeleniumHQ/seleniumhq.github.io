# Running Selenium Java Tests
The following steps will guide you on how to 
run Selenium Java tests using a repository 
of `SeleniumHQ/seleniumhq.github.io` examples.

## Initial Setup

### Prerequisites

Ensure that Java Development Kit (JDK) and Maven 
are installed on your system. If they are not installed, 
you will need to download and install them. You can 
find detailed installation guides for both on their 
respective official sites.

### Clone the repository
First, we need to get the Selenium Java examples 
on your local machine. This can be done by 
cloning the `SeleniumHQ/seleniumhq.github.io` Git repository. 
Run the following command in your terminal:

```bash
git clone https://github.com/SeleniumHQ/seleniumhq.github.io.git
```
## Navigate to the java directory
After cloning the repository, navigate into the 
directory where the Selenium Java examples are 
located. Run the following command:

```bash
cd seleniumhq.github.io/examples/java
```

## Running the Tests
### Install dependencies
Before running the tests, we need to install all 
necessary dependencies. Maven, a software 
project management tool, can do this for us. 
Run the following command:

```bash
mvn test-compile
```

### Run all tests
To verify if everything is installed correctly and 
functioning properly, we should run all 
available tests. This can be done with the following command:

```bash
mvn test
```

Please be patient! If this is your first time running these tests, 
it might take a while to download and verify all necessary browser drivers.

## Execute a specific example
To run a specific Selenium Java example, use the following command:
```bash
mvn exec:java -D"exec.mainClass"="dev.selenium.getting_started.FirstScript" -D"exec.classpathScope"=test
```

Make sure to replace `dev.selenium.getting_started.FirstScript` with the path and name of the example you want to run.