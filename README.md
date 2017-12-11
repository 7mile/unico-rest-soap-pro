# unico-rest-soap-pro
An enterprise Java application that implements RESTful and SOAP web services that is secure.

Users can push 2 integers and get a list of all the elements ever pushed to the application
through RESTful services. The integers will be sent to a JMS Queue and stored in H2 database(other
databases are also supported).

Users can get the Greatest Common Divisor (GCD) of 2 integers from the head of the Queue, get the
calculated GCD list from database and get the sum of all calculated GCD list through SOAP web services.

In order to keep thread-safety and the order when getting 2 messages from the JMS Queue, use synchronized
to ensure every request get the correct 2 messages from the head of the JMS Queue.

# Used Technology:
    Java 8, Spring Boot, Spring MVC, Spring, JPA, Hibernate, ActiveMQ, H2 database, Spring-WS, JUnit,
    Mockito, Spring Test, Tomcat, Logback


# Building:

Check out the code using command:

git clone https://github.com/7mile/unico-rest-soap-pro directory-cloned-into

Navigate to the directory-cloned-into and run command:

mvn package

This project can be built into jar or war file, the default is war file.

This will run all tests and build the application into:
directory-cloned-into/target/rest-soap-service-0.1.0.war

# Running:

unzip directory-cloned-into/target/rest-soap-service-0.1.0.war to tomcat's webapps directory,like:
apache-tomcat-8.0.24\webapps\ROOT

Run command: ./apache-tomcat-8.0.24/bin/start.sh

# User Example :
Push 2 integers to the Application using curl:

    curl -d "i1=10&i2=20" http://localhost:8080/item/push

Get a list of all the elements ever pushed to the Application using curl:

    curl http://localhost:8080/item/all


Access SOAP Service:

Create a file named request.xml, like below:
```
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
				  xmlns:gs="http://www.unico.com.au/gcd-ws">
   <soapenv:Header/>
   <soapenv:Body>
      <gs:getGcdRequest>
      </gs:getGcdRequest>
   </soapenv:Body>
</soapenv:Envelope>
```

get the gcd from the queue

curl --header "content-type: text/xml" -d @request.xml http://localhost:8080/ws

To access gcdList() service change ```<gs:getGcdListRequest>``` to ```<gs:getGcdListRequest>```

To access gcdList() service change ```<gs:getGcdSumRequest>``` to ```<gs:getGcdSumRequest>```

#Trouble Shooting:

This application use logback to do logging.
You can find the log file in log directory with name unico-rest-soap.log.
You can also set the log level in the file application.properties(in directory src/main/resources).
