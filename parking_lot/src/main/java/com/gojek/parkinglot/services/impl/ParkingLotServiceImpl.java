package com.gojek.parkinglot.services.impl;

import com.gojek.parkinglot.constants.ParkingLotConstants;
import com.gojek.parkinglot.domain.Vehicle;
import com.gojek.parkinglot.provider.InMemoryDbUtility;
import com.gojek.parkinglot.services.ParkingLotService;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

/**
 * Created by azeem.khan on 8/April/19.
 */
public class ParkingLotServiceImpl implements ParkingLotService {

    public ParkingLotServiceImpl() {
        this(-1);
    }

    public ParkingLotServiceImpl(int maxNoOfVehicles) {
        InMemoryDbUtility.slotToCarMapping = Maps.newTreeMap();
        InMemoryDbUtility.registrationToCarMapping = Maps.newTreeMap();
        InMemoryDbUtility.colorToCarMapping = ArrayListMultimap.create();
        InMemoryDbUtility.maxNoOfVehicles = maxNoOfVehicles;
    }

    public String parkCar(String registrationNo, String color) {
        if (!isParkingLotCreated()) {
            return ParkingLotConstants.PARKING_LOT_NOT_CREATED_MSG;
        } else if (InMemoryDbUtility.vacantSlots.isEmpty()) {
            return ParkingLotConstants.PARKING_FULL_MSG;
        } else {
            int slotNum = InMemoryDbUtility.vacantSlots.remove();
            Vehicle newCar = new Vehicle(slotNum, registrationNo, color);
            InMemoryDbUtility.colorToCarMapping.put(color, newCar);
            InMemoryDbUtility.registrationToCarMapping.put(registrationNo, newCar);
            InMemoryDbUtility.slotToCarMapping.put(slotNum, newCar);
            return String.format(ParkingLotConstants.PARKING_ALLOTTED_MSG, slotNum);
        }
    }

    public String leaveParkingLot(int parkingSlotNo) {
        if (!isParkingLotCreated()) {
            return ParkingLotConstants.PARKING_LOT_NOT_CREATED_MSG;
        } else if (InMemoryDbUtility.slotToCarMapping.containsKey(parkingSlotNo)) {
            Vehicle carToLeave = InMemoryDbUtility.slotToCarMapping.remove(parkingSlotNo);
            InMemoryDbUtility.registrationToCarMapping.remove(carToLeave.getRegistrationNumber());
            List<Vehicle> colorList = (List<Vehicle>) InMemoryDbUtility.colorToCarMapping.get(carToLeave.getColor());
            colorList.remove(carToLeave);
            InMemoryDbUtility.vacantSlots.add(carToLeave.getSlotNumber());
            return String.format(ParkingLotConstants.PARKING_LEAVE_MSG, parkingSlotNo);
        } else {
            return ParkingLotConstants.NOT_FOUND_MSG;
        }
    }

    public void parkingStatus() {

    }

    public String registrationNosofCarsWithColor(String color) {
        if (!isParkingLotCreated()) {
            return ParkingLotConstants.PARKING_LOT_NOT_CREATED_MSG;
        } else if (InMemoryDbUtility.colorToCarMapping.containsKey(color)) {
            List<Vehicle> colorList = (List<Vehicle>) InMemoryDbUtility.colorToCarMapping.get(color);
            List<String> registrationNumbers = colorList.stream().
                    map(Vehicle::getRegistrationNumber).
                    collect(Collectors.toList());
            return StringUtils.join(registrationNumbers, ", ");
        } else {
            return ParkingLotConstants.NOT_FOUND_MSG;
        }
    }

    public String slotNosOfCarsWithColor(String color) {
        if (!isParkingLotCreated()) {
            return ParkingLotConstants.PARKING_LOT_NOT_CREATED_MSG;
        } else if (InMemoryDbUtility.colorToCarMapping.containsKey(color)) {
            List<Vehicle> colorList = (List<Vehicle>) InMemoryDbUtility.colorToCarMapping.get(color);
            List<Integer> slotNums = colorList.stream().
                    map(Vehicle::getSlotNumber).
                    collect(Collectors.toList());
            return StringUtils.join(slotNums, ", ");
        } else {
            return ParkingLotConstants.NOT_FOUND_MSG;
        }

    }

    public String slotNoBasedOnRegistrationNo(String registrationNo) {
        if (!isParkingLotCreated()) {
            return ParkingLotConstants.PARKING_LOT_NOT_CREATED_MSG;
        } else if (InMemoryDbUtility.registrationToCarMapping.containsKey(registrationNo)) {
            return String.valueOf(InMemoryDbUtility.registrationToCarMapping.get(registrationNo).getSlotNumber());
        } else {
            return ParkingLotConstants.NOT_FOUND_MSG;
        }
    }

    @Override
    public String createParkingLot(int maxNoOfVehicles) {
        InMemoryDbUtility.maxNoOfVehicles = maxNoOfVehicles;
        InMemoryDbUtility.vacantSlots = new PriorityQueue(maxNoOfVehicles);
        for (int i = 1; i <= maxNoOfVehicles; i++) {
            InMemoryDbUtility.vacantSlots.add(i);
        }

        return String.format(ParkingLotConstants.PARKING_LOT_CREATED_MSG, maxNoOfVehicles);
    }

    private boolean isParkingLotCreated() {
        return InMemoryDbUtility.maxNoOfVehicles >= 0;
    }
}
