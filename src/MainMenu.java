import api.AdminResource;
import api.HotelResource;
import model.Customer;
import model.IRoom;
import model.Reservation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Scanner;

public class MainMenu {
    private static final HotelResource hotelRefrence = HotelResource.gethotelresource();
    private static final String format = "MM/dd/yyyy";

    public static void start() {
        Scanner scanner = new Scanner(System.in);
        String choice = "";
        boolean counter=true;

        menu();

        try {
            while (counter){
                choice = scanner.nextLine();
                if (choice.length() == 1) {
                    switch (choice.charAt(0)) {
                        case '1':
                            findAndReserve();
                            menu();
                            break;
                        case '2':
                            seeMyReservation();
                            menu();
                            break;
                        case '3':
                            createAccount();
                            menu();
                            break;
                        case '4':
                            AdminMenu.adminMenu();
                            break;
                        case '5':
                            System.out.println("Exit");
                            System.exit(0);
                        default:
                            System.out.println("Invalid input, please select a number for the menu option:");



                    }
                } else {
                    System.out.println("Invalid input, please select a number for the menu option:");

                }
            }
        } catch (StringIndexOutOfBoundsException ex) {
            System.out.println("Empty input received.");
        }
    }
    public static void menu()
    {
        System.out.print("\nWelcome to the Hotel Reservation Application\n" +
                "--------------------------------------------\n" +
                "1. Find and reserve a room\n" +
                "2. See my reservations\n" +
                "3. Create an Account\n" +
                "4. Admin\n" +
                "5. Exit\n" +
                "--------------------------------------------\n" +
                "Please select a number for the menu option:\n");
    }
    private static void findAndReserve() {
        final Scanner reader = new Scanner(System.in);
        try {
        System.out.println("Enter Check-In Date mm/dd/yyyy example 02/01/2020");

            Date checkIn = new SimpleDateFormat(format).parse(reader.nextLine());


        System.out.println("Enter Check-Out Date mm/dd/yyyy example 02/21/2020");
        Date checkOut = new SimpleDateFormat(format).parse(reader.nextLine());
        boolean checkDates = checkIn != null && checkOut != null;

        if (checkDates) {
            Collection<IRoom> roomsAvailable;
            roomsAvailable = hotelRefrence.findARoom(checkIn, checkOut);

            if (roomsAvailable.isEmpty())
                System.out.println("There is no rooms founded.");
            else {
                for (IRoom r:roomsAvailable) {
                    System.out.println(r);
                }
                final Reservation reservation;
                final String bookRoom;
                final String account;
                final String customerEmail;
                final String roomNumber;
                System.out.println("Would you like to book? y/n");
                bookRoom = reader.nextLine();

                if (bookRoom.equalsIgnoreCase("y")) {
                    System.out.println("Do you have an account with us? y/n");
                    account = reader.nextLine();

                    if (account.equalsIgnoreCase("y")) {
                        System.out.println("Enter Email format: name@domain.com");
                        customerEmail = reader.nextLine();
                       Customer alreadyExist=hotelRefrence.getCustomer(customerEmail);
                        if (!(alreadyExist== null)) {

                            System.out.println("What room number would you like to reserve?");
                            roomNumber = reader.nextLine();
                            boolean checkRoom=roomsAvailable.stream().anyMatch(room -> room.getRoomNumber().equals(roomNumber));
                            if (checkRoom) {
                                final IRoom room = hotelRefrence.getRoom(roomNumber);
                                reservation = hotelRefrence.bookARoom(customerEmail, room, checkIn, checkOut);
                                System.out.println("Reservation created successfully!");
                                System.out.println(reservation);
                            } else {
                                System.out.println("room number is not available. try to reserve again.");
                            }
                        } else {
                            System.out.println("Customer not found.\nYou need to create a new account.");
                        }

                    } else {
                        System.out.println("Please, create an account then make reservation.");
                    }
                } else if (bookRoom.equalsIgnoreCase("n")){
                    System.out.println("Back to menu.");           }
        }
        }} catch (ParseException ex) {
            System.out.println("Invalid date.");
        }}



    private static void seeMyReservation() {

            final Scanner scanner = new Scanner(System.in);
            System.out.println("Enter your Email with format: name@domain.com");
            final String customerEmail = scanner.nextLine();
            Customer checkCustomer=AdminResource.getCustomer(customerEmail);
        if (checkCustomer!= null){
                final Collection<Reservation> reservations= hotelRefrence.getCustomersReservations(customerEmail);
                if (reservations == null ) {
                    System.out.println("No reservations found.");
                } else if (reservations.isEmpty()) {
                System.out.println("No reservations found.");
            }  else {
                    for (Reservation r:reservations) {
                        System.out.println(r);
                    }
                }
            }
            else {
                System.out.println("invalid email please try again");
            }
    }



    private static void createAccount() {
        final Scanner scanner = new Scanner(System.in);

            System.out.println("Enter Email format: name@domain.com");
            final String email = scanner.nextLine();

        System.out.println("First Name:");
        final String firstName = scanner.nextLine();

        System.out.println("Last Name:");
        final String lastName = scanner.nextLine();
        try {
            hotelRefrence.createACustomer(email, firstName, lastName);
            System.out.println("Account created successfully!");

        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getLocalizedMessage());
            createAccount();
        }
    }


}
