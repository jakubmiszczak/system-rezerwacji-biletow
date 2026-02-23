package org.example.Model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Screening {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime startDateTime;

    @ManyToOne(optional = false)
    @JoinColumn(name = "hall_id", nullable = false)
    private Hall hall;

    @ManyToOne(optional = false)
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;

    @OneToMany(mappedBy = "screening", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ticket> tickets = new ArrayList<>();

    @OneToMany(mappedBy = "screening", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reservation> reservations = new ArrayList<>();

    public Screening() {
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public Hall getHall() {
        return hall;
    }

    public Movie getMovie() {
        return movie;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public void setHall(Hall hall) {
        if (this.hall == hall) return;

        Hall oldHall = this.hall;
        this.hall = hall;

        if (oldHall != null && oldHall.getScreenings().contains(this)) {
            oldHall.removeScreening(this);
        }

        if (hall != null && !hall.getScreenings().contains(this)) {
            hall.addScreening(this);
        }
    }

    public void setMovie(Movie movie) {
        if (this.movie == movie) return;

        Movie oldMovie = this.movie;
        this.movie = movie;

        if (oldMovie != null && oldMovie.getScreenings().contains(this)) {
            oldMovie.removeScreening(this);
        }

        if (movie != null && !movie.getScreenings().contains(this)) {
            movie.addScreening(this);
        }
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public void addTicket(Ticket ticket) {
        if (!tickets.contains(ticket)) {
            tickets.add(ticket);
            if (ticket.getScreening() != this) {
                ticket.setScreening(this);
            }
        }
    }

    public void removeTicket(Ticket ticket) {
        if (tickets.contains(ticket)) {
            tickets.remove(ticket);
            if (ticket.getScreening() == this) {
                ticket.setScreening(null);
            }
        }
    }

    public void addReservation(Reservation reservation) {
        if (!reservations.contains(reservation)) {
            reservations.add(reservation);
            if (reservation.getScreening() != this) {
                reservation.setScreening(this);
            }
        }
    }

    public void removeReservation(Reservation reservation) {
        if (reservations.contains(reservation)) {
            reservations.remove(reservation);
            if (reservation.getScreening() == this) {
                reservation.setScreening(null);
            }
        }
    }

    @Transient
    public LocalDateTime getEndDateTime() {
        return startDateTime.plusMinutes(movie.getDurationMinutes());
    }

    @Transient
    public List<Seat> getAllSeats() {
        return hall.getSeats();
    }

    @Transient
    public List<Seat> getAvailableSeats() {
        List<Seat> availableSeats = new ArrayList<>(hall.getSeats());
        for (Ticket ticket : tickets) {
            availableSeats.remove(ticket.getSeat());
        }
        return availableSeats;
    }
}
