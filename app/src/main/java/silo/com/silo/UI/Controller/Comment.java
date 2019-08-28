package silo.com.silo.UI.Controller;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Comment {
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("user")
    @Expose
    private String user;
    @SerializedName("posting")
    @Expose
    private int posting;

    /**
     * No args constructor for use in serialization
     *
     */
    public Comment() {
    }

    /**
     *
     * @param content
     * @param user
     * @param posting
     */
    public Comment(String content, String user, int posting) {
        super();
        this.content = content;
        this.user = user;
        this.posting = posting;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getPosting() {
        return posting;
    }

    public void setPosting(int posting) {
        this.posting = posting;
    }
}
