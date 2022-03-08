package steps;

import api_utils.BaseAPI;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class APIStep {
    String bodyResponseExpect = "{\n" +
            "    \"buyer\": {\n" +
            "        \"id\": 2583,\n" +
            "        \"uid\": \"thuy+exam11@podfoods.co\",\n" +
            "        \"email\": \"thuy+exam11@podfoods.co\",\n" +
            "        \"provider\": \"email\",\n" +
            "        \"first_name\": \"exam\",\n" +
            "        \"last_name\": \"chicago11\",\n" +
            "        \"contact_number\": \"1212121122\",\n" +
            "        \"image\": null,\n" +
            "        \"sub_buyer\": false,\n" +
            "        \"invoice_only\": true,\n" +
            "        \"stripe_customer_id\": \"cus_LEZatn4u2bO01T\",\n" +
            "        \"did_agree_to_terms\": true,\n" +
            "        \"store_id\": 2405,\n" +
            "        \"store_name\": \"exam store\",\n" +
            "        \"buyer_company_id\": 2170,\n" +
            "        \"buyer_company_name\": \"exam buyer company\",\n" +
            "        \"buyer_company_approved\": true,\n" +
            "        \"buyer_company_onboarding_step\": null,\n" +
            "        \"buyer_email_group_ids\": [\n" +
            "            1,\n" +
            "            2\n" +
            "        ],\n" +
            "        \"uneditable_email_group_ids\": [],\n" +
            "        \"plaid\": {\n" +
            "            \"public_token\": null,\n" +
            "            \"status\": null\n" +
            "        }\n" +
            "    }\n" +
            "}";

    private Response response;

    @Given("Call API {}")
    public void callAPI(String url) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Authority", "apibeta.podfoods.co");
        headers.put("content-type", "application/json;charset=UTF-8");
        String body = "{\"email\":\"thuy+exam11@podfoods.co\",\"password\":\"123456789a\"}";
        response = BaseAPI.callPostAPI(url, body, headers);
    }

    @Then("Check response code = {}")
    public void checkResponseCode(int responseCode){
        assertThat(response.getStatusCode()).isEqualTo(responseCode);
    }

    @And("Check response body correct")
    public void checkResponseBody(){
        JsonObject realBodyResponse = new JsonParser().parse(response.getBody().asString()).getAsJsonObject();
        JsonObject expectBodyResponse = new JsonParser().parse(bodyResponseExpect).getAsJsonObject();
        boolean compareResult = realBodyResponse.equals(expectBodyResponse);
        assertThat(compareResult).isTrue();
    }

}
