# phonebook-springboot rest api

Whit this simple Java Springboot rest program you can create, read, update and delete people or their phone numbers from the phonebook.

## The following tools have been used in this project:

[Java] (https://www.java.com/en/)
[Spring Boot] (https://spring.io/projects/spring-boot)
[Maven] (https://maven.apache.org/)
[H2-database] (https://www.h2database.com/html/main.html)
[Hibernate] (https://hibernate.org/)

[testing for a program whit Postman] (https://www.postman.com/)

## Deployment
1. Clone this repository `git clone https://github.com/TummeliRuonakoski/phonebook-springboot.git`
2. Open the program and go to folder phonebook in the terminal. Start the program whit the command: `mvn spring-boot:run`
3. The server `localhost:8080` is running
4. Open Postman or h2-database and test
## Postman
### create person
(.../images/createPerson.png)
### get person
(.../images/getPerson.png)
### get all
(.../images/createAll.png)
### update person
(.../images/updatePerson.png)
### detele person
(.../images/deletePerson.png)


## H2 database
1. Go `localhost:8080/h2-console`
2. User name is `sa` and password is empty
(.../images/h2-console.png)
