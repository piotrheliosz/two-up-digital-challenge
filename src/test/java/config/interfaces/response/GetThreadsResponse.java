package config.interfaces.response;

import com.google.gson.annotations.Expose;
import config.interfaces.bean.Item;

import javax.annotation.Generated;
import java.util.List;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class GetThreadsResponse {

    @Expose
    private List<Item> items;
    @Expose
    private int itemsFound;
    @Expose
    private int limit;
    @Expose
    private String next;
    @Expose
    private String previous;
    @Expose
    private int start;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public int getItemsFound() {
        return itemsFound;
    }

    public void setItemsFound(int itemsFound) {
        this.itemsFound = itemsFound;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

}
