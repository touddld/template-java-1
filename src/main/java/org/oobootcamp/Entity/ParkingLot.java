package org.oobootcamp.Entity;

import org.oobootcamp.Exception.InvalidTicketException;
import org.oobootcamp.Exception.ParkingLotFullException;

import java.util.HashMap;

public class ParkingLot {
    private HashMap<Ticket, Car> parkingLotManager = new HashMap<>();
    private int capacity;
    public ParkingLot(int i) {
        capacity = i;
    }

    public Car GetCar(Ticket ticket) throws InvalidTicketException {
        var car = parkingLotManager.get(ticket);
        if(car == null)
        {
            throw new InvalidTicketException();
        }
        return car;
    }

    public Ticket ParkCar(Car car) throws ParkingLotFullException {
        if(parkingLotManager.size() >= capacity)
        {
            throw new ParkingLotFullException();
        }
        Ticket ticket = new Ticket();
        parkingLotManager.put(ticket,car);
        return ticket;
    }

    public int GetSpacer() {
        return capacity -parkingLotManager.size();
    }

    public boolean IsFull()
    {
        return capacity == parkingLotManager.size();
    }

    public boolean ContainCar(Ticket ticket)
    {
        return parkingLotManager.containsKey(ticket);
    }
}
