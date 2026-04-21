package entity;

public class Room {
    private Integer id;
    private String roomNumber;
    private Double price;
    private String status;

    public Room() {
    }

    public Room(Integer id, String roomNumber, Double price, String status) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.price = price;
        this.status = status;
    }

    public Room(String roomNumber, Double price, String status) {
        this.roomNumber = roomNumber;
        this.price = price;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", roomNumber='" + roomNumber + '\'' +
                ", price=" + price +
                ", status='" + status + '\'' +
                '}';
    }
}
