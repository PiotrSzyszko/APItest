package codechallenge.apitest;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.junit.Cucumber;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.runner.RunWith;

import static io.restassured.RestAssured.given;


@RunWith(Cucumber.class)
public class Stepdefs {

    private static RequestSpecification requestSpecification;
    private static Response response;
    private static String userName;
    private static String commentID;

    @Given("^User \"([^\"]*)\" logs in$")
    public void user_something_logs_in(String strArg1) throws Throwable {
        userName = strArg1;
    }

    @When("^User creates comment for a gist with ID \"([^\"]*)\"$")
    public void user_creates_comment_for_a_gist_with_id_something(String strArg1) throws Throwable {
        requestSpecification = given().log().all().baseUri("https://api.github.com/gists/" + strArg1 + "/comments")
                .header("Authorization", "Basic cGlvdHIuc3p5c3prb0BnbWFpbC5jb206S3JlbWF0b3JpdW0jMDk=")
                .header("Content-Type", "application/json")
                //.header("Cookie", "_octo=GH1.1.1887974940.1592602259; logged_in=no")
                .body("{\n" +
                        "  \"body\": \"Just commenting for the sake of commenting\"\n" +
                        "}");

        response = requestSpecification.when().post();
    }

    @When("^User gets list of comments for a gist with ID \"([^\"]*)\"$")
    public void user_gets_list_of_comments_for_a_gist_with_id_something(String strArg1) throws Throwable {
        requestSpecification = given().log().all()
                .baseUri("https://api.github.com/gists/" + strArg1 + "/comments")
                .header("Authorization", "Basic cGlvdHIuc3p5c3prb0BnbWFpbC5jb206S3JlbWF0b3JpdW0jMDk=");

        response = requestSpecification.when().get();
        getCommentID();
    }

    public void getCommentID(){
        commentID = response.jsonPath().get("[0].id").toString();
        System.out.println("Comment id: " + commentID);
    }

    @When("^User gets comment for a gist with ID \"([^\"]*)\"$")
    public void user_gets_comment_for_a_gist_with_id_something(String strArg1) throws Throwable {
        requestSpecification = given().log().all()
                .baseUri("https://api.github.com/gists/" + strArg1 + "/comments/" + commentID)
                .header("Authorization", "Basic cGlvdHIuc3p5c3prb0BnbWFpbC5jb206S3JlbWF0b3JpdW0jMDk=");

        response = requestSpecification.when().get();
    }

    @When("^User updates comment for a gist with ID \"([^\"]*)\"$")
    public void user_updates_comment_for_a_gist_with_id_something(String strArg1) throws Throwable {
        requestSpecification = given().log().all().baseUri("https://api.github.com/gists/" + strArg1 + "/comments/" + commentID)
                .header("Authorization", "Basic cGlvdHIuc3p5c3prb0BnbWFpbC5jb206S3JlbWF0b3JpdW0jMDk=")
                .header("Content-Type", "application/json")
                //.header("Cookie", "_octo=GH1.1.1887974940.1592602259; logged_in=no")
                .body("{\n" +
                        "  \"body\": \"Just updating the comment\"\n" +
                        "}");

        response = requestSpecification.when().patch();
    }

    @When("^User deletes comment for a gist with ID \"([^\"]*)\"$")
    public void user_deletes_comment_for_a_gist_with_id_something(String strArg1) throws Throwable {
        requestSpecification = given().log().all()
                .baseUri("https://api.github.com/gists/" + strArg1 + "/comments/" + commentID)
                .header("Authorization", "Basic cGlvdHIuc3p5c3prb0BnbWFpbC5jb206S3JlbWF0b3JpdW0jMDk=");

        response = requestSpecification.when().delete();
    }

    @Then("^Comment is created$")
    public void comment_is_created() throws Throwable {
        Assert.assertNotNull(response);
        Assert.assertEquals(201, response.statusCode());
    }

    @Then("^List is received$")
    public void list_is_received() throws Throwable {
        Assert.assertNotNull(response);
        Assert.assertEquals(200, response.statusCode());
    }

    @Then("^Comment is received$")
    public void comment_is_received() throws Throwable {
        Assert.assertNotNull(response);
        Assert.assertEquals(200, response.statusCode());
    }

    @Then("^Comment is updated$")
    public void comment_is_updated() throws Throwable {
        Assert.assertNotNull(response);
        Assert.assertEquals(200, response.statusCode());
    }

    @Then("^Comment is deleted$")
    public void comment_is_deleted() throws Throwable {
        Assert.assertNotNull(response);
        Assert.assertEquals(204, response.statusCode());
    }


}