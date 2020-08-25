package config;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.assertj.core.api.SoftAssertions;
import org.junit.After;
import org.junit.BeforeClass;

public class TestSetup {

    protected SoftAssertions softly = new SoftAssertions();

    protected static RequestSpecification singUpEndpoint;
    protected static RequestSpecification threadsEndpoint;
    protected static RequestSpecification threadsMessagesEndpoint;

    @BeforeClass
    public static void setupEndpointsAndLogs() {
        singUpEndpoint = buildEndpoint("/signup");

        threadsEndpoint = buildEndpoint("/threads")
                .auth().basic("OTKDtANSCJvQCxWCcvIG", "1234");

        threadsMessagesEndpoint = buildEndpoint("/threads/id/{thread_id}/messages")
                .auth().basic("OTKDtANSCJvQCxWCcvIG", "1234");

        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @After
    public void assetAll() {
        softly.assertAll();
    }

    private static RequestSpecification buildEndpoint(String path) {
        return new RequestSpecBuilder()
                .setBaseUri("http://18.195.251.80")
                .setPort(9000)
                .setBasePath(path)
                .setContentType(ContentType.JSON)
                .build();
    }

}
