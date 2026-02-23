package org.example.DTOs;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReservationDataDTO {
    private Long id;
    private LocalDateTime reservationDateTime;
    private String movieTitle;
    private String cinemaName;
    private String hallNumber;
    private String totalPrice;
    private List<TicketDataDTO> ticketData = new ArrayList<>();

    public ReservationDataDTO(Long id, LocalDateTime reservationDateTime, String movieTitle, String cinemaName, String hallNumber, String totalPrice) {
        this.id = id;
        this.reservationDateTime = reservationDateTime;
        this.movieTitle = movieTitle;
        this.cinemaName = cinemaName;
        this.hallNumber = hallNumber;
        this.totalPrice = totalPrice;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getReservationDateTime() {
        return reservationDateTime;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public String getCinemaName() {
        return cinemaName;
    }

    public String getHallNumber() {
        return hallNumber;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public List<TicketDataDTO> getTicketData() {
        return ticketData;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setReservationDateTime(LocalDateTime reservationDateTime) {
        this.reservationDateTime = reservationDateTime;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public void setCinemaName(String cinemaName) {
        this.cinemaName = cinemaName;
    }

    public void setHallNumber(String hallNumber) {
        this.hallNumber = hallNumber;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setTicketData(List<TicketDataDTO> ticketData) {
        this.ticketData = ticketData;
    }
}

