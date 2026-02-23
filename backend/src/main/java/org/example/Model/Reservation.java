package org.example.Model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime reservationDateTime;

    @Enumerated(EnumType.STRING)
    private ReservationStatus status;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "screening_id", nullable = false)
    private Screening screening;

    @ManyToMany
    private List<Promotion> promotions = new ArrayList<>();

    @OneToOne(mappedBy = "reservation", cascade = CascadeType.ALL, orphanRemoval = true)
    private CardPayment cardPayment;

    @OneToOne(mappedBy = "reservation", cascade = CascadeType.ALL, orphanRemoval = true)
    private CashPayment cashPayment;

    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ticket> tickets = new ArrayList<>();

    public Reservation() {
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getReservationDateTime() {
        return reservationDateTime;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public User getUser() {
        return user;
    }

    public List<Promotion> getPromotions() {
        return promotions;
    }

    public CardPayment getCardPayment() {
        return cardPayment;
    }

    public CashPayment getCashPayment() {
        return cashPayment;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public Screening getScreening() {
        return screening;
    }

    public void setReservationDateTime(LocalDateTime reservationDateTime) {
        this.reservationDateTime = reservationDateTime;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

    public void setUser(User user) {
        if (this.user == user) return;

        User oldUser = this.user;
        this.user = user;

        if (oldUser != null && oldUser.getReservations().contains(this)) {
            oldUser.removeReservation(this);
        }

        if (user != null && !user.getReservations().contains(this)) {
            user.addReservation(this);
        }
    }

    public void setPromotions(List<Promotion> promotions) {
        this.promotions = promotions;
    }

    public void setCardPayment(CardPayment cardPayment) {
        if (this.cashPayment != null) {
            throw new IllegalStateException("Reservation already has a cash payment, cannot set card payment.");
        }

        if (this.cardPayment == cardPayment) return;

        CardPayment old = this.cardPayment;
        this.cardPayment = cardPayment;

        if (old != null && old.getReservation() == this) {
            old.setReservation(null);
        }

        if (cardPayment != null && cardPayment.getReservation() != this) {
            cardPayment.setReservation(this);
        }

        if (cardPayment != null) {
            this.status = ReservationStatus.Paid;
        } else {
            this.status = ReservationStatus.Created;
        }
    }

    public void setCashPayment(CashPayment cashPayment) {
        if (this.cardPayment != null) {
            throw new IllegalStateException("Reservation already has a card payment, cannot set cash payment.");
        }

        if (this.cashPayment == cashPayment) return;

        CashPayment old = this.cashPayment;
        this.cashPayment = cashPayment;

        if (old != null && old.getReservation() == this) {
            old.setReservation(null);
        }

        if (cashPayment != null && cashPayment.getReservation() != this) {
            cashPayment.setReservation(this);
        }

        if (cashPayment != null) {
            this.status = ReservationStatus.Paid;
        } else {
            this.status = ReservationStatus.Created;
        }
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public void setScreening(Screening screening) {
        if (this.screening == screening) return;

        Screening oldScreening = this.screening;
        this.screening = screening;

        if (oldScreening != null && oldScreening.getReservations().contains(this)) {
            oldScreening.removeReservation(this);
        }

        if (screening != null && !screening.getReservations().contains(this)) {
            screening.addReservation(this);
        }
    }

    public void addPromotion(Promotion promotion) {
        if (!promotions.contains(promotion)) {
            promotions.add(promotion);
        }
    }

    public void removePromotion(Promotion promotion) {
        promotions.remove(promotion);
    }

    public void addTicket(Ticket ticket) {
        if (!tickets.contains(ticket)) {
            tickets.add(ticket);
            if (ticket.getReservation() != this) {
                ticket.setReservation(this);
            }
        }
    }

    public void removeTicket(Ticket ticket) {
        if (tickets.contains(ticket)) {
            tickets.remove(ticket);
            if (ticket.getReservation() == this) {
                ticket.setReservation(null);
            }
        }
    }

    public void addTickets(List<Ticket> tickets) {
        for (Ticket ticket : tickets) {
            addTicket(ticket);
        }
    }

    public void removeAllConnections() {
        user.removeReservation(this);
        user = null;

        screening.removeReservation(this);
        screening = null;

        for (Ticket ticket : new ArrayList<>(tickets)) {
            ticket.getSeat().removeTicket(ticket);
            ticket.setSeat(null);

            ticket.getScreening().removeTicket(ticket);
            ticket.setScreening(null);

            removeTicket(ticket);
        }

        for (Promotion promotion : new ArrayList<>(promotions)) {
            removePromotion(promotion);
        }

        if (cardPayment != null) {
            cardPayment.setReservation(null);
            cardPayment = null;
        }

        if (cashPayment != null) {
            cashPayment.setReservation(null);
            cashPayment = null;
        }
    }


    @Transient
    public boolean isPaidFor() {
        return cardPayment != null || cashPayment != null;
    }

    @Transient
    public BigDecimal getTotalCost() {
        BigDecimal total = BigDecimal.ZERO;
        for (Ticket ticket : tickets) {
            total = total.add(ticket.getPrice());
        }

        int cumulativeDiscount = 0;
        for (Promotion promotion : promotions) {
            cumulativeDiscount += promotion.getDiscountPercent();
        }
        cumulativeDiscount = Math.min(50, cumulativeDiscount);

        if (cumulativeDiscount > 0) {
            BigDecimal discountRate = BigDecimal.valueOf(cumulativeDiscount)
                    .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
            BigDecimal discountAmount = total.multiply(discountRate);
            total = total.subtract(discountAmount);
        }

        return total;
    }

}
