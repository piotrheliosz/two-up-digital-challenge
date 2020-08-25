package config.interfaces.response;

import com.google.gson.annotations.SerializedName;
import config.interfaces.bean.Item;

import javax.annotation.Generated;
import java.util.List;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class GetThreadMessageResponse {

    @SerializedName("items")
    private List<Item> mItems;
    @SerializedName("itemsFound")
    private Long mItemsFound;
    @SerializedName("limit")
    private Long mLimit;
    @SerializedName("next")
    private String mNext;
    @SerializedName("previous")
    private String mPrevious;
    @SerializedName("start")
    private Long mStart;

    public List<Item> getItems() {
        return mItems;
    }

    public void setItems(List<Item> items) {
        mItems = items;
    }

    public Long getItemsFound() {
        return mItemsFound;
    }

    public void setItemsFound(Long itemsFound) {
        mItemsFound = itemsFound;
    }

    public Long getLimit() {
        return mLimit;
    }

    public void setLimit(Long limit) {
        mLimit = limit;
    }

    public String getNext() {
        return mNext;
    }

    public void setNext(String next) {
        mNext = next;
    }

    public String getPrevious() {
        return mPrevious;
    }

    public void setPrevious(String previous) {
        mPrevious = previous;
    }

    public Long getStart() {
        return mStart;
    }

    public void setStart(Long start) {
        mStart = start;
    }

}
