package org.oobootcamp.Entity;

import org.oobootcamp.Exception.InvalidTicketException;
import org.oobootcamp.Exception.ParkingLotFullException;

import java.util.HashMap;

public class ParkingLot {
    private final HashMap<Ticket, Car> parkingLotManager = new HashMap<>();
    private final int capacity;
    public ParkingLot(int i) {
        capacity = i;
    }

    public Car Pick(Ticket ticket) throws InvalidTicketException {
        var car = parkingLotManager.get(ticket);
        if(car == null)
        {
            throw new InvalidTicketException();
        }
        return car;
    }

    public Ticket Park(Car car) throws ParkingLotFullException {
        if(parkingLotManager.size() >= capacity)
        {
            throw new ParkingLotFullException();
        }
        Ticket ticket = new Ticket();
        parkingLotManager.put(ticket,car);
        return ticket;
    }

    public int GetSpacerAmount() {
        return capacity - parkingLotManager.size();
    }

    public boolean IsFull()
    {
        return capacity == parkingLotManager.size();
    }

    public boolean ContainsCar(Ticket ticket)
    {
        return parkingLotManager.containsKey(ticket);
    }
}
