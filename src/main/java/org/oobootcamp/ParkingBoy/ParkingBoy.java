package org.oobootcamp.ParkingBoy;

import org.oobootcamp.Entity.Car;
import org.oobootcamp.Exception.InvalidTicketException;
import org.oobootcamp.Entity.ParkingLot;
import org.oobootcamp.Entity.Ticket;

import java.util.ArrayList;
import java.util.List;

public abstract class ParkingBoy
{
    public List<ParkingLot> parkingLotList;
    public ParkingBoy(ArrayList<ParkingLot> parkingLotList)
    {
        this.parkingLotList = parkingLotList;
    }

    public abstract Ticket Park(Car car) throws Exception;

    public Car Pick(Ticket ticket) throws Exception
    {
        for (var parkingLot:parkingLotList) {
            if(parkingLot.ContainsCar(ticket))
            {
                return parkingLot.Pick(ticket);
            }
        }
        throw new InvalidTicketException();
    }
}
