package config.interfaces.request;

import com.google.gson.annotations.Expose;
import org.apache.commons.lang3.RandomStringUtils;

import javax.annotation.Generated;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class SingUpRequest {

    @Expose
    private String firstname;
    @Expose
    private String lastname;
    @Expose
    private String password;
    @Expose
    private String username;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public static SingUpRequest setSingUpRequestBodyWithUserName(String userName) {
        SingUpRequest singUpRequest = setSingUpRequestBody(0, "Nick", "Cave", "1234");
        singUpRequest.setUsername(userName);
        return singUpRequest;
    }

    public static SingUpRequest setSingUpRequestBody(int userNameLength, String firstName, String lastName, String password) {
        SingUpRequest requestBody = new SingUpRequest();
        requestBody.setUsername(RandomStringUtils.random(userNameLength, true, false));
        requestBody.setFirstname(firstName);
        requestBody.setLastname(lastName);
        requestBody.setPassword(password);
        return requestBody;
    }
}
