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
import org.oobootcamp.ParkingBoy.SmartParkingBoy;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class SmartParkingBoyTest {

    //AC1：A、B均未停满，顺序停入空车位最多的A停车场
    @Test
    public void should_park_into_parkingLot_A_and_get_ticket_given_2_car_in_parkingLot_A_and_1_car_in_parkingLot_B_When_park_car() throws Exception
    {
        ParkingLot parkingLotA = new ParkingLot(4);
        ParkingLot parkingLotB = new ParkingLot(3);
        parkingLotA.ParkCar(new Car());
        parkingLotA.ParkCar(new Car());
        parkingLotB.ParkCar(new Car());
        ArrayList<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(parkingLotA);
        parkingLotList.add(parkingLotB);
        var car = new Car();
        ParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLotList);
        Ticket ticket = smartParkingBoy.ParkCar(car);

        assertThat(parkingLotA.GetCar(ticket)).isEqualTo(car);
    }

    @Test
    public void should_park_into_parkingLot_B_and_get_ticket_given_3_car_in_parkingLot_A_and_1_car_in_parkingLot_B_When_park_car() throws Exception
    {
        ParkingLot parkingLotA = new ParkingLot(4);
        ParkingLot parkingLotB = new ParkingLot(3);
        parkingLotA.ParkCar(new Car());
        parkingLotA.ParkCar(new Car());
        parkingLotA.ParkCar(new Car());
        parkingLotB.ParkCar(new Car());
        ArrayList<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(parkingLotA);
        parkingLotList.add(parkingLotB);
        var car = new Car();
        ParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLotList);
        Ticket ticket = smartParkingBoy.ParkCar(car);

        assertThat(parkingLotB.GetCar(ticket)).isEqualTo(car);
    }

    @Test
    public void should_park_into_parkingLot_A_and_get_ticket_given_1_car_in_parkingLot_A_and_1_car_in_parkingLot_B_When_park_car() throws Exception
    {
        ParkingLot parkingLotA = new ParkingLot(4);
        ParkingLot parkingLotB = new ParkingLot(3);
        parkingLotA.ParkCar(new Car());
        parkingLotB.ParkCar(new Car());
        ArrayList<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(parkingLotA);
        parkingLotList.add(parkingLotB);
        var car = new Car();
        ParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLotList);
        Ticket ticket = smartParkingBoy.ParkCar(car);

        assertThat(parkingLotA.GetCar(ticket)).isEqualTo(car);
    }

    @Test
    public void should_park_into_parkingLot_B_and_get_ticket_given_4_car_in_parkingLot_A_and_1_car_in_parkingLot_B_When_park_car() throws Exception
    {
        ParkingLot parkingLotA = new ParkingLot(4);
        ParkingLot parkingLotB = new ParkingLot(3);
        parkingLotA.ParkCar(new Car());
        parkingLotA.ParkCar(new Car());
        parkingLotA.ParkCar(new Car());
        parkingLotA.ParkCar(new Car());
        parkingLotB.ParkCar(new Car());
        ArrayList<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(parkingLotA);
        parkingLotList.add(parkingLotB);
        var car = new Car();
        ParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLotList);
        Ticket ticket = smartParkingBoy.ParkCar(car);

        assertThat(parkingLotB.GetCar(ticket)).isEqualTo(car);
    }

    //AC2：聪明小弟管理多个停车场，停车场均无空位，停车失败，提示“停车位已满”
    @Test
    public void should_not_park_into_parkingLot_and_not_get_ticket_given_4_car_in_parkingLot_A_and_3_car_in_parkingLot_B_When_park_car() throws Exception {
        ParkingLot parkingLotA = new ParkingLot(4);
        ParkingLot parkingLotB = new ParkingLot(3);
        parkingLotA.ParkCar(new Car());
        parkingLotA.ParkCar(new Car());
        parkingLotA.ParkCar(new Car());
        parkingLotA.ParkCar(new Car());
        parkingLotB.ParkCar(new Car());
        parkingLotB.ParkCar(new Car());
        parkingLotB.ParkCar(new Car());
        var car = new Car();
        ArrayList<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(parkingLotA);
        parkingLotList.add(parkingLotB);
        ParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLotList);

        Assertions.assertThrows(ParkingLotFullException.class, () ->smartParkingBoy.ParkCar(car));
    }

    //AC3：聪明小弟凭自己管理的停车场中的有效票取到对应的车
    @Test
    public void should_get_my_car_given_my_car_in_parkingLot_A_When_get_car() throws Exception
    {
        ParkingLot parkingLotA = new ParkingLot(1);
        var car = new Car();
        var ticket = parkingLotA.ParkCar(car);
        ArrayList<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(parkingLotA);
        ParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLotList);

        assertThat(smartParkingBoy.PickCar(ticket)).isEqualTo(car);
    }

    //AC4：聪明小弟凭无效票取车失败，获得提示信息“无效 Ticket”
    @Test
    public void should_not_get_car_given_my_car_in_parkingLot_A_When_get_car() throws Exception
    {
        ParkingLot parkingLotA = new ParkingLot(1);
        var car = new Car();
        parkingLotA.ParkCar(car);
        var ticket = new Ticket();
        ArrayList<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(parkingLotA);
        ParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLotList);

        Assertions.assertThrows(InvalidTicketException.class, () ->smartParkingBoy.PickCar(ticket));
    }
}
