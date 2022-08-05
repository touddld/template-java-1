package org.oobootcamp.ParkingBoy;

import org.oobootcamp.Entity.Car;
import org.oobootcamp.Exception.ParkingLotFullException;
import org.oobootcamp.Entity.ParkingLot;
import org.oobootcamp.Entity.Ticket;

import java.util.ArrayList;

public class SmartParkingBoy extends ParkingBoy{
    public SmartParkingBoy(ArrayList<ParkingLot> parkingLotList) {
        super(parkingLotList);
    }

    @Override
    public Ticket ParkCar(Car car) throws ParkingLotFullException {
        var parkingLot = GetMostSpacerParkingLot();
        if(parkingLot != null)
        {
            return parkingLot.ParkCar(car);
        }
        throw new ParkingLotFullException();
    }

    private ParkingLot GetMostSpacerParkingLot()
    {
        int max = 0;
        ParkingLot mostSpacerParkingLot = null;
        for (var parkingLot:parkingLotList) {
            if(parkingLot.GetSpacer() > max)
            {
                max = parkingLot.GetSpacer();
                mostSpacerParkingLot = parkingLot;
            }
        }
        return mostSpacerParkingLot;
    }
}
