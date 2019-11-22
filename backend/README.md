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
####For changing the access of different roles, please look into the spring security configuration
##Manage Profile/User
###get user
```
curl --request GET \
  --url http://localhost:8080/api/user \
  --header 'authorization: Basic YWRtaW46cGFzc3dvcmQ=' \
  --cookie JSESSIONID=DA854AEC7E6F1434F8C2D3F7E7F260A0
```
return code CONFLICT, OK or UNAUTHORIZED
Response (Code OK example)
```
{
  "username": "admin",
  "mail": null
}
```
###put user
```
curl --request PUT \
  --url http://localhost:8080/api/user \
  --header 'authorization: Basic YWRtaW46cGFzc3dvcmQ=' \
  --header 'content-type: application/json' \
  --cookie JSESSIONID=DA854AEC7E6F1434F8C2D3F7E7F260A0 \
  --data '{
	"mail": "bookly@cool.de"
}'
```
return code CONFLICT, OK or UNAUTHORIZED
Response (Code OK example)
```
{
  "username": "admin",
  "mail": "bookly@cool.de"
}
```
##Manage Book
###update title from book
```
curl -i -X PUT -u user:password http://localhost:8080/api/friendshipbook\?title\=test
```
return code CONFLICT, OK or UNAUTHORIZED
###read book
```
curl -i -X GET -u user:password http://localhost:8080/api/friendshipbook
```
return code CONFLICT, OK or UNAUTHORIZED
Response (Code OK example)
```
{"uuid":"6e1e88a5-478e-4763-aaf8-ac4c27fba614","title":"test","user":{"username":"user","mail":null},"pages":[]}
```

##Manage Page
###create page
```
curl --request POST \
  --url http://localhost:8080/api/page \
  --header 'authorization: Basic YWRtaW46cGFzc3dvcmQ=' \
  --header 'content-type: application/json' \
  --cookie JSESSIONID=DA854AEC7E6F1434F8C2D3F7E7F260A0 \
  --data '{
	"entries": [
		{
			"title": "Vorname",
			"description": "Bookly"
		},
		{
			"title": "Nachname",
			"description": "Admin"
		},
		{
			"title": "Spitzname",
			"description": "Bobo"
		}
	]
}'
```
return code CONFLICT, OK or UNAUTHORIZED
Response (Code OK example)
```
[
  {
    "uuid": "ef28be80-58ed-41ba-b5f7-5b335335277e",
    "entries": [
      {
        "uuid": "4f0dd9ed-ceb1-4294-9364-8d45d885d190",
        "title": "Vorname",
        "description": "Bookly"
      },
      {
        "uuid": "88d53233-716c-4636-8b18-52feabdd2a66",
        "title": "Nachname",
        "description": "Admin"
      },
      {
        "uuid": "19077881-1f93-45a6-95f3-f87c1eb0b069",
        "title": "Spitzname",
        "description": "Bobo"
      }
    ]
  }
]
```
###get pages
```
curl --request GET \
  --url http://localhost:8080/api/page \
  --header 'authorization: Basic YWRtaW46cGFzc3dvcmQ=' \
  --cookie JSESSIONID=DA854AEC7E6F1434F8C2D3F7E7F260A0
```
return code CONFLICT, OK or UNAUTHORIZED
Response (Code OK example)
```
[
  {
    "uuid": "ef28be80-58ed-41ba-b5f7-5b335335277e",
    "entries": [
      {
        "uuid": "4f0dd9ed-ceb1-4294-9364-8d45d885d190",
        "title": "Vorname",
        "description": "Bookly"
      },
      {
        "uuid": "88d53233-716c-4636-8b18-52feabdd2a66",
        "title": "Nachname",
        "description": "Admin"
      },
      {
        "uuid": "19077881-1f93-45a6-95f3-f87c1eb0b069",
        "title": "Spitzname",
        "description": "Bobo"
      }
    ]
  }
]
```

###update pages
```
curl --request PUT \
  --url http://localhost:8080/api/page \
  --header 'authorization: Basic YWRtaW46cGFzc3dvcmQ=' \
  --header 'content-type: application/json' \
  --cookie JSESSIONID=DA854AEC7E6F1434F8C2D3F7E7F260A0 \
  --data '{
	"uuid": "ef28be80-58ed-41ba-b5f7-5b335335277e",
	"entries": [
		{
			"title": "Vorname",
			"description": "The bookly project"
		}
	]
}'
```
return code CONFLICT, OK or UNAUTHORIZED
Response (Code OK example)
```
[
  {
    "uuid": "ef28be80-58ed-41ba-b5f7-5b335335277e",
    "entries": [
      {
        "uuid": "65d29426-7c56-4615-9f36-979c2099c86d",
        "title": "Vorname",
        "description": "The bookly project"
      }
    ]
  }
]
```

###delete pages
```
curl --request DELETE \
  --url http://localhost:8080/api/page \
  --header 'authorization: Basic YWRtaW46cGFzc3dvcmQ=' \
  --header 'content-type: application/json' \
  --cookie JSESSIONID=DA854AEC7E6F1434F8C2D3F7E7F260A0 \
  --data '{
	"uuid": "ef28be80-58ed-41ba-b5f7-5b335335277e"
}'
```
return code CONFLICT, OK or UNAUTHORIZED
Response (Code OK example)
```
[]
```