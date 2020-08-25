package config.interfaces.response;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;
import java.util.List;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class PostThreadResponse {

    @SerializedName("createdAt")
    private Long mCreatedAt;
    @SerializedName("deleted")
    private Boolean mDeleted;
    @SerializedName("id")
    private String mId;
    @SerializedName("modelType")
    private String mModelType;
    @SerializedName("name")
    private String mName;
    @SerializedName("owner")
    private String mOwner;
    @SerializedName("private")
    private Boolean mPrivate;
    @SerializedName("updatedAt")
    private Long mUpdatedAt;
    @SerializedName("users")
    private List<String> mUsers;

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

    public String getModelType() {
        return mModelType;
    }

    public void setModelType(String modelType) {
        mModelType = modelType;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getOwner() {
        return mOwner;
    }

    public void setOwner(String owner) {
        mOwner = owner;
    }

    public Boolean getPrivate() {
        return mPrivate;
    }

    public void setPrivate(Boolean mprivate) {
        mPrivate = mprivate;
    }

    public Long getUpdatedAt() {
        return mUpdatedAt;
    }

    public void setUpdatedAt(Long updatedAt) {
        mUpdatedAt = updatedAt;
    }

    public List<String> getUsers() {
        return mUsers;
    }

    public void setUsers(List<String> users) {
        mUsers = users;
    }

}
