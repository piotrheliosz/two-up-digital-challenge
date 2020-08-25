import config.TestSetup;
import config.interfaces.request.ThreadRequest;
import config.interfaces.response.GetThreadsResponse;
import config.interfaces.response.PostThreadResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

import java.util.Date;

import static config.TestValues.MESSAGE_JSON_PATH;
import static config.TestValues.MESSAGE_NAME_JSON_PATH;
import static config.interfaces.request.ThreadRequest.setThreadRequest;
import static io.restassured.RestAssured.given;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.hamcrest.CoreMatchers.is;

public class ThreadsTest extends TestSetup {

    @Test
    public void serviceShouldRespondWithThreadsList() {
        GetThreadsResponse getThreadsResponse =
                given()
                        .spec(threadsEndpoint)
                        .when()
                        .get()
                        .then()
                        .statusCode(200)
                        .extract().as(GetThreadsResponse.class);

        softly.assertThat(getThreadsResponse.getItems()).as("items")
                .isNotNull();
        softly.assertThat(getThreadsResponse.getItems().size())
                .isLessThanOrEqualTo(getThreadsResponse.getLimit());
        softly.assertThat(getThreadsResponse.getItemsFound()).as("itemsFound")
                .isNotNull().isNotNegative();
        softly.assertThat(getThreadsResponse.getLimit()).as("limit")
                .isEqualTo(100);
        softly.assertThat(getThreadsResponse.getNext()).as("next")
                .isNotNull();
        softly.assertThat(getThreadsResponse.getPrevious()).as("previous")
                .isNotNull();
        softly.assertThat(getThreadsResponse.getStart()).as("start")
                .isNotNull();
    }

    @Test
    public void serviceShouldCreateNewThread() {
        String threadName = RandomStringUtils.random(10, true, false);
        ThreadRequest threadRequest = setThreadRequest(threadName, true);

        PostThreadResponse threadResponse =
                given()
                        .spec(threadsEndpoint.body(threadRequest))
                        .when()
                        .post()
                        .then()
                        .statusCode(200)
                        .extract().as(PostThreadResponse.class);

        softly.assertThat(new Date(SECONDS.toMillis(threadResponse.getCreatedAt()))).as("createdAt")
                .isToday();
        softly.assertThat(threadResponse.getDeleted()).as("deleted")
                .isFalse();
        softly.assertThat(threadResponse.getId()).as("id")
                .isNotEmpty().isNotNull();
        softly.assertThat(threadResponse.getModelType()).as("modelType")
                .isEqualTo("ThreadModel");
        softly.assertThat(threadResponse.getName()).as("name")
                .isEqualTo(threadName);
        softly.assertThat(threadResponse.getOwner()).as("owner")
                .isNotEmpty().isNotNull();
        softly.assertThat(threadResponse.getPrivate()).as("private")
                .isTrue();
        softly.assertThat(new Date(SECONDS.toMillis(threadResponse.getUpdatedAt()))).as("updatedAt")
                .isToday();
        softly.assertThat(threadResponse.getUsers()).as("users")
                .hasSize(1);
    }

    @Test
    public void serviceShouldRespondWithUnProcessableEntryWhenNameLengthIsLessThan2() {
        String threadName = RandomStringUtils.random(1, true, false);
        ThreadRequest threadRequest = setThreadRequest(threadName, false);

        given()
                .spec(threadsEndpoint.body(threadRequest))
                .when()
                .post()
                .then()
                .statusCode(422)
                .body(MESSAGE_JSON_PATH, is("Thread name length must be between 2 and 50 characters"));
    }

    @Test
    public void serviceShouldRespondWithUnProcessableEntryWhenNameLengthIsOver50() {
        String threadName = RandomStringUtils.random(51, true, false);
        ThreadRequest threadRequest = setThreadRequest(threadName, false);

        given()
                .spec(threadsEndpoint.body(threadRequest))
                .when()
                .post()
                .then()
                .statusCode(422)
                .body(MESSAGE_JSON_PATH, is("Thread name length must be between 2 and 50 characters"));
    }

    @Test
    public void serviceShouldRespondWithBadRequestWhenMissingJsonParameters() {
        given()
                .spec(threadsEndpoint.body("{}"))
                .when()
                .post()
                .then()
                .statusCode(400)
                .body(MESSAGE_NAME_JSON_PATH, is("Missing required parameter in the JSON body"));
    }

    @Test
    public void serviceShouldRespondWithConflictWhenNameIsNotUnique() {
        String threadName = RandomStringUtils.random(10, true, false);
        ThreadRequest threadRequest = setThreadRequest(threadName, false);

        given()
                .spec(threadsEndpoint.body(threadRequest))
                .when()
                .post()
                .then()
                .statusCode(200);
        given()
                .spec(threadsEndpoint.body(threadRequest))
                .when()
                .post()
                .then()
                .statusCode(409)
                .body(MESSAGE_JSON_PATH, is("Thread name already taken"));
    }

}
