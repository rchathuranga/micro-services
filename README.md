# micro-services
#### Training Micro Services with Spring Cloud and Eureka Servers

* Implement Currency Rate Service for getting Currency Rate by Given Two Currency Codes.
* Implement Currency Exchange Service for getting Currency Exchange Rate(Amount) for given Currency Code and Amount to Change.
* Implement Naming Registry Server For Manage Micro Services
* Implement API Gateway to expose Micro Services to public

#####  Name Every Micro Services in Property File
````
spring.application.name=currency-rate-service
spring.application.name=currency-exchange-service
spring.application.name=naming-server
spring.application.name=api-gateway
````

##### Common Dependencies
```sh
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-config</artifactId>
</dependency>
```

##### Dependencies For Load Balancing
```sh
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-openfeign</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-ribbon</artifactId>
</dependency>
```

> Enable Feign Client in SpringBoot Application Starter Class by 

````
@EnableFeignClients
````

> Enable Discovery Client for Register MicroService with Naming Registry Server by 

````
@EnableDiscoveryClient
````

>Register MicroService with Naming Registry Server By Giving Server location by adding below code to
>property file in micro services applications
````
eureka.client.service-url.default-zone=http://localhost:8761/eureka
````

##### Steps to Implement Naming Registry Server
> Dependencies Add for Naming Registry Server, Over Cloud Dependencies
```sh
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
    <version>2.2.5.RELEASE</version>
</dependency>
```

> Enable Eureka Server by Annotating SpringBoot Application Starter Class
```sh
@EnableEurekaServer
````

>Add Below Code to Property File in Naming Register server to Avoid 
>Registering Own Server with Naming Registry Server

````
eureka.client.register-with-eureka=false
eureka.client.fetch-registry=false
````  

##### Steps to Implement API Gateway

> Dependencies Add for API Gateway Server, Over Cloud Dependencies
```sh
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-zuul</artifactId>
</dependency>
```

> Enable Discovery Client and Zuul Poxy by Annotating SpringBoot Application Starter Class.
> Zuul provides capabilities for dynamic routing, monitoring, resiliency and security.

```sh
@EnableDiscoveryClient
@EnableZuulProxy
````

>Add Below Code to Property File(.yml) in API Gateway server to register with Naming Registry server.

```sh
eureka:
  client:
    service-url:
      default-zone: http://localhost:8761/eureka
````

>To handle the request passing through API Gateway, you can create filters with
>various instance by extending `ZuulFilter` and setting Filter Type. Below are some Filter Types

```sh
FilterConstants.PRE_TYPE    Handle request before hit to relevant service
FilterConstants.POST_TYPE   Handle request after hit to relevant service
FilterConstants.ERROR_TYPE  Handle errors
````

>You can make request to relevant micro-service through API Gateway by
>adding micro-service name to base url of API Gateway

````sh
http://localhost:8000/currency-rate-service/...
http://localhost:8000/currency-exchange-service/...
````
