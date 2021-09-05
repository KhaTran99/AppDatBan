package location;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.SequenceInputStream;
import java.io.Serializable;

public class Location implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("icon")
    @Expose
    private String icon;

    /**
     * No args constructor for use in serialization
     *
     */
    public Location() {
    }

    /**
     *
     * @param address
     * @param name
     * @param icon
     * @param id
     */
    public Location(Integer id, String name, String address, String icon) {
        super();
        this.id = id;
        this.name = name;
        this.address = address;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

}