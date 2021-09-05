package order;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Oder {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("amount")
    @Expose
    private Integer amount;
    @SerializedName("day")
    @Expose
    private String day;
    @SerializedName("hours")
    @Expose
    private String hours;
    @SerializedName("totalBill")
    @Expose
    private Integer totalBill;
    @SerializedName("note")
    @Expose
    private String note;
    @SerializedName("status")
    @Expose
    private Integer status;

    /**
     * No args constructor for use in serialization
     *
     */
    public Oder() {
    }

    /**
     *
     * @param note
     * @param amount
     * @param hours
     * @param totalBill
     * @param location
     * @param id
     * @param day
     *
     * @param status
     */
    public Oder(Integer id, String location, Integer amount, String day, String hours, Integer totalBill, String note, Integer status) {
        super();
        this.id = id;
        this.location = location;
        this.amount = amount;
        this.day = day;
        this.hours = hours;
        this.totalBill = totalBill;
        this.note = note;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public Integer getTotalBill() {
        return totalBill;
    }

    public void setTotalBill(Integer totalBill) {
        this.totalBill = totalBill;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}