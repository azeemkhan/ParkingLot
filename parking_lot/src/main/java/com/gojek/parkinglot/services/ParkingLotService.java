package com.gojek.parkinglot.services;

/**
 * Created by azeem.khan on 8/April/19.
 */
public interface ParkingLotService {
    String parkCar(String registrationNo, String color);
    String leaveParkingLot(int parkingSlotNo);
    void parkingStatus();
    String registrationNosofCarsWithColor(String color);
    String slotNosOfCarsWithColor(String color);
    String slotNoBasedOnRegistrationNo(String registrationNo);
    String createParkingLot(int maxNoOfVehicles);
}
