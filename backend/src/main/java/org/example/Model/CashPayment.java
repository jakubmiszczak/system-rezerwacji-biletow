package org.example.Model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class CashPayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean isRefunded;
    private BigDecimal refundAmount;
    private String cashier;

    @OneToOne(optional = false)
    @JoinColumn(name = "reservation_id", nullable = false, unique = true)
    private Reservation reservation;

    public CashPayment() {
    }

    public Long getId() {
        return id;
    }

    public Boolean getRefunded() {
        return isRefunded;
    }

    public BigDecimal getRefundAmount() {
        return refundAmount;
    }

    public String getCashier() {
        return cashier;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setRefunded(Boolean refunded) {
        isRefunded = refunded;
    }

    public void setRefundAmount(BigDecimal refundAmount) {
        this.refundAmount = refundAmount;
    }

    public void setCashier(String cashier) {
        this.cashier = cashier;
    }

    public void setReservation(Reservation reservation) {
        if (this.reservation == reservation) return;

        Reservation old = this.reservation;
        this.reservation = reservation;

        if (old != null && old.getCashPayment() == this) {
            old.setCashPayment(null);
        }

        if (reservation != null && reservation.getCashPayment() != this) {
            reservation.setCashPayment(this);
        }
    }
}
