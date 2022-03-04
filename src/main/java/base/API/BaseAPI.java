package base.API;

import com.google.gson.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.RequestSpecification;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class BaseAPI {
    protected Response response;

    public Response sendPost(String url, Object body){
        response = given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post(url)
                .then()
                .extract().response();
        return response;
    }
    public String getJsonAsString() {
        return this.response.body().asString();
    }
    public <T> Object saveResponseObject(Class<T> c) {
        try {
            return parseJsonToModel(this.getJsonAsString(), c);
        } catch (Exception var3) {
            var3.printStackTrace();
            return null;
        }
    }
    public static <T> Object parseJsonToModel(String json, Class<T> c) {
        JsonDeserializer<T> deserializer = new JsonDeserializer<T>() {
            public T deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                return json.isJsonNull() ? null : (new Gson()).fromJson(json, typeOfT);
            }
        };
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(c, deserializer);
        Gson gson = gsonBuilder.create();
        return gson.fromJson(json, c);
    }

}
