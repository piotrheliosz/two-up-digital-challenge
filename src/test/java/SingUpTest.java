import config.TestSetup;
import config.interfaces.request.SingUpRequest;
import config.interfaces.response.PostSingUpResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

import java.util.Date;

import static config.TestValues.MESSAGE_JSON_PATH;
import static config.TestValues.MESSAGE_USERNAME_JSON_PATH;
import static config.interfaces.request.SingUpRequest.setSingUpRequestBody;
import static config.interfaces.request.SingUpRequest.setSingUpRequestBodyWithUserName;
import static io.restassured.RestAssured.given;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.hamcrest.CoreMatchers.is;

public class SingUpTest extends TestSetup {

    @Test
    public void serviceShouldRespondWithNewUserModel() {
        String firstName = "Nick";
        String lastName = "Cave";
        SingUpRequest requestBody = setSingUpRequestBody(20, firstName, lastName, "1234");

        PostSingUpResponse singUpResponse =
                given()
                        .spec(singUpEndpoint.body(requestBody)).log().all()
                        .when()
                        .post()
                        .then().log().all()
                        .statusCode(200)
                        .and()
                        .extract().as(PostSingUpResponse.class);

        softly.assertThat(new Date(SECONDS.toMillis(singUpResponse.getCreatedAt()))).as("createdAt")
                .isToday();
        softly.assertThat(singUpResponse.getFirstname()).as("firstname")
                .isEqualTo(firstName);
        softly.assertThat(singUpResponse.getId()).as("id")
                .isNotEmpty().isNotNull();
        softly.assertThat(singUpResponse.getLastname()).as("lastName")
                .isEqualTo(lastName);
        softly.assertThat(singUpResponse.getModelType()).as("modelType")
                .isEqualTo("UserModel");
        softly.assertThat(new Date(SECONDS.toMillis(singUpResponse.getUpdatedAt()))).as("updatedAt")
                .isToday();
        softly.assertThat(singUpResponse.getUsername()).as("username")
                .isEqualTo(requestBody.getUsername());
    }

    @Test
    public void serviceShouldRespondWithConflictWhenNameIsNotUnique() {
        String userName = RandomStringUtils.random(10, true, false);
        SingUpRequest requestBody = setSingUpRequestBodyWithUserName(userName);

        given()
                .spec(singUpEndpoint.body(requestBody)).log().all()
                .when()
                .post()
                .then().log().all()
                .statusCode(200);

        given()
                .spec(singUpEndpoint.body(requestBody)).log().all()
                .when()
                .post()
                .then().log().all()
                .statusCode(409)
                .body(MESSAGE_JSON_PATH, is("Username already taken"));

    }

    @Test
    public void serviceShouldRespondWithUnProcessableEntryWhenUsernameContainsNumbers() {
        String userName = RandomStringUtils.random(20, false, true);
        SingUpRequest requestBody = setSingUpRequestBodyWithUserName(userName);

        given()
                .spec(singUpEndpoint.body(requestBody))
                .when()
                .post()
                .then()
                .statusCode(422)
                .body(MESSAGE_JSON_PATH, is("Username must not be a number"));
    }

    @Test
    public void serviceShouldRespondWithUnProcessableEntryWhenUsernameLengthIsLessThan2() {
        SingUpRequest requestBody = setSingUpRequestBody(1, "Piotr", "Heliosz", "1234");

        given()
                .spec(singUpEndpoint.body(requestBody))
                .when()
                .post()
                .then()
                .statusCode(422)
                .body(MESSAGE_JSON_PATH, is("Username length must be between 2 and 20 characters"));
    }

    @Test
    public void serviceShouldRespondWithUnProcessableEntryWhenUsernameLengthIsOver20() {
        SingUpRequest requestBody = setSingUpRequestBody(21, "Piotr", "Heliosz", "1234");

        given()
                .spec(singUpEndpoint.body(requestBody))
                .when()
                .post()
                .then()
                .statusCode(422)
                .body(MESSAGE_JSON_PATH, is("Username length must be between 2 and 20 characters"));
    }

    @Test
    public void serviceShouldRespondWithUnProcessableEntryWhenPasswordLengthIsLessThan4() {
        SingUpRequest requestBody = setSingUpRequestBody(20, "Piotr", "Heliosz", "123");

        given()
                .spec(singUpEndpoint.body(requestBody))
                .when()
                .post()
                .then()
                .statusCode(422)
                .body(MESSAGE_JSON_PATH, is("password length must be between 4 and 20 characters"));
    }

    @Test
    public void serviceShouldRespondWithUnProcessableEntryWhenPasswordLengthOver20() {
        String password = RandomStringUtils.random(21, true, false);
        SingUpRequest requestBody = setSingUpRequestBody(20, "Piotr", "Heliosz", password);

        given()
                .spec(singUpEndpoint.body(requestBody))
                .when()
                .post()
                .then()
                .statusCode(422)
                .body(MESSAGE_JSON_PATH, is("password length must be between 4 and 20 characters"));
    }

    @Test
    public void serviceShouldRespondWithUnProcessableEntryWhenFirstNameLengthLessThan2() {
        String firstName = RandomStringUtils.random(1, true, false);
        SingUpRequest requestBody = setSingUpRequestBody(20, firstName, "Heliosz", "1234");

        given()
                .spec(singUpEndpoint.body(requestBody))
                .when()
                .post()
                .then()
                .statusCode(422)
                .body(MESSAGE_JSON_PATH, is("First name length must be between 2 and 20 characters"));
    }

    @Test
    public void serviceShouldRespondWithUnProcessableEntryWhenFirstNameLengthOver20() {
        String firstName = RandomStringUtils.random(21, true, false);
        SingUpRequest requestBody = setSingUpRequestBody(20, firstName, "Heliosz", "1234");

        given()
                .spec(singUpEndpoint.body(requestBody))
                .when()
                .post()
                .then()
                .statusCode(422)
                .body(MESSAGE_JSON_PATH, is("First name length must be between 2 and 20 characters"));
    }

    @Test
    public void serviceShouldRespondWithUnProcessableEntryWhenLastNameLengthLessThan2() {
        String lastName = RandomStringUtils.random(1, true, false);
        SingUpRequest requestBody = setSingUpRequestBody(20, "Piotr", lastName, "1234");

        given()
                .spec(singUpEndpoint.body(requestBody))
                .when()
                .post()
                .then()
                .statusCode(422)
                .body(MESSAGE_JSON_PATH, is("Last name length must be between 2 and 50 characters"));
    }

    @Test
    public void serviceShouldRespondWithUnProcessableEntryWhenLastNameLengthOver50() {
        String lastName = RandomStringUtils.random(51, true, false);
        SingUpRequest requestBody = setSingUpRequestBody(20, "Piotr", lastName, "1234");

        given()
                .spec(singUpEndpoint.body(requestBody))
                .when()
                .post()
                .then()
                .statusCode(422)
                .body(MESSAGE_JSON_PATH, is("Last name length must be between 2 and 50 characters"));
    }

    @Test
    public void serviceShouldRespondWithBadRequestWhenMissingJsonParameters() {
        given()
                .spec(singUpEndpoint.body("{}"))
                .when()
                .post()
                .then()
                .statusCode(400)
                .body(MESSAGE_USERNAME_JSON_PATH, is("Missing required parameter in the JSON body"));
    }

}
