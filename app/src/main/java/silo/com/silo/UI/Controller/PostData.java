package silo.com.silo.UI.Controller;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostData {
    @SerializedName("data")
    @Expose
    private Post data;

    /**
     * No args constructor for use in serialization
     *
     */
    public PostData() {
    }

    /**
     *
     * @param data
     */
    public PostData(Post data) {
        super();
        this.data = data;
    }

    public Post getData() {
        return data;
    }

    public void setData(Post data) {
        this.data = data;
    }
}
