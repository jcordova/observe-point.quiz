# observe-point.quiz

This is a very basic project which demonstrates how REST API service end-point(s) can be tested in an automation fashion using Rest-Assured JAVA Library.  

I worked on this mini project during this past weekend as a job interview's quiz.

## Requirements
* JAVA 8 (JDK 8)
* MAVEN or use Maven Wrapped (included)

## Main libraries
* REST-Assured
* Allure-Reports
* JUnit 4

## Installation
You are welcome to clone and run this project on your local sandbox.  Just follow next steps ..

* Clone repository

```bash
  git clone https://github.com/jcordova/observe-point.quiz.git
  cd observe-point.quiz
```

* Pull all dependencies, build project, run test and create/access test report

  Mac/Linux/Windows - using local Maven

  ```bash
  mvn clean install
  mvn clean -Dtest=SearchForUnqualifiedPlayers test 
  mvn allure:report
  mvn allure:serve 
  ```
  
  Mac/Linux - using Maven wrapper  

  ```bash
  ./mvnw clean install
  ./mvnw clean -Dtest=SearchForUnqualifiedPlayers test 
  ./mvnw allure:report
  ./mvnw allure:serve 
  ``` 
  
  Windows - using Maven wrapper
  
  ```batch
   mvnw clean install
   mvnw clean -Dtest=SearchForUnqualifiedPlayers test 
   mvnw allure:report
   mvnw allure:serve 
``` 

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update test as appropriate.

## License
[MIT](https://choosealicense.com/licenses/mit/)
