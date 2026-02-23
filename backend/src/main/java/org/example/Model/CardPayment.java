package org.example.Model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class CardPayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cardNumber;
    private LocalDate expiryDate;
    private Integer cvv;
    private String cardSignature;

    @OneToOne(optional = false)
    @JoinColumn(name = "reservation_id", nullable = false, unique = true)
    private Reservation reservation;

    public CardPayment() {
    }

    public Long getId() {
        return id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public Integer getCvv() {
        return cvv;
    }

    public String getCardSignature() {
        return cardSignature;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public void setCvv(Integer cvv) {
        this.cvv = cvv;
    }

    public void setCardSignature(String cardSignature) {
        this.cardSignature = cardSignature;
    }

    public void setReservation(Reservation reservation) {
        if (this.reservation == reservation) return;

        Reservation old = this.reservation;
        this.reservation = reservation;

        if (old != null && old.getCardPayment() == this) {
            old.setCardPayment(null);
        }

        if (reservation != null && reservation.getCardPayment() != this) {
            reservation.setCardPayment(this);
        }
    }
}