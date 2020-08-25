package config.interfaces.request;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class ThreadMessageRequest {

    @SerializedName("message")
    private String mMessage;

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public static ThreadMessageRequest setThreadMessageRequest(String messageText) {
        ThreadMessageRequest threadMessageRequest = new ThreadMessageRequest();
        threadMessageRequest.setMessage(messageText);
        return threadMessageRequest;
    }
}
