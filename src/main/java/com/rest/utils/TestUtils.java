package com.rest.utils;

import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class TestUtils {

    /**
     * This method is used to parse data from the JSONObject
     * @param responsejson
     * @param jpath
     * @return
     */
    public static String getValueByJPath(JSONObject responsejson, String jpath) {

        Object obj = responsejson;
        for (String s : jpath.split("/"))
            if (!s.isEmpty())
                if (!(s.contains("[") || s.contains("]")))
                    obj = ((JSONObject) obj).get(s);
                else if (s.contains("[") || s.contains("]"))
                    obj = ((JSONArray) ((JSONObject) obj).get(s.split("\\[")[0]))
                            .get(Integer.parseInt(s.split("\\[")[1].replace("]", "")));
        return obj.toString();
    }

    public static String getApiResponseWithRetry(String url, int maxRetries){
        int attempt = 0;
        while(attempt < maxRetries){
            try {
                Response response = given().get(url);
                if (response.statusCode() == 200) {
                    return response.asString();
                }
            } catch (Exception e) {
                System.out.println("Retrying request... Attempt " + (attempt + 1));
            }
            attempt++;
        }
        return "API request failed after " + maxRetries + " attempts.";


        }


}
