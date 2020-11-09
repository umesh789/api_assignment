package apiStepDefs;

import PayLoad.CategoryPayLoad;
import PayLoad.CreateNewPetPayLoad;
import PayLoad.TagsPayLoad;
import apiStaticData.RestEndPoint;
import base.TestBase;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.restassured.response.Response;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import utility.RestAPICommanHeaders;
import utility.RestAPIMethods;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.*;

public class PetStoreAPIStepdefs extends TestBase {

    private Response response;
    private RestEndPoint restEndPoints;
    private RestAPIMethods restApiCalls;
    private RestAPICommanHeaders restAPICommanHeaders;
    private CategoryPayLoad categoryPayLoad= new CategoryPayLoad();
    private TagsPayLoad tagsPayLoad= new TagsPayLoad();
    private CreateNewPetPayLoad createNewPetPayLoad =new CreateNewPetPayLoad();
    public ObjectMapper mapper = new ObjectMapper();
    private String jsonInString;
    private static String petId;

    public PetStoreAPIStepdefs() {
        restEndPoints = new RestEndPoint(API_environment.toLowerCase().trim());
        restApiCalls = new RestAPIMethods();
        restAPICommanHeaders = new RestAPICommanHeaders();
    }

    @When("Get available pet API")
    public void getAvailablePetAPI() {
        response=restApiCalls.getResponse(restEndPoints.getPET_STORE_HOST()+"pet/"+petId);
    }

    @Then("Verify Get available pet API response {int}.")
    public void verifyGetAvailablePetAPIResponse(int statusCode) {
        Assert.assertEquals("API Response=" + response.prettyPrint(), statusCode, response.getStatusCode());
    }

    @And("Verify category name should be {string}")
    public void verifyCategoryNameShouldBe(String name) {
      String cat_name=response.jsonPath().getString("category.name");
        assertEquals(cat_name,name);
    }

    @And("Verify status should be {string}")
    public void verifyStatusShouldBe(String status) {
        String cat_status=response.jsonPath().getString("status");
        assertEquals(cat_status,status);
    }

    @Given("Create request body with new pet details")
    public void createRequestBodyWithNewPetDetails(List<Map<String, String>> inputParam) throws JsonProcessingException {
        categoryPayLoad.setId(0);
        categoryPayLoad.setName(inputParam.get(0).get("cat_Name"));
        tagsPayLoad.setName(inputParam.get(0).get("tagName"));
        tagsPayLoad.setId(2);
        createNewPetPayLoad.setCategory(categoryPayLoad);
        createNewPetPayLoad.setTags(Collections.singletonList(tagsPayLoad));
        createNewPetPayLoad.setStatus(inputParam.get(0).get("status"));
        createNewPetPayLoad.setPhotoUrls(Collections.singletonList(inputParam.get(0).get("photoUrls")));
        createNewPetPayLoad.setName(inputParam.get(0).get("name"));
        jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(createNewPetPayLoad);
        System.out.println(jsonInString);
    }

    @When("Run Create a new pet API")
    public void runCreateANewPetAPI() {
        response=restApiCalls.postResponse(jsonInString,restEndPoints.getPET_STORE_HOST()+"pet");
        response.prettyPrint();
    }

    @And("Verify category created successfully and status should be {string}")
    public void verifyCategoryCreatedSuccessfullyAndStatusShouldBe(String exp_status) {
        petId=response.jsonPath().getString("id");
        String act_status=response.jsonPath().getString("status");
        assertEquals(act_status,exp_status);
      }

    @Given("Create request body with update pet details")
    public void createRequestBodyWithUpdatePetDetails(List<Map<String, String>> inputParam) throws JsonProcessingException {
        categoryPayLoad.setId(0);
        categoryPayLoad.setName(inputParam.get(0).get("cat_Name"));
        tagsPayLoad.setName(inputParam.get(0).get("tagName"));
        tagsPayLoad.setId(2);
        createNewPetPayLoad.setCategory(categoryPayLoad);
        createNewPetPayLoad.setTags(Collections.singletonList(tagsPayLoad));
        createNewPetPayLoad.setStatus(inputParam.get(0).get("status"));
        createNewPetPayLoad.setPhotoUrls(Collections.singletonList(inputParam.get(0).get("photoUrls")));
        createNewPetPayLoad.setName(inputParam.get(0).get("name"));
        createNewPetPayLoad.setId(petId);
        jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(createNewPetPayLoad);
        System.out.println(jsonInString);
    }

    @When("Run update pet API")
    public void runUpdatePetAPI() {
        response=restApiCalls.putResponse(jsonInString,restEndPoints.getPET_STORE_HOST()+"pet");
        response.prettyPrint();
    }

    @When("run delete pet API where pet id is given")
    public void runDeletePetAPIWherePetIdIsGiven() {
        response=restApiCalls.deleteResponse(restEndPoints.getPET_STORE_HOST()+"pet/"+petId);
        response.prettyPrint();
    }
}
