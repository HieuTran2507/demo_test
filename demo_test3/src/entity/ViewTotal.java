package entity;

public class ViewTotal {
    private Integer orderID;
    private Double total;

    public ViewTotal() {
    }

    public ViewTotal(Integer orderID, Double total) {
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
        return "ViewTotal{" +
                "orderID=" + orderID +
                ", total=" + total +
                '}';
    }
}
