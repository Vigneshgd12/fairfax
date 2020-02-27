This service has a three major endpoints. 

POST api/v1/articles
GET api/v1/articles/{id}
GET api/v1/tag/{tagName}/{date}

This service can be locally installed and tested. The MongoDB configuration is also explicitly done. If needed it can also be pointed to local instance if you have any

The above points will be explained in detail

1. This is a spring boot application and it is built by gradle. Make sure that your local machine has both Java and gradle installed
This is a easy process and google can help you out with installation.
2. If your local machine has github installed then clone the repository from the link or download the code 
3. Open the project with any IDE and run the FairfaxApplication.java file
4.Hit http://localhost:8081/swagger-ui.html#/ 

you will find two tabs "article-controller" and "tags-controller"
This will list the above endpoints. this is pretty much self explainatory.

To change the database 
go to application.properties and change the value of "spring.data.mongodb.uri"
