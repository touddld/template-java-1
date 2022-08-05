package org.oobootcamp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.oobootcamp.Entity.Car;
import org.oobootcamp.Entity.ParkingLot;
import org.oobootcamp.Entity.Ticket;
import org.oobootcamp.Exception.InvalidTicketException;
import org.oobootcamp.Exception.ParkingLotFullException;

import static org.assertj.core.api.Assertions.assertThat;

public class ParkingLotTest {
    @Test
    public void should_not_park_given_parking_lot_is_full_when_park() throws Exception
    {
        ParkingLot parkingLotA =new ParkingLot(1);
        parkingLotA.Park(new Car());

        Assertions.assertThrows(ParkingLotFullException.class, () ->parkingLotA.Park(new Car()));
    }

    @Test
    public void should_park_given_parking_lot_is_not_full_when_park() throws Exception
    {
        ParkingLot parkingLotA =new ParkingLot(1);
        var ticket = parkingLotA.Park(new Car());

        assertThat(ticket).isNotNull();
    }

    @Test
    public void should_pick_car_given_valid_ticket_when_pick() throws Exception
    {
        ParkingLot parkingLotA =new ParkingLot(1);
        var car = new Car();
        var ticket = parkingLotA.Park(car);

        assertThat(parkingLotA.Pick(ticket)).isEqualTo(car);
    }

    @Test
    public void should_not_pick_car_given_invalid_ticket_when_pick() throws Exception
    {
        ParkingLot parkingLotA =new ParkingLot(1);
        parkingLotA.Park(new Car());
        var ticket = new Ticket();

        Assertions.assertThrows(InvalidTicketException.class, () ->parkingLotA.Pick(ticket));
    }
}
