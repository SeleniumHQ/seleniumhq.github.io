# Running Selenium .NET (C#) Tests

The following steps will guide you on how to  
run Selenium .NET (C#) tests using the examples  
from the `SeleniumHQ/seleniumhq.github.io` repository.

## Initial Setup

### Prerequisites

Ensure you have the following installed:

- [.NET SDK (8.0 or later)](https://dotnet.microsoft.com/en-us/download)
- An IDE like [Visual Studio](https://visualstudio.microsoft.com/) or [Visual Studio Code](https://code.visualstudio.com/)
- [.NET CLI tools](https://learn.microsoft.com/en-us/dotnet/core/tools/)

### Clone the repository

Clone the Selenium documentation repository to your local machine:

```bash
git clone https://github.com/SeleniumHQ/seleniumhq.github.io.git
```

## Navigate to the .NET directory
### Change into the .NET examples directory:

```bash
cd seleniumhq.github.io/examples/dotnet/SeleniumDocs
```

## Running the Tests
### Restore dependencies
Install necessary dependencies using the .NET CLI:

```bash
dotnet restore
```

### Run all tests
To run all available tests:

```bash
dotnet test
```

### Run a Specific Test Method
To run a specific test method using its fully qualified name:

```bash
dotnet test --filter "FullyQualifiedName=Namespace.ClassName.MethodName"
```
For example:
```bash
dotnet test --filter "FullyQualifiedName=SeleniumDocs.GettingStarted.UsingSeleniumTest.EightComponents"
```

You can also filter by test name only (if it's unique):
```bash
dotnet test --filter "Name=EightComponents"
```