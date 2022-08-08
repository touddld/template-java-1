package org.oobootcamp.ParkingBoy;

import org.oobootcamp.Entity.Car;
import org.oobootcamp.Entity.ParkingLot;
import org.oobootcamp.Entity.Ticket;
import org.oobootcamp.Exception.InvalidTicketException;
import org.oobootcamp.Exception.ParkingLotFullException;

import java.util.ArrayList;

public class ParkingManager extends ParkingBoy {
    public ParkingManager(ArrayList<ParkingLot> parkingLotList, ArrayList<ParkingBoy> parkingBoys) {
        super(parkingLotList);
        this.parkingBoys = parkingBoys;
    }

    public final ArrayList<ParkingBoy> parkingBoys;

    @Override
    public Ticket Park(Car car) throws ParkingLotFullException {
        if(!IsFull())
        {
            return ParkInOwnParkingLot(car);
        }
        return ParkInParkingBoyLot(car);
    }

    private Ticket ParkInOwnParkingLot(Car car) throws ParkingLotFullException
    {
        for (var parkingLot: parkingLotList) {
            if(!parkingLot.IsFull())
            {
                return parkingLot.Park(car);
            }
        }
        throw new ParkingLotFullException();
    }

    private Ticket ParkInParkingBoyLot(Car car) throws ParkingLotFullException
    {
        for (var parkingBoy: parkingBoys) {
            if(!parkingBoy.IsFull())
            {
                return parkingBoy.Park(car);
            }
        }
        throw new ParkingLotFullException();
    }

    @Override
    public Car Pick(Ticket ticket) throws InvalidTicketException {
        if(ContainsCar(ticket))
        {
            return PickCarFromOwnParkingLot(ticket);
        }
        return PickCarFromParkingBoyLot(ticket);
    }

    private Car PickCarFromOwnParkingLot(Ticket ticket) throws InvalidTicketException
    {
        for (var parkingLot:parkingLotList) {
            if(parkingLot.ContainsCar(ticket))
            {
                return parkingLot.Pick(ticket);
            }
        }
        throw new InvalidTicketException();
    }

    private Car PickCarFromParkingBoyLot(Ticket ticket) throws InvalidTicketException
    {
        for (var parkingBoy: parkingBoys) {
            if(parkingBoy.ContainsCar(ticket))
            {
                return parkingBoy.Pick(ticket);
            }
        }
        throw new InvalidTicketException();
    }
}
