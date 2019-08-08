package silo.com.silo.UI.Controller;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PostList {
    @SerializedName("data")
    @Expose
    private List<Post> data = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public PostList() {
    }

    /**
     *
     * @param data
     */
    public PostList(List<Post> data) {
        super();
        this.data = data;
    }

    public List<Post> getData() {
        return data;
    }

    public void setData(List<Post> data) {
        this.data = data;
    }
}
