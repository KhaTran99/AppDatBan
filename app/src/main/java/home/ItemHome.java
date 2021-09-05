package home;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ItemHome {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("title")
    @Expose
    private String title;

    /**
     * No args constructor for use in serialization
     *
     */
    public ItemHome() {
    }

    /**
     *
     * @param id
     * @param title
     */
    public ItemHome(Integer id, String title) {
        super();
        this.id = id;
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
