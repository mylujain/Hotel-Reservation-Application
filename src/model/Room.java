package model;

import java.util.Objects;

public class Room implements IRoom {

    private final String roomNumber;
   private final Double price;
   private final RoomType enumeration;
    public Room(String roomNumber,Double price, RoomType enumeration){
        this.roomNumber=roomNumber;
        this.price=price;
        this.enumeration=enumeration;

    }

    @Override
    public String getRoomNumber() {
        return roomNumber;
    }

    @Override
    public Double getRoomPrice() {
        return price;
    }

    @Override
    public RoomType getRoomType() {
        return enumeration;
    }

    @Override
    public boolean isFree() {
        return price != null && price.equals(0.0);
    }

    @Override
    public String toString() {
        return "the room number is: "+roomNumber+" "+"the room price is: $"+price+" "+"the room type is: "+enumeration;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }

        if((obj == null) || (obj.getClass() != this.getClass()))
            return false;

        final Room room = (Room) obj;
        return Objects.equals(this.roomNumber, room.roomNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomNumber);
    }
}
