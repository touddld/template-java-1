package org.oobootcamp.ParkingBoy;

import org.oobootcamp.Entity.Car;
import org.oobootcamp.Exception.InvalidTicketException;
import org.oobootcamp.Exception.ParkingLotFullException;
import org.oobootcamp.Entity.ParkingLot;
import org.oobootcamp.Entity.Ticket;

import java.util.ArrayList;

public class SmartParkingBoy extends ParkingBoy{
    public SmartParkingBoy(ArrayList<ParkingLot> parkingLotList) {
        super(parkingLotList);
    }

    @Override
    public Ticket Park(Car car) throws ParkingLotFullException {
        var parkingLot = GetMostSpacerParkingLot();
        if(parkingLot != null)
        {
            return parkingLot.Park(car);
        }
        throw new ParkingLotFullException();
    }

    private ParkingLot GetMostSpacerParkingLot()
    {
        int max = 0;
        ParkingLot mostSpacerParkingLot = null;
        for (var parkingLot:parkingLotList) {
            if(parkingLot.GetSpacerAmount() > max)
            {
                max = parkingLot.GetSpacerAmount();
                mostSpacerParkingLot = parkingLot;
            }
        }
        return mostSpacerParkingLot;
    }

    @Override
    public Car Pick(Ticket ticket) throws InvalidTicketException {
        for (var parkingLot:parkingLotList) {
            if(parkingLot.ContainsCar(ticket))
            {
                return parkingLot.Pick(ticket);
            }
        }
        throw new InvalidTicketException();
    }
}
