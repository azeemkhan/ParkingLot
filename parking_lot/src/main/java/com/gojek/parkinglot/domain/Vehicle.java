package com.gojek.parkinglot.domain;

/**
 * Created by azeem.khan on 8/April/19.
 */
public class Vehicle {
    private int slotNumber;
    private String registrationNumber;
    private String color;

    public Vehicle(int slotNumber, String registrationNumber, String color) {
        this.slotNumber = slotNumber;
        this.registrationNumber = registrationNumber;
        this.color = color;
    }

    public int getSlotNumber() {
        return slotNumber;
    }

    public void setSlotNumber(int slotNumber) {
        this.slotNumber = slotNumber;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
