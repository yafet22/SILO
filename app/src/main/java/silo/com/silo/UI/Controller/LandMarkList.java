package silo.com.silo.UI.Controller;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LandMarkList {
    @SerializedName("data")
    @Expose
    private List<LandMark> data = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public LandMarkList() {
    }

    /**
     *
     * @param data
     */
    public LandMarkList(List<LandMark> data) {
        super();
        this.data = data;
    }

    public List<LandMark> getData() {
        return data;
    }

    public void setData(List<LandMark> data) {
        this.data = data;
    }
}
