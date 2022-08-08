package org.oobootcamp.ParkingBoy;

import org.oobootcamp.Entity.Car;
import org.oobootcamp.Exception.InvalidTicketException;
import org.oobootcamp.Exception.ParkingLotFullException;
import org.oobootcamp.Entity.ParkingLot;
import org.oobootcamp.Entity.Ticket;

import java.util.ArrayList;

public class GraduateParkingBoy extends ParkingBoy {

    public GraduateParkingBoy(ArrayList<ParkingLot> parkingLotList) {
        super(parkingLotList);
    }

    @Override
    public Ticket Park(Car car) throws ParkingLotFullException{
        for (var parkingLot:parkingLotList) {
            if(!parkingLot.IsFull())
            {
                return parkingLot.Park(car);
            }
        }
        throw new ParkingLotFullException();
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
