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

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class GraduateParkingBoyTest {

    //AC1 小弟管理的多个停车场，都没有停车，停入第一个停车场
    @Test
    public void should_park_into_parkingLot_A_and_get_ticket_given_0_car_in_parkingLot_A_and_0_car_in_parkingLot_B_When_park_car() throws Exception {
        ParkingLot parkingLotA = new ParkingLot(4);
        ParkingLot parkingLotB = new ParkingLot(3);
        var car = new Car();
        ArrayList<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(parkingLotA);
        parkingLotList.add(parkingLotB);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLotList);
        Ticket ticket = graduateParkingBoy.ParkCar(car);

        assertThat(parkingLotA.GetCar(ticket)).isEqualTo(car);
    }

    //AC2 小弟管理的多个停车场，其中有一部分停车场未停满，停车时按顺序停入第一个未停满的停车场
    @Test
    public void should_park_into_parkingLot_A_and_get_ticket_given_3_car_in_parkingLot_A_and_0_car_in_parkingLot_B_When_park_car() throws Exception {
        ParkingLot parkingLotA = new ParkingLot(4);
        ParkingLot parkingLotB = new ParkingLot(3);
        parkingLotA.ParkCar(new Car());
        parkingLotA.ParkCar(new Car());
        parkingLotA.ParkCar(new Car());
        var car = new Car();
        ArrayList<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(parkingLotA);
        parkingLotList.add(parkingLotB);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLotList);
        Ticket ticket = graduateParkingBoy.ParkCar(car);

        assertThat(parkingLotA.GetCar(ticket)).isEqualTo(car);
    }

    @Test
    public void should_park_into_parkingLot_B_and_get_ticket_given_4_car_in_parkingLot_A_and_2_car_in_parkingLot_B_When_park_car () throws Exception
    {
        ParkingLot parkingLotA = new ParkingLot(4);
        ParkingLot parkingLotB = new ParkingLot(3);
        parkingLotA.ParkCar(new Car());
        parkingLotA.ParkCar(new Car());
        parkingLotA.ParkCar(new Car());
        parkingLotA.ParkCar(new Car());
        parkingLotB.ParkCar(new Car());
        parkingLotB.ParkCar(new Car());
        var car = new Car();
        ArrayList<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(parkingLotA);
        parkingLotList.add(parkingLotB);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLotList);
        Ticket ticket = graduateParkingBoy.ParkCar(car);

        assertThat(parkingLotB.GetCar(ticket)).isEqualTo(car);
    }


    //AC3：小弟管理的所有停车场停满，不能停车，提示 “停车位已满”
    @Test
    public void should_not_park_into_parkingLot_and_not_get_ticket_given_4_car_in_parkingLot_A_and_3_car_in_parkingLot_B_When_park_car() throws Exception
    {
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
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLotList);

        Assertions.assertThrows(ParkingLotFullException.class, () ->graduateParkingBoy.ParkCar(car));
    }

    //AC4：小弟凭自己管理的停车场中的有效票取到对应的车
    @Test
    public void should_get_my_car_given_my_car_in_parkingLot_A_When_get_car() throws Exception
    {
        ParkingLot parkingLotA = new ParkingLot(1);
        var car = new Car();
        var ticket = parkingLotA.ParkCar(car);
        ArrayList<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(parkingLotA);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLotList);

        assertThat(graduateParkingBoy.PickCar(ticket)).isEqualTo(car);
    }

    //AC5：小弟凭无效票取车失败，获得提示信息“无效 Ticket”
    @Test
    public void should_not_get_car_given_my_car_in_parkingLot_A_When_get_car() throws Exception
    {
        ParkingLot parkingLotA = new ParkingLot(1);
        var car = new Car();
        parkingLotA.ParkCar(car);
        var ticket = new Ticket();
        ArrayList<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(parkingLotA);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLotList);

        Assertions.assertThrows(InvalidTicketException.class, () ->graduateParkingBoy.PickCar(ticket));
    }
}
