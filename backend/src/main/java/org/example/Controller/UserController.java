package org.example.Controller;

import org.example.DTOs.ReservationDataDTO;
import org.example.DTOs.UserBasicDTO;
import org.example.Repository.UserRepository;
import org.example.Service.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository userRepository;
    private final ReservationService reservationService;

    public UserController(UserRepository userRepository, ReservationService reservationService) {
        this.userRepository = userRepository;
        this.reservationService = reservationService;
    }

    @GetMapping("/basic")
    public List<UserBasicDTO> getBasicUsers() {
        return userRepository.findAllUserBasics();
    }

    @GetMapping("/{userId}/reservations")
    public ResponseEntity<List<ReservationDataDTO>> getReservationsForUser(@PathVariable Long userId) {
        return ResponseEntity.ok(reservationService.getReservationsForUser(userId));
    }

    @DeleteMapping("/reservations/{reservationId}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long reservationId) {
        if (!reservationService.deleteReservationById(reservationId)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

}
