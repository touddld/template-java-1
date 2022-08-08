package org.oobootcamp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.oobootcamp.Entity.Car;
import org.oobootcamp.Entity.ParkingLot;
import org.oobootcamp.Entity.Ticket;
import org.oobootcamp.Exception.InvalidTicketException;
import org.oobootcamp.Exception.ParkingLotFullException;
import org.oobootcamp.ParkingBoy.GraduateParkingBoy;
import org.oobootcamp.ParkingBoy.ParkingBoy;
import org.oobootcamp.ParkingBoy.ParkingManager;
import org.oobootcamp.ParkingBoy.SmartParkingBoy;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class ParkingManagerTest {
    //AC1：停车经理管理的自己的停车场和多个聪明小弟和毕业小弟，当没有停满车时，可以停车，并返回停车票
    @Test
    public void should_park_in_own_parkingLot_given_1_spacer_in_own_parkingLot_when_parking() throws Exception {
        ParkingLot parkingLotA = new ParkingLot(1);
        ParkingLot parkingLotB = new ParkingLot(1);
        ArrayList arrayListA= new ArrayList();
        arrayListA.add(parkingLotA);
        ArrayList arrayListB= new ArrayList();
        arrayListB.add(parkingLotB);
        ParkingBoy parkingBoyA = new SmartParkingBoy(arrayListB);
        ArrayList<ParkingBoy> parkingBoys = new ArrayList<>();
        parkingBoys.add(parkingBoyA);
        ParkingManager parkingManager = new ParkingManager(arrayListA,parkingBoys);
        Car car = new Car();
        var ticket = parkingManager.Park(car);

        assertThat(parkingLotA.Pick(ticket)).isEqualTo(car);
    }

    @Test
    public void should_park_in_parkingBoys_parkingLot_given_no_spacer_in_own_parkingLot_and_1_in_parkingBoy_parkingLot_when_parking() throws Exception {
        ParkingLot parkingLotA = new ParkingLot(1);
        ParkingLot parkingLotB = new ParkingLot(1);
        parkingLotA.Park(new Car());
        ArrayList arrayListA= new ArrayList();
        arrayListA.add(parkingLotA);
        ArrayList arrayListB= new ArrayList();
        arrayListB.add(parkingLotB);
        ParkingBoy parkingBoyA = new SmartParkingBoy(arrayListB);
        ArrayList<ParkingBoy> parkingBoys = new ArrayList<>();
        parkingBoys.add(parkingBoyA);
        ParkingManager parkingManager = new ParkingManager(arrayListA,parkingBoys);
        Car car = new Car();
        var ticket = parkingManager.Park(car);

        assertThat(parkingLotB.Pick(ticket)).isEqualTo(car);
    }

    // AC2：<停车经理管理的自己的停车场和多个聪明小弟、毕业小弟，均停满车时，无法停车，提示停车位已满>
    @Test
    public void should_not_park_given_no_spacer_in_own_parkingLot_and_no_spacer_in_parkingBoy_parkingLot_when_parking() throws Exception
    {
        ParkingLot parkingLotA = new ParkingLot(1);
        ParkingLot parkingLotB = new ParkingLot(1);
        parkingLotA.Park(new Car());
        parkingLotB.Park(new Car());
        ArrayList arrayListA= new ArrayList();
        arrayListA.add(parkingLotA);
        ArrayList arrayListB= new ArrayList();
        arrayListB.add(parkingLotB);
        ParkingBoy parkingBoyA = new SmartParkingBoy(arrayListB);
        ArrayList<ParkingBoy> parkingBoys = new ArrayList<>();
        parkingBoys.add(parkingBoyA);
        ParkingManager parkingManager = new ParkingManager(arrayListA,parkingBoys);
        Car car = new Car();

        Assertions.assertThrows(ParkingLotFullException.class, () ->parkingManager.Park(car));
    }

    //AC3：<我（VIP用户）拿着有效的停车票让停车经理取车，可以取到车>
    @Test
    public void should_get_car_given_valid_ticket_and_car_in_own_parkingLot_when_picking_car() throws Exception
    {
        ParkingLot parkingLotA = new ParkingLot(1);
        ParkingLot parkingLotB = new ParkingLot(1);
        Car car = new Car();
        Ticket ticket = parkingLotA.Park(car);
        ArrayList arrayListA= new ArrayList();
        arrayListA.add(parkingLotA);
        ArrayList arrayListB= new ArrayList();
        arrayListB.add(parkingLotB);
        ParkingBoy parkingBoyA = new SmartParkingBoy(arrayListB);
        ArrayList<ParkingBoy> parkingBoys = new ArrayList<>();
        parkingBoys.add(parkingBoyA);
        ParkingManager parkingManager = new ParkingManager(arrayListA,parkingBoys);

        assertThat(parkingManager.Pick(ticket)).isEqualTo(car);
    }

    @Test
    public void should_get_car_given_valid_ticket_and_car_in_parkingBoy_parkingLot_when_picking_car() throws Exception
    {
        ParkingLot parkingLotA = new ParkingLot(1);
        ParkingLot parkingLotB = new ParkingLot(1);
        Car car = new Car();
        Ticket ticket = parkingLotB.Park(car);
        ArrayList arrayListA= new ArrayList();
        arrayListA.add(parkingLotA);
        ArrayList arrayListB= new ArrayList();
        arrayListB.add(parkingLotB);
        ParkingBoy parkingBoyA = new SmartParkingBoy(arrayListB);
        ArrayList<ParkingBoy> parkingBoys = new ArrayList<>();
        parkingBoys.add(parkingBoyA);
        ParkingManager parkingManager = new ParkingManager(arrayListA,parkingBoys);

        assertThat(parkingManager.Pick(ticket)).isEqualTo(car);
    }

    //AC4：<我（VIP）拿着无效票让停车经理取车，提示取车失败，无法取到车>
    @Test
    public void should_get_car_given_invalid_ticket_when_picking_car() throws Exception
    {
        ParkingLot parkingLotA = new ParkingLot(1);
        ParkingLot parkingLotB = new ParkingLot(1);
        parkingLotA.Park(new Car());
        ArrayList arrayListA= new ArrayList();
        arrayListA.add(parkingLotA);
        ArrayList arrayListB= new ArrayList();
        arrayListB.add(parkingLotB);
        ParkingBoy parkingBoyA = new SmartParkingBoy(arrayListB);
        ArrayList<ParkingBoy> parkingBoys = new ArrayList<>();
        parkingBoys.add(parkingBoyA);
        ParkingManager parkingManager = new ParkingManager(arrayListA,parkingBoys);

        Assertions.assertThrows(InvalidTicketException.class, () ->parkingManager.Pick(new Ticket()));
    }
}
