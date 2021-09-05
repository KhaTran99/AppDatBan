package menu;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Menu {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("icon")
    @Expose
    private String icon;

    /**
     * No args constructor for use in serialization
     *
     */
    public Menu() {
    }

    /**
     *
     * @param name
     * @param icon
     * @param id
     * @param content
     */
    public Menu(Integer id, String name, String content, String icon) {
        super();
        this.id = id;
        this.name = name;
        this.content = content;
        this.icon = icon;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

}