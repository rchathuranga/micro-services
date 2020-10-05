# micro-services
#### Training Micro Services with Spring Cloud and Eureka Servers

* Implement Currency Rate Service for getting Currency Rate by Given Two Currency Codes.
* Implement Currency Exchange Service for getting Currency Exchange Rate(Amount) for given Currency Code and Amount to Change.
* Implement Naming Registry Server For Manage Micro Services

#####  Name Every Micro Services in Property File
````
spring.application.name=currency-rate-service
spring.application.name=currency-exchange-service
spring.application.name=naming-server
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

>Add Below Code to Property File in Namig Register server to Avoid 
>Registering Own Server with Naming Registry Server

````
eureka.client.register-with-eureka=false
eureka.client.fetch-registry=false
````  


