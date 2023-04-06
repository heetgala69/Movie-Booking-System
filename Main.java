import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

interface Promotion {
    int calculateDiscount(List<Seat> seats);
}

class EarlyBirdDiscount implements Promotion {
    public int calculateDiscount(List<Seat> seats) {
        return 10; // Return a 10% discount
    }
}

class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean checkLogin(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    @Override
    public String toString() {
        return "User{" +
                "\nusername='" + username + '\'' +
                "\npassword='" + password + '\'' +
                '}';
    }
}



class Customer extends User {
    private String name;
    private String email;

    private static final String EMAIL_PATTERN = "^\\S+@\\S+\\.\\S+$";

    public Customer(String username, String password, String name, String email) throws IllegalArgumentException {
        super(username, password);

        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }

        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Email is not in a valid format");
        }

        this.name = name;
        this.email = email;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "\nname='" + name + '\'' +
                ",\nemail='" + email + '\'' +
                '}';
    }
}


class Movie {
    private String title;
    private int duration;

    public Movie(String title, int duration) {
        this.title = title;
        this.duration = duration;
    }

    public String getTitle() {
        return this.title;
    }

    public int getDuration() {
        return this.duration;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "\ntitle='" + title + '\'' +
                ",\nduration=" + duration +
                '}';
    }
}

class Screen {
    private int id;
    private int capacity;
    private Movie movie;

    public Screen(int id, int capacity, Movie movie) {
        this.id = id;
        this.capacity = capacity;
        this.movie = movie;
    }

    public int getId() {
        return this.id;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public Movie getMovie() {
        return this.movie;
    }

    @Override
    public String toString() {
        return "Screen{" +
                "\nid=" + id +
                ",\ncapacity=" + capacity +
                ",\nmovie=" + movie +
                '}';
    }
}

class Seat {
    private int id;
    private boolean isOccupied;
    private SeatType seatType;

    public Seat(int id, boolean isOccupied, SeatType seatType) {
        this.id = id;
        this.isOccupied = isOccupied;
        this.seatType = seatType;
    }

    public SeatType getSeatType() {
        return seatType;
    }

    public void setSeatType(SeatType seatType) {
        this.seatType = seatType;
    }

    public Seat(int id) {
        this.id = id;
        this.isOccupied = false;
    }

    public int getId() {
        return this.id;
    }

    public boolean isOccupied() {
        return this.isOccupied;
    }

    public void occupy() {
        this.isOccupied = true;
    }

    public void release() {
        this.isOccupied = false;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "\nid=" + id +
                ",\nisOccupied=" + isOccupied +
                ",\nseatType=" + seatType +
                '}';
    }
}
class SeatType {
    private String name;
    private int price;

    public SeatType(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return this.name;
    }

    public int getPrice() {
        return this.price;
    }

    @Override
    public String toString() {
        return "SeatType{" +
                "\nname='" + name + '\'' +
                ",\nprice=" + price +
                '}';
    }
}
class Ticket {
    private Screen screen;
    private Customer customer;
    private List<Seat> seats;
    private Payment payment;

    public Ticket(Screen screen, Customer customer, List<Seat> seats, Payment payment) {
        this.screen = screen;
        this.customer = customer;
        this.seats = seats;
        this.payment = payment;
    }

    public Screen getScreen() {
        return this.screen;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public List<Seat> getSeats() {
        return this.seats;
    }

    public Payment getPayment() {
        return this.payment;
    }

    public int getTotalCost() {
        int totalCost = 0;
        for (Seat seat : this.seats) {
            totalCost += this.screen.getMovie().getDuration() * seat.getId();
        }
        return totalCost;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "\nscreen=" + screen +
                ",\ncustomer=" + customer +
                ",\npayment=" + payment +
                '}';
    }
}

abstract class Payment {
    private int amount;

    public Payment(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return this.amount;
    }

    public abstract String getPaymentMethod();
}

class CreditCardPayment extends Payment {
    private String cardNumber;
    private String cardholderName;
    private String expiryDate;

    public CreditCardPayment(int amount, String cardNumber, String cardholderName, String expiryDate) {
        super(amount);
        this.cardNumber = cardNumber;
        this.cardholderName = cardholderName;
        this.expiryDate = expiryDate;
    }

    public String getCardNumber() {
        return this.cardNumber;
    }

    public String getCardholderName() {
        return this.cardholderName;
    }

    public String getExpiryDate() {
        return this.expiryDate;
    }

    public String getPaymentMethod() {
        return "Credit Card";
    }

    @Override
    public String toString() {
        return "CreditCardPayment{" +
                "\ncardNumber='" + cardNumber + '\'' +
                ",\ncardholderName='" + cardholderName + '\'' +
                ",\nexpiryDate='" + expiryDate + '\'' +
                '}';
    }
}

class PaypalPayment extends Payment {
    private String email;

    public PaypalPayment(int amount, String email) {
        super(amount);
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPaymentMethod() {
        return "PayPal";
    }

    @Override
    public String toString() {
        return "PaypalPayment{" +
                "\nemail='" + email + '\'' +
                '}';
    }
}

class MovieRating {
    private Movie movie;
    private int rating;
    private String movieTitle;
    private String customerName;


    public MovieRating(Movie movie, int rating) {
        this.movie = movie;
        this.rating = rating;
    }

    public Movie getMovie() {
        return this.movie;
    }

    public int getRating() {
        return this.rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
    public String getMovieTitle() {
        return movieTitle;
    }

    public String getCustomerName() {
        return customerName;
    }

    @Override
    public String toString() {
        return "MovieRating{" +
                "\nmovie=" + movie +
                ",\nrating=" + rating +
                ",\nmovieTitle='" + movieTitle + '\'' +
                ",\ncustomerName='" + customerName + '\'' +
                '}';
    }
}

class BookingSystem {
    private List<Customer> customers;
    private List<Movie> movies;
    private List<Screen> screens;
    private List<Seat> seats;
    private List<Promotion> promotions;
    private List<MovieRating> movieRatings;

    public BookingSystem() {
        this.customers = new ArrayList<>();
        this.movies = new ArrayList<>();
        this.screens = new ArrayList<>();
        this.seats = new ArrayList<>();
        this.promotions = new ArrayList<>();
        this.movieRatings = new ArrayList<>();

    }

    public void addCustomer(Customer customer) {
        this.customers.add(customer);
    }

    public void addMovie(Movie movie) {
        this.movies.add(movie);
    }
    public void addPromotion(Promotion promotion) {
        this.promotions.add(promotion);
    }

    public List<Promotion> getPromotions() {
        return this.promotions;
    }
    public int calculateDiscount(List<Seat> seats) {
        int totalDiscount = 0;
        for (Promotion promotion : this.promotions) {
            totalDiscount += promotion.calculateDiscount(seats);
        }
        return totalDiscount;
    }

    public void addScreen(Screen screen) {
        this.screens.add(screen);
        for (int i = 1; i <= screen.getCapacity(); i++) {
            this.seats.add(new Seat(i));
        }
    }

    public Customer login(String username, String password) {
        for (Customer customer : this.customers) {
            if (customer.checkLogin(username, password)) {
                return customer;
            }
        }
        return null;
    }

    public List<Movie> getMovies() {
        return this.movies;
    }

    public List<Screen> getScreens() {
        return this.screens;
    }

    public List<Seat> getSeats() {
        return this.seats;
    }

    public List<Screen> getAvailableScreens() {
        List<Screen> availableScreens = new ArrayList<>();
        for (Screen screen : this.screens) {
            if (screen.getMovie() != null) {
                availableScreens.add(screen);
            }
        }
        return availableScreens;
    }

    public List<Seat> getAvailableSeats(Screen screen) {
        List<Seat> availableSeats = new ArrayList<>();
        for (Seat seat : this.seats) {
            if (!seat.isOccupied() && screen.getId() == seat.getId() / 10) {
                availableSeats.add(seat);
            }
        }
        return availableSeats;
    }

    public boolean bookTicket(Screen screen, Customer customer, List<Seat> seats, Payment payment) {
        for (Seat seat : seats) {
            if (seat.isOccupied() || !this.seats.contains(seat)) {
                return false;
            }
        }
        for (Seat seat : seats) {
            seat.occupy();
        }
        Ticket ticket = new Ticket(screen, customer, seats, payment);
        return true;
    }
    public void addMovieRating(Customer customer, Movie movie, int rating) throws IllegalArgumentException {
        if (rating < 1 || rating > 10) {
            throw new IllegalArgumentException("Rating must be between 1 and 10");
        }

        MovieRating movieRating = null;
        for (MovieRating mr : this.movieRatings) {
            if (mr.getMovie().equals(movie) && mr.getCustomerName().equals(customer)) {
                movieRating = mr;
                break;
            }
        }

        if (movieRating == null) {
            movieRating = new MovieRating(movie, rating);
            this.movieRatings.add(movieRating);
        } else {
            movieRating.setRating(rating);
        }
    }

    public double getAverageRating(Movie movie) {
        int totalRating = 0;
        int numRatings = 0;

        for (MovieRating mr : this.movieRatings) {
            if (mr.getMovie().equals(movie)) {
                totalRating += mr.getRating();
                numRatings++;
            }
        }

        if (numRatings == 0) {
            return 0;
        } else {
            return (double) totalRating / numRatings;
        }
    }

    @Override
    public String toString() {
        return "BookingSystem{" +
                " \ncustomers=" + customers +
                ",\nmovies=" + movies +
                ",\nscreens=" + screens +
                "\n }";
    }

}

public class Main {
    public static void main(String[] args) {
        try {
            BookingSystem bookingSystem = new BookingSystem();

            // add customers
            bookingSystem.addCustomer(new Customer("john", "123456", "John Doe", "john@example.com"));

            // add movies
            bookingSystem.addMovie(new Movie("Avengers: Endgame", 182));
            bookingSystem.addMovie(new Movie("Spider-Man: No Way Home", 148));


            // add screens
            bookingSystem.addScreen(new Screen(1, 100, bookingSystem.getMovies().get(0)));
            bookingSystem.addScreen(new Screen(2, 80, bookingSystem.getMovies().get(1)));

            // login
            Customer customer = bookingSystem.login("john", "123456");

            // book ticket
            System.out.println("-------------------------------------------------");
            System.out.println("John Doe is Booking Ticket.....");
            System.out.println("-------------------------------------------------");
            List<Screen> availableScreens = bookingSystem.getAvailableScreens();
            if (availableScreens.size() > 0) {
                Screen screen = availableScreens.get(0);
                List<Seat> availableSeats = bookingSystem.getAvailableSeats(screen);
                List<Seat> selectedSeats = new ArrayList<>();
                selectedSeats.add(availableSeats.get(0));
                Payment payment = new CreditCardPayment(1000, "John Doe", "1234 5678 9012 3456", "05/24");
                if (bookingSystem.bookTicket(screen, customer, selectedSeats, payment)) {
                    System.out.println("Ticket booked successfully!");
                } else {
                    System.out.println("Ticket booking failed!");
                }
            } else {
                System.out.println("No available screens!");
            }
            System.out.println("-------------------------------------------------");
            System.out.println("Overview What all is present in BookingSystem");
            System.out.println("-------------------------------------------------");
            System.out.println(bookingSystem.toString());
            System.out.println("-------------------------------------------------");
            System.out.println("Review of one Movie");
            System.out.println("-------------------------------------------------");
            Movie movie = new Movie("The Shawshank Redemption", 142);
            MovieRating rating = new MovieRating(movie, 9);

            System.out.println("Movie: " + rating.getMovie().getTitle());
            System.out.println("Rating: " + rating.getRating());
            System.out.println("-------------------------------------------------");


            // Create a list of seat types
            List<SeatType> seatTypes = new ArrayList<>();
            seatTypes.add(new SeatType("Regular", 10));
            seatTypes.add(new SeatType("Premium", 15));

            // Create a movie
            movie = new Movie("The Matrix", 120);

            // Create a screen
            Screen screen = new Screen(1, 100, movie);

            // Create some seats
            List<Seat> seats = new ArrayList<>();
            for (int i = 1; i <= screen.getCapacity(); i++) {
                SeatType seatType = seatTypes.get(i % 2); // Alternate between regular and premium seats
                seats.add(new Seat(i, false, seatType));
            }

            // Create a customer
            customer = new Customer("john_Wick", "password123", "John Wick", "john.wick@example.com");

            // Create a payment
            Payment payment = new CreditCardPayment(1000, "John Wick", "1234 5678 9012 3456", "05/24");

            // Create a ticket
            Ticket ticket = new Ticket(screen, customer, seats, payment);

            // Print the ticket
            System.out.println("Joh Wick Ticket Details......");
            System.out.println("-------------------------------------------------");
            System.out.println("Ticket Details:\n"+ticket);
            System.out.println("-------------------------------------------------");

            // Calculate the total cost of the ticket
            int totalCost = ticket.getTotalCost();

            // Apply a promotion to the ticket
            Promotion promotion = new EarlyBirdDiscount();
            int discount = promotion.calculateDiscount(seats);
            int discountedCost = totalCost - (totalCost * discount / 100);

            // Print the total cost and the discounted cost
            System.out.println("Joh Wick got Discount on Ticket Details......");
            System.out.println("-------------------------------------------------");
            System.out.println("Total cost: " + totalCost);
            System.out.println("Discount: " + discount + "%");
            System.out.println("Discounted cost: " + discountedCost);
            System.out.println("-------------------------------------------------");
            // Print the customer details
            System.out.println("Customer Details:");
            System.out.println("-------------------------------------------------");
            System.out.println("Name: " + customer.getName());
            System.out.println("Email: " + customer.getEmail());
            System.out.println("-------------------------------------------------");
            // Print the payment details
            System.out.println("Payment Details:");
            System.out.println(payment.toString());
            System.out.println("-------------------------------------------------");

        }
        catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }



    }
}
