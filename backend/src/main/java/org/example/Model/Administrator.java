package org.example.Model;

import jakarta.persistence.Entity;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Administrator extends Account {
    private LocalDate hireDate;
    private BigDecimal monthlySalary;

    public Administrator() {
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public BigDecimal getMonthlySalary() {
        return monthlySalary;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public void setMonthlySalary(BigDecimal monthlySalary) {
        this.monthlySalary = monthlySalary;
    }
}
