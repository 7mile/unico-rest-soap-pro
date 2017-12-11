# unico-rest-soap-pro
An enterprise Java application that implements RESTful and SOAP web services that is secure.

Users can push 2 integers and get a list of all the elements ever pushed to the application
through RESTful services. The integers will be sent to a JMS Queue and stored in H2 database(other
databases are also supported).

Users can get the Greatest Common Divisor (GCD) of 2 integers from the head of the Queue, get the
calculated GCD list from database and get the sum of all calculated GCD list through SOAP web services.

# Building:

Check out the code using command:
git clone https://github.com/7mile/unico-rest-soap-pro directory-cloned-into

Navigate to the directory-cloned-into and run command:
mvn package

This will run all tests and build the application into:
directory-cloned-into/target/rest-soap-service-0.1.0.jar

# Running:

In the directory-cloned-into/target

Run command: java -jar rest-soap-service-0.1.0.jar

# User Example:
Push 2 integers to the Application using curl:
    curl -d "i1=10&i2=20" http://localhost:8080/item/push

Get a list of all the elements ever pushed to the Application using curl:
    curl http://localhost:8080/item/all

