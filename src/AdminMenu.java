import api.AdminResource;
import model.*;

import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

public class AdminMenu {

    private static final AdminResource adminResource = AdminResource.getAdminresource();

    public static void adminMenu() {
        String choice = "";
        final Scanner scanner = new Scanner(System.in);
        boolean counter=true;

        printMenu();

        try {
            while (counter){
                choice = scanner.nextLine();

                    switch (choice.charAt(0)) {
                        case '1':
                            displayCustomers();
                            printMenu();
                            break;
                        case '2':
                            displayRooms();
                            printMenu();
                            break;
                        case '3':
                            adminResource.printAllReservations();
                            printMenu();
                            break;
                        case '4':
                            addRoom();
                            break;
                        case '5':
                            MainMenu.menu();
                            counter=false;
                            break;
                        default:
                            System.out.println("Invalid input, please select a number for the menu option:");
                            break;
                    }

            }
        } catch (StringIndexOutOfBoundsException ex) {
            System.out.println("Empty input received.");
        }
    }

    private static void printMenu() {
        System.out.print("\nAdmin Menu\n" +
                "--------------------------------------------\n" +
                "1. See all Customers\n" +
                "2. See all Rooms\n" +
                "3. See all Reservations\n" +
                "4. Add a Room\n" +
                "5. Back to Main Menu\n" +
                "--------------------------------------------\n" +
                "Please select a number for the menu option:\n");
    }
    private static void displayCustomers() {
        Collection<Customer> customers;
        customers = adminResource.getAllCustomers();

        if (customers.isEmpty()) {
            System.out.println("No customers found.");
        } else {
            for (Customer customer:customers) {
                System.out.println(customer);
            }
        }
    }
    private static void displayRooms() {
        Collection<IRoom> rooms = adminResource.getRooms();

        if(rooms.isEmpty()) {
            System.out.println("No rooms found.");
        } else {
            for (IRoom room:rooms) {
                System.out.println(room);
            }        }
    }
    private static boolean checkExist(String number) {
        Collection<IRoom>  rooms = adminResource.getRooms();
        for( IRoom room : rooms){
            if(room.getRoomNumber().equals(number))
                return true;
        }
        return false;
    }
    private static void addRoom() {
        final Scanner scanner = new Scanner(System.in);
        String roomNumber;
        double roomprice=0;//defaulter
        boolean roomExist;
        RoomType roomType=RoomType.SINGLE;//defaulter
        do{
            System.out.println("Enter room number:");
        roomNumber = scanner.nextLine();
        roomExist = AdminMenu.checkExist(roomNumber);
        if(roomExist)
            System.out.println("Room number already exist please enter another number");
    } while (roomExist);
        System.out.println("Enter price per night:");
        boolean rightNumber=true;
        do{
        try {
            roomprice=Double.parseDouble(scanner.nextLine());
            rightNumber=false;
        } catch (NumberFormatException ex) {
            System.out.println("Please enter a valid number.");
        }}while (rightNumber);

        System.out.println("Enter room type: 1 for single bed, 2 for double bed:");
        boolean checktype=true;
      do {
          try {
              roomType = RoomType.type(scanner.nextLine());
              checktype=false;
          } catch (IllegalArgumentException ex) {
              System.out.println("Invalid room type! Please, choose 1 for single bed or 2 for double bed:");
          }
      }while (checktype);
        final Room room = new Room(roomNumber, roomprice, roomType);

        adminResource.addRoom(Collections.singletonList(room));
        System.out.println("Room added successfully!");

        System.out.println("Would like to add another room? Y/N");
        try {
            String moreRoom;

            moreRoom = scanner.nextLine();

            while (moreRoom.length() != 1  || (moreRoom.charAt(0) != 'N'  && moreRoom.charAt(0) != 'Y'  )) {
                System.out.println("Please enter Y (Yes) or N (No)");
                moreRoom = scanner.nextLine();
            }

            if (moreRoom.charAt(0) == 'Y' ) {
                addRoom();
            } else {
                printMenu();
            }
        } catch (StringIndexOutOfBoundsException ex) {
            anotherRoom();
        }    }



    private static void anotherRoom() {
        final Scanner scanner = new Scanner(System.in);


    }






}
