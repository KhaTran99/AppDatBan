package TimeOder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Hours {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("hoursOfDay")
    @Expose
    private String hoursOfDay;

    /**
     * No args constructor for use in serialization
     *
     */
    public Hours() {
    }

    /**
     *
     * @param hoursOfDay
     * @param id
     * @param status
     */
    public Hours(Integer id, Integer status, String hoursOfDay) {
        super();
        this.id = id;
        this.status = status;
        this.hoursOfDay = hoursOfDay;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getHoursOfDay() {
        return hoursOfDay;
    }

    public void setHoursOfDay(String hoursOfDay) {
        this.hoursOfDay = hoursOfDay;
    }

}