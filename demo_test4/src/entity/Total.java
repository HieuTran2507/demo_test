package entity;

public class Total {
    private Integer orderID;
    private Double total;

    public Total() {
    }

    public Total(Integer orderID, Double total) {
        this.orderID = orderID;
        this.total = total;
    }

    public Integer getOrderID() {
        return orderID;
    }

    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Total{" +
                "orderID=" + orderID +
                ", total=" + total +
                '}';
    }
}
