# Toptal Calories Calculator Application

This is the assignment for toptal final screening. The assignment is for the full stack developer role. Frontend of the application is in ReactJS and Backend is in Java Spring Boot. Databases used for the project is Postgres DB.

Video walkthrough is available on the [Link](https://drive.google.com/file/d/1bIfBUAu6eGVWrD6rozMebPA3oLN6Tlxy/view?usp=sharing).

## Backend Application

The application is built using Java Spring Boot and is a REST API. Hikari is used for the Repository management. Swagger is available on [Swagger](http://localhost:8083/caloriesCalculator/swagger-ui/index.html). The application when deployed locally will be available on [http://localhost:8083/](http://localhost:8083/).

Following are the endpoints and their description for the REST API:
#### Auth-Controller
```sh
GET /caloriesCalculator/auth/{token} (Authenticate the token)
```
```sh
GET /caloriesCalculator/auth/getToken/{userId} (Get token for User)
```
```sh
PUT /caloriesCalculator/auth/updateToken/{userId} (Update token for User)
```

#### User-Controller
```sh
GET /caloriesCalculator/users (Get all Users)
```
```sh
POST /caloriesCalculator/users (Add a User)
```
```sh
GET /caloriesCalculator/users/{id} (Get specific User)
```
```sh
DELETE /caloriesCalculator/users/{id} (Delete specific User)
```
```sh
PUT /caloriesCalculator/users/updateCaloriesLimit/{id} (Update calories limit for User)
```
```sh
PUT /caloriesCalculator/users/updateExpenseLimit/{id} (Update expense limit for User)
```

#### Meal-Controller
```sh
GET /caloriesCalculator/meals (Get all Meals)
```
```sh
GET /caloriesCalculator/meals/{id} (Get specific Meal)
```
```sh
DELETE /caloriesCalculator/meals/{id} (Delete specific Meal)
```
```sh
POST /caloriesCalculator/meals/{userId} (Add a Meal for specific User)
```
```sh
GET /caloriesCalculator/meals/allMealsForUser/{userId} (Get all Meals for Specific User sorted on the bases of add Date)
```
```sh
GET /caloriesCalculator/meals/earliestMealDate/{userId} (Get earliest Date for Meal added for specific User)
```
```sh
DELETE /caloriesCalculator/meals/userMeals/{userId} (Delete all Meals for specific User)
```
```sh
GET /caloriesCalculator/meals/userMealsByDate/{userId} (Get all Meals for Specific User on Specific Date)
```

To setup application locally, just download the IDE and execute with Java 11 through main class. If you want to use Docker, use following command to create package
```sh
mvn clean install
```

## Front Application
Frontend of the application is built usin ReactJS. Base URL is available on [http://localhost:3000/](http://localhost:3000/). following are the endpoints for the application:
#### Login
```sh
http://localhost:3000/login
```
#### User
```sh
http://localhost:3000/user
```
#### Admin
```sh
http://localhost:3000/admin
```
To setup the application locally, use following commands:
```sh
npm i
npm start
```
## Databases
Postgres is used as the database in the project. Following are the tables and schemas for them:
#### auth
```sh
user_id (long)
token (varchar)
expiry (timestamp)
```
#### users
```sh
id (long auto-increment)
name (varchar)
role (varchar)
calories_limit (int)
expense_limit (int)
```
#### meals
```sh
id (long auto-increment)
user_id (long)
user_name (varchar)
meal_name (varchar)
calories (int)
price (int)
```
