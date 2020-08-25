package config.interfaces.bean;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Item {

    @SerializedName("createdAt")
    private Long mCreatedAt;
    @SerializedName("deleted")
    private Boolean mDeleted;
    @SerializedName("id")
    private String mId;
    @SerializedName("message")
    private String mMessage;
    @SerializedName("modelType")
    private String mModelType;
    @SerializedName("thread")
    private String mThread;
    @SerializedName("updatedAt")
    private Long mUpdatedAt;
    @SerializedName("user")
    private String mUser;

    public Long getCreatedAt() {
        return mCreatedAt;
    }

    public void setCreatedAt(Long createdAt) {
        mCreatedAt = createdAt;
    }

    public Boolean getDeleted() {
        return mDeleted;
    }

    public void setDeleted(Boolean deleted) {
        mDeleted = deleted;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public String getModelType() {
        return mModelType;
    }

    public void setModelType(String modelType) {
        mModelType = modelType;
    }

    public String getThread() {
        return mThread;
    }

    public void setThread(String thread) {
        mThread = thread;
    }

    public Long getUpdatedAt() {
        return mUpdatedAt;
    }

    public void setUpdatedAt(Long updatedAt) {
        mUpdatedAt = updatedAt;
    }

    public String getUser() {
        return mUser;
    }

    public void setUser(String user) {
        mUser = user;
    }

}
