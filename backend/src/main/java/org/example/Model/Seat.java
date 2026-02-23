package org.example.Model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String seatRow;
    private Integer number;

    @ManyToOne(optional = false)
    @JoinColumn(name = "hall_id", nullable = false)
    private Hall hall;

    @OneToMany(mappedBy = "seat", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ticket> tickets = new ArrayList<>();

    public Seat() {
    }

    public Long getId() {
        return id;
    }

    public String getSeatRow() {
        return seatRow;
    }

    public int getNumber() {
        return number;
    }

    public Hall getHall() {
        return hall;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setSeatRow(String seatRow) {
        this.seatRow = seatRow;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setHall(Hall hall) {
        if (this.hall == hall) return;

        Hall oldHall = this.hall;
        this.hall = hall;

        if (oldHall != null && oldHall.getSeats().contains(this)) {
            oldHall.removeSeat(this);
        }

        if (hall != null && !hall.getSeats().contains(this)) {
            hall.addSeat(this);
        }
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public void addTicket(Ticket ticket) {
        if (!tickets.contains(ticket)) {
            this.tickets.add(ticket);
            if (ticket.getSeat() != this) {
                ticket.setSeat(this);
            }
        }
    }

    public void removeTicket(Ticket ticket) {
        if (tickets.contains(ticket)) {
            tickets.remove(ticket);
            if (ticket.getSeat() == this) {
                ticket.setSeat(null);
            }
        }
    }
}
