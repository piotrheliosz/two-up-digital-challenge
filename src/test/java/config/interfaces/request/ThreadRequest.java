package config.interfaces.request;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class ThreadRequest {

    @SerializedName("name")
    private String mName;
    @SerializedName("private")
    private Boolean mPrivate;

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public Boolean getPrivate() {
        return mPrivate;
    }

    public void setPrivate(Boolean mprivate) {
        mPrivate = mprivate;
    }

    public static ThreadRequest setThreadRequest(String threadName, boolean isPrivate) {
        ThreadRequest threadRequest = new ThreadRequest();
        threadRequest.setName(threadName);
        threadRequest.setPrivate(isPrivate);
        return threadRequest;
    }

}
