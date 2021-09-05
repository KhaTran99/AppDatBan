package TimeOder;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Day {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("dayOfTheWeek")
    @Expose
    private String dayOfTheWeek;
    @SerializedName("date")
    @Expose
    private String date;

    /**
     * No args constructor for use in serialization
     *
     */
    public Day() {
    }

    /**
     *
     * @param date
     * @param dayOfTheWeek
     * @param id
     */
    public Day(Integer id, String dayOfTheWeek, String date) {
        super();
        this.id = id;
        this.dayOfTheWeek = dayOfTheWeek;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDayOfTheWeek() {
        return dayOfTheWeek;
    }

    public void setDayOfTheWeek(String dayOfTheWeek) {
        this.dayOfTheWeek = dayOfTheWeek;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
