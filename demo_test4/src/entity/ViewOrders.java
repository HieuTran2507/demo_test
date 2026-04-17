package entity;

public class ViewOrders {
    private Integer orderID;
    private String customerName;
    private String productName;
    private Integer quantity;

    public ViewOrders() {
    }

    public ViewOrders(Integer orderID, String customerName, String productName, Integer quantity) {
        this.orderID = orderID;
        this.customerName = customerName;
        this.productName = productName;
        this.quantity = quantity;
    }

    public Integer getOrderID() {
        return orderID;
    }

    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "ViewOrders{" +
                "orderID=" + orderID +
                ", customerName='" + customerName + '\'' +
                ", productName='" + productName + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
