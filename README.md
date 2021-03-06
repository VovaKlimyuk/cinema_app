# <p>Cinema app </p>

Cinema is a simple API implemented with Hibernate and Spring that 
allows you to register, select a movie session or book a ticket, 
view attendance history if you are a user, an administrator can create a movie session and more.

# About project
The application has the ability:
- Register new user
- Cinema hall add or get all
- Add a movie or get all
- Movie session add, find by date, update or delete
- Complete the order or get the order story
- Add a shopping cart, get a shopping cart for the user
- Find user by email

There are two types of roles in the application: `ADMIN` and `USER`. </br>
During registration, the user is assigned a role USER.
###### All users have access to pages:
`/register` </br>
`/login` </br>
###### Users with role Admin or User have access to pages:
`GET: /cinema-halls` - get a list of cinema halls </br>
`GET: /movies` - get a list of movies </br>
`GET: /movie-sessions/available` - get a list of all movie sessions for a movie (by id) on a date </br>
###### Users with role Admin have access to pages:
`POST: /movies` - add movie </br>
`POST: /movie-sessions` - add movie session </br>
`POST: /cinema-halls` - add cinema hall </br>
`GET: /users/by-email` - get user by email </br>
`PUT: /shopping-carts/movie-sessions` - add movie session to shopping cart</br>
`DELETE: /movie-sessions/{id}` - delete movie session by id </br>
###### Users with role User have access to pages:
`GET: /orders` - get a list of user's orders </br>
`GET: /shopping-carts/by-user` - get user's shopping cart </br>
`POST: /orders/complete` - add order </br>
`PUT: /shopping-carts/movie-sessions` - change movie session (by id) from shopping cart </br>

# Technologies used:
- Java 11
- Hibernate
- Spring Framework
- REST
- MySQL
- Apache Tomcat 9.0.54

### If you want to run this project on your computer, you need:
1. To have or install MySQL and Apache Tomcat 9.0.50
2. Clone this project:
```bash
git clone https://github.com/VovaKlimyuk/cinema_app.git
```
3. Configure `dbProperties` file from `resources` directory to create connection to db:
```java
    db.driver=com.mysql.cj.jdbc.Driver
    db.url=YOUR_URL
    db.user=YOUR_LOGIN
    db.password=YOUR_PASSWORD
```
4. Run project.

After all these steps you will be able to run this project locally.


Then you can log in with :

`USER` roles:
```java
username - vova@google.com
password - vova1234
```
`ADMIN` roles:
```java
username - admin@google.com
password - admin2002
```