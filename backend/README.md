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
  --header 'authorization: Basic YWRtaW46cGFzc3dvcmQ='
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

###post cover image
```
curl --request POST \
  --url http://localhost:8080/api/friendshipbook/image \
  --header 'authorization: Basic YWRtaW46cGFzc3dvcmQ=' \
  --header 'content-type: multipart/form-data; boundary=---011000010111000001101001' \
  --form file=
```
Attention: uses multipart/form-data as content-type
return code CONFLICT, OK or UNAUTHORIZED

###read cover image
```
curl --request GET \
  --url http://localhost:8080/api/friendshipbook/image \
  --header 'authorization: Basic YWRtaW46cGFzc3dvcmQ=' \
  --header 'content-type: multipart/form-data; boundary=---011000010111000001101001'
```
return code NOT_FOUND, OK or UNAUTHORIZED

##Manage Page
###create page
```
curl --request POST \
  --url http://localhost:8080/api/page \
  --header 'authorization: Basic YWRtaW46cGFzc3dvcmQ=' \
  --header 'content-type: application/json' \
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
  --header 'authorization: Basic YWRtaW46cGFzc3dvcmQ='
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
  --data '{
	"uuid": "ef28be80-58ed-41ba-b5f7-5b335335277e"
}'
```
return code CONFLICT, OK or UNAUTHORIZED
Response (Code OK example)
```
[]
```
# Install Keycloak
1. Download Standalone Server Distribution https://www.keycloak.org/downloads.html
2. Unpack ZIP
3. 1. Start Server with ```bin/standalone.sh -Djboss.socket.binding.port-offset=100``` -> The Server should be available under http://localhost:8180/auth2. Then
        add a user ```bin/add-user-keycloak.sh -r master -u admin -p nimdanimda``` and restart the server
    2. Alternative: ```docker run --name keycloak -e KEYCLOAK_USER=admin -e KEYCLOAK_PASSWORD=nimdanimda -p 8180:8180 jboss/keycloak -Djboss.socket.binding.port-offset=100```
5. Access the Server under http://localhost:8180/auth/admin
6. Import JSON ````realm-export.json````

## Create a user in Keycloak
7. Go to Roles > Realm Roles > Add Role > Role Name: user
8. Go to Roles > Default Roles > Realm Roles: select user and click on Add selected
9. Go to Users > Add user > username: user > Save
10. Go to Users > View all users > currently created user: Actions Edit > Credentials > Set Password > password: user > disable temporary > Set Password
11. Role Mappings > Realm Roles > Available Roles > select user and add (if not already assigned)
