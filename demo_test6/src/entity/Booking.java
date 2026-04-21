package entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Booking {
    private Integer id;
    private Integer guestID;
    private LocalDate checkin;
    private LocalDate checkout;
    private Double total;

    public Booking() {
    }

    public Booking(Integer id, Integer guestID, LocalDate checkin, LocalDate checkout) {
        this.id = id;
        this.guestID = guestID;
        this.checkin = checkin;
        this.checkout = checkout;
    }

    public Booking(Integer guestID, LocalDate checkin, LocalDate checkout) {
        this.guestID = guestID;
        this.checkin = checkin;
        this.checkout = checkout;
    }

    public Booking(Integer id, Double total) {
        this.id = id;
        this.total = total;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGuestID() {
        return guestID;
    }

    public void setGuestID(Integer guestID) {
        this.guestID = guestID;
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

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", guestID=" + guestID +
                ", checkin=" + checkin.format(formatter) +
                ", checkout=" + checkout.format(formatter) +
                '}';
    }

}
