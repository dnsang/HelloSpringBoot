# SpringDIExample
A simple Spring DI example to get understand how spring DI work.

#Test:
curl --data "id=1&&name=test" -XPOST localhost:8082/add
Using RestAssured to test Restfull service
Example:
        when().get("/hello?id=" + u1.getId()).
        then()
                .statusCode(HttpStatus.SC_OK)
                .body("id", Matchers.is(u1.getId()))
                .body("name", Matchers.is(u1.getName()));