## Project Information

This project covers simple test for public api [PetStore](https://petstore.swagger.io/). Tests are build with Java, Maven, Rest Assured an TestNG

### Coverage

**Endpoint**: /pet \
**Methods**: GET and POST

### Test Scope

- Verifies status codes for correct and incorrect calls (**200**, **404**, **400**, **405**)
- Verifies if GET responses matches expected format and data based on [Swagger documentation](https://petstore.swagger.io/)
- Verifies if POST request creates a new item

### How to Run

Tests cna be run from IntelliJ IDEA or command window:
```
mvn clean test -DsuiteXmlFile="testng.xml"
```

### Failure reports

If tets were run form command run, HTML report is available under  
> Petstore\target\surefire-reports\index.html