package cinema.project;

import cinema.project.exception.RegistrationException;
import cinema.project.lib.Injector;
import cinema.project.model.CinemaHall;
import cinema.project.model.Movie;
import cinema.project.model.MovieSession;
import cinema.project.model.ShoppingCart;
import cinema.project.model.User;
import cinema.project.security.AuthenticationService;
import cinema.project.service.CinemaHallService;
import cinema.project.service.MovieService;
import cinema.project.service.MovieSessionService;
import cinema.project.service.OrderService;
import cinema.project.service.ShoppingCartService;
import cinema.project.service.UserService;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {
    private static final Injector injector = Injector.getInstance("cinema.project");
    private static final MovieService movieService
            = (MovieService) injector.getInstance(MovieService.class);
    private static final MovieSessionService movieSessionService
            = (MovieSessionService) injector.getInstance(MovieSessionService.class);
    private static final CinemaHallService cinemaHallService
            = (CinemaHallService) injector.getInstance(CinemaHallService.class);
    private static final ShoppingCartService shoppingCartService
            = (ShoppingCartService) injector.getInstance(ShoppingCartService.class);
    private static final OrderService orderService
            = (OrderService) injector.getInstance(OrderService.class);
    private static final AuthenticationService authenticationService
            = (AuthenticationService) injector.getInstance(AuthenticationService.class);
    private static final UserService userService =
            (UserService) injector.getInstance(UserService.class);

    public static void main(String[] args) {
        Movie fastAndFurious = new Movie("Fast and Furious");
        fastAndFurious.setDescription("An action film about street racing, heists, and spies.");
        movieService.add(fastAndFurious);
        System.out.println(movieService.get(fastAndFurious.getId()));
        movieService.getAll().forEach(System.out::println);

        CinemaHall firstCinemaHall = new CinemaHall();
        firstCinemaHall.setCapacity(100);
        firstCinemaHall.setDescription("first hall with capacity 100");

        CinemaHall secondCinemaHall = new CinemaHall();
        secondCinemaHall.setCapacity(200);
        secondCinemaHall.setDescription("second hall with capacity 200");

        cinemaHallService.add(firstCinemaHall);
        cinemaHallService.add(secondCinemaHall);

        System.out.println(cinemaHallService.getAll());
        System.out.println(cinemaHallService.get(firstCinemaHall.getId()));

        MovieSession tomorrowMovieSession = new MovieSession();
        tomorrowMovieSession.setCinemaHall(firstCinemaHall);
        tomorrowMovieSession.setMovie(fastAndFurious);
        tomorrowMovieSession.setShowTime(LocalDateTime.now().plusDays(1L));

        MovieSession yesterdayMovieSession = new MovieSession();
        yesterdayMovieSession.setCinemaHall(firstCinemaHall);
        yesterdayMovieSession.setMovie(fastAndFurious);
        yesterdayMovieSession.setShowTime(LocalDateTime.now().minusDays(1L));

        movieSessionService.add(tomorrowMovieSession);
        movieSessionService.add(yesterdayMovieSession);

        System.out.println(movieSessionService.get(yesterdayMovieSession.getId()));
        System.out.println(movieSessionService.findAvailableSessions(
                fastAndFurious.getId(), LocalDate.now()));

        User user = new User();
        user.setEmail("klimyuk054@gmail.com");
        user.setPassword("123412334");
        try {
            authenticationService.register(user.getEmail(), user.getPassword());
        } catch (RegistrationException e) {
            throw new RuntimeException("Can't register new user by login: " + user.getEmail(), e);
        }

        User userFromDb = userService.findByEmail(user.getEmail()).get();

        shoppingCartService.addSession(yesterdayMovieSession, userFromDb);
        shoppingCartService.addSession(tomorrowMovieSession, userFromDb);
        ShoppingCart shoppingCartServiceByUser = shoppingCartService.getByUser(userFromDb);

        System.out.println(orderService.completeOrder(shoppingCartServiceByUser));
        orderService.getOrdersHistory(userFromDb).forEach(System.out::println);
    }
}
