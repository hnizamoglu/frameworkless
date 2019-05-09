# Simple REST Application Without Using Any Framework
This is a demo application that does not use any framework for REST services. It is lightweight and serve as an HTTP server.

The application simulates fund transfer between accounts, querying accounts and creating accounts.

* For demo purposes, data resides in memory as `Map`.
* For the simplicity, there are two dependencies in total. One of them is **JUnit**, the other one is **Jackson JSON** library.

### Compile

`mvn package`

### Run

`java -jar {PATH-TO-JAR}`

### Endpoints
Once the server boots up, it should listen on port **8080** by default.

1. #### Transfer Funds
* Method : `POST`
* URL : `/api/transfer`

* Example Data :
```javascript
{
	"sender":0,
	"receiver":1,
	"amount":40
}
```
* Output :
```javascript
{
    "message": "Transfer Complete",
    "data": null
}
```

2. #### Query Account /w Balances
* Method : `GET`
* URL : `/api/transfer?id={accountId}`
* Output :
```javascript
{
    "message": "OK",
    "data": {
        "id": 0,
        "name": "John Doe",
        "balance": 100
    }
}
```

3. #### Create Account
* Method : `POST`
* URL : `/api/transfer/create`
* Example Data :
```javascript
{
	"name":"John Doe",
	"balance":100
}
```
* Output :
```javascript
{
    "message": "Account Created",
    "data": {
        "id": 0,
        "name": "John Doe",
        "balance": 100
    }
}
```
