# NumbersAPI

**NumbersAPI** is a java based spring-boot application that exposes a GET REST API to convert integer
into roman number
> Developer: Janakiraman Raghu

## Table of Contents

- [Tech Stack](#tech-stack)
- [Architecture](#architecture)
- [Packaging Layout](#packaging-layout)
- [Build & Deploy](#build--deploy)
- [Run Application - StandAlone Mode](#run-application---standalone-mode)
- [API Request/Responses](#api-requestresponses)
- [Testing](#testing)
- [Un-install Application Stack](#un-install-the-application-stack)
- [Next Steps](#next-steps)
- [Dependency Tree](#dependency-tree)
- [References](#references)

## Tech Stack

* Java 8
* spring-boot 2
* Maven 3
* Junit 5
* Mockito
* Splunk
* Swagger
* Grafana
* Prometheus
* Docker
* Docker-Compose

## Architecture

![Architecture](readme-images/Architecture.png)

## Packaging Layout

![Packaging-Layout](readme-images/Packaging-Layout.png)

## Build & Deploy

1. Clone the git repo

```
git clone https://github.com/janakiraman06/NumbersAPI.git

cd NumbersAPI
```

2. Pre-requisite checks for required frameworks

```
java -version
docker -v
docker-compose -v
mvn -v
```

3. Use Maven to build the artifact - Spring Boot Jar file

```
mvn clean install
```

4. On successful maven build, we can start the whole application stack using the commands

```
cd devops

docker-compose up -d --build
```

5. Make sure all the docker containers are up and running. Especially splunk containers take ~1 min to start up completely.

```
 docker-compose ps                            
           Name                          Command                  State                                                      Ports                                                
----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
devops_grafana_1              /run.sh                          Up             0.0.0.0:3000->3000/tcp,:::3000->3000/tcp                                                            
devops_numberconverterapp_1   java -jar /app.jar               Up             0.0.0.0:8080->8080/tcp,:::8080->8080/tcp                                                            
devops_prometheus_1           /bin/prometheus --config.f ...   Up             0.0.0.0:9090->9090/tcp,:::9090->9090/tcp                                                            
devops_splunk_1               /sbin/entrypoint.sh start- ...   Up (healthy)   0.0.0.0:8000->8000/tcp,:::8000->8000/tcp, 8065/tcp, 8088/tcp, 8089/tcp, 8191/tcp, 9887/tcp, 9997/tcp
devops_splunkforwarder_1      /sbin/entrypoint.sh start- ...   Up (healthy)   8088/tcp, 8089/tcp, 9997/tcp          
```

6. Validate the deployed services are up and running using the endpoints listed below

> Number converter  - http://localhost:8080/romannumeral?query=5  [Credential:  testuser/Spring@2021]
>
> Swagger UI(API Spec) - http://localhost:8080/swagger-ui.html
>
> Prometheus - http://localhost:9090/
>
> Grafana - http://localhost:3000/  [Credential:  admin/Grafana@2021]
> 
> Splunk - http://localhost:8000/   [Credential:  admin/password]

7. Spring security is enabled for this application.  The credential to access the /romannumeral endpoint is

   ```
   username: testuser
   password: Spring@2021
   ```

## Run Application - Standalone Mode

1. Run the `NumberConverterApplication` java class.
2. Make sure that stack is not running using the Devops functionalities while running in standlone mode, since port 8080 is the default port using by tomcat

## API Spec

### Swagger Documentation

> http://localhost:8080/swagger-ui.html
![Swagger](readme-images/Swagger.png)

## DevOps Capabilities

### Logging

#### Viewing logs in Splunk

> http://localhost:8000/

1. Login into the splunk using the credentails [admin/password]
   ![Splunk-LoginPage](readme-images/Splunk-Login.png)

2. After successfully logging in, close all the popups[if any] and click Search & Reporting
   ![Splunk-Homepage](readme-images/Splunk-Homepage.png)

3. After landing on the search page, type * in the search field, select appropriate time frame and search
   ![Splunk-Logs](readme-images/Splunk-Logs.png)


### Metrics & Monitoring

#### Graphana Dashboard

> http://localhost:3000/

1. Login Credential

   ```admin/Grafana@2021```


2. JVM Dashboard is autoconfigured. So, once you login you should see something like this.
   ![Grafana-HomePage](readme-images/Grafana-HomePage.png)


3. Navigate to Dashboards[http://localhost:3000/dashboards]

   ![Grafana-Dashboards](readme-images/Grafana-Dashboards.png)

4. Select the JVM(Micrometer) dashboard and change the time range to "Last 1 hour" or "Last 30 minutes".
   ![](readme-images/JVM-Dashboard-1.png)
   ![](readme-images/JVM-Dashboard-2.png)
   ![](readme-images/JVM-Dashboard-3.png)

5. This dasboard displays the following information
- JVM Memory
- process Memory
- CPU-Usage, Threads
- class Loading


## API Request/Responses

### Successful Request

```
curl -X GET "http://localhost:8080/romannumeral?query=100" -H "accept: application/json"  -H "Authorization: Basic dGVzdHVzZXI6U3ByaW5nQDIwMjE="
```

### Successful Response

```
Http Status Code - 200
{
  "input": "100",
  "output": "C"
}
```

### Error Request

```
curl -X GET "http://localhost:8080/romannumeral?query=4000" -H "accept: application/json"  -H "Authorization: Basic dGVzdHVzZXI6U3ByaW5nQDIwMjE="
```

### Error Response

```
Http Status Code - 400
{
   "error":{
      "code":"INVALID_INPUT_RANGE",
      "message":"Input is out of supported range.  Please provide valid number within the range [1 - 3999]"
   }
}
```

## Testing

### Health check

> http://localhost:8080/actuator/health

### Sample test

> http://localhost:8080/romannumeral?query=5

Output:

```
{
"input": "5",
"output": "V"
}
```

### Run Unit & Integration tests

From application root directory[NumberAPI], run the following command
> mvn clean test

```
Test Cases:

NumberConverterControllerTest
- convertToRomanSuccessScenario : Success scenario
- convertToRomanFailureScenario : input number less than min value < 1

NumberConverterServiceImplTest
- convertToRomanNumeralSuccessScenario : 14 Different success scenarios
- convertToRomanNumeralFailureScenario : 3 Different failure scenarios : num < 0 , num = 0, num > 3999

NumbersConverterIntegrationTest
- convertToRoman_successScenario : Success scenario
- convertToRoman_inputEmpty : Input empty
- convertToRoman_outOfRangeNegativeInput : Input -5
- convertToRoman_outOfRangePositiveInput : Input 4500
- convertToRoman_invalidInput : Input a2b
```

## Un-install the application stack

To stop and remove the whole stack run the following command. Make sure you are in the devops folder

```
docker-compose down
```

## Next Steps

- CI/CD Pipeline - Jenkins file
- Load Testing in Pipeline
- Helm Chart for Kubernetes deployments

## Dependency Tree
![](readme-images/DependencyTree-Part1.png)
![](readme-images/DependencyTree-Part2.png)
![](readme-images/DependencyTree-Part3.png)

## References

1. [Roman Numeral Wikipedia Reference](https://simple.wikipedia.org/wiki/Roman_numerals)
2. [spring-boot](https://spring.io/projects/spring-boot)
3. [docker-compose](https://docs.docker.com/compose/)
4. [Splunk](https://docs.splunk.com/Documentation)
5. [Prometheus & Grafana](https://grafana.com/docs/grafana/latest/getting-started/getting-started-prometheus/)
6. [Swagger UI](https://swagger.io/tools/swagger-ui/)