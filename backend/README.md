## general information about the backend

local database h2 available at
http://localhost:8080/h2-console with the database **jdbc:h2:mem:testdb**
with **username:sa password:(empty)**

####For changing the access of different roles look into the Spring Security Config

###GET with user credentials
```
curl localhost:8080/books -u user:password
```

###GET with admin credentials
```
curl localhost:8080/books/1 -u admin:password
```

###POST with admin credentials
```
curl -X POST localhost:8080/books -H "Content-type:application/json" 
	-d {\"name\":\"ABC\",\"author\":\"mkyong\",\"price\":\"8.88\"} -u admin:password
```

