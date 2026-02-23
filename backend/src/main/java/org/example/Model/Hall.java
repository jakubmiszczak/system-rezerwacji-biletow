package org.example.Model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Hall {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer number;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cinema_id", nullable = false)
    private Cinema cinema;

    @OneToMany(mappedBy = "hall", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Seat> seats = new ArrayList<>();

    @OneToMany(mappedBy = "hall", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Screening> screenings = new ArrayList<>();

    public Hall() {
    }

    public Long getId() {
        return id;
    }

    public int getNumber() {
        return number;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public List<Screening> getScreenings() {
        return screenings;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setCinema(Cinema cinema) {
        if (this.cinema == cinema) return;

        Cinema oldCinema = this.cinema;
        this.cinema = cinema;

        if (oldCinema != null && oldCinema.getHalls().contains(this)) {
            oldCinema.removeHall(this);
        }

        if (cinema != null && !cinema.getHalls().contains(this)) {
            cinema.addHall(this);
        }
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    public void setScreenings(List<Screening> screenings) {
        this.screenings = screenings;
    }

    public void addSeat(Seat seat) {
        if (!seats.contains(seat)) {
            seats.add(seat);
            if (seat.getHall() != this) {
                seat.setHall(this);
            }
        }
    }

    public void removeSeat(Seat seat) {
        if (seats.contains(seat)) {
            seats.remove(seat);
            if (seat.getHall() == this) {
                seat.setHall(null);
            }
        }
    }

    public void addScreening(Screening screening) {
        if (!screenings.contains(screening)) {
            screenings.add(screening);
            if (screening.getHall() != this) {
                screening.setHall(this);
            }
        }
    }

    public void removeScreening(Screening screening) {
        if (screenings.contains(screening)) {
            screenings.remove(screening);
            if (screening.getHall() == this) {
                screening.setHall(null);
            }
        }
    }

    @Transient
    public int getCapacity() {
        return seats.size();
    }

}