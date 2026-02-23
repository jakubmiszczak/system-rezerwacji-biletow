package org.example.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String promoCode;
    private Integer discountPercent;
    private LocalDate startDate;
    private LocalDate endDate;

    public Promotion() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPromoCode() {
        return promoCode;
    }

    public Integer getDiscountPercent() {
        return discountPercent;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPromoCode(String promoCode) {
        this.promoCode = promoCode;
    }

    public void setDiscountPercent(Integer discountPercent) {
        this.discountPercent = discountPercent;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
