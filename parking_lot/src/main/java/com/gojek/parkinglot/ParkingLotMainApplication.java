package com.gojek.parkinglot;

import com.gojek.parkinglot.reader.InputReader;
import com.gojek.parkinglot.reader.InputReaderFactory;
import com.gojek.parkinglot.services.ParkingLotService;
import com.gojek.parkinglot.services.impl.ParkingLotServiceImpl;

import java.io.FileNotFoundException;

/**
 * Created by azeem.khan on 8/April/19.
 */
public class ParkingLotMainApplication {
    private InputReaderFactory inputReaderFactory;
    private InputReader inputReader;
    private ParkingLotService parkingLotService;

    public ParkingLotMainApplication(String[] args) throws FileNotFoundException {
        inputReaderFactory = new InputReaderFactory();
        parkingLotService = new ParkingLotServiceImpl();
        inputReader = inputReaderFactory.build(args, parkingLotService);
    }

    public static void main(String[] args) throws FileNotFoundException {
        ParkingLotMainApplication parkingLotApplication = new ParkingLotMainApplication(args);
        parkingLotApplication.inputReader.processInput();
    }
}
