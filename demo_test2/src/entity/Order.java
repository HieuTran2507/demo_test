package entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Order {
    private Integer id;
    private Integer customerID;
    private LocalDateTime dateTime;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
    public Order() {
    }

    public Order(Integer id, Integer customerID, LocalDateTime dateTime) {
        this.id = id;
        this.customerID = customerID;
        this.dateTime = dateTime;
    }

    public Order(Integer customerID) {
        this.customerID = customerID;
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Integer customerID) {
        this.customerID = customerID;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customerID=" + customerID +
                ", dateTime=" + dateTime.format(formatter) +
                '}';
    }
}
