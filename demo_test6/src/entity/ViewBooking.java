package entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ViewBooking {
    private String guestName;
    private LocalDate checkin;
    private LocalDate checkout;
    private String roomNumber;
    private Double price;

    public ViewBooking() {
    }

    public ViewBooking(String guestName, LocalDate checkin, LocalDate checkout, String roomNumber, Double price) {
        this.guestName = guestName;
        this.checkin = checkin;
        this.checkout = checkout;
        this.roomNumber = roomNumber;
        this.price = price;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public LocalDate getCheckin() {
        return checkin;
    }

    public void setCheckin(LocalDate checkin) {
        this.checkin = checkin;
    }

    public LocalDate getCheckout() {
        return checkout;
    }

    public void setCheckout(LocalDate checkout) {
        this.checkout = checkout;
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

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

    @Override
    public String toString() {
        return "ViewBooking{" +
                "guestName='" + guestName + '\'' +
                ", checkin=" + checkin.format(formatter) +
                ", checkout=" + checkout.format(formatter) +
                ", roomNumber='" + roomNumber + '\'' +
                ", price=" + price +
                '}';
    }
}
