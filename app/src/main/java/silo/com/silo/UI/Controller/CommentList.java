package silo.com.silo.UI.Controller;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CommentList {
    @SerializedName("data")
    @Expose
    private List<Comment> data = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public CommentList() {
    }

    /**
     *
     * @param data
     */
    public CommentList(List<Comment> data) {
        super();
        this.data = data;
    }

    public List<Comment> getData() {
        return data;
    }

    public void setData(List<Comment> data) {
        this.data = data;
    }
}
