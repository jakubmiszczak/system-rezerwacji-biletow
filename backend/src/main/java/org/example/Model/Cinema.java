package org.example.Model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Cinema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;
    private String phone;
    private String email;

    @OneToMany(mappedBy = "cinema", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Hall> halls = new ArrayList<>();

    public Cinema() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public List<Hall> getHalls() {
        return halls;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setHalls(List<Hall> halls) {
        this.halls = halls;
    }

    public void addHall(Hall hall) {
        if (!halls.contains(hall)) {
            halls.add(hall);
            if (hall.getCinema() != this) {
                hall.setCinema(this);
            }
        }
    }

    public void removeHall(Hall hall) {
        if (halls.contains(hall)) {
            halls.remove(hall);
            if (hall.getCinema() == this) {
                hall.setCinema(null);
            }
        }
    }
}