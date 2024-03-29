package silo.com.silo.UI.Controller;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LandMark {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("photo")
    @Expose
    private String photo;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("category")
    @Expose
    private String category;
//    @SerializedName("rating")
//    @Expose
//    private Object rating;

    /**
     * No args constructor for use in serialization
     *
     */
    public LandMark() {
    }

    /**
     *
     * @param id
     * @param category
     * @param title
     * @param description
     * @param photo
     */
    public LandMark(int id, String title, String photo, String description, String category) {
        super();
        this.id = id;
        this.title = title;
        this.photo = photo;
        this.description = description;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

//    public Object getRating() {
//        return rating;
//    }
//
//    public void setRating(Object rating) {
//        this.rating = rating;
//    }
}
