package org.oobootcamp.ParkingBoy;

import org.oobootcamp.Entity.Car;
import org.oobootcamp.Exception.ParkingLotFullException;
import org.oobootcamp.Entity.ParkingLot;
import org.oobootcamp.Entity.Ticket;

import java.util.ArrayList;

public class GraduateParkingBoy extends ParkingBoy {

    public GraduateParkingBoy(ArrayList<ParkingLot> parkingLotList) {
        super(parkingLotList);
    }

    @Override
    public Ticket ParkCar(Car car) throws ParkingLotFullException{
        for (var parkingLot:parkingLotList) {
            if(!parkingLot.IsFull())
            {
                return parkingLot.ParkCar(car);
            }
        }
        throw new ParkingLotFullException();
    }
}
