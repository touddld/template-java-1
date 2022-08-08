package org.oobootcamp.ParkingBoy;

import org.oobootcamp.Entity.Car;
import org.oobootcamp.Exception.InvalidTicketException;
import org.oobootcamp.Entity.ParkingLot;
import org.oobootcamp.Entity.Ticket;
import org.oobootcamp.Exception.ParkingLotFullException;

import java.util.ArrayList;
import java.util.List;

public abstract class ParkingBoy
{
    public List<ParkingLot> parkingLotList;
    public ParkingBoy(ArrayList<ParkingLot> parkingLotList)
    {
        this.parkingLotList = parkingLotList;
    }

    public abstract Ticket Park(Car car) throws ParkingLotFullException;

    public abstract Car Pick(Ticket ticket) throws InvalidTicketException;

    public boolean IsFull()
    {
        for (var parkingLot: parkingLotList) {
            if(!parkingLot.IsFull())
                return false;
        }
        return true;
    }

    public boolean ContainsCar(Ticket ticket)
    {
        for (var parkingLot: parkingLotList) {
            if(parkingLot.ContainsCar(ticket))
                return true;
        }
        return false;
    }

}
