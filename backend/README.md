##Run the backend
####If you haven't installed maven you can use the included maven wrapper!

Build the complete project including backend and frontend
```
mvn clean install -DskipTests
java -jar backend/target/backend<version>.jar
#OR: play button in StartBookApplication.java
```

Running tests
```
mvn test
```

## general information about the backend

local database h2 available at
http://localhost:8080/h2-console with the database **jdbc:h2:mem:testdb**
with **username:sa password:(empty)**

####For changing the access of different roles look into the Spring Security Config

###GET with user credentials
```
curl localhost:8080/books -u user:password
```
Returns 200 and data when successful and 401 when the visitor is not authorized

###GET with admin credentials
```
curl localhost:8080/books/1 -u admin:password
```
Returns 200 and data when successful and 401 when the visitor is not authorized


###POST with admin credentials
```
curl -X POST localhost:8080/books -H "Content-type:application/json" 
	-d {\"name\":\"ABC\",\"author\":\"mkyong\",\"price\":\"8.88\"} -u admin:password
```
Returns 200 and data when successful and 401 when the visitor is not authorized


###save credentials in a cookie
```
curl -i -X POST -d username=user -d password=password -c cookies.txt http://localhost:8080/login
```
Returns 200 and data when successful and 401 when the visitor is not authorized

###use cookie for authentication
```
curl -i --header "Accept:application/json" -X GET -b cookies.txt http://localhost:8080/books
```
Returns 200 and data when successful and 401 when the visitor is not authorized

##Manage Book
###create book
```
curl -i -X POST -u user:password http://localhost:8080/api/friendshipbook
```
Already created return code 400 otherwise code 200 (successful) or 401 (unauthorized)

###update title from book
```
curl -i -X PUT -u user:password http://localhost:8080/api/friendshipbook\?title\=test
```
Without title return code 400 otherwise successful with 200 (succesful) or unauthorized 401
###read book
```
curl -i -X GET -u user:password http://localhost:8080/api/friendshipbook
```
Response 200 if successful or 401 if unauthorized or otherwise 400

```
{"uuid":"6e1e88a5-478e-4763-aaf8-ac4c27fba614","title":"test","user":{"username":"user","mail":null},"pages":[]}
```
###delete book
```
curl -i -X DELETE -u user:password http://localhost:8080/api/friendshipbook
```
Response 200 if successful or 401 if unauthorized or otherwise 400