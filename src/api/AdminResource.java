package api;

import model.Customer;
import model.IRoom;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.List;

public class AdminResource {
    private static final AdminResource adminresource = new AdminResource();
    private static final CustomerService customerService= CustomerService.getcustomerservice();
    private static final ReservationService reservationService =ReservationService.getreservationservices();
    public static AdminResource getAdminresource(){return adminresource;}

    public static Customer getCustomer(String email){
        return customerService.getCustomer(email);
    }
    public void addRoom(List<IRoom> rooms){
        for (IRoom room:rooms) {
            reservationService.addRoom(room);
        }
    }

    public Collection<IRoom> getRooms(){
        return  reservationService.getRooms();
    }

    public Collection<Customer> getAllCustomers(){
        return customerService.getAllCustomer();
    }

    public void printAllReservations(){
        reservationService.printAllReservation();
    }
}
