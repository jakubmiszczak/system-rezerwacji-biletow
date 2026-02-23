package org.example.Service;

import jakarta.transaction.Transactional;
import org.example.DTOs.ReservationDataDTO;
import org.example.DTOs.TicketDataDTO;
import org.example.Model.Reservation;
import org.example.Repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public List<ReservationDataDTO> getReservationsForUser(Long userId) {
        List<Reservation> reservations = reservationRepository.findByUserId(userId);

        return reservations.stream().map(res -> {
            ReservationDataDTO dto = new ReservationDataDTO(
                    res.getId(),
                    res.getReservationDateTime(),
                    res.getScreening().getMovie().getTitle(),
                    res.getScreening().getHall().getCinema().getName(),
                    String.valueOf(res.getScreening().getHall().getNumber()),
                    res.getTotalCost().toPlainString()
            );

            List<TicketDataDTO> ticketDTOs = res.getTickets().stream()
                    .map(ticket -> new TicketDataDTO(
                            ticket.getSeat().getSeatRow(),
                            ticket.getSeat().getNumber()
                    ))
                    .collect(Collectors.toList());

            dto.setTicketData(ticketDTOs);
            return dto;
        }).collect(Collectors.toList());
    }

    @Transactional
    public boolean deleteReservationById(Long id) {
        Optional<Reservation> opt = reservationRepository.findById(id);
        if (opt.isEmpty()) return false;

        Reservation reservation = opt.get();
        reservation.removeAllConnections();
        reservationRepository.delete(reservation);
        return true;
    }


}
