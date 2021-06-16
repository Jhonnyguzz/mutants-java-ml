# Mutants

## Run locally

1. Set JAVA_HOME environment variable with JDK 11
2. Run in this project folder:

````
mvn clean install
mvn spring-boot:run
````

Use mvnw if you don't have maven installation

3. (Optional) Run with IntelliJ
4. Endpoints:

POST localhost:8080/mutant

{
 "dna":["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]
}

GET localhost:8080/stats

## Cloud Endpoints

POST https://mercadolibretest.azurewebsites.net/mutant

{
"dna":["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]
}

GET https://mercadolibretest.azurewebsites.net/stats

Available until June 23, 2021

## Docs

Spring Cloud Functions

* https://docs.microsoft.com/en-us/azure/developer/java/spring-framework/getting-started-with-spring-cloud-function-in-azure
* https://github.com/spring-cloud/spring-cloud-function/tree/main/spring-cloud-function-samples/function-sample-azure