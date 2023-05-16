package service;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.*;
import java.util.stream.Collectors;

public class ReservationService {

    private static final ReservationService reservationservices = new ReservationService();
    private final Map<String, Collection<Reservation>> Allreservations = new HashMap<>();
    private final Map<String, IRoom> Allrooms = new HashMap<>();

    public static ReservationService getreservationservices() {
        return reservationservices;
    }
    public void addRoom(IRoom room){
        Allrooms.put(room.getRoomNumber(),room);
    }
    public IRoom getARoom(String roomId){
        return Allrooms.get(roomId);
    }

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate,Date checkOutDate){

        Collection<Reservation> custReservations;
        custReservations = getCustomersReservation(customer);
        if (custReservations == null) {
            custReservations = new LinkedList<>();
        }
        Reservation reserveRoom;
     reserveRoom = new Reservation(customer, room, checkInDate, checkOutDate);
        custReservations.add(reserveRoom);
        Allreservations.put(customer.getEmail(), custReservations);

        return reserveRoom;
    }

    public Collection<IRoom> findRooms(Date checkInDate,Date checkOutDate){
        final Collection<IRoom> unAvailableRooms = new LinkedList<>();

        final Collection<Reservation> tempallReservations = new LinkedList<>();

        for(Collection<Reservation> r : Allreservations.values()) {
            tempallReservations.addAll(r);
        }

        for (Reservation reservation : tempallReservations) {
            if ( checkOutDate.after(reservation.getCheckInDate()) && checkInDate.before(reservation.getCheckOutDate())
                    ) {
                unAvailableRooms.add(reservation.getRoom());
            }
        }
        
        Collection<IRoom> RoomsFinded =new LinkedList<>();

        RoomsFinded= Allrooms.values().stream().filter(eachRoom -> unAvailableRooms.stream()
                        .noneMatch(unavailable -> unavailable.equals(eachRoom)))
                .collect(Collectors.toList());
        return RoomsFinded;
    }

    public Collection<IRoom> getRooms() {
        return Allrooms.values();
    }

    public Collection<Reservation> getCustomersReservation(Customer customer){
        Collection<Reservation> customerreservations =new LinkedList<>();
        customerreservations= Allreservations.get(customer.getEmail());
        return customerreservations;

    }

    public void printAllReservation(){
        final Collection<Reservation> allReservation = new LinkedList<>();
        for(Collection<Reservation> reservations : Allreservations.values()) {
            allReservation.addAll(reservations);
        }
        final Collection<Reservation> reservations = allReservation;

        if (reservations.isEmpty()) {
            System.out.println("There is no reservations founded.");
        } else {
            for (Reservation reservation : reservations) {
                System.out.println(reservation + "\n");
            }
        }
    }

}
