# I made a project for storing, processing and displaying news.
+ That project include these operations -
```
GET, POST, PUT, DELETE methods for news sources;
GET, POST, PUT, DELETE methods for news;
GET, POST, PUT, DELETE methods for news topics;
GET method for getting list of all news sources;
GET method for getting list of all news topics;
GET method for getting list of all news (with pagination);
GET method for getting list of news by source id (with pagination);
GET method for getting list of news by topic id (with pagination);
```

+ Also -
```
The project contains a security mechanism via the client token API.
The user should receive a token during authentication.

```
+ ### The database used was - MySQL (news_db) -
```
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/news_db
spring.datasource.username=root
spring.datasource.password=

spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5Dialect
spring.jpa.hibernate.naming.physical-strategy=org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl


server.port=9099
```

## 1.First the user must register
POST - http://localhost:9099/auth/new 

![image](https://github.com/KNurassyl/NewsSystem/assets/91272348/98e434f1-c5e8-4a92-a7a6-bdee756172c0)

+ To register, you need to fill out - 
```
{
    "username": "Nurassyl",
    "email": "nurik@gmail.com",
    "password": "12345678"
}
```

- If successfully registered,  the message comes out - `Sucessfully added`
- If there is already a user with this username, it comes out - `Please create another unique username`


## 2. After registration or if the user already has an account, it must pass a login
POST - http://localhost:9099/auth/authenticate

![image](https://github.com/KNurassyl/NewsSystem/assets/91272348/da0d225e-9bce-4b23-ab87-c1dea47b2068)

+ To login - 
```
{
    "username": "Nurassyl",
    "password": "12345678"
}
```
- After a successful login, a token appears that allows us to continue to store, process and display the news. 
- In my case - eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJOdXJhc3N5bCIsImlhdCI6MTY4NDE2MzAzOCwiZXhwIjoxNjg0MTY0ODM4fQ.qekAE7FLzeQDgHid5zQWldOCmAxW5AyC6zUA65Rm_hU
- ### Only using the token we can further do all the above operations

## 3. CRUD operations for `News Sources`
1. `GET` all news sources - http://localhost:9099/news_sources
![image](https://github.com/KNurassyl/NewsSystem/assets/91272348/5bf75206-ae32-4eb7-b90b-c4350a637525)

- By clicking **Authorization > Type > Bearer Token** I pasted token, which I received when I logged in.
- Without this token there will be no access to see all sources of news.

2. `GET` news source by ID - http://localhost:9099/news_sources/2 (instead of 2 we can write any number id)
![image](https://github.com/KNurassyl/NewsSystem/assets/91272348/efb80641-1e54-40d7-bc73-bc350adcc6cf)

3. `POST` - http://localhost:9099/news_sources
![image](https://github.com/KNurassyl/NewsSystem/assets/91272348/2025dcbe-185a-45e9-aebe-70d98927acf6)
- To post  -  
```
{
    "name": "KazTrue",
    "url": "kaz-True.com"
}
```
4. `PUT` - http://localhost:9099/news_sources/4 (instead of 4 we can write any number id)
![image](https://github.com/KNurassyl/NewsSystem/assets/91272348/3e15f43a-0bc3-41f7-8df8-d5a084564bac)
- To put  -  
```
{
    "name": "Kaz-True",
    "url": "kaz-True.com"
}
``` 
5. `DELETE` - http://localhost:9099/news_sources/4 (instead of 4 we can write any number id)
![image](https://github.com/KNurassyl/NewsSystem/assets/91272348/e3d4c15d-c840-4d45-b122-266a7988a456)

## 4. CRUD operations for `News Topic`
1. `GET` all news topics - http://localhost:9099/news_topics
![image](https://github.com/KNurassyl/NewsSystem/assets/91272348/60243647-f2fe-4598-82dd-c2a73aed01b8)
2. `GET` news topic by ID - http://localhost:9099/news_topics/3
![image](https://github.com/KNurassyl/NewsSystem/assets/91272348/054f4bd6-07a4-4041-8cf8-ba7d4994c287)
3. `POST` - http://localhost:9099/news_topics
![image](https://github.com/KNurassyl/NewsSystem/assets/91272348/b073d919-e11c-4d56-b66a-b601098934d2)
- To post  -  
```
{
    "name": "Actors"
}
```
4. `PUT` - http://localhost:9099/news_topics/4
![image](https://github.com/KNurassyl/NewsSystem/assets/91272348/0a33f73b-6c8d-44c7-99be-7569e107e8ba)
- To put  -  
```
{
    "name": "Actor"
}
```
5. `DELETE` - http://localhost:9099/news_topics/4
![image](https://github.com/KNurassyl/NewsSystem/assets/91272348/f1af9113-027c-4daf-945b-a0c1e6e4a808)

## 4. CRUD operations for `News`
1. `GET` all news- http://localhost:9099/news
![image](https://github.com/KNurassyl/NewsSystem/assets/91272348/66b371a3-2618-44cf-9aa6-7b34e78cea32)
2. `GET` news by id - http://localhost:9099/news/7
![image](https://github.com/KNurassyl/NewsSystem/assets/91272348/abe73431-a887-4850-9c7c-fc3478da0a23)
3. `GET` news by source id - http://localhost:9099/news/by_source/2
![image](https://github.com/KNurassyl/NewsSystem/assets/91272348/99365ead-bd06-462d-8ea6-96e09dd9b4bd)
4. `GET` news by topic id - http://localhost:9099/news/by_topic/2
![image](https://github.com/KNurassyl/NewsSystem/assets/91272348/95d93cb8-609f-40f0-a2aa-b9fda3362b57)
5. `POST` - http://localhost:9099/news
- To make a post, we first have to write the following code in the Pre-request Script -
```
var current_timestamp = new Date();
postman.setEnvironmentVariable("current_timestamp", current_timestamp.toISOString());
```
- because in the News class we have a Date called `publishedAt` that records the published date of the news.

![image](https://github.com/KNurassyl/NewsSystem/assets/91272348/89dc24fc-5f63-438d-b68f-1caf88151cfb)

- After that we fill out the body in JSON format 

![image](https://github.com/KNurassyl/NewsSystem/assets/91272348/a2b8b635-d16d-47a3-86ff-dd1fc1265ddf)

- To post  -  
```
{
    "title": "The Lamborghini Huracán Is Sold Out",
    "content": "The Lamborghini is officially sold out. ",
    "publishedAt": "{{current_timestamp}}",
    "source":{
         "id":1
     },
     "topic":{
         "id":2
     }
}
```
6. `PUT` - http://localhost:9099/news/10

![image](https://github.com/KNurassyl/NewsSystem/assets/91272348/e9f74825-d994-439a-ba15-a4fbc86c9cb4)

7. `DELETE` - http://localhost:9099/news/8

![image](https://github.com/KNurassyl/NewsSystem/assets/91272348/59aa05e7-f0ce-4b33-a4e7-b22d8556216d)
