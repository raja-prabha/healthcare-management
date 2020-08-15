# Health Care

Springboot app.


## Endpoints

##### 1. Add a customer profile

```
POST /createCustomer

Request params

{
“first_name":”Raja Prabha”,
"last_name”:”Shankar”,
"phone_number":”7483929372”,
"email_id":”rajaprabha@gmail.com”}

HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8

Response
{
"customerId":1,
“first_name":”Raja Prabha”,
"last_name”:”Shankar”,
"phone_number":”7483929372”,
"email_id":”rajaprabha@gmail.com”,
”signup_date”:"2020-08-15T09:47:38.3224328"",

}

HTTP/1.1 500 SERVER_ERROR
{
  message:"Server Error",
  details:["Error message"]
}

HTTP/1.1 400 BAD_REQUEST
{
  message:"Validation Error",
  details:["Error message"]
}
```


##### 2. Edit Profile(Only name)



```
PUT /editCustomer/{id}

@pathParam id -> Customer ID

Request params

{
“first_name":”Raja Prabha”,
"last_name”:”Shankar”,
}

HTTP 200 OK
Content-Type: application/json;charset=UTF-8

Response
{
"customerId":1,
“first_name":”Raja Prabha”,
"last_name”:”Shankar”,
"phone_number":”7483929372”,
"email_id":”rajaprabha@gmail.com”,
”signup_date”:"2020-08-15T09:47:38.3224328"",

}

HTTP: 404 NOT_FOUND
{
 message:"Not found",
details:["User not found"]
}

HTTP: 500 SERVER_ERROR
{
  message:"Server Error",
  details:["Error message"]
}

HTTP: 400 BAD_REQUEST
{
  message:"Validation Error",
  details:["Error message"]
}
```


##### 3. Add Medical History

```
POST /addMedicalHistory

Request params

{
”customerId”:1,
“type”:”DENTIST”,
”status”:”INPROGRESS”,
”description”:”Treatment”,
”amount”:20000}

HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8

Response
{
“historyId “:1 ,
”customerId”:1,
“type”:”DENTIST”,
”status”:”INPROGRESS”,
”description”:”Treatment”,
”amount”:20000
}

HTTP/1.1 500 SERVER_ERROR
{
  message:"Server Error",
  details:["Error message"]
}

HTTP/1.1 400 BAD_REQUEST
{
  message:"Validation Error",
  details:["Error message"]
}
```


##### 4. Update Medical history

```
PUT /editCustomer/{id}

@pathParam id -> Customer ID

Request params

{
“description":"Treatment on Aug",
"status”:”DONE”,
}

HTTP 200 OK
Content-Type: application/json;charset=UTF-8

Response
{
“historyId “:1 ,
”customerId”:1,
“type”:”DENTIST”,
”status”:”DONE”,
”description”:”Treatment on Aug”,
”amount”:20000
}

HTTP: 404 NOT_FOUND
{
 message:"Not found",
details:["User not found"]
}

HTTP: 500 SERVER_ERROR
{
  message:"Server Error",
  details:["Error message"]
}

HTTP: 400 BAD_REQUEST
{
  message:"Validation Error",
  details:["Error message"]
}
```

##### 5. Add/Update Payment record


```
POST /updatePayment/{id}

@pathParam id -> Customer ID


Request params

{
“paidAmount”:12000
}


HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8

Response
{
“paymentId “:1 ,
”customerId”:1,
“paidAmount”:12000
}


HTTP/1.1 500 SERVER_ERROR
{
  message:"Server Error",
  details:["Error message"]
}

HTTP/1.1 400 BAD_REQUEST
{
  message:"Validation Error",
  details:["Error message"]
}
```


##### 6. Get the payment

```
GET /getPayment/{id}

@pathParam id -> Customer ID


HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8

Response
{
"customerId":"1",
"total":"20000",
"paid":"12000"
"excess":"0",
"due":"8000"

}

HTTP/1.1 500 SERVER_ERROR
{
  message:"Server Error",
  details:["Error message"]
}

HTTP: 404 NOT_FOUND
{
 message:"Not found",
details:["User not found"]
}

```