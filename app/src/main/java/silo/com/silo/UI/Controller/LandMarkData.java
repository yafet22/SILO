package silo.com.silo.UI.Controller;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LandMarkData {
    @SerializedName("data")
    @Expose
    private LandMark data = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public LandMarkData() {
    }

    /**
     *
     * @param data
     */
    public LandMarkData(LandMark data) {
        super();
        this.data = data;
    }

    public LandMark getData() {
        return data;
    }

    public void setData(LandMark data) {
        this.data = data;
    }
}
