package config.interfaces.response;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class PostSingUpResponse {

    @SerializedName("createdAt")
    private Long mCreatedAt;
    @SerializedName("firstname")
    private String mFirstname;
    @SerializedName("id")
    private String mId;
    @SerializedName("lastname")
    private String mLastname;
    @SerializedName("modelType")
    private String mModelType;
    @SerializedName("updatedAt")
    private Long mUpdatedAt;
    @SerializedName("username")
    private String mUsername;

    public Long getCreatedAt() {
        return mCreatedAt;
    }

    public void setCreatedAt(Long createdAt) {
        mCreatedAt = createdAt;
    }

    public String getFirstname() {
        return mFirstname;
    }

    public void setFirstname(String firstname) {
        mFirstname = firstname;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getLastname() {
        return mLastname;
    }

    public void setLastname(String lastname) {
        mLastname = lastname;
    }

    public String getModelType() {
        return mModelType;
    }

    public void setModelType(String modelType) {
        mModelType = modelType;
    }

    public Long getUpdatedAt() {
        return mUpdatedAt;
    }

    public void setUpdatedAt(Long updatedAt) {
        mUpdatedAt = updatedAt;
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String username) {
        mUsername = username;
    }

}
