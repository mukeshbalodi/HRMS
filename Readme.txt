Welcome! Readers
This project is divided into two sections:
1.For Running on Local Machine >>>
2.For Running on Lambdatest Hub >>>

--------- Features of Local---------

1. TestngLocal.xml for local project execution on windows (chrome,edge and firefox Browsers) as i have only windows infrastructure.
2. In test output folder you can find (ExtentReport.html) which is the extent report for the executed test Locally.
3. failed test screenshot is attached along with report  test case.
4. Sensitive data is handled securely
5. pom.xml for maven dependency management.
6. page factory for easy initialization of elements.
 
 project structure:
 src/main/java >>> package Base contain BaseClassLocal for browserSetup and extent report management.
 src/main/java >>> package PageObjects for different pages
  src/main/java >>> package Utils configreader.java for reading of sensitive data from src/test/resources/config.properties(secret file not to be  shared on git) and extentmanager.java for report setup.
  src/test/java >>> package TestCases herokuapp_Local.java contains all the test cases.
  test-output folder >>>> ExtentReport.html (Report for executed tests)
  					>>>>> testng-failed.xml for re-execution of failed test cases.
 
 pom.xml >>> for maven dependancy management
 testngLocal.xml >>> for cross browser testing on local 
 
 
 
 --------- Features of Lambdatest---------
 
 1.testngLambda.xml for lambdatest cloud execution for various os and browsers.
 2. Results analysis is easier.
 3. separate video for each test on each os and browsers.
 4. Attractive Dashboard.
 5. Sensitive data is handled securely
 6. page factory for easy initialization of elements.
 

 
 project structure:
 src/main/java >>> package Base contain BaseClassLambda for browserSetup and extent report management.
 src/main/java >>> package PageObjects for different pages
  src/main/java >>> package Utils configreader.java for reading of sensitive data from src/test/resources/config.properties(secret file not to be  shared on git) and extentmanager.java for report setup.
  src/test/java >>> package TestCases herokuapp_Lambda.java contains all the test cases.

 
 pom.xml >>> for maven dependancy management
 testngLambda.xml >>> for cross browser testing on Lambdatest Cloud.
 