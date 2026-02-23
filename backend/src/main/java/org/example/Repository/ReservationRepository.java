package org.example.Repository;

import org.example.Model.Reservation;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @EntityGraph(attributePaths = {
            "screening.movie",
            "screening.hall.cinema",
            "tickets.seat"
    })
    List<Reservation> findByUserId(Long userId);
}
