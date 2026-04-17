package entity;

import java.time.LocalDateTime;

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

    public Order(Integer customerID, LocalDateTime created) {
        this.customerID = customerID;
        this.created = created;
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

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customerID=" + customerID +
                ", created=" + created +
                '}';
    }
}
