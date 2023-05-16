package api;


import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.Date;

public class HotelResource {
    private static final HotelResource hotelresource = new HotelResource();
    public static HotelResource gethotelresource() {
        return hotelresource;
    }
    public Customer getCustomer(String email){
        return CustomerService.getcustomerservice().getCustomer(email);
    }
public void createACustomer(String email,String firstName,String lastName){
    CustomerService.getcustomerservice().addCustomer(email,firstName,lastName);
}
public IRoom getRoom(String roomNumber){
    return ReservationService.getreservationservices().getARoom(roomNumber);
}

public Reservation bookARoom(String customerEmail, IRoom room, Date checkInDate, Date checkOutDate){
    return ReservationService.getreservationservices().reserveARoom(getCustomer(customerEmail),room,checkInDate,checkOutDate);
}

public Collection<Reservation> getCustomersReservations(String customerEmail){
      Customer customer=CustomerService.getcustomerservice().getCustomer(customerEmail);
    Collection<Reservation> collection = ReservationService.getreservationservices().getCustomersReservation(customer);
   return collection;
}


public Collection<IRoom> findARoom(Date checkInDate,Date checkOutDate){
        return ReservationService.getreservationservices().findRooms(checkInDate, checkOutDate);
}
}
