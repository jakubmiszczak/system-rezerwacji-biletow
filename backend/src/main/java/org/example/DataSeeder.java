package org.example;

import jakarta.transaction.Transactional;
import org.example.Model.*;
import org.example.Repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Transactional
@Component
public class DataSeeder implements CommandLineRunner {

    private final AccountRepository accountRepository;
    private final ActorRepository actorRepository;
    private final AdministratorRepository administratorRepository;
    private final CardPaymentRepository cardPaymentRepository;
    private final CashPaymentRepository cashPaymentRepository;
    private final CinemaRepository cinemaRepository;
    private final GenreRepository genreRepository;
    private final HallRepository hallRepository;
    private final MovieRepository movieRepository;
    private final PromotionRepository promotionRepository;
    private final ReservationRepository reservationRepository;
    private final ScreeningRepository screeningRepository;
    private final SeatRepository seatRepository;
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;

    public DataSeeder(AccountRepository accountRepository, ActorRepository actorRepository, AdministratorRepository administratorRepository, CardPaymentRepository cardPaymentRepository, CashPaymentRepository cashPaymentRepository, CinemaRepository cinemaRepository, GenreRepository genreRepository, HallRepository hallRepository, MovieRepository movieRepository, PromotionRepository promotionRepository, ReservationRepository reservationRepository, ScreeningRepository screeningRepository, SeatRepository seatRepository, TicketRepository ticketRepository, UserRepository userRepository) {
        this.accountRepository = accountRepository;
        this.actorRepository = actorRepository;
        this.administratorRepository = administratorRepository;
        this.cardPaymentRepository = cardPaymentRepository;
        this.cashPaymentRepository = cashPaymentRepository;
        this.cinemaRepository = cinemaRepository;
        this.genreRepository = genreRepository;
        this.hallRepository = hallRepository;
        this.movieRepository = movieRepository;
        this.promotionRepository = promotionRepository;
        this.reservationRepository = reservationRepository;
        this.screeningRepository = screeningRepository;
        this.seatRepository = seatRepository;
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        if (accountRepository.count() == 0 && actorRepository.count() == 0 && administratorRepository.count() == 0 && cardPaymentRepository.count() == 0 && cashPaymentRepository.count() == 0 && cinemaRepository.count() == 0 && genreRepository.count() == 0 && hallRepository.count() == 0 && movieRepository.count() == 0 && promotionRepository.count() == 0 && reservationRepository.count() == 0 && screeningRepository.count() == 0 && seatRepository.count() == 0 && ticketRepository.count() == 0 && userRepository.count() == 0) {
            Genre action = new Genre();
            action.setName("Action");
            action.setDescription("Action movies");
            genreRepository.save(action);

            Genre drama = new Genre();
            drama.setName("Drama");
            drama.setDescription("Drama movies");
            genreRepository.save(drama);

            Genre comedy = new Genre();
            comedy.setName("Comedy");
            comedy.setDescription("Comedy movies");
            genreRepository.save(comedy);

            Genre horror = new Genre();
            horror.setName("Horror");
            horror.setDescription("Horror movies");
            genreRepository.save(horror);

            Genre fantasy = new Genre();
            fantasy.setName("Fantasy");
            fantasy.setDescription("Fantasy movies");
            genreRepository.save(fantasy);

            Genre scifi = new Genre();
            scifi.setName("Science Fiction");
            scifi.setDescription("Science fiction movies");
            genreRepository.save(scifi);

            Genre thriller = new Genre();
            thriller.setName("Thriller");
            thriller.setDescription("Thriller movies");
            genreRepository.save(thriller);

            Genre adventure = new Genre();
            adventure.setName("Adventure");
            adventure.setDescription("Adventure movies");
            genreRepository.save(adventure);

            Actor actor1 = new Actor();
            actor1.setFirstName("John");
            actor1.setLastName("Doe");
            actorRepository.save(actor1);

            Actor actor2 = new Actor();
            actor2.setFirstName("Jane");
            actor2.setLastName("Smith");
            actorRepository.save(actor2);

            Actor actor3 = new Actor();
            actor3.setFirstName("Robert");
            actor3.setLastName("Johnson");
            actorRepository.save(actor3);

            Actor actor4 = new Actor();
            actor4.setFirstName("Emily");
            actor4.setLastName("Davis");
            actorRepository.save(actor4);

            Actor actor5 = new Actor();
            actor5.setFirstName("Michael");
            actor5.setLastName("Brown");
            actorRepository.save(actor5);

            Actor actor6 = new Actor();
            actor6.setFirstName("Olivia");
            actor6.setLastName("Wilson");
            actorRepository.save(actor6);

            Movie movie1 = new Movie();
            movie1.setTitle("Star Wars: Episode IV – A New Hope");
            movie1.setDirector("George Lucas");
            movie1.setDurationMinutes(121);
            movie1.setReleaseYear(1977);
            movie1.setDescription("A young farm boy joins a rebellion to save the galaxy from an evil empire.");
            movie1.addGenre(scifi);
            movie1.addGenre(adventure);
            movie1.addActor(actor1);
            movie1.addActor(actor2);
            movieRepository.save(movie1);

            Movie movie2 = new Movie();
            movie2.setTitle("The Terminator");
            movie2.setDirector("James Cameron");
            movie2.setDurationMinutes(107);
            movie2.setReleaseYear(1984);
            movie2.setDescription("A cyborg assassin is sent from the future to kill the mother of a resistance leader.");
            movie2.addGenre(scifi);
            movie2.addGenre(action);
            movie2.addActor(actor3);
            movieRepository.save(movie2);

            Movie movie3 = new Movie();
            movie3.setTitle("Alien");
            movie3.setDirector("Ridley Scott");
            movie3.setDurationMinutes(117);
            movie3.setReleaseYear(1979);
            movie3.setDescription("The crew of a space freighter must fight for survival against a deadly alien lifeform.");
            movie3.addGenre(scifi);
            movie3.addGenre(horror);
            movie3.addActor(actor4);
            movieRepository.save(movie3);

            Movie movie4 = new Movie();
            movie4.setTitle("Blade Runner");
            movie4.setDirector("Ridley Scott");
            movie4.setDurationMinutes(117);
            movie4.setReleaseYear(1982);
            movie4.setDescription("A blade runner must hunt down four rogue replicants in a dystopian future.");
            movie4.addGenre(scifi);
            movie4.addGenre(thriller);
            movie4.addActor(actor5);
            movieRepository.save(movie4);

            Movie movie5 = new Movie();
            movie5.setTitle("Back to the Future");
            movie5.setDirector("Robert Zemeckis");
            movie5.setDurationMinutes(116);
            movie5.setReleaseYear(1985);
            movie5.setDescription("A teenager is accidentally sent back in time and must make sure his parents fall in love.");
            movie5.addGenre(scifi);
            movie5.addGenre(comedy);
            movie5.addActor(actor6);
            movieRepository.save(movie5);

            Cinema cinema1 = new Cinema();
            cinema1.setName("Main Cinema");
            cinema1.setAddress("123 Main St");
            cinema1.setPhone("123-456-789");
            cinema1.setEmail("maincinema@gmail.com");
            cinemaRepository.save(cinema1);

            Cinema cinema2 = new Cinema();
            cinema2.setName("Secondary Cinema");
            cinema2.setAddress("456 secondary Ave");
            cinema2.setPhone("234-567-890");
            cinema2.setEmail("secondarycinema@gmail.com");
            cinemaRepository.save(cinema2);

            Cinema cinema3 = new Cinema();
            cinema3.setName("Old Cinema");
            cinema3.setAddress("789 old Blvd");
            cinema3.setPhone("345-678-901");
            cinema3.setEmail("oldcinema@gmail.com");
            cinemaRepository.save(cinema3);


            Hall hall1 = new Hall();
            hall1.setNumber(1);
            hall1.setCinema(cinema1);
            hallRepository.save(hall1);

            Hall hall2 = new Hall();
            hall2.setNumber(2);
            hall2.setCinema(cinema2);
            hallRepository.save(hall2);

            Hall hall3 = new Hall();
            hall3.setNumber(3);
            hall3.setCinema(cinema2);
            hallRepository.save(hall3);

            Hall hall4 = new Hall();
            hall4.setNumber(4);
            hall4.setCinema(cinema3);
            hallRepository.save(hall4);



            List<Hall> allHalls = List.of(hall1, hall2, hall3, hall4);

            for (Hall hall : allHalls) {
                for (int rowIndex = 0; rowIndex < 3; rowIndex++) {
                    char rowChar = (char) ('A' + rowIndex);
                    String rowLabel = String.valueOf(rowChar);

                    for (int num = 1; num <= 5; num++) {
                        Seat seat = new Seat();
                        seat.setSeatRow(rowLabel);
                        seat.setNumber(num);
                        seat.setHall(hall);
                        seatRepository.save(seat);
                    }
                }
            }

            Screening screening1 = new Screening();
            screening1.setStartDateTime(LocalDateTime.now().plusDays(1).withHour(16).withMinute(0));
            screening1.setHall(hall1);
            screening1.setMovie(movie1);
            screeningRepository.save(screening1);

            Screening screening2 = new Screening();
            screening2.setStartDateTime(LocalDateTime.now().plusDays(1).withHour(19).withMinute(0));
            screening2.setHall(hall1);
            screening2.setMovie(movie2);
            screeningRepository.save(screening2);

            Screening screening3 = new Screening();
            screening3.setStartDateTime(LocalDateTime.now().plusDays(2).withHour(15).withMinute(30));
            screening3.setHall(hall2);
            screening3.setMovie(movie3);
            screeningRepository.save(screening3);

            Screening screening4 = new Screening();
            screening4.setStartDateTime(LocalDateTime.now().plusDays(2).withHour(18).withMinute(15));
            screening4.setHall(hall2);
            screening4.setMovie(movie4);
            screeningRepository.save(screening4);

            Screening screening5 = new Screening();
            screening5.setStartDateTime(LocalDateTime.now().plusDays(3).withHour(17).withMinute(45));
            screening5.setHall(hall3);
            screening5.setMovie(movie5);
            screeningRepository.save(screening5);

            Screening screening6 = new Screening();
            screening6.setStartDateTime(LocalDateTime.now().plusDays(3).withHour(20).withMinute(0));
            screening6.setHall(hall4);
            screening6.setMovie(movie1);
            screeningRepository.save(screening6);

            User user1 = new User();
            user1.setEmail("alice@gmail.com");
            user1.setLogin("Alice211");
            user1.setPassword("securepassword");
            user1.setFirstName("Alice");
            user1.setLastName("Wonderland");
            user1.setPhoneNumber("111-222-333");
            userRepository.save(user1);

            User user2 = new User();
            user2.setEmail("bob.smith@example.com");
            user2.setLogin("BobTheBuilder");
            user2.setPassword("strongpass123");
            user2.setFirstName("Bob");
            user2.setLastName("Smith");
            user2.setPhoneNumber("222-333-444");
            userRepository.save(user2);

            User user3 = new User();
            user3.setEmail("carol.jones@example.com");
            user3.setLogin("CarolJ");
            user3.setPassword("pass456word");
            user3.setFirstName("Carol");
            user3.setLastName("Jones");
            user3.setPhoneNumber("333-444-555");
            userRepository.save(user3);

            User user4 = new User();
            user4.setEmail("david.miller@example.com");
            user4.setLogin("DaveM");
            user4.setPassword("d4v1dp@ss");
            user4.setFirstName("David");
            user4.setLastName("Miller");
            user4.setPhoneNumber("444-555-666");
            userRepository.save(user4);

            User user5 = new User();
            user5.setEmail("emma.brown@example.com");
            user5.setLogin("EmzB");
            user5.setPassword("emm@secure");
            user5.setFirstName("Emma");
            user5.setLastName("Brown");
            user5.setPhoneNumber("555-666-777");
            userRepository.save(user5);

            Promotion promo1 = new Promotion();
            promo1.setName("Spring Sale");
            promo1.setPromoCode("SPRING2024");
            promo1.setDiscountPercent(20);
            promo1.setStartDate(LocalDate.now());
            promo1.setEndDate(LocalDate.now().plusDays(30));
            promotionRepository.save(promo1);

            Promotion promo2 = new Promotion();
            promo2.setName("Summer Blast");
            promo2.setPromoCode("SUMMER2024");
            promo2.setDiscountPercent(25);
            promo2.setStartDate(LocalDate.now().plusDays(10));
            promo2.setEndDate(LocalDate.now().plusDays(40));
            promotionRepository.save(promo2);

            Promotion promo3 = new Promotion();
            promo3.setName("Student Discount");
            promo3.setPromoCode("STUDENT10");
            promo3.setDiscountPercent(10);
            promo3.setStartDate(LocalDate.now());
            promo3.setEndDate(LocalDate.now().plusMonths(3));
            promotionRepository.save(promo3);

            Promotion promo4 = new Promotion();
            promo4.setName("Weekend Madness");
            promo4.setPromoCode("WEEKEND50");
            promo4.setDiscountPercent(50);
            promo4.setStartDate(LocalDate.now());
            promo4.setEndDate(LocalDate.now().plusMonths(1));
            promotionRepository.save(promo4);

            Reservation reservation1 = new Reservation();
            reservation1.setReservationDateTime(java.time.LocalDateTime.now());
            reservation1.setStatus(ReservationStatus.Created);
            reservation1.setUser(user1);
            reservation1.setScreening(screening1);
            reservation1.addPromotion(promo1);
            reservationRepository.save(reservation1);

            Reservation reservation2 = new Reservation();
            reservation2.setReservationDateTime(LocalDateTime.now().minusDays(1));
            reservation2.setStatus(ReservationStatus.Created);
            reservation2.setUser(user2);
            reservation2.setScreening(screening2);
            reservation2.addPromotion(promo2);
            reservationRepository.save(reservation2);

            Reservation reservation3 = new Reservation();
            reservation3.setReservationDateTime(LocalDateTime.now().minusHours(5));
            reservation3.setStatus(ReservationStatus.Created);
            reservation3.setUser(user3);
            reservation3.setScreening(screening3);
            reservation3.addPromotion(promo3);
            reservation3.addPromotion(promo4);
            reservationRepository.save(reservation3);

            Reservation reservation4 = new Reservation();
            reservation4.setReservationDateTime(LocalDateTime.now().minusDays(2));
            reservation4.setStatus(ReservationStatus.Created);
            reservation4.setUser(user4);
            reservation4.setScreening(screening4);
            reservationRepository.save(reservation4);

            Reservation reservation5 = new Reservation();
            reservation5.setReservationDateTime(LocalDateTime.now());
            reservation5.setStatus(ReservationStatus.Created);
            reservation5.setUser(user1);
            reservation5.setScreening(screening5);
            reservation5.addPromotion(promo3);
            reservationRepository.save(reservation5);

            Reservation reservation6 = new Reservation();
            reservation6.setReservationDateTime(LocalDateTime.now().minusDays(3));
            reservation6.setStatus(ReservationStatus.Created);
            reservation6.setUser(user2);
            reservation6.setScreening(screening6);
            reservation6.addPromotion(promo4);
            reservationRepository.save(reservation6);

            Map<Reservation, Integer> ticketCounts = Map.of(
                    reservation1, 2,
                    reservation2, 3,
                    reservation3, 1,
                    reservation4, 4,
                    reservation5, 4,
                    reservation6, 2
            );

            for (Map.Entry<Reservation, Integer> entry : ticketCounts.entrySet()) {
                Reservation reservation = entry.getKey();
                int count = entry.getValue();

                Screening screening = reservation.getScreening();
                Hall hall = screening.getHall();

                Set<Seat> usedSeats = ticketRepository.findByScreening(screening).stream()
                        .map(Ticket::getSeat)
                        .collect(Collectors.toSet());

                List<Seat> allSeats = seatRepository.findByHall(hall);

                int created = 0;
                for (Seat seat : allSeats) {
                    if (usedSeats.contains(seat)) continue;

                    BigDecimal price = BigDecimal.valueOf(20 + Math.random() * 10).setScale(2, RoundingMode.HALF_UP);

                    Ticket ticket = new Ticket();
                    ticket.setPrice(price);
                    ticket.setReservation(reservation);
                    ticket.setScreening(screening);
                    ticket.setSeat(seat);
                    ticketRepository.save(ticket);

                    created++;
                    if (created >= count) break;
                }
            }


            CardPayment cardPayment1 = new CardPayment();
            cardPayment1.setCardNumber("1234567890123456");
            cardPayment1.setExpiryDate(LocalDate.now().plusYears(2));
            cardPayment1.setCvv(123);
            cardPayment1.setCardSignature("Alice W.");
            cardPayment1.setReservation(reservation1);
            cardPaymentRepository.save(cardPayment1);

            CardPayment cardPayment2 = new CardPayment();
            cardPayment2.setCardNumber("9876543210987654");
            cardPayment2.setExpiryDate(LocalDate.now().plusYears(3));
            cardPayment2.setCvv(456);
            cardPayment2.setCardSignature("Bob S.");
            cardPayment2.setReservation(reservation2);
            cardPaymentRepository.save(cardPayment2);

            CashPayment cashPayment1 = new CashPayment();
            cashPayment1.setCashier("Carol");
            cashPayment1.setRefunded(false);
            cashPayment1.setRefundAmount(BigDecimal.ZERO);
            cashPayment1.setReservation(reservation3);
            cashPaymentRepository.save(cashPayment1);

            CashPayment cashPayment2 = new CashPayment();
            cashPayment2.setCashier("David");
            cashPayment2.setRefunded(true);
            cashPayment2.setRefundAmount(new BigDecimal("5.00"));
            cashPayment2.setReservation(reservation4);
            cashPaymentRepository.save(cashPayment2);

            Administrator admin1 = new Administrator();
            admin1.setEmail("admin@gmail.com");
            admin1.setLogin("Admin123");
            admin1.setPassword("adminpassword");
            admin1.setHireDate(java.time.LocalDate.now().minusYears(1));
            admin1.setMonthlySalary(new java.math.BigDecimal("4000.00"));
            administratorRepository.save(admin1);

            Administrator admin2 = new Administrator();
            admin2.setEmail("lucy.manager@gmail.com");
            admin2.setLogin("LucyM");
            admin2.setPassword("pass456!");
            admin2.setHireDate(LocalDate.now().minusMonths(18));
            admin2.setMonthlySalary(new BigDecimal("4200.00"));
            administratorRepository.save(admin2);

            Administrator admin3 = new Administrator();
            admin3.setEmail("mike.supervisor@gmail.com");
            admin3.setLogin("MikeS");
            admin3.setPassword("mikeAdmin@2024");
            admin3.setHireDate(LocalDate.now().minusYears(2));
            admin3.setMonthlySalary(new BigDecimal("4500.00"));
            administratorRepository.save(admin3);

            Administrator admin4 = new Administrator();
            admin4.setEmail("sara.admin@gmail.com");
            admin4.setLogin("SaraA");
            admin4.setPassword("sara!admin");
            admin4.setHireDate(LocalDate.now().minusMonths(6));
            admin4.setMonthlySalary(new BigDecimal("3900.00"));
            administratorRepository.save(admin4);

            System.out.println("Seeding completed successfully!");
        }
    }
}
