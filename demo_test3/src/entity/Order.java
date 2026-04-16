package entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Order {
    private Integer id;
    private Integer customerID;
    private LocalDateTime created;

    public Order() {
    }

    public Order(Integer id, Integer customerID, LocalDateTime created) {
        this.id = id;
        this.customerID = customerID;
        this.created = created;
    }

    public Order(Integer customerID) {
        this.customerID = customerID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Integer customerID) {
        this.customerID = customerID;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customerID=" + customerID +
                ", created=" + created.format(formatter) +
                '}';
    }
}
