package silo.com.silo.UI.Controller;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CommentData {

    @SerializedName("data")
    @Expose
    private Comment data = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public CommentData() {
    }

    /**
     *
     * @param data
     */
    public CommentData(Comment data) {
        super();
        this.data = data;
    }

    public Comment getData() {
        return data;
    }

    public void setData(Comment data) {
        this.data = data;
    }
}
