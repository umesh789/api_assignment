package utility;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

import static com.jayway.restassured.RestAssured.given;

public class RestAPIMethods {

    public Response getResponse(String endPoint) {
        Response response = given().
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                when().
                get(endPoint).
                thenReturn();
        return response;
    }

    public Response postResponse(String jsonInString, String endPoint) {
        Response response = given().
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(jsonInString).
                when().
                post(endPoint).
                thenReturn();
        return response;
    }

    public Response putResponse(String jsonInString, String endPoint) {
        Response response = given().
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(jsonInString).
                when().
                put(endPoint).
                thenReturn();
        return response;
    }

    public Response deleteResponse(String endPoint) {
        Response response = given().
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                when().
                delete(endPoint).
                thenReturn();
        return response;
    }
}
