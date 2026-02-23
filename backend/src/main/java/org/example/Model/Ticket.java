package org.example.Model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal price;

    @ManyToOne(optional = false)
    @JoinColumn(name = "reservation_id", nullable = false)
    private Reservation reservation;

    @ManyToOne(optional = false)
    @JoinColumn(name = "screening_id", nullable = false)
    private Screening screening;

    @ManyToOne(optional = false)
    @JoinColumn(name = "seat_id", nullable = false)
    private Seat seat;

    public Ticket() {
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public Screening getScreening() {
        return screening;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setReservation(Reservation reservation) {
        if (this.reservation == reservation) return;

        Reservation oldReservation = this.reservation;
        this.reservation = reservation;

        if (oldReservation != null && oldReservation.getTickets().contains(this)) {
            oldReservation.removeTicket(this);
        }

        if (reservation != null && !reservation.getTickets().contains(this)) {
            reservation.addTicket(this);
        }
    }

    public void setScreening(Screening screening) {
        if (this.screening == screening) return;

        Screening oldScreening = this.screening;
        this.screening = screening;

        if (oldScreening != null && oldScreening.getTickets().contains(this)) {
            oldScreening.removeTicket(this);
        }

        if (screening != null && !screening.getTickets().contains(this)) {
            screening.addTicket(this);
        }
    }

    public void setSeat(Seat seat) {
        if (this.seat == seat) return;

        Seat oldSeat = this.seat;
        this.seat = seat;

        if (oldSeat != null && oldSeat.getTickets().contains(this)) {
            oldSeat.removeTicket(this);
        }

        if (seat != null && !seat.getTickets().contains(this)) {
            seat.addTicket(this);
        }
    }
}