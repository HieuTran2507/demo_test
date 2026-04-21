package entity;

public class BookingItem {
    private Integer id;
    private Integer bookingID;
    private Integer roomID;

    public BookingItem() {
    }

    public BookingItem(Integer id, Integer bookingID, Integer roomID) {
        this.id = id;
        this.bookingID = bookingID;
        this.roomID = roomID;
    }

    public BookingItem(Integer bookingID, Integer roomID) {
        this.bookingID = bookingID;
        this.roomID = roomID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBookingID() {
        return bookingID;
    }

    public void setBookingID(Integer bookingID) {
        this.bookingID = bookingID;
    }

    public Integer getRoomID() {
        return roomID;
    }

    public void setRoomID(Integer roomID) {
        this.roomID = roomID;
    }

    @Override
    public String toString() {
        return "BookingItem{" +
                "id=" + id +
                ", bookingID=" + bookingID +
                ", roomID=" + roomID +
                '}';
    }
}
