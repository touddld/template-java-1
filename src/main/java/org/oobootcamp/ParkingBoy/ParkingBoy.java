package org.oobootcamp.ParkingBoy;

import org.oobootcamp.Entity.Car;
import org.oobootcamp.Exception.InvalidTicketException;
import org.oobootcamp.Entity.ParkingLot;
import org.oobootcamp.Entity.Ticket;

import java.util.ArrayList;

public abstract class ParkingBoy
{
    public ArrayList<ParkingLot> parkingLotList;
    public ParkingBoy(ArrayList<ParkingLot> parkingLotList)
    {
        this.parkingLotList = parkingLotList;
    }

    public abstract Ticket ParkCar(Car car) throws Exception;

    public Car PickCar(Ticket ticket) throws Exception
    {
        for (var parkingLot:parkingLotList) {
            if(parkingLot.ContainCar(ticket))
            {
                return parkingLot.GetCar(ticket);
            }
        }
        throw new InvalidTicketException();
    }
}
