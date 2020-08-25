import config.TestSetup;
import config.interfaces.bean.Item;
import config.interfaces.request.ThreadMessageRequest;
import config.interfaces.request.ThreadRequest;
import config.interfaces.response.GetThreadMessageResponse;
import config.interfaces.response.PostThreadResponse;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Date;

import static config.TestValues.MESSAGE_JSON_PATH;
import static config.interfaces.request.ThreadMessageRequest.setThreadMessageRequest;
import static config.interfaces.request.ThreadRequest.setThreadRequest;
import static io.restassured.RestAssured.given;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assume.assumeFalse;
import static org.junit.Assume.assumeTrue;

public class ThreadsMessagesTest extends TestSetup {

    @BeforeClass
    public static void createTestThread() {
        String threadName = RandomStringUtils.random(10, true, false);
        ThreadRequest threadRequest = setThreadRequest(threadName, false);

        Response threadResponse =
                given()
                        .spec(threadsEndpoint.body(threadRequest)).log().all()
                        .when()
                        .post()
                        .then().log().all()
                        .extract()
                        .response();

        assumeTrue("ThreadsMessagesTest ignored cause POST thread respond with " + threadResponse.statusLine(),
                threadResponse.statusCode() == 200);

        PostThreadResponse postThreadResponse = threadResponse.as(PostThreadResponse.class);
        threadsMessagesEndpoint.pathParam("thread_id", postThreadResponse.getId());
    }

    @Test
    public void serviceShouldPostThreadMessageItem() {
        String messageText = RandomStringUtils.random(300, true, true);
        ThreadMessageRequest threadMessageRequest = setThreadMessageRequest(messageText);

        Item item =
                given()
                        .spec(threadsMessagesEndpoint.body(threadMessageRequest)).log().all()
                        .when()
                        .post()
                        .then().log().all()
                        .extract().as(Item.class);

        itemAssertions(item);
    }

    @Test
    public void serviceShouldRespondWithAlreadyPostedThreadMessageItem() {
        String messageText = RandomStringUtils.random(300, true, true);
        ThreadMessageRequest threadMessageRequest = setThreadMessageRequest(messageText);

        Response postResponse =
                given()
                        .spec(threadsMessagesEndpoint.body(threadMessageRequest)).log().all()
                        .when()
                        .post()
                        .then()
                        .extract().response();

        assumeTrue("Test ignored cause POST message respond with " + postResponse.statusLine(),
                postResponse.statusCode() == 200);

        GetThreadMessageResponse getThreadMessageResponse =
                given()
                        .spec(threadsMessagesEndpoint).log().all()
                        .when()
                        .get()
                        .then().log().all()
                        .statusCode(200)
                        .extract().as(GetThreadMessageResponse.class);

        assumeFalse(getThreadMessageResponse.getItems().isEmpty());

        Item item = getThreadMessageResponse.getItems().stream()
                .filter(i -> i.getMessage().equals(messageText))
                .findAny()
                .orElseThrow(() -> new AssertionError("Posted message was not found in GET response"));

        itemAssertions(item);

        softly.assertThat(getThreadMessageResponse.getItemsFound()).as("itemsFound")
                .isGreaterThanOrEqualTo(1);
        softly.assertThat(getThreadMessageResponse.getLimit()).as("limit")
                .isEqualTo(100);
        softly.assertThat(getThreadMessageResponse.getNext()).as("next")
                .isNotNull();
        softly.assertThat(getThreadMessageResponse.getPrevious()).as("previous")
                .isNotNull();
        softly.assertThat(getThreadMessageResponse.getStart()).as("start")
                .isNotNull();
    }

    @Test
    public void serviceShouldRespondWithUnProcessableEntryWhenMessageLenghtIsOver300() {
        String messageText = RandomStringUtils.random(301, true, true);
        ThreadMessageRequest threadMessageRequest = setThreadMessageRequest(messageText);

        given()
                .spec(threadsMessagesEndpoint.body(threadMessageRequest)).log().all()
                .when()
                .post()
                .then()
                .statusCode(422)
                .body(MESSAGE_JSON_PATH, is("Text has to be between 1 and 300 characters"));
    }

    @Test
    public void serviceShouldRespondWithUnProcessableEntryWhenMessageLengthIsLessThanOne() {
        ThreadMessageRequest threadMessageRequest = setThreadMessageRequest("");

        given()
                .spec(threadsMessagesEndpoint.body(threadMessageRequest)).log().all()
                .when()
                .post()
                .then()
                .statusCode(422)
                .body(MESSAGE_JSON_PATH, is("Text has to be between 1 and 300 characters"));
    }

    private void itemAssertions(Item item) {
        softly.assertThat(new Date(SECONDS.toMillis(item.getCreatedAt()))).as("createdAt")
                .isToday();
        softly.assertThat(item.getDeleted()).as("deleted")
                .isFalse();
        softly.assertThat(item.getId()).as("id")
                .isNotNull().isNotEmpty();
        softly.assertThat(item.getModelType()).as("ThreadMessageModel")
                .isEqualTo("ThreadMessageModel");
        softly.assertThat(item.getThread()).as("thread")
                .isNotNull().isNotEmpty();
        softly.assertThat(new Date(SECONDS.toMillis(item.getUpdatedAt()))).as("updatedAt")
                .isToday();
        softly.assertThat(item.getUser()).as("user")
                .isNotNull().isNotEmpty();
    }

}
